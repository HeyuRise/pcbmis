package com.pcbwx.pcbmis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.ProductOrder;

public interface ProductOrderMapper extends BaseMapper<ProductOrder> {
	
	ProductOrder selectByOrderNum(@Param("orderNum") String orderNum);
	
	List<ProductOrder> listByOrderNum(List<String> orderNums);
	
	Integer getOrderNums();
	
	List<ProductOrder> getOrderList(@Param("start") Integer start, @Param("pageSize") Integer pageSize);
	
	Integer getOrderNumsByCondition(@Param("orderNum") String orderNum, 
									@Param("guestCodes") List<String> guestCodes, 
									@Param("factoryId") Integer factoryId);
	
	List<ProductOrder> getOrderListByCondition(@Param("orderNum") String orderNum, 
												@Param("guestCodes") List<String> guestCodes, 
												@Param("factoryId") Integer factoryId, 
												@Param("start") Integer start, 
												@Param("pageSize") Integer pageSize);
	
}