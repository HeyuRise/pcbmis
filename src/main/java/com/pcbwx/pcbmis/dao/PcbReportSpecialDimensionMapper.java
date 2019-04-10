package com.pcbwx.pcbmis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.PcbReportSpecialDimension;

public interface PcbReportSpecialDimensionMapper extends BaseMapper<PcbReportSpecialDimension>{
	/**
	 * 按报告单号删除
	 * @return
	 */
	Integer deleteByReportNum(@Param("reportNum") String reportNum);
	
	/**
	 * 按报告单号删除
	 * @param reportNum
	 * @return
	 */
	List<PcbReportSpecialDimension> selectByReportNum(@Param("reportNum") String reportNum);
}