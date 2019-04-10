package com.pcbwx.pcbmis.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.PcbInStorageOrder;

public interface PcbInStorageOrderMapper extends BaseMapper<PcbInStorageOrder> {	
	
	/**
	 * 按出入库单号获取
	 * @param checkNum
	 * @return
	 */
	PcbInStorageOrder selectByOrderNumber(@Param("orderNumber") String orderNumber);
	
	
	List<PcbInStorageOrder> selectByInOrderNums(@Param("orderNums") List<String> orderNums);
				
	
	int updatePcbBoardName(@Param("productOrderNum") String productOrderNum, @Param("boardName") String boardName);
	int updateJoinBoardName(@Param("joinBoardOrderCode") String joinBoardOrderCode, @Param("boardName") String boardName);
	
	/**
	 * 按条件搜索入库单列表
	 * @param orderNum
	 * @param boardName
	 * @param inAmountPcs
	 * @param inStorageDateStart
	 * @param inStorageDateEnd
	 * @param factoryId
	 * @param gradeId
	 * @param guestCodes
	 * @return
	 */
	List<PcbInStorageOrder> selectByParam(@Param("orderNum") String orderNum,
			@Param("boardName") String boardName,
			@Param("inAmountPcs") Integer inAmountPcs,
			@Param("inStorageDateStart") Date inStorageDateStart,
			@Param("inStorageDateEnd") Date inStorageDateEnd,
			@Param("factoryId") Integer factoryId,
			@Param("gradeId") Integer gradeId,
			@Param("guestCodes") List<String> guestCodes,
			@Param("start") Integer start, 
			@Param("size") Integer size);
	
	Integer getSelectByParamNum(@Param("orderNum") String orderNum,
			@Param("boardName") String boardName,
			@Param("inAmountPcs") Integer inAmountPcs,
			@Param("inStorageDateStart") Date inStorageDateStart,
			@Param("inStorageDateEnd") Date inStorageDateEnd,
			@Param("factoryId") Integer factoryId,
			@Param("gradeId") Integer gradeId,
			@Param("guestCodes") List<String> guestCodes);
}

