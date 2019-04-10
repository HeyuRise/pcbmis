package com.pcbwx.pcbmis.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.pcbwx.pcbmis.enums.DictionaryEnum;
import com.pcbwx.pcbmis.enums.FormFieldTypeEnum;
import com.pcbwx.pcbmis.model.BasemAterial;
import com.pcbwx.pcbmis.model.BoardPlyTolerance;
import com.pcbwx.pcbmis.model.CompanyInfo;
import com.pcbwx.pcbmis.model.CraftInfo;
import com.pcbwx.pcbmis.model.Dictionary;
import com.pcbwx.pcbmis.model.EdaGuest;
import com.pcbwx.pcbmis.model.FactoryInfo;
import com.pcbwx.pcbmis.model.FormDefaultPerson;
import com.pcbwx.pcbmis.model.FrameTolerance;
import com.pcbwx.pcbmis.model.JoinBoardWay;
import com.pcbwx.pcbmis.model.Menu;
import com.pcbwx.pcbmis.model.PcbCheckTemplate;
import com.pcbwx.pcbmis.model.PcbReportTemplate;
import com.pcbwx.pcbmis.model.RoleAuth;
import com.pcbwx.pcbmis.model.SurfaceProcess;
import com.pcbwx.pcbmis.model.UserAuth;
import com.pcbwx.pcbmis.model.UserRole;
import com.pcbwx.pcbmis.model.UserRoleRelation;
import com.pcbwx.pcbmis.model.WxtbDepartment;
import com.pcbwx.pcbmis.model.WxtbUser;
import com.pcbwx.pcbmis.utils.DataUtil;

public class CacheServiceImpl implements CacheService {
	private static Logger logger = Logger.getLogger(CacheServiceImpl.class);
	
	// type#id,Dictionary / type|code,Dictionary
	private Map<String, Dictionary> dictionaryCache = new HashMap<String, Dictionary>();
	private Map<String, List<Dictionary>> dictionaryByTypeCache = new HashMap<String, List<Dictionary>>();
	// id,JoinBoardWay
	private Map<Integer, JoinBoardWay> joinBoardWayCache = new HashMap<Integer, JoinBoardWay>();
	
	private Map<String, EdaGuest> edaGuestCache = new HashMap<String, EdaGuest>();	
	private Map<String, WxtbDepartment> wxtbDepartmentCache = new HashMap<String, WxtbDepartment>();
	private Map<Integer, FactoryInfo> factoryCache = new HashMap<Integer, FactoryInfo>();
	private Map<Integer, SurfaceProcess> surfaceProcessCache = new HashMap<Integer, SurfaceProcess>();
	private Map<Integer, CompanyInfo> companyCache = new HashMap<Integer, CompanyInfo>();
	private Map<String, CompanyInfo> companyCodeCache = new HashMap<String, CompanyInfo>();
	private Map<Integer, BoardPlyTolerance> boardPlyToleranceCache = new HashMap<Integer, BoardPlyTolerance>();
	private Map<Integer, BasemAterial> basemAterialCache = new HashMap<Integer, BasemAterial>();
	private Map<Integer, FrameTolerance> frameToleranceCache = new HashMap<Integer, FrameTolerance>();
	private Map<Integer, CraftInfo> craftCache = new HashMap<Integer, CraftInfo>();
	private Map<Integer, PcbCheckTemplate> pcbCheckTemplateCache = new HashMap<>();
	private Map<Integer,PcbReportTemplate> pcbReportTemplateCache = new HashMap<>();
	private Map<String, FormDefaultPerson> formDefaultPersonCache = new HashMap<>();
	
