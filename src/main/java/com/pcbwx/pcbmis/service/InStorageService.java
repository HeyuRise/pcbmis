package com.pcbwx.pcbmis.service;

import java.util.Date;
import java.util.Map;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.pcbmis.bean.ReceiveProductBody;
import com.pcbwx.pcbmis.bean.request.ReceiveProductBodyNew;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.model.JoinBoardOrder;
import com.pcbwx.pcbmis.model.PcbInStorageOrder;
import com.pcbwx.pcbmis.model.ProductOrder;

public interface InStorageService {
	
	/*
	 * 获取入库单列表
	 */
	Map<String, Object> getInStorageList(String orderNum, String guestName,
			String boardName, Integer inAmountNum, String inStorageDateStart,
			String inStorageDateEnd, Integer factoryId, Integer gradeId, Integer page, Integer rows);
	
	/*
	 *获取出入库生成的检验单和报告的状态 
	 */
	Map<String, Object> getProductState(String orderNumber);
	
	/*
	 * 接收产品
	 */
	@Deprecated
	Map<String, Object> receiveProduct(AkAuthUser wxtbUser, String orderNumber, ReceiveProductBody receiveProductBody);
	
	/*
	 * 接收产品
	 */
	Map<String, Object> receiveProductNew(AkAuthUser wxtbUser, String orderNumber, ReceiveProductBodyNew receiveProductBody);
	
	/*
	 * 修改板名
	 */
	ErrorCodeEnum modifyBoardName(String orderNumber, String boardname);
	
	/*
	 * 生成报告单
	 */
	Map<String, Object> recordCheckReport(PcbInStorageOrder inStorageOrder, ProductOrder productOrder,
			JoinBoardOrder joinBoardOrder, Date date, String checkNum);
}
