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
import com.pcbwx.pcbmis.service.ReportService;
import com.pcbwx.pcbmis.service.SupportService;

@Controller
@RequestMapping("/reportAudit")
public class ReportAuditController {

	@Autowired
	private AuthService authService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private LogService logService;
	@Autowired
	private SupportService supportService;
	
	/**
	 * 提交报告是否审核通过
	 * @param request
	 * @param reportNum
	 * @param orderNum
	 * @param isPass
	 * @return
	 */
	@RequestMapping( value = {"/reportAuditSubmit"}, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> judgeReportPass(HttpServletRequest request, 
				@RequestParam("reportNum") String reportNum,
				@RequestParam(value = "orderNum", required = false) String orderNum,
				@RequestParam("isPass") Integer isPass){	
		final AkAuthUser wxtbUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<String, Object>();
		if (reportNum == null || reportNum.equals("") || isPass == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			return response;
		}
		logService.addAction("", ActionTypeEnum.REPORT_AUDIT_SUBMIT.getCode(), wxtbUser.getUserCode(), "");
		supportService.doSystemLog(request, wxtbUser.getUsername(), "提交报告是否审核通过", 1);
		Integer isSuccess = reportService.judgeReportPass(wxtbUser, reportNum, isPass);
		if (isSuccess == 1) {
			response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		}else{
			response.put("result", ErrorCodeEnum.ILLEGAL_ACTION.getCode());
			response.put("msg", "提交失败");
		}
		return response;
	}
}
