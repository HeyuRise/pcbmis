package com.pcbwx.pcbmis.dao;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.OutStorageOrder;

public interface OutStorageOrderMapper extends BaseMapper<OutStorageOrder>{
	
	/**
	 * 按订单id查找
	 * @param orderId
	 * @return
	 */
	OutStorageOrder selectByOrderId(@Param("orderId") Integer orderId);
}