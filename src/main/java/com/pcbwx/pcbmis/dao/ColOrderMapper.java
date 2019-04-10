package com.pcbwx.pcbmis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.ColOrder;

public interface ColOrderMapper extends BaseMapper<ColOrder>{
	
	/**
	 * 按工单号集合查找
	 * @param codes
	 * @return
	 */
	List<ColOrder> listByCodes(List<String> codes);
	
	/**
	 * 按工单号查找
	 * @return
	 */
	ColOrder selectByOrderNumber(@Param("orderNumber") String orderNumber);
}