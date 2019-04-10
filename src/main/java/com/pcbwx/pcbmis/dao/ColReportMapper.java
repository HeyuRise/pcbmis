package com.pcbwx.pcbmis.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.bean.response.ColReportBean;
import com.pcbwx.pcbmis.model.ColReport;

public interface ColReportMapper extends BaseMapper<ColReport>{
	/**
	 * 获取列表总数量
	 */
	Integer countByQuery(@Param("serialNumber") String serialNumber, @Param("orderNumber") String orderNumber,
			@Param("guestName") String guestName, @Param("productNum") Integer productNum, @Param("boardName") String boardName,
			@Param("contactName") String contactName, @Param("productDateStart") Date productDateStart,
			@Param("productDateEnd") Date productDateEnd, @Param("inspector") String inspector, @Param("reInspector") String reInspector,
			@Param("orderStatus") String orderStatus, @Param("reportStatus") Integer reportStatus);

	/**
	 * 获取列表
	 * @return
	 */
	List<ColReportBean> selectByQuery(@Param("serialNumber") String serialNumber, @Param("orderNumber") String orderNumber,
			@Param("guestName") String guestName, @Param("productNum") Integer productNum, @Param("boardName") String boardName,
			@Param("contactName") String contactName, @Param("productDateStart") Date productDateStart,
			@Param("productDateEnd") Date productDateEnd, @Param("inspector") String inspector, @Param("reInspector") String reInspector,
			@Param("orderStatus") String orderStatus, @Param("reportStatus") Integer reportStatus, @Param("start") Integer start,
			@Param("pageSize") Integer pageSize);
	
	/**
	 * 按id查找
	 * @param orderId
	 * @return
	 */
	ColReport selectByOrderId(@Param("orderId") Integer orderId);
}