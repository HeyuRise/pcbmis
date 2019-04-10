package com.pcbwx.pcbmis.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.pcbmis.bean.user.MenuAndUrl;
import com.pcbwx.pcbmis.bean.user.MenuItem;
import com.pcbwx.pcbmis.bean.user.OperateLogBean;
import com.pcbwx.pcbmis.component.CacheService;
import com.pcbwx.pcbmis.dao.OperateLogMapper;
import com.pcbwx.pcbmis.dao.RoleAuthMapper;
import com.pcbwx.pcbmis.dao.UserAuthMapper;
import com.pcbwx.pcbmis.dao.UserRoleMapper;
import com.pcbwx.pcbmis.dao.UserRoleRelationMapper;
import com.pcbwx.pcbmis.dao.WxtbUserMapper;
import com.pcbwx.pcbmis.model.Menu;
import com.pcbwx.pcbmis.model.OperateLog;
import com.pcbwx.pcbmis.model.RoleAuth;
import com.pcbwx.pcbmis.model.UserAuth;
import com.pcbwx.pcbmis.model.UserRole;
import com.pcbwx.pcbmis.model.UserRoleRelation;
import com.pcbwx.pcbmis.model.WxtbUser;
import com.pcbwx.pcbmis.service.SupportService;
import com.pcbwx.pcbmis.service.UserService;
import com.pcbwx.pcbmis.utils.DateTimeUtil;

@Service("userService")
public class UserServiceImpl implements UserService{
	
//	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private WxtbUserMapper wxtbUserMapper;
	@Autowired
	private UserRoleRelationMapper userRoleRelationMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private UserAuthMapper userAuthMapper;
	@Autowired
	private RoleAuthMapper roleAuthMapper;
	@Autowired
	private OperateLogMapper operateLogMapper;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private SupportService supportService;
	
	
	@Override
	public Map<String, Object> getUserDetail(AkAuthUser wxtbUser) {
		Map<String, Object> response = new HashMap<String, Object>();
		String userName = wxtbUser.getUsername();
		String userCode = wxtbUser.getUserCode();
		String account = wxtbUser.getAccount();
		response.put("userName", userName);
		response.put("userCode", userCode);
		response.put("account", account);
		List<String> roleNames = new ArrayList<String>();
		List<UserRole> userRoles = cacheService.getUserRole(userCode);
		if (userRoles == null || userRoles.size() == 0) {
			return response;
		}
		for (UserRole userRole : userRoles) {
			roleNames.add(userRole.getRoleName());
		}
		response.put("roles", roleNames);
		Set<Integer> authIds = supportService.getUserAuths(userCode);
		Map<Integer, Menu> menuCache = cacheService.getMenu();
		List<Menu> menus = new ArrayList<Menu>();
		List<Menu> secondMenus = new ArrayList<Menu>();
		for (Map.Entry<Integer, Menu> entry : menuCache.entrySet()) {
			Menu menu = entry.getValue();
			String authTypesStr = menu.getAuthType();
			if (authTypesStr == null) {
				continue;
			}
			// 按钮的权限集合
			String[] sp = authTypesStr.split(",");
			List<String> sps = new ArrayList<String>();
			for (String string : sp) {
				sps.add(string);
			}
			// 查看用户是否存在该权限
			for (Integer authId : authIds) {
				if (sps.contains(authId.toString())) {
					if (menu.getMenuLevel() == 1) {
						menus.add(menu);
					} else {
						secondMenus.add(menu);
					}
					break;
				}
			}
		}
		List<MenuItem> mainMenus = new ArrayList<MenuItem>();
		for (Menu menu : menus) {
			if (menu.getMenuLevel() == 1) {
				MenuItem mainMenu = new MenuItem();
				mainMenu.setTitle(menu.getMenu());
				List<MenuAndUrl> menuItems = new ArrayList<MenuAndUrl>();
				Integer mainMenuId = menu.getMainMenuId();
				for (Menu menuSecond : secondMenus) {
					if (Objects.equals(menuSecond.getMainMenuId(), mainMenuId)) {
						MenuAndUrl menuItem = new MenuAndUrl();
						menuItem.setMenuName(menuSecond.getMenu());
						menuItem.setUrl(menuSecond.getMenuUrl());
						menuItems.add(menuItem);
						mainMenu.setSecondMenu(menuItems);
					}
				}
				mainMenus.add(mainMenu);
			}
		}
		response.put("menu", mainMenus);
		return response;
	}

	@Override
	public Map<String, Object> getlogList(String keyWord, String startTimeStr,
			String stopTimeStr, Integer page, Integer pageSize) {
		Map<String, Object> response = new HashMap<String, Object>();
		Date startTime = DateTimeUtil.dateStr2date(startTimeStr);
		Date stopTime = DateTimeUtil.dateStr2date(stopTimeStr);
		stopTime = DateTimeUtil.getTheDayLastTime(stopTime);
		Integer start = (page - 1) * pageSize;
		Integer total = operateLogMapper.getSelectByConditionNum(keyWord, startTime, stopTime);
		List<OperateLog> operateLogs = operateLogMapper.selectByCondition(keyWord, startTime, stopTime, start, pageSize);
		List<OperateLogBean> operateLogBeans = new ArrayList<OperateLogBean>();
		if (operateLogs == null || operateLogs.size() == 0) {
			return null;
		}
		for (OperateLog operateLog : operateLogs) {
			OperateLogBean operateLogBean = new OperateLogBean();
			operateLogBean.setExceptionMessage(operateLog.getExceptionMessage());
			operateLogBean.setId(operateLog.getId());
			operateLogBean.setOperateContent(operateLog.getOperateContent());
			operateLogBean.setOperateName(operateLog.getOperateName());
			operateLogBean.setOperateResult(operateLog.getOperateResult());
			operateLogBean.setUsername(operateLog.getUsername());
			String operateDateStr = DateTimeUtil.date2dateStr(operateLog.getCreatTime());
			operateLogBean.setOperateTime(operateDateStr);
			operateLogBeans.add(operateLogBean);
		}
		if (total != null) {
			response.put("total", total);
			response.put("rows", operateLogBeans);
			return response;
		}else {
			return null;
		}
	}
	
	public void reloadUserRole(){
		List<UserRole> userRoles = userRoleMapper.load();
		cacheService.reloadUserRole(userRoles);
	}
	public void reloadWxtbUser(){
		List<WxtbUser> wxtbUsers = wxtbUserMapper.load();
		cacheService.reloadWxtbUser(wxtbUsers);
	}
	public void reloadUserRealtion() {
		List<UserRoleRelation> userRoleRelationsNew = userRoleRelationMapper.load();
		cacheService.reloadUserRoleRelation(userRoleRelationsNew);
	}
	public void reloadRoleAuth(){
		List<RoleAuth> roleAuths = roleAuthMapper.load();
		cacheService.reloadRoleAuth(roleAuths);
	}
    public void reloadUserAuth(){
    	List<UserAuth> userAuths = userAuthMapper.load();
    	cacheService.reloadUserAuth(userAuths);
    } 
}
