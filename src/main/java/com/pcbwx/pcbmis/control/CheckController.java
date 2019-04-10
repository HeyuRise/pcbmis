package com.pcbwx.pcbmis.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
import com.pcbwx.pcbmis.bean.SizeAndWarpDegree;
import com.pcbwx.pcbmis.enums.ActionTypeEnum;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.service.CheckService;
import com.pcbwx.pcbmis.service.LogService;
import com.pcbwx.pcbmis.service.ReportService;
import com.pcbwx.pcbmis.service.SupportService;
import com.pcbwx.pcbmis.utils.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/check")
@Api(tags = "检验单接收文档")
public class CheckController {
	private static Logger logger = Logger.getLogger(CheckController.class);

	@Autowired
	private AuthService authService;
	@Autowired
	private LogService logService;
	@Autowired
	private SupportService supportService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private CheckService checkService;

	/**
	 * 接受前端数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/checkDate" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> postDetail(HttpServletRequest request, 
			@RequestParam("checkNum") String checkNum,
			@RequestParam("templateId") Integer templateId,
			@RequestParam(value = "orderNum", required = false) String orderNum, 
			@RequestBody CheckDetail checkDetail) {
		final AkAuthUser wxtbUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<String, Object>();
		if (checkNum == null || checkNum.equals("")) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			return response;
		}
		if (templateId == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", "验收标准不能为空");
			return response;
		}
		logService.addAction("", ActionTypeEnum.CHECK_DATE.getCode(), wxtbUser.getUserCode(), checkNum);
		supportService.doSystemLog(request, wxtbUser.getUsername(), ActionTypeEnum.CHECK_DATE.getDesc(), 2);
		ErrorCodeEnum isSuccess = ErrorCodeEnum.SYSTEM_ERROR;
		try {
			isSuccess = checkService.postDetail(wxtbUser, checkNum, orderNum, checkDetail, templateId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		response.put("result", isSuccess.getCode());
		response.put("msg", isSuccess.getDescr());
		return response;
	}

	@RequestMapping(value = { "/size" }, method = RequestMethod.PUT)
	@ResponseBody
	@ApiOperation("修改尺寸翘曲度")
	public Map<String, Object> modifySize(HttpServletRequest request, @RequestParam("checkNum") String checkNum,
			@RequestBody List<SizeAndWarpDegree> sizeAndWarpDegree) {
		final AkAuthUser wxtbUser = authService.getLoginUser();
		logService.addAction("", ActionTypeEnum.SIZE.getCode(), wxtbUser.getUserCode(), checkNum);
		supportService.doSystemLog(request, wxtbUser.getUsername(), ActionTypeEnum.SIZE.getDesc(), 2);
		return checkService.modifySize(checkNum, sizeAndWarpDegree);
	}

	/**
	 * 获取打印合格证信息
	 * 
	 * @param request
	 * @param checkNum
	 * @return
	 */
	@RequestMapping(value = { "/certificate" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getCertificateInfo(HttpServletRequest request,
			@RequestParam("checkNum") String checkNum) {
		final AkAuthUser wxtbUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<String, Object>();
		checkNum = checkNum.trim();
		if (StringUtil.isEmpty(checkNum)) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", ErrorCodeEnum.SYSTEM_ERROR.getDescr());
			return response;
		}
		logService.addAction("", ActionTypeEnum.PRINT_CERTIFICATE.getCode(), wxtbUser.getUserCode(), checkNum);
		supportService.doSystemLog(request, wxtbUser.getUsername(), ActionTypeEnum.PRINT_CERTIFICATE.getDesc(), 0);
		return reportService.goforCertificateInfo(checkNum, wxtbUser);
	}
}
