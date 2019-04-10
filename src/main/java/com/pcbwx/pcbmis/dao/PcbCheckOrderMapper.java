package com.pcbwx.pcbmis.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.PcbCheckOrder;

public interface PcbCheckOrderMapper extends BaseMapper<PcbCheckOrder> {
	

	List<PcbCheckOrder> listCheckOrderByProductOrder(@Param("productOrderNum") String productOrderNum);
	
	List<PcbCheckOrder> listCheckOrderByJoinOrder(@Param("joinBoardOrderCode") String joinBoardOrderCode);

	/**
	 * 按检验单号获取
	 * 
	 * @param checkNum
	 * @return
	 */
	PcbCheckOrder selectByCheckNum(@Param("checkNum") String checkNum);

	/**
	 * 获取待检验、待审核列表
	 * 
	 * @param tobe
	 * @param doing
	 * @param userCode
	 * @return
	 */
	Integer selectPreCheckNum(@Param("toBe") Integer tobe,
			@Param("doing") Integer doing,
			@Param("orderNum") String orderNum,
			@Param("inspector") String inspector,
			@Param("auditor") String auditor);

	List<PcbCheckOrder> selectPreCheck(@Param("toBe") Integer tobe,
			@Param("doing") Integer doing,
			@Param("orderNum") String orderNum,
			@Param("inspector") String inspector,
			@Param("auditor") String auditor, @Param("start") Integer start,
			@Param("pageSize") Integer pageSize);

//	/**
//	 * 获取检验单列表数据集合
//	 * 
//	 * @param keyWord
//	 * @param orderNums
//	 * @return
//	 */
//	List<PcbCheckOrder> selectByKeyWordAndOrderNumsNum(
//			@Param("keyWord") String keyWord,
//			@Param("orderNums") List<String> orderNums);

	
	/**
	 * 获取检验单数量
	 * 
	 * @param keyWord
	 * @param orderNums
	 * @return
	 */
	Integer getSelectByKeyWordAndOrderNumsNum(@Param("orderNum") String orderNum,
			@Param("content") String content,
			@Param("boardName") String boardName,
			@Param("factoryId") Integer factoryId,
			@Param("statusId") Integer statusId,
			@Param("gradeId") Integer gradeId,
			@Param("checkStart") Date checkStart,
			@Param("checkEnd") Date checkEnd,
			@Param("auditStart") Date auditStart,
			@Param("auditEnd") Date auditEnd,
			@Param("guestCodes") List<String> guestCodes);
	/**
	 * 获取检验单列表
	 * 
	 * @param keyWord
	 * @param orderNums
	 * @return
	 */
	List<PcbCheckOrder> selectByKeyWordAndOrderNums(
			@Param("orderNum") String orderNum,
			@Param("content") String content,
			@Param("boardName") String boardName,
			@Param("factoryId") Integer factoryId,
			@Param("statusId") Integer statusId,
			@Param("gradeId") Integer gradeId,
			@Param("checkStart") Date checkStart,
			@Param("checkEnd") Date checkEnd,
			@Param("auditStart") Date auditStart,
			@Param("auditEnd") Date auditEnd,
			@Param("guestCodes") List<String> guestCodes,
			@Param("start") Integer start, @Param("pageSize") Integer pageSize);

	/**
	 * 按检验单号集合查找
	 * @param checkNums
	 * @return
	 */
	List<PcbCheckOrder> selectByCheckNums(@Param("checkNums") Set<String> checkNums);

	/**
	 * 按出入库单号查找
	 * @param orderNumber
	 * @return
	 */
	List<PcbCheckOrder> selectByOrderNumer(@Param("orderNumber") String orderNumber);
	
	Integer updatePcbBoardName(@Param("productOrderNum") String productOrderNum, @Param("boardName") String boardName);
	Integer updateJoinBoardName(@Param("joinBoardOrderCode") String joinBoardOrderCode, @Param("boardName") String boardName);
//	Integer updateInStorageInfo(@Param("productPeriod") String productPeriod,
//			@Param("inStorageDate") Date inStorageDate,
//			@Param("inAmountPcs") Integer inAmountPcs,
//			@Param("orderNumber") String orderNumber);
	

	/**
	 * 按入库单集合查找
	 * @param instorageNums
	 * @return
	 */
	List<PcbCheckOrder> listCheckOrderByInstorageNum(@Param("instorageNums") Set<String> instorageNums);
}