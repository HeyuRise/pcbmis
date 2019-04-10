package com.pcbwx.pcbmis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.ProductBasemAterial;

public interface ProductBasemAterialMapper extends BaseMapper<ProductBasemAterial> {
	int clearByProductOrder(@Param("orderNum") String orderNum);
	
	List<ProductBasemAterial> selectByOrderNum(@Param("orderNum") String orderNum);
}