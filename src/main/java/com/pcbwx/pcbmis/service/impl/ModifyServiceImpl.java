package com.pcbwx.pcbmis.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.pcbmis.bean.CertificationExtra;
import com.pcbwx.pcbmis.bean.CheckDetail;
import com.pcbwx.pcbmis.bean.CheckItemRequest;
import com.pcbwx.pcbmis.bean.OrderDetail;
import com.pcbwx.pcbmis.bean.ReportApertureCheck;
import com.pcbwx.pcbmis.bean.ReportCertification;
import com.pcbwx.pcbmis.bean.ReportDetail;
import com.pcbwx.pcbmis.bean.ReportIntegrity;
import com.pcbwx.pcbmis.bean.ReportIntro;
import com.pcbwx.pcbmis.bean.ReportItem;
import com.pcbwx.pcbmis.bean.ReportSpecialDimension;
import com.pcbwx.pcbmis.bean.ReportSpecialDimensionItem;
import com.pcbwx.pcbmis.bean.ReportSpecialImpedance;
import com.pcbwx.pcbmis.bean.ReportVcut;
import com.pcbwx.pcbmis.bean.SizeAndWarpDegree;
import com.pcbwx.pcbmis.bean.request.PcbCheckNoteBean;
import com.pcbwx.pcbmis.bean.request.PcbReportNoteBean;
import com.pcbwx.pcbmis.component.CacheService;
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
import com.pcbwx.pcbmis.dao.ProductOrderMapper;
import com.pcbwx.pcbmis.dao.SizeAndWarpingDegreeMapper;
import com.pcbwx.pcbmis.enums.CheckOptionEnum;
import com.pcbwx.pcbmis.enums.DictionaryEnum;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.enums.ReportSpecialDimensionEnum;
import com.pcbwx.pcbmis.enums.ReportTemplateOptionEnum;
import com.pcbwx.pcbmis.model.Dictionary;
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
import com.pcbwx.pcbmis.model.ProductOrder;
import com.pcbwx.pcbmis.model.SizeAndWarpingDegree;
import com.pcbwx.pcbmis.model.SurfaceProcess;
import com.pcbwx.pcbmis.service.ModifyService;
import com.pcbwx.pcbmis.service.OrderService;
import com.pcbwx.pcbmis.service.SupportService;
import com.pcbwx.pcbmis.utils.DataUtil;
import com.pcbwx.pcbmis.utils.DateTimeUtil;
import com.pcbwx.pcbmis.utils.PcbmisUtil;
import com.pcbwx.pcbmis.utils.StringUtil;

@Service("modifyService")
public class ModifyServiceImpl implements ModifyService {

	private static Logger logger = Logger.getLogger(CheckServiceImpl.class);

	@Autowired
	private PcbCheckOrderMapper pcbCheckOrderMapper;
	@Autowired
	private PcbCheckDetailMapper pcbCheckDetailMapper;
	@Autowired
	private SizeAndWarpingDegreeMapper sizeAndWarpingDegreeMapper;
	@Autowired
	private PcbCheckReportMapper pcbCheckReportMapper;
	@Autowired
	private PcbReportDetailMapper pcbReportDetailMapper;
	@Autowired
	private PcbReportApertureCheckMapper pcbReportApertureCheckMapper;
	@Autowired
	private PcbReportCertificationExtraMapper pcbReportCertificationExtraMapper;
	@Autowired
	private PcbReportCertificationMapper pcbReportCertificationMapper;
	@Autowired
	private PcbReportIntegrityMapper pcbReportIntegrityMapper;
	@Autowired
	private PcbReportIntroMapper pcbReportIntroMapper;
	@Autowired
	private PcbReportSpecialDimensionMapper pcbReportSpecialDimensionMapper;
	@Autowired
	private PcbReportSpecialImpedanceMapper pcbReportSpecialImpedanceMapper;
	@Autowired
	private PcbReportVcutMapper pcbReportVcutMapper;
	@Autowired
	private ProductOrderMapper productOrderMapper;
	@Autowired
	private PcbInStorageOrderMapper pcbInStorageOrderMapper;
	@Autowired
	private JoinBoardOrderMapper joinBoardOrderMapper;

	@Autowired
	private OrderService orderService;
	@Autowired
	private SupportService supportService;
	@Autowired
	private CacheService cacheService;

