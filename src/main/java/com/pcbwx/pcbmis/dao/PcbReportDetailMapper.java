package com.pcbwx.pcbmis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.PcbReportDetail;

public interface PcbReportDetailMapper extends BaseMapper<PcbReportDetail>{
	List<PcbReportDetail> selectByReportNum(@Param("reportNum") String reportNum);
	List<PcbReportDetail> selectByReportNumAndOptionName(@Param("reportNum") String reportNum,
														@Param("optionName") String optionName);
														
	Integer deleteByReportNum(@Param("reportNum") String reportNum);													
}