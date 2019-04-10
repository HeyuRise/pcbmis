package com.pcbwx.pcbmis.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.pcbmis.bean.BaseOrderInfo;
import com.pcbwx.pcbmis.bean.BoardToleranceBean;
import com.pcbwx.pcbmis.bean.CheckHead;
import com.pcbwx.pcbmis.bean.CheckInfo;
import com.pcbwx.pcbmis.bean.CheckState;
import com.pcbwx.pcbmis.bean.LatelyOperate;
import com.pcbwx.pcbmis.bean.OrderDetail;
import com.pcbwx.pcbmis.bean.OrderInfo;
import com.pcbwx.pcbmis.bean.SizeAndWarpDegree;
import com.pcbwx.pcbmis.bean.response.CheckItem;
import com.pcbwx.pcbmis.bean.response.PcbCheckNote;
import com.pcbwx.pcbmis.component.CacheService;
import com.pcbwx.pcbmis.dao.BoardToleranceMapper;
import com.pcbwx.pcbmis.dao.FactoryInfoMapper;
import com.pcbwx.pcbmis.dao.JoinBoardOrderMapper;
import com.pcbwx.pcbmis.dao.OperateLogMapper;
import com.pcbwx.pcbmis.dao.PcbCheckDetailMapper;
import com.pcbwx.pcbmis.dao.PcbCheckOrderMapper;
import com.pcbwx.pcbmis.dao.PcbInStorageOrderMapper;
import com.pcbwx.pcbmis.dao.PcbUnqualifiedMapper;
import com.pcbwx.pcbmis.dao.ProductOrderMapper;
import com.pcbwx.pcbmis.dao.SizeAndWarpingDegreeMapper;
import com.pcbwx.pcbmis.enums.CheckStateEnum;
import com.pcbwx.pcbmis.enums.DictionaryEnum;
import com.pcbwx.pcbmis.enums.TemplateBaseIdEnum;
import com.pcbwx.pcbmis.model.BoardTolerance;
import com.pcbwx.pcbmis.model.CompanyInfo;
import com.pcbwx.pcbmis.model.CraftInfo;
import com.pcbwx.pcbmis.model.Dictionary;
import com.pcbwx.pcbmis.model.EdaGuest;
import com.pcbwx.pcbmis.model.FactoryInfo;
import com.pcbwx.pcbmis.model.JoinBoardOrder;
import com.pcbwx.pcbmis.model.OperateLog;
import com.pcbwx.pcbmis.model.PcbCheckDetail;
import com.pcbwx.pcbmis.model.PcbCheckOrder;
import com.pcbwx.pcbmis.model.PcbCheckTemplate;
import com.pcbwx.pcbmis.model.PcbInStorageOrder;
import com.pcbwx.pcbmis.model.PcbUnqualified;
import com.pcbwx.pcbmis.model.ProductOrder;
import com.pcbwx.pcbmis.model.RoleAuth;
import com.pcbwx.pcbmis.model.SizeAndWarpingDegree;
import com.pcbwx.pcbmis.model.SurfaceProcess;
import com.pcbwx.pcbmis.model.UserRole;
import com.pcbwx.pcbmis.model.WxtbUser;
import com.pcbwx.pcbmis.service.OrderService;
import com.pcbwx.pcbmis.service.SupportService;
import com.pcbwx.pcbmis.service.UtilService;
import com.pcbwx.pcbmis.utils.DateTimeUtil;
import com.pcbwx.pcbmis.utils.PcbmisUtil;
import com.pcbwx.pcbmis.utils.StringUtil;

