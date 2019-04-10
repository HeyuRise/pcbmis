package com.pcbwx.pcbmis.control;

import java.util.Date;
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
import com.pcbwx.pcbmis.bean.request.ReceiveProductBodyNew;
import com.pcbwx.pcbmis.enums.ActionTypeEnum;
import com.pcbwx.pcbmis.service.LogService;
import com.pcbwx.pcbmis.service.ReceiveOrderService;
import com.pcbwx.pcbmis.service.SupportService;
import com.pcbwx.pcbmis.utils.DateTimeUtil;
import com.pcbwx.pcbmis.utils.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/receive")
@Api(tags = "接收单文档")
public class ReceiveOrderController extends BaseController {

	@Autowired
	private AuthService authService;
	@Autowired
	private LogService logService;
	@Autowired
	private SupportService supportService;
	@Autowired
	private ReceiveOrderService receiveOrderService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取接收单列表")
	public Map<String, Object> getReceiveOrder(HttpServletRequest request,
			@RequestParam(value = "orderNum", required = false) String orderNum,
			@RequestParam(value = "guestName", required = false) String guestName,
			@RequestParam(value = "factoryId", required = false) Integer factoryId,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "productNumSet", required = false) Integer productNumSet,
			@RequestParam(value = "productNumPcs", required = false) Integer productNumPcs,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "receiveNum", required = false) Integer receiveNum,
			@RequestParam(value = "receiveTypeId", required = false) Integer receiveTypeId,
			@RequestParam(value = "receiveDateStart", required = false) String receiveDateStart,
			@RequestParam(value = "receiveDateEnd", required = false) String receiveDateEnd,
			@RequestParam(value = "receiver", required = false) String userName,
			@RequestParam(value = "rows", defaultValue = "20", required = false) Integer rows,
			@RequestParam(value = "page", defaultValue = "1", required = false) Integer page) {
		final AkAuthUser wxtbUser = authService.getLoginUser();
		if (StringUtil.isEmpty(orderNum)) {
			orderNum = null;
		}
		if (StringUtil.isEmpty(guestName)) {
			guestName = null;
		}
		Date receiveDateS = DateTimeUtil.dateStr2date(receiveDateStart);
		Date receiveDateE = DateTimeUtil.dateStr2date(receiveDateEnd);
		receiveDateE = DateTimeUtil.getTheDayLastTime(receiveDateE);
		logService.addAction("", ActionTypeEnum.RECEIVE_LIST.getCode(), wxtbUser.getUserCode(), orderNum);
		supportService.doSystemLog(request, wxtbUser.getUsername(), ActionTypeEnum.RECEIVE_LIST.getDesc(), 0);
		return receiveOrderService.goForReceiveOrderList(orderNum, guestName, factoryId, gradeId, productNumSet,
				productNumPcs, contentId, receiveNum, receiveTypeId, receiveDateS, receiveDateE, userName, rows, page);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	@ApiOperation(value = "修改接受单")
	public Map<String, Object> modifyReceiveOrder(HttpServletRequest request,
			@RequestParam("receiveId") Integer receiveId, @RequestBody ReceiveProductBodyNew receiveProductBody) {
		final AkAuthUser wxtbUser = authService.getLoginUser();
		logService.addAction("", ActionTypeEnum.RECEIVE_MODIFY.getCode(), wxtbUser.getUserCode(), receiveId.toString());
		supportService.doSystemLog(request, wxtbUser.getUsername(), ActionTypeEnum.RECEIVE_MODIFY.getDesc(), 0);
		return receiveOrderService.modifyReceiveOrder(wxtbUser, receiveId, receiveProductBody.getReceiveOrderType(),
				receiveProductBody.getContentIds());
	}

	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value = "删除接受单")
	public Map<String, Object> deleteReceiveOrder(HttpServletRequest request,
			@RequestParam("receiveId") Integer receiveId) {
		final AkAuthUser wxtbUser = authService.getLoginUser();
		logService.addAction("", ActionTypeEnum.RECEIVE_DELETE.getCode(), wxtbUser.getUserCode(), receiveId.toString());
		return receiveOrderService.deleteReceiveOrder(receiveId);
	}

}
