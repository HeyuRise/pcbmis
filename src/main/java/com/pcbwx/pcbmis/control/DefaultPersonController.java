package com.pcbwx.pcbmis.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.service.DefaultPersonService;
import com.pcbwx.pcbmis.utils.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author heyu
 */
@Controller
@RequestMapping("/default-person")
@Api(tags = "默认人员管理api")
public class DefaultPersonController extends BaseController {

	@Autowired
	private DefaultPersonService defaultPersonService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation("默认人员列表")
	public Map<String, Object> getdefaultPerson(
			@RequestParam(value = "rows", defaultValue = "20", required = false) Integer rows,
			@RequestParam(value = "page", defaultValue = "1", required = false) Integer page) {
		expResult = defaultPersonService.getList(page, rows);

		return expResult;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	@ApiOperation("修改人员列表")
	public Map<String, Object> modify(@PathVariable Integer id, @RequestParam("userCode") String userCode) {
		expResult = new HashMap<>();
		ErrorCodeEnum errorCodeEnum = null;
		if (StringUtil.isEmpty(userCode)) {
			errorCodeEnum = ErrorCodeEnum.SYSTEM_ERROR;
		} else {
			errorCodeEnum = defaultPersonService.modify(id, userCode);
		}
		expResult.put("result", errorCodeEnum.getCode());
		expResult.put("msg", errorCodeEnum.getDescr());
		return expResult;
	}
}
