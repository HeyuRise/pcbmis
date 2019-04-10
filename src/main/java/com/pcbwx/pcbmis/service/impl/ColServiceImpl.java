package com.pcbwx.pcbmis.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
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

import com.pcbwx.pcbmis.bean.response.ColCertification;
import com.pcbwx.pcbmis.bean.response.ColCheckReport;
import com.pcbwx.pcbmis.bean.response.ColReportBean;
import com.pcbwx.pcbmis.bean.response.ColReportDetail;
import com.pcbwx.pcbmis.bean.response.ColSizeWarpingBean;
import com.pcbwx.pcbmis.bean.response.ColSizeWarpingItemBean;
import com.pcbwx.pcbmis.component.CacheService;
import com.pcbwx.pcbmis.dao.ColOrderMapper;
import com.pcbwx.pcbmis.dao.ColReportMapper;
import com.pcbwx.pcbmis.dao.ColSizeWarpingItemMapper;
import com.pcbwx.pcbmis.dao.ColSizeWarpingMapper;
import com.pcbwx.pcbmis.enums.ColSmtReportEnum;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.model.ColOrder;
import com.pcbwx.pcbmis.model.ColReport;
import com.pcbwx.pcbmis.model.ColSizeWarping;
import com.pcbwx.pcbmis.model.ColSizeWarpingItem;
import com.pcbwx.pcbmis.model.EdaGuest;
import com.pcbwx.pcbmis.model.PcbCheckDetail;
import com.pcbwx.pcbmis.service.ColService;
import com.pcbwx.pcbmis.utils.DateTimeUtil;
import com.pcbwx.pcbmis.utils.EnumUtil;
import com.pcbwx.pcbmis.utils.POIUtil;
import com.pcbwx.pcbmis.utils.PcbmisUtil;

@Service("colService")
public class ColServiceImpl implements ColService {

	private static Logger logger = LoggerFactory.getLogger(ColServiceImpl.class);

	@Autowired
	private ColOrderMapper colOrderMapper;
	@Autowired
	private ColReportMapper colReportMapper;
	@Autowired
	private ColSizeWarpingMapper colSizeWarpingMapper;
	@Autowired
	private ColSizeWarpingItemMapper colSizeWarpingItemMapper;

	@Autowired
	private CacheService cacheService;

	@Override
	public Map<String, Object> getColReport(String serialNumber, String orderNumber, String guestName,
			Integer productNum, String boardName, String contactName, Date productDateStart, Date productDateEnd,
			String inspector, String reInspector, String orderStatus, Integer reportStatus, Integer page,
			Integer rows) {
		Map<String, Object> response = new HashMap<>();
		productDateEnd = DateTimeUtil.getTheDayLastTime(productDateEnd);
		Integer count = colReportMapper.countByQuery(serialNumber, orderNumber, guestName, productNum, boardName,
				contactName, productDateStart, productDateEnd, inspector, reInspector, orderStatus, reportStatus);
		List<ColReportBean> colReportBeans = new ArrayList<>();
		response.put("total", count);
		if (count == 0) {
			response.put("rows", colReportBeans);
			return response;
		}
		Integer start = (page - 1) * rows;
		colReportBeans = colReportMapper.selectByQuery(serialNumber, orderNumber, guestName, productNum, boardName,
				contactName, productDateStart, productDateEnd, inspector, reInspector, orderStatus, reportStatus, start,
				rows);
		for (ColReportBean colReportBean : colReportBeans) {
			String stasuStr = colReportBean.getReportStatus();
			ColSmtReportEnum reportEnum = EnumUtil.getEnumByCode(ColSmtReportEnum.class, "getCode", stasuStr);
			colReportBean.setReportStatus(reportEnum.getDescr());
		}
		response.put("rows", colReportBeans);
		return response;
	}

