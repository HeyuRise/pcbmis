package com.pcbwx.pcbmis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.ColSizeWarpingItem;

public interface ColSizeWarpingItemMapper extends BaseMapper<ColSizeWarpingItem> {

	/**
	 * 获取尺寸翘曲度列表
	 * @param colSizeWarpingId		
	 * @return
	 */
	List<ColSizeWarpingItem> selectByColReportId(@Param("colReportId") Integer colReportId);
	
	/**
	 * 按冷板报告Id获取
	 * @param colReportId
	 * @return
	 */
	Integer deleteByColReportId(@Param("colReportId") Integer colReportId);
}