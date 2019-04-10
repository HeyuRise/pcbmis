package com.pcbwx.pcbmis.dao;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.PcbReportIntegrity;

public interface PcbReportIntegrityMapper extends BaseMapper<PcbReportIntegrity>{
	PcbReportIntegrity selectByReportNum(@Param("reportNum") String reportNum);
	Integer deleteByReportNum(@Param("reportNum") String reportNum);
}