	@Override
	public Map<String, Object> getColReportDetail(Integer id) {
		Map<String, Object> response = new HashMap<>();
		ColReport colReport = colReportMapper.selectByPrimaryKey(id);
		if (colReport == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", ErrorCodeEnum.SYSTEM_ERROR.getDescr());
			return response;
		}
		String orderNumber = colReport.getOrderNumber();
		ColOrder colOrder = colOrderMapper.selectByOrderNumber(orderNumber);
		if (colOrder == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", ErrorCodeEnum.SYSTEM_ERROR.getDescr());
			return response;
		}
		String productDateStr = DateTimeUtil.date2dateStr(colOrder.getProductDate());
		Integer amount = colReport.getOutSum();
		Integer samplingNum = PcbmisUtil.getColSampling(amount);
		EdaGuest edaGuest = cacheService.getGuest(colOrder.getGuestCode());
		String outStorageDate = DateTimeUtil.date2dateStr(colReport.getOutStorageDate());
		ColReportDetail colReportDetail = new ColReportDetail();
		// 合格证
		ColCertification certification = obtainColCertification(colOrder, colReport, productDateStr, outStorageDate);
		colReportDetail.setCertification(certification);
		// 检验报告
		ColCheckReport colCheckReport = obtainColCheckReport(edaGuest, samplingNum, amount, colOrder, colReport,
				productDateStr, outStorageDate);
		colReportDetail.setReport(colCheckReport);
		// 尺寸翘曲度
		ColSizeWarpingBean colSizeWarping = getColSizeWarping(amount, samplingNum, edaGuest, colOrder, colReport,
				productDateStr, outStorageDate);
		colReportDetail.setSizeWarping(colSizeWarping);
		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		response.put("msg", ErrorCodeEnum.SUCCESS.getDescr());
		response.put("data", colReportDetail);
		return response;
	}

	/**
	 * 获取冷板合格证
	 * 
	 * @param colOrder
	 * @param colReport
	 * @param productDateStr
	 * @param inspectDate
	 * @return
	 */
	private ColCertification obtainColCertification(ColOrder colOrder, ColReport colReport, String productDateStr,
			String inspectDate) {
		ColCertification certification = new ColCertification();
		certification.setCompanyName("无锡市同步电子科技有限公司");
		certification.setName(colOrder.getColBoardName());
		certification.setProductDate(productDateStr);
		certification.setProductNum(colReport.getOutSum() + "PCS");
		certification.setInspector(colReport.getInspector());
		certification.setInspectDate(inspectDate);
		return certification;
	}

	/**
	 * 获取冷板检验报告
	 * 
	 * @param colOrder
	 * @param colReport
	 * @param productDateStr
	 * @param inspectDate
	 * @return
	 */
	private ColCheckReport obtainColCheckReport(EdaGuest edaGuest, Integer samplingNum, Integer amount,
			ColOrder colOrder, ColReport colReport, String productDateStr, String inspectDate) {
		String serialNumber = colReport.getSerialNumber();
		ColCheckReport colCheckReport = new ColCheckReport();
		colCheckReport.setBoardName(colOrder.getColBoardName());
		BigDecimal boardply = colOrder.getBoardPly().setScale(1, BigDecimal.ROUND_HALF_UP);
		colCheckReport.setBoardPlyRequire(boardply.toString());
		colCheckReport.setBoardplyResult("见" + serialNumber + "尺寸记录表");
		colCheckReport.setCounterboreTypeRequire(colOrder.getCounterboreTypeName());
		colCheckReport.setCounterboreTypeResult(colOrder.getCounterboreTypeName());
		colCheckReport.setSerialNumber(serialNumber);
		colCheckReport.setRevision(colReport.getRevision());
		colCheckReport.setDocumentNumber(colReport.getDocumentNumber());
		if (edaGuest != null) {
			colCheckReport.setGuestName(edaGuest.getShortNameCn());
		}
		colCheckReport.setImpurityRequire("无外来杂质");
		colCheckReport.setImpurityResult("冷板外观无外来杂质");
		colCheckReport.setInspectDate(inspectDate);
		colCheckReport.setReInspectDate(inspectDate);
		colCheckReport.setInspector(colReport.getInspector());
		colCheckReport.setReInspector(colReport.getReInspector());
		colCheckReport.setProductDate(productDateStr);
		colCheckReport.setProductNum(amount);
		colCheckReport.setProductNumRequire(amount);
		colCheckReport.setProductNumResult("检验数量为" + amount + "块，和订单的数量一致");
		colCheckReport.setSamplingNum(samplingNum);
		String sizeRequire = colOrder.getBoardLong() + "×" + colOrder.getBoardWide();
		colCheckReport.setSizeRequire(sizeRequire);
		colCheckReport.setSizeResult("见" + serialNumber + "尺寸记录表");
		String surfaceProcessName = colOrder.getSurfaceProcessName();
		colCheckReport.setSurfaceProcessRequire(surfaceProcessName);
		colCheckReport.setSurfaceProcessResult("表面处理为" + surfaceProcessName);
		return colCheckReport;
	}

	private ColSizeWarpingBean getColSizeWarping(Integer amount, Integer samplingNum, EdaGuest edaGuest,
			ColOrder colOrder, ColReport colReport, String productDateStr, String inspectDate) {
		ColSizeWarping colSizeWarping = colSizeWarpingMapper.selectByColReportId(colReport.getId());
		ColSizeWarpingBean colSizeWarpingBean = new ColSizeWarpingBean();
		colSizeWarpingBean.setBoardName(colOrder.getColBoardName());
		colSizeWarpingBean.setSerialNumber(colSizeWarping.getSerialNumber());
		colSizeWarpingBean.setRevision(colSizeWarping.getRevision());
		colSizeWarpingBean.setDocumentNumber(colSizeWarping.getDocumentNumber());
		if (edaGuest != null) {
			colSizeWarpingBean.setGuestName(edaGuest.getShortNameCn());
		}
		colSizeWarpingBean.setProductDate(productDateStr);
		colSizeWarpingBean.setProductNum(amount);
		colSizeWarpingBean.setSamplingNum(samplingNum);
		colSizeWarpingBean.setInspectDate(inspectDate);
		colSizeWarpingBean.setReInspectDate(inspectDate);
		colSizeWarpingBean.setInspector(colReport.getInspector());
		colSizeWarpingBean.setReInspector(colReport.getReInspector());
		//
		List<ColSizeWarpingItemBean> colSizeWarpingItemBeans = new ArrayList<>();
		List<ColSizeWarpingItem> colSizeWarpingItems = colSizeWarpingItemMapper.selectByColReportId(colReport.getId());
		ColSizeWarpingItemBean colSizeWarpingItemBean = null;
		Integer count = 1;
		BigDecimal boardLongAll = new BigDecimal(0);
		BigDecimal boardWideAll = new BigDecimal(0);
		BigDecimal boardPlyAll = new BigDecimal(0);
		for (ColSizeWarpingItem colSizeWarpingItem : colSizeWarpingItems) {
			colSizeWarpingItemBean = new ColSizeWarpingItemBean();
			BigDecimal boardLong = colSizeWarpingItem.getBoardLong();
			BigDecimal boardWide = colSizeWarpingItem.getBoardWide();
			BigDecimal boardPly = colSizeWarpingItem.getBoardPly();
			boardLongAll = boardLongAll.add(boardLong);
			boardWideAll = boardWideAll.add(boardWide);
			boardPlyAll = boardPlyAll.add(boardPly);
			colSizeWarpingItemBean.setItemName(count.toString());
			colSizeWarpingItemBean.setBoardLong(colSizeWarpingItem.getBoardLong().toString());
			colSizeWarpingItemBean.setBoardWide(colSizeWarpingItem.getBoardWide().toString());
			colSizeWarpingItemBean.setBoardPly(colSizeWarpingItem.getBoardPly().toString());
			colSizeWarpingItemBean.setWarping(colSizeWarpingItem.getWarping() + "%");
			colSizeWarpingItemBeans.add(colSizeWarpingItemBean);
			count = count + 1;
		}
		// 平均值
		BigDecimal samplingNumDec = new BigDecimal(samplingNum);
		colSizeWarpingItemBean = new ColSizeWarpingItemBean();
		colSizeWarpingItemBean.setItemName("平均值");
		colSizeWarpingItemBean
				.setBoardLong(boardLongAll.divide(samplingNumDec, 3, BigDecimal.ROUND_HALF_UP).toString());
		colSizeWarpingItemBean
				.setBoardWide(boardWideAll.divide(samplingNumDec, 3, BigDecimal.ROUND_HALF_UP).toString());
		colSizeWarpingItemBean
				.setBoardPly(boardPlyAll.divide(samplingNumDec, 3, BigDecimal.ROUND_HALF_UP).toString());
		colSizeWarpingItemBean.setWarping("0.4%");
		colSizeWarpingItemBeans.add(colSizeWarpingItemBean);
		// 标准值
		colSizeWarpingItemBean = new ColSizeWarpingItemBean();
		colSizeWarpingItemBean.setItemName("标准值");
		colSizeWarpingItemBean.setBoardLong(colOrder.getBoardLong().toString());
		colSizeWarpingItemBean.setBoardWide(colOrder.getBoardWide().toString());
		colSizeWarpingItemBean.setBoardPly(colOrder.getBoardPly().toString());
		colSizeWarpingItemBean.setWarping("0.75%");
		colSizeWarpingItemBeans.add(colSizeWarpingItemBean);
		// 公差
		colSizeWarpingItemBean = new ColSizeWarpingItemBean();
		colSizeWarpingItemBean.setItemName("公差");
		colSizeWarpingItemBean.setBoardLong("±0.3mm");
		colSizeWarpingItemBean.setBoardWide("±0.3mm");
		colSizeWarpingItemBean.setBoardPly("±10%");
		colSizeWarpingItemBean.setWarping("/");
		colSizeWarpingItemBeans.add(colSizeWarpingItemBean);
		// 判定
		colSizeWarpingItemBean = new ColSizeWarpingItemBean();
		colSizeWarpingItemBean.setItemName("判定");
		colSizeWarpingItemBean.setBoardLong("ACC");
		colSizeWarpingItemBean.setBoardWide("ACC");
		colSizeWarpingItemBean.setBoardPly("ACC");
		colSizeWarpingItemBean.setWarping("ACC");
		colSizeWarpingItemBeans.add(colSizeWarpingItemBean);
		colSizeWarpingBean.setSizeWarpingItems(colSizeWarpingItemBeans);
		return colSizeWarpingBean;
	}

