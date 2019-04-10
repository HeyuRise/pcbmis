package com.pcbwx.pcbmis.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.pcbmis.bean.BaseReportInfo;
import com.pcbwx.pcbmis.bean.CertificationExtra;
import com.pcbwx.pcbmis.bean.CheckInfo;
import com.pcbwx.pcbmis.bean.ReportApertureCheck;
import com.pcbwx.pcbmis.bean.ReportCertification;
import com.pcbwx.pcbmis.bean.ReportIntegrity;
import com.pcbwx.pcbmis.bean.ReportIntro;
import com.pcbwx.pcbmis.bean.ReportItem;
import com.pcbwx.pcbmis.bean.ReportSpecialDimension;
import com.pcbwx.pcbmis.bean.ReportSpecialDimensionItem;
import com.pcbwx.pcbmis.bean.ReportSpecialImpedance;
import com.pcbwx.pcbmis.bean.ReportVcut;
import com.pcbwx.pcbmis.bean.UnqualifiedQueryBean;
import com.pcbwx.pcbmis.component.CacheService;
import com.pcbwx.pcbmis.dao.PcbCheckOrderMapper;
import com.pcbwx.pcbmis.dao.PcbCheckReportMapper;
import com.pcbwx.pcbmis.dao.PcbReceiveOrderMapper;
import com.pcbwx.pcbmis.dao.PcbReportIntroMapper;
import com.pcbwx.pcbmis.dao.PcbUnqualifiedMapper;
import com.pcbwx.pcbmis.dao.ProductOrderMapper;
import com.pcbwx.pcbmis.enums.DictionaryEnum;
import com.pcbwx.pcbmis.enums.DisposalMethodEnum;
import com.pcbwx.pcbmis.enums.InstorageOrderTypeEnum;
import com.pcbwx.pcbmis.enums.ProductTypeEnum;
import com.pcbwx.pcbmis.model.Dictionary;
import com.pcbwx.pcbmis.model.PcbCheckDetail;
import com.pcbwx.pcbmis.model.PcbCheckOrder;
import com.pcbwx.pcbmis.model.PcbCheckReport;
import com.pcbwx.pcbmis.model.PcbReceiveOrder;
import com.pcbwx.pcbmis.model.PcbReportIntro;
import com.pcbwx.pcbmis.model.PcbUnqualified;
import com.pcbwx.pcbmis.model.ProductOrder;
import com.pcbwx.pcbmis.model.WxtbUser;
import com.pcbwx.pcbmis.service.ExcelService;
import com.pcbwx.pcbmis.service.ReportService;
import com.pcbwx.pcbmis.service.SupportService;
import com.pcbwx.pcbmis.service.UtilService;
import com.pcbwx.pcbmis.utils.DateTimeUtil;
import com.pcbwx.pcbmis.utils.EnumUtil;
import com.pcbwx.pcbmis.utils.ExcelProcess;
import com.pcbwx.pcbmis.utils.FileUtil;
import com.pcbwx.pcbmis.utils.POIUtil;
import com.pcbwx.pcbmis.utils.PcbmisUtil;
import com.pcbwx.pcbmis.utils.StringUtil;