/**
 * 日志接口实现类
 * 
 * @author 王海龙
 *
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

	// private static Logger logger = Logger.getLogger(OrderServiceImpl.class);

	@Autowired
	private ProductOrderMapper productOrderMapper;
	@Autowired
	private PcbCheckOrderMapper pcbCheckOrderMapper;
	@Autowired
	private FactoryInfoMapper factoryInfoMapper;
	@Autowired
	private PcbCheckDetailMapper pcbCheckDetailMapper;
	@Autowired
	private BoardToleranceMapper boardToleranceMapper;
	@Autowired
	private SizeAndWarpingDegreeMapper sizeAndWarpingDegreeMapper;
	@Autowired
	private JoinBoardOrderMapper JoinBoardOrderMapper;
	@Autowired
	private OperateLogMapper operateLogMapper;
	@Autowired
	private PcbInStorageOrderMapper pcbInStorageOrderMapper;
	@Autowired
	private PcbUnqualifiedMapper pcbUnqualifiedMapper;

	@Autowired
	private UtilService utilService;
	@Autowired
	private SupportService supportService;
	@Autowired
	private CacheService cacheService;

	OrderServiceImpl() {
	}

	@Override
	public Map<String, Object> selectPreCheckAndAudit(String userCode, String productNum, Integer checkStartId,
			Integer page, Integer pageSize) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<CheckInfo> orders = new ArrayList<CheckInfo>();
		List<String> orderNums = new ArrayList<String>();
		List<PcbCheckOrder> pcbCheckOrders = null;
		Integer start = (page - 1) * pageSize;
		Integer totalNum = null;
		if (checkStartId == 1) {
			// 检验员，获取待检验状态；或者检验中状态，检验员为自己
			pcbCheckOrders = pcbCheckOrderMapper.selectPreCheck(CheckStateEnum.TO_CHECK.getCode(),
					CheckStateEnum.CHECKING.getCode(), productNum, userCode, null, start, pageSize);
			totalNum = pcbCheckOrderMapper.selectPreCheckNum(CheckStateEnum.TO_CHECK.getCode(),
					CheckStateEnum.CHECKING.getCode(), productNum, userCode, null);
		} else {
			// 审核员，获取待审核状态；或者审核中状态，审核员为自己
			pcbCheckOrders = pcbCheckOrderMapper.selectPreCheck(CheckStateEnum.TO_AUDIT.getCode(),
					CheckStateEnum.AUDITING.getCode(), productNum, null, userCode, start, pageSize);
			totalNum = pcbCheckOrderMapper.selectPreCheckNum(CheckStateEnum.TO_AUDIT.getCode(),
					CheckStateEnum.AUDITING.getCode(), productNum, null, userCode);
		}
		response.put("total", totalNum);
		for (PcbCheckOrder pcbCheckOrder : pcbCheckOrders) {
			String orderNum = pcbCheckOrder.getProductOrderNum();
			orderNums.add(orderNum);
		}
		List<ProductOrder> pcbOrders = new ArrayList<ProductOrder>();
		if (orderNums != null && !orderNums.isEmpty()) {
			pcbOrders = productOrderMapper.listByOrderNum(orderNums);
		}
		Map<String, ProductOrder> productOrderMap = new HashMap<String, ProductOrder>();
		if (pcbOrders != null && pcbOrders.size() != 0) {
			for (ProductOrder productOrder : pcbOrders) {
				productOrderMap.put(productOrder.getOrderNum(), productOrder);
			}
		}
		for (PcbCheckOrder pcbCheckOrder : pcbCheckOrders) {
			ProductOrder productOrder = productOrderMap.get(pcbCheckOrder.getProductOrderNum());
			CheckInfo checkInfo = new CheckInfo();
			checkInfo.init(productOrder, pcbCheckOrder);
			orders.add(checkInfo);
		}
		orders = addAll(pcbCheckOrders, orders);
		response.put("rows", orders);
		return response;
	}

	@Override
	public Map<String, Object> getOrderListByCondition(String orderNum, String guest, Integer factoryId, Integer page,
			Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (orderNum == null || orderNum.equals("")) {
			orderNum = null;
		}
		List<String> edaGuestCode = utilService.getEdaGuest(guest);
		Integer totalNum = productOrderMapper.getOrderNumsByCondition(orderNum, edaGuestCode, factoryId);
		List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
		int start = (page - 1) * pageSize;
		List<ProductOrder> productOrders = productOrderMapper.getOrderListByCondition(orderNum, edaGuestCode, factoryId,
				start, pageSize);
		for (ProductOrder po : productOrders) {
			OrderInfo order = new OrderInfo(po);
			orderInfos.add(order);
		}
		map.put("total", totalNum);
		map.put("rows", orderInfos);
		return map;
	}

	@Override
	public Map<String, Object> getCheckListNew(AkAuthUser wxtbUser, String orderNum, String guest, Integer gradeId,
			Integer content, String boardName, Integer factoryId, Integer statusId, String checkStartStr,
			String checkEndStr, String auditStartStr, String auditEndStr, Integer page, Integer pageSize) {
		Date checkStart = DateTimeUtil.dateStr2date(checkStartStr);
		Date checkEnd = DateTimeUtil.dateStr2date(checkEndStr);
		checkEnd = DateTimeUtil.getTheDayLastTime(checkEnd);
		Date auditStart = DateTimeUtil.dateStr2date(auditStartStr);
		Date auditEnd = DateTimeUtil.dateStr2date(auditEndStr);
		auditEnd = DateTimeUtil.getTheDayLastTime(auditEnd);
		Map<String, Object> response = new HashMap<String, Object>();
		List<String> edaGuestCode = utilService.getEdaGuest(guest);
		int start = (page - 1) * pageSize;
		String contentStr = null;
		if (content != null) {
			contentStr = content.toString();
		}
		Integer total = pcbCheckOrderMapper.getSelectByKeyWordAndOrderNumsNum(orderNum, contentStr, boardName,
				factoryId, statusId, gradeId, checkStart, checkEnd, auditStart, auditEnd, edaGuestCode);
		List<PcbCheckOrder> pcbCheckOrders = pcbCheckOrderMapper.selectByKeyWordAndOrderNums(orderNum, contentStr,
				boardName, factoryId, statusId, gradeId, checkStart, checkEnd, auditStart, auditEnd, edaGuestCode,
				start, pageSize);
		if (pcbCheckOrders == null || pcbCheckOrders.size() == 0) {
			response.put("total", 0);
			response.put("rows", null);
			return response;
		}
		List<String> orderNums = new ArrayList<String>();
		for (PcbCheckOrder pcbCheckOrder : pcbCheckOrders) {
			orderNums.add(pcbCheckOrder.getProductOrderNum());
		}
		List<ProductOrder> productOrders = productOrderMapper.listByOrderNum(orderNums);
		Map<String, ProductOrder> pcbOrders = new HashMap<String, ProductOrder>();
		for (ProductOrder productOrder : productOrders) {
			pcbOrders.put(productOrder.getOrderNum(), productOrder);
		}
		List<CheckInfo> checkInfos = new ArrayList<CheckInfo>();
		String userCode = wxtbUser.getUserCode();
		Set<Integer> authIds = getUserAuths(userCode);
		for (PcbCheckOrder pcbCheckOrder : pcbCheckOrders) {
			ProductOrder productOrder = pcbOrders.get(pcbCheckOrder.getProductOrderNum());
			CheckInfo checkInfo = new CheckInfo();
			checkInfo.init(productOrder, pcbCheckOrder);
			Map<String, Object> checkMap = PcbmisUtil.isOrderCheckable(pcbCheckOrder, authIds, userCode);
			checkInfo.setCheckable((Integer) checkMap.get("checkable"));
			checkInfo.setHref(checkMap.get("href").toString());
			checkInfos.add(checkInfo);
		}
		checkInfos = addAll(pcbCheckOrders, checkInfos);
		response.put("total", total);
		response.put("rows", checkInfos);
		return response;
	}

	@Override
	public Map<String, Object> goForAuditOrder(AkAuthUser wxtbUser, String checkNum, Integer type) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<PcbCheckDetail> pcbCheckDetails = pcbCheckDetailMapper.selectByCheckNumAndOptionName(checkNum, null);
		if (pcbCheckDetails == null || pcbCheckDetails.size() == 0) {
			return response;
		}
		for (PcbCheckDetail pcbCheckDetail : pcbCheckDetails) {
			CheckItem checkItem = new CheckItem();
			checkItem.setRequire(pcbCheckDetail.getCheckRequire());
			checkItem.setBoardTolerance(pcbCheckDetail.getBoardTolerance());
			checkItem.setResult(pcbCheckDetail.getCheckResult());
			checkItem.setBadNum(PcbmisUtil.getBadNum(pcbCheckDetail.getBadNum()));
			checkItem.setJudgeResult(PcbmisUtil.int2judge(pcbCheckDetail.getJudgeResult()));
			response.put(pcbCheckDetail.getOptionName(), checkItem);
		}
		List<SizeAndWarpingDegree> sizeAndWarpingDegrees = sizeAndWarpingDegreeMapper.selectByCheckNum(checkNum);
		List<SizeAndWarpDegree> sizeAndWarpDegrees = new ArrayList<SizeAndWarpDegree>();
		if (sizeAndWarpingDegrees != null && sizeAndWarpingDegrees.size() != 0) {
			for (SizeAndWarpingDegree sizeAndWarpingDegree : sizeAndWarpingDegrees) {
				SizeAndWarpDegree sizeAndWarpDegree = new SizeAndWarpDegree();
				sizeAndWarpDegree.setBoardNum(sizeAndWarpingDegree.getBoardNum());
				sizeAndWarpDegree.setBoardLong(PcbmisUtil.bigDecimal2double(sizeAndWarpingDegree.getBoardLong()));
				sizeAndWarpDegree.setBoardWide(PcbmisUtil.bigDecimal2double(sizeAndWarpingDegree.getBoardWide()));
				sizeAndWarpDegree.setBoardPly(sizeAndWarpingDegree.getBoardPly());
				sizeAndWarpDegree.setLayHeight(sizeAndWarpingDegree.getLayHeight());
				sizeAndWarpDegree.setWarpHeight(sizeAndWarpingDegree.getWarpHeight());
				sizeAndWarpDegree.setWarpingDegree(sizeAndWarpingDegree.getWarpingDegree());
				sizeAndWarpDegree.setJudge(PcbmisUtil.int2judge(sizeAndWarpingDegree.getJudge()));
				sizeAndWarpDegrees.add(sizeAndWarpDegree);
			}
		}
		response.put("sizeAndWarpDegrees", sizeAndWarpDegrees);

		PcbCheckOrder pcbCheckOrder = pcbCheckOrderMapper.selectByCheckNum(checkNum);
		if (pcbCheckOrder == null) {
			return response;
		}
		String orderNum = pcbCheckOrder.getProductOrderNum();
		ProductOrder productOrder = productOrderMapper.selectByOrderNum(orderNum);
		if (productOrder == null) {
			return response;
		}
		// 添加基础信息
		BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
		baseOrderInfo.setCheckNum(checkNum);
		baseOrderInfo.setOrderNum(orderNum);
		baseOrderInfo.setBoardName(pcbCheckOrder.getBoardName());
		EdaGuest guest = cacheService.getGuest(productOrder.getGuestCode());
		if (guest != null) {
			baseOrderInfo.setGuestName(guest.getShortNameCn());
		}
		Date productDate = productOrder.getProductDate();
		if (productDate != null) {
			String proDate = DateTimeUtil.date2dateTimeStr(productDate, "yyyy-MM-dd");
			baseOrderInfo.setProductDate(proDate);
		}
		Dictionary dictGrade = cacheService.getDictionary(DictionaryEnum.CATEGORY_GRADE,
				productOrder.getCategoryGradeId());
		if (dictGrade != null) {
			String categoryGrade = dictGrade.getValueStr();
			baseOrderInfo.setCategoryGrade(categoryGrade);
		}
		Integer craftId = productOrder.getCraftId();
		CraftInfo craftInfo = cacheService.getCraftInfo(craftId);
		if (craftInfo != null) {
			baseOrderInfo.setCraft(craftInfo.getCraftName());
		}
		final FactoryInfo factory = cacheService.getFactory(productOrder.getFactoryId());
		if (factory != null) {
			baseOrderInfo.setFactoryName(factory.getFactoryName());
		}
		Dictionary dictionary = cacheService.getDictionary(DictionaryEnum.CHECK_STANDARD, 1);
		if (dictionary != null) {
			baseOrderInfo.setInspectionSpecification(dictionary.getValueStr());
		}
		// cam处理
		baseOrderInfo.setCAMGuide(productOrder.getCamTip());
		String notes = PcbmisUtil.getProductNote(utilService.getProductNotes(orderNum, 0),
				utilService.getProductNotes(orderNum, 1));
		baseOrderInfo.setProductionNote(notes);
		baseOrderInfo.setDeliveryNote(productOrder.getShipmentsNotes());
		baseOrderInfo.setBusinessNote(productOrder.getBusinessNotes());
		baseOrderInfo.setImpedanceRequire(productOrder.getImpedanceRequire());
		baseOrderInfo.setMetallography_require(productOrder.getMetallographyRequire());
		baseOrderInfo.setProductionNumSet(productOrder.getProductionNumSet());
		baseOrderInfo.setProductionNumPcs(productOrder.getProductionNumPcs());
		baseOrderInfo.setAmountCheckoutPcs(pcbCheckOrder.getInAmountPcs());
		baseOrderInfo.setSpotCheckNumPcs(pcbCheckOrder.getSpotCheckNumPcs());
		String userCode = pcbCheckOrder.getCreaterCode();
		if (userCode != null) {
			WxtbUser wxtbUser1 = cacheService.getWxtbUser(userCode);
			if (wxtbUser1 != null) {
				baseOrderInfo.setDeliveryMan(wxtbUser1.getUsername());
			}
		}
		String orderNumber = pcbCheckOrder.getOrderNumber();
		PcbInStorageOrder pcbInStorageOrder = null;
		if (!StringUtil.isEmpty(orderNumber)) {
			pcbInStorageOrder = pcbInStorageOrderMapper.selectByOrderNumber(orderNumber);
		}
		if (pcbInStorageOrder != null) {
			baseOrderInfo.setDeliveryDate(DateTimeUtil.date2dateStr(pcbInStorageOrder.getInnerCreateTime()));
		}
		response.put("baseOrderInfo", baseOrderInfo);
		// 添加 备注信息
		PcbCheckNote pcbCheckNote = new PcbCheckNote();
		pcbCheckNote.setNote(pcbCheckOrder.getNote());
		pcbCheckNote.setMistakeNote(pcbCheckOrder.getMistakeNote());
		response.put("note", pcbCheckNote);
		// 添加检验单头部
		CheckHead checkHead = new CheckHead();
		checkHead.setBoardName(pcbCheckOrder.getBoardName());
		Integer templateId = pcbCheckOrder.getTemplateId();
		String templateName = null;
		if (templateId == null) {
			TemplateBaseIdEnum checkStandard = PcbmisUtil.goforPcbCheckTemplate(baseOrderInfo.getCategoryGrade(),
					baseOrderInfo.getCraft());
			templateName = checkStandard.getDesc();
		} else {
			PcbCheckTemplate pcbCheckTemplate = cacheService.getPcbCheckTemplate(templateId);
			templateName = pcbCheckTemplate.getTemplateName();
		}
		checkHead.setCheckStandard(templateName);
		checkHead.setDocument_number(pcbCheckOrder.getDocumentNumber());
		if (factory != null) {
			checkHead.setGuestName(factory.getFactoryName());
		}
		checkHead.setInAmountSet(pcbCheckOrder.getInAmountSet());
		checkHead.setProductOrderNum(orderNum);
		checkHead.setRevision(pcbCheckOrder.getRevision());
		checkHead.setSerialNumber(pcbCheckOrder.getSerialNumber());
		checkHead.setSpotCheckNum(pcbCheckOrder.getSpotCheckNumPcs());
		response.put("checkHead", checkHead);

		Dictionary warpingD = cacheService.getDictionary(DictionaryEnum.WARPING_DEGREE, 1);
		if (warpingD != null) {
			response.put("warping_degree", warpingD.getValueStr());
		}
		Integer checkStatus = pcbCheckOrder.getCheckStateId();
		if (type == 0) {
			if (checkStatus == CheckStateEnum.TO_AUDIT.getCode()) {
				Set<Integer> userAuths = getUserAuths(wxtbUser.getUserCode());
				if (userAuths.contains(2)) {
					pcbCheckOrder.setCheckStateId(CheckStateEnum.AUDITING.getCode());
					pcbCheckOrder.setAuditor(wxtbUser.getUserCode());
					pcbCheckOrder.setCheckState(CheckStateEnum.AUDITING.getDescr());
				}
				pcbCheckOrder.setUpdateTime(null);
				pcbCheckOrderMapper.updateByPrimaryKeySelective(pcbCheckOrder);
			}
			supportService.doOperateLog("检验单审核", orderNum, "检验单开始审核", wxtbUser.getUsername());
		} else if (type == 1) {
			supportService.doOperateLog("查看", orderNum, "查看检验单详情", wxtbUser.getUsername());
		}
		return response;
	}

	@Override
	public Map<String, Object> goForOrderDetail(AkAuthUser wxtbUser, String checkNum, Integer type,
			Integer templateId) {
		Map<String, Object> response = new HashMap<String, Object>();
		PcbCheckOrder pcbCheckOrder = pcbCheckOrderMapper.selectByCheckNum(checkNum);
		if (pcbCheckOrder == null) {
			return response;
		}
		Integer localTemplateId = null;
		List<PcbCheckDetail> pcbCheckDetails = pcbCheckDetailMapper.selectByCheckNumAndOptionName(checkNum, null);
		if (pcbCheckDetails.isEmpty()) {
			OrderDetail detail = selectOrderDetail(checkNum, templateId);
			if (detail == null) {
				return response;
			}
			localTemplateId = detail.getTemplateId();
			CheckItem checkItem = new CheckItem();
			checkItem.setRequire(detail.getFactory());
			response.put("factory", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getBoard_prevent_smt());
			response.put("board_prevent_smt", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getPrevent_smt_color());
			response.put("prevent_smt_color", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getBoard_character());
			response.put("board_character", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getCharacter_color());
			response.put("character_color", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getLogo());
			response.put("logo", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getBatch_number());
			response.put("batch_number", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getSurface_process());
			response.put("surface_process", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getViaHoleProcess());
			response.put("viaHoleProcess", checkItem);
			// 含模板字段
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getBoardLong() + "");
			checkItem.setBoardTolerance(detail.getBoardLong_tolerance());
			checkItem.setResult(detail.getBoardLongResult());
			response.put("boardLong", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getBoardWide() + "");
			checkItem.setBoardTolerance(detail.getBoardWide_tolerance());
			checkItem.setResult(detail.getBoardWideResult());
			response.put("boardWide", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getBoardPly() + "");
			checkItem.setBoardTolerance(detail.getBoardPly_tolerance());
			checkItem.setResult(detail.getBoardPlyResult());
			response.put("boardPly", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getLay_height());
			checkItem.setResult(detail.getSmoothnessResult());
			response.put("Lay_height", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getWarp_height());
			checkItem.setResult(detail.getSmoothnessResult());
			response.put("warp_height", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getBurrs());
			response.put("burrs", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getGap());
			response.put("gap", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getExposedCopper());
			response.put("exposedCopper", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getFabricTexture());
			response.put("fabricTexture", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getPitVoid());
			response.put("pitVoid", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getSpotCrack());
			response.put("spotCrack", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getDelaminationFoaming());
			response.put("delaminationFoaming", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getForeignImpurity());
			response.put("foreignImpurity", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getCoverageAdhesion());
			response.put("coverageAdhesion", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getCoincidenceDegree());
			response.put("coincidenceDegree", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getFoamingLayering());
			response.put("foamingLayering", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getCorrugation());
			response.put("corrugation", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getFalseExposedCopper());
			response.put("falseExposedCopper", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getFalseBridgeDam());
			response.put("falseBridgeDam", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getChromaticAberration());
			response.put("chromaticAberration", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getIdentificationAdhesion());
			response.put("identificationAdhesion", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getNodulesBurrs());
			response.put("nodulesBurrs", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getDarkOfHoleTinLead());
			response.put("darkOfHoleTinLead", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getPadCocked());
			response.put("padCocked", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getHaloRing());
			response.put("haloRing", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getOuterRingWidth());
			response.put("outerRingWidth", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getSolderInHole());
			response.put("solderInHole", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getClogHole());
			response.put("clogHole", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getLineWidthSpacing());
			response.put("lineWidthSpacing", checkItem);

			checkItem = new CheckItem();
			checkItem.setRequire(detail.getPrintedBoardElse());
			response.put("printed_board_else", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getSpecialBoardNum());
			response.put("special_board_num", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getBaseMaterialElse());
			response.put("base_material_else", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getSolderMaskElse());
			response.put("soldermask_else", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getMarkElse());
			response.put("mark_else", checkItem);
			checkItem = new CheckItem();
			checkItem.setRequire(detail.getConductivePatternElse());
			response.put("conductive_pattern_else", checkItem);
		} else {
			if (templateId == null) {
				for (PcbCheckDetail pcbCheckDetail : pcbCheckDetails) {
					CheckItem checkItem = new CheckItem();
					checkItem.setRequire(pcbCheckDetail.getCheckRequire());
					checkItem.setBoardTolerance(pcbCheckDetail.getBoardTolerance());
					checkItem.setResult(pcbCheckDetail.getCheckResult());
					checkItem.setBadNum(PcbmisUtil.getBadNum(pcbCheckDetail.getBadNum()));
					checkItem.setJudgeResult(PcbmisUtil.int2judge(pcbCheckDetail.getJudgeResult()));
					response.put(pcbCheckDetail.getOptionName(), checkItem);
				}
			} else {
				PcbCheckTemplate pcbCheckTemplate = cacheService.getPcbCheckTemplate(templateId);
				for (PcbCheckDetail pcbCheckDetail : pcbCheckDetails) {
					String optionName = pcbCheckDetail.getOptionName();
					CheckItem checkItem = new CheckItem();
					response.put(optionName, checkItem);
					checkItem.setBoardTolerance(pcbCheckDetail.getBoardTolerance());
					checkItem.setBadNum(PcbmisUtil.getBadNum(pcbCheckDetail.getBadNum()));
					checkItem.setJudgeResult(PcbmisUtil.int2judge(pcbCheckDetail.getJudgeResult()));
					checkItem.setRequire(pcbCheckDetail.getCheckRequire());
					checkItem.setResult(pcbCheckDetail.getCheckResult());
					// 提交后修改模板
					PcbmisUtil.operateCheckItem(optionName, checkItem, pcbCheckTemplate);
				}
			}
		}
		// 获取尺寸翘曲度信息
		List<SizeAndWarpingDegree> sizeAndWarpingDegrees = sizeAndWarpingDegreeMapper.selectByCheckNum(checkNum);
		List<SizeAndWarpDegree> sizeAndWarpDegrees = new ArrayList<SizeAndWarpDegree>();
		if (sizeAndWarpingDegrees != null && sizeAndWarpingDegrees.size() != 0) {
			for (SizeAndWarpingDegree sizeAndWarpingDegree : sizeAndWarpingDegrees) {
				SizeAndWarpDegree sizeAndWarpDegree = new SizeAndWarpDegree();
				sizeAndWarpDegree.setBoardNum(sizeAndWarpingDegree.getBoardNum());
				sizeAndWarpDegree.setBoardLong(PcbmisUtil.bigDecimal2double(sizeAndWarpingDegree.getBoardLong()));
				sizeAndWarpDegree.setBoardWide(PcbmisUtil.bigDecimal2double(sizeAndWarpingDegree.getBoardWide()));
				sizeAndWarpDegree.setBoardPly(sizeAndWarpingDegree.getBoardPly());
				sizeAndWarpDegree.setLayHeight(sizeAndWarpingDegree.getLayHeight());
				sizeAndWarpDegree.setWarpHeight(sizeAndWarpingDegree.getWarpHeight());
				sizeAndWarpDegree.setWarpingDegree(sizeAndWarpingDegree.getWarpingDegree());
				sizeAndWarpDegree.setJudge(PcbmisUtil.int2judge(sizeAndWarpingDegree.getJudge()));
				sizeAndWarpDegrees.add(sizeAndWarpDegree);
			}
		}
		response.put("sizeAndWarpDegrees", sizeAndWarpDegrees);
		Dictionary warpingD = cacheService.getDictionary(DictionaryEnum.WARPING_DEGREE, 1);
		if (warpingD != null) {
			response.put("warping_degree", warpingD.getValueStr());
		}
		CheckState checkState = new CheckState();
		checkState.setState(pcbCheckOrder.getCheckState());
		checkState.setStateId(pcbCheckOrder.getCheckStateId());
		response.put("checkState", checkState);
		String orderNum = pcbCheckOrder.getProductOrderNum();
		ProductOrder productOrder = productOrderMapper.selectByOrderNum(orderNum);
		if (productOrder == null) {
			return response;
		}
		// 添加基础信息
		BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
		baseOrderInfo.setCheckNum(checkNum);
		baseOrderInfo.setOrderNum(orderNum);
		String boardName = pcbCheckOrder.getBoardName();
		baseOrderInfo.setBoardName(boardName);
		EdaGuest guest = cacheService.getGuest(productOrder.getGuestCode());
		if (guest != null) {
			baseOrderInfo.setGuestName(guest.getShortNameCn());
		}
		Date productDate = productOrder.getProductDate();
		if (productDate != null) {
			String proDate = DateTimeUtil.date2dateTimeStr(productDate, "yyyy-MM-dd");
			baseOrderInfo.setProductDate(proDate);
		}
		Dictionary dictGrade = cacheService.getDictionary(DictionaryEnum.CATEGORY_GRADE,
				productOrder.getCategoryGradeId());
		if (dictGrade != null) {
			String categoryGrade = dictGrade.getValueStr();
			baseOrderInfo.setCategoryGrade(categoryGrade);
		}
		Integer craftId = productOrder.getCraftId();
		CraftInfo craftInfo = cacheService.getCraftInfo(craftId);
		if (craftInfo != null) {
			baseOrderInfo.setCraft(craftInfo.getCraftName());
		}
		FactoryInfo factory = cacheService.getFactory(productOrder.getFactoryId());
		if (factory != null) {
			baseOrderInfo.setFactoryName(factory.getFactoryName());
		}
		baseOrderInfo.setImpedanceRequire(productOrder.getImpedanceRequire());
		baseOrderInfo.setMetallography_require(productOrder.getMetallographyRequire());
		baseOrderInfo.setProductionNumSet(productOrder.getProductionNumSet());
		baseOrderInfo.setProductionNumPcs(productOrder.getProductionNumPcs());
		baseOrderInfo.setAmountCheckoutPcs(pcbCheckOrder.getInAmountPcs());
		baseOrderInfo.setSpotCheckNumPcs(pcbCheckOrder.getSpotCheckNumPcs());
		Dictionary dictionary = cacheService.getDictionary(DictionaryEnum.CHECK_STANDARD, 1);
		if (dictionary != null) {
			baseOrderInfo.setInspectionSpecification(dictionary.getValueStr());
		}
		// 添加生产配注
		String notes = PcbmisUtil.getProductNote(utilService.getProductNotes(orderNum, 0),
				utilService.getProductNotes(orderNum, 1));
		baseOrderInfo.setProductionNote(notes);
		// 添加发货备注
		baseOrderInfo.setDeliveryNote(productOrder.getShipmentsNotes());
		// 添加商务备注
		baseOrderInfo.setBusinessNote(productOrder.getBusinessNotes());
		// 添加cam处理
		baseOrderInfo.setCAMGuide(productOrder.getCamTip());
		String userCode = pcbCheckOrder.getCreaterCode();
		if (userCode != null) {
			WxtbUser userCreator = cacheService.getWxtbUser(userCode);
			if (userCreator != null) {
				baseOrderInfo.setDeliveryMan(userCreator.getUsername());
			}
		}
		String orderNumber = pcbCheckOrder.getOrderNumber();
		PcbInStorageOrder pcbInStorageOrder = null;
		if (!StringUtil.isEmpty(orderNumber)) {
			pcbInStorageOrder = pcbInStorageOrderMapper.selectByOrderNumber(orderNumber);
		}
		if (pcbInStorageOrder != null) {
			baseOrderInfo.setDeliveryDate(DateTimeUtil.date2dateStr(pcbInStorageOrder.getInnerCreateTime()));
		}
		response.put("baseOrderInfo", baseOrderInfo);
		// 添加检验单头部
		CheckHead checkHead = new CheckHead();
		checkHead.setBoardName(boardName);
		if (templateId == null) {
			if (localTemplateId == null) {
				templateId = pcbCheckOrder.getTemplateId();
			} else {
				templateId = localTemplateId;
			}
		}
		PcbCheckTemplate checkTemplate = cacheService.getPcbCheckTemplate(templateId);
		if (checkTemplate != null) {
			checkHead.setCheckStandard(checkTemplate.getTemplateName());
			checkHead.setTemplateId(templateId);
		}
		checkHead.setDocument_number(pcbCheckOrder.getDocumentNumber());
		if (factory != null) {
			checkHead.setGuestName(factory.getFactoryName());
		}
		checkHead.setInAmountSet(pcbCheckOrder.getInAmountSet());
		checkHead.setProductOrderNum(orderNum);
		checkHead.setRevision(pcbCheckOrder.getRevision());
		checkHead.setSerialNumber(pcbCheckOrder.getSerialNumber());
		checkHead.setSpotCheckNum(pcbCheckOrder.getSpotCheckNumPcs());
		response.put("checkHead", checkHead);
		// 添加 备注信息
		PcbCheckNote pcbCheckNote = new PcbCheckNote();
		pcbCheckNote.setNote(pcbCheckOrder.getNote());
		pcbCheckNote.setMistakeNote(pcbCheckOrder.getMistakeNote());
		response.put("note", pcbCheckNote);
		// 添加下拉框
		List<String> factroys = getFactorys();
		List<String> board_prevent_smts = getBoard_prevent_smts();
		List<String> prevent_smts_color = getPrevent_smt_color();
		List<String> board_characters = getBoard_characters();
		List<String> character_color = getCharacter_color();
		List<String> logos = getLogos();
		List<String> surfaceProcessNames = getSurface_process();
		response.put("factoryNames", factroys);
		response.put("board_prevent_smts", board_prevent_smts);
		response.put("prevent_smts_color", prevent_smts_color);
		response.put("board_characters", board_characters);
		response.put("character_colors", character_color);
		response.put("logos", logos);
		response.put("surfaceProcessNames", surfaceProcessNames);
		Integer checkStatus = pcbCheckOrder.getCheckStateId();
		if (checkStatus == CheckStateEnum.TO_CHECK.getCode() && type == 1) {
			Set<Integer> userAuths = getUserAuths(wxtbUser.getUserCode());
			if (userAuths.contains(1)) {
				pcbCheckOrder.setCheckStateId(CheckStateEnum.CHECKING.getCode());
				pcbCheckOrder.setInspector(wxtbUser.getUserCode());
				pcbCheckOrder.setCheckState(CheckStateEnum.CHECKING.getDescr());
				supportService.doOperateLog("检验", orderNum, "开始检验", wxtbUser.getUsername());
			}
		}
		Integer companyId = productOrder.getBelongCompanyId();
		CompanyInfo companyInfo = cacheService.getCompany(companyId);
		String companyCode = null;
		String companyName = null;
		if (companyInfo != null) {
			companyCode = companyInfo.getOrgCode();
			companyName = companyInfo.getCompanyName();
		}
		response.put("companyCode", companyCode);
		response.put("companyName", companyName);
		// 以下字段为导出而添加
		// 流水号
		response.put("serialNumber", pcbCheckOrder.getSerialNumber());
		// 版次
		response.put("revision", pcbCheckOrder.getRevision());
		// 编号
		response.put("documentNumber", pcbCheckOrder.getDocumentNumber());
		pcbCheckOrder.setUpdateTime(null);
		pcbCheckOrderMapper.updateByPrimaryKeySelective(pcbCheckOrder);
		return response;
	}

	@Override
	public synchronized Integer judgeAuditPass(AkAuthUser wxtbUser, String checkNum, Integer isPass) {
		PcbCheckOrder pcbCheckOrder = pcbCheckOrderMapper.selectByCheckNum(checkNum);
		if (pcbCheckOrder == null) {
			return 1;
		}
		String orderNum = pcbCheckOrder.getProductOrderNum();
		if (orderNum == null) {
			return 1;
		}
		if (pcbCheckOrder.getCheckStateId() != CheckStateEnum.AUDITING.getCode()) {
			return 2;
		}
		ProductOrder productOrder = productOrderMapper.selectByOrderNum(orderNum);
		if (productOrder == null) {
			return 1;
		}
		Date date = new Date();
		String userCode = null;
		String userName = null;
		if (wxtbUser != null) {
			userCode = wxtbUser.getUserCode();
			userName = wxtbUser.getUsername();
		}
		pcbCheckOrder.setAuditor(userCode);
		if (isPass == 1) {
			// 如果通过，设置检验单状态为已检验
			pcbCheckOrder.setCheckState(CheckStateEnum.COMPLETE.getDescr());
			pcbCheckOrder.setCheckStateId(CheckStateEnum.COMPLETE.getCode());
			pcbCheckOrder.setAuditDate(date);
			supportService.doOperateLog("检验单审核", pcbCheckOrder.getProductOrderNum(), "检验单审核通过", userName);
			// 如果存在不合格品单，并且有生成
			Integer badNum = pcbCheckDetailMapper.selectSumBadNumByChecknum(checkNum);
			Integer unjudge = sizeAndWarpingDegreeMapper.selectUnJudgeByCheckNum(checkNum);
			if (badNum == null) {
				badNum = 0;
			}
			badNum = badNum + unjudge;
			if (badNum > 0) {
				PcbUnqualified unqualified = pcbUnqualifiedMapper.selectByCheckNumUnable(checkNum);
				if (unqualified != null) {
					unqualified.setEnable(1);
					unqualified.setUpdateTime(null);
					pcbUnqualifiedMapper.updateByPrimaryKeySelective(unqualified);
				}
			}
		} else {
			// 如果不通过，设置检验单状态为检验中
			pcbCheckOrder.setAuditor(userCode);
			pcbCheckOrder.setCheckState(CheckStateEnum.CHECKING.getDescr());
			pcbCheckOrder.setCheckStateId(CheckStateEnum.CHECKING.getCode());
			supportService.doOperateLog("检验单审核", pcbCheckOrder.getProductOrderNum(), "检验单审核不通过", userName);
			// 作废不合格品处置单
			PcbUnqualified unqualified = pcbUnqualifiedMapper.selectByCheckNumber(pcbCheckOrder.getCheckNum());
			if (unqualified != null && unqualified.getId() != null) {
				unqualified.setEnable(0);
				pcbUnqualifiedMapper.updateByPrimaryKeySelective(unqualified);
			}
		}
		pcbCheckOrderMapper.updateByPrimaryKeySelective(pcbCheckOrder);
		return 0;
	}

	@Override
	public Map<String, Object> getLatelyOperate(Integer page, Integer pageSize) {
		Map<String, Object> response = new HashMap<String, Object>();
		Integer start = (page - 1) * pageSize;
		Integer total = operateLogMapper.getSelectByConditionNum(null, null, null);
		List<OperateLog> operateLogs = operateLogMapper.selectByCondition(null, null, null, start, pageSize);
		if (operateLogs == null || operateLogs.size() == 0) {
			response.put("total", total);
			response.put("rows", null);
		}
		List<LatelyOperate> operateLogStr = new ArrayList<LatelyOperate>();
		for (OperateLog operateLog : operateLogs) {
			String time = DateTimeUtil.date2dateTimeStr(operateLog.getCreatTime(), "yyyy年MM月dd日  HH:mm");
			if (time == null) {
				time = "";
			}
			String log = time + "," + operateLog.getUsername() + " " + operateLog.getOperateName()
					+ operateLog.getOperateContent() + operateLog.getOperateResult();
			LatelyOperate latelyOperate = new LatelyOperate();
			latelyOperate.setOperate(log);
			operateLogStr.add(latelyOperate);
		}
		response.put("total", total);
		response.put("rows", operateLogStr);
		return response;
	}

	@Override
	@Deprecated
	public BoardToleranceBean goForBoardToleranceBean(AkAuthUser wxtbUser, String checkNum) {
		List<BoardTolerance> boardTolerances = boardToleranceMapper.selectByCheckNum(checkNum);
		BoardTolerance boardTolerance = new BoardTolerance();
		if (boardTolerances != null && !boardTolerances.isEmpty()) {
			boardTolerance = boardTolerances.get(0);
		}
		BoardToleranceBean boardToleranceBean = new BoardToleranceBean();
		boardToleranceBean.setMaxBoardlongTolerance(boardTolerance.getMaxBoardlongTolerance());
		boardToleranceBean.setMinBoardlongTolerance(boardTolerance.getMinBoardlongTolerance());
		boardToleranceBean.setMaxBoardwideTolerance(boardTolerance.getMaxBoardwideTolerance());
		boardToleranceBean.setMinBoardwideTolerance(boardTolerance.getMinBoardwideTolerance());
		boardToleranceBean.setMaxBoardplyTolerance(boardTolerance.getMaxBoardplyTolerance());
		boardToleranceBean.setMinBoardplyTolerance(boardTolerance.getMinBoardplyTolerance());
		return boardToleranceBean;
	}

	public List<Integer> getFactoryIds(String factory) {
		List<Integer> factoryId = null;
		if (factory != null && !factory.equals("")) {
			List<FactoryInfo> factoryInfos = factoryInfoMapper.selectByFactory_name(factory);
			if (!(factoryInfos == null || factoryInfos.isEmpty())) {
				factoryId = new ArrayList<Integer>();
				for (FactoryInfo factoryInfo : factoryInfos) {
					factoryId.add(factoryInfo.getInnerId());
				}
			}
		}
		return factoryId;
	}

	public BigDecimal getMax(BigDecimal big1, BigDecimal big2) {
		int result = big1.compareTo(big2);
		if (result == 1) {
			return big1;
		}
		return big2;
	}

	// @Override
	public List<String> getFactorys() {
		Map<Integer, FactoryInfo> factoryMap = cacheService.getFactorys();
		List<String> factorys = new ArrayList<String>();
		for (Integer key : factoryMap.keySet()) {
			String factoryName = factoryMap.get(key).getFactoryName();
			factorys.add(factoryName);
		}
		return factorys;
	}

	// @Override
	public List<String> getBoard_prevent_smts() {
		List<String> board_prevent_smts = new ArrayList<String>();
		List<Dictionary> Dictionarys = cacheService.getDictionarys(DictionaryEnum.PREVENT_SMT);
		if (Dictionarys == null || Dictionarys.size() == 0) {
			board_prevent_smts.add("qeqwrw");
			board_prevent_smts.add("qeqwrw2");
			return board_prevent_smts;
		}
		for (Dictionary dictionary : Dictionarys) {
			board_prevent_smts.add(dictionary.getValueStr());
		}
		return board_prevent_smts;
	}

	// @Override
	public List<String> getPrevent_smt_color() {
		List<String> prevent_smts_color = new ArrayList<String>();
		List<Dictionary> Dictionarys = cacheService.getDictionarys(DictionaryEnum.PREVENT_SMT_COLOR);
		if (Dictionarys == null || Dictionarys.size() == 0) {
			prevent_smts_color.add("颜色1");
			prevent_smts_color.add("颜色2");
			return prevent_smts_color;
		}
		for (Dictionary dictionary : Dictionarys) {
			prevent_smts_color.add(dictionary.getValueStr());
		}
		return prevent_smts_color;
	}

	// @Override
	public List<String> getBoard_characters() {
		List<String> board_characters = new ArrayList<String>();
		List<Dictionary> Dictionarys = cacheService.getDictionarys(DictionaryEnum.CHARACTER);
		if (Dictionarys == null || Dictionarys.size() == 0) {
			board_characters.add("asdafds");
			board_characters.add("asdafds2");
			return board_characters;
		}
		for (Dictionary dictionary : Dictionarys) {
			board_characters.add(dictionary.getValueStr());
		}
		return board_characters;
	}

	// @Override
	public List<String> getCharacter_color() {
		List<String> character_color = new ArrayList<String>();
		List<Dictionary> Dictionarys = cacheService.getDictionarys(DictionaryEnum.CHARACTER_COLOUR);
		if (Dictionarys == null || Dictionarys.size() == 0) {
			character_color.add("颜色1");
			character_color.add("颜色2");
			return character_color;
		}
		for (Dictionary dictionary : Dictionarys) {
			character_color.add(dictionary.getValueStr());
		}
		return character_color;
	}

	// @Override
	public List<String> getLogos() {
		List<String> batchCode = new ArrayList<String>();
		List<Dictionary> dictionaries = cacheService.getDictionarys(DictionaryEnum.PRODUCTION_BATCH);
		if (dictionaries == null || dictionaries.size() == 0) {
			batchCode.add("logo1");
			batchCode.add("logo2");
			return batchCode;
		}
		for (Dictionary dictionary : dictionaries) {
			batchCode.add(dictionary.getValueStr());
		}
		return batchCode;
	}

	// @Override
	public List<String> getSurface_process() {
		List<String> surfaceProcessNames = new ArrayList<String>();
		Map<Integer, SurfaceProcess> surfaceProcess = cacheService.getSurfaceProcess();
		for (Integer key : surfaceProcess.keySet()) {
			String surfaceProcessName = surfaceProcess.get(key).getProcessName();
			if (surfaceProcessName != null) {
				surfaceProcessNames.add(surfaceProcessName);
			}
		}
		return surfaceProcessNames;
	}

	/**
	 * 获取检验单模板信息
	 * 
	 * @param checkNum
	 * @return
	 */
	@Override
	public OrderDetail selectOrderDetail(String checkNum, Integer templateId) {
		PcbCheckOrder pcbCheckOrder = pcbCheckOrderMapper.selectByCheckNum(checkNum);
		if (pcbCheckOrder == null) {
			return null;
		}
		String orderNum = pcbCheckOrder.getProductOrderNum();
		ProductOrder productOrder = productOrderMapper.selectByOrderNum(orderNum);
		if (productOrder == null) {
			return null;
		}
		Integer surfaceProcessId = productOrder.getSurfaceProcessId();
		SurfaceProcess surfaceProcess = cacheService.getSurfaceProcess(surfaceProcessId);
		String surfaceProcessStr = null;
		if (surfaceProcess != null) {
			surfaceProcessStr = surfaceProcess.getProcessName();
		}
		FactoryInfo factory = cacheService.getFactory(productOrder.getFactoryId());
		String factoryName = null;
		if (factory != null) {
			factoryName = factory.getFactoryName();
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
		List<JoinBoardOrder> joinBoardOrders = JoinBoardOrderMapper.listByCodes(joinOrderCodes);
		JoinBoardOrder joinBoardOrder = null;
		if (joinBoardOrders != null && joinBoardOrders.size() != 0) {
			joinBoardOrder = joinBoardOrders.get(0);
		}
		PcbCheckTemplate pcbCheckTemplate = null;
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
		if (templateId == null) {
			if (!cate.contains("民") && !cate.contains("-")) {
				if (craft.contains("软")) {
					templateId = TemplateBaseIdEnum.GJB_7548_2012.getCode();
				} else {
					templateId = TemplateBaseIdEnum.GJB_362B_2009.getCode();
				}
			} else {
				templateId = TemplateBaseIdEnum.IPC_A_600H.getCode();
			}
		}
		
		pcbCheckTemplate = cacheService.getPcbCheckTemplate(templateId);
		OrderDetail orderDetail = new OrderDetail(pcbCheckTemplate);
		BigDecimal boardLong = productOrder.getBoardLong();
		BigDecimal boardWide = productOrder.getBoardWide();
		if (joinBoardOrder != null) {
			boardLong = joinBoardOrder.getJoinBoardLong();
			boardWide = joinBoardOrder.getJoinBoardWide();
			orderDetail.setBoardLong(boardLong);
			orderDetail.setBoardWide(boardWide);
		} else {
			orderDetail.setBoardLong(boardLong);
			orderDetail.setBoardWide(boardWide);
		}
		String layHeightStr = null;
		if (boardLong != null && boardWide != null) {
			BigDecimal lay_height = null;
			lay_height = getMax(boardLong, boardWide).multiply(new BigDecimal(0.0075));
			Double layHeight = lay_height.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			layHeightStr = layHeight.toString();
		}
		String warpHeightStr = null;
		if (boardLong != null && boardWide != null) {
			BigDecimal boardLong1 = boardLong.multiply(boardLong);
			BigDecimal boardWide1 = boardWide.multiply(boardWide);
			BigDecimal warpHeight1 = boardLong1.add(boardWide1);
			Double warpheigh2 = warpHeight1.doubleValue();
			Double warpHeight = Math.sqrt(warpheigh2) * 0.0075;
			BigDecimal bigDecimal = new BigDecimal(warpHeight);
			Double warpHeightDou = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			warpHeightStr = warpHeightDou.toString();
		}
		orderDetail.setOrderNum(orderNum);
		orderDetail.setFactory(factoryName);
		orderDetail.setBoard_character(productOrder.getBoardCharacter());
		orderDetail.setBoard_prevent_smt(productOrder.getPreventSmtColorId() + "");
		orderDetail.setSurface_process(surfaceProcessStr);
		// TODO 过孔处理
		orderDetail.setViaHoleProcess(productOrder.getThroughHoleTreatment());
		orderDetail.setBoardPly(productOrder.getBoardPly());
		// 边框公差
		Integer frameId = productOrder.getFrameToleranceId();
		String boardTolerance = utilService.getFrameTolerance(frameId);
		orderDetail.setBoardLong_tolerance(boardTolerance);
		orderDetail.setBoardWide_tolerance(boardTolerance);
		// 板厚公差
		Integer boardPlyId = productOrder.getBoardPlyToleranceId();
		String boardPlyTolerance = utilService.getBoardPlyTolerance(boardPlyId);
		orderDetail.setBoardPly_tolerance(boardPlyTolerance);
		orderDetail.setLay_height("≤" + layHeightStr);
		orderDetail.setWarp_height("≤" + warpHeightStr);
		orderDetail.setBoard_prevent_smt(productOrder.getPreventSmt());
		Integer preSmtColor = productOrder.getPreventSmtColorId();
		Dictionary aString = cacheService.getDictionary(DictionaryEnum.PREVENT_SMT_COLOR, preSmtColor);
		if (aString != null) {
			orderDetail.setPrevent_smt_color(aString.getValueStr());
		}

		Integer charColor = productOrder.getCharacterColourId();
		Dictionary charColorD = cacheService.getDictionary(DictionaryEnum.CHARACTER_COLOUR, charColor);
		if (charColorD != null) {
			orderDetail.setCharacter_color(charColorD.getValueStr());
		}
		Integer logoInteger = productOrder.getBatchProductionId();
		Dictionary dictionary = cacheService.getDictionary(DictionaryEnum.PRODUCTION_BATCH, logoInteger);
		if (dictionary != null) {
			String logo = dictionary.getValueStr();
			orderDetail.setLogo(logo);
			orderDetail.setBatch_number(logo);
		}
		return orderDetail;
	}

	public List<CheckInfo> addAll(List<PcbCheckOrder> pcbCheckOrders, List<CheckInfo> checkInfos) {
		List<String> joinOrderCodes = new ArrayList<String>();
		Map<String, PcbCheckOrder> pcbMap = new HashMap<String, PcbCheckOrder>();
		for (PcbCheckOrder pcbCheckOrder : pcbCheckOrders) {
			pcbMap.put(pcbCheckOrder.getCheckNum(), pcbCheckOrder);
			String orderType = pcbCheckOrder.getOrderType();
			if (orderType != null) {
				if (orderType.equals("JoinBoardOrder")) {
					joinOrderCodes.add(pcbCheckOrder.getJoinBoardOrderCode());
				}
			}
		}
		if (joinOrderCodes.size() == 0) {
			joinOrderCodes = null;
		}
		List<JoinBoardOrder> joinBoardOrders = JoinBoardOrderMapper.listByCodes(joinOrderCodes);
		Map<String, JoinBoardOrder> joinMap = new HashMap<String, JoinBoardOrder>();
		if (joinBoardOrders != null && joinBoardOrders.size() != 0) {
			for (JoinBoardOrder joinBoardOrder : joinBoardOrders) {
				joinMap.put(joinBoardOrder.getJoinBoardCode(), joinBoardOrder);
			}
		}
		for (CheckInfo checkInfo : checkInfos) {
			PcbCheckOrder pcbCheckOrder = pcbMap.get(checkInfo.getCheckNum());
			String joinCode = pcbCheckOrder.getJoinBoardOrderCode();
			String orderType = pcbCheckOrder.getOrderType();
			if (orderType != null) {
				if (orderType.equals("JoinBoardOrder")) {
					JoinBoardOrder joinBoardOrder = joinMap.get(joinCode);
					if (joinBoardOrder != null) {
						checkInfo.setBoardLong(joinBoardOrder.getJoinBoardLong());
						checkInfo.setBoardWide(joinBoardOrder.getJoinBoardWide());
					}
				}
			}
		}
		return checkInfos;
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

}
