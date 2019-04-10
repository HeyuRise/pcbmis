package com.pcbwx.pcbmis.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.authkit.callback.AuthService;
import com.pcbwx.pcbmis.enums.ActionTypeEnum;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.service.LogService;
import com.pcbwx.pcbmis.service.SupportService;
import com.pcbwx.pcbmis.service.UserService;

@Controller
@RequestMapping("/user")
public class UserControl extends BaseController{

//	private static Logger logger = Logger.getLogger(UserControl.class);

	@Autowired
	private AuthService authService;
	@Autowired
	private UserService userService;
	@Autowired
	private LogService logService;
	@Autowired
	private SupportService supportService;
	
	/**
	 * 获取用户列表
	 * @param request
	 * @param keyWord
	 * @return
	 */
//	@RequestMapping( value = {"/userList"}, method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> getUserList(HttpServletRequest request,
//			@RequestParam( value = "keyWord", required = false) String keyWord){
////		WxtbAuthUser wxtbUser = (WxtbAuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		final AkAuthUser wxtbUser = authService.getLoginUser();
//		Map<String, Object> response = new HashMap<String, Object>();
//		// 每页显示的行数
//		String rowsStr = request.getParameter("rows");
//		String pageStr = request.getParameter("page");
//		int page = 1;
//		int rows = 20;
//		if (pageStr != "" && pageStr != null) {
//			try {
//				page = Integer.parseInt(pageStr);
//			} catch (Exception e) {
//				response.put("total", 0);
//				response.put("rows", null);
//				response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
//				return response;
//			}
//		}
//		if (rowsStr != "" && rowsStr != null) {
//			try {
//				rows = Integer.parseInt(rowsStr);
//			} catch (Exception e) {
//				response.put("total", 0);
//				response.put("rows", null);
//				response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
//				return response;
//			}
//		}
//		logService.addAction("", ActionTypeEnum.USER_LIST.getCode(), wxtbUser.getUserCode(), "");
//		supportService.doSystemLog(request, wxtbUser.getUsername(), "获取用户列表", 0);
//		Map<String, Object> resultMap = userService.getUserList(page, rows, keyWord);
//		if (resultMap == null) {
//			response.put("total", 0);
//			response.put("rows", null);
//			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
//			return response;
//		}
//		response = resultMap;
//		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
//		return response;
//	}
	
	/**
	 * 编辑用户
	 * @param request
	 * @param account
	 * @param enable
	 * @param userRoleEnable
	 * @return
	 */
//	@RequestMapping( value = "editUser", method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> editUser(HttpServletRequest request, 
//			@RequestParam("account") String account,
//			@RequestBody UserRoleEnableNew userRoleEnableNew){
////		WxtbAuthUser wxtbUser = (WxtbAuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		final AkAuthUser wxtbUser = authService.getLoginUser();
//		Map<String, Object> response = new HashMap<String, Object>();
//		if (account == null || userRoleEnableNew == null) {
//			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
//			response.put("msg", "请求获取失败");
//			return response;
//		}
//		logService.addAction("", ActionTypeEnum.EDIT_USER.getCode(), wxtbUser.getUserCode(), "");
//		supportService.doSystemLog(request, wxtbUser.getUsername(), "编辑用户", 1);
//		Integer isSuccess = userService.editUser(account, userRoleEnableNew);
//		if (isSuccess == 1) {
//			response.put("result", ErrorCodeEnum.SUCCESS.getCode());
//		}else{
//			response.put("result", ErrorCodeEnum.ILLEGAL_ACTION.getCode());
//			response.put("msg", "编辑用户失败");
//		}
//		return response;
//	}
	
	
	/**
	 * 获取角色列表
	 * @param request
	 * @param keyWord
	 * @return
	 */
//	@RequestMapping( value = {"/roleList"}, method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> getRoleList(HttpServletRequest request,
//			@RequestParam( value = "keyWord" , required = false) String keyWord){
////		WxtbAuthUser wxtbUser = (WxtbAuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		final AkAuthUser wxtbUser = authService.getLoginUser();
//		Map<String, Object> response = new HashMap<String, Object>();
//		// 每页显示的行数
//		String rowsStr = request.getParameter("rows");
//		String pageStr = request.getParameter("page");
//		int page = 1;
//		int rows = 20;
//		if (pageStr != "" && pageStr != null) {
//			try {
//				page = Integer.parseInt(pageStr);
//			} catch (Exception e) {
//				response.put("total", 0);
//				response.put("rows", null);
//				response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
//				return response;
//			}
//		}
//		if (rowsStr != "" && rowsStr != null) {
//			try {
//				rows = Integer.parseInt(rowsStr);
//			} catch (Exception e) {
//				response.put("total", 0);
//				response.put("rows", null);
//				response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
//				return response;
//			}
//		}
//		logService.addAction("", ActionTypeEnum.ROLE_LIST.getCode(), wxtbUser.getUserCode(), "");
//		supportService.doSystemLog(request, wxtbUser.getUsername(), "获取角色列表", 0);
//		Map<String, Object> resultMap = userService.getRoleList(page, rows, keyWord);
//		if (resultMap == null) {
//			response.put("total", 0);
//			response.put("rows", null);
//			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
//			return response;
//		}
//		response = resultMap;
//		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
//		return response;
//	}
	
	
	/**
	 * 编辑角色
	 * @param request
	 * @param roleName
	 * @param userAuthEnable
	 * @return
	 */
//	@RequestMapping( value = {"/editRole"}, method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> editUserAuth(HttpServletRequest request,
//			@RequestParam("roleName") String roleName,
//			@RequestBody UserAuthEnableNew userAuthEnableNew){
////		WxtbAuthUser wxtbUser = (WxtbAuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		final AkAuthUser wxtbUser = authService.getLoginUser();
//		Map<String, Object> response = new HashMap<String, Object>();
//		if (roleName == null || userAuthEnableNew == null) {
//			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
//			response.put("msg", "请求获取失败");
//			return response;
//		}
//		logService.addAction("", ActionTypeEnum.EDIT_ROLE.getCode(), wxtbUser.getUserCode(), "");
//		supportService.doSystemLog(request, wxtbUser.getUsername(), "编辑角色", 1);
//		Integer isSuccess = userService.editRole(wxtbUser, roleName, userAuthEnableNew);
//		if (isSuccess == 1) {
//			response.put("result", ErrorCodeEnum.SUCCESS.getCode());
//		}else if(isSuccess == 2){
//			response.put("result", ErrorCodeEnum.ILLEGAL_ACTION.getCode());
//			response.put("msg", "角色名称不能与其他角色名称相同");
//		}else {
//			response.put("result", ErrorCodeEnum.ILLEGAL_ACTION.getCode());
//			response.put("msg", "编辑角色失败");
//		}
//		return response;
//	}
	
	/**
	 * 获取日志列表
	 * @param request
	 * @param keyWord
	 * @param startTime
	 * @param stopTime
	 * @return
	 */
	@RequestMapping( value = {"/getLogList"}, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getLogList(HttpServletRequest request,
			@RequestParam( value = "keyWord", required = false) String keyWord,
			@RequestParam( value = "startTime", required = false) String startTime,
			@RequestParam( value = "stopTime", required = false) String stopTime,
			@RequestParam(value = "rows", defaultValue = "20", required = false) Integer rows,
			@RequestParam(value = "page", defaultValue = "1",required = false) Integer page){
		final AkAuthUser wxtbUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<String, Object>();
		logService.addAction("", ActionTypeEnum.GET_LOG_LIST.getCode(), wxtbUser.getUserCode(), "");
		supportService.doSystemLog(request, wxtbUser.getUsername(), "获取日志列表", 0);
		Map<String, Object> resultMap = userService.getlogList(keyWord, startTime, stopTime, page, rows);
		if (resultMap == null) {
			response.put("total", 0);
			response.put("rows", new ArrayList<T>());
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
		}else {
			response = resultMap;
			response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		}
		return response;
	}
	
	/**
	 * 添加角色
	 * @param request
	 * @param userAuthEnable
	 * @return
	 */
//	@RequestMapping( value = {"/addRole"}, method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> putNewRole(HttpServletRequest request,
//			@RequestBody UserAuthEnableNew userAuthEnableNew){
////		WxtbAuthUser wxtbUser = (WxtbAuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		final AkAuthUser wxtbUser = authService.getLoginUser();
//		Map<String, Object> response = new HashMap<String, Object>();
//		if (userAuthEnableNew == null) {
//			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
//			response.put("msg", "请求获取失败");
//			return response;
//		}
//		logService.addAction("", ActionTypeEnum.ADD_ROLE.getCode(), wxtbUser.getUserCode(), "");
//		supportService.doSystemLog(request, wxtbUser.getUsername(), "添加角色", 2);
//		Integer isSuccess = userService.addRole(wxtbUser, userAuthEnableNew);
//		if (isSuccess == 1) {
//			response.put("result", ErrorCodeEnum.SUCCESS.getCode());
//		}else if(isSuccess == 2){
//			response.put("result", ErrorCodeEnum.ILLEGAL_ACTION.getCode());
//			response.put("msg", "角色名字不能重复");
//		}else {
//			response.put("result", ErrorCodeEnum.ILLEGAL_ACTION.getCode());
//			response.put("msg", "添加角色失败");
//		}
//		return response;
//	}
	
	/**
	 * 删除角色
	 * @param request
	 * @param roleName
	 * @return
	 */
//	@RequestMapping( value = {"/deleteRole"}, method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> deleteRole(HttpServletRequest request,
//			@RequestParam("roleName") String roleName){
////		WxtbAuthUser wxtbUser = (WxtbAuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		final AkAuthUser wxtbUser = authService.getLoginUser();
//		Map<String, Object> response = new HashMap<String, Object>();
//		if(roleName == null || roleName.equals("")){
//			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
//			response.put("msg", "请求获取失败");
//			return response;
//		}
//		logService.addAction("", ActionTypeEnum.DELETE_ROLE.getCode(), wxtbUser.getUserCode(), "");
//		supportService.doSystemLog(request, wxtbUser.getUsername(), "删除角色", 3);
//		Integer isSuccess = userService.delete(roleName);
//		if (isSuccess == 1) {
//			response.put("result", ErrorCodeEnum.SUCCESS.getCode());
//		}else{
//			response.put("result", ErrorCodeEnum.ILLEGAL_ACTION.getCode());
//			response.put("msg", "删除角色失败");
//		}
//		return response;
//	}
	
}