	@Override
	public ErrorCodeEnum modifyCheckOrder(String checkNum, CheckDetail checkDetail, AkAuthUser wxtbUser) {
		PcbCheckOrder pcbCheckOrder = pcbCheckOrderMapper.selectByCheckNum(checkNum);
		if (pcbCheckOrder == null || pcbCheckOrder.getTemplateId() == null) {
			return ErrorCodeEnum.SYSTEM_ERROR;
		}
		Integer templateId = pcbCheckOrder.getTemplateId();
		String orderNum = pcbCheckOrder.getProductOrderNum();
		OrderDetail detail = orderService.selectOrderDetail(checkNum, templateId);
		Map<String, CheckItemRequest> map = new HashMap<String, CheckItemRequest>();
		// 处理提交的数据
		PcbmisUtil.getCheckItem(map, detail, checkDetail);
		// 处理尺寸翘曲度信息
		List<SizeAndWarpDegree> sizeAndWarpDegrees = checkDetail.getSizeAndWarpDegree();
		Date date = new Date();
		List<PcbCheckDetail> pcbCheckDetails = pcbCheckDetailMapper.selectByCheckNumAndOptionName(checkNum, null);
		Map<String, PcbCheckDetail> optionMap = new HashMap<String, PcbCheckDetail>();
		try {
			optionMap = DataUtil.list2map(pcbCheckDetails, PcbCheckDetail.class, "optionName");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage());
		}
		Integer badRecord = 0;
		// 如果原来存储过就更新，否则插入
		String mistakeNote = pcbCheckOrder.getMistakeNote();
		if (mistakeNote == null) {
			mistakeNote = "";
		}
		for (Map.Entry<String, CheckItemRequest> entry : map.entrySet()) {
			String key = entry.getKey();
			CheckItemRequest checkItem = entry.getValue();
			PcbCheckDetail pcbCheckDetail = optionMap.get(key);
			if (pcbCheckDetail == null || checkItem == null) {
				continue;
			}
			// 新数据
			Integer newBadNum = checkItem.getBadNum();
			String newRequire = checkItem.getRequire();
			String newResult = checkItem.getResult();
			Integer newJudgeResult = PcbmisUtil.judge2int(checkItem.getJudgeResult());
			String newBoardTolerance = checkItem.getBoardTolerance();
			// 旧数据
			Integer oldBadNum = pcbCheckDetail.getBadNum();
			String oldRequire = pcbCheckDetail.getCheckRequire();
			String oldResult = pcbCheckDetail.getCheckResult();
			Integer oldJudgeResult = pcbCheckDetail.getJudgeResult();
			String oldBoardTolerance = pcbCheckDetail.getBoardTolerance();
			if (Objects.equals(newBadNum, oldBadNum) && Objects.equals(newRequire, oldRequire)
					&& Objects.equals(newResult, oldResult) && Objects.equals(newJudgeResult, oldJudgeResult)
					&& Objects.equals(newBoardTolerance, oldBoardTolerance)) {
				continue;
			}
			for (CheckOptionEnum checkOptionEnum : CheckOptionEnum.values()) {
				if (!Objects.equals(key, checkOptionEnum.getCode())) {
					continue;
				}
				if (!Objects.equals(newBadNum, oldBadNum)) {
					mistakeNote = mistakeNote + checkOptionEnum.getDesc() + "不良数量由" + oldBadNum + "修改为" + newBadNum
							+ "<br>";
				}
				if (!Objects.equals(newRequire, oldRequire)) {
					mistakeNote = mistakeNote + checkOptionEnum.getDesc() + "检验要求由" + oldRequire + "修改为" + newRequire
							+ "<br>";
				}
				if (!Objects.equals(newResult, oldResult)) {
					mistakeNote = mistakeNote + checkOptionEnum.getDesc() + "检验结果由" + oldResult + "修改为" + newResult
							+ "<br>";
				}
				if (!Objects.equals(newJudgeResult, oldJudgeResult)) {
					mistakeNote = mistakeNote + checkOptionEnum.getDesc() + "检验结果由"
							+ PcbmisUtil.int2judge(oldJudgeResult) + "修改为" + PcbmisUtil.int2judge(newJudgeResult)
							+ "<br>";
				}
			}
			pcbCheckDetail.setCheckRequire(newRequire);
			pcbCheckDetail.setBoardTolerance(newBoardTolerance);
			pcbCheckDetail.setCheckResult(newResult);
			pcbCheckDetail.setBadNum(newBadNum);
			pcbCheckDetail.setJudgeResult(newJudgeResult);
			if (Objects.equals(newJudgeResult, 0)) {
				badRecord = 1;
			}
			if (newJudgeResult != null && newJudgeResult == 1) {
				pcbCheckDetail.setBadNum(null);
			}
			pcbCheckDetail.setUpdateTime(date);
			pcbCheckDetailMapper.updateByPrimaryKey(pcbCheckDetail);
		}
		// 插入日志
		PcbCheckNoteBean noteBean = checkDetail.getNote();
		if (noteBean != null) {
			pcbCheckOrder.setNote(noteBean.getNote());
		}
		// 删除原来的尺寸翘曲度数据
		List<SizeAndWarpingDegree> sizeAndWarpingDegrees = sizeAndWarpingDegreeMapper.selectByCheckNum(checkNum);
		Integer orginalNum = sizeAndWarpingDegrees.size();
		for (SizeAndWarpingDegree sizeAndWarpingDegree : sizeAndWarpingDegrees) {
			sizeAndWarpingDegreeMapper.deleteByPrimaryKey(sizeAndWarpingDegree.getId());
		}
		Integer spotNum = 0;
		if (sizeAndWarpDegrees != null && sizeAndWarpDegrees.size() != 0) {
			spotNum = sizeAndWarpDegrees.size();
			for (SizeAndWarpDegree sizeAndWarpDegree : sizeAndWarpDegrees) {
				SizeAndWarpingDegree sizeAndWarpingDegree = new SizeAndWarpingDegree();
				sizeAndWarpingDegree.setCheckNum(checkNum);
				sizeAndWarpingDegree.setOrderNum(orderNum);
				sizeAndWarpingDegree.setBoardNum(sizeAndWarpDegree.getBoardNum());
				sizeAndWarpingDegree.setBoardLong(PcbmisUtil.double2bigDecimal(sizeAndWarpDegree.getBoardLong()));
				sizeAndWarpingDegree.setBoardWide(PcbmisUtil.double2bigDecimal(sizeAndWarpDegree.getBoardWide()));
				sizeAndWarpingDegree.setBoardPly(sizeAndWarpDegree.getBoardPly());
				String layHeight = sizeAndWarpDegree.getLayHeight();
				String warpHeight = sizeAndWarpDegree.getWarpHeight();
				Integer judge = PcbmisUtil.judge2int((sizeAndWarpDegree.getJudge()));
				if (judge == 0) {
					badRecord = 1;
				}
				sizeAndWarpingDegree.setLayHeight(layHeight);
				sizeAndWarpingDegree.setWarpHeight(warpHeight);
				sizeAndWarpingDegree.setWarpingDegree(sizeAndWarpDegree.getWarpingDegree());
				sizeAndWarpingDegree.setJudge(judge);
				sizeAndWarpingDegree.setCreateTime(date);
				sizeAndWarpingDegreeMapper.insertSelective(sizeAndWarpingDegree);
			}
		}
		if (!Objects.equals(orginalNum, spotNum)) {
			mistakeNote = mistakeNote + "尺寸翘曲度个数由" + orginalNum + "修改为" + spotNum
					+ "<br>";
		}
		if (StringUtil.isEmpty(mistakeNote)) {
			mistakeNote = null;
		}
		pcbCheckOrder.setMistakeNote(mistakeNote);
		pcbCheckOrder.setSpotCheckNumPcs(spotNum);
		pcbCheckOrder.setBadRecord(badRecord);
		pcbCheckOrder.setUpdateTime(null);
		pcbCheckOrderMapper.updateByPrimaryKeySelective(pcbCheckOrder);
		supportService.doOperateLog("修改", orderNum, "修改检验结果", wxtbUser.getUsername());
		return ErrorCodeEnum.SUCCESS;
	}

	@Override
	public ErrorCodeEnum modifyReportOrder(String reportNum, ReportDetail reportDetail, AkAuthUser wxtbUser) {
		PcbCheckReport pcbCheckReport = pcbCheckReportMapper.selectByReportNum(reportNum);
		if (pcbCheckReport == null) {
			return ErrorCodeEnum.SYSTEM_ERROR;
		}
		String mistakeNote = pcbCheckReport.getMistakeNote();
		if (mistakeNote == null) {
			mistakeNote = "";
		}
		Date now = new Date();
		String orderNum = pcbCheckReport.getOrderNum();
		ProductOrder productOrder = productOrderMapper.selectByOrderNum(orderNum);
		if (productOrder == null) {
			return ErrorCodeEnum.SYSTEM_ERROR;
		}
		String checkNum = pcbCheckReport.getCheckNum();
		PcbCheckOrder pcbCheckOrder = pcbCheckOrderMapper.selectByCheckNum(checkNum);
		if (pcbCheckOrder == null) {
			pcbCheckOrder = new PcbCheckOrder();
		}
		PcbReportTemplate pcbReportTemplate = cacheService.getPcbReportTemplate(pcbCheckReport.getTemplateId());
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
//		reportDetail.getBase_material_appearance().setCheckRequire(pcbReportTemplate.getBaseMaterialAppearance());
//		reportDetail.getConductive_pattern().setCheckRequire(pcbReportTemplate.getConductivePattern());
//		reportDetail.getPrevent_smt_appearance().setCheckRequire(pcbReportTemplate.getPreventSmtAppearance());
//		reportDetail.getCharacter_appearance().setCheckRequire(pcbReportTemplate.getCharacterAppearance());
//		reportDetail.getExternal_coating_adhesion().setCheckRequire(pcbReportTemplate.getExternalCoatingAdhesion());
//		reportDetail.getPrevent_smt_character_adhesion()
//				.setCheckRequire(pcbReportTemplate.getPreventSmtCharacterAdhesion());
//		reportDetail.getSolderability().setCheckRequire(pcbReportTemplate.getSolderability());
//		reportDetail.getAperture().setCheckRequire(pcbReportTemplate.getAperture());
//		reportDetail.getV_cut().setCheckRequire(pcbReportTemplate.getSpecialDimension());
//		reportDetail.getCircuit_connectivity().setCheckRequire(pcbReportTemplate.getCircuit());
//		reportDetail.getCircuit_insulativity().setCheckRequire(pcbReportTemplate.getCircuit());
//		reportDetail.getSpecial_impedance().setCheckRequire(pcbReportTemplate.getSpecialImpedance());
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
		PcbReportIntro pcbReportIntro = pcbReportIntroMapper.selectByReportNum(reportNum);
		if (reportIntro != null && pcbReportIntro != null) {
			String dispatchDateStr = reportIntro.getDispatchDate();
			if (!Objects.equals(reportIntro.getNumber(), pcbReportIntro.getNumber())) {
				mistakeNote = mistakeNote + "检验报告数量由" + pcbReportIntro.getNumber() + "修改为" + reportIntro.getNumber()
						+ "<br>";
			}
			if (!Objects.equals(reportIntro.getUnit(), pcbReportIntro.getUnit())) {
				mistakeNote = mistakeNote + "检验报告数量单位由" + pcbReportIntro.getUnit() + "修改为" + reportIntro.getUnit()
						+ "<br>";
			}
			if (!Objects.equals(reportIntro.getProductPeriod(), pcbReportIntro.getProductPeriod())) {
				mistakeNote = mistakeNote + "检验报告生产周期由" + pcbReportIntro.getProductPeriod() + "修改为"
						+ reportIntro.getProductPeriod() + "<br>";
			}
			String oldDispatchDateStr = DateTimeUtil.date2dateStr(pcbReportIntro.getDispatchDate());
			if (!Objects.equals(dispatchDateStr, oldDispatchDateStr)) {
				mistakeNote = mistakeNote + "检验报告出货日期由" + oldDispatchDateStr + "修改为" + dispatchDateStr + "<br>";
			}
			pcbReportIntro.setAudit(reportIntro.getAudit());
			pcbReportIntro.setBoardName(pcbCheckReport.getBoardName());
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
			pcbReportIntro.setUpdateTime(now);
			pcbReportIntroMapper.updateByPrimaryKey(pcbReportIntro);
		}
		// 孔径检测
		List<ReportApertureCheck> reportApertureChecks = reportDetail.getReportApertureChecks();
		if (reportApertureChecks != null && !reportApertureChecks.isEmpty()) {
			List<PcbReportApertureCheck> pcbReportApertureChecks = pcbReportApertureCheckMapper
					.selectByReportNum(reportNum);
//			Integer oldSize = 0;
			if (!pcbReportApertureChecks.isEmpty()) {
				List<Integer> ids = new ArrayList<Integer>();
				for (PcbReportApertureCheck pcbReportApertureCheck : pcbReportApertureChecks) {
					ids.add(pcbReportApertureCheck.getId());
				}
				for (Integer integer : ids) {
					pcbReportApertureCheckMapper.deleteByPrimaryKey(integer);
				}
//				oldSize = ids.size();
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
//			if (!Objects.equals(oldSize, reportApertureChecks.size())) {
//				mistakeNote = mistakeNote + "检验报告孔径检测个数由" + oldSize + "修改为" + reportApertureChecks.size() + "<br>";
//			}
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
		if (!pcbReportDetails.isEmpty()) {
			for (Map.Entry<String, ReportItem> entry : reportItemMap.entrySet()) {
				String key = entry.getKey();
				ReportItem reportItem = entry.getValue();
				PcbReportDetail pcbReportDetail = optionMap.get(key);
				if (pcbReportDetail == null || reportItem == null) {
					continue;
				}
				String newCheckRequire = reportItem.getCheckRequire();
				String newCheckResult = reportItem.getCheckResult();
				String newBoardTolerance = reportItem.getBoardTolerance();
				Integer newJudgeResult = PcbmisUtil.judge2int(reportItem.getJudgeResult());
				String oldCheckRequire = pcbReportDetail.getCheckRequire();
				String oldCheckResult = pcbReportDetail.getCheckResult();
				String oldBoardTolerance = pcbReportDetail.getBoardTolerance();
				Integer oldJudgeResult = pcbReportDetail.getJudgeResult();
				if (Objects.equals(newCheckRequire, oldCheckRequire) && Objects.equals(newCheckResult, oldCheckResult)
						&& Objects.equals(newBoardTolerance, oldBoardTolerance)
						&& Objects.equals(newJudgeResult, oldJudgeResult)) {
					continue;
				}
				for (ReportTemplateOptionEnum reportTemplateOptionEnum : ReportTemplateOptionEnum.values()) {
					if (!Objects.equals(reportTemplateOptionEnum.getCode(), key)) {
						continue;
					}
					if (!Objects.equals(newCheckRequire, oldCheckRequire)) {
						mistakeNote = mistakeNote + reportTemplateOptionEnum.getDesc() + "检验要求由" + oldCheckRequire
								+ "修改为" + newCheckRequire + "<br>";
					}
					if (!Objects.equals(newCheckResult, oldCheckResult)) {
						mistakeNote = mistakeNote + reportTemplateOptionEnum.getDesc() + "检验结果由" + oldCheckResult
								+ "修改为" + newCheckResult + "<br>";
					}
					if (!Objects.equals(newBoardTolerance, oldBoardTolerance)) {
						mistakeNote = mistakeNote + reportTemplateOptionEnum.getDesc() + "公差由" + oldBoardTolerance
								+ "修改为" + newBoardTolerance + "<br>";
					}
					if (!Objects.equals(newJudgeResult, oldJudgeResult)) {
						mistakeNote = mistakeNote + reportTemplateOptionEnum.getDesc() + "检验判定由"
								+ PcbmisUtil.int2judge(oldJudgeResult) + "修改为" + PcbmisUtil.int2judge(newJudgeResult)
								+ "<br>";
					}
				}
				pcbReportDetail.setCheckRequire(newCheckRequire);
				pcbReportDetail.setCheckResult(newCheckResult);
				pcbReportDetail.setJudgeResult(newJudgeResult);
				if (Objects.equals(key, "board_long") || Objects.equals(key, "board_wide")) {
					pcbReportDetail.setBoardTolerance(newBoardTolerance);
				}
				pcbReportDetail.setOptionName(key);
				pcbReportDetail.setOrderNum(orderNum);
				pcbReportDetail.setReportNum(reportNum);
				pcbReportDetailMapper.updateByPrimaryKeySelective(pcbReportDetail);
			}
		}
		// 更新note信息
		pcbCheckReport.setMistakeNote(mistakeNote);
		PcbReportNoteBean pcbReportNote = reportDetail.getNote();
		if (pcbCheckOrder != null) {
			pcbCheckReport.setNote(pcbReportNote.getNote());
			pcbCheckReport.setSupplierMistakeNote(pcbReportNote.getSupplierMistakeNote());
		}
		pcbCheckReportMapper.updateByPrimaryKeySelective(pcbCheckReport);
		supportService.doOperateLog("提交", orderNum, "修改报告", wxtbUser.getUsername());
		return ErrorCodeEnum.SUCCESS;
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

}
