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
import com.pcbwx.pcbmis.enums.ActionTypeEnum;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.service.LogService;
import com.pcbwx.pcbmis.service.OrderService;
import com.pcbwx.pcbmis.service.SupportService;

@Controller
@RequestMapping("/audit")
public class AuditController {
	
	@Autowired
	private AuthService authService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private LogService logService;
	@Autowired
	private SupportService supportService;
	
	/**
	 * 检验单提交审核
	 * @param request
	 * @param checkNum	 检验单号
	 * @param isPass     1为通过，其他为不通过
	 * @return
	 */
	@RequestMapping( value = {"/auditSubmit"}, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> auditPass(HttpServletRequest request, 
			@RequestParam("checkNum") String checkNum,
			@RequestParam("isPass") Integer isPass) {
		final AkAuthUser loginUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<String, Object>();
		if (checkNum == null || checkNum.equals("") || isPass == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			return response;
		}
		logService.addAction("", ActionTypeEnum.AUDIT_SUBMIT.getCode(), loginUser.getUserCode(), checkNum, isPass + "");
		supportService.doSystemLog(request, loginUser.getUsername(), ActionTypeEnum.AUDIT_SUBMIT.getDesc(), 1);
		Integer result = orderService.judgeAuditPass(loginUser, checkNum, isPass);
		if (result == 0) {
			response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		}else{
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", "提交审核失败");
		}
		return response;
	}
	
}
