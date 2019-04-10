package com.pcbwx.pcbmis.dao;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.PcbReportVcut;

public interface PcbReportVcutMapper extends BaseMapper<PcbReportVcut>{
	/**
	 * 按运单号删除
	 * @param reportNum
	 * @return
	 */
	Integer deleteByReportNum(@Param("reportNum") String reportNum);
	
	/**
	 * 按运单号获取
	 * @param reportNum
	 * @return
	 */
	PcbReportVcut selectByReportNum(@Param("reportNum") String reportNum);
}