	private Map<Integer, Menu> menuCache = new LinkedHashMap<Integer, Menu>();	
	private Map<Integer, UserRole> userRoleCache = new HashMap<Integer, UserRole>();
	private Map<String, List<UserRoleRelation>> userRoleRelationCache = new HashMap<>();
	private Map<String, WxtbUser> wxtbUserCache = new HashMap<String, WxtbUser>();
	private Map<Integer, UserAuth> userAuthCache = new HashMap<Integer, UserAuth>();
	private Map<Integer, RoleAuth> roleAuthCache = new HashMap<Integer, RoleAuth>();
	
	
	@Override
	public void reloadDictionary(List<Dictionary> dictionarys) {
		logger.info("载入字典表相关信息");
		Map<String, Dictionary> newDictionaryCache = new HashMap<String, Dictionary>();
		if (dictionarys != null && !dictionarys.isEmpty()) {
			for (Dictionary record : dictionarys) {
				if (record.getInnerId() != null) {
					String key = record.getType() + "#" + record.getInnerId();
					newDictionaryCache.put(key, record);
				}
				if (record.getInnerCode() != null) {
					String key = record.getType() + "|" + record.getInnerCode();
					newDictionaryCache.put(key, record);
				}
			}
			dictionaryCache = newDictionaryCache;
			logger.info("字典缓存条数:" + dictionarys.size());
			
			try {
				dictionaryByTypeCache = DataUtil.list2mapList(dictionarys, Dictionary.class, "type");
				logger.info("字典类别缓存条数:" + dictionaryByTypeCache.size());
			} catch (NoSuchMethodException | SecurityException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	@Override
	public void reloadJoinBoardWay(List<JoinBoardWay> joinBoardWays) {
		logger.info("载入拼板方式");
		Map<Integer, JoinBoardWay> tempCache = new HashMap<Integer, JoinBoardWay>();
		try {
			tempCache = DataUtil.list2map(joinBoardWays, JoinBoardWay.class, "innerId");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		
		joinBoardWayCache = tempCache;
	}

	@Override
	public void reloadEdaGuest(List<EdaGuest> edaGuests) {
		logger.info("载入客户信息");	
		Map<String, EdaGuest> edaGuestNewCache = new HashMap<String, EdaGuest>();
		try {
			edaGuestNewCache = DataUtil.list2map(edaGuests, EdaGuest.class, "guestCode");
			Map<String, EdaGuest> edaMap = DataUtil.list2map(edaGuests, EdaGuest.class, "shortNameCn");
			edaGuestNewCache.putAll(edaMap);
		} catch (NoSuchMethodException | SecurityException e) {
			return;
		}
		edaGuestCache = edaGuestNewCache;
		logger.info("载入客户信息条数=" + edaGuestCache.size());
	}

	@Override
	public void reloadWxtbDepartment(List<WxtbDepartment> wxtbDepartments){
		logger.info("载入部门信息");
		Map<String, WxtbDepartment> wxtbDepartmentNewCache = new HashMap<String, WxtbDepartment>();
		try {
			wxtbDepartmentNewCache = DataUtil.list2map(wxtbDepartments, WxtbDepartment.class, "departmentCode");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		wxtbDepartmentCache = wxtbDepartmentNewCache;
		logger.info("载入部门信息条数=" + wxtbDepartmentCache.size());
	}
	
	@Override
	public void reloadFactory(List<FactoryInfo> factories) {
		logger.info("载入厂家信息");
		Map<Integer, FactoryInfo> tempCache = new HashMap<Integer, FactoryInfo>();
		try {
			tempCache = DataUtil.list2map(factories, FactoryInfo.class, "innerId");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		factoryCache = tempCache;
		logger.info("载入厂家信息条数=" + factoryCache.size());
	}
	
	@Override
	public void reloadSurfaceProcess(List<SurfaceProcess> processes) {
		logger.info("载入表面处理信息");
		Map<Integer, SurfaceProcess> tempCache = new HashMap<Integer, SurfaceProcess>();
		try {
			tempCache = DataUtil.list2map(processes, SurfaceProcess.class, "innerId");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		surfaceProcessCache = tempCache;
		logger.info("载入表面处理条数=" + surfaceProcessCache.size());
	}
	
	@Override
	public void reloadCompany(List<CompanyInfo> companies) {
		logger.info("载入公司信息");
		Map<Integer, CompanyInfo> tempCache = new HashMap<Integer, CompanyInfo>();
		// 按id查找
		try {
			tempCache = DataUtil.list2map(companies, CompanyInfo.class, "innerId");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		companyCache = tempCache;
		// 按code查找
		Map<String, CompanyInfo> tempCodeCache = new HashMap<String, CompanyInfo>();
		try {
			tempCodeCache = DataUtil.list2map(companies, CompanyInfo.class, "companyCode");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		companyCodeCache = tempCodeCache;
		logger.info("载入公司条数=" + companyCache.size());
	}
	
	@Override
	public void reloadBoardPlyTolerance(List<BoardPlyTolerance> records) {
		logger.info("板厚公差信息");
		Map<Integer, BoardPlyTolerance> tempCache = new HashMap<Integer, BoardPlyTolerance>();
		try {
			tempCache = DataUtil.list2map(records, BoardPlyTolerance.class, "innerId");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		boardPlyToleranceCache = tempCache;
		logger.info("载入公司条数=" + boardPlyToleranceCache.size());
	}
	
	@Override
	public void reloadBasemAterial(List<BasemAterial> records) {
		logger.info("基材信息");
		Map<Integer, BasemAterial> tempCache = new HashMap<Integer, BasemAterial>();
		try {
			tempCache = DataUtil.list2map(records, BasemAterial.class, "innerId");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		basemAterialCache = tempCache;
		logger.info("载入基材条数=" + basemAterialCache.size());
	}
	
	@Override
	public void reloadFrameTolerance(List<FrameTolerance> records) {
		logger.info("边框公差信息");
		Map<Integer, FrameTolerance> tempCache = new HashMap<Integer, FrameTolerance>();
		try {
			tempCache = DataUtil.list2map(records, FrameTolerance.class, "innerId");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		frameToleranceCache = tempCache;
		logger.info("载入边框公差条数=" + frameToleranceCache.size());		
	}
	
	@Override
	public void reloadCraftInfo(List<CraftInfo> records) {
		logger.info("边框工艺信息");
		Map<Integer, CraftInfo> tempCache = new HashMap<Integer, CraftInfo>();
		try {
			tempCache = DataUtil.list2map(records, CraftInfo.class, "innerId");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		craftCache = tempCache;
		logger.info("载入工艺条数=" + craftCache.size());	
	}
	
	@Override
	public void reloadPcbCheckTemplate(List<PcbCheckTemplate> records) {
		logger.info("载入pcb印制板检验模板");
		Map<Integer, PcbCheckTemplate> tempCache = new HashMap<Integer, PcbCheckTemplate>();
		try {
			tempCache = DataUtil.list2map(records, PcbCheckTemplate.class, "id");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		pcbCheckTemplateCache = tempCache;
		logger.info("载入pcb印制板检验模板条数=" + pcbCheckTemplateCache.size());
	}

	@Override
	public void reloadPcbReportTemplate(List<PcbReportTemplate> records) {
		logger.info("载入pcb印制板报告模板");
		Map<Integer, PcbReportTemplate> tempCache = new HashMap<>();
		try {
			tempCache = DataUtil.list2map(records, PcbReportTemplate.class, "id");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		pcbReportTemplateCache = tempCache;
		logger.info("载入pcb印制板报告模板条数=" + pcbCheckTemplateCache.size());
	}
	
	@Override
	public void reloadFormDefaultPerson(List<FormDefaultPerson> records) {
		logger.info("载入默认模板人员");
		Map<String, FormDefaultPerson> tempCache = new HashMap<>();
		try {
			tempCache = DataUtil.list2map(records, FormDefaultPerson.class, "fieldType");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		formDefaultPersonCache = tempCache;
		logger.info("载入默认模板人员个数=" + formDefaultPersonCache.size());
	}
	
	@Override
	public void reloadUserRole(List<UserRole> records) {
		logger.info("载入系统用户角色");
		Map<Integer, UserRole> tempCache = new HashMap<Integer, UserRole>();
		try {
			tempCache = DataUtil.list2map(records, UserRole.class, "id");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		userRoleCache = tempCache;
		logger.info("载入系统功能模块条数=" + userRoleCache.size());		
	}
	@Override
	public void reloadUserRoleRelation(List<UserRoleRelation> records) {
		logger.info("载入系统用户角色关系");
		Map<String, List<UserRoleRelation>> tempCache = new HashMap<String, List<UserRoleRelation>>();
		try {
			tempCache = DataUtil.list2mapList(records, UserRoleRelation.class, "userCode");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		userRoleRelationCache = tempCache;
		logger.info("载入系统用户角色关系条数=" + userRoleRelationCache.size());
	}
	@Override
	public void reloadWxtbUser(List<WxtbUser> records) {
		logger.info("载入系统用户");
		Map<String, WxtbUser> tempCache = new HashMap<String, WxtbUser>();
		try {
			tempCache = DataUtil.list2map(records, WxtbUser.class, "userCode");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		wxtbUserCache = tempCache;
		logger.info("载入系统用户条数=" + wxtbUserCache.size());	
	}

	@Override
	public void reloadUserAuth(List<UserAuth> records) {
		logger.info("载入权限");
		Map<Integer, UserAuth> tempCache = new HashMap<Integer, UserAuth>();
		try {
			tempCache = DataUtil.list2map(records, UserAuth.class, "authType");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		userAuthCache = tempCache;
		logger.info("载入权限个数" + userAuthCache.size());
	}

	@Override
	public void reloadRoleAuth(List<RoleAuth> records) {
		logger.info("载入角色权限关系");
		Map<Integer, RoleAuth> tempCache = new HashMap<Integer, RoleAuth>();
		try {
			tempCache = DataUtil.list2map(records, RoleAuth.class, "id");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		roleAuthCache = tempCache;
		logger.info("载入角色权限关系条数" + roleAuthCache.size());
	}
	
	//----------------------------------------------------------------------
	private String getDictionaryKey(DictionaryEnum type, Integer innerId) {
		String dictionaryKey = type.getCode() + "#" + innerId;
		return dictionaryKey;
	}

	private String getDictionaryKey(DictionaryEnum type, String innerCode) {
		String dictionaryKey = type.getCode() + "|" + innerCode;
		return dictionaryKey;
	}

	private String getDictionaryKeyOfValue(DictionaryEnum type, String value) {
		String dictionaryKey = type.getCode() + "?" + value;
		return dictionaryKey;
	}

	@Override
	public Dictionary getDictionary(DictionaryEnum type, Integer innerId) {
		String key = this.getDictionaryKey(type, innerId);
		return dictionaryCache.get(key);
	}

	@Override
	public Dictionary getDictionary(DictionaryEnum type, String innerCode) {
		String key = this.getDictionaryKey(type, innerCode);
		return dictionaryCache.get(key);
	}

	@Override
	public Dictionary getDictionaryByValue(DictionaryEnum type, String value) {
		String key = this.getDictionaryKeyOfValue(type, value);
		return dictionaryCache.get(key);
	}
	
	@Override
	public List<Dictionary> getDictionarys(DictionaryEnum type) {
		return dictionaryByTypeCache.get(type.getCode());
	}

	@Override
	public JoinBoardWay getJoinBoardWay(Integer id) {
		return joinBoardWayCache.get(id);
	}
	@Override
	public EdaGuest getGuest(String key) {
		return edaGuestCache.get(key);
	}
	@Override
	public WxtbUser getWxtbUser(String userCode) {
		return wxtbUserCache.get(userCode);
	}
	@Override
	public WxtbDepartment getDepartment(String key) {
		return wxtbDepartmentCache.get(key);
	}

	@Override
	public FactoryInfo getFactory(Integer id) {
		return factoryCache.get(id);
	}
	@Override
	public SurfaceProcess getSurfaceProcess(Integer id) {
		return surfaceProcessCache.get(id);
	}
	@Override
	public CompanyInfo getCompany(Integer id) {
		return companyCache.get(id);
	}
	@Override
	public CompanyInfo getCompany(String code) {
		return companyCodeCache.get(code);
	}
	@Override
	public BoardPlyTolerance getBoardPlyTolerance(Integer id) {
		return boardPlyToleranceCache.get(id);
	}
	@Override
	public CraftInfo getCraftInfo(Integer id) {
		return craftCache.get(id);
	}
	@Override
	public PcbCheckTemplate getPcbCheckTemplate(Integer id) {
		return pcbCheckTemplateCache.get(id);
	}
	@Override
	public UserAuth getUserAuth(Integer authType) {
		return userAuthCache.get(authType);
	}

	@Override
	public List<EdaGuest> selectGuestByCondition(String condition) {
		List<EdaGuest> guests = new ArrayList<EdaGuest>();
		if (StringUtils.isBlank(condition)) {
			return guests;
		}
		for (Map.Entry<String, EdaGuest> entry : edaGuestCache.entrySet()) {
			EdaGuest guest = entry.getValue();
			if (StringUtils.isBlank(guest.getShortNameCn())) {
				continue;
			}
			if (guest.getShortNameCn().contains(condition)) {
				guests.add(guest);
			}
		}
		return guests;
	}

	@Override
	public Map<Integer, FactoryInfo> getFactorys() {
		return factoryCache;
	}

	@Override
	public Map<Integer, SurfaceProcess> getSurfaceProcess() {
		return surfaceProcessCache;
	}

	@Override
	public Map<Integer, UserRole> getUserRoles() {
		return userRoleCache;
	}

	@Override
	public Map<String, WxtbUser> getWxtbUser() {
		return wxtbUserCache;
	}
	@Override
	public Map<Integer, RoleAuth> getRoleAuth() {
		return roleAuthCache;
	}
	
	@Override
	public Map<Integer, UserAuth> getUserAuths() {
		return userAuthCache;
	}
	
	@Override
	public List<UserRole> getUserRole(String userCode) {
		List<UserRole> roles = new ArrayList<UserRole>();
		final List<UserRoleRelation> relations = userRoleRelationCache.get(userCode);
		if (relations == null || relations.isEmpty()) {
			return roles;
		}
		for (UserRoleRelation ur : relations) {
			final UserRole userRole = userRoleCache.get(ur.getRoleId());
			if (userRole == null || userRole.getEnable() == 0) {
				continue;
			}
			roles.add(userRole);
		}
		return roles;
	}

	@Override
	public void reloadMenu(List<Menu> records) {
		logger.info("载入侧边栏信息");
		Map<Integer, Menu> tempCache = new LinkedHashMap<Integer, Menu>();
		for (Menu menu : records) {
			tempCache.put(menu.getId(), menu);
		}
		menuCache = tempCache;
		logger.info("载入侧边栏信息" + menuCache.size() + "条");
		
	}

	@Override
	public Map<Integer, Menu> getMenu() {
		return menuCache;
	}

	@Override
	public PcbReportTemplate getPcbReportTemplate(Integer id) {
		return pcbReportTemplateCache.get(id);
	}

	@Override
	public FormDefaultPerson getFormDefaultPerson(FormFieldTypeEnum type) {
		return formDefaultPersonCache.get(type.getCode());
	}
}