@Service("excelService")
public class ExcelServiceImpl implements ExcelService{
    private static Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);
	
	@Autowired
	private PcbUnqualifiedMapper pcbUnqualifiedMapper;
	@Autowired
	private PcbCheckOrderMapper pcbCheckOrderMapper;
	@Autowired
	private ProductOrderMapper productOrderMapper;
	@Autowired
	private PcbCheckReportMapper pcbCheckReportMapper;
	@Autowired
	private PcbReportIntroMapper pcbReportIntroMapper;
	@Autowired
	private PcbReceiveOrderMapper pcbReceiveOrderMapper;
	
	@Autowired
	private ReportService reportService;
	@Autowired
	private UtilService utilService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private SupportService supportService;
	
	@Override
	public Workbook exportReport(String reportNum, AkAuthUser wxtbUser) {
		String inFilePath = null;
		FileInputStream in = null;
		Workbook workbook = null;
		try {
			Map<String, Object> map = reportService.goForReportDetail(null, reportNum, 2, null);
			if (map.get("companyCode") == null) {
				return workbook;
			}
			// 获取基础信息
			BaseReportInfo baseReportInfo = (BaseReportInfo) map.get("baseReportInfo");
			if (baseReportInfo != null) {
				supportService.doOperateLog("导出", baseReportInfo.getOrderNum(), "印制板检验报告", wxtbUser.getUsername());
			}
			// 获取出货检验报告数据
			ReportIntro reportIntro = (ReportIntro)map.get("report_intro");
			// 获取检验内容数据
			@SuppressWarnings("unchecked")
			Map<String, ReportItem> reportIteMap = (Map<String, ReportItem>)map.get("report_content");
			// 获取孔径检测数据
			@SuppressWarnings("unchecked")
			List<ReportApertureCheck> reportApertureChecks = (List<ReportApertureCheck>)map.get("aperture_check");
			// 特殊尺寸
			ReportSpecialDimension reportSpecialDimension = (ReportSpecialDimension) map.get("special_dimensions");
			// v_cut测量
			ReportVcut reportVcut = (ReportVcut) map.get("report_vcut");
			// 获取完善性报告数据
			ReportIntegrity reportIntegrity = (ReportIntegrity)map.get("report_integrity");
			// 获取特性阻抗数据
			// 单端阻抗
			@SuppressWarnings("unchecked")
			List<ReportSpecialImpedance> singleEndedImpedancesList = (List<ReportSpecialImpedance>)map.get("single_ended_impedances");
			// 差分阻抗
			@SuppressWarnings("unchecked")
			List<ReportSpecialImpedance> differentialCharacteristicImpedanceList = (List<ReportSpecialImpedance>)map.get("differential_characteristic_impedance");
			// 判断intro报告中是否有新的行，如果有，就用第二个报告模板，否则用第一个报告模板
			boolean hasNewRow;
			if (reportIntro != null  && !StringUtils.isEmpty(reportIntro.getNewDateKey()) && !StringUtils.isEmpty(reportIntro.getNewDateValue())) {
				hasNewRow = true;
			} else {
				hasNewRow = false;
			}
			if (hasNewRow) {
				inFilePath = URLDecoder.decode(PcbCheckDetail.class.getResource("/template/test_report2.xlsx")
						.getPath(), "UTF-8");
			} else {
				inFilePath = URLDecoder.decode(PcbCheckDetail.class.getResource("/template/test_report.xlsx")
						.getPath(), "UTF-8");
			}
			inFilePath = inFilePath.substring(1);
			in = new FileInputStream(File.separator + inFilePath);
			workbook = new XSSFWorkbook(in);
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(0);
			Cell cell = row.getCell(4);
			cell.setCellValue("无锡市同步电子有限公司");
			// 写入出货检验报告数据
			// No
			String serialNumber;
			if (map.get("serialNumber") == null) {
				serialNumber = "";
			} else {
				serialNumber = map.get("serialNumber").toString();
			}
			// 版次
			String revision;
			if (map.get("revision") == null) {
				revision = "";
			} else {
				revision = map.get("revision").toString();
			}
			// 编号
			String documentNumber;
			if (map.get("documentNumber") == null) {
				documentNumber = "";
			} else {
				documentNumber = map.get("documentNumber").toString();
			}
			/**
			 * 写入检验报告数据
			 */
			this.writeReportIntro(sheet, reportIntro, serialNumber, revision, documentNumber, hasNewRow, (String)map.get("companyName"));
			/**
			 * 写入检验内容数据
			 */
			this.writeReportIteMap(sheet, reportIteMap, hasNewRow);
			/**
			 * 写入孔径检测数据
			 */
			XSSFSheet sheetApertureCheck = (XSSFSheet) workbook.getSheetAt(1);
			Integer lastRow = this.writeReportApertureChecks(sheetApertureCheck, reportApertureChecks, hasNewRow);
				// 添加表尾（把存在最后一个sheet的格式复制过来）
			POIUtil.copyRows(workbook, 6, 1, 0, 1, lastRow);
				// 在主sheet最后新建一行
			sheet.createRow(sheet.getLastRowNum() + 1);
				// 把孔径检测sheet复制到主sheet
			POIUtil.copyRows(workbook, 1, 0, 0, lastRow + 2, sheet.getLastRowNum());
			/**
			 * 写入特殊尺寸
			 */
			XSSFSheet sheetSpecialDimension = (XSSFSheet) workbook.getSheetAt(2);
			Integer lastRowSpecialDimension = this.writeSpecialDimension(sheetSpecialDimension, reportSpecialDimension);
				// 添加表尾（把存在最后一个sheet的格式复制过来）
			POIUtil.copyRows(workbook, 6, 2, 3, 4, lastRowSpecialDimension);
				// 在主sheet最后新建一行
			sheet.createRow(sheet.getLastRowNum() + 1);
				// 把特殊尺寸sheet复制到主sheet
			POIUtil.copyRows(workbook, 2, 0, 0, lastRowSpecialDimension + 2, sheet.getLastRowNum());
			/**
			 * 写入V_cut检测
			 */
			XSSFSheet xssfSheetVcut = (XSSFSheet) workbook.getSheetAt(3);
			this.writeVcut(xssfSheetVcut, reportVcut);
				// 在主sheet最后新建一行
			sheet.createRow(sheet.getLastRowNum() + 1);
			Integer vcutLast = sheet.getLastRowNum();
				// 把V_cut检测sheet复制到主sheet
			POIUtil.copyRows(workbook, 3, 0, 0, xssfSheetVcut.getLastRowNum(), vcutLast);
			/**
			 * 写入电气完善性报告数据
			 */
			XSSFSheet sheetReportIntegrity = (XSSFSheet) workbook.getSheetAt(4);
			this.writeReportIntegrity(sheetReportIntegrity, reportIntegrity, hasNewRow);
				// 在主sheet最后新建一行
			sheet.createRow(sheet.getLastRowNum() + 1);
				// 把电气完善性报告sheet复制到主sheet
			POIUtil.copyRows(workbook, 4, 0, 0, sheetReportIntegrity.getLastRowNum(), sheet.getLastRowNum());
			/**
			 * 写入特性阻抗数据
			 */
			XSSFSheet xssfSheet = (XSSFSheet) workbook.getSheetAt(5);
			Integer specialImpedance = this.writeReportSpecialImpedance(workbook, xssfSheet, singleEndedImpedancesList, differentialCharacteristicImpedanceList);
				// 添加表尾（把存在最后一个sheet的格式复制过来）
			POIUtil.copyRows(workbook, 6, 5, 6, 7, specialImpedance);
				// 在主sheet最后新建一行
			sheet.createRow(sheet.getLastRowNum() + 1);
				// 把特性阻抗sheet复制到主sheet
			POIUtil.copyRows(workbook, 5, 0, 0, xssfSheet.getLastRowNum(), sheet.getLastRowNum());
			// 插入图片
	    	String picPath =  URLDecoder.decode(POIUtil.class.getResource("/template/pic.png").getPath(), "UTF-8");
	    	InputStream is = new FileInputStream(picPath);
			POIUtil.operate(workbook, sheet, vcutLast + 5, 1, is, Workbook.PICTURE_TYPE_PNG);
			// 删除无用sheet
			for (int i = 0; i < 6; i++) {
				workbook.removeSheetAt(1);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return workbook;
	}
	/**
	 * 写入出货检验报告数据
	 * @param sheet
	 * @param reportIntro
	 * @param serialNum
	 * @param banci
	 * @param bianhao
	 */
	void writeReportIntro(Sheet sheet, ReportIntro reportIntro, String serialNum, String banci, String bianhao, boolean hasNewRow, String companyName) {
		if (reportIntro == null) {
			return;
		}
		Row row = sheet.getRow(0);
		Cell cellCompany = row.getCell(4);
		if (companyName.contains("科技")) {
			cellCompany.setCellValue("无锡市同步电子科技有限公司");
		}else {
			cellCompany.setCellValue("无锡市同步电子有限公司");
		}
		row = sheet.getRow(8);
		Cell cell = row.getCell(5);
		cell.setCellValue(serialNum);
		Cell cell2 = row.getCell(15);
		StringBuffer doc = new StringBuffer();
		doc.append("版次：").append(banci).append("  编号：").append(bianhao);
		cell2.setCellValue(doc.toString());
		
		if (hasNewRow) {
			// 顾客名称
			Row row2 = sheet.getRow(10);
			Cell cell3 = row2.getCell(9);
			cell3.setCellValue(reportIntro.getGuestName());
			// 板名
			Row row3 = sheet.getRow(11);
			Cell cell4 = row3.getCell(9);
			cell4.setCellValue(reportIntro.getBoardName());
			// 动态添加的行
			Row row4 = sheet.getRow(12);
			Cell cell10 = row4.getCell(4);
			String key = reportIntro.getNewDateKey();
			cell10.setCellValue(key == null ? "" : key + ":");
			Cell cell5 = row4.getCell(9);
			cell5.setCellValue(reportIntro.getNewDateValue());
			// 数量
			Row row5 = sheet.getRow(13);
			Cell cell6 = row5.getCell(9);
			String number = reportIntro.getNumber();
			if (number == null) {
				number = "";
			}
			String unit = reportIntro.getUnit();
			if (unit == null) {
				unit = "PCS";
			}
			StringBuffer numberUnit = new StringBuffer();
			numberUnit.append(number).append(unit);
			cell6.setCellValue(numberUnit.toString());
			// 生产周期
			Row row6 = sheet.getRow(14);
			Cell cell7 = row6.getCell(9);
			cell7.setCellValue(reportIntro.getProductPeriod() == null ? "" : reportIntro.getProductPeriod().toString());
			// 验收标准
			Row row7 = sheet.getRow(15);
			Cell cell8 = row7.getCell(9);
			cell8.setCellValue(reportIntro.getAcceptanceStandard());
			// 出货日期
			Row row8 = sheet.getRow(16);
			Cell cell9 = row8.getCell(9);
			cell9.setCellValue(reportIntro.getDispatchDate());
		} else {
			// 顾客名称
			Row row2 = sheet.getRow(10);
			Cell cell3 = row2.getCell(9);
			cell3.setCellValue(reportIntro.getGuestName());
			// 板名
			Row row3 = sheet.getRow(11);
			Cell cell4 = row3.getCell(9);
			cell4.setCellValue(reportIntro.getBoardName());
			// 数量
			Row row5 = sheet.getRow(12);
			Cell cell6 = row5.getCell(9);
			String number = reportIntro.getNumber();
			if (number == null) {
				number = "";
			}
			String unit = reportIntro.getUnit();
			if (unit == null) {
				unit = "PCS";
			}
			StringBuffer numberUnit = new StringBuffer();
			numberUnit.append(number).append(unit);
			cell6.setCellValue(numberUnit.toString());
			// 生产周期
			Row row6 = sheet.getRow(13);
			Cell cell7 = row6.getCell(9);
			cell7.setCellValue(reportIntro.getProductPeriod() == null ? "" : reportIntro.getProductPeriod());
			// 验收标准
			Row row7 = sheet.getRow(14);
			Cell cell8 = row7.getCell(9);
			cell8.setCellValue(reportIntro.getAcceptanceStandard());
			// 出货日期
			Row row8 = sheet.getRow(15);
			Cell cell9 = row8.getCell(9);
			cell9.setCellValue(reportIntro.getDispatchDate());
		}
	}
	
	/**
	 * 写入检验内容
	 * @param sheet
	 * @param reportIteMap
	 */
	void writeReportIteMap(Sheet sheet, Map<String, ReportItem> reportIteMap, boolean hasNewRow) {
		if (reportIteMap == null) {
			return;
		}
		List<ReportItem> reportItemList = new ArrayList<ReportItem>();
		reportItemList = this.createReportItemList(reportIteMap);
		for (int i = 0; i < reportItemList.size(); i++) {
			ReportItem reportItem = reportItemList.get(i);
			if (reportItem == null) {
				continue;
			}
			Row row;
			if (hasNewRow) {
				row = sheet.getRow(40 + i);
			} else {
				row = sheet.getRow(39 + i);
			}
			if (i == 19 || i == 20 || i == 21 || i == 22 || i == 23) {
				Cell cell = row.getCell(11);
				cell.setCellValue(reportItem.getCheckRequire());
				Cell cell6 = row.getCell(24);
				cell6.setCellValue(reportItem.getJudgeResult());
				continue;
			}
			if (i == 17 || i == 18) {
				Cell cell4 = row.getCell(13);
				cell4.setCellValue(reportItem.getCheckRequire());
				Cell cell4_1 = row.getCell(15);
				String boardTolerence = reportItem.getBoardTolerance();
				if (boardTolerence != null) {
					cell4_1.setCellValue(boardTolerence + "mm");
				}
				Cell cell5 = row.getCell(18);
				cell5.setCellValue(reportItem.getCheckResult());
				Cell cell6 = row.getCell(24);
				cell6.setCellValue(reportItem.getJudgeResult());
				continue;
			}
			if (i == 25) {
				Cell cell = row.getCell(11);
				cell.setCellValue(reportItem.getCheckRequire());
				Cell cell3 = row.getCell(24);
				cell3.setCellValue(reportItem.getJudgeResult());
				continue;
			}
			if (i == 15) {
				Cell cell = row.getCell(11);
				String require = reportItem.getCheckRequire();
				if (require != null) {
					cell.setCellValue(require + "mm");
				}
				Cell cell2 = row.getCell(18);
				cell2.setCellValue(reportItem.getCheckResult());
				Cell cell3 = row.getCell(24);
				cell3.setCellValue(reportItem.getJudgeResult());
				continue;
			}
			Cell cell = row.getCell(11);
			cell.setCellValue(reportItem.getCheckRequire());
			Cell cell2 = row.getCell(18);
			cell2.setCellValue(reportItem.getCheckResult());
			Cell cell3 = row.getCell(24);
			cell3.setCellValue(reportItem.getJudgeResult());
		}
	}
	
	/**
	 * 写入孔径检测数据返回最后一行的行数
	 * @param sheet
	 * @param reportApertureChecks
	 */
	Integer writeReportApertureChecks(XSSFSheet sheet, List<ReportApertureCheck> reportApertureChecks, boolean hasNewRow) {
		// 把P与N分开
		List<ReportApertureCheck> p = new ArrayList<>();
		List<ReportApertureCheck> n = new ArrayList<>();
		for (ReportApertureCheck reportApertureCheck : reportApertureChecks) {
			if (Objects.equals(reportApertureCheck.getpN(), "N")) {
				n.add(reportApertureCheck);
			}else if (Objects.equals(reportApertureCheck.getpN(), "P")){
				p.add(reportApertureCheck);
			}
		}
		for (ReportApertureCheck reportApertureCheck : reportApertureChecks) {
			if (Objects.equals(reportApertureCheck.getpN(), "Via")) {
				p.add(reportApertureCheck);
			}
		}
		// excel模板最后一行
		Integer lastRow = 14;
		// 在sheet尾生成一个新行
		sheet.createRow(lastRow);
		if (reportApertureChecks == null) {
			return lastRow;
		}
		Integer pSize = p.size();
		Integer nSize = n.size();
		Integer maxSize = PcbmisUtil.getMax(pSize, nSize);
		Integer num = 10;
		// 查看数据条数是否超过模板条数，如果超过的话就添加行
		if (maxSize > 10) {
			for (int i = 0; i < maxSize - 10; i++) {
				num++;
				// 把第13行的样式复制到刚才生成的最后一行
				POIUtil.copyRows(13, 13, lastRow, sheet);
				XSSFRow xssfRow = sheet.getRow(lastRow);
				// 写入新的行数
				XSSFCell xssfCell = xssfRow.getCell(0);
				xssfCell.setCellValue(num);
				XSSFCell xssfCell2 = xssfRow.getCell(13);
				xssfCell2.setCellValue(num);
				lastRow = lastRow + 1;
				// 在sheet尾生成一个新行
				sheet.createRow(lastRow);
			}
		}
		Integer rowStart = 4;
		for (ReportApertureCheck reportApertureCheck : p) {
			Row row;
			row = sheet.getRow(rowStart);
			Cell cell = row.getCell(2);
			cell.setCellValue(reportApertureCheck.getpN());
			Cell cell2 = row.getCell(4);
			cell2.setCellValue(PcbmisUtil.string2Num(reportApertureCheck.getRequire(), 3));
			Cell cell5 = row.getCell(6);
			cell5.setCellValue(reportApertureCheck.getRequireTolerance());
			Cell cell3 = row.getCell(8);
			cell3.setCellValue(PcbmisUtil.string2Num(reportApertureCheck.getRealCheck(), 3));
			Cell cell4 = row.getCell(11);
			cell4.setCellValue(reportApertureCheck.getJudge());
			rowStart++;
		}
		rowStart = 4;
		for (ReportApertureCheck reportApertureCheck : n) {
			Row row;
			row = sheet.getRow(rowStart);
			Cell cell = row.getCell(15);
			cell.setCellValue(reportApertureCheck.getpN());
			Cell cell2 = row.getCell(18);
			cell2.setCellValue(PcbmisUtil.string2Num(reportApertureCheck.getRequire(), 3));
			Cell cell5 = row.getCell(20);
			cell5.setCellValue(reportApertureCheck.getRequireTolerance());
			Cell cell3 = row.getCell(22);
			cell3.setCellValue(PcbmisUtil.string2Num(reportApertureCheck.getRealCheck(), 3));
			Cell cell4 = row.getCell(25);
			cell4.setCellValue(reportApertureCheck.getJudge());
			rowStart++;
		}
		// 在sheet尾生成一个新行
		sheet.createRow(lastRow);
		return lastRow;
	}
	
	// 写入特殊尺寸，返回sheet最后一行行坐标
	Integer writeSpecialDimension(XSSFSheet sheet, ReportSpecialDimension reportSpecialDimension){
		List<ReportSpecialDimensionItem> rests = reportSpecialDimension.getRests();
		if (rests == null) {
			rests  = new ArrayList<ReportSpecialDimensionItem>();
		}
		// 最后一行的下一行
		Integer lastRow = 15;
		// 在sheet最后新建一行
		sheet.createRow(lastRow);
		// 行标签
		Integer num = 8;
		Integer retSize = rests.size();
		Row newRow = null;
		// 如果其他特殊尺寸总数多余3，新建行
		if (retSize > 3) {
			for (int i = 0; i < retSize - 3; i++) {
				// 复制样式到最后一行
				POIUtil.copyRows(14, 14, lastRow, sheet);
				newRow = sheet.getRow(lastRow);
				Cell cell = newRow.getCell(0);
				cell.setCellValue(num);
				num++;
				lastRow++;
				sheet.createRow(lastRow);
			}
		}
		ReportSpecialDimensionItem vCutResidualThickness = reportSpecialDimension.getvCutResidualThickness();
		ReportSpecialDimensionItem vCutDeviation = reportSpecialDimension.getvCutDeviation();
		ReportSpecialDimensionItem goldfingerBevelDepth = reportSpecialDimension.getGoldfingerBevelDepth();
		ReportSpecialDimensionItem goldfingerResidualThickness = reportSpecialDimension.getGoldfingerResidualThickness();
		ReportSpecialDimensionItem stepWidth = reportSpecialDimension.getStepWidth();
		ReportSpecialDimensionItem stepResidualThickness = reportSpecialDimension.getStepResidualThickness();
		ReportSpecialDimensionItem steppedHoleAperture = reportSpecialDimension.getSteppedHoleAperture();
		ReportSpecialDimensionItem steppedHoleHoleDepth = reportSpecialDimension.getSteppedHoleHoleDepth();
		this.writeSpecialDimensionItem(sheet, vCutResidualThickness, 4, 0);
		this.writeSpecialDimensionItem(sheet, vCutDeviation, 5, 0);
		this.writeSpecialDimensionItem(sheet, goldfingerBevelDepth, 6, 0);
		this.writeSpecialDimensionItem(sheet, goldfingerResidualThickness, 7, 0);
		this.writeSpecialDimensionItem(sheet, stepWidth, 8, 0);
		this.writeSpecialDimensionItem(sheet, stepResidualThickness, 9, 0);
		this.writeSpecialDimensionItem(sheet, steppedHoleAperture, 10, 0);
		this.writeSpecialDimensionItem(sheet, steppedHoleHoleDepth, 11, 0);
		Integer currentRow = 11;
		for (ReportSpecialDimensionItem specialDimensionItem : rests) {
			currentRow++;
			this.writeSpecialDimensionItem(sheet, specialDimensionItem, currentRow, 1);
		}
		return lastRow;
	}
	
	// 写入特殊尺寸中的一行
	private void writeSpecialDimensionItem(XSSFSheet sheet, ReportSpecialDimensionItem item, Integer rowAt, Integer type){
		Row itemRow = null;
		Cell cell = null;
		if (item != null) {
			itemRow = sheet.getRow(rowAt);
			if (type == 1) {
				cell = itemRow.getCell(2);
				cell.setCellValue(item.getItemName());
			}
			cell = itemRow.getCell(10);
			cell.setCellValue(item.getRequire());
			cell = itemRow.getCell(15);
			cell.setCellValue(item.getUnit());
			cell = itemRow.getCell(19);
			cell.setCellValue(item.getRealCheck());
			cell = itemRow.getCell(24);
			cell.setCellValue(item.getJudge());
		}
	}
	
	void writeVcut(XSSFSheet sheet, ReportVcut reportVcut){
		if (reportVcut == null) {
			return;
		}
		Row row = sheet.getRow(4);
		Cell cell = row.getCell(9);
		cell.setCellValue(reportVcut.getRequireAngle());
		cell = row.getCell(15);
		cell.setCellValue(reportVcut.getRealCheakAngle());
		cell = row.getCell(23);
		cell.setCellValue(reportVcut.getJudgeAngle());
		
		row = sheet.getRow(5);
		cell = row.getCell(9);
		cell.setCellValue(reportVcut.getRequireH());
		cell = row.getCell(15);
		cell.setCellValue(reportVcut.getRealCheckH());
		cell = row.getCell(23);
		cell.setCellValue(reportVcut.getJudgeH());
		
		row = sheet.getRow(6);
		cell = row.getCell(9);
		cell.setCellValue(reportVcut.getRequireB());
		cell = row.getCell(12);
		cell.setCellValue(reportVcut.getRequireBtolerance());
		cell = row.getCell(15);
		cell.setCellValue(reportVcut.getRealCheckB());
		cell = row.getCell(23);
		cell.setCellValue(reportVcut.getJudgeB());
	}
	
	/**
	 * 写入电气完善性报告数据
	 * @param sheet
	 * @param reportIntegrity
	 */
	void writeReportIntegrity(Sheet sheet, ReportIntegrity reportIntegrity, boolean hasNewRow) {
		if (reportIntegrity == null) {
			return;
		}
		Row row = sheet.getRow(3);
		Cell cell = row.getCell(6);
		cell.setCellValue(reportIntegrity.getCheckInstrument());
			
		Row row2 = sheet.getRow(4);
		Cell cell2 = row2.getCell(6);
		cell2.setCellValue(reportIntegrity.getCheckVoltage());
		Cell cell3 = row2.getCell(20);
		cell3.setCellValue(reportIntegrity.getConnectedResistance());
			
		Row row3 = sheet.getRow(5);
		Cell cell4 = row3.getCell(6);
		cell4.setCellValue(reportIntegrity.getCheckPointNum());
		Cell cell5 = row3.getCell(20);
		cell5.setCellValue(reportIntegrity.getInsulatedResistance());
			
		Row row4 = sheet.getRow(6);
		Cell cell6 = row4.getCell(6);
		cell6.setCellValue(reportIntegrity.getNetNum());
		Cell cell7 = row4.getCell(20);
		cell7.setCellValue(reportIntegrity.getConnectedNetNum());
			
		Row row5 = sheet.getRow(7);
		Cell cell8 = row5.getCell(6);
		cell8.setCellValue(reportIntegrity.getResult());
	}
	
	/**
	 * 写入特性阻抗数据
	 * @param sheet
	 * @param singleEndedImpedancesList
	 * @param differentialCharacteristicImpedanceList
	 */
	Integer writeReportSpecialImpedance(Workbook workbook, XSSFSheet sheet, List<ReportSpecialImpedance> singleEndedImpedancesList,
			List<ReportSpecialImpedance> differentialCharacteristicImpedanceList) {
		XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中 
		cellStyle.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);//左边框中等粗 
		XSSFCellStyle cellStyle2 = (XSSFCellStyle) workbook.createCellStyle();
		cellStyle2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cellStyle2.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中 
		cellStyle2.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框细 
		Integer lastRow = 16;
		// 获取内容行高
		short rowHeight = sheet.getRow(4).getHeight();
		//sheet.createRow(lastRow);
		if (singleEndedImpedancesList == null) {
			singleEndedImpedancesList = new ArrayList<ReportSpecialImpedance>();
		}
		Integer currentRowSingle = 4;
		Integer currentRowDiff = 10;
		Integer singleSize = singleEndedImpedancesList.size();
		// 如果单端阻抗的数量>6，新建行
		if (singleSize > 6) {
			for (int i = 0; i < singleSize - 6; i++) {
				// 在单端阻抗后面新建一行
				POIUtil.createRow(sheet, currentRowSingle + 1);
				// 把内容格式复制到新建的行
				POIUtil.copyRows(6, 6, currentRowSingle + 1, sheet);
				lastRow++;
				currentRowDiff++;
			}
		}
		// 写入单端阻抗
		Row row = null;
		Cell cell = null;
		for (ReportSpecialImpedance reportSpecialImpedance : singleEndedImpedancesList) {
			row = sheet.getRow(currentRowSingle);
			cell = row.getCell(7);
			cell.setCellValue(reportSpecialImpedance.getLayer());
			cell = row.getCell(9);
			cell.setCellValue(reportSpecialImpedance.getLayerNum());
			cell = row.getCell(12);
			cell.setCellValue(reportSpecialImpedance.getRequire());
			cell = row.getCell(15);
			cell.setCellValue(reportSpecialImpedance.getRequireTolerance());
			cell = row.getCell(19);
			cell.setCellValue(reportSpecialImpedance.getRealCheck());
			cell = row.getCell(24);
			cell.setCellValue(reportSpecialImpedance.getJudge());
			currentRowSingle++;
		}
		if (differentialCharacteristicImpedanceList == null) {
			differentialCharacteristicImpedanceList = new ArrayList<ReportSpecialImpedance>();
		}
		Integer differential = differentialCharacteristicImpedanceList.size();
		// 如果差分阻抗的数量>6，新建行
		if (differential > 6) {
			for (int i = 0; i < differential - 6; i++) {
				POIUtil.createRow(sheet, currentRowDiff + 1);
				POIUtil.copyRows(lastRow - 3, lastRow - 3, currentRowDiff + 1, sheet);
				lastRow++;
			}
		}
		// 写入差分阻抗
		for (ReportSpecialImpedance reportSpecialImpedance : differentialCharacteristicImpedanceList) {
			row = sheet.getRow(currentRowDiff);
			cell = row.getCell(7);
			cell.setCellValue(reportSpecialImpedance.getLayer());
			cell = row.getCell(9);
			cell.setCellValue(reportSpecialImpedance.getLayerNum());
			cell = row.getCell(12);
			cell.setCellValue(reportSpecialImpedance.getRequire());
			cell = row.getCell(15);
			cell.setCellValue(reportSpecialImpedance.getRequireTolerance());
			cell = row.getCell(19);
			cell.setCellValue(reportSpecialImpedance.getRealCheck());
			cell = row.getCell(24);
			cell.setCellValue(reportSpecialImpedance.getJudge());
			currentRowDiff++;
		}
		// 同意设置新建行的行号
		for (int i = 4; i < lastRow; i++) {
			sheet.getRow(i).setHeight(rowHeight);
		}
		sheet.createRow(lastRow);
		// 如果单端阻抗不足6条，合并单元格按6条处理
		if (currentRowSingle < 10) {
			currentRowSingle = 10;
		}
		// 如果差分阻抗不足6条，合并单元格按6条处理
		if (currentRowDiff < 16) {
			currentRowDiff = 16;
		}
		// 合并单元格
		CellRangeAddress cra=new CellRangeAddress(4, currentRowSingle -1, 0, 1);
		CellRangeAddress cra1=new CellRangeAddress(4, currentRowSingle -1, 2, 6);
		CellRangeAddress cra2=new CellRangeAddress(currentRowSingle, currentRowDiff -1, 0, 1);
		CellRangeAddress cra3=new CellRangeAddress(currentRowSingle, currentRowDiff -1, 2, 6);
		sheet.addMergedRegion(cra);
		sheet.addMergedRegion(cra1);
		sheet.addMergedRegion(cra2);
		sheet.addMergedRegion(cra3);
		row = sheet.getRow(4);
		cell = row.getCell(0);
		cell.setCellValue(1);
		cell.setCellStyle(cellStyle);
		cell = row.getCell(2);
		cell.setCellValue("单端阻抗");
		cell.setCellStyle(cellStyle2);
		row = sheet.getRow(currentRowSingle);
		cell = row.getCell(0);
		cell.setCellValue(2);
		cell.setCellStyle(cellStyle);
		cell = row.getCell(2);
		cell.setCellValue("差分阻抗");
		cell.setCellStyle(cellStyle2);
		return lastRow;
	}
	
	/**
	 * 按顺序拼接“检验内容”部分数据
	 * @param reportIteMap
	 * @return
	 */
	List<ReportItem> createReportItemList(Map<String, ReportItem> reportIteMap){
		List<ReportItem> reportItemList = new ArrayList<ReportItem>();
		ReportItem reportItem = reportIteMap.get("base_material_types");
		reportItemList.add(reportItem);
		ReportItem reportItem2 = reportIteMap.get("base_material_appearance");
		reportItemList.add(reportItem2);
		ReportItem reportItem3 = reportIteMap.get("conductive_pattern");
		reportItemList.add(reportItem3);
		ReportItem reportItem4 = reportIteMap.get("prevent_smt_type");
		reportItemList.add(reportItem4);
		ReportItem reportItem5 = reportIteMap.get("prevent_smt_appearance");
		reportItemList.add(reportItem5);
		ReportItem reportItem6 = reportIteMap.get("character_type");
		reportItemList.add(reportItem6);
		ReportItem reportItem7 = reportIteMap.get("character_appearance");
		reportItemList.add(reportItem7);
		ReportItem reportItem8 = reportIteMap.get("surface_process");
		reportItemList.add(reportItem8);
		ReportItem reportItem9 = reportIteMap.get("regular_label");
		reportItemList.add(reportItem9);
		ReportItem reportItem10 = reportIteMap.get("produce_period");
		reportItemList.add(reportItem10);
		ReportItem reportItem11 = reportIteMap.get("external_coating_adhesion");
		reportItemList.add(reportItem11);
		ReportItem reportItem12 = reportIteMap.get("prevent_smt_character_adhesion");
		reportItemList.add(reportItem12);
		ReportItem reportItem13 = reportIteMap.get("min_line_width");
		reportItemList.add(reportItem13);
		ReportItem reportItem14 = reportIteMap.get("min_line_distance");
		reportItemList.add(reportItem14);
		ReportItem reportItem15 = reportIteMap.get("min_annular_ring");
		reportItemList.add(reportItem15);
		ReportItem reportItem16 = reportIteMap.get("board_ply");
		reportItemList.add(reportItem16);
		ReportItem reportItem17 = reportIteMap.get("warping_degree");
		reportItemList.add(reportItem17);
		ReportItem reportItem18 = reportIteMap.get("board_long");
		reportItemList.add(reportItem18);
		ReportItem reportItem19 = reportIteMap.get("board_wide");
		reportItemList.add(reportItem19);
		ReportItem reportItem20 = reportIteMap.get("aperture");
		reportItemList.add(reportItem20);
		ReportItem reportItem21 = reportIteMap.get("v_cut");
		reportItemList.add(reportItem21);
		ReportItem reportItem22 = reportIteMap.get("circuit_connectivity");
		reportItemList.add(reportItem22);
		ReportItem reportItem23 = reportIteMap.get("circuit_insulativity");
		reportItemList.add(reportItem23);
		ReportItem reportItem24 = reportIteMap.get("special_impedance");
		reportItemList.add(reportItem24);
		ReportItem reportItem25 = reportIteMap.get("solderability");
		reportItemList.add(reportItem25);
		ReportItem reportItem26 = reportIteMap.get("microsectioning");
		reportItemList.add(reportItem26);
		return reportItemList;
	}
	@Override
	public SXSSFWorkbook exportUnqualifiedList(UnqualifiedQueryBean unqualifiedQueryBean) {
		List<Map<String, Object>> pcbUnqualifieds = pcbUnqualifiedMapper
				.selectListByQuery(unqualifiedQueryBean, null, null);
		for (Map<String, Object> map : pcbUnqualifieds) {
			 if (map.get("disposal_method") != null) {
				StringBuffer descBuffer = new StringBuffer();
				// 把处置方式转换成列表
				String disposal_method = map.get("disposal_method").toString()
						.replace("[", "").replace("]", "");
				String[] split = disposal_method.split(",");
				for (String index : split) {
					// 循环处置方式，然后取出描述
					for (DisposalMethodEnum disposalMethodEnum : DisposalMethodEnum
							.values()) {
						if (disposalMethodEnum.getIndex() == Integer
								.parseInt(index.trim() + "")) {
							descBuffer.append(disposalMethodEnum.getDesc())
									.append(",");
						}
					}
				}
				if (descBuffer.length() > 0) {
					descBuffer.deleteCharAt(descBuffer.length() - 1);
				}
				map.put("disposal_method", descBuffer.toString());
			}
			// 产品等级转成字符串
			if (map.get("product_level") != null) {
				Integer productLevel = (Integer) map.get("product_level");
				Dictionary dictGrade = cacheService.getDictionary(
						DictionaryEnum.CATEGORY_GRADE, productLevel);
				map.put("product_level", dictGrade.getValueStr());
			}
			// 产品类型转成字符串
			if (map.get("product_type") != null) {
				Integer productType = Integer.valueOf(map.get("product_type")
						.toString());
				for (ProductTypeEnum productTypeEnum : ProductTypeEnum.values()) {
					if (productTypeEnum.getIndex() == productType) {
						map.put("product_type", productTypeEnum.getDesc());
						break;
					}
				}

			}
		}
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		Row row = sheet.createRow(0);
		CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 21);
		sheet.addMergedRegion(cra);
		// 生成标题样式
		CellStyle cellStyle = FileUtil.createCellStyle(workbook, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER,
				"宋体", (short)18, Font.BOLDWEIGHT_BOLD, HSSFColor.WHITE.index, CellStyle.BORDER_NONE, HSSFColor.BLACK.index);
		Cell cell = row.getCell(0);
		if (cell == null) {
			cell = row.createCell(0);
		}
		row.setHeight((short)800);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("不合格品统计表");
		// 生成标题行样式
		CellStyle cellStyleTitle = FileUtil.generateColumnTitleStyle(workbook);
		String[] titles = { "流水号", "日期", "电子/科技", "工单号", "客户", "板名", "产品类型", "产品等级",
				"生产厂家", "来料数量（块）", "不良板数量(块)", "检验员", "问题板不良现象", "批次号", "审理员",
				"处置方式", "跟踪进程", "二次收板日期", "二次检验结果", "处置方式", "是否收到供应商回复邮件",
				"是否闭环" };
		// 生成标题行
		FileUtil.setColumnTitle(sheet, titles, cellStyleTitle, 1);
		// 设置列宽度
		FileUtil.setColumnWidth(sheet, 3600);
		if (pcbUnqualifieds.isEmpty()) {
			return workbook;
		}
		for (int i = 0; i < pcbUnqualifieds.size(); i++) {
			row = sheet.createRow(i + 2);
			Map<String, Object> map = pcbUnqualifieds.get(i);
			// 流水号
			cell = row.createCell(0);
			cell.setCellValue(PcbmisUtil.toString(map.get("serial_number")));
			// 拟制日期
			cell = row.createCell(1);
			Date submitTime = (Date) map.get("submit_time");
			cell.setCellValue(DateTimeUtil.date2dateStr(submitTime));
			// 所属公司
			cell = row.createCell(2);
			cell.setCellValue(PcbmisUtil.toString(map.get("company_name")));
			// 工单号
			cell = row.createCell(3);
			cell.setCellValue(PcbmisUtil.toString(map.get("order_number")));
			// 客户
			cell = row.createCell(4);
			cell.setCellValue(PcbmisUtil.toString(map.get("guest_name")));
			// 板名
			cell = row.createCell(5);
			cell.setCellValue(PcbmisUtil.toString(map.get("board_name")));
			// 产品类型
			cell = row.createCell(6);
			cell.setCellValue(PcbmisUtil.toString(map.get("product_type")));
			// 产品等级
			cell = row.createCell(7);
			cell.setCellValue(PcbmisUtil.toString(map.get("product_level")));
			// 生产厂家
			cell = row.createCell(8);
			cell.setCellValue(PcbmisUtil.toString(map.get("factory_name")));
			// 来料数量
			cell = row.createCell(9);
			cell.setCellValue(PcbmisUtil.toString(map.get("storage_number")));
			// 不良板数量
			cell = row.createCell(10);
			cell.setCellValue(PcbmisUtil.toString(map.get("unqualified_number")));
			// 检验员
			cell = row.createCell(11);
			cell.setCellValue(PcbmisUtil.toString(map.get("username")));
			// 问题板不良现象
			cell = row.createCell(12);
			cell.setCellValue(PcbmisUtil.toString(map.get("unqualified_desc")));
			// 批次号
			cell = row.createCell(13);
			cell.setCellValue(PcbmisUtil.toString(map.get("unqualified_batch")));
			// 审理员
			cell = row.createCell(14);
			cell.setCellValue(PcbmisUtil.toString(map.get("trial_personnel")));
			// 处理方式
			cell = row.createCell(15);
			cell.setCellValue(PcbmisUtil.toString(map.get("disposal_method")));
		}
		return workbook;
	}
	
	@Override
	public SXSSFWorkbook exportCheckList(String orderNum, String guest,
			Integer gradeId, Integer content, String boardName,
			Integer factoryId, Integer statusId, String checkStartStr,
			String checkEndStr, String auditStartStr, String auditEndStr) {
		Date checkStart = DateTimeUtil.dateStr2date(checkStartStr);
		Date checkEnd = DateTimeUtil.dateStr2date(checkEndStr);
		checkEnd = DateTimeUtil.getTheDayLastTime(checkEnd);
		Date auditStart = DateTimeUtil.dateStr2date(auditStartStr);
		Date auditEnd = DateTimeUtil.dateStr2date(auditEndStr);
		auditEnd = DateTimeUtil.getTheDayLastTime(auditEnd);
		Map<String, Object> response = new HashMap<String, Object>();
		List<String> edaGuestCode = utilService.getEdaGuest(guest);
		String contentStr = null;
		if (content != null) {
			contentStr = content.toString();
		}
		List<PcbCheckOrder> pcbCheckOrders = pcbCheckOrderMapper
				.selectByKeyWordAndOrderNums(orderNum, contentStr, boardName,
						factoryId, statusId, gradeId, checkStart, checkEnd,
						auditStart, auditEnd, edaGuestCode, null, null);
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		Sheet sheet = workbook.createSheet("印制板检验生产统计表");
		// 设置列宽
		for (int i = 0; i < 32; i++) {
			sheet.setColumnWidth(i, 4000);
		}
		// 生成一个样式
		CellStyle style = FileUtil.generateColumnTitleStyle(workbook);

		String strs = "流水号,工单号,客户,板名,电子/科技,生产厂家,生产数量set,数量,等级,金相要求,投产日期,来料数量（块）,"
				+ "抽检数量（块）,收板日期,收板时间,检完日期,是否异常,异常数量（块),异常问题,出货日期,检验员,编辑报告人员,备注,"
				+ "错检、漏检,报告错误,生产日期,长,宽,厚,规格,表面处理,拼板方式,出货方式,来料类型,检验内容";
		String[] columnName = strs.split(",");
		// 产生表格标题行
		FileUtil.setColumnTitle(sheet, columnName, style, 0);
		if (PcbmisUtil.isEmpity(pcbCheckOrders)) {
			response.put("total", 0);
			response.put("rows", null);
			return workbook;
		}
		Set<String> orderNumSet = new HashSet<String>();
		Set<String> checkNums = new HashSet<String>();
		Set<String> reportNums = new HashSet<String>();
		for (PcbCheckOrder pcbCheckOrder : pcbCheckOrders) {
			orderNumSet.add(pcbCheckOrder.getProductOrderNum());
			checkNums.add(pcbCheckOrder.getCheckNum());
		}
		List<String> orderNums = new ArrayList<String>(orderNumSet);
		List<ProductOrder> productOrders = productOrderMapper.listByOrderNum(orderNums);
		List<PcbCheckReport> pcbCheckReports = pcbCheckReportMapper.listByCheckNum(checkNums);
		List<PcbUnqualified> pcbUnqualifieds = pcbUnqualifiedMapper.selectByCheckNums(checkNums);
		// 工单
		Map<String, ProductOrder> pcbOrders = new HashMap<String, ProductOrder>();
		for (ProductOrder productOrder : productOrders) {
			pcbOrders.put(productOrder.getOrderNum(), productOrder);
		}
		// 检验报告
		Map<String, PcbCheckReport> reportMap = new HashMap<String, PcbCheckReport>();
		for (PcbCheckReport pcbCheckReport : pcbCheckReports) {
			reportNums.add(pcbCheckReport.getReportNum());
			reportMap.put(pcbCheckReport.getCheckNum(), pcbCheckReport);
		}
		// 不合格品
		Map<String, PcbUnqualified> unqualifiedMap = new HashMap<String, PcbUnqualified>();
		for (PcbUnqualified pcbUnqualified : pcbUnqualifieds) {
			unqualifiedMap.put(pcbUnqualified.getCheckNum(), pcbUnqualified);
		}
		// 报告intro
		if (reportNums.isEmpty()) {
			reportNums = null;
		}
		List<PcbReportIntro> reportIntros = pcbReportIntroMapper.listByReportNums(reportNums);
		Map<String, Date> reportIntroMap = new HashMap<String, Date>();
		for (PcbReportIntro pcbReportIntro : reportIntros) {
			reportIntroMap.put(pcbReportIntro.getReportNum(), pcbReportIntro.getDispatchDate());
		}
		// 备注
		Map<String, String> noteCheckMap = new HashMap<String, String>();
		Map<String, String> noteReportMap = new HashMap<String, String>();
		for (PcbCheckOrder pcbCheckOrder : pcbCheckOrders) {
			String note = pcbCheckOrder.getNote();
			if (!StringUtil.isEmpty(note)) {
				note = note.replaceAll("<br>", "\n");
			}
			noteCheckMap.put(pcbCheckOrder.getCheckNum(), note);
		}
		for (PcbCheckReport pcbCheckReport : pcbCheckReports) {
			String note = pcbCheckReport.getNote();
			if (!StringUtil.isEmpty(note)) {
				note = note.replaceAll("<br>", "\n");
			}
			noteReportMap.put(pcbCheckReport.getCheckNum(), note);
		}
		// 收货类型
		List<PcbReceiveOrder> pcbReceiveOrders = pcbReceiveOrderMapper.listByCheckNums(checkNums);
		Map<String, String> receiveMap = new HashMap<>();
		for (PcbReceiveOrder pcbReceiveOrder : pcbReceiveOrders) {
			InstorageOrderTypeEnum instorageOrderTypeEnum = EnumUtil.getEnumByCode(InstorageOrderTypeEnum.class,
					"getCode", pcbReceiveOrder.getReceiveType().toString());
			receiveMap.put(pcbReceiveOrder.getCheckNum(), instorageOrderTypeEnum.getDescr());
		}
		//
		List<CheckInfo> checkInfoList = new ArrayList<CheckInfo>();
		for (PcbCheckOrder pcbCheckOrder : pcbCheckOrders) {
			String productOrderNum = pcbCheckOrder.getProductOrderNum();
			ProductOrder productOrder = pcbOrders.get(productOrderNum);
			CheckInfo checkInfo = new CheckInfo();
			checkInfo.init(productOrder, pcbCheckOrder);
			checkInfo.setProductNote(this.getProductNote(pcbCheckOrder.getCheckNum(), noteCheckMap, noteReportMap));
			String checkNum = pcbCheckOrder.getCheckNum();
			PcbCheckReport pcbCheckReport = reportMap.get(checkNum);
			String mistakeNoteReport = null; 
			String supplier_mistake_note = null;
			if (pcbCheckReport != null) {
				mistakeNoteReport = pcbCheckReport.getMistakeNote();
				supplier_mistake_note = pcbCheckReport.getSupplierMistakeNote();
				String reportMakerCode = pcbCheckReport.getReportMakerCode();
				String reportNum = pcbCheckReport.getReportNum();
				Date dispatchDate = reportIntroMap.get(reportNum);
				checkInfo.setDispatchDate(DateTimeUtil.date2dateStr(dispatchDate));
				if (reportMakerCode != null) {
					WxtbUser wxtbUser = cacheService.getWxtbUser(reportMakerCode);
					if (wxtbUser != null) {
						checkInfo.setReportMakerName(wxtbUser.getUsername());
					}
				}
			}
			PcbUnqualified pcbUnqualified = unqualifiedMap.get(pcbCheckOrder.getCheckNum());
			if (pcbUnqualified != null) {
				checkInfo.setErrorNum(pcbUnqualified.getUnqualifiedNumber());
				checkInfo.setErrorQuestion(pcbUnqualified.getUnqualifiedDesc());
			}
			checkInfo.setReceiveType(receiveMap.get(checkNum));
			// 错检漏检
			String mistakeNote = pcbCheckOrder.getMistakeNote();
			if (!StringUtil.isEmpty(mistakeNote)) {
				mistakeNote = mistakeNote.replaceAll("<br>", "\n");
			}
			checkInfo.setMistakeNote(mistakeNote);
			// 报告错误
			if (!StringUtil.isEmpty(mistakeNoteReport)) {
				mistakeNoteReport = mistakeNoteReport.replaceAll("<br>", "\n");
			} else {
				mistakeNoteReport = "";
			}
			if (!StringUtil.isEmpty(supplier_mistake_note)) {
				supplier_mistake_note = supplier_mistake_note.replaceAll("<br>", "\n");
			} else {
				supplier_mistake_note = "";
			}
			String reportError = "内部错误：\n" + mistakeNoteReport + "\n供应商报告错误：\n" + supplier_mistake_note;
			checkInfo.setReportError(reportError);
			checkInfoList.add(checkInfo);
		}
		checkInfoList = utilService.addAll(pcbCheckOrders, checkInfoList);
		Row row;
		CheckInfo checkInfo = null;
		for(int i = 0;i < checkInfoList.size();i++){
			row = sheet.createRow(i+1);
			checkInfo = checkInfoList.get(i);
			ExcelProcess.writeCheckInfo(checkInfo, row);
		}
		return workbook;
	}
	
	private String getProductNote(String orderNum, Map<String, String> check, Map<String, String> report){
		String checkStr = check.get(orderNum) == null ? "" : check.get(orderNum);
		String reportStr = report.get(orderNum) == null ? "" : report.get(orderNum);
		StringBuffer note = new StringBuffer();
		note.append("检验备注：\n").append(checkStr).append("\n报告备注：\n").append(reportStr);
		return note.toString();
	}
	
	@Override
	public void writeReportCertification(XSSFSheet sheet, ReportCertification reportCertification, String companyName) {
		if (reportCertification == null) {
			return;
		}
		Row row = sheet.getRow(0);
		// 公司名称
		if (StringUtils.equals("同步电子", companyName)) {
			Cell cell = row.getCell(0);
			cell.setCellValue("无锡市同步电子有限公司");
		} else {
			Cell cell = row.getCell(0);
			cell.setCellValue("无锡市同步电子科技有限公司");
		}
		// 印制板等级
		Row row2 = sheet.getRow(2);
		Cell cell = row2.getCell(0);
		if (reportCertification.getCategoryGrade() != null
				&& reportCertification.getCategoryGrade().contains("民")) {
			cell.setCellValue("民品印制板");
		} else {
			cell.setCellValue("军品印制板");
		}
		// 工 单 号
		Row row3 = sheet.getRow(4);
		Cell cell3 = row3.getCell(3);
		cell3.setCellValue(reportCertification.getOrderNum());
		if (reportCertification.getOrderNumName() == null) {
			Cell cell8 = row3.getCell(0);
			cell8.setCellValue("工单号");
		} else {
			Cell cell8 = row3.getCell(0);
			cell8.setCellValue(reportCertification.getOrderNumName());
		}
		// 产品名称
		Row row4 = sheet.getRow(5);
		Cell cell4 = row4.getCell(3);
		cell4.setCellValue(reportCertification.getBoardName());

		// 生产日期
		Row row5 = sheet.getRow(6);
		Cell cell5 = row5.getCell(3);
		cell5.setCellValue(reportCertification.getProductDate());

		// 检验日期
		Row row6 = sheet.getRow(7);
		Cell cell6 = row6.getCell(3);
		cell6.setCellValue(reportCertification.getCheckDate());

		// 包装日期
		Row row7 = sheet.getRow(8);
		Cell cell7 = row7.getCell(3);
		cell7.setCellValue(reportCertification.getPackagingDate());

		// 保 质 期
		Row row8 = sheet.getRow(10);
		Cell cell8 = row8.getCell(3);
		cell8.setCellValue(reportCertification.getExpirationDate());

		// 数 量
		Row row9 = sheet.getRow(11);
		Cell cell9 = row9.getCell(3);
		String spotCheckNumPcs = reportCertification.getNumberPcs();
		cell9.setCellValue(spotCheckNumPcs == null ? "" : spotCheckNumPcs);

		// 批 号
		Row row10 = sheet.getRow(12);
		Cell cell10 = row10.getCell(3);
		cell10.setCellValue(reportCertification.getBatchNumber());

		// 规 格
		Row row11 = sheet.getRow(13);
		Cell cell11 = row11.getCell(3);
		cell11.setCellValue(reportCertification.getSize());
		
		Integer lastRow = 14;
		List<CertificationExtra> add = reportCertification.getAdd();
		if (add == null || add.isEmpty()) {
			return;
		}
		Row row12 = null;
		Cell cell12 = null;
		for (CertificationExtra certificationExtra : add) {
			// 在sheet尾生成一个新行
			sheet.createRow(lastRow);
			// 复制上一行的格式
			POIUtil.copyRows(13, 13, lastRow, sheet);
			row12 = sheet.getRow(lastRow);
			cell12 = row12.getCell(0);
			if (cell12 == null) {
				cell12 = row.createCell(0);
			}
			cell12.setCellValue(certificationExtra.getKey());
			cell12 = row12.getCell(3);
			if (cell12 == null) {
				cell12 = row.createCell(3);
			}
			cell12.setCellValue(certificationExtra.getValue());
			lastRow = lastRow + 1;
		}
	}
}
