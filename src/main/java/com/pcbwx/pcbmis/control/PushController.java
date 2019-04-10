package com.pcbwx.pcbmis.control;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcbwx.pcbmis.bean.channel.OutStorage;
import com.pcbwx.pcbmis.component.LogContext;
import com.pcbwx.pcbmis.model.OutStorageOrder;
import com.pcbwx.pcbmis.service.PushService;
import com.pcbwx.pcbmis.utils.JsonUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/push")
@Api(tags = "推送api")
public class PushController extends BaseController {

	private static Logger logger = Logger.getLogger(PushController.class);

	@Autowired
	private PushService pushService;

	@ApiOperation("推送出库信息")
	@RequestMapping(value = "/outStorage", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPush(@RequestBody OutStorage outStorage) {
		expResult = new HashMap<>();
		OutStorageOrder outStorageOrder = pushService.getOutStorageNumber(outStorage);
		if (outStorageOrder == null) {
			expResult.put("state_code", 0);
			return expResult;
		}
		logger.info("推送信息" + JsonUtil.obj2json(outStorageOrder));
		boolean isSuccess = pushService.operatePush(outStorageOrder);
		Integer resultCode = 1;
		if (!isSuccess) {
			resultCode = 0;
			LogContext.error("获取流水号异常", outStorage.getOrderId() + "获取流水号异常");
		}
		pushService.insertPush(outStorageOrder, isSuccess);
		expResult.put("state_code", resultCode);
		return expResult;
	}
}