	@Override
	public Workbook exportWork(Integer id) {
		String inFilePath = null;
		FileInputStream in = null;
		Workbook workbook = null;
		try {
			inFilePath = URLDecoder.decode(PcbCheckDetail.class.getResource("/template/col.xlsx").getPath(), "UTF-8");
			inFilePath = inFilePath.substring(1);
			in = new FileInputStream(File.separator + inFilePath);
			workbook = new XSSFWorkbook(in);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		Map<String, Object> response = this.getColReportDetail(id);
		Integer code = (Integer) response.get("result");
		if (!Objects.equals(code, ErrorCodeEnum.SUCCESS.getCode())) {
			return null;
		}
		ColReportDetail colReportDetail = (ColReportDetail) response.get("data");
		if (colReportDetail == null) {
			return null;
		}
		ColCheckReport colCheckReport = colReportDetail.getReport();
		ColCertification colCertification = colReportDetail.getCertification();
		ColSizeWarpingBean sizeWarping = colReportDetail.getSizeWarping();
		gerenateColCheckReport(colCheckReport, workbook);
		gerenateColCertification(colCertification, workbook);
		generateSizeAndWarping(sizeWarping, workbook);
		return workbook;
	}
	
	/**
	 * 生成 冷板报告
	 * @param report    报告信息
	 * @param workbook  xlsx
	 */
	private void gerenateColCheckReport(ColCheckReport report, Workbook workbook) {
		Sheet sheet = workbook.getSheetAt(0);
		// 头信息
		Row row = sheet.getRow(1);
		Cell cell = row.getCell(1);
		cell.setCellValue(report.getSerialNumber());
		cell = row.getCell(7);
		cell.setCellValue("版次：" + report.getRevision() + "       编号：" + report.getDocumentNumber());
		row = sheet.getRow(2);
		cell = row.getCell(4);
		cell.setCellValue(report.getGuestName());
		cell = row.getCell(8);
		cell.setCellValue(report.getBoardName());
		row = sheet.getRow(3);
		cell = row.getCell(4);
		cell.setCellValue(report.getProductDate());
		row = sheet.getRow(4);
		cell = row.getCell(4);
		cell.setCellValue(report.getProductNum());
		cell = row.getCell(8);
		cell.setCellValue(report.getSamplingNum());
		// 检验内容
		row = sheet.getRow(7);
		cell = row.getCell(5);
		cell.setCellValue(report.getProductNumRequire());
		cell = row.getCell(7);
		cell.setCellValue(report.getProductNumResult());
		row = sheet.getRow(11);
		cell = row.getCell(5);
		cell.setCellValue(report.getImpurityRequire());
		cell = row.getCell(7);
		cell.setCellValue(report.getImpurityResult());
		row = sheet.getRow(12);
		cell = row.getCell(5);
		cell.setCellValue(report.getSurfaceProcessRequire());
		cell = row.getCell(7);
		cell.setCellValue(report.getSurfaceProcessResult());
		row = sheet.getRow(13);
		cell = row.getCell(5);
		cell.setCellValue(report.getCounterboreTypeRequire());
		cell = row.getCell(7);
		cell.setCellValue(report.getCounterboreTypeResult());
		row = sheet.getRow(15);
		cell = row.getCell(5);
		cell.setCellValue(report.getSizeRequire());
		cell = row.getCell(7);
		cell.setCellValue(report.getSizeResult());
		row = sheet.getRow(16);
		cell = row.getCell(5);
		cell.setCellValue(report.getBoardPlyRequire());
		cell = row.getCell(7);
		cell.setCellValue(report.getBoardplyResult());
		// 尾部信息
		row = sheet.getRow(22);
		cell = row.getCell(4);
		cell.setCellValue(report.getInspector());
		cell = row.getCell(7);
		cell.setCellValue(report.getInspectDate());
		row = sheet.getRow(23);
		cell = row.getCell(4);
		cell.setCellValue(report.getReInspector());
		cell = row.getCell(7);
		cell.setCellValue(report.getReInspectDate());
	}
	
	/**
	 * 生成冷板合格证
	 * @param certification   合格证信息
	 * @param workbook		  xlsx
	 */
	private void gerenateColCertification(ColCertification certification, Workbook workbook) {
		Sheet sheet = workbook.getSheetAt(2);
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
	
	private void generateSizeAndWarping(ColSizeWarpingBean sizeWarping, Workbook workbook) {
		Sheet sheet = workbook.getSheetAt(1);
		List<ColSizeWarpingItemBean> sizeWarpingItemBeans = sizeWarping.getSizeWarpingItems();
		// 生成导出样式，写入平均值等和人员信息
		Integer size = sizeWarpingItemBeans.size();
		List<ColSizeWarpingItemBean> sizeWarpingItem = sizeWarpingItemBeans.subList(0, size - 4);
		List<ColSizeWarpingItemBean> sizeWarpingAve = sizeWarpingItemBeans.subList(size - 4, size);
		size = size - 4;
		if (size <= 9) {
			sheet.createRow(5);
			POIUtil.copyRows(workbook, 3, 1, 6, 12, 5);
			operateAve(sheet, 6, sizeWarpingAve);
			operateUser(sheet, 10, sizeWarping);
		} else if(size < 23) {
			sheet.createRow(5);
			POIUtil.copyRows(workbook, 3, 1, 0, 4, 5);
			sheet.createRow(10);
			POIUtil.copyRows(workbook, 3, 1, 6, 12, 10);
			operateAve(sheet, 11, sizeWarpingAve);
			operateUser(sheet, 15, sizeWarping);
		} else {
			sheet.createRow(5);
			POIUtil.copyRows(workbook, 3, 1, 0, 4, 5);
			sheet.createRow(10);
			POIUtil.copyRows(workbook, 3, 1, 0, 4, 10);
			sheet.createRow(15);
			POIUtil.copyRows(workbook, 3, 1, 6, 12, 15);
			operateAve(sheet, 16, sizeWarpingAve);
			operateUser(sheet, 20, sizeWarping);
		}
		workbook.removeSheetAt(3);
		// 写入头数据
		Row row = sheet.getRow(1);
		Cell cell = row.getCell(1);
		cell.setCellValue(sizeWarping.getSerialNumber());
		cell = row.getCell(10);
		cell.setCellValue("版次：" + sizeWarping.getRevision() + "       编号：" + sizeWarping.getDocumentNumber());
		row = sheet.getRow(2);
		cell = row.getCell(1);
		cell.setCellValue(sizeWarping.getGuestName());
		cell = row.getCell(7);
		cell.setCellValue(sizeWarping.getBoardName());
		row = sheet.getRow(3);
		cell = row.getCell(1);
		cell.setCellValue(sizeWarping.getProductDate());
		cell = row.getCell(7);
		cell.setCellValue(sizeWarping.getProductNum());
		cell = row.getCell(11);
		cell.setCellValue(sizeWarping.getSamplingNum());
		// 写入随机值
		for (int i = 0; i < sizeWarpingItem.size(); i++) {
			ColSizeWarpingItemBean item = sizeWarpingItem.get(i);
			if (i <= 12) {
				row = sheet.getRow(5);
				cell = row.getCell(i + 1);
				cell.setCellValue(item.getItemName());
				row = sheet.getRow(6);
				cell = row.getCell(i + 1);
				cell.setCellValue(item.getBoardLong());
				row = sheet.getRow(7);
				cell = row.getCell(i + 1);
				cell.setCellValue(item.getBoardWide());
				row = sheet.getRow(8);
				cell = row.getCell(i + 1);
				cell.setCellValue(item.getBoardPly());
				row = sheet.getRow(9);
				cell = row.getCell(i + 1);
				cell.setCellValue(item.getWarping());
			} else {
				row = sheet.getRow(10);
				cell = row.getCell(i - 12);
				cell.setCellValue(item.getItemName());
				row = sheet.getRow(11);
				cell = row.getCell(i - 12);
				cell.setCellValue(item.getBoardLong());
				row = sheet.getRow(12);
				cell = row.getCell(i - 12);
				cell.setCellValue(item.getBoardWide());
				row = sheet.getRow(13);
				cell = row.getCell(i - 12);
				cell.setCellValue(item.getBoardPly());
				row = sheet.getRow(14);
				cell = row.getCell(i - 12);
				cell.setCellValue(item.getWarping());
			}
		}
	}
	
	private void operateAve(Sheet sheet, Integer rowPoi, List<ColSizeWarpingItemBean> ave) {
		Row row = null;
		Cell cell = null;
		Integer cellPoi = 0;
		for (ColSizeWarpingItemBean item : ave) {
			row = sheet.getRow(rowPoi);
			cell = row.getCell(10 + cellPoi);
			cell.setCellValue(item.getBoardLong());
			row = sheet.getRow(rowPoi + 1);
			cell = row.getCell(10 + cellPoi);
			cell.setCellValue(item.getBoardWide());
			row = sheet.getRow(rowPoi + 2);
			cell = row.getCell(10 + cellPoi);
			cell.setCellValue(item.getBoardPly());
			row = sheet.getRow(rowPoi + 3);
			cell = row.getCell(10 + cellPoi);
			cell.setCellValue(item.getWarping());
			cellPoi = cellPoi + 1;
		}
	}
	
	private void operateUser(Sheet sheet, Integer rowPoi, ColSizeWarpingBean sizeWarping) {
		Row row = null;
		Cell cell = null;
		row = sheet.getRow(rowPoi);
		cell = row.getCell(9);
		cell.setCellValue(sizeWarping.getInspector());
		cell = row.getCell(12);
		cell.setCellValue(sizeWarping.getInspectDate());
		row = sheet.getRow(rowPoi + 1);
		cell = row.getCell(9);
		cell.setCellValue(sizeWarping.getReInspector());
		cell = row.getCell(12);
		cell.setCellValue(sizeWarping.getReInspectDate());
	}

}
