package com.pcbwx.pcbmis.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.pcbwx.authkit.bean.AkAuthUser;

public interface ReceiveOrderService {
	
	/**
	 * 获取接收单列表
	 * @return
	 */
	Map<String, Object> goForReceiveOrderList(String orderNum, String guestName, Integer factoryId, Integer gradeId, 
			Integer productNumSet, Integer productNumPcs, Integer contentId, Integer receiveNum, Integer receiveType, 
			Date receiveDateStart, Date receiveDateEnd, String userName, Integer rows, Integer page) ;
	
	/**
	 * 修改接收单
	 * @param id
	 * @param receiveProductBody
	 * @return
	 */
	Map<String, Object> modifyReceiveOrder(AkAuthUser akAuthUser, Integer id, Integer receiveOrderType, List<Integer> contentIds);
	
	/**
	 * 删除接收单
	 * @param id
	 * @return
	 */
	Map<String, Object> deleteReceiveOrder(Integer id);
}
