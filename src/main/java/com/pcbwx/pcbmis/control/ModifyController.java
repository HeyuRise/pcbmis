package com.pcbwx.pcbmis.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.authkit.callback.AuthService;
import com.pcbwx.pcbmis.bean.CheckDetail;
import com.pcbwx.pcbmis.bean.ReportDetail;
import com.pcbwx.pcbmis.enums.ActionTypeEnum;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.service.LogService;
import com.pcbwx.pcbmis.service.ModifyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/modify")
@Api(tags = "修改检验单与报告")
public class ModifyController {

	@Autowired
	private AuthService authService;
	@Autowired
	private ModifyService modifyService;
	@Autowired
	private LogService logService;

	@ApiOperation("修改检验单")
	@RequestMapping(value = "/check", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> modifyCheckOrder(@RequestParam("checkNum") String checkNum,
			@RequestBody CheckDetail checkDetail) {
		Map<String, Object> response = new HashMap<>();
		final AkAuthUser wxtbUser = authService.getLoginUser();
		logService.addAction("", ActionTypeEnum.MODIFY_CHECK.getCode(), wxtbUser.getUserCode(), checkNum);
		ErrorCodeEnum errorCodeEnum = modifyService.modifyCheckOrder(checkNum, checkDetail, wxtbUser);
		response.put("result", errorCodeEnum.getCode());
		response.put("msg", errorCodeEnum.getDescr());
		return response;
	}
	
	@ApiOperation("修改报告单")
	@RequestMapping(value = "/report", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> modifyReportOrder(@RequestParam("reportNum") String reportNum,
			@RequestBody ReportDetail reportDetail) {
		Map<String, Object> response = new HashMap<>();
		final AkAuthUser wxtbUser = authService.getLoginUser();
		logService.addAction("", ActionTypeEnum.MODIFY_REPORT.getCode(), wxtbUser.getUserCode(), reportNum);
		ErrorCodeEnum errorCodeEnum = modifyService.modifyReportOrder(reportNum, reportDetail, wxtbUser);
		response.put("result", errorCodeEnum.getCode());
		response.put("msg", errorCodeEnum.getDescr());
		return response;
	}
}
