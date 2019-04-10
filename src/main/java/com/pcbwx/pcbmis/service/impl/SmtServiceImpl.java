package com.pcbwx.pcbmis.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcbwx.pcbmis.bean.response.SmtCertification;
import com.pcbwx.pcbmis.bean.response.SmtCheckReport;
import com.pcbwx.pcbmis.bean.response.SmtReportBean;
import com.pcbwx.pcbmis.bean.response.SmtReportDetail;
import com.pcbwx.pcbmis.component.CacheService;
import com.pcbwx.pcbmis.dao.SmtOrderMapper;
import com.pcbwx.pcbmis.dao.SmtReportMapper;
import com.pcbwx.pcbmis.enums.ColSmtReportEnum;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.model.EdaGuest;
import com.pcbwx.pcbmis.model.PcbCheckDetail;
import com.pcbwx.pcbmis.model.SmtOrder;
import com.pcbwx.pcbmis.model.SmtReport;
import com.pcbwx.pcbmis.service.SmtService;
import com.pcbwx.pcbmis.utils.DateTimeUtil;
import com.pcbwx.pcbmis.utils.EnumUtil;
import com.pcbwx.pcbmis.utils.PcbmisUtil;

@Service("smtService")
public class SmtServiceImpl implements SmtService {
	
	private static Logger logger = LoggerFactory.getLogger(SmtServiceImpl.class);

	@Autowired
	private SmtOrderMapper smtOrderMapper;
	@Autowired
	private SmtReportMapper smtReportMapper;

	@Autowired
	private CacheService cacheService;

	@Override
	public Map<String, Object> getSmtService(String serialNumber, String orderNumber, String guestName,
			Integer productNum, String boardName, String contactName, Date productDateStart, Date productDateEnd,
			String inspector, String reInspector, String orderStatus, Integer reportStatus, Integer page,
			Integer rows) {
		Map<String, Object> response = new HashMap<>();
		productDateEnd = DateTimeUtil.getTheDayLastTime(productDateEnd);
		Integer count = smtReportMapper.countByQuery(serialNumber, orderNumber, guestName, productNum, boardName,
				contactName, productDateStart, productDateEnd, inspector, reInspector, orderStatus, reportStatus);
		List<SmtReportBean> smtReportBeans = new ArrayList<>();
		response.put("total", count);
		if (count == 0) {
			response.put("rows", smtReportBeans);
			return response;
		}
		Integer start = (page - 1) * rows;
		smtReportBeans = smtReportMapper.selectByQuery(serialNumber, orderNumber, guestName, productNum, boardName,
				contactName, productDateStart, productDateEnd, inspector, reInspector, orderStatus, reportStatus, start,
				rows);
		for (SmtReportBean smtReportBean : smtReportBeans) {
			String stasuStr = smtReportBean.getReportStatus();
			ColSmtReportEnum reportEnum = EnumUtil.getEnumByCode(ColSmtReportEnum.class, "getCode", stasuStr);
			smtReportBean.setReportStatus(reportEnum.getDescr());
		}
		response.put("rows", smtReportBeans);
		return response;
	}

	@Override
	public Map<String, Object> getSmtReportDetail(Integer Id) {
		Map<String, Object> response = new HashMap<>();
		SmtReport smtReport = smtReportMapper.selectByPrimaryKey(Id);
		if (smtReport == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", ErrorCodeEnum.SYSTEM_ERROR.getDescr());
		    return response;
		}
		String orderNumber = smtReport.getOrderNumber();
		SmtOrder smtOrder = smtOrderMapper.selectByOrderNumber(orderNumber);
		if (smtOrder == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", ErrorCodeEnum.SYSTEM_ERROR.getDescr());
		    return response;
		}
		SmtReportDetail smtReportDetail = new SmtReportDetail();
		SmtCheckReport smtCheckReport = new SmtCheckReport();
		SmtCertification certification = new SmtCertification();
		smtReportDetail.setCertification(certification);
		smtReportDetail.setReport(smtCheckReport);
		String productDateStr = DateTimeUtil.date2dateStr(smtOrder.getProductDate());
		Integer smtAmout = smtReport.getOutSum();
		String outStorageDate = DateTimeUtil.date2dateStr(smtReport.getOutStorageDate());
		// 合格证
		certification.setCompanyName("无锡市同步电子科技有限公司");
		certification.setName(smtOrder.getBoardName());
		certification.setProductDate(productDateStr);
		certification.setProductNum(smtReport.getOutSum() + "PCS");
		certification.setInspector(smtReport.getInspector());
		certification.setInspectDate(outStorageDate);
		// 检验报告
		smtCheckReport.setSerialNumber(smtReport.getSerialNumber());
		smtCheckReport.setRevision(smtReport.getRevision());
		smtCheckReport.setDocumentNumber(smtReport.getDocumentNumber());
		EdaGuest edaGuest = cacheService.getGuest(smtOrder.getGuestCode());
		if (edaGuest != null) {
			smtCheckReport.setGuestName(edaGuest.getShortNameCn());
		}
		smtCheckReport.setBoardName(smtOrder.getBoardName());
		smtCheckReport.setProductDate(productDateStr);
		smtCheckReport.setProductNumRequire(smtReport.getOutSum());
		smtCheckReport.setProductNumResult(smtReport.getOutSum());
		smtCheckReport.setBatchNumberResult(smtOrder.getPcbBatchNumber());
		Integer xRay = smtOrder.getxRay();
		String XrayStr = "";
		if (xRay >= 1) {
			XrayStr = "有X-Ray照片";
		} else {
			XrayStr = "无X-Ray照片";
		}
		smtCheckReport.setxRayRequire(XrayStr);
		smtCheckReport.setxRayResult(XrayStr);
		Integer samplingNum = PcbmisUtil.getSmtSamplingNum(smtAmout);
		smtCheckReport.setSamplingNumRequire(samplingNum);
		smtCheckReport.setSamplingNumResult("抽检数量为" + samplingNum);
		smtCheckReport.setInspector(smtReport.getInspector());
		smtCheckReport.setReInspector(smtReport.getReInspector());
		smtCheckReport.setInspectDate(outStorageDate);
		smtCheckReport.setReInspectDate(outStorageDate);
		
		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		response.put("msg", ErrorCodeEnum.SUCCESS.getDescr());
		response.put("data", smtReportDetail);
	    return response;
	}

