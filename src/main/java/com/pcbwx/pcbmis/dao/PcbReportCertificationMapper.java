package com.pcbwx.pcbmis.dao;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.PcbReportCertification;

public interface PcbReportCertificationMapper extends BaseMapper<PcbReportCertification>{
	PcbReportCertification selectByReportNum(@Param("reportNum") String reportNum);
	Integer deleteByReportNum(@Param("reportNum") String reportNum);
	
	/*
	 * 更新板名
	 */
	Integer updateBoardNameByOrderNumber(@Param("boardName") String boardName, 
			@Param("orderNumber") String orderNumber);
}