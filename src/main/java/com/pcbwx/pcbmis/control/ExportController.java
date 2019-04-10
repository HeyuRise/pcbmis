package com.pcbwx.pcbmis.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.authkit.callback.AuthService;
import com.pcbwx.pcbmis.bean.BaseOrderInfo;
import com.pcbwx.pcbmis.bean.BaseReportInfo;
import com.pcbwx.pcbmis.bean.CheckHead;
import com.pcbwx.pcbmis.bean.ReportCertification;
import com.pcbwx.pcbmis.bean.SizeAndWarpDegree;
import com.pcbwx.pcbmis.bean.UnqualifiedQueryBean;
import com.pcbwx.pcbmis.bean.response.CheckItem;
import com.pcbwx.pcbmis.component.CacheService;
import com.pcbwx.pcbmis.dao.PcbCheckOrderMapper;
import com.pcbwx.pcbmis.enums.ActionTypeEnum;
import com.pcbwx.pcbmis.model.PcbCheckDetail;
import com.pcbwx.pcbmis.model.PcbCheckOrder;
import com.pcbwx.pcbmis.model.WxtbUser;
import com.pcbwx.pcbmis.service.ExcelService;
import com.pcbwx.pcbmis.service.LogService;
import com.pcbwx.pcbmis.service.OrderService;
import com.pcbwx.pcbmis.service.ReportService;
import com.pcbwx.pcbmis.service.SupportService;
import com.pcbwx.pcbmis.utils.DateTimeUtil;
import com.pcbwx.pcbmis.utils.POIUtil;
import com.pcbwx.pcbmis.utils.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/downLoadFile")
@Api("导出api")
public class ExportController {
	
	private static Logger logger = Logger.getLogger(ExportController.class);
	
	@Autowired
	private AuthService authService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private SupportService supportService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private LogService logService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private ExcelService excelService;

	@Autowired
	private PcbCheckOrderMapper pcbCheckOrderMapper;
	
	@ApiOperation("导出印制板检验记录表")
	@RequestMapping( value = {"/downTestNum"}, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> exportTestNum(HttpServletRequest request, HttpServletResponse response){
		final AkAuthUser wxtbUser = authService.getLoginUser();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String checkNum = request.getParameter("checkNum");
		supportService.doSystemLog(request, wxtbUser.getUsername(), "导出印制板检验记录表", 5);
		logService.addAction("", ActionTypeEnum.DOWN_TEST_NUM.getCode(), wxtbUser.getUserCode(), "");
		Map<String, Object> orderDetails = orderService.goForOrderDetail(wxtbUser, checkNum, 3, null);
		if (orderDetails.get("companyCode") == null) {
			returnMap.put("result", false);
			returnMap.put("msg", "所属公司为空，导出失败！");
			return returnMap;
		}
		String serialNumber;
		if (orderDetails.get("serialNumber") == null) {
			serialNumber = "";
		} else {
			serialNumber = orderDetails.get("serialNumber").toString();
		}
		String revision;
		if (orderDetails.get("revision") == null) {
			revision = "";
		} else {
			revision = orderDetails.get("revision").toString();
		}
		String documentNumber;
		if (orderDetails.get("documentNumber") == null) {
			documentNumber = "";
		} else {
			documentNumber = orderDetails.get("documentNumber").toString();
		}
		BaseOrderInfo baseOrderInfo = (BaseOrderInfo)orderDetails.get("baseOrderInfo");
		CheckHead checkHead = (CheckHead) orderDetails.get("checkHead");
		if (baseOrderInfo != null) {
			supportService.doOperateLog("导出", baseOrderInfo.getOrderNum(), "印制板检验记录表", wxtbUser.getUsername());
		}
		//检验列表list
		List<CheckItem> checkItemList = this.createCheckItemList(orderDetails);
		
		String inFilePath = null;
		FileInputStream in = null;
		ServletOutputStream outputStream = null;
		try {
			inFilePath = URLDecoder.decode(PcbCheckDetail.class.getResource("/template/test_num.xlsx")
						.getPath(), "UTF-8");
			inFilePath = inFilePath.substring(1);
			in = new FileInputStream(File.separator + inFilePath);
			Workbook workbook = new XSSFWorkbook(in);
			XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
			// 生成表头数据
			this.generateHeaderDatas(sheet, checkHead, serialNumber, revision, documentNumber);
			// 生成表中间数据
			this.generateContentDatas(checkNum, workbook, checkItemList, sheet, baseOrderInfo);
			// 生成表尾数据
			this.generateEndDatas(workbook, sheet, orderDetails);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/vnd.ms-excel");
			String fileName = "印制板检验记录表-" + DateTimeUtil.date2dateTimeStr(new Date(), "yyyyMMddHHmmss") + ".xlsx";
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename="+fileName);
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
			outputStream.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			returnMap.put("result", false);
			returnMap.put("msg", e.getMessage());
			return returnMap;
		} finally {
			if (null != outputStream) {
				try {
					outputStream.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
					returnMap.put("result", false);
					returnMap.put("msg", e.getMessage());
					return returnMap;
				}
			}
		}
		returnMap.put("result", true);
		return returnMap;
	}

	@ApiOperation("导出印制板检验报告")
	@RequestMapping( value = {"/downWriteReport"}, method = RequestMethod.POST)
	@ResponseBody
	public void exportWriteReport(HttpServletRequest request, HttpServletResponse response){	
		final AkAuthUser wxtbUser = authService.getLoginUser();
		String reportNum = request.getParameter("reportNum");
		supportService.doSystemLog(request, wxtbUser.getUsername(), "印制板检验报告", 5);
		logService.addAction("", ActionTypeEnum.DOWN_WRITE_REPORT.getCode(), wxtbUser.getUserCode(), "");
		Workbook workbook = excelService.exportReport(reportNum, wxtbUser);
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/vnd.ms-excel");
			String fileName = "检验报告-"
					+ DateTimeUtil.date2dateTimeStr(new Date(), "yyyyMMddHHmmss")
					+ ".xlsx";
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

	}
	
	@ApiOperation("导出印制板检验生产统计表")
	@RequestMapping( value = {"/downCheckList"}, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> downCheckList(HttpServletRequest request,
			@RequestParam( value = "orderNum", required = false) String orderNum,
			@RequestParam( value = "guest", required = false) String guest,
			@RequestParam( value = "gradeId", required = false) Integer gradeId,
			@RequestParam( value = "content", required = false) Integer content,
			@RequestParam( value = "boardName", required = false) String boardName,
			@RequestParam( value = "factoryId", required = false) Integer factoryId,
			@RequestParam( value = "statusId", required = false) Integer statusId,
			@RequestParam( value = "checkStart", required = false) String checkStart,
			@RequestParam( value = "checkEnd", required = false) String checkEnd,
			@RequestParam( value = "auditStart", required = false) String auditStart,
			@RequestParam( value = "auditEnd", required = false) String auditEnd,
			HttpServletResponse response){
		final AkAuthUser wxtbUser = authService.getLoginUser();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (StringUtil.isEmpty(orderNum)) {
			orderNum = null;
		}
		if (StringUtil.isEmpty(guest)) {
			guest = null;
		}
		if (StringUtil.isEmpty(boardName)) {
			boardName = null;
		}
		SXSSFWorkbook workbook = excelService.exportCheckList(orderNum, guest, gradeId, content, boardName, factoryId, statusId, checkStart, checkEnd, auditStart, auditEnd);
		supportService.doOperateLog("导出", "", "印制板检验生产统计表", wxtbUser.getUsername());
		supportService.doSystemLog(request, wxtbUser.getUsername(), "印制板检验生产统计表", 5);
		ServletOutputStream outputStream = null;
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/vnd.ms-excel");
			String fileName = "印制板检验生产统计表-" + DateTimeUtil.date2dateTimeStr(new Date(), "yyyyMMddHHmmss") + ".xlsx";
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename="+fileName);
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
			outputStream.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			returnMap.put("result", false);
			returnMap.put("msg", e.getMessage());
			return returnMap;
		} finally {
			if (null != outputStream) {
				try {
					outputStream.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
					returnMap.put("result", false);
					returnMap.put("msg", e.getMessage());
					return returnMap;
				}
			}
		}
		returnMap.put("result", true);
		return returnMap;
	}
	
	@ApiOperation("导出合格证")
	@RequestMapping( value = {"/downReportCertification"}, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> downReportCertification(HttpServletRequest request, HttpServletResponse response){
		final AkAuthUser wxtbUser = authService.getLoginUser();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String reportNum = request.getParameter("reportNum");	
		supportService.doSystemLog(request, wxtbUser.getUsername(), "导出产品合格证表", 5);
		logService.addAction("", ActionTypeEnum.DOWN_TEST_NUM.getCode(), wxtbUser.getUserCode(), "");
		Map<String, Object> map = reportService.goForReportDetail(null, reportNum, 2, null);
		// 所属公司
		String companyName = "";
		if (map.get("companyName") != null) {
			companyName = map.get("companyName").toString();
		}
		ReportCertification reportCertification = (ReportCertification)map.get("certification");
		BaseReportInfo baseReportInfo = (BaseReportInfo) map.get("baseReportInfo");
		if (baseReportInfo != null) {
			supportService.doOperateLog("导出", baseReportInfo.getOrderNum(), "产品合格证", wxtbUser.getUsername());
		}
		String inFilePath = null;
		FileInputStream in = null;
		ServletOutputStream outputStream = null;
		try {
			inFilePath = URLDecoder.decode(PcbCheckDetail.class.getResource("/template/certification.xlsx")
					.getPath(), "UTF-8");
			inFilePath = inFilePath.substring(1);
			in = new FileInputStream(File.separator + inFilePath);
			@SuppressWarnings("resource")
			Workbook workbook = new XSSFWorkbook(in);
			XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
			// 导入产品合格证数据
			excelService.writeReportCertification(sheet, reportCertification, companyName);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/vnd.ms-excel");
			String fileName = "产品合格证表-" + DateTimeUtil.date2dateTimeStr(new Date(), "yyyyMMddHHmmss") + ".xlsx";
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename="+fileName);
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
			outputStream.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			returnMap.put("result", false);
			returnMap.put("msg", e.getMessage());
			return returnMap;
		} finally {
			if (null != outputStream) {
				try {
					outputStream.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
					returnMap.put("result", false);
					returnMap.put("msg", e.getMessage());
					return returnMap;
				}
			}
		}
		returnMap.put("result", true);
		return returnMap;
	}
	
	@ApiOperation("导出不合格品统计表")
    @RequestMapping(value="/excelList", method = RequestMethod.GET)
    @ResponseBody
    public void exportList(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> unqualifiedQueryMap){
    	final AkAuthUser wxtbUser = authService.getLoginUser();	
    	UnqualifiedQueryBean unqualifiedQueryBean = new UnqualifiedQueryBean();
        unqualifiedQueryBean.setBelongCompanyId(StringUtil.isEmpty(unqualifiedQueryMap.get("belongCompanyId")) ? null : Integer.valueOf(unqualifiedQueryMap.get("belongCompanyId")));
        unqualifiedQueryBean.setBoardName(unqualifiedQueryMap.get("boardName") == null ? null : unqualifiedQueryMap.get("boardName"));
        unqualifiedQueryBean.setCustomer(unqualifiedQueryMap.get("customer") == null ? null : unqualifiedQueryMap.get("customer"));
        unqualifiedQueryBean.setDisposal(unqualifiedQueryMap.get("disposal") == null ? null : unqualifiedQueryMap.get("disposal"));
        unqualifiedQueryBean.setFactoryId(StringUtil.isEmpty(unqualifiedQueryMap.get("factoryId")) ? null : Integer.valueOf(unqualifiedQueryMap.get("factoryId")));
        unqualifiedQueryBean.setInspector(unqualifiedQueryMap.get("inspector") == null ? null : unqualifiedQueryMap.get("inspector"));
        unqualifiedQueryBean.setOrderNumber(unqualifiedQueryMap.get("orderNumber") == null ? null : unqualifiedQueryMap.get("orderNumber"));
        unqualifiedQueryBean.setProductLevel(StringUtil.isEmpty(unqualifiedQueryMap.get("productLevel")) ? null : Integer.valueOf(unqualifiedQueryMap.get("productLevel")));
        unqualifiedQueryBean.setProductType(StringUtil.isEmpty(unqualifiedQueryMap.get("productType")) ? null : Integer.valueOf(unqualifiedQueryMap.get("productType")));
		unqualifiedQueryBean.setSubmitEndTime(StringUtil.isEmpty(unqualifiedQueryMap.get("submitEndTime")) ? null : unqualifiedQueryMap.get("submitEndTime") + " 23:59:59");
		unqualifiedQueryBean.setSubmitStartTime(StringUtil.isEmpty(unqualifiedQueryMap.get("submitStartTime")) ? null : unqualifiedQueryMap.get("submitStartTime") + " 00:00:00");
    	SXSSFWorkbook workbook = excelService.exportUnqualifiedList(unqualifiedQueryBean);
    	logService.addAction("", ActionTypeEnum.DOWN_UNQUALIFIED_LIST.getCode(), wxtbUser.getUserCode(), null);
		supportService.doSystemLog(request, wxtbUser.getUsername(), ActionTypeEnum.DOWN_UNQUALIFIED_LIST.getDesc(), 5);
		supportService.doOperateLog("导出", "", "不合格品统计表", wxtbUser.getUsername());
    	try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/vnd.ms-excel");
			String fileName = "不合格品统计表-"
					+ DateTimeUtil.date2dateTimeStr(new Date(), "yyyyMMddHHmmss")
					+ ".xlsx";
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
    }
	
	/**
	 * 按顺序拼接“印制板检验记录表”中的中间内容部分数据
	 * @param orderDetails
	 * @return
	 */
	List<CheckItem> createCheckItemList(Map<String, Object> orderDetails) {
		List<CheckItem> checkItemList = new ArrayList<CheckItem>();
		// 生产厂家 行
		CheckItem checkItem = (CheckItem)orderDetails.get("factory");
		checkItemList.add(checkItem);
		// 毛刺
		CheckItem checkItem2 = (CheckItem)orderDetails.get("burrs");
		checkItemList.add(checkItem2);
		// 缺口
		CheckItem checkItem3 = (CheckItem)orderDetails.get("gap");
		checkItemList.add(checkItem3);
		// 露铜
		CheckItem checkItem4 = (CheckItem)orderDetails.get("exposedCopper");
		checkItemList.add(checkItem4);
		// 其他缺陷
		CheckItem checkItem5 = (CheckItem)orderDetails.get("printed_board_else");
		checkItemList.add(checkItem5);
		// 露织物、显布纹
		CheckItem checkItem6 = (CheckItem)orderDetails.get("fabricTexture");
		checkItemList.add(checkItem6);
		// 麻点和空洞
		CheckItem checkItem7 = (CheckItem)orderDetails.get("pitVoid");
		checkItemList.add(checkItem7);
		// 白斑、微裂纹
		CheckItem checkItem8 = (CheckItem)orderDetails.get("spotCrack");
		checkItemList.add(checkItem8);
		// 分层、起泡
		CheckItem checkItem9 = (CheckItem)orderDetails.get("delaminationFoaming");
		checkItemList.add(checkItem9);
		// 外来夹杂物
		CheckItem checkItem10 = (CheckItem)orderDetails.get("foreignImpurity");
		checkItemList.add(checkItem10);
		// 其他缺陷
		CheckItem checkItem11 = (CheckItem)orderDetails.get("base_material_else");
		checkItemList.add(checkItem11);
		// 印刷面
		CheckItem checkItem12 = (CheckItem)orderDetails.get("board_prevent_smt");
		checkItemList.add(checkItem12);
		// 颜色
		CheckItem checkItem13 = (CheckItem)orderDetails.get("prevent_smt_color");
		checkItemList.add(checkItem13);
		/*// 露织物、显布纹
		CheckItem checkItem14 = (CheckItem)orderDetails.get("fabricTexture");
		checkItemList.add(checkItem14);*/
		// 覆盖度、附着力
		CheckItem checkItem15 = (CheckItem)orderDetails.get("coverageAdhesion");
		checkItemList.add(checkItem15);
		// 重合度
		CheckItem checkItem16 = (CheckItem)orderDetails.get("coincidenceDegree");
		checkItemList.add(checkItem16);
		// 起泡、分层
		CheckItem checkItem17 = (CheckItem)orderDetails.get("foamingLayering");
		checkItemList.add(checkItem17);
		// 波纹、皱褶
		CheckItem checkItem18 = (CheckItem)orderDetails.get("corrugation");
		checkItemList.add(checkItem18);
		// 假性露铜
		CheckItem checkItem19 = (CheckItem)orderDetails.get("falseExposedCopper");
		checkItemList.add(checkItem19);
		// 掉桥、掉坝
		CheckItem checkItem20 = (CheckItem)orderDetails.get("falseBridgeDam");
		checkItemList.add(checkItem20);
		// 色差
		CheckItem checkItem21 = (CheckItem)orderDetails.get("chromaticAberration");
		checkItemList.add(checkItem21);
		// 其他缺陷
		CheckItem checkItem22 = (CheckItem)orderDetails.get("soldermask_else");
		checkItemList.add(checkItem22);
		// 印刷面
		CheckItem checkItem23 = (CheckItem)orderDetails.get("board_character");
		checkItemList.add(checkItem23);
		// 颜色
		CheckItem checkItem24 = (CheckItem)orderDetails.get("character_color");
		checkItemList.add(checkItem24);
		// 辨识度、附着力
		CheckItem checkItem25 = (CheckItem)orderDetails.get("identificationAdhesion");
		checkItemList.add(checkItem25);
		// Logo
		CheckItem checkItem26 = (CheckItem)orderDetails.get("logo");
		checkItemList.add(checkItem26);
		// 批次号
		CheckItem checkItem27 = (CheckItem)orderDetails.get("batch_number");
		checkItemList.add(checkItem27);
		// 特殊板号
		CheckItem checkItem28 = (CheckItem)orderDetails.get("special_board_num");
		checkItemList.add(checkItem28);
		// 其他缺陷
		CheckItem checkItem29 = (CheckItem)orderDetails.get("mark_else");
		checkItemList.add(checkItem29);
		// 结瘤、毛刺
		CheckItem checkItem30 = (CheckItem)orderDetails.get("nodulesBurrs");
		checkItemList.add(checkItem30);
		// 孔内铅锡发暗
		CheckItem checkItem31 = (CheckItem)orderDetails.get("darkOfHoleTinLead");
		checkItemList.add(checkItem31);
		// 焊盘起翘
		CheckItem checkItem32 = (CheckItem)orderDetails.get("padCocked");
		checkItemList.add(checkItem32);
		// 晕圈
		CheckItem checkItem33 = (CheckItem)orderDetails.get("haloRing");
		checkItemList.add(checkItem33);
		// 外层环宽
		CheckItem checkItem34 = (CheckItem)orderDetails.get("outerRingWidth");
		checkItemList.add(checkItem34);
		// 锡珠入孔
		CheckItem checkItem35 = (CheckItem)orderDetails.get("solderInHole");
		checkItemList.add(checkItem35);
		// 堵孔
		CheckItem checkItem36 = (CheckItem)orderDetails.get("clogHole");
		checkItemList.add(checkItem36);
		// 线宽、间距等
		CheckItem checkItem37 = (CheckItem)orderDetails.get("lineWidthSpacing");
		checkItemList.add(checkItem37);
		// 其他缺陷
		CheckItem checkItem38 = (CheckItem)orderDetails.get("conductive_pattern_else");
		checkItemList.add(checkItem38);
		// V1.2添加过孔处理
		CheckItem checkItem391 = (CheckItem)orderDetails.get("viaHoleProcess");
		checkItemList.add(checkItem391);
		// 表面处理
		CheckItem checkItem39 = (CheckItem)orderDetails.get("surface_process");
		checkItemList.add(checkItem39);
		// 板长（mm）
		CheckItem checkItem40 = (CheckItem)orderDetails.get("boardLong");
		checkItemList.add(checkItem40);
		// 板宽（mm）
		CheckItem checkItem41 = (CheckItem)orderDetails.get("boardWide");
		checkItemList.add(checkItem41);
		// 板厚（mm）
		CheckItem checkItem42 = (CheckItem)orderDetails.get("boardPly");
		checkItemList.add(checkItem42);
		// 弓曲高度
		CheckItem checkItem43 = (CheckItem)orderDetails.get("Lay_height");
		checkItemList.add(checkItem43);
		// 扭曲高度
		CheckItem checkItem44 = (CheckItem)orderDetails.get("warp_height");
		checkItemList.add(checkItem44);
		return checkItemList;
	}
	
	/**
	 * 写入“印制板检验记录表”表头数据
	 * @param sheet
	 * @param baseOrderInfo
	 * @param checkNum
	 */
	void generateHeaderDatas(Sheet sheet, CheckHead checkHead, String serialNum, String banci, String bianhao) {
		if (checkHead == null) {
			return;
		}
		Row row = sheet.getRow(1);
		// No：
		Cell cell = row.getCell(3);
		cell.setCellValue(serialNum);
		// 版次
		Cell cell8 = row.getCell(36);
		cell8.setCellValue(banci);
		// 编号
		Cell cell9 = row.getCell(44);
		cell9.setCellValue(bianhao);
		
		Row row2 = sheet.getRow(2);
		// 工单号
		Cell cell2 = row2.getCell(8);
		cell2.setCellValue(checkHead.getProductOrderNum());
		// 验收标准
		Cell cell3 = row2.getCell(32);
		cell3.setCellValue(checkHead.getCheckStandard());
		
		Row row3 = sheet.getRow(3);
		// 来料数量（块）
		Cell cell4 = row3.getCell(8);
		cell4.setCellValue(checkHead.getInAmountSet() == null ? "" : checkHead.getInAmountSet().toString());
		// 抽检数量（块）
		Cell cell5 = row3.getCell(32);
		cell5.setCellValue(checkHead.getSpotCheckNum() == null ? "" : checkHead.getSpotCheckNum().toString());
		
		Row row4 = sheet.getRow(4);
		// 顾客单位
		Cell cell6 = row4.getCell(8);
		cell6.setCellValue(checkHead.getGuestName());
		// 板名
		Cell cell7 = row4.getCell(32);
		cell7.setCellValue(checkHead.getBoardName());
	}
	
	/**
	 * 写入“印制板检验记录表”中间内容部分
	 * @param checkItemList
	 * @param sheet
	 * @param baseOrderInfo
	 */
	void generateContentDatas(String checkNum, Workbook workbook, List<CheckItem> checkItemList, Sheet sheet, BaseOrderInfo baseOrderInfo) {
		int rowNum = 6;
		for (CheckItem checkItem : checkItemList) {
			if (checkItem == null) {
				rowNum++;
				continue;
			}
			Row row5 = sheet.getRow(rowNum);
			Cell cell8 = row5.getCell(13);
			String require = checkItem.getRequire();
			if (checkItem.getRequire() != null && checkItem.getRequire().contains("<br>")) {
				require = require.replace("<br>", "");
			}
			cell8.setCellValue(require);
			if (checkItem.getBoardTolerance() != null) {
				Cell cell12 = row5.getCell(20);
				cell12.setCellValue(checkItem.getBoardTolerance());
			}
			Cell cell9 = row5.getCell(28);
			cell9.setCellValue(checkItem.getResult());
			Cell cell10 = row5.getCell(44);
			cell10.setCellValue(checkItem.getBadNum());
			Cell cell11 = row5.getCell(48);
			cell11.setCellValue(checkItem.getJudgeResult());
			rowNum++;
		}
		if (baseOrderInfo == null) {
			return;
		}
		PcbCheckOrder pcbCheckOrder = pcbCheckOrderMapper.selectByCheckNum(checkNum);
		if (pcbCheckOrder != null) {
			String inspector = "        \t";
			String makeDateStr = "        \t";
			String auditor = "        \t";
			String auditDateStr = "        \t";
			String makerCode = pcbCheckOrder.getInspector();
			Date makeDate = pcbCheckOrder.getCheckDate();
			if (makerCode != null) {
				WxtbUser wxtbUser = cacheService.getWxtbUser(makerCode);
				if (wxtbUser != null) {
					inspector = wxtbUser.getUsername();
				}
			}
			if (makeDate != null) {
				makeDateStr = DateTimeUtil.date2dateStr(makeDate);
			}
			String auditCode = pcbCheckOrder.getAuditor();
			Date auditDate = pcbCheckOrder.getAuditDate();
			if (auditCode != null) {
				WxtbUser wxtbUser = cacheService.getWxtbUser(auditCode);
				if (wxtbUser != null) {
					auditor = wxtbUser.getUsername();
				}
			}
			if (auditDate != null) {
				auditDateStr = DateTimeUtil.date2dateStr(auditDate);
			}
			String check = null;
			if (!StringUtil.isEmpty(inspector.trim()) && !StringUtil.isEmpty(makeDateStr.trim())) {
				check = inspector + "/" + makeDateStr;
			}else {
				check = inspector + makeDateStr;
			}
			String audit = null;
			if (!StringUtil.isEmpty(auditor.trim()) && !StringUtil.isEmpty(auditDateStr.trim())) {
				audit = auditor + "/" + auditDateStr;
			}else {
				audit = auditor + auditDateStr;
			}
			String content = "检验员/日期：" + check + "            审核员/日期：" + audit;
			Row rowFoot = sheet.getRow(51);
			CellStyle cellStyle = rowFoot.getCell(0).getCellStyle();
			Cell cellFoot = rowFoot.getCell(0);
			Font font = workbook.createFont();
			font.setFontName("华文中宋");
			font.setFontHeightInPoints((short)10);
			font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
			font.setColor(HSSFColor.BLACK.index);
			font.setUnderline(Font.U_SINGLE); // 下划线
			Font font2 = workbook.createFont();
			font2.setFontName("华文中宋");
			font2.setFontHeightInPoints((short)10);
			font2.setBoldweight(Font.BOLDWEIGHT_NORMAL);
			font2.setColor(HSSFColor.BLACK.index);
			cellFoot.setCellValue(UnderLineIndex(new StringBuffer(content), 7 , check.length() + 7, content.lastIndexOf("：") + 1, content.lastIndexOf("：") + audit.length() + 1 , font, font2));
			cellStyle.setFont(font2);
			cellFoot.setCellStyle(cellStyle);
		}
		
		Row row5 = sheet.getRow(54);
		Cell cell8 = row5.getCell(0);
		// 生产备注
		String note = baseOrderInfo.getProductionNote();
		if (note != null) {
			note = note.replaceAll("<br>", "\r\n");
		}
		cell8.setCellValue(note);
		
		Row row6 = sheet.getRow(56);
		Cell cell9 = row6.getCell(0);
		// 商务备注
		cell9.setCellValue(baseOrderInfo.getBusinessNote());
		
		Row row7 = sheet.getRow(58);
		Cell cell10 = row7.getCell(0);
		// 发货备注
		cell10.setCellValue(baseOrderInfo.getDeliveryNote());
		
		Row row8 = sheet.getRow(60);
		Cell cell11 = row8.getCell(0);
		// CAM指示
		cell11.setCellValue(baseOrderInfo.getCAMGuide());
		
	}
	
	/**
	 * 写入“印制板检验记录表”表尾数据
	 * @param workbook
	 * @param sheet
	 * @param orderDetails
	 */
	void generateEndDatas(Workbook workbook, XSSFSheet sheet, Map<String, Object> orderDetails) {
		// 印制板尺寸检验记录表
		// 板长（mm）
		CheckItem checkItem40 = (CheckItem)orderDetails.get("boardLong");
		// 板宽（mm）
		CheckItem checkItem41 = (CheckItem)orderDetails.get("boardWide");
		// 板厚（mm）
		CheckItem checkItem42 = (CheckItem)orderDetails.get("boardPly");
		// 弓区高度
		CheckItem checkItem43 = (CheckItem)orderDetails.get("Lay_height");
		// 扭曲高度
		CheckItem checkItem44 = (CheckItem)orderDetails.get("warp_height");
		
		String boardLongRequire = "";
		String boardLongTolerance = "";
		if (checkItem40 != null) {
			// 板长（mm）要求值
			boardLongRequire = checkItem40.getRequire();
			// 板长（mm）公差
			boardLongTolerance = checkItem40.getBoardTolerance();
		}
		String boardWideRequire = "";
		String boardWideTolerance = "";
		if (checkItem41 != null) {
			// 板宽（mm）要求值
			boardWideRequire = checkItem41.getRequire();
			// 板宽（mm）公差
			boardWideTolerance = checkItem41.getBoardTolerance();
		}
		String boardPlyRequire = "";
		String boardPlyTolerance = "";
		if (checkItem42 != null) {
			// 板厚（mm）要求值
			boardPlyRequire = checkItem42.getRequire();
			// 板厚（mm）公差
			boardPlyTolerance = checkItem42.getBoardTolerance();
		}
		String layHeight = "";
		String warpHeight = "";
		if (checkItem43 != null) {
			layHeight = checkItem43.getRequire();
		}
		if (checkItem44 != null) {
			warpHeight = checkItem44.getRequire();
		}
		// 右边板号各值
		@SuppressWarnings("unchecked")
		List<SizeAndWarpDegree> sizeAndWarpDegree = (List<SizeAndWarpDegree>)orderDetails.get("sizeAndWarpDegrees");
		int columnNum = 16;
		Row row = sheet.getRow(65);
		Cell cell = row.getCell(6);
		cell.setCellValue(boardLongRequire);
		Cell cell2 = row.getCell(11);
		cell2.setCellValue(boardLongTolerance);
		
		Row row2 = sheet.getRow(66);
		Cell cell3 = row2.getCell(6);
		cell3.setCellValue(boardWideRequire);
		Cell cell4 = row2.getCell(11);
		cell4.setCellValue(boardWideTolerance);
		
		Row row3 = sheet.getRow(67);
		Cell cell5 = row3.getCell(6);
		cell5.setCellValue(boardPlyRequire);
		Cell cell6 = row3.getCell(11);
		cell6.setCellValue(boardPlyTolerance);
		
		Row row4 = sheet.getRow(68);
		Cell cell7 = row4.getCell(6);
		cell7.setCellValue(layHeight);
		
		Row row5 = sheet.getRow(69);
		Cell cell8 = row5.getCell(6);
		cell8.setCellValue(warpHeight);
		
		if (sizeAndWarpDegree != null) {
			if (sizeAndWarpDegree.size() < 10) {
				for (SizeAndWarpDegree sizeAndWarpDegreeTemp : sizeAndWarpDegree) {
					Row row8 = sheet.getRow(64);
					Cell cell11 = row8.getCell(columnNum);
					cell11.setCellValue(sizeAndWarpDegreeTemp.getBoardNum());
					
					Row row9 = sheet.getRow(65);
					Cell cell12 = row9.getCell(columnNum);
					cell12.setCellValue(sizeAndWarpDegreeTemp.getBoardLong());
					
					Row row10 = sheet.getRow(66);
					Cell cell13 = row10.getCell(columnNum);
					cell13.setCellValue(sizeAndWarpDegreeTemp.getBoardWide());
					
					Row row11 = sheet.getRow(67);
					Cell cell14 = row11.getCell(columnNum);
					cell14.setCellValue(sizeAndWarpDegreeTemp.getBoardPly());
					
					Row row12 = sheet.getRow(68);
					Cell cell15 = row12.getCell(columnNum);
					cell15.setCellValue(sizeAndWarpDegreeTemp.getLayHeight());
					
					Row row13 = sheet.getRow(69);
					Cell cell16 = row13.getCell(columnNum);
					cell16.setCellValue(sizeAndWarpDegreeTemp.getWarpHeight());
					
					columnNum += 4;
				}
			} else {
				int size = (sizeAndWarpDegree.size() -1) /9;
				for (int i = 0; i < size; i++) {
					Integer newRow = sheet.getLastRowNum() + 1;
					sheet.createRow(newRow);
					POIUtil.copyRows(63, 69, newRow, sheet);
				}
				int rowNum3 = 64;
				int columnNum2 = 16;
				for (int i = 0; i < sizeAndWarpDegree.size(); i++) {
					SizeAndWarpDegree sizeAndWarpDegreeTemp = sizeAndWarpDegree.get(i);
					if (i != 0 && i%9 == 0) {
						rowNum3 += 7;
						columnNum2 = 16;
					}
					if (sizeAndWarpDegreeTemp == null) {
						continue;
					}
					Row row8 = sheet.getRow(rowNum3);
					Cell cell11 = row8.getCell(columnNum2);
					cell11.setCellValue(sizeAndWarpDegreeTemp.getBoardNum());
					
					Row row9 = sheet.getRow(rowNum3+1);
					Cell cell12 = row9.getCell(columnNum2);
					cell12.setCellValue(sizeAndWarpDegreeTemp.getBoardLong());
					
					Row row10 = sheet.getRow(rowNum3+2);
					Cell cell13 = row10.getCell(columnNum2);
					cell13.setCellValue(sizeAndWarpDegreeTemp.getBoardWide());
					
					Row row11 = sheet.getRow(rowNum3+3);
					Cell cell14 = row11.getCell(columnNum2);
					cell14.setCellValue(sizeAndWarpDegreeTemp.getBoardPly());
					
					Row row12 = sheet.getRow(rowNum3+4);
					Cell cell15 = row12.getCell(columnNum2);
					cell15.setCellValue(sizeAndWarpDegreeTemp.getLayHeight());
					
					Row row13 = sheet.getRow(rowNum3+5);
					Cell cell16 = row13.getCell(columnNum2);
					cell16.setCellValue(sizeAndWarpDegreeTemp.getWarpHeight());
					
					columnNum2 += 4;
				}
			}
		}

	}
	
	// 加入下划线
	public static XSSFRichTextString UnderLineIndex(StringBuffer detail, Integer poi1, Integer poi2, Integer poi3, Integer poi4, Font font, Font font2) {
		XSSFRichTextString richString = null;
		richString = new XSSFRichTextString(detail.toString());
		richString.applyFont(poi1, poi2, font); // 下划线的起始位置，结束位置
		richString.applyFont(poi2 + 1, poi3 - 1, font2);
		richString.applyFont(poi3, poi4, font);
		return richString;
	}

}