	@Override
	public Workbook exportWork(Integer id) {
		String inFilePath = null;
		FileInputStream in = null;
		Workbook workbook = null;
		try {
			inFilePath = URLDecoder.decode(PcbCheckDetail.class.getResource("/template/smt.xlsx").getPath(), "UTF-8");
			inFilePath = inFilePath.substring(1);
			in = new FileInputStream(File.separator + inFilePath);
			workbook = new XSSFWorkbook(in);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		Map<String, Object> smtDetail = this.getSmtReportDetail(id);
		Integer code = (Integer) smtDetail.get("result");
		if (!Objects.equals(code, ErrorCodeEnum.SUCCESS.getCode())) {
			return null;
		}
		SmtReportDetail smtReportDetail = (SmtReportDetail) smtDetail.get("data");
		if (smtReportDetail == null) {
			return null;
		}
		SmtCheckReport report = smtReportDetail.getReport();
		SmtCertification certification = smtReportDetail.getCertification();
		writeSmtReport(workbook, report);
		writeSmtCertification(workbook, certification);
		return workbook;
	}
	
	/**
	 * 写入smt检验报告信息
	 * @param workbook  xlsx 表格
	 * @param report    报告详细信息
	 */
	private void writeSmtReport(Workbook workbook, SmtCheckReport report) {
		Sheet sheet = workbook.getSheetAt(0);
		Row row = sheet.getRow(1);
		Cell cell = row.getCell(1);
		cell.setCellValue(report.getSerialNumber());
		cell = row.getCell(6);
		cell.setCellValue("版次：" + report.getRevision() + "       编号：" + report.getDocumentNumber());
		row = sheet.getRow(2);
		cell = row.getCell(4);
		cell.setCellValue(report.getGuestName());
		cell = row.getCell(8);
		cell.setCellValue(report.getBoardName());
		row = sheet.getRow(3);
		cell = row.getCell(4);
		cell.setCellValue(report.getProductDate());
		row = sheet.getRow(6);
		cell = row.getCell(5);
		cell.setCellValue(report.getProductNumRequire());
		cell = row.getCell(7);
		cell.setCellValue(report.getProductNumResult());
		row = sheet.getRow(7);
		cell = row.getCell(7);
		cell.setCellValue(report.getBatchNumberResult());
		row = sheet.getRow(10);
		cell = row.getCell(5);
		cell.setCellValue(report.getxRayRequire());
		cell = row.getCell(7);
		cell.setCellValue(report.getxRayResult());
		row = sheet.getRow(12);
		cell = row.getCell(5);
		cell.setCellValue(report.getSamplingNumRequire());
		cell = row.getCell(7);
		cell.setCellValue(report.getSamplingNumResult());
		row = sheet.getRow(20);
		cell = row.getCell(2);
		cell.setCellValue("检验人员：" + report.getInspector());
		cell = row.getCell(7);
		cell.setCellValue(report.getInspectDate());
		row = sheet.getRow(21);
		cell = row.getCell(2);
		cell.setCellValue("复核人员：" + report.getReInspector());
		cell = row.getCell(7);
		cell.setCellValue(report.getReInspectDate());
	}
	
	/**
	 * 写入smt合格证信息
	 * @param workbook       xlsx 表格
	 * @param certification  报告详细信息
	 */
	private void writeSmtCertification(Workbook workbook, SmtCertification certification) {
		Sheet sheet = workbook.getSheetAt(1);
		Row row = sheet.getRow(0);
		Cell cell = row.getCell(0);
		cell.setCellValue(certification.getCompanyName());
		row = sheet.getRow(2);
		cell = row.getCell(1);
		cell.setCellValue(certification.getName());
		row = sheet.getRow(4);
		cell = row.getCell(1);
		cell.setCellValue(certification.getProductDate());
		row = sheet.getRow(6);
		cell = row.getCell(1);
		cell.setCellValue(certification.getProductNum());
		row = sheet.getRow(7);
		cell = row.getCell(1);
		cell.setCellValue(certification.getInspector());
		row = sheet.getRow(8);
		cell = row.getCell(1);
		cell.setCellValue(certification.getInspectDate());
	}
}
