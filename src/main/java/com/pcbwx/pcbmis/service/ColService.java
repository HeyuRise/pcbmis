package com.pcbwx.pcbmis.service;

import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

public interface ColService {

	/**
	 * 获取Col列表
	 * @param serialNumber
	 * @param orderNumber
	 * @param guestName
	 * @param productNum
	 * @param boardName
	 * @param contactName
	 * @param productDateStart
	 * @param productDateEnd
	 * @param inspector
	 * @param reInspector
	 * @param orderStatus
	 * @param reportStatus
	 * @param page
	 * @param rows
	 * @return
	 */
	Map<String, Object> getColReport(String serialNumber, String orderNumber, String guestName, Integer productNum,
			String boardName, String contactName, Date productDateStart, Date productDateEnd, String inspector,
			String reInspector, String orderStatus, Integer reportStatus, Integer page, Integer rows);
	
	/**
	 * 获取Col报告详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getColReportDetail(Integer id);
	
	/**
	 * 导出
	 * @param id
	 * @return
	 */
	Workbook exportWork(Integer id);
		
}
