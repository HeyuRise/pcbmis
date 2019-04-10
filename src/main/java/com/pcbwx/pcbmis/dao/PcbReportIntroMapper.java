package com.pcbwx.pcbmis.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.PcbReportIntro;

public interface PcbReportIntroMapper extends BaseMapper<PcbReportIntro>{
	PcbReportIntro selectByReportNum(@Param("reportNum") String reportNum);
	Integer deleteByReportNum(@Param("reportNum") String reportNum);
	
	List<PcbReportIntro> listByReportNums(@Param("reportNums") Set<String> reportNums);
	
	/*
	 * 更新板名
	 */
    Integer updateBoardNameByOrderNumber(@Param("boardName") String boardName, @Param("orderNumber") String orderNumber);

}