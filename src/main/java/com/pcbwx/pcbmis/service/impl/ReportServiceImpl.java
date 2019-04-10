package com.pcbwx.pcbmis.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.pcbmis.bean.BaseReportInfo;
import com.pcbwx.pcbmis.bean.BoardSize;
import com.pcbwx.pcbmis.bean.CertificateInfo;
import com.pcbwx.pcbmis.bean.CertificationExtra;
import com.pcbwx.pcbmis.bean.CheckReport;
import com.pcbwx.pcbmis.bean.CheckReportLitter;
import com.pcbwx.pcbmis.bean.ReportApertureCheck;
import com.pcbwx.pcbmis.bean.ReportCertification;
import com.pcbwx.pcbmis.bean.ReportDetail;
import com.pcbwx.pcbmis.bean.ReportIntegrity;
import com.pcbwx.pcbmis.bean.ReportIntro;
import com.pcbwx.pcbmis.bean.ReportItem;
import com.pcbwx.pcbmis.bean.ReportSpecialDimension;
import com.pcbwx.pcbmis.bean.ReportSpecialDimensionItem;
import com.pcbwx.pcbmis.bean.ReportSpecialImpedance;
import com.pcbwx.pcbmis.bean.ReportState;
import com.pcbwx.pcbmis.bean.ReportVcut;
import com.pcbwx.pcbmis.bean.SerialNumber;
import com.pcbwx.pcbmis.bean.request.PcbReportNoteBean;
import com.pcbwx.pcbmis.bean.response.PcbReportNote;
import com.pcbwx.pcbmis.component.CacheService;
import com.pcbwx.pcbmis.dao.BasemAterialMapper;
import com.pcbwx.pcbmis.dao.JoinBoardOrderMapper;
import com.pcbwx.pcbmis.dao.PcbCheckDetailMapper;
import com.pcbwx.pcbmis.dao.PcbCheckOrderMapper;
import com.pcbwx.pcbmis.dao.PcbCheckReportMapper;
import com.pcbwx.pcbmis.dao.PcbInStorageOrderMapper;
import com.pcbwx.pcbmis.dao.PcbReportApertureCheckMapper;
import com.pcbwx.pcbmis.dao.PcbReportCertificationExtraMapper;
import com.pcbwx.pcbmis.dao.PcbReportCertificationMapper;
import com.pcbwx.pcbmis.dao.PcbReportDetailMapper;
import com.pcbwx.pcbmis.dao.PcbReportIntegrityMapper;
import com.pcbwx.pcbmis.dao.PcbReportIntroMapper;
import com.pcbwx.pcbmis.dao.PcbReportSpecialDimensionMapper;
import com.pcbwx.pcbmis.dao.PcbReportSpecialImpedanceMapper;
import com.pcbwx.pcbmis.dao.PcbReportVcutMapper;
import com.pcbwx.pcbmis.dao.ProductBasemAterialMapper;
import com.pcbwx.pcbmis.dao.ProductOrderMapper;
import com.pcbwx.pcbmis.dao.SizeAndWarpingDegreeMapper;
import com.pcbwx.pcbmis.enums.DictionaryEnum;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.enums.ReportSpecialDimensionEnum;
import com.pcbwx.pcbmis.enums.ReportStateEnum;
import com.pcbwx.pcbmis.enums.TemplateBaseIdEnum;
import com.pcbwx.pcbmis.model.BasemAterial;
import com.pcbwx.pcbmis.model.CompanyInfo;
import com.pcbwx.pcbmis.model.CraftInfo;
import com.pcbwx.pcbmis.model.Dictionary;
import com.pcbwx.pcbmis.model.EdaGuest;
import com.pcbwx.pcbmis.model.FactoryInfo;
import com.pcbwx.pcbmis.model.JoinBoardOrder;
import com.pcbwx.pcbmis.model.PcbCheckDetail;
import com.pcbwx.pcbmis.model.PcbCheckOrder;
import com.pcbwx.pcbmis.model.PcbCheckReport;
import com.pcbwx.pcbmis.model.PcbInStorageOrder;
import com.pcbwx.pcbmis.model.PcbReportApertureCheck;
import com.pcbwx.pcbmis.model.PcbReportCertification;
import com.pcbwx.pcbmis.model.PcbReportCertificationExtra;
import com.pcbwx.pcbmis.model.PcbReportDetail;
import com.pcbwx.pcbmis.model.PcbReportIntegrity;
import com.pcbwx.pcbmis.model.PcbReportIntro;
import com.pcbwx.pcbmis.model.PcbReportSpecialDimension;
import com.pcbwx.pcbmis.model.PcbReportSpecialImpedance;
import com.pcbwx.pcbmis.model.PcbReportTemplate;
import com.pcbwx.pcbmis.model.PcbReportVcut;
import com.pcbwx.pcbmis.model.ProductBasemAterial;
import com.pcbwx.pcbmis.model.ProductOrder;
import com.pcbwx.pcbmis.model.RoleAuth;
import com.pcbwx.pcbmis.model.SizeAndWarpingDegree;
import com.pcbwx.pcbmis.model.SurfaceProcess;
import com.pcbwx.pcbmis.model.UserRole;
import com.pcbwx.pcbmis.model.WxtbUser;
import com.pcbwx.pcbmis.service.ReportService;
import com.pcbwx.pcbmis.service.SupportService;
import com.pcbwx.pcbmis.service.UtilService;
import com.pcbwx.pcbmis.utils.DataUtil;
import com.pcbwx.pcbmis.utils.DateTimeUtil;
import com.pcbwx.pcbmis.utils.PcbmisUtil;
import com.pcbwx.pcbmis.utils.StringUtil;

/**
 * 
 * 报告Service
 * 
 * @author 孙贺宇
 *
 */
@Service("reportService")
public class ReportServiceImpl implements ReportService {

	private static Logger logger = Logger.getLogger(ReportServiceImpl.class);

	@Autowired
	private ProductOrderMapper productOrderMapper;
	@Autowired
	private PcbInStorageOrderMapper pcbInStorageOrderMapper;
	@Autowired
	private PcbCheckOrderMapper pcbCheckOrderMapper;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private PcbCheckReportMapper pcbCheckReportMapper;
	@Autowired
	private PcbReportApertureCheckMapper pcbReportApertureCheckMapper;
	@Autowired
	private PcbReportCertificationMapper pcbReportCertificationMapper;
	@Autowired
	private PcbReportDetailMapper pcbReportDetailMapper;
	@Autowired
	private PcbReportIntegrityMapper pcbReportIntegrityMapper;
	@Autowired
	private PcbReportIntroMapper pcbReportIntroMapper;
	@Autowired
	private PcbReportSpecialImpedanceMapper pcbReportSpecialImpedanceMapper;
	@Autowired
	private BasemAterialMapper basemAterialMapper;
	@Autowired
	private ProductBasemAterialMapper productBasemAterialMapper;
	@Autowired
	private JoinBoardOrderMapper joinBoardOrderMapper;
	@Autowired
	private PcbCheckDetailMapper pcbCheckDetailMapper;
	@Autowired
	private PcbReportSpecialDimensionMapper pcbReportSpecialDimensionMapper;
	@Autowired
	private PcbReportVcutMapper pcbReportVcutMapper;
	@Autowired
	private PcbReportCertificationExtraMapper pcbReportCertificationExtraMapper;
	@Autowired
	private SizeAndWarpingDegreeMapper sizeAndWarpingDegreeMapper;

	@Autowired
	private SupportService supportService;
	@Autowired
	private UtilService utilService;

	@Override
	public Map<String, Object> getCheckReports(AkAuthUser wxtbUser, String orderNum, String guest, String boardName,
			Integer gradeId, Integer factoryId, Integer statusId, String makeTimeStart, String makeTimeEnd,
			String auditTimeStart, String auditTimeEnd, Integer page, Integer pageSize) {
		Date makeStart = DateTimeUtil.dateStr2date(makeTimeStart);
		Date makeEnd = DateTimeUtil.dateStr2date(makeTimeEnd);
		makeEnd = DateTimeUtil.getTheDayLastTime(makeEnd);
		Date auditStart = DateTimeUtil.dateStr2date(auditTimeStart);
		Date auditEnd = DateTimeUtil.dateStr2date(auditTimeEnd);
		auditEnd = DateTimeUtil.getTheDayLastTime(auditEnd);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		List<String> edaGuestCode = utilService.getEdaGuest(guest);
		int start = (page - 1) * pageSize;
		List<PcbCheckReport> pcbCheckReports = null;
		List<CheckReport> checkReports = new ArrayList<CheckReport>();
		int total = 0;
		total = pcbCheckReportMapper.selectByParamNum(orderNum, boardName, gradeId, factoryId, statusId, makeStart,
				makeEnd, auditStart, auditEnd, edaGuestCode);
		pcbCheckReports = pcbCheckReportMapper.selectByParam(orderNum, boardName, gradeId, factoryId, statusId,
				makeStart, makeEnd, auditStart, auditEnd, edaGuestCode, start, pageSize);
		if (pcbCheckReports == null || pcbCheckReports.size() == 0) {
			responseMap.put("total", 0);
			responseMap.put("rows", null);
			return responseMap;
		}
		String userCode = wxtbUser.getUserCode();
		Set<Integer> authIds = getUserAuths(userCode);
		for (PcbCheckReport pcbCheckReport : pcbCheckReports) {
			CheckReport checkReport = new CheckReport(pcbCheckReport);
			Map<String, Object> checkMap = PcbmisUtil.isReportCheckable(pcbCheckReport, authIds, userCode);
			checkReport.setCheckable((Integer) checkMap.get("checkable"));
			checkReport.setHref(checkMap.get("href").toString());
			checkReports.add(checkReport);
		}
		responseMap.put("total", total);
		responseMap.put("rows", checkReports);
		return responseMap;
	}

	@Override
	public Map<String, Object> goForReportDetail(AkAuthUser wxtbUser, String reportNum, Integer type,
			Integer templateId) {
		boolean isChangeTemplate = true;
		if (templateId == null) {
			isChangeTemplate = false;
		}
		Map<String, Object> response = new HashMap<String, Object>();
		PcbCheckReport pcbCheckReport = pcbCheckReportMapper.selectByReportNum(reportNum);
		if (pcbCheckReport == null) {
			return response;
		}
		String orderNum = pcbCheckReport.getOrderNum();
		if (orderNum == null) {
			return response;
		}
		ReportState reportState = new ReportState();
		reportState.setState(pcbCheckReport.getStatus());
		reportState.setStateId(pcbCheckReport.getStatusId());
		response.put("reportState", reportState);
		// 以下字段为导出而添加
		// 流水号
		response.put("serialNumber", pcbCheckReport.getSerialNumber());
		// 版次
		response.put("revision", pcbCheckReport.getRevision());
		// 编号
		response.put("documentNumber", pcbCheckReport.getDocumentNumber());
		String checkNum = pcbCheckReport.getCheckNum();
		PcbCheckOrder pcbCheckOrder = pcbCheckOrderMapper.selectByCheckNum(checkNum);
		if (pcbCheckOrder == null) {
			pcbCheckOrder = new PcbCheckOrder();
		}
		String orderNumber = pcbCheckReport.getOrderNumber();
		PcbInStorageOrder pcbInStorageOrder = new PcbInStorageOrder();
		if (orderNumber != null) {
			pcbInStorageOrder = pcbInStorageOrderMapper.selectByOrderNumber(orderNumber);
		}
		ProductOrder order = productOrderMapper.selectByOrderNum(orderNum);
		if (order == null) {
			return response;
		}
		PcbReportTemplate pcbReportTemplate = new PcbReportTemplate();
		if (templateId == null) {
			if (pcbCheckReport.getTemplateId() == null) {
				TemplateBaseIdEnum templateBaseIdEnum = goforPcbCheckTemplate(order);
				if (templateBaseIdEnum != null) {
					templateId = templateBaseIdEnum.getCode();
				}
			} else {
				templateId = pcbCheckReport.getTemplateId();
			}
		}
		pcbReportTemplate = cacheService.getPcbReportTemplate(templateId);
		// 检验记录表中的标记>批次号，检验结果栏
		List<PcbCheckDetail> pcbCheckDetails = pcbCheckDetailMapper.selectByCheckNumAndOptionName(checkNum,
				"batch_number");
		String productPeriod = null;
		if (pcbCheckDetails != null && pcbCheckDetails.size() != 0) {
			productPeriod = pcbCheckDetails.get(0).getCheckResult();
		}
		// 检验记录表中的标记>logo，检验结果栏
		pcbCheckDetails = pcbCheckDetailMapper.selectByCheckNumAndOptionName(checkNum, "logo");
		String logo = null;
		if (pcbCheckDetails != null && pcbCheckDetails.size() != 0) {
			logo = pcbCheckDetails.get(0).getCheckResult();
		}
		// 检验内容
		List<PcbReportDetail> pcbReportDetails = pcbReportDetailMapper.selectByReportNum(reportNum);
		Map<String, ReportItem> reportIteMap = new HashMap<String, ReportItem>();
		List<String> joinOrderCodes = new ArrayList<String>();
		String orderType = pcbCheckReport.getOrderType();
		if (orderType != null) {
			if (orderType.equals("JoinBoardOrder")) {
				joinOrderCodes.add(pcbCheckReport.getJoinBoardOrderCode());
			}
		}
		if (joinOrderCodes.size() == 0) {
			joinOrderCodes = null;
		}
		List<JoinBoardOrder> joinBoardOrders = joinBoardOrderMapper.listByCodes(joinOrderCodes);
		JoinBoardOrder joinBoardOrder = null;
		if (joinBoardOrders != null && joinBoardOrders.size() != 0) {
			joinBoardOrder = joinBoardOrders.get(0);
		}
		boolean isNew = false;
		if (pcbReportDetails.isEmpty()) {
			String base = null;
			List<ProductBasemAterial> productBasemAterials = productBasemAterialMapper.selectByOrderNum(orderNum);
			List<Integer> baseIDs = new ArrayList<Integer>();
			for (ProductBasemAterial productBasemAterial : productBasemAterials) {
				Integer basemAterial = productBasemAterial.getBasemAterialId();
				baseIDs.add(basemAterial);
			}
			if (!baseIDs.isEmpty()) {
				List<BasemAterial> basemAterials = basemAterialMapper.listByInnerIds(baseIDs);
				for (BasemAterial basemAterial : basemAterials) {
					if (StringUtil.isEmpty(base)) {
						base = basemAterial.getBasemAterialName();
					} else {
						base = base + "," + basemAterial.getBasemAterialName();
					}
				}
			}
			isNew = true;
			BoardSize boardSize = getBoardSize(checkNum, orderNum, pcbCheckOrder);
			// 基材类型
			ReportItem reportItem = new ReportItem();
			reportItem.setCheckRequire(base);
			reportItem.setJudgeResult("合格");
			reportIteMap.put("base_material_types", reportItem);
			// 基材外观
			reportItem = new ReportItem();
			reportItem.setCheckRequire(pcbReportTemplate.getBaseMaterialAppearance());
			reportItem.setCheckResult("无缺陷");
			reportItem.setJudgeResult("合格");
			reportIteMap.put("base_material_appearance", reportItem);
			// 导电图形
			reportItem = new ReportItem();
			reportItem.setCheckRequire(pcbReportTemplate.getConductivePattern());
			reportItem.setCheckResult("无缺陷");
			reportItem.setJudgeResult("合格");
			reportIteMap.put("conductive_pattern", reportItem);
			// 阻焊类型
			reportItem = new ReportItem();
			reportItem.setCheckRequire(pcbReportTemplate.getPreventSmtType());
			reportItem.setJudgeResult("合格");
			reportIteMap.put("prevent_smt_type", reportItem);
			// 阻焊外观
			reportItem = new ReportItem();
			reportItem.setCheckRequire(pcbReportTemplate.getPreventSmtAppearance());
			reportItem.setCheckResult("无缺陷");
			reportItem.setJudgeResult("合格");
			reportIteMap.put("prevent_smt_appearance", reportItem);
			// 字符类型
			reportItem = new ReportItem();
			reportItem.setCheckRequire(pcbReportTemplate.getCharacterType());
			reportItem.setJudgeResult("合格");
			reportIteMap.put("character_type", reportItem);
			// 字符外观
			reportItem = new ReportItem();
			reportItem.setCheckRequire(pcbReportTemplate.getCharacterAppearance());
			reportItem.setCheckResult("无缺陷");
			reportItem.setJudgeResult("合格");
			reportIteMap.put("character_appearance", reportItem);
			// 表面处理
			reportItem = new ReportItem();
			final SurfaceProcess surfaceProcess = cacheService.getSurfaceProcess(order.getSurfaceProcessId());
			if (surfaceProcess != null) {
				reportItem.setCheckRequire(surfaceProcess.getProcessName());
			}
			reportItem.setJudgeResult("合格");
			reportIteMap.put("surface_process", reportItem);
			// 常规标记
			reportItem = new ReportItem();
			reportItem.setCheckRequire(logo);
			reportItem.setCheckResult(logo);
			reportItem.setJudgeResult("合格");
			reportIteMap.put("regular_label", reportItem);
			// 生产周期
			reportItem = new ReportItem();
			reportItem.setCheckRequire(productPeriod);
			reportItem.setCheckResult(productPeriod);
			reportItem.setJudgeResult("合格");
			reportIteMap.put("produce_period", reportItem);
			// 外镀层附着力
			reportItem = new ReportItem();
			reportItem.setCheckRequire(pcbReportTemplate.getExternalCoatingAdhesion());
			reportItem.setCheckResult("无脱落");
			reportItem.setJudgeResult("合格");
			reportIteMap.put("external_coating_adhesion", reportItem);
			// 阻焊/字符附着力
			reportItem = new ReportItem();
			reportItem.setCheckRequire(pcbReportTemplate.getPreventSmtCharacterAdhesion());
			reportItem.setCheckResult("无阻焊/字符脱落");
			reportItem.setJudgeResult("合格");
			reportIteMap.put("prevent_smt_character_adhesion", reportItem);
			// 最小线宽
			reportItem = new ReportItem();
			BigDecimal minLineWidth = null;
			if (joinBoardOrder != null) {
				minLineWidth = joinBoardOrder.getMinLineWidth();
			} else {
				minLineWidth = order.getMinLineWidth();
			}
			String minLineWidthStr = null;
			if (minLineWidth != null) {
				minLineWidth = minLineWidth.multiply(new BigDecimal("0.0254"));
				minLineWidthStr = minLineWidth.setScale(3, BigDecimal.ROUND_HALF_UP) + "±20%mm";
			}
			reportItem.setCheckRequire(minLineWidthStr);
			reportItem.setJudgeResult("合格");
			reportIteMap.put("min_line_width", reportItem);
			// 最小间距
			reportItem = new ReportItem();
			BigDecimal minLineDistance = null;
			if (joinBoardOrder != null) {
				minLineDistance = joinBoardOrder.getMinLineDistance();
			} else {
				minLineDistance = order.getMinLineDistance();
			}
			String minLineDistanceStr = null;
			if (minLineDistance != null) {
				minLineDistance = minLineDistance.multiply(new BigDecimal("0.0254"));
				minLineDistanceStr = minLineDistance.setScale(3, BigDecimal.ROUND_HALF_UP) + "±20%mm";
			}
			reportItem.setCheckRequire(minLineDistanceStr);
			reportItem.setJudgeResult("合格");
			reportIteMap.put("min_line_distance", reportItem);
			// 最小环宽
			reportItem = new ReportItem();
			reportItem.setJudgeResult("合格");
			reportIteMap.put("min_annular_ring", reportItem);
			// 成品板厚
			Integer boardPlyId = order.getBoardPlyToleranceId();
			String boardPlyTolerance = utilService.getBoardPlyTolerance(boardPlyId);
			reportItem = new ReportItem();
			reportItem.setCheckRequire(order.getBoardPly() + " " + boardPlyTolerance);
			reportItem.setCheckResult(boardSize.getBoardPly());
			reportItem.setBoardTolerance("");
			reportItem.setJudgeResult("合格");
			reportIteMap.put("board_ply", reportItem);
			// 翘曲度
			reportItem = new ReportItem();
			Dictionary dictionaryW = cacheService.getDictionary(DictionaryEnum.WARPING_DEGREE, 1);
			reportItem.setCheckRequire("≤" + dictionaryW.getValueStr());
			reportItem.setCheckResult(boardSize.getWarpingDegree());
			reportItem.setJudgeResult("合格");
			reportIteMap.put("warping_degree", reportItem);
			// 板长板宽公差
			Integer frameId = order.getFrameToleranceId();
			String boardTolerance = utilService.getFrameTolerance(frameId);
			// 板长
			reportItem = new ReportItem();
			if (joinBoardOrder != null) {
				reportItem.setCheckRequire(joinBoardOrder.getJoinBoardLong().toString());
			} else {
				reportItem.setCheckRequire(order.getBoardLong().toString());
			}
			reportItem.setCheckResult(boardSize.getBoardLong());
			reportItem.setBoardTolerance(boardTolerance);
			reportItem.setJudgeResult("合格");
			reportIteMap.put("board_long", reportItem);
			// 板宽
			reportItem = new ReportItem();
			if (joinBoardOrder != null) {
				reportItem.setCheckRequire(joinBoardOrder.getJoinBoardWide().toString());
			} else {
				reportItem.setCheckRequire(order.getBoardWide().toString());
			}
			reportItem.setCheckResult(boardSize.getBoardWide());
			reportItem.setBoardTolerance(boardTolerance);
			reportItem.setJudgeResult("合格");
			reportIteMap.put("board_wide", reportItem);
			// 孔径
			reportItem = new ReportItem();
			reportItem.setCheckRequire(pcbReportTemplate.getAperture());
			reportItem.setJudgeResult("合格");
			reportIteMap.put("aperture", reportItem);
			// 特殊尺寸
			reportItem = new ReportItem();
			reportItem.setCheckRequire(pcbReportTemplate.getSpecialDimension());
			reportItem.setJudgeResult("合格");
			reportIteMap.put("v_cut", reportItem);
			// 电路连通性
			reportItem = new ReportItem();
			reportItem.setCheckRequire(pcbReportTemplate.getCircuit());
			reportItem.setJudgeResult("合格");
			reportIteMap.put("circuit_connectivity", reportItem);
			// 电路绝缘性
			reportItem = new ReportItem();
			reportItem.setCheckRequire(pcbReportTemplate.getCircuit());
			reportItem.setJudgeResult("合格");
			reportIteMap.put("circuit_insulativity", reportItem);
			// 特性阻抗
			reportItem = new ReportItem();
			reportItem.setCheckRequire(pcbReportTemplate.getSpecialImpedance());
			reportItem.setJudgeResult("合格");
			reportIteMap.put("special_impedance", reportItem);
			// 可焊性
			reportItem = new ReportItem();
			reportItem.setCheckRequire(pcbReportTemplate.getSolderability());
			reportItem.setCheckResult("上锡良好");
			reportItem.setJudgeResult("合格");
			reportIteMap.put("solderability", reportItem);
			// 显微剖切
			reportItem = new ReportItem();
			reportItem.setCheckRequire("/");
			reportItem.setJudgeResult("/");
			reportIteMap.put("microsectioning", reportItem);
		} else {
			for (PcbReportDetail pcbReportDetail : pcbReportDetails) {
				String optionName = pcbReportDetail.getOptionName();
				ReportItem reportItem = new ReportItem();
				reportItem.setCheckRequire(pcbReportDetail.getCheckRequire());
				reportItem.setCheckResult(pcbReportDetail.getCheckResult());
				reportItem.setBoardTolerance(pcbReportDetail.getBoardTolerance());
				reportItem.setJudgeResult(PcbmisUtil.int2judge(pcbReportDetail.getJudgeResult()));
				reportIteMap.put(optionName, reportItem);
				if (isChangeTemplate) {
					PcbmisUtil.operateReportItem(optionName, reportItem, pcbReportTemplate);
				}
			}

		}
		response.put("report_content", reportIteMap);
//		if (!isChangeTemplate) {
//			templateId = pcbCheckReport.getTemplateId();
//		}
		// 出货检验报告
		pcbReportTemplate = cacheService.getPcbReportTemplate(templateId);
		PcbReportIntro pcbReportIntro = pcbReportIntroMapper.selectByReportNum(reportNum);
		ReportIntro reportIntro = null;
		if (pcbReportIntro == null) {
			reportIntro = new ReportIntro();
			String guestName = null;
			final EdaGuest guest = cacheService.getGuest(order.getGuestCode());
			if (guest != null) {
				guestName = guest.getShortNameCn();
			}
			reportIntro.setGuestName(guestName);
			reportIntro.setBoardName(pcbCheckReport.getBoardName());
			reportIntro.setProductPeriod(productPeriod);
			if (isNew) {
				reportIntro.setAcceptanceStandard(pcbReportTemplate.getTemplateName());
				reportIntro.setTemplateId(pcbReportTemplate.getId());
			} else {
				reportIntro.setAcceptanceStandard(pcbReportTemplate.getTemplateName());
				reportIntro.setTemplateId(pcbReportTemplate.getId());
			}
		} else {
			reportIntro = new ReportIntro(pcbReportIntro, productPeriod);
			reportIntro.setTemplateId(pcbReportTemplate.getTemplateId());
			reportIntro.setAcceptanceStandard(pcbReportTemplate.getTemplateName());
		}
		response.put("report_intro", reportIntro);
		// 孔径检测
		List<PcbReportApertureCheck> pcbReportApertureChecks = pcbReportApertureCheckMapper
				.selectByReportNum(reportNum);
		List<ReportApertureCheck> reportApertureChecks = new ArrayList<ReportApertureCheck>();
		if (!pcbReportApertureChecks.isEmpty()) {
			for (PcbReportApertureCheck pcbReportApertureCheck : pcbReportApertureChecks) {
				ReportApertureCheck reportApertureCheck = new ReportApertureCheck();
				reportApertureCheck.setID(pcbReportApertureCheck.getApertureId());
				reportApertureCheck.setJudge(PcbmisUtil.int2judge(pcbReportApertureCheck.getJudge()));
				reportApertureCheck.setpN(pcbReportApertureCheck.getPn());
				reportApertureCheck.setRealCheck(pcbReportApertureCheck.getRealCheck());
				reportApertureCheck.setRequire(pcbReportApertureCheck.getCheckRequire());
				reportApertureCheck.setRequireTolerance(pcbReportApertureCheck.getCheckRequireTolerance());
				reportApertureChecks.add(reportApertureCheck);
			}
		}
		response.put("aperture_check", reportApertureChecks);
		// 特殊尺寸
		List<PcbReportSpecialDimension> pcbReportSpecialDimensions = pcbReportSpecialDimensionMapper
				.selectByReportNum(reportNum);
		ReportSpecialDimension reportSpecialDimension = new ReportSpecialDimension();
		if (pcbReportSpecialDimensions != null && !pcbReportSpecialDimensions.isEmpty()) {
			List<ReportSpecialDimensionItem> items = new ArrayList<ReportSpecialDimensionItem>();
			for (PcbReportSpecialDimension pcbReportSpecialDimension : pcbReportSpecialDimensions) {
				if (Objects.equals(pcbReportSpecialDimension.getItemKey(),
						ReportSpecialDimensionEnum.V_CUT_RESIDUAL_THICKNESS.getCode())) {
					ReportSpecialDimensionItem item = new ReportSpecialDimensionItem(pcbReportSpecialDimension);
					reportSpecialDimension.setvCutResidualThickness(item);
					continue;
				}
				if (Objects.equals(pcbReportSpecialDimension.getItemKey(),
						ReportSpecialDimensionEnum.V_CUT_DEVIATION.getCode())) {
					ReportSpecialDimensionItem item = new ReportSpecialDimensionItem(pcbReportSpecialDimension);
					reportSpecialDimension.setvCutDeviation(item);
					continue;
				}
				if (Objects.equals(pcbReportSpecialDimension.getItemKey(),
						ReportSpecialDimensionEnum.GOLDFINGER_BEVEL_DEPTH.getCode())) {
					ReportSpecialDimensionItem item = new ReportSpecialDimensionItem(pcbReportSpecialDimension);
					reportSpecialDimension.setGoldfingerBevelDepth(item);
					continue;
				}
				if (Objects.equals(pcbReportSpecialDimension.getItemKey(),
						ReportSpecialDimensionEnum.GOLDFINGER_RESIDUAL_THICKNESS.getCode())) {
					ReportSpecialDimensionItem item = new ReportSpecialDimensionItem(pcbReportSpecialDimension);
					reportSpecialDimension.setGoldfingerResidualThickness(item);
					continue;
				}
				if (Objects.equals(pcbReportSpecialDimension.getItemKey(),
						ReportSpecialDimensionEnum.STEP_WIDTH.getCode())) {
					ReportSpecialDimensionItem item = new ReportSpecialDimensionItem(pcbReportSpecialDimension);
					reportSpecialDimension.setStepWidth(item);
					continue;
				}
				if (Objects.equals(pcbReportSpecialDimension.getItemKey(),
						ReportSpecialDimensionEnum.STEP_RESIDUAL_THICKNESS.getCode())) {
					ReportSpecialDimensionItem item = new ReportSpecialDimensionItem(pcbReportSpecialDimension);
					reportSpecialDimension.setStepResidualThickness(item);
					continue;
				}
				if (Objects.equals(pcbReportSpecialDimension.getItemKey(),
						ReportSpecialDimensionEnum.STEPPED_HOLE_APERTURE.getCode())) {
					ReportSpecialDimensionItem item = new ReportSpecialDimensionItem(pcbReportSpecialDimension);
					reportSpecialDimension.setSteppedHoleAperture(item);
					continue;
				}
				if (Objects.equals(pcbReportSpecialDimension.getItemKey(),
						ReportSpecialDimensionEnum.STEPPED_HOLE_HOLE_DEPTH.getCode())) {
					ReportSpecialDimensionItem item = new ReportSpecialDimensionItem(pcbReportSpecialDimension);
					reportSpecialDimension.setSteppedHoleHoleDepth(item);
					continue;
				}
				ReportSpecialDimensionItem item = new ReportSpecialDimensionItem(pcbReportSpecialDimension);
				items.add(item);
			}
			reportSpecialDimension.setRests(items);
		}
		response.put("special_dimensions", reportSpecialDimension);
		// V_cut检验
		ReportVcut reportVcut = null;
		PcbReportVcut pcbReportVcut = pcbReportVcutMapper.selectByReportNum(reportNum);
		if (pcbReportVcut != null) {
			reportVcut = new ReportVcut();
			reportVcut.setRequireAngle(pcbReportVcut.getRequireAngle());
			reportVcut.setRequireH(pcbReportVcut.getRequireH());
			reportVcut.setRequireB(pcbReportVcut.getRequireB());
			reportVcut.setRequireBtolerance(pcbReportVcut.getRequireBtolerance());
			reportVcut.setRealCheakAngle(pcbReportVcut.getRealCheakAngle());
			reportVcut.setRealCheckH(pcbReportVcut.getRealCheckH());
			reportVcut.setRealCheckB(pcbReportVcut.getRealCheckB());
			reportVcut.setJudgeAngle(PcbmisUtil.int2judge(pcbReportVcut.getJudgeAngle()));
			reportVcut.setJudgeH(PcbmisUtil.int2judge(pcbReportVcut.getJudgeH()));
			reportVcut.setJudgeB(PcbmisUtil.int2judge(pcbReportVcut.getJudgeB()));
		}
		response.put("report_vcut", reportVcut);
		// 完整性报告
		PcbReportIntegrity pcbReportIntegrity = pcbReportIntegrityMapper.selectByReportNum(reportNum);
		ReportIntegrity reportIntegrity = new ReportIntegrity();
		if (pcbReportIntegrity != null) {
			reportIntegrity.setCheckInstrument(pcbReportIntegrity.getCheckInstrument());
			reportIntegrity.setCheckPointNum(pcbReportIntegrity.getCheckPointNum());
			reportIntegrity.setCheckVoltage(pcbReportIntegrity.getCheckVoltage());
			reportIntegrity.setConnectedNetNum(pcbReportIntegrity.getConnectedNetNum());
			reportIntegrity.setConnectedResistance(pcbReportIntegrity.getConnectedResistance());
			reportIntegrity.setInsulatedResistance(pcbReportIntegrity.getInsulatedResistance());
			reportIntegrity.setNetNum(pcbReportIntegrity.getNetNum());
			reportIntegrity.setResult(pcbReportIntegrity.getResult());
		}
		response.put("report_integrity", reportIntegrity);
		// 特性阻抗
		// ---单端阻抗
		List<PcbReportSpecialImpedance> singleEndedImpedances = pcbReportSpecialImpedanceMapper
				.selectByReportNumAndId(reportNum, 1);
		List<ReportSpecialImpedance> reportSpecialImpedances = new ArrayList<ReportSpecialImpedance>();
		if (singleEndedImpedances != null && singleEndedImpedances.size() != 0) {
			for (PcbReportSpecialImpedance pcbReportSpecialImpedance : singleEndedImpedances) {
				ReportSpecialImpedance reportSpecialImpedance = new ReportSpecialImpedance();
				reportSpecialImpedance.setSpecialId(1);
				reportSpecialImpedance.setLayer(pcbReportSpecialImpedance.getLayer());
				reportSpecialImpedance.setLayerNum(pcbReportSpecialImpedance.getLayerNum());
				reportSpecialImpedance.setRequire(pcbReportSpecialImpedance.getCheckRequire());
				reportSpecialImpedance.setRequireTolerance(pcbReportSpecialImpedance.getCheckRequireTolerance());
				reportSpecialImpedance.setRealCheck(pcbReportSpecialImpedance.getRealCheck());
				reportSpecialImpedance.setJudge(PcbmisUtil.int2judge(pcbReportSpecialImpedance.getJudge()));
				reportSpecialImpedances.add(reportSpecialImpedance);
			}
		}
		response.put("single_ended_impedances", reportSpecialImpedances);
		// ---差分阻抗
		List<PcbReportSpecialImpedance> differentialCharacteristicImpedance = pcbReportSpecialImpedanceMapper
				.selectByReportNumAndId(reportNum, 2);
		reportSpecialImpedances = new ArrayList<ReportSpecialImpedance>();
		if (differentialCharacteristicImpedance != null && differentialCharacteristicImpedance.size() != 0) {
			for (PcbReportSpecialImpedance pcbReportSpecialImpedance : differentialCharacteristicImpedance) {
				ReportSpecialImpedance reportSpecialImpedance = new ReportSpecialImpedance();
				reportSpecialImpedance.setSpecialId(2);
				reportSpecialImpedance.setLayer(pcbReportSpecialImpedance.getLayer());
				reportSpecialImpedance.setLayerNum(pcbReportSpecialImpedance.getLayerNum());
				reportSpecialImpedance.setRequire(pcbReportSpecialImpedance.getCheckRequire());
				reportSpecialImpedance.setRequireTolerance(pcbReportSpecialImpedance.getCheckRequireTolerance());
				reportSpecialImpedance.setRealCheck(pcbReportSpecialImpedance.getRealCheck());
				reportSpecialImpedance.setJudge(PcbmisUtil.int2judge(pcbReportSpecialImpedance.getJudge()));
				reportSpecialImpedances.add(reportSpecialImpedance);
			}
		}
		response.put("differential_characteristic_impedance", reportSpecialImpedances);
		// 合格证
		PcbReportCertification pcbReportCertification = pcbReportCertificationMapper.selectByReportNum(reportNum);
		ReportCertification reportCertification = new ReportCertification();
		Date checkDate = pcbCheckReport.getAuditDate();
		if (pcbReportCertification != null) {
			reportCertification.setBatchNumber(pcbReportCertification.getBatchNumber());
			reportCertification.setBoardName(pcbReportCertification.getBoardName());
			reportCertification.setCategoryGrade(pcbReportCertification.getCategoryGrade());
			Date proDate = pcbReportCertification.getProductDate();
			Date pakeDate = pcbReportCertification.getPackagingDate();
			reportCertification.setCheckDate(DateTimeUtil.date2dateStr(checkDate));
			reportCertification.setProductDate(DateTimeUtil.date2dateStr(proDate));
			reportCertification.setPackagingDate(DateTimeUtil.date2dateStr(pakeDate));
			String inspectorCode = pcbCheckOrder.getInspector();
			if (inspectorCode != null) {
				WxtbUser inspectorUser = cacheService.getWxtbUser(pcbCheckOrder.getInspector());
				if (wxtbUser != null) {
					reportCertification.setInspector(inspectorUser.getUsername());
				}
			}
			reportCertification.setExpirationDate(pcbReportCertification.getExpirationDate());
			reportCertification.setOrderNumName(pcbReportCertification.getOrderNumName());
			reportCertification.setSize(pcbReportCertification.getSize());
			if (pcbReportCertification.getNumberPcs() != null) {
				reportCertification.setNumberPcs(pcbReportCertification.getNumberPcs());
			}
			reportCertification.setOrderNum(pcbReportCertification.getOrderNum());
			reportCertification.setOrderNumName(pcbReportCertification.getOrderNumName());
			List<PcbReportCertificationExtra> extras = pcbReportCertificationExtraMapper.selectByReportNum(reportNum);
			if (!extras.isEmpty()) {
				List<CertificationExtra> extrasBean = new ArrayList<>();
				for (PcbReportCertificationExtra pcbReportCertificationExtra : extras) {
					CertificationExtra certificationExtra = new CertificationExtra();
					certificationExtra.setKey(pcbReportCertificationExtra.getKey());
					certificationExtra.setValue(pcbReportCertificationExtra.getValue());
					extrasBean.add(certificationExtra);
				}
				reportCertification.setAdd(extrasBean);
			}
		} else {
			final Dictionary dictGrade = cacheService.getDictionary(DictionaryEnum.CATEGORY_GRADE,
					order.getCategoryGradeId());
			if (dictGrade != null) {
				reportCertification.setCategoryGrade(dictGrade.getValueStr());
			}
			reportCertification.setOrderNum(orderNum);
			reportCertification.setBoardName(pcbCheckReport.getBoardName());
			reportCertification.setOrderNumName("工单号");
			Date productDate = order.getProductDate();
			Date startCheckDate = pcbCheckOrder.getCheckDate();
			reportCertification.setProductDate(DateTimeUtil.date2dateStr(productDate));
			reportCertification.setCheckDate(DateTimeUtil.date2dateStr(startCheckDate));
			String inspectorCode = pcbCheckOrder.getInspector();
			if (inspectorCode != null) {
				WxtbUser inspectorUser = cacheService.getWxtbUser(pcbCheckOrder.getInspector());
				if (wxtbUser != null) {
					reportCertification.setInspector(inspectorUser.getUsername());
				}
			}
			if (pcbCheckOrder.getSpotCheckNumPcs() != null) {
				reportCertification.setNumberPcs(pcbCheckOrder.getInAmountPcs() + "PCS");
			}
			reportCertification.setBatchNumber(productPeriod);
			if (joinBoardOrder != null) {
				reportCertification.setSize(joinBoardOrder.getJoinBoardLong() + "*" + joinBoardOrder.getJoinBoardWide()
						+ "*" + order.getBoardPly());
			} else {
				reportCertification
						.setSize(order.getBoardLong() + "*" + order.getBoardWide() + "*" + order.getBoardPly());
			}

		}
		response.put("certification", reportCertification);
		// 基础信息
		BaseReportInfo baseReportInfo = new BaseReportInfo();
		baseReportInfo.setReportNum(reportNum);
		baseReportInfo.setOrderNum(orderNum);
		baseReportInfo.setBoardName(pcbCheckReport.getBoardName());
		EdaGuest guest = cacheService.getGuest(order.getGuestCode());
		if (guest != null) {
			baseReportInfo.setGuestName(guest.getShortNameCn());
		}
		final FactoryInfo factory = cacheService.getFactory(order.getFactoryId());
		if (factory != null) {
			baseReportInfo.setFactoryName(factory.getFactoryName());
			;
		}
		Date productDate = order.getProductDate();
		if (productDate != null) {
			String proDate = DateTimeUtil.date2dateTimeStr(productDate, "yyyy-MM-dd");
			baseReportInfo.setProductDate(proDate);
		}
		Dictionary dictGrade = cacheService.getDictionary(DictionaryEnum.CATEGORY_GRADE, order.getCategoryGradeId());
		if (dictGrade != null) {
			String categoryGrade = dictGrade.getValueStr();
			baseReportInfo.setCategoryGrade(categoryGrade);
		}
		Integer craftId = order.getCraftId();
		CraftInfo craftInfo = cacheService.getCraftInfo(craftId);
		if (craftInfo != null) {
			baseReportInfo.setCraft(craftInfo.getCraftName());
		}
		baseReportInfo.setMetallography_require(order.getMetallographyRequire());
		baseReportInfo.setProductionNumSet(order.getProductionNumSet());
		baseReportInfo.setProductionNumPcs(order.getProductionNumPcs());
		baseReportInfo.setAmountCheckoutPcs(pcbCheckOrder.getInAmountPcs());
		baseReportInfo.setSpotCheckNumPcs(pcbCheckOrder.getSpotCheckNumPcs());
		String userCode = pcbInStorageOrder.getCreaterCode();
		if (userCode != null) {
			WxtbUser wxtbUser2 = cacheService.getWxtbUser(userCode);
			if (wxtbUser2 != null) {
				baseReportInfo.setDeliveryMan(wxtbUser2.getUsername());
			}
		}
		baseReportInfo.setDeliveryDate(DateTimeUtil.date2dateStr(pcbInStorageOrder.getInnerCreateTime()));
		response.put("baseReportInfo", baseReportInfo);
		// note信息
		PcbReportNote pcbReportNote = new PcbReportNote();
		pcbReportNote.setMistakeNote(pcbCheckReport.getMistakeNote());
		pcbReportNote.setNote(pcbCheckReport.getNote());
		pcbReportNote.setSupplierMistakeNote(pcbCheckReport.getSupplierMistakeNote());
		response.put("note", pcbReportNote);
		Integer companyId = order.getBelongCompanyId();
		CompanyInfo companyInfo = cacheService.getCompany(companyId);
		String companyCode = null;
		String companyName = null;
		if (companyInfo != null) {
			companyCode = companyInfo.getOrgCode();
			companyName = companyInfo.getCompanyName();
		}
		response.put("companyCode", companyCode);
		response.put("companyName", companyName);
		if (wxtbUser == null) {
			return response;
		}
		Set<Integer> userAuths = getUserAuths(wxtbUser.getUserCode());
		if (type == 1) {
			Integer status = pcbCheckReport.getStatusId();
			if (status == ReportStateEnum.TO_CREATE.getCode() && userAuths.contains(3)) {
				pcbCheckReport.setStatusId(ReportStateEnum.EDITING.getCode());
				pcbCheckReport.setStatus(ReportStateEnum.EDITING.getDescr());
				pcbCheckReport.setReportMakerCode(wxtbUser.getUserCode());
				supportService.doOperateLog("报告", orderNum, "开始编辑报告", wxtbUser.getUsername());
			} else if (status == ReportStateEnum.TO_PROVE.getCode() && userAuths.contains(4)) {
				pcbCheckReport.setStatusId(ReportStateEnum.PROVING.getCode());
				pcbCheckReport.setStatus(ReportStateEnum.PROVING.getDescr());
				pcbCheckReport.setReportAuditorCode(wxtbUser.getUserCode());
				supportService.doOperateLog("报告", orderNum, "开始审核报告", wxtbUser.getUsername());
			}
			pcbCheckReportMapper.updateByPrimaryKeySelective(pcbCheckReport);
		}
		return response;
	}

	@Override
	public synchronized ErrorCodeEnum postReportDetail(AkAuthUser wxtbUser, String reportNum, ReportDetail reportDetail,
			Integer templateId) {
		PcbCheckReport pcbCheckReport = pcbCheckReportMapper.selectByReportNum(reportNum);
		if (pcbCheckReport == null) {
			return ErrorCodeEnum.SYSTEM_ERROR;
		}
		Date now = new Date();
		if (pcbCheckReport.getStatusId() != ReportStateEnum.EDITING.getCode()) {
			return ErrorCodeEnum.SYSTEM_ERROR;
		}
		String orderNum = pcbCheckReport.getOrderNum();
		ProductOrder productOrder = productOrderMapper.selectByOrderNum(orderNum);
		if (productOrder == null) {
			return ErrorCodeEnum.SYSTEM_ERROR;
		}
		Integer companyId = productOrder.getBelongCompanyId();
		CompanyInfo companyInfo = cacheService.getCompany(companyId);
		String companyCode = null;
		if (companyInfo != null) {
			companyCode = companyInfo.getOrgCode();
		}
		// 获取流水号
		if (pcbCheckReport.getSerialNumber() == null) {
			// No：
			String serialNum = null;
			// 版次：
			String banci = null;
			// 编号：
			String bianhao = null;
			SerialNumber serialNumber = supportService.getQrgsSerialNumber(wxtbUser.getInnerCode(), "出货检验报告",
					new Date(), companyCode);
			if (serialNumber == null || StringUtil.isEmpty(serialNumber.getDocumentNumber())
					|| StringUtil.isEmpty(serialNumber.getRevision()) || StringUtil.isEmpty(serialNumber.getSn())) {
				return ErrorCodeEnum.NO_SERIAL_NUMBER;
			}
			serialNum = serialNumber.getSn();
			banci = serialNumber.getRevision();
			bianhao = serialNumber.getDocumentNumber();
			pcbCheckReport.setSerialNumber(serialNum);
			pcbCheckReport.setRevision(banci);
			pcbCheckReport.setDocumentNumber(bianhao);
		}
		String checkNum = pcbCheckReport.getCheckNum();
		PcbCheckOrder pcbCheckOrder = pcbCheckOrderMapper.selectByCheckNum(checkNum);
		if (pcbCheckOrder == null) {
			pcbCheckOrder = new PcbCheckOrder();
		}
		pcbCheckReport.setTemplateId(templateId);
		PcbReportTemplate pcbReportTemplate = cacheService.getPcbReportTemplate(templateId);
		String orderNumber = pcbCheckReport.getOrderNumber();
		PcbInStorageOrder pcbInStorageOrder = new PcbInStorageOrder();
		if (orderNumber != null) {
			pcbInStorageOrder = pcbInStorageOrderMapper.selectByOrderNumber(orderNumber);
			if (pcbInStorageOrder == null) {
				pcbInStorageOrder = new PcbInStorageOrder();
			}
		}
		final SurfaceProcess surfaceProcess = cacheService.getSurfaceProcess(productOrder.getSurfaceProcessId());
		if (surfaceProcess != null) {
			reportDetail.getSurface_process().setCheckRequire(surfaceProcess.getProcessName());
		}
		// 基材
		String base = null;
		List<ProductBasemAterial> productBasemAterials = productBasemAterialMapper.selectByOrderNum(orderNum);
		List<Integer> baseIDs = new ArrayList<Integer>();
		for (ProductBasemAterial productBasemAterial : productBasemAterials) {
			Integer basemAterial = productBasemAterial.getBasemAterialId();
			baseIDs.add(basemAterial);
		}
		if (!baseIDs.isEmpty()) {
			List<BasemAterial> basemAterials = basemAterialMapper.listByInnerIds(baseIDs);
			for (BasemAterial basemAterial : basemAterials) {
				if (StringUtil.isEmpty(base)) {
					base = basemAterial.getBasemAterialName();
				} else {
					base = base + "," + basemAterial.getBasemAterialName();
				}
			}
		}
		// 基材检验要求可编辑
		// reportDetail.getBase_material_types().setCheckRequire(base);
		// reportDetail.getBase_material_appearance().setCheckRequire(pcbReportTemplate.getBaseMaterialAppearance());
		// reportDetail.getConductive_pattern().setCheckRequire(pcbReportTemplate.getConductivePattern());
		// reportDetail.getPrevent_smt_appearance().setCheckRequire(pcbReportTemplate.getPreventSmtAppearance());
		// reportDetail.getCharacter_appearance().setCheckRequire(pcbReportTemplate.getCharacterAppearance());
		// reportDetail.getExternal_coating_adhesion().setCheckRequire(pcbReportTemplate.getExternalCoatingAdhesion());
		// reportDetail.getPrevent_smt_character_adhesion()
		// .setCheckRequire(pcbReportTemplate.getPreventSmtCharacterAdhesion());
		// reportDetail.getSolderability().setCheckRequire(pcbReportTemplate.getSolderability());
		// reportDetail.getAperture().setCheckRequire(pcbReportTemplate.getAperture());
		// reportDetail.getV_cut().setCheckRequire(pcbReportTemplate.getSpecialDimension());
		// reportDetail.getCircuit_connectivity().setCheckRequire(pcbReportTemplate.getCircuit());
		// reportDetail.getCircuit_insulativity().setCheckRequire(pcbReportTemplate.getCircuit());
		// reportDetail.getSpecial_impedance().setCheckRequire(pcbReportTemplate.getSpecialImpedance());
		List<String> joinOrderCodes = new ArrayList<String>();
		String orderType = pcbCheckReport.getOrderType();
		if (orderType != null) {
			if (orderType.equals("JoinBoardOrder")) {
				joinOrderCodes.add(pcbCheckReport.getJoinBoardOrderCode());
			}
		}
		if (joinOrderCodes.size() == 0) {
			joinOrderCodes = null;
		}
		List<JoinBoardOrder> joinBoardOrders = joinBoardOrderMapper.listByCodes(joinOrderCodes);
		JoinBoardOrder joinBoardOrder = null;
		if (joinBoardOrders != null && joinBoardOrders.size() != 0) {
			joinBoardOrder = joinBoardOrders.get(0);
		}
		// 接受出货检验报告
		ReportIntro reportIntro = reportDetail.getReportIntro();
		if (reportIntro != null) {
			PcbReportIntro pcbReportIntro = pcbReportIntroMapper.selectByReportNum(reportNum);
			boolean insert = true;
			if (pcbReportIntro == null) {
				pcbReportIntro = new PcbReportIntro();
			} else {
				insert = false;
			}
			pcbReportIntro.setAudit(reportIntro.getAudit());
			pcbReportIntro.setBoardName(pcbCheckReport.getBoardName());
			String dispatchDateStr = reportIntro.getDispatchDate();
			pcbReportIntro.setDispatchDate(DateTimeUtil.dateStr2date(dispatchDateStr));
			pcbReportIntro.setGuestCode(productOrder.getGuestCode());
			pcbReportIntro.setNewDateKey(reportIntro.getNewDateKey());
			pcbReportIntro.setNewDateValue(reportIntro.getNewDateValue());
			pcbReportIntro.setNumber(reportIntro.getNumber());
			pcbReportIntro.setProductPeriod(reportIntro.getProductPeriod());
			pcbReportIntro.setUnit(reportIntro.getUnit());
			pcbReportIntro.setOrderNum(orderNum);
			pcbReportIntro.setReportNum(reportNum);
			pcbReportIntro.setSerialNumber(reportIntro.getSerialNumber());
			pcbReportIntro.setAcceptanceStandard(pcbReportTemplate.getTemplateName());
			if (insert) {
				pcbReportIntro.setCreatTime(now);
				pcbReportIntroMapper.insert(pcbReportIntro);
			} else {
				pcbReportIntroMapper.updateByPrimaryKey(pcbReportIntro);
			}
		}
		// 孔径检测
		List<ReportApertureCheck> reportApertureChecks = reportDetail.getReportApertureChecks();
		if (reportApertureChecks != null && !reportApertureChecks.isEmpty()) {
			List<PcbReportApertureCheck> pcbReportApertureChecks = pcbReportApertureCheckMapper
					.selectByReportNum(reportNum);
			if (pcbReportApertureChecks != null && pcbReportApertureChecks.size() != 0) {
				List<Integer> ids = new ArrayList<Integer>();
				for (PcbReportApertureCheck pcbReportApertureCheck : pcbReportApertureChecks) {
					ids.add(pcbReportApertureCheck.getId());
				}
				for (Integer integer : ids) {
					pcbReportApertureCheckMapper.deleteByPrimaryKey(integer);
				}
			}
			for (ReportApertureCheck reportApertureCheck : reportApertureChecks) {
				PcbReportApertureCheck pcbReportApertureCheck = new PcbReportApertureCheck();
				pcbReportApertureCheck.setCheckRequire(reportApertureCheck.getRequire());
				pcbReportApertureCheck.setCheckRequireTolerance(reportApertureCheck.getRequireTolerance());
				pcbReportApertureCheck.setOrderNum(orderNum);
				pcbReportApertureCheck.setApertureId(reportApertureCheck.getID());
				pcbReportApertureCheck.setPn(reportApertureCheck.getpN());
				pcbReportApertureCheck.setRealCheck(reportApertureCheck.getRealCheck());
				pcbReportApertureCheck.setReportNum(reportNum);
				pcbReportApertureCheck.setJudge(PcbmisUtil.judge2int(reportApertureCheck.getJudge()));
				pcbReportApertureCheckMapper.insertSelective(pcbReportApertureCheck);
			}
		}
		// 特殊尺寸
		ReportSpecialDimension reportSpecialDimension = reportDetail.getReportSpecialDimension();
		if (reportSpecialDimension != null) {
			// 删除原有数据
			pcbReportSpecialDimensionMapper.deleteByReportNum(reportNum);
			// ----------
			ReportSpecialDimensionItem vCutResidualThickness = reportSpecialDimension.getvCutResidualThickness();
			this.insertReportSpecial(orderNumber, reportNum, vCutResidualThickness, now,
					ReportSpecialDimensionEnum.V_CUT_RESIDUAL_THICKNESS.getCode());
			ReportSpecialDimensionItem vCutDeviation = reportSpecialDimension.getvCutDeviation();
			this.insertReportSpecial(orderNumber, reportNum, vCutDeviation, now,
					ReportSpecialDimensionEnum.V_CUT_DEVIATION.getCode());
			ReportSpecialDimensionItem goldfingerBevelDepth = reportSpecialDimension.getGoldfingerBevelDepth();
			this.insertReportSpecial(orderNumber, reportNum, goldfingerBevelDepth, now,
					ReportSpecialDimensionEnum.GOLDFINGER_BEVEL_DEPTH.getCode());
			ReportSpecialDimensionItem goldfingerResidualThickness = reportSpecialDimension
					.getGoldfingerResidualThickness();
			this.insertReportSpecial(orderNumber, reportNum, goldfingerResidualThickness, now,
					ReportSpecialDimensionEnum.GOLDFINGER_RESIDUAL_THICKNESS.getCode());
			ReportSpecialDimensionItem stepWidth = reportSpecialDimension.getStepWidth();
			this.insertReportSpecial(orderNumber, reportNum, stepWidth, now,
					ReportSpecialDimensionEnum.STEP_WIDTH.getCode());
			ReportSpecialDimensionItem stepResidualThickness = reportSpecialDimension.getStepResidualThickness();
			this.insertReportSpecial(orderNumber, reportNum, stepResidualThickness, now,
					ReportSpecialDimensionEnum.STEP_RESIDUAL_THICKNESS.getCode());
			ReportSpecialDimensionItem steppedHoleAperture = reportSpecialDimension.getSteppedHoleAperture();
			this.insertReportSpecial(orderNumber, reportNum, steppedHoleAperture, now,
					ReportSpecialDimensionEnum.STEPPED_HOLE_APERTURE.getCode());
			ReportSpecialDimensionItem steppedHoleHoleDepth = reportSpecialDimension.getSteppedHoleHoleDepth();
			this.insertReportSpecial(orderNumber, reportNum, steppedHoleHoleDepth, now,
					ReportSpecialDimensionEnum.STEPPED_HOLE_HOLE_DEPTH.getCode());
			// ---------------
			List<ReportSpecialDimensionItem> reportSpecialDimensionItems = reportSpecialDimension.getRests();
			if (reportSpecialDimensionItems != null && !reportSpecialDimensionItems.isEmpty()) {
				for (ReportSpecialDimensionItem reportSpecialDimensionItem : reportSpecialDimensionItems) {
					this.insertReportSpecial(orderNumber, reportNum, reportSpecialDimensionItem, now, null);
				}
			}
		}
		// V_cut检验
		ReportVcut reportVcut = reportDetail.getReportVcut();
		if (reportVcut != null) {
			// 删除原有数据
			pcbReportVcutMapper.deleteByReportNum(reportNum);
			// -----------
			PcbReportVcut pcbReportVcut = new PcbReportVcut();
			pcbReportVcut.setReportNum(reportNum);
			pcbReportVcut.setOrderNum(orderNum);
			pcbReportVcut.setCeateTime(now);
			pcbReportVcut.setRequireAngle(reportVcut.getRequireAngle());
			pcbReportVcut.setRequireH(reportVcut.getRequireH());
			pcbReportVcut.setRequireB(reportVcut.getRequireB());
			pcbReportVcut.setRequireBtolerance(reportVcut.getRequireBtolerance());
			pcbReportVcut.setRealCheakAngle(reportVcut.getRealCheakAngle());
			pcbReportVcut.setRealCheckH(reportVcut.getRealCheckH());
			pcbReportVcut.setRealCheckB(reportVcut.getRealCheckB());
			pcbReportVcut.setJudgeAngle(PcbmisUtil.judge2int(reportVcut.getJudgeAngle()));
			pcbReportVcut.setJudgeH(PcbmisUtil.judge2int(reportVcut.getJudgeH()));
			pcbReportVcut.setJudgeB(PcbmisUtil.judge2int(reportVcut.getJudgeB()));
			pcbReportVcutMapper.insertSelective(pcbReportVcut);
		}
		// 完整性报告
		ReportIntegrity reportIntegrity = reportDetail.getReportIntegrity();
		if (reportIntegrity != null) {
			PcbReportIntegrity pcbReportIntegrity = pcbReportIntegrityMapper.selectByReportNum(reportNum);
			boolean insert = true;
			if (pcbReportIntegrity != null) {
				insert = false;
			} else {
				pcbReportIntegrity = new PcbReportIntegrity();
			}
			pcbReportIntegrity.setCheckInstrument(reportIntegrity.getCheckInstrument());
			pcbReportIntegrity.setCheckPointNum(reportIntegrity.getCheckPointNum());
			pcbReportIntegrity.setCheckVoltage(reportIntegrity.getCheckVoltage());
			pcbReportIntegrity.setConnectedNetNum(reportIntegrity.getConnectedNetNum());
			pcbReportIntegrity.setConnectedResistance(reportIntegrity.getConnectedResistance());
			pcbReportIntegrity.setInsulatedResistance(reportIntegrity.getInsulatedResistance());
			pcbReportIntegrity.setNetNum(reportIntegrity.getNetNum());
			pcbReportIntegrity.setOrderNum(orderNum);
			pcbReportIntegrity.setReportNum(reportNum);
			pcbReportIntegrity.setResult(reportIntegrity.getResult());
			if (insert) {
				pcbReportIntegrity.setCreatTime(now);
				pcbReportIntegrityMapper.insertSelective(pcbReportIntegrity);
			} else {
				pcbReportIntegrityMapper.updateByPrimaryKeySelective(pcbReportIntegrity);
			}
		}
		// 特性阻抗-单端
		List<ReportSpecialImpedance> single_ended_impedances = reportDetail.getSingle_ended_impedances();
		if (single_ended_impedances != null && single_ended_impedances.size() != 0) {
			List<PcbReportSpecialImpedance> pcbReportSpecialImpedances = pcbReportSpecialImpedanceMapper
					.selectByReportNumAndId(reportNum, 1);
			if (pcbReportSpecialImpedances != null && pcbReportSpecialImpedances.size() != 0) {
				List<Integer> ids = new ArrayList<Integer>();
				for (PcbReportSpecialImpedance pcbReportSpecialImpedance : pcbReportSpecialImpedances) {
					ids.add(pcbReportSpecialImpedance.getId());
				}
				for (Integer integer : ids) {
					pcbReportSpecialImpedanceMapper.deleteByPrimaryKey(integer);
				}
			}
			for (ReportSpecialImpedance reportSpecialImpedance : single_ended_impedances) {
				PcbReportSpecialImpedance pcbReportSpecialImpedance = new PcbReportSpecialImpedance();
				pcbReportSpecialImpedance.setCheckRequire(reportSpecialImpedance.getRequire());
				pcbReportSpecialImpedance.setCheckRequireTolerance(reportSpecialImpedance.getRequireTolerance());
				pcbReportSpecialImpedance.setCreatTime(now);
				pcbReportSpecialImpedance.setJudge(PcbmisUtil.judge2int(reportSpecialImpedance.getJudge()));
				pcbReportSpecialImpedance.setLayer(reportSpecialImpedance.getLayer());
				pcbReportSpecialImpedance.setLayerNum(reportSpecialImpedance.getLayerNum());
				pcbReportSpecialImpedance.setOrderNum(orderNum);
				pcbReportSpecialImpedance.setRealCheck(reportSpecialImpedance.getRealCheck());
				pcbReportSpecialImpedance.setReportNum(reportNum);
				pcbReportSpecialImpedance.setSpecialId(1);
				pcbReportSpecialImpedanceMapper.insertSelective(pcbReportSpecialImpedance);
			}
		}
		// 特性阻抗-差分
		List<ReportSpecialImpedance> differential_characteristic_impedance = reportDetail
				.getDifferential_characteristic_impedance();
		if (differential_characteristic_impedance != null && differential_characteristic_impedance.size() != 0) {
			List<PcbReportSpecialImpedance> pcbReportSpecialImpedances = pcbReportSpecialImpedanceMapper
					.selectByReportNumAndId(reportNum, 2);
			if (pcbReportSpecialImpedances != null && pcbReportSpecialImpedances.size() != 0) {
				List<Integer> ids = new ArrayList<Integer>();
				for (PcbReportSpecialImpedance pcbReportSpecialImpedance : pcbReportSpecialImpedances) {
					ids.add(pcbReportSpecialImpedance.getId());
				}
				for (Integer integer : ids) {
					pcbReportSpecialImpedanceMapper.deleteByPrimaryKey(integer);
				}
			}
			for (ReportSpecialImpedance reportSpecialImpedance : differential_characteristic_impedance) {
				PcbReportSpecialImpedance pcbReportSpecialImpedance = new PcbReportSpecialImpedance();
				pcbReportSpecialImpedance.setCheckRequire(reportSpecialImpedance.getRequire());
				pcbReportSpecialImpedance.setCheckRequireTolerance(reportSpecialImpedance.getRequireTolerance());
				pcbReportSpecialImpedance.setCreatTime(now);
				pcbReportSpecialImpedance.setJudge(PcbmisUtil.judge2int(reportSpecialImpedance.getJudge()));
				pcbReportSpecialImpedance.setLayer(reportSpecialImpedance.getLayer());
				pcbReportSpecialImpedance.setLayerNum(reportSpecialImpedance.getLayerNum());
				pcbReportSpecialImpedance.setOrderNum(orderNum);
				pcbReportSpecialImpedance.setRealCheck(reportSpecialImpedance.getRealCheck());
				pcbReportSpecialImpedance.setReportNum(reportNum);
				pcbReportSpecialImpedance.setSpecialId(2);
				pcbReportSpecialImpedanceMapper.insertSelective(pcbReportSpecialImpedance);
			}
		}
		// 合格证
		ReportCertification reportCertification = reportDetail.getReportCertification();
		if (reportCertification != null) {
			PcbReportCertification pcbReportCertification = pcbReportCertificationMapper.selectByReportNum(reportNum);
			boolean insert = true;
			if (pcbReportCertification != null) {
				insert = false;
			} else {
				pcbReportCertification = new PcbReportCertification();
			}
			pcbReportCertification.setBatchNumber(reportCertification.getBatchNumber());
			pcbReportCertification.setBoardName(pcbCheckReport.getBoardName());
			final Dictionary dictGrade = cacheService.getDictionary(DictionaryEnum.CATEGORY_GRADE,
					productOrder.getCategoryGradeId());
			if (dictGrade != null) {
				pcbReportCertification.setCategoryGrade(dictGrade.getValueStr());
			}
			pcbReportCertification.setCheckDate(pcbCheckOrder.getCheckDate());
			pcbReportCertification.setExpirationDate(reportCertification.getExpirationDate());
			if (reportCertification.getOrderNum() != null) {
				pcbReportCertification.setOrderNum(reportCertification.getOrderNum());
			} else {
				pcbReportCertification.setOrderNum(orderNum);
			}
			pcbReportCertification.setOrderNumName(reportCertification.getOrderNumName());
			pcbReportCertification.setPackagingDate(DateTimeUtil.dateStr2date(reportCertification.getPackagingDate()));
			pcbReportCertification.setProductDate(productOrder.getProductDate());
			pcbReportCertification.setReportNum(reportNum);
			if (joinBoardOrder != null) {
				pcbReportCertification.setSize(joinBoardOrder.getJoinBoardLong() + "*"
						+ joinBoardOrder.getJoinBoardWide() + "*" + productOrder.getBoardPly());
			} else {
				pcbReportCertification.setSize(productOrder.getBoardLong() + "*" + productOrder.getBoardWide() + "*"
						+ productOrder.getBoardPly());
			}
			pcbReportCertification.setNumberPcs(reportCertification.getNumberPcs());
			if (insert) {
				pcbReportCertification.setCreatTime(now);
				pcbReportCertificationMapper.insertSelective(pcbReportCertification);
			} else {
				// 删除原多余的行
				pcbReportCertificationExtraMapper.deleteByReportNum(reportNum);
				pcbReportCertificationMapper.updateByPrimaryKeySelective(pcbReportCertification);
			}
			// 插入新增行
			List<CertificationExtra> add = reportCertification.getAdd();
			if (add != null && !add.isEmpty()) {
				List<PcbReportCertificationExtra> extras = new ArrayList<>();
				for (CertificationExtra certificationExtra : add) {
					PcbReportCertificationExtra extra = new PcbReportCertificationExtra();
					extra.setOrderNum(orderNum);
					extra.setReportNum(reportNum);
					extra.setKey(certificationExtra.getKey());
					extra.setValue(certificationExtra.getValue());
					extras.add(extra);
				}
				pcbReportCertificationExtraMapper.insertBatch(extras);
			}
		}
		// 检验内容
		Map<String, ReportItem> reportItemMap = new HashMap<String, ReportItem>();
		ReportItem base_material_types = reportDetail.getBase_material_types();
		reportItemMap.put("base_material_types", base_material_types);
		ReportItem base_material_appearance = reportDetail.getBase_material_appearance();
		reportItemMap.put("base_material_appearance", base_material_appearance);
		ReportItem conductive_pattern = reportDetail.getConductive_pattern();
		reportItemMap.put("conductive_pattern", conductive_pattern);
		ReportItem prevent_smt_type = reportDetail.getPrevent_smt_type();
		reportItemMap.put("prevent_smt_type", prevent_smt_type);
		ReportItem prevent_smt_appearance = reportDetail.getPrevent_smt_appearance();
		reportItemMap.put("prevent_smt_appearance", prevent_smt_appearance);
		ReportItem character_type = reportDetail.getCharacter_type();
		reportItemMap.put("character_type", character_type);
		ReportItem character_appearance = reportDetail.getCharacter_appearance();
		reportItemMap.put("character_appearance", character_appearance);
		ReportItem surface_process = reportDetail.getSurface_process();
		reportItemMap.put("surface_process", surface_process);
		ReportItem regular_label = reportDetail.getRegular_label();
		reportItemMap.put("regular_label", regular_label);
		ReportItem produce_period = reportDetail.getProduce_period();
		reportItemMap.put("produce_period", produce_period);
		ReportItem external_coating_adhesion = reportDetail.getExternal_coating_adhesion();
		reportItemMap.put("external_coating_adhesion", external_coating_adhesion);
		ReportItem prevent_smt_character_adhesion = reportDetail.getPrevent_smt_character_adhesion();
		reportItemMap.put("prevent_smt_character_adhesion", prevent_smt_character_adhesion);
		ReportItem min_line_width = reportDetail.getMin_line_width();
		reportItemMap.put("min_line_width", min_line_width);
		ReportItem min_line_distance = reportDetail.getMin_line_distance();
		reportItemMap.put("min_line_distance", min_line_distance);
		ReportItem min_annular_ring = reportDetail.getMin_annular_ring();
		reportItemMap.put("min_annular_ring", min_annular_ring);
		ReportItem board_ply = reportDetail.getBoard_ply();
		reportItemMap.put("board_ply", board_ply);
		ReportItem warping_degree = reportDetail.getWarping_degree();
		reportItemMap.put("warping_degree", warping_degree);
		ReportItem board_long = reportDetail.getBoard_long();
		reportItemMap.put("board_long", board_long);
		ReportItem board_wide = reportDetail.getBoard_wide();
		reportItemMap.put("board_wide", board_wide);
		ReportItem aperture = reportDetail.getAperture();
		reportItemMap.put("aperture", aperture);
		ReportItem v_cut = reportDetail.getV_cut();
		reportItemMap.put("v_cut", v_cut);
		ReportItem circuit_connectivity = reportDetail.getCircuit_connectivity();
		reportItemMap.put("circuit_connectivity", circuit_connectivity);
		ReportItem circuit_insulativity = reportDetail.getCircuit_insulativity();
		reportItemMap.put("circuit_insulativity", circuit_insulativity);
		ReportItem special_impedance = reportDetail.getSpecial_impedance();
		reportItemMap.put("special_impedance", special_impedance);
		ReportItem solderability = reportDetail.getSolderability();
		reportItemMap.put("solderability", solderability);
		ReportItem microsectioning = reportDetail.getMicrosectioning();
		reportItemMap.put("microsectioning", microsectioning);
		List<PcbReportDetail> pcbReportDetails = pcbReportDetailMapper.selectByReportNum(reportNum);
		Map<String, PcbReportDetail> optionMap = new HashMap<String, PcbReportDetail>();
		try {
			optionMap = DataUtil.list2map(pcbReportDetails, PcbReportDetail.class, "optionName");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage());
		}
		if (pcbReportDetails != null && pcbReportDetails.size() != 0) {
			for (String key : reportItemMap.keySet()) {
				ReportItem reportItem = reportItemMap.get(key);
				PcbReportDetail pcbReportDetail = optionMap.get(key);
				if (pcbReportDetail == null || reportItem == null) {
					continue;
				}
				if (StringUtils.equals(reportItem.getCheckRequire(), pcbReportDetail.getCheckRequire())
						&& StringUtils.equals(reportItem.getCheckResult(), pcbReportDetail.getCheckResult())
						&& StringUtils.equals(reportItem.getBoardTolerance(), pcbReportDetail.getBoardTolerance())
						&& DataUtil.equals(PcbmisUtil.judge2int(reportItem.getJudgeResult()),
								pcbReportDetail.getJudgeResult())) {
					continue;
				}
				pcbReportDetail.setCheckRequire(reportItem.getCheckRequire());
				pcbReportDetail.setCheckResult(reportItem.getCheckResult());
				pcbReportDetail.setJudgeResult(PcbmisUtil.judge2int(reportItem.getJudgeResult()));
				if (Objects.equals(key, "board_long") || Objects.equals(key, "board_wide")) {
					pcbReportDetail.setBoardTolerance(reportItem.getBoardTolerance());
				}
				pcbReportDetail.setOptionName(key);
				pcbReportDetail.setOrderNum(orderNum);
				pcbReportDetail.setReportNum(reportNum);
				pcbReportDetailMapper.updateByPrimaryKeySelective(pcbReportDetail);
			}
		} else {
			for (String key : reportItemMap.keySet()) {
				ReportItem reportItem = reportItemMap.get(key);
				PcbReportDetail pcbReportDetail = new PcbReportDetail();
				if (reportItem != null) {
					pcbReportDetail.setCheckRequire(reportItem.getCheckRequire());
					pcbReportDetail.setCheckResult(reportItem.getCheckResult());
					pcbReportDetail.setJudgeResult(PcbmisUtil.judge2int(reportItem.getJudgeResult()));
					if (Objects.equals(key, "board_long") || Objects.equals(key, "board_wide")) {
						pcbReportDetail.setBoardTolerance(reportItem.getBoardTolerance());
					}
				}
				pcbReportDetail.setOptionName(key);
				pcbReportDetail.setCreatTime(now);
				pcbReportDetail.setOrderNum(orderNum);
				pcbReportDetail.setReportNum(reportNum);
				pcbReportDetailMapper.insertSelective(pcbReportDetail);
			}
		}
		// 更新note信息
		PcbReportNoteBean pcbReportNote = reportDetail.getNote();
		if (pcbCheckOrder != null) {
			pcbCheckReport.setNote(pcbReportNote.getNote());
			pcbCheckReport.setSupplierMistakeNote(pcbReportNote.getSupplierMistakeNote());
		}
		String userCode = null;
		String userName = null;
		if (wxtbUser != null) {
			userCode = wxtbUser.getUserCode();
			userName = wxtbUser.getUsername();
		}
		pcbCheckReport.setReportMakerCode(userCode);
		pcbCheckReport.setMakeTime(now);
		pcbCheckReport.setStatus(ReportStateEnum.TO_PROVE.getDescr());
		pcbCheckReport.setStatusId(ReportStateEnum.TO_PROVE.getCode());
		pcbCheckReportMapper.updateByPrimaryKeySelective(pcbCheckReport);
		supportService.doOperateLog("提交", orderNum, "编辑报告", userName);
		return ErrorCodeEnum.SUCCESS;
	}

	@Override
	public synchronized Integer judgeReportPass(AkAuthUser wxtbUser, String reportNum, Integer pass) {
		PcbCheckReport pcbCheckReport = pcbCheckReportMapper.selectByReportNum(reportNum);
		if (pcbCheckReport == null) {
			return 0;
		}
		Date date = new Date();
		String userCode = null;
		String userName = null;
		if (wxtbUser != null) {
			userCode = wxtbUser.getUserCode();
			userName = wxtbUser.getUsername();
		}
		if (pcbCheckReport.getStatusId() != ReportStateEnum.PROVING.getCode()) {
			return 2;
		}
		pcbCheckReport.setReportAuditorCode(userCode);
		if (pass == 1) {
			// 如果通过，状态变为已完成
			pcbCheckReport.setReportAuditorCode(userCode);
			pcbCheckReport.setStatus(ReportStateEnum.COMPLETE.getDescr());
			pcbCheckReport.setStatusId(ReportStateEnum.COMPLETE.getCode());
			pcbCheckReport.setAuditDate(date);
		} else if (pass == 0) {
			// 如果不通过，状态变为编辑中
			pcbCheckReport.setReportAuditorCode(userCode);
			pcbCheckReport.setStatus(ReportStateEnum.EDITING.getDescr());
			pcbCheckReport.setStatusId(ReportStateEnum.EDITING.getCode());
		}
		pcbCheckReportMapper.updateByPrimaryKeySelective(pcbCheckReport);
		if (pass == 1) {
			supportService.doOperateLog("审核", pcbCheckReport.getOrderNum(), "报告审核通过", userName);
		} else {
			supportService.doOperateLog("审核", pcbCheckReport.getOrderNum(), "报告审核不通过", userName);
		}
		return 1;
	}

	@Override
	public Map<String, Object> getPreCheckAndAudit(AkAuthUser wxtbUser, String productNum, Integer stateId,
			Integer page, Integer pageSize) {
		String userCode = wxtbUser.getUserCode();
		Integer total = 0;
		Map<String, Object> response = new HashMap<String, Object>();
		Integer start = pageSize * (page - 1);
		List<PcbCheckReport> pcbCheckReports = null;
		if (stateId == 1) {
			// 检验员，获取状态为待创建；或者状态为编辑中，并且编辑人为自己
			pcbCheckReports = pcbCheckReportMapper.getOperatOrAuditReport(ReportStateEnum.TO_CREATE.getCode(),
					ReportStateEnum.EDITING.getCode(), productNum, userCode, null, start, pageSize);
			total = pcbCheckReportMapper.getOperatOrAuditReportNum(ReportStateEnum.TO_CREATE.getCode(),
					ReportStateEnum.EDITING.getCode(), productNum, userCode, null);
		} else {
			// 审核员，获取状态为待审核；或者状态为审核中，并且审核人为自己
			pcbCheckReports = pcbCheckReportMapper.getOperatOrAuditReport(ReportStateEnum.TO_PROVE.getCode(),
					ReportStateEnum.PROVING.getCode(), productNum, null, userCode, start, pageSize);
			total = pcbCheckReportMapper.getOperatOrAuditReportNum(ReportStateEnum.TO_PROVE.getCode(),
					ReportStateEnum.PROVING.getCode(), productNum, null, userCode);
		}
		response.put("total", total);
		if (pcbCheckReports == null || pcbCheckReports.size() == 0) {
			response.put("total", 0);
			response.put("rows", null);
			return response;
		}
		List<CheckReportLitter> checkReportLitters = new ArrayList<CheckReportLitter>();
		Set<String> checkNumSet = new HashSet<String>();
		for (PcbCheckReport pcbCheckReport : pcbCheckReports) {
			CheckReportLitter checkReportLitter = new CheckReportLitter();
			checkReportLitter.setReportNum(pcbCheckReport.getReportNum());
			checkReportLitter.setOrderNum(pcbCheckReport.getOrderNum());
			checkReportLitter.setBoardName(pcbCheckReport.getBoardName());
			String checkNum = pcbCheckReport.getCheckNum();
			if (StringUtils.isNotBlank(checkNum)) {
				checkNumSet.add(checkNum);
				checkReportLitter.setCheckNum(checkNum);
			}
			String guestCode = pcbCheckReport.getGuestCode();
			if (guestCode == null) {
				checkReportLitters.add(checkReportLitter);
				continue;
			}
			final EdaGuest guest = cacheService.getGuest(guestCode);
			if (guest != null) {
				checkReportLitter.setGuestName(guest.getShortNameCn());
			}
			// 添加报告拟制人
			String reportorCode = pcbCheckReport.getReportMakerCode();
			if (reportorCode != null) {
				WxtbUser wxtbUserRe = cacheService.getWxtbUser(reportorCode);
				if (wxtbUserRe != null) {
					checkReportLitter.setReportor(wxtbUserRe.getUsername());
				}
			}
			// 添加报告拟制日期
			Date reportDate = pcbCheckReport.getMakeTime();
			String reportDateStr = DateTimeUtil.date2dateStr(reportDate);
			checkReportLitter.setReportDate(reportDateStr);
			// 添加报告单状态
			checkReportLitter.setStatus(pcbCheckReport.getStatus());
			// 添加生产厂家
			FactoryInfo factory = cacheService.getFactory(pcbCheckReport.getFactoryId());
			if (factory != null) {
				checkReportLitter.setFactoryName(factory.getFactoryName());
			}
			// 添加报告审核员
			String auditorCode = pcbCheckReport.getReportAuditorCode();
			if (auditorCode != null) {
				WxtbUser wxtbUserReAu = cacheService.getWxtbUser(auditorCode);
				if (wxtbUserReAu != null) {
					checkReportLitter.setAuditor(wxtbUserReAu.getUsername());
				}
			}
			checkReportLitters.add(checkReportLitter);
		}
		// -----------------
		if (Objects.equals(1, stateId) && !checkNumSet.isEmpty()) {
			List<PcbCheckOrder> pcbCheckOrders = pcbCheckOrderMapper.selectByCheckNums(checkNumSet);
			Map<String, PcbCheckOrder> pcbcheckOrderMap = new HashMap<String, PcbCheckOrder>();
			try {
				pcbcheckOrderMap = DataUtil.list2map(pcbCheckOrders, PcbCheckOrder.class, "checkNum");
			} catch (NoSuchMethodException | SecurityException e) {

			}
			for (CheckReportLitter checkReportLitter : checkReportLitters) {
				String checkNum = checkReportLitter.getCheckNum();
				if (checkNum == null) {
					continue;
				}
				PcbCheckOrder pcbCheckOrder = pcbcheckOrderMap.get(checkNum);
				if (pcbCheckOrder == null) {
					continue;
				}
				// 添加检验日期
				Date checkDate = pcbCheckOrder.getCheckDate();
				String checkDateStr = DateTimeUtil.date2dateStr(checkDate);
				checkReportLitter.setCheckDate(checkDateStr);
				// 添加检验员
				String inspectorCode = pcbCheckOrder.getInspector();
				if (inspectorCode != null) {
					WxtbUser wxtbUserIns = cacheService.getWxtbUser(inspectorCode);
					if (wxtbUser != null) {
						checkReportLitter.setInspector(wxtbUserIns.getUsername());
					}
				}
				Date receive = pcbCheckOrder.getReceiveDate();
				String receiveDate = DateTimeUtil.date2dateStr(receive);
				checkReportLitter.setReceiveDate(receiveDate);
			}
		}
		response.put("rows", checkReportLitters);
		return response;
	}

	public TemplateBaseIdEnum goforPcbCheckTemplate(ProductOrder productOrder) {
		String cate = "";
		Integer cateID = productOrder.getCategoryGradeId();
		if (cateID != null) {
			Dictionary dictionary = cacheService.getDictionary(DictionaryEnum.CATEGORY_GRADE, cateID);
			if (dictionary != null) {
				cate = dictionary.getValueStr();
			}
		}

		String craft = "";
		Integer craftid = productOrder.getCraftId();
		CraftInfo craftInfo = cacheService.getCraftInfo(craftid);
		if (craftInfo != null) {
			craft = craftInfo.getCraftName();
		}
		return PcbmisUtil.goforPcbCheckTemplate(cate, craft);
	}

	public Set<Integer> getUserAuths(String userCode) {
		List<String> roleNames = new ArrayList<String>();
		List<UserRole> userRoles = cacheService.getUserRole(userCode);
		if (userRoles == null || userRoles.size() == 0) {
			return null;
		}
		for (UserRole userRole : userRoles) {
			roleNames.add(userRole.getRoleName());
		}
		Set<Integer> allAuthId = new HashSet<Integer>();
		List<RoleAuth> roleAuths = new ArrayList<RoleAuth>();
		Map<Integer, RoleAuth> role = cacheService.getRoleAuth();
		for (Integer roleAuthId : role.keySet()) {
			roleAuths.add(role.get(roleAuthId));
		}
		for (UserRole userRole : userRoles) {
			for (RoleAuth roleAuth : roleAuths) {
				if (userRole.getId() == roleAuth.getRoleId()) {
					allAuthId.add(roleAuth.getAuthId());
				}
			}
		}
		return allAuthId;
	}

	@Override
	public Map<String, Object> goforCertificateInfo(String checkNum, AkAuthUser wxtbUser) {
		Map<String, Object> response = new HashMap<String, Object>();
		PcbCheckOrder pcbCheckOrder = pcbCheckOrderMapper.selectByCheckNum(checkNum);
		if (pcbCheckOrder == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", ErrorCodeEnum.SYSTEM_ERROR.getDescr());
			return response;
		}
		String inStorageNum = pcbCheckOrder.getOrderNumber();
		List<String> inStorageNums = new ArrayList<String>();
		inStorageNums.add(inStorageNum);
		List<PcbInStorageOrder> pcbInStorageOrders = pcbInStorageOrderMapper.selectByInOrderNums(inStorageNums);
		PcbInStorageOrder pcbInStorageOrder = null;
		if (pcbInStorageOrders == null || pcbInStorageOrders.isEmpty()) {
			pcbInStorageOrder = new PcbInStorageOrder();
		}
		pcbInStorageOrder = pcbInStorageOrders.get(0);
		CertificateInfo certificateInfo = new CertificateInfo();
		certificateInfo.setBatchNumber(pcbInStorageOrder.getBatchNumber());
		List<PcbCheckReport> pcbCheckReports = pcbCheckReportMapper.selectByCheckNum(checkNum);
		if (pcbCheckReports == null || pcbCheckReports.isEmpty()) {
			response.put("result", ErrorCodeEnum.SUCCESS.getCode());
			response.put("certificate", certificateInfo);
			return response;
		}
		PcbCheckReport pcbCheckReport = pcbCheckReports.get(0);
		String reportNum = pcbCheckReport.getReportNum();
		PcbReportCertification pcbReportCertification = pcbReportCertificationMapper.selectByReportNum(reportNum);
		if (pcbReportCertification == null) {
			response.put("result", ErrorCodeEnum.SUCCESS.getCode());
			response.put("certificate", certificateInfo);
			return response;
		}
		Date packageDate = pcbReportCertification.getPackagingDate();
		String packageDateStr = DateTimeUtil.date2dateStr(packageDate);
		certificateInfo.setPackageDate(packageDateStr);
		certificateInfo.setExpirationDate(pcbReportCertification.getExpirationDate());
		certificateInfo.setOrderNumName(pcbReportCertification.getOrderNumName());
		// supportService.doOperateLog("打印", pcbCheckReport.getOrderNum(), "打印合格证",
		// wxtbUser.getUsername());
		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		response.put("certificate", certificateInfo);
		return response;
	}

	private void insertReportSpecial(String orderNum, String reportNum,
			ReportSpecialDimensionItem reportSpecialDimensionItem, Date now, String itemKey) {
		if (reportSpecialDimensionItem != null) {
			if (itemKey == null) {
				itemKey = reportSpecialDimensionItem.getItemName();
			}
			PcbReportSpecialDimension pcbReportSpecialDimension = new PcbReportSpecialDimension();
			pcbReportSpecialDimension.setItemKey(itemKey);
			pcbReportSpecialDimension.setApertureId(reportSpecialDimensionItem.getId());
			pcbReportSpecialDimension.setReportNum(reportNum);
			pcbReportSpecialDimension.setOrderNum(orderNum);
			pcbReportSpecialDimension.setCheckRequire(reportSpecialDimensionItem.getRequire());
			pcbReportSpecialDimension.setUnit(reportSpecialDimensionItem.getUnit());
			pcbReportSpecialDimension.setRealCheck(reportSpecialDimensionItem.getRealCheck());
			pcbReportSpecialDimension.setJudge(PcbmisUtil.judge2int(reportSpecialDimensionItem.getJudge()));
			pcbReportSpecialDimension.setCreatTime(now);
			pcbReportSpecialDimensionMapper.insertSelective(pcbReportSpecialDimension);
		}
	}

	public BoardSize getBoardSize(String checkNum, String orderNum, PcbCheckOrder pcbCheckOrder) {
		ProductOrder productOrder = productOrderMapper.selectByOrderNum(orderNum);
		if (productOrder == null) {
			return null;
		}
		List<String> joinOrderCodes = new ArrayList<String>();
		String orderType = pcbCheckOrder.getOrderType();
		if (orderType != null) {
			if (orderType.equals("JoinBoardOrder")) {
				joinOrderCodes.add(pcbCheckOrder.getJoinBoardOrderCode());
			}
		}
		if (joinOrderCodes.size() == 0) {
			joinOrderCodes = null;
		}
		List<JoinBoardOrder> joinBoardOrders = joinBoardOrderMapper.listByCodes(joinOrderCodes);
		JoinBoardOrder joinBoardOrder = null;
		if (joinBoardOrders != null && joinBoardOrders.size() != 0) {
			joinBoardOrder = joinBoardOrders.get(0);
		}
		BigDecimal boardLongAll = productOrder.getBoardLong();
		BigDecimal boardWideAll = productOrder.getBoardWide();
		if (joinBoardOrder != null) {
			boardLongAll = joinBoardOrder.getJoinBoardLong();
			boardWideAll = joinBoardOrder.getJoinBoardWide();
		}
		BigDecimal lay_height = null;
		if (boardLongAll != null && boardWideAll != null) {
			lay_height = PcbmisUtil.getMax(boardLongAll, boardWideAll).multiply(new BigDecimal(0.0075));
		}
		BigDecimal warpHeightDou = null;
		if (boardLongAll != null && boardWideAll != null) {
			BigDecimal boardLong1 = boardLongAll.multiply(boardLongAll);
			BigDecimal boardWide1 = boardWideAll.multiply(boardWideAll);
			BigDecimal warpHeight1 = boardLong1.add(boardWide1);
			Double warpheigh2 = warpHeight1.doubleValue();
			Double warpHeight = Math.sqrt(warpheigh2) * 0.0075;
			BigDecimal bigDecimal = new BigDecimal(warpHeight);
			warpHeightDou = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		BoardSize boardSize = new BoardSize();
		List<SizeAndWarpingDegree> sizeAndWarpingDegrees = sizeAndWarpingDegreeMapper.selectByCheckNum(checkNum);
		if (sizeAndWarpingDegrees.isEmpty()) {
			return boardSize;
		}
		Integer size = sizeAndWarpingDegrees.size();
		BigDecimal standard = new BigDecimal("0.75");
		if (Objects.equals(1, size)) {
			SizeAndWarpingDegree sizeAndWarpingDegree = sizeAndWarpingDegrees.get(0);
			BigDecimal boardLong = sizeAndWarpingDegree.getBoardLong();
			BigDecimal boardWide = sizeAndWarpingDegree.getBoardWide();
			Double boardPly = sizeAndWarpingDegree.getBoardPly();
			boardSize.setBoardLong(boardLong == null ? "" : boardLong.toString());
			boardSize.setBoardWide(boardWide == null ? "" : boardWide.toString());
			boardSize.setBoardPly(boardPly == null ? "" : boardPly.toString());
			String layHeight = sizeAndWarpingDegree.getLayHeight();
			String warpHeight = sizeAndWarpingDegree.getWarpHeight();
			BigDecimal layHeightDec = null;
			BigDecimal warpHeightDec = null;
			try {
				layHeightDec = new BigDecimal(layHeight);
			} catch (Exception e) {
			}
			try {
				warpHeightDec = new BigDecimal(warpHeight);
			} catch (Exception e) {
			}
			if (layHeightDec != null) {
				
			}
			BigDecimal warpingDegree1 = PcbmisUtil.operate(standard, lay_height, layHeightDec);
			BigDecimal warpingDegree2 = PcbmisUtil.operate(standard, warpHeightDou, warpHeightDec);
			BigDecimal warpingDegreeMin = null;
			BigDecimal warpingDegreeMax = null;
			String warpingDegree = null;
			if (warpingDegree1 != null && warpingDegree2 != null) {
				warpingDegreeMin = warpingDegree1.min(warpingDegree2);
				warpingDegreeMax = warpingDegree1.max(warpingDegree2);
				warpingDegree = warpingDegreeMin.toString() + "~" + warpingDegreeMax.toString();
			} else if (warpingDegree1 != null) {
				warpingDegree = warpingDegree1.toString();
			} else if (warpingDegree2 != null) {
				warpingDegree = warpingDegree2.toString();
			}
			boardSize.setWarpingDegree(warpingDegree);
			return boardSize;
		}
		String boardLongStr = "";
		String boardWideStr = "";
		String boardPlyStr = "";
		String warpingDegree = "";
		BigDecimal boardLongMax = null;
		BigDecimal boardWideMax = null;
		Double boardPlyMax = null;
		BigDecimal warpingDegreeMax = null;
		BigDecimal boardLongMin = null;
		BigDecimal boardWideMin = null;
		Double boardPlyMin = null;
		BigDecimal warpingDegreeMin = null;
		for (int i = 0; i < size; i++) {
			SizeAndWarpingDegree sizeAndWarpingDegree = sizeAndWarpingDegrees.get(i);
			BigDecimal boardLong = sizeAndWarpingDegree.getBoardLong();
			BigDecimal boardWide = sizeAndWarpingDegree.getBoardWide();
			Double boardPly = sizeAndWarpingDegree.getBoardPly();

			String layHeight = sizeAndWarpingDegree.getLayHeight();
			String warpHeight = sizeAndWarpingDegree.getWarpHeight();
			BigDecimal layHeightDec = null;
			BigDecimal warpHeightDec = null;
			BigDecimal warpingDegreeItemMax = null;
			BigDecimal warpingDegreeItemMin = null;
			try {
				layHeightDec = new BigDecimal(layHeight);
			} catch (Exception e) {
			}
			try {
				warpHeightDec = new BigDecimal(warpHeight);
			} catch (Exception e) {
			}
			BigDecimal warpingDegree1 = PcbmisUtil.operate(standard, lay_height, layHeightDec);
			BigDecimal warpingDegree2 = PcbmisUtil.operate(standard, warpHeightDou, warpHeightDec);
			
			if (warpingDegree1 == null) {
				if (warpingDegree2 != null) {
					warpingDegreeItemMax = warpingDegree2;
					warpingDegreeItemMin = warpingDegree2;
				}
			} else if (warpingDegree2 == null) {
				warpingDegreeItemMax = warpingDegree1;
				warpingDegreeItemMin = warpingDegree1;
			} else {
				warpingDegreeItemMax = warpingDegree1.max(warpingDegree2);
				warpingDegreeItemMin = warpingDegree1.min(warpingDegree2);
			}

			if (i == 0) {
				boardLongMax = boardLong;
				boardLongMin = boardLong;
				boardWideMax = boardWide;
				boardWideMin = boardWide;
				boardPlyMax = boardPly;
				boardPlyMin = boardPly;
				warpingDegreeMax = warpingDegreeItemMax;
				warpingDegreeMin = warpingDegreeItemMin;
				continue;
			}
			if (Objects.equals(1, boardLong.compareTo(boardLongMax))) {
				boardLongMax = boardLong;
			}
			if (Objects.equals(-1, boardLong.compareTo(boardLongMin))) {
				boardLongMin = boardLong;
			}
			if (Objects.equals(1, boardWide.compareTo(boardWideMax))) {
				boardWideMax = boardWide;
			}
			if (Objects.equals(-1, boardWide.compareTo(boardWideMin))) {
				boardWideMin = boardWide;
			}
			if (Objects.equals(1, boardPly.compareTo(boardPlyMax))) {
				boardPlyMax = boardPly;
			}
			if (Objects.equals(-1, boardPly.compareTo(boardPlyMin))) {
				boardPlyMin = boardPly;
			}
			if (warpingDegreeMax == null) {
				warpingDegreeMax = warpingDegreeItemMax;
			} else if (warpingDegreeMax != null && warpingDegreeItemMax != null
					) {
				warpingDegreeMax = warpingDegreeItemMax.max(warpingDegreeMax);
			}
			if (warpingDegreeMin == null) {
				warpingDegreeMin = warpingDegreeItemMin;
			}
			if (warpingDegreeMin != null && warpingDegreeItemMin != null
					) {
				warpingDegreeMin = warpingDegreeItemMin.min(warpingDegreeMin);
			}
		}
		boardLongStr = boardLongMin + "-" + boardLongMax;
		boardWideStr = boardWideMin + "-" + boardWideMax;
		boardPlyStr = boardPlyMin + "-" + boardPlyMax;
		if (warpingDegreeMax != null) {
			if (Objects.equals(warpingDegreeMax, warpingDegreeMin)) {
				warpingDegree = warpingDegreeMin.toString();
			} else {
				warpingDegree = warpingDegreeMin + "~" + warpingDegreeMax;
			}
		}
		boardSize.setBoardLong(boardLongStr);
		boardSize.setBoardWide(boardWideStr);
		boardSize.setBoardPly(boardPlyStr);
		boardSize.setWarpingDegree(warpingDegree);
		return boardSize;
	}

}
