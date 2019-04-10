package com.pcbwx.pcbmis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.PcbReportCertificationExtra;

public interface PcbReportCertificationExtraMapper extends BaseMapper<PcbReportCertificationExtra>{
	
	/**
	 * 批量插入
	 * @param list
	 * @return
	 */
	Integer insertBatch(@Param("list") List<PcbReportCertificationExtra> list);
	
	/**
	 * 按报告单号查找
	 * @param reportNum
	 * @return
	 */
	List<PcbReportCertificationExtra> selectByReportNum(@Param("reportNum") String reportNum);
	
	/**
	 * 按报告单号删除
	 * @param reportNum
	 * @return
	 */
	Integer deleteByReportNum(@Param("reportNum") String reportNum);
}