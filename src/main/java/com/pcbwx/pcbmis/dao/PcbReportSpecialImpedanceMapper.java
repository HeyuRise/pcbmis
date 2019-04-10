package com.pcbwx.pcbmis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.PcbReportSpecialImpedance;

public interface PcbReportSpecialImpedanceMapper extends BaseMapper<PcbReportSpecialImpedance>{
	List<PcbReportSpecialImpedance> selectByReportNumAndId(@Param("reportNum") String reportNum, 
															@Param("specialId") Integer id);
	Integer deleteByReportNum(@Param("reportNum") String reportNum);
}