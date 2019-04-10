package com.pcbwx.pcbmis.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.authkit.callback.AuthService;
import com.pcbwx.pcbmis.common.ConfigProperties;
import com.pcbwx.pcbmis.enums.ActionTypeEnum;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.service.AccountService;
import com.pcbwx.pcbmis.service.CheckService;
import com.pcbwx.pcbmis.service.LogService;
import com.pcbwx.pcbmis.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("roleAuth")
@Api(tags = "用户api")
public class AuthControl extends BaseController{
	
	@Autowired
	private AuthService authService;
	@Autowired
	private UserService userService;
	@Autowired
	private LogService logService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private CheckService checkService;
	
	/**
	 * 获取用户详情
	 * @param request
	 * @return
	 */
	@ApiOperation("获取用户详情")
	@RequestMapping( value = {"/getUserDetail"}, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getWxtbUser(HttpServletRequest request){
		final AkAuthUser wxtbUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<String, Object>();
		if (wxtbUser == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			return response;
		}
		logService.addAction("", ActionTypeEnum.GET_USER_DETAIL.getCode(), wxtbUser.getUserCode(), "");
		response = userService.getUserDetail(wxtbUser);
		// 获取重新选择系统url
		String systemsUrl = ConfigProperties.getSystemsUrl();
		response.put("systemsUrl", systemsUrl);
		
		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		return response;
	}

	@ApiOperation("获取按钮是否显示")
	@RequestMapping( value = {"/button"}, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> buttonAppear(HttpServletRequest request,
			@RequestParam("buttonId") Integer buttonId,
			@RequestParam("type") Integer type,
			@RequestParam("orderNum") String orderNum){
		Map<String, Object> response = new HashMap<String, Object>();
		final AkAuthUser wxtbUser = authService.getLoginUser();
		boolean idAppear = accountService.getButtonAppear(wxtbUser.getUserCode(), buttonId);
		boolean isCanEdit = checkService.getIsCanEdit(type, orderNum);
		if (idAppear && isCanEdit) {
			response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		}else {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
		}
		return response;
	}
}
