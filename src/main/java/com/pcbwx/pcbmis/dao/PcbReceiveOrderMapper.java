package com.pcbwx.pcbmis.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.PcbReceiveOrder;

public interface PcbReceiveOrderMapper extends BaseMapper<PcbReceiveOrder>{
	/*
	 * 获取接收列表
	 */
	List<PcbReceiveOrder> selectByParam(@Param("orderNum") String orderNum, 
			@Param("guestCodes") List<String> guestCodes, 
			@Param("factoryId") Integer factoryId,
			@Param("gradeId") Integer gradeId,
			@Param("productionNumSet") Integer productionNumSet,
			@Param("productionNumPcs") Integer productionNumPcs,
			@Param("contentId") String contentId,
			@Param("receiveNum") Integer receiveNum,
			@Param("receiveType") Integer receiveType,
			@Param("receiveDateStart") Date receiveDateStart,
			@Param("receiveDateEnd") Date receiveDateEnd,
			@Param("userName") String username,
			@Param("start") Integer start,
			@Param("size") Integer size);
	/*
	 * 获取接收列表条数
	 */
	Integer countByParam(@Param("orderNum") String orderNum, 
			@Param("guestCodes") List<String> guestCodes, 
			@Param("factoryId") Integer factoryId,
			@Param("gradeId") Integer gradeId,
			@Param("productionNumSet") Integer productionNumSet,
			@Param("productionNumPcs") Integer productionNumPcs,
			@Param("contentId") String contentId,
			@Param("receiveNum") Integer receiveNum,
			@Param("receiveType") Integer receiveType,
			@Param("receiveDateStart") Date receiveDateStart,
			@Param("receiveDateEnd") Date receiveDateEnd,
			@Param("userName") String username);
	
	/*
	 * 获取检验单号和收货类型
	 */
	List<PcbReceiveOrder> listByCheckNums(@Param("checkNums") Set<String> checkNums);
	
//	/*
//	 * 按报告单号查找
//	 */
//	List<PcbReceiveOrder> selectByReportNum(@Param("reportNum") String reportNum);
}