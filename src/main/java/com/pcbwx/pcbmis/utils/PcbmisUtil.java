package com.pcbwx.pcbmis.utils;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.pcbwx.pcbmis.bean.CheckDetail;
import com.pcbwx.pcbmis.bean.CheckItemRequest;
import com.pcbwx.pcbmis.bean.OrderDetail;
import com.pcbwx.pcbmis.bean.ReportItem;
import com.pcbwx.pcbmis.bean.request.CheckDataItem;
import com.pcbwx.pcbmis.bean.response.CheckItem;
import com.pcbwx.pcbmis.enums.CheckStateEnum;
import com.pcbwx.pcbmis.enums.CheckTemplateOptionEnum;
import com.pcbwx.pcbmis.enums.ReportStateEnum;
import com.pcbwx.pcbmis.enums.ReportTemplateOptionEnum;
import com.pcbwx.pcbmis.enums.TemplateBaseIdEnum;
import com.pcbwx.pcbmis.model.PcbCheckOrder;
import com.pcbwx.pcbmis.model.PcbCheckReport;
import com.pcbwx.pcbmis.model.PcbCheckTemplate;
import com.pcbwx.pcbmis.model.PcbReportTemplate;

public class PcbmisUtil {

	public static BigDecimal getMax(BigDecimal big1, BigDecimal big2) {
		int result = big1.compareTo(big2);
		if (result == 1) {
			return big1;
		}
		return big2;
	}

	public static BigDecimal operate(BigDecimal a, BigDecimal b, BigDecimal c) {
		if (b == null || c == null) {
			return null;
		}
		return a.divide(b, 5, BigDecimal.ROUND_HALF_UP).multiply(c).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public static String int2judge(Integer judgeint) {
		if (judgeint == null) {
			return null;
		}

		if (judgeint == 2) {
			return "/";
		} else if (judgeint == 1) {
			return "合格";
		} else if (judgeint == 0) {
			return "不合格";
		} else {
			return "";
		}
	}

	public static Integer judge2int(String str) {
		if (StringUtil.isEmpty(str)) {
			return 3;
		}
		if (str.equals("/")) {
			return 2;
		} else if (str.equals("合格")) {
			return 1;
		} else if (str.equals("不合格")) {
			return 0;
		} else {
			return null;
		}
	}

	public static String int2enable(Integer enable) {
		if (enable == null) {
			return null;
		}
		if (enable == 1) {
			return "启用";
		} else if (enable == 0) {
			return "禁用";
		}
		return null;
	}

	public static TemplateBaseIdEnum goforPcbCheckTemplate(String cate, String craft) {
		if (cate != null && craft != null) {
			if (!cate.contains("民") && !cate.contains("-")) {
				if (craft.contains("软")) {
					return TemplateBaseIdEnum.GJB_7548_2012;
				} else {
					return TemplateBaseIdEnum.GJB_362B_2009;
				}
			} else {
				return TemplateBaseIdEnum.IPC_A_600H;
			}
		} else {
			return null;
		}
	}

	public static BigDecimal double2bigDecimal(Double dou) {
		if (dou == null) {
			return null;
		}
		BigDecimal bigDecimal = new BigDecimal(dou);
		return bigDecimal;
	}

	public static Double str2double(String string) {
		Double a = null;
		try {
			a = Double.valueOf(string);
		} catch (NumberFormatException e) {
			a = null;
		}
		return a;
	}

	public static Double bigDecimal2double(BigDecimal bigDecimal) {
		if (bigDecimal == null) {
			return null;
		}
		Double dou = bigDecimal.doubleValue();
		return dou;
	}

	/**
	 * 判断列表是否为空
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpity(List list) {
		if (list == null || list.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 获取生产备注
	 * 
	 * @param noteIn
	 *            生产备注内部
	 * @param noteOut
	 *            生产备注外部
	 * @return
	 */
	public static String getProductNote(List<String> noteIn, List<String> noteOut) {
		String notes = "";
		notes = notes + "内部：<br>";
		if (PcbmisUtil.isEmpity(noteIn)) {
			notes = notes + "-<br>";
		} else {
			for (String string : noteIn) {
				notes = notes + string + "<br>";
			}
		}
		notes = notes + "外部：<br>";
		if (PcbmisUtil.isEmpity(noteOut)) {
			notes = notes + "-<br>";
		} else {
			for (String string : noteOut) {
				notes = notes + string + "<br>";
			}
		}
		return notes;
	}

	/**
	 * 查看检验单是否可点击
	 * 
	 * @param pcbCheckOrder
	 * @param auths
	 * @param userCode
	 * @return
	 */
	public static Map<String, Object> isOrderCheckable(PcbCheckOrder pcbCheckOrder, Set<Integer> auths,
			String userCode) {
		String href = "";
		Integer checkable = 0;
		if (pcbCheckOrder.getCheckStateId() == CheckStateEnum.NO_CHECK.getCode()
				|| pcbCheckOrder.getSpotCheckNumPcs() == null || pcbCheckOrder.getSpotCheckNumPcs() == 0) {
			checkable = 0;
		}
		if (pcbCheckOrder.getCheckStateId() == CheckStateEnum.TO_CHECK.getCode()
				&& pcbCheckOrder.getSpotCheckNumPcs() != null && pcbCheckOrder.getSpotCheckNumPcs() != 0
				&& auths.contains(1)) {
			href = "test-num.html";
			checkable = 1;
		}
		if (pcbCheckOrder.getCheckStateId() == CheckStateEnum.CHECKING.getCode() && auths.contains(1)
				&& userCode.equals(pcbCheckOrder.getInspector())) {
			href = "test-num.html";
			checkable = 1;
		}
		if (pcbCheckOrder.getCheckStateId() == CheckStateEnum.TO_AUDIT.getCode() && auths.contains(2)) {
			href = "review-num.html";
			checkable = 1;
		}
		if (pcbCheckOrder.getCheckStateId() == CheckStateEnum.AUDITING.getCode() && auths.contains(2)
				&& userCode.equals(pcbCheckOrder.getAuditor())) {
			href = "review-num.html";
			checkable = 1;
		}
		if (pcbCheckOrder.getCheckStateId() == CheckStateEnum.COMPLETE.getCode()) {
			href = "num.html";
			checkable = 1;
		}
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("checkable", checkable);
		response.put("href", href);
		return response;
	}

	/**
	 * 查看报告单是否可点击
	 * 
	 * @param pcbCheckReport
	 * @param authIds
	 * @param userCode
	 * @return
	 */
	public static Map<String, Object> isReportCheckable(PcbCheckReport pcbCheckReport, Set<Integer> authIds,
			String userCode) {
		Integer checkable = 0;
		String href = "";
		if (pcbCheckReport.getStatusId() == ReportStateEnum.TO_CREATE.getCode() && authIds.contains(3)) {
			checkable = 1;
			href = "write-report.html";
		}
		if (pcbCheckReport.getStatusId() == ReportStateEnum.EDITING.getCode()
				&& userCode.equals(pcbCheckReport.getReportMakerCode()) && authIds.contains(3)) {
			checkable = 1;
			href = "write-report.html";
		}
		if (pcbCheckReport.getStatusId() == ReportStateEnum.TO_PROVE.getCode() && authIds.contains(4)) {
			checkable = 1;
			href = "review-report.html";
		}
		if (pcbCheckReport.getStatusId() == ReportStateEnum.PROVING.getCode()
				&& userCode.equals(pcbCheckReport.getReportAuditorCode()) && authIds.contains(4)) {
			checkable = 1;
			href = "review-report.html";
		}
		if (pcbCheckReport.getStatusId() == ReportStateEnum.COMPLETE.getCode()) {
			checkable = 1;
			href = "num-report.html";
		}
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("checkable", checkable);
		response.put("href", href);
		return response;
	}

	public static String toString(Object object) {
		if (object == null) {
			return null;
		}
		return object.toString();
	}

	/**
	 * 获取两个数中大的数
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static Integer getMax(Integer a, Integer b) {
		if (Objects.equals(a, null)) {
			return a;
		}
		if (Objects.equals(b, null)) {
			return b;
		}
		if (a > b) {
			return a;
		} else {
			return b;
		}
	}

	/**
	 * 把数字str保存两位小数
	 * 
	 * @param str
	 * @return
	 */
	public static String stringNum2Two(String str) {
		BigDecimal doublel = null;
		try {
			doublel = new BigDecimal(str);
		} catch (Exception e) {
			return str;
		}
		doublel = doublel.setScale(2, RoundingMode.HALF_UP);

		return doublel.toString();
	}

	/**
	 * 把数字str转为小数
	 * 
	 * @param str
	 *            字符串数字
	 * @param scale
	 *            小数位数
	 * @return
	 */
	public static String string2Num(String str, Integer scale) {
		BigDecimal doublel = null;
		try {
			doublel = new BigDecimal(str);
		} catch (Exception e) {
			return str;
		}
		doublel = doublel.setScale(scale, RoundingMode.HALF_UP);

		return doublel.toString();
	}

	public static List<Integer> str2list(String jsonList) {
		List<Integer> contentIdList = null;
		try {
			Gson g = new Gson();
			Type type = (Type) new TypeToken<List<Integer>>() {
			}.getType();
			contentIdList = g.fromJson(jsonList, type);
			return contentIdList;
		} catch (JsonSyntaxException e) {
			return new ArrayList<>();
		}
	}

	public static CheckItem operateCheckItem(String optionName, CheckItem checkItem,
			PcbCheckTemplate pcbCheckTemplate) {
		if (Objects.equals(optionName, CheckTemplateOptionEnum.BURRS.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getBurrs());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.GAP.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getGap());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.EXPOSEDCOPPER.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getExposedCopper());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.FABRICTEXTURE.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getFabricTexture());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.PITVOID.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getPitVoid());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.SPOTCRACK.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getSpotCrack());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.DELAMINATIONFOAMING.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getDelaminationFoaming());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.FOREIGNIMPURITY.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getForeignImpurity());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.COVERAGEADHESION.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getCoverageAdhesion());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.COINCIDENCEDEGREE.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getCoincidenceDegree());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.FOAMINGLAYERING.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getFoamingLayering());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.CORRUGATION.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getCorrugation());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.FALSEEXPOSEDCOPPER.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getFalseExposedCopper());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.FALSEBRIDGEDAM.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getFalseBridgeDam());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.CHROMATICABERRATION.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getChromaticAberration());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.IDENTIFICATIONADHESION.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getIdentificationAdhesion());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.NODULESBURRS.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getNodulesBurrs());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.DARKOFHOLETINLEAD.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getDarkOfHoleTinLead());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.PADCOCKED.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getPadCocked());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.HALORING.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getHaloRing());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.OUTERRINGWIDTH.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getOuterRingWidth());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.SOLDERINHOLE.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getSolderInHole());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.CLOGHOLE.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getClogHole());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.LINEWIDTHSPACING.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getLineWidthSpacing());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.SPECIAL_BOARD_NUM.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getSpecialBoardNum());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.PRINTED_BOARD_ELSE.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getPrintedBoardElse());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.BASE_MATERIAL_ELSE.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getBaseMaterialElse());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.SOLDERMASK_ELSE.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getSolderMaskElse());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.MARK_ELSE.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getMarkElse());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.CONDUCTIVE_PATTERN_ELSE.getCode())) {
			checkItem.setRequire(pcbCheckTemplate.getConductivePatternElse());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.BOARDLONG.getCode())) {
			checkItem.setResult(pcbCheckTemplate.getBoardLongResult());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.BOARDWIDE.getCode())) {
			checkItem.setResult(pcbCheckTemplate.getBoardWideResult());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.BOARDPLY.getCode())) {
			checkItem.setResult(pcbCheckTemplate.getBoardPlyResult());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.LAY_HEIGHT.getCode())) {
			checkItem.setResult(pcbCheckTemplate.getSmoothnessResult());
		} else if (Objects.equals(optionName, CheckTemplateOptionEnum.WARP_HEIGHT.getCode())) {
			checkItem.setResult(pcbCheckTemplate.getSmoothnessResult());
		}
		return checkItem;
	}

	public static String getBadNum(Integer badNum) {
		String badNumStr = null;
		if (badNum == null || badNum == 0) {
			badNumStr = "/";
		} else {
			badNumStr = badNum.toString();
		}
		return badNumStr;
	}

	/**
	 * 
	 * @param detail
	 * @param checkDetail
	 * @return
	 */
	public static void getCheckItem(Map<String, CheckItemRequest> map, OrderDetail detail, CheckDetail checkDetail) {
		CheckItemRequest factory = new CheckItemRequest();
		factory.setRequire(detail.getFactory());
		factory.setResult(checkDetail.getFactory_result());
		factory.setBadNum(checkDetail.getFactory_bad_num());
		factory.setJudgeResult(checkDetail.getFactory_judge_result());
		map.put("factory", factory);
		CheckItemRequest board_prevent_smt = new CheckItemRequest();
		board_prevent_smt.setRequire(detail.getBoard_prevent_smt());
		board_prevent_smt.setResult(checkDetail.getBoard_prevent_smt_result());
		board_prevent_smt.setBadNum(checkDetail.getBoard_prevent_smt_bad_num());
		board_prevent_smt.setJudgeResult(checkDetail.getBoard_prevent_smt_judge_result());
		map.put("board_prevent_smt", board_prevent_smt);
		CheckItemRequest prevent_smt_color = new CheckItemRequest();
		prevent_smt_color.setRequire(detail.getPrevent_smt_color());
		prevent_smt_color.setResult(checkDetail.getPrevent_smt_color_result());
		prevent_smt_color.setBadNum(checkDetail.getPrevent_smt_color_bad_num());
		prevent_smt_color.setJudgeResult(checkDetail.getPrevent_smt_color_judge_result());
		map.put("prevent_smt_color", prevent_smt_color);
		CheckItemRequest board_character = new CheckItemRequest();
		board_character.setRequire(detail.getBoard_character());
		board_character.setResult(checkDetail.getBoard_character_result());
		board_character.setBadNum(checkDetail.getBoard_character_bad_num());
		board_character.setJudgeResult(checkDetail.getBoard_character_judge_result());
		map.put("board_character", board_character);
		CheckItemRequest character_color = new CheckItemRequest();
		character_color.setRequire(detail.getCharacter_color());
		character_color.setResult(checkDetail.getCharacter_color_result());
		character_color.setBadNum(checkDetail.getCharacter_color_bad_num());
		character_color.setJudgeResult(checkDetail.getCharacter_color_judge_result());
		map.put("character_color", character_color);
		CheckItemRequest logo = new CheckItemRequest();
		logo.setRequire(detail.getLogo());
		logo.setResult(checkDetail.getLogo_result());
		logo.setBadNum(checkDetail.getLogo_bad_num());
		logo.setJudgeResult(checkDetail.getLogo_judge_result());
		map.put("logo", logo);
		CheckItemRequest batch_number = new CheckItemRequest();
		batch_number.setRequire(checkDetail.getLogo_result());
		batch_number.setResult(checkDetail.getBatch_number_result());
		batch_number.setBadNum(checkDetail.getBatch_number_bad_num());
		batch_number.setJudgeResult(checkDetail.getBatch_number_judge_result());
		map.put("batch_number", batch_number);
		CheckItemRequest surface_process = new CheckItemRequest();
		surface_process.setRequire(detail.getSurface_process());
		surface_process.setResult(checkDetail.getSurface_process_result());
		surface_process.setBadNum(checkDetail.getSurface_process_bad_num());
		surface_process.setJudgeResult(checkDetail.getSurface_process_judge_result());
		map.put("surface_process", surface_process);
		CheckItemRequest viaHoleProcess = new CheckItemRequest();
		CheckDataItem checkDataItem = checkDetail.getViaHoleProcess();
		if (checkDataItem != null) {
			viaHoleProcess.setRequire(checkDataItem.getRequire());
			viaHoleProcess.setResult(checkDataItem.getResult());
			viaHoleProcess.setBadNum(checkDataItem.getBadNum());
			viaHoleProcess.setJudgeResult(checkDataItem.getJudge());
			map.put("viaHoleProcess", viaHoleProcess);
		}
		//
		CheckItemRequest boardLong = new CheckItemRequest();
		boardLong.setRequire(detail.getBoardLong() + "");
		boardLong.setBoardTolerance(detail.getBoardLong_tolerance());
		boardLong.setResult(checkDetail.getBoardLong_result());
		boardLong.setBadNum(checkDetail.getBoardLong_bad_num());
		boardLong.setJudgeResult(checkDetail.getBoardLong_judge_result());
		map.put("boardLong", boardLong);
		CheckItemRequest boardWide = new CheckItemRequest();
		boardWide.setRequire(detail.getBoardWide() + "");
		boardWide.setBoardTolerance(detail.getBoardWide_tolerance());
		boardWide.setResult(checkDetail.getBoardWide_result());
		boardWide.setBadNum(checkDetail.getBoardWide_bad_num());
		boardWide.setJudgeResult(checkDetail.getBoardWide_judge_result());
		map.put("boardWide", boardWide);
		CheckItemRequest boardPly = new CheckItemRequest();
		boardPly.setRequire(detail.getBoardPly() + "");
		boardPly.setBoardTolerance(detail.getBoardPly_tolerance());
		boardPly.setResult(checkDetail.getBoardPly_result());
		boardPly.setBadNum(checkDetail.getBoardPly_bad_num());
		boardPly.setJudgeResult(checkDetail.getBoardPly_judge_result());
		map.put("boardPly", boardPly);
		CheckItemRequest Lay_height = new CheckItemRequest();
		Lay_height.setRequire(detail.getLay_height() + "");
		Lay_height.setResult(checkDetail.getLay_height_result());
		Lay_height.setBadNum(checkDetail.getLay_height_bad_num());
		Lay_height.setJudgeResult(checkDetail.getLay_height_judge_result());
		map.put("Lay_height", Lay_height);
		CheckItemRequest warp_height = new CheckItemRequest();
		warp_height.setRequire(detail.getWarp_height() + "");
		warp_height.setResult(checkDetail.getWarp_height_result());
		warp_height.setBadNum(checkDetail.getWarp_height_bad_num());
		warp_height.setJudgeResult(checkDetail.getWarp_height_judge_result());
		map.put("warp_height", warp_height);
		CheckItemRequest burrs = new CheckItemRequest();
		burrs.setRequire(checkDetail.getBurrs_require());
		burrs.setResult(checkDetail.getBurrs_result());
		burrs.setBadNum(checkDetail.getBurrs_bad_num());
		burrs.setJudgeResult(checkDetail.getBurrs_judge_result());
		map.put("burrs", burrs);
		CheckItemRequest gap = new CheckItemRequest();
		gap.setRequire(checkDetail.getGap_require());
		gap.setResult(checkDetail.getGap_result());
		gap.setBadNum(checkDetail.getGap_bad_num());
		gap.setJudgeResult(checkDetail.getGap_judge_result());
		map.put("gap", gap);
		CheckItemRequest exposedCopper = new CheckItemRequest();
		exposedCopper.setRequire(checkDetail.getExposedCopper_require());
		exposedCopper.setResult(checkDetail.getExposedCopper_result());
		exposedCopper.setBadNum(checkDetail.getExposedCopper_bad_num());
		exposedCopper.setJudgeResult(checkDetail.getExposedCopper_judge_result());
		map.put("exposedCopper", exposedCopper);
		CheckItemRequest fabricTexture = new CheckItemRequest();
		fabricTexture.setRequire(checkDetail.getFabricTexture_require());
		fabricTexture.setResult(checkDetail.getFabricTexture_result());
		fabricTexture.setBadNum(checkDetail.getFabricTexture_bad_num());
		fabricTexture.setJudgeResult(checkDetail.getFabricTexture_judge_result());
		map.put("fabricTexture", fabricTexture);
		CheckItemRequest pitVoid = new CheckItemRequest();
		pitVoid.setRequire(checkDetail.getPitVoid_require());
		pitVoid.setResult(checkDetail.getPitVoid_result());
		pitVoid.setBadNum(checkDetail.getPitVoid_bad_num());
		pitVoid.setJudgeResult(checkDetail.getPitVoid_judge_result());
		map.put("pitVoid", pitVoid);
		CheckItemRequest spotCrack = new CheckItemRequest();
		spotCrack.setRequire(checkDetail.getSpotCrack_require());
		spotCrack.setResult(checkDetail.getSpotCrack_result());
		spotCrack.setBadNum(checkDetail.getSpotCrack_bad_num());
		spotCrack.setJudgeResult(checkDetail.getSpotCrack_judge_result());
		map.put("spotCrack", spotCrack);
		CheckItemRequest delaminationFoaming = new CheckItemRequest();
		delaminationFoaming.setRequire(checkDetail.getDelaminationFoaming_require());
		delaminationFoaming.setResult(checkDetail.getDelaminationFoaming_result());
		delaminationFoaming.setBadNum(checkDetail.getDelaminationFoaming_bad_num());
		delaminationFoaming.setJudgeResult(checkDetail.getDelaminationFoaming_judge_result());
		map.put("delaminationFoaming", delaminationFoaming);
		CheckItemRequest foreignImpurity = new CheckItemRequest();
		foreignImpurity.setRequire(checkDetail.getForeignImpurity_require());
		foreignImpurity.setResult(checkDetail.getForeignImpurity_result());
		foreignImpurity.setBadNum(checkDetail.getForeignImpurity_bad_num());
		foreignImpurity.setJudgeResult(checkDetail.getForeignImpurity_judge_result());
		map.put("foreignImpurity", foreignImpurity);
		CheckItemRequest coverageAdhesion = new CheckItemRequest();
		coverageAdhesion.setRequire(checkDetail.getCoverageAdhesion_require());
		coverageAdhesion.setResult(checkDetail.getCoverageAdhesion_result());
		coverageAdhesion.setBadNum(checkDetail.getCoverageAdhesion_bad_num());
		coverageAdhesion.setJudgeResult(checkDetail.getCoverageAdhesion_judge_result());
		map.put("coverageAdhesion", coverageAdhesion);
		CheckItemRequest coincidenceDegree = new CheckItemRequest();
		coincidenceDegree.setRequire(checkDetail.getCoincidenceDegree_require());
		coincidenceDegree.setResult(checkDetail.getCoincidenceDegree_result());
		coincidenceDegree.setBadNum(checkDetail.getCoincidenceDegree_bad_num());
		coincidenceDegree.setJudgeResult(checkDetail.getCoincidenceDegree_judge_result());
		map.put("coincidenceDegree", coincidenceDegree);
		CheckItemRequest foamingLayering = new CheckItemRequest();
		foamingLayering.setRequire(checkDetail.getFoamingLayering_require());
		foamingLayering.setResult(checkDetail.getFoamingLayering_result());
		foamingLayering.setBadNum(checkDetail.getFoamingLayering_bad_num());
		foamingLayering.setJudgeResult(checkDetail.getFoamingLayering_judge_result());
		map.put("foamingLayering", foamingLayering);
		CheckItemRequest corrugation = new CheckItemRequest();
		corrugation.setRequire(checkDetail.getCorrugation_require());
		corrugation.setResult(checkDetail.getCorrugation_result());
		corrugation.setBadNum(checkDetail.getCorrugation_bad_num());
		corrugation.setJudgeResult(checkDetail.getCorrugation_judge_result());
		map.put("corrugation", corrugation);
		CheckItemRequest falseExposedCopper = new CheckItemRequest();
		falseExposedCopper.setRequire(checkDetail.getFalseExposedCopper_require());
		falseExposedCopper.setResult(checkDetail.getFalseExposedCopper_result());
		falseExposedCopper.setBadNum(checkDetail.getFalseExposedCopper_bad_num());
		falseExposedCopper.setJudgeResult(checkDetail.getFalseExposedCopper_judge_result());
		map.put("falseExposedCopper", falseExposedCopper);
		CheckItemRequest falseBridgeDam = new CheckItemRequest();
		falseBridgeDam.setRequire(checkDetail.getFalseBridgeDam_require());
		falseBridgeDam.setResult(checkDetail.getFalseBridgeDam_result());
		falseBridgeDam.setBadNum(checkDetail.getFalseBridgeDam_bad_num());
		falseBridgeDam.setJudgeResult(checkDetail.getFalseBridgeDam_judge_result());
		map.put("falseBridgeDam", falseBridgeDam);
		CheckItemRequest chromaticAberration = new CheckItemRequest();
		chromaticAberration.setRequire(checkDetail.getChromaticAberration_require());
		chromaticAberration.setResult(checkDetail.getChromaticAberration_result());
		chromaticAberration.setBadNum(checkDetail.getChromaticAberration_bad_num());
		chromaticAberration.setJudgeResult(checkDetail.getChromaticAberration_judge_result());
		map.put("chromaticAberration", chromaticAberration);
		CheckItemRequest identificationAdhesion = new CheckItemRequest();
		identificationAdhesion.setRequire(checkDetail.getIdentificationAdhesion_require());
		identificationAdhesion.setResult(checkDetail.getIdentificationAdhesion_result());
		identificationAdhesion.setBadNum(checkDetail.getIdentificationAdhesion_bad_num());
		identificationAdhesion.setJudgeResult(checkDetail.getIdentificationAdhesion_judge_result());
		map.put("identificationAdhesion", identificationAdhesion);
		CheckItemRequest nodulesBurrs = new CheckItemRequest();
		nodulesBurrs.setRequire(checkDetail.getNodulesBurrs_require());
		nodulesBurrs.setResult(checkDetail.getNodulesBurrs_result());
		nodulesBurrs.setBadNum(checkDetail.getNodulesBurrs_bad_num());
		nodulesBurrs.setJudgeResult(checkDetail.getNodulesBurrs_judge_result());
		map.put("nodulesBurrs", nodulesBurrs);
		CheckItemRequest darkOfHoleTinLead = new CheckItemRequest();
		darkOfHoleTinLead.setRequire(checkDetail.getDarkOfHoleTinLead_require());
		darkOfHoleTinLead.setResult(checkDetail.getDarkOfHoleTinLead_result());
		darkOfHoleTinLead.setBadNum(checkDetail.getDarkOfHoleTinLead_bad_num());
		darkOfHoleTinLead.setJudgeResult(checkDetail.getDarkOfHoleTinLead_judge_result());
		map.put("darkOfHoleTinLead", darkOfHoleTinLead);
		CheckItemRequest padCocked = new CheckItemRequest();
		padCocked.setRequire(checkDetail.getPadCocked_require());
		padCocked.setResult(checkDetail.getPadCocked_result());
		padCocked.setBadNum(checkDetail.getPadCocked_bad_num());
		padCocked.setJudgeResult(checkDetail.getPadCocked_judge_result());
		map.put("padCocked", padCocked);
		CheckItemRequest haloRing = new CheckItemRequest();
		haloRing.setRequire(checkDetail.getHaloRing_require());
		haloRing.setResult(checkDetail.getHaloRing_result());
		haloRing.setBadNum(checkDetail.getHaloRing_bad_num());
		haloRing.setJudgeResult(checkDetail.getHaloRing_judge_result());
		map.put("haloRing", haloRing);
		CheckItemRequest outerRingWidth = new CheckItemRequest();
		outerRingWidth.setRequire(checkDetail.getOuterRingWidth_require());
		outerRingWidth.setResult(checkDetail.getOuterRingWidth_result());
		outerRingWidth.setBadNum(checkDetail.getOuterRingWidth_bad_num());
		outerRingWidth.setJudgeResult(checkDetail.getOuterRingWidth_judge_result());
		map.put("outerRingWidth", outerRingWidth);
		CheckItemRequest solderInHole = new CheckItemRequest();
		solderInHole.setRequire(checkDetail.getSolderInHole_require());
		solderInHole.setResult(checkDetail.getSolderInHole_result());
		solderInHole.setBadNum(checkDetail.getSolderInHole_bad_num());
		solderInHole.setJudgeResult(checkDetail.getSolderInHole_judge_result());
		map.put("solderInHole", solderInHole);
		CheckItemRequest clogHole = new CheckItemRequest();
		clogHole.setRequire(checkDetail.getClogHole_require());
		clogHole.setResult(checkDetail.getClogHole_result());
		clogHole.setBadNum(checkDetail.getClogHole_bad_num());
		clogHole.setJudgeResult(checkDetail.getClogHole_judge_result());
		map.put("clogHole", clogHole);
		CheckItemRequest lineWidthSpacing = new CheckItemRequest();
		lineWidthSpacing.setRequire(checkDetail.getLineWidthSpacing_require());
		lineWidthSpacing.setResult(checkDetail.getLineWidthSpacing_result());
		lineWidthSpacing.setBadNum(checkDetail.getLineWidthSpacing_bad_num());
		lineWidthSpacing.setJudgeResult(checkDetail.getLineWidthSpacing_judge_result());
		map.put("lineWidthSpacing", lineWidthSpacing);
		CheckItemRequest printed_board_else = new CheckItemRequest();
		printed_board_else.setRequire(checkDetail.getPrinted_board_else_require());
		printed_board_else.setResult(checkDetail.getPrinted_board_else_result());
		printed_board_else.setBadNum(checkDetail.getPrinted_board_else_bad_num());
		printed_board_else.setJudgeResult(checkDetail.getPrinted_board_else_judge_result());
		map.put("printed_board_else", printed_board_else);
		CheckItemRequest special_board_num = new CheckItemRequest();
		special_board_num.setRequire(checkDetail.getSpecial_board_num_require());
		special_board_num.setResult(checkDetail.getSpecial_board_num_result());
		special_board_num.setBadNum(checkDetail.getSpecial_board_num_bad_num());
		special_board_num.setJudgeResult(checkDetail.getSpecial_board_num_judge_result());
		map.put("special_board_num", special_board_num);
		CheckItemRequest base_material_else = new CheckItemRequest();
		base_material_else.setRequire(checkDetail.getBase_material_else_require());
		base_material_else.setResult(checkDetail.getBase_material_else_result());
		base_material_else.setBadNum(checkDetail.getBase_material_else_bad_num());
		base_material_else.setJudgeResult(checkDetail.getBase_material_else_judge_result());
		map.put("base_material_else", base_material_else);
		CheckItemRequest soldermask_else = new CheckItemRequest();
		soldermask_else.setRequire(checkDetail.getSoldermask_else_require());
		soldermask_else.setResult(checkDetail.getSoldermask_else_result());
		soldermask_else.setBadNum(checkDetail.getSoldermask_else_bad_num());
		soldermask_else.setJudgeResult(checkDetail.getSoldermask_else_judge_result());
		map.put("soldermask_else", soldermask_else);
		CheckItemRequest mark_else = new CheckItemRequest();
		mark_else.setRequire(checkDetail.getMark_else_require());
		mark_else.setResult(checkDetail.getMark_else_result());
		mark_else.setBadNum(checkDetail.getMark_else_bad_num());
		mark_else.setJudgeResult(checkDetail.getMark_else_judge_result());
		map.put("mark_else", mark_else);
		CheckItemRequest conductive_pattern_else = new CheckItemRequest();
		conductive_pattern_else.setRequire(checkDetail.getConductive_pattern_else_require());
		conductive_pattern_else.setResult(checkDetail.getConductive_pattern_else_result());
		conductive_pattern_else.setBadNum(checkDetail.getConductive_pattern_else_bad_num());
		conductive_pattern_else.setJudgeResult(checkDetail.getConductive_pattern_else_judge_result());
		map.put("conductive_pattern_else", conductive_pattern_else);
	}

	public static ReportItem operateReportItem(String optionName, ReportItem reportItem,
			PcbReportTemplate pcbReportTemplate) {
		if (Objects.equals(optionName, ReportTemplateOptionEnum.BASE_MATERIAL_APPEARANCE.getCode())) {
			reportItem.setCheckRequire(pcbReportTemplate.getBaseMaterialAppearance());
		} else if (Objects.equals(optionName, ReportTemplateOptionEnum.CONDUCTIVE_PATTERN.getCode())) {
			reportItem.setCheckRequire(pcbReportTemplate.getConductivePattern());
		} else if (Objects.equals(optionName, ReportTemplateOptionEnum.PREVENT_SMT_TYPE.getCode())) {
			reportItem.setCheckRequire(pcbReportTemplate.getPreventSmtType());
		} else if (Objects.equals(optionName, ReportTemplateOptionEnum.PREVENT_SMT_APPEARANCE.getCode())) {
			reportItem.setCheckRequire(pcbReportTemplate.getPreventSmtAppearance());
		} else if (Objects.equals(optionName, ReportTemplateOptionEnum.CHARACTER_TYPE.getCode())) {
			reportItem.setCheckRequire(pcbReportTemplate.getCharacterType());
		} else if (Objects.equals(optionName, ReportTemplateOptionEnum.CHARACTER_APPEARANCE.getCode())) {
			reportItem.setCheckRequire(pcbReportTemplate.getCharacterAppearance());
		} else if (Objects.equals(optionName, ReportTemplateOptionEnum.APERTURE.getCode())) {
			reportItem.setCheckRequire(pcbReportTemplate.getAperture());
		} else if (Objects.equals(optionName, ReportTemplateOptionEnum.V_CUT.getCode())) {
			reportItem.setCheckRequire(pcbReportTemplate.getSpecialDimension());
		} else if (Objects.equals(optionName, ReportTemplateOptionEnum.EXTERNAL_COATING_ADHESION.getCode())) {
			reportItem.setCheckRequire(pcbReportTemplate.getExternalCoatingAdhesion());
		} else if (Objects.equals(optionName, ReportTemplateOptionEnum.PREVENT_SMT_CHARACTER_ADHESION.getCode())) {
			reportItem.setCheckRequire(pcbReportTemplate.getPreventSmtCharacterAdhesion());
		} else if (Objects.equals(optionName, ReportTemplateOptionEnum.CIRCUIT_CONNECTIVITY.getCode())) {
			reportItem.setCheckRequire(pcbReportTemplate.getCircuit());
		} else if (Objects.equals(optionName, ReportTemplateOptionEnum.CIRCUIT_INSULATIVITY.getCode())) {
			reportItem.setCheckRequire(pcbReportTemplate.getCircuit());
		} else if (Objects.equals(optionName, ReportTemplateOptionEnum.SPECIAL_IMPEDANCE.getCode())) {
			reportItem.setCheckRequire(pcbReportTemplate.getSpecialImpedance());
		} else if (Objects.equals(optionName, ReportTemplateOptionEnum.SOLDERABILITY.getCode())) {
			reportItem.setCheckRequire(pcbReportTemplate.getSolderability());
		}
		return reportItem;
	}

	public static Integer getSmtSamplingNum(Integer productNum) {
		if (productNum == null) {
			return 0;
		}
		Integer smaplingNum = 0;
		if (productNum >= 1200) {
			smaplingNum = 23;
		} else if (productNum >= 500) {
			smaplingNum = 19;
		} else if (productNum >= 280) {
			smaplingNum = 16;
		} else if (productNum >= 150) {
			smaplingNum = 13;
		} else if (productNum >= 90) {
			smaplingNum = 11;
		} else if (productNum >= 50) {
			smaplingNum = 7;
		} else if (productNum >= 5) {
			smaplingNum = 5;
		} else {
			smaplingNum = productNum;
		}
		return smaplingNum;
	}

	/**
	 * 获取冷板抽检数量
	 * 
	 * @param productNum
	 * @return
	 */
	public static Integer getColSampling(Integer productNum) {
		if (productNum == null) {
			return 0;
		}
		Integer smaplingNum = 0;
		if (productNum >= 1200) {
			smaplingNum = 23;
		} else if (productNum >= 500) {
			smaplingNum = 19;
		} else if (productNum >= 280) {
			smaplingNum = 16;
		} else if (productNum >= 150) {
			smaplingNum = 13;
		} else if (productNum >= 90) {
			smaplingNum = 11;
		} else if (productNum >= 50) {
			smaplingNum = 7;
		} else if (productNum >= 5) {
			smaplingNum = 5;
		} else {
			smaplingNum = productNum;
		}
		return smaplingNum;
	}

	/**
	 * 返回某个数在某个区间的随机数
	 * 
	 * @param max
	 *            最大值
	 * @param min
	 *            最小值
	 * @return
	 */
	public static BigDecimal nextBigDecimal(BigDecimal max, BigDecimal min, Integer scale) {
		BigDecimal db = new BigDecimal(Math.random()).multiply((max.subtract(min))).add(min);
		db = db.setScale(scale, BigDecimal.ROUND_HALF_UP);
		return db;
	}

}
