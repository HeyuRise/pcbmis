package com.pcbwx.pcbmis.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.authkit.callback.AuthService;
import com.pcbwx.pcbmis.bean.ReportDetail;
import com.pcbwx.pcbmis.enums.ActionTypeEnum;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.service.LogService;
import com.pcbwx.pcbmis.service.ReportService;
import com.pcbwx.pcbmis.service.SupportService;

@Controller
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private AuthService authService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private SupportService supportService;
	@Autowired
	private LogService logService;
	
	
	/**
	 * 接受报告数据
	 * @param reportNum	  	报告单号
	 * @param reportDetail	报告内容
	 * @return
	 */
	@RequestMapping( value = {"/postReport"}, method = RequestMethod.POST)  
	@ResponseBody
	public Map<String, Object> postReportContent(HttpServletRequest request,
			@RequestParam("reportNum") String reportNum,
			@RequestParam("templateId") Integer templateId,
			@RequestBody ReportDetail reportDetail){
		final AkAuthUser wxtbUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<String, Object>();
		if (reportNum == null || reportNum.equals("") || reportDetail == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			return response;
		}
		if (templateId == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", "报告模板不能为空");
			return response;
		}
		ErrorCodeEnum isSuccess = reportService.postReportDetail(wxtbUser, reportNum, reportDetail, templateId);
		logService.addAction("", ActionTypeEnum.POST_REPORT.getCode(), wxtbUser.getUserCode(), "");
		supportService.doSystemLog(request, wxtbUser.getUsername(), ActionTypeEnum.POST_REPORT.getDesc(), 2);

		response.put("result", isSuccess.getCode());
		response.put("msg", isSuccess.getDescr());
		return response;
	}
}
