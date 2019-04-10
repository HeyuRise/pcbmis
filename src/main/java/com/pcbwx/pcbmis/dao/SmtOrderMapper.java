package com.pcbwx.pcbmis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.SmtOrder;

public interface SmtOrderMapper extends BaseMapper<SmtOrder>{
	
	/**
	 * 按工单号集合查找
	 * @param codes
	 * @return
	 */
	List<SmtOrder> listByCodes(List<String> codes);
	
	/**
	 * 按工单号查找
	 * @param orderNumber
	 * @return
	 */
	SmtOrder selectByOrderNumber(@Param("orderNumber") String orderNumber);
}