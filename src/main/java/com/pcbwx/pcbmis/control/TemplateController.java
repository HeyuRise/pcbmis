package com.pcbwx.pcbmis.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.authkit.callback.AuthService;
import com.pcbwx.pcbmis.bean.request.PcbCheckTemplateBean;
import com.pcbwx.pcbmis.bean.request.PcbReportTemplateBean;
import com.pcbwx.pcbmis.enums.ActionTypeEnum;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.service.LogService;
import com.pcbwx.pcbmis.service.TemplateService;
import com.pcbwx.pcbmis.utils.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/template")
@Api(tags = "模板api")
public class TemplateController extends BaseController {

	@Autowired
	private LogService logService;
	@Autowired
	private AuthService authService;

	@Autowired
	private TemplateService templateService;

	@ApiOperation("模板列表")
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getTemplateList(HttpServletRequest request,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "rows", defaultValue = "20", required = false) Integer rows,
			@RequestParam(value = "page", defaultValue = "1", required = false) Integer page) {
		if (StringUtil.isEmpty(name)) {
			name = null;
		}
		return templateService.getTemplateList(name, page, rows);
	}

	@ApiOperation("添加模板")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addtemplate(HttpServletRequest request, @RequestParam("name") String name) {
		final AkAuthUser loginUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<>();
		if (StringUtil.isEmpty(name)) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", "名字为空");
			return response;
		}
		logService.addAction("", ActionTypeEnum.TEMPLATE_ADD.getCode(), loginUser.getUserCode(), name);
		ErrorCodeEnum errorCodeEnum = templateService.addTempalte(name, loginUser);
		response.put("result", errorCodeEnum.getCode());
		response.put("msg", errorCodeEnum.getDescr());
		return response;
	}

	@ApiOperation("修改可用不可用")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> modifyEnable(@PathVariable Integer id) {
		final AkAuthUser loginUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<>();
		if (id == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", "id为空");
			return response;
		}
		logService.addAction("", ActionTypeEnum.TEMPLATE_ENABLE.getCode(), loginUser.getUserCode(), null);
		ErrorCodeEnum errorCodeEnum = templateService.modifyEnable(id, loginUser);
		response.put("result", errorCodeEnum.getCode());
		response.put("msg", errorCodeEnum.getDescr());
		return response;
	}

	@ApiOperation("查看检验单模板")
	@RequestMapping(value = "/check/{templateId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getCheckTempalte(@PathVariable Integer templateId,
			@RequestParam(value = "type", required = false) Integer type) {
		final AkAuthUser loginUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<>();
		if (templateId == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", "id为空");
			return response;
		}
		logService.addAction("", ActionTypeEnum.TEMPLATE_CHECK.getCode(), loginUser.getUserCode(),
				templateId.toString());
		return templateService.getCheckTemplate(templateId, type);
	}

	@ApiOperation("查看报告单模板")
	@RequestMapping(value = "/report/{templateId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getReportTempalte(@PathVariable Integer templateId) {
		final AkAuthUser loginUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<>();
		if (templateId == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", "id为空");
			return response;
		}
		logService.addAction("", ActionTypeEnum.TEMPLATE_REPORT.getCode(), loginUser.getUserCode(),
				templateId.toString());
		return templateService.getReportTemplate(templateId);
	}

	@ApiOperation("修改检验单模板")
	@RequestMapping(value = "/check/{templateId}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> modifyCheck(@PathVariable Integer templateId,
			@RequestBody PcbCheckTemplateBean checkTemplate) {
		final AkAuthUser loginUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<>();
		if (templateId == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", "id为空");
			return response;
		}
		logService.addAction("", ActionTypeEnum.TEMPLATE_CHECK_MODIFY.getCode(), loginUser.getUserCode(),
				templateId.toString());
		ErrorCodeEnum errorCodeEnum = templateService.modifyCheck(templateId, loginUser, checkTemplate);
		response.put("result", errorCodeEnum.getCode());
		response.put("msg", errorCodeEnum.getDescr());
		return response;
	}

	@ApiOperation("修改报告单模板")
	@RequestMapping(value = "/report/{templateId}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> modifyReport(@PathVariable Integer templateId,
			@RequestBody PcbReportTemplateBean reportTemplate) {
		final AkAuthUser loginUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<>();
		if (templateId == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", "id为空");
			return response;
		}
		logService.addAction("", ActionTypeEnum.TEMPLATE_REPORT_MODIFY.getCode(), loginUser.getUserCode(),
				templateId.toString());
		ErrorCodeEnum errorCodeEnum = templateService.modifyReport(templateId, loginUser, reportTemplate);
		response.put("result", errorCodeEnum.getCode());
		response.put("msg", errorCodeEnum.getDescr());
		return response;
	}
}
