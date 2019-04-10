package com.pcbwx.pcbmis.dao;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.ColSizeWarping;

public interface ColSizeWarpingMapper extends BaseMapper<ColSizeWarping>{
	
	/**
	 * 按冷板报告Id获取
	 * @return
	 */
	ColSizeWarping selectByColReportId(@Param("colReportId") Integer colReportId);
}