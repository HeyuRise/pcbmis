package com.pcbwx.pcbmis.service;

import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

public interface SmtService {

	/**
	 * 获取smt报告列表
	 * 
	 * @return
	 */
	Map<String, Object> getSmtService(String serialNumber, String orderNumber, String guestName, Integer productNum,
			String boardName, String contactName, Date productDateStart, Date productDateEnd, String inspector,
			String reInspector, String orderStatus, Integer reportStatus, Integer page, Integer rows);
	
	/**
	 * 获取报告详情
	 * @param Id
	 * @return
	 */
	Map<String, Object> getSmtReportDetail(Integer Id);
	
	/**
	 * 导出
	 * @param id
	 * @return
	 */
	Workbook exportWork(Integer id);
}
