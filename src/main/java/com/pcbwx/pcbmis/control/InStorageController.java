package com.pcbwx.pcbmis.control;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
import com.pcbwx.pcbmis.bean.ReceiveProductBody;
import com.pcbwx.pcbmis.bean.request.ReceiveProductBodyNew;
import com.pcbwx.pcbmis.enums.ActionTypeEnum;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.service.InStorageService;
import com.pcbwx.pcbmis.service.LogService;
import com.pcbwx.pcbmis.service.SupportService;
import com.pcbwx.pcbmis.utils.StringUtil;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/inStorage")
public class InStorageController extends BaseController{
	
	@Autowired
	private InStorageService inStorageService;
	@Autowired
	private AuthService authService;
	@Autowired
	private LogService logService;
	@Autowired
	private SupportService supportService;
	
	/**
	 * 获取入库单列表
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getInstorageOrder(
			HttpServletRequest request,
			@RequestParam(value = "orderNum", required = false) String orderNum,
			@RequestParam(value = "guestName", required = false) String guestName,
			@RequestParam(value = "boardName", required = false) String boardName,
			@RequestParam(value = "inAmountNum", required = false) Integer inAmountNum,
			@RequestParam(value = "inStorageDateStart", required = false) String inStorageDateStart,
			@RequestParam(value = "inStorageDateEnd", required = false) String inStorageDateEnd,
			@RequestParam(value = "factoryId", required = false) Integer factoryId,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "rows", defaultValue = "20", required = false) Integer rows,
			@RequestParam(value = "page", defaultValue = "1", required = false) Integer page) {
		final AkAuthUser wxtbUser = authService.getLoginUser();	
		if (StringUtil.isEmpty(orderNum)) {
			orderNum = null;
		}
		if (StringUtil.isEmpty(guestName)) {
			guestName = null;
		}
		if (StringUtil.isEmpty(boardName)) {
			boardName = null;
		}
		if (StringUtil.isEmpty(inStorageDateStart)) {
			inStorageDateStart = null;
		}
		if (StringUtil.isEmpty(inStorageDateEnd)) {
			inStorageDateEnd = null;
		}
		logService.addAction("", ActionTypeEnum.INSTORAGE_LIST.getCode(), wxtbUser.getUserCode(), null);
		supportService.doSystemLog(request, wxtbUser.getUsername(), ActionTypeEnum.INSTORAGE_LIST.getDesc(), 0);
		return inStorageService.getInStorageList(orderNum, guestName,
				boardName, inAmountNum, inStorageDateStart, inStorageDateEnd,
				factoryId, gradeId, page, rows);
	}
	
	/**
	 * 获取出入库生成的检验单和报告的状态
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = { "/productEnable" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getProductState(HttpServletRequest request, 
			@RequestParam("orderNumber") String orderNumber){
		return inStorageService.getProductState(orderNumber);
	}
	
	/**
	 * 接受产品
	 * @param orderNumber			// 入库单号
	 * @param receiveProductBody	// 填写内容
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = { "/product" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> receiveProduct(HttpServletRequest request,
			@RequestParam("orderNumber") String orderNumber,
			@RequestBody ReceiveProductBody receiveProductBody) {
		final AkAuthUser wxtbUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<>();
		if (StringUtil.isEmpty(orderNumber)) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", ErrorCodeEnum.SYSTEM_ERROR.getDescr());
			return response;
		}
		logService.addAction("", ActionTypeEnum.RECEIVE_PRODUCT.getCode(), wxtbUser.getUserCode(), null);
		supportService.doSystemLog(request, wxtbUser.getUsername(), ActionTypeEnum.RECEIVE_PRODUCT.getDesc(), 1);
		return inStorageService.receiveProduct(wxtbUser, orderNumber, receiveProductBody);
	}
	
	@RequestMapping(value = { "/productNew" }, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "接受产品")
	public Map<String, Object> Receive(HttpServletRequest request,
			@RequestParam("orderNumber") String orderNumber,
			@RequestBody ReceiveProductBodyNew receiveProductBody){
		final AkAuthUser wxtbUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<>();
		if (StringUtil.isEmpty(orderNumber)) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", ErrorCodeEnum.SYSTEM_ERROR.getDescr());
			return response;
		}
		if (Objects.equals(receiveProductBody.getInStoragePcs(), null) 
				|| Objects.equals(receiveProductBody.getInStoragePcs(), 0)) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", "接收数量不能为0");
			return response;
		}
		logService.addAction("", ActionTypeEnum.RECEIVE_PRODUCT.getCode(), wxtbUser.getUserCode(), null);
		supportService.doSystemLog(request, wxtbUser.getUsername(), ActionTypeEnum.RECEIVE_PRODUCT.getDesc(), 1);
		return inStorageService.receiveProductNew(wxtbUser, orderNumber, receiveProductBody);
	}
	
	@RequestMapping(value = { "/boardName" }, method = RequestMethod.PUT)
	@ResponseBody
	@ApiOperation(value = "修改板名")
	public Map<String, Object> modifyBoardName(HttpServletRequest request,
			@RequestParam("orderNumber") String orderNumber,
			@RequestParam("boardName") String boardName){
		final AkAuthUser wxtbUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<>();
		if (StringUtil.isEmpty(boardName)) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", "板名不能为空");
			return response;
		}
		logService.addAction("", ActionTypeEnum.MODIFY_BOARD_NAME.getCode(), wxtbUser.getUserCode(), null);
		supportService.doSystemLog(request, wxtbUser.getUsername(), ActionTypeEnum.MODIFY_BOARD_NAME.getDesc(), 1);
		ErrorCodeEnum errorCodeEnum = inStorageService.modifyBoardName(orderNumber, boardName);
		response.put("result", errorCodeEnum.getCode());
		response.put("msg", errorCodeEnum.getDescr());
		return response;
	}
	
}
