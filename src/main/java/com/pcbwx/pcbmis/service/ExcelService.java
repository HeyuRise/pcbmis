package com.pcbwx.pcbmis.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.pcbmis.bean.ReportCertification;
import com.pcbwx.pcbmis.bean.UnqualifiedQueryBean;

public interface ExcelService {

	/**
	 * 导出检验报告
	 */
	Workbook exportReport(String reportNum, AkAuthUser wxtbUser);
	
	/**
	 * 导出不合格品统计表
	 * @return
	 */
	SXSSFWorkbook exportUnqualifiedList(UnqualifiedQueryBean unqualifiedQueryBean);
	
	/**
	 * 导出不合格品统计表
	 * @return
	 */
	SXSSFWorkbook exportCheckList(String orderNum, String guest,
			Integer gradeId, Integer content, String boardName,
			Integer factoryId, Integer statusId, String checkStart,
			String checkEnd, String auditStart, String auditEnd);
	
	/**
	 * 写入产品合格证数据
	 * @param sheet
	 * @param reportCertification
	 * @param companyName
	 */
	void writeReportCertification(XSSFSheet sheet, ReportCertification reportCertification, String companyName);
}
