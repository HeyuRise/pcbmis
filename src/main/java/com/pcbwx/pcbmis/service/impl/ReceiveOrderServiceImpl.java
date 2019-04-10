package com.pcbwx.pcbmis.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.ohkit.utils.StringUtil;
import com.pcbwx.pcbmis.bean.response.PcbReceiveOrderResponse;
import com.pcbwx.pcbmis.component.CacheService;
import com.pcbwx.pcbmis.dao.JoinBoardOrderMapper;
import com.pcbwx.pcbmis.dao.PcbCheckOrderMapper;
import com.pcbwx.pcbmis.dao.PcbCheckReportMapper;
import com.pcbwx.pcbmis.dao.PcbInStorageOrderMapper;
import com.pcbwx.pcbmis.dao.PcbReceiveOrderMapper;
import com.pcbwx.pcbmis.dao.PcbReportApertureCheckMapper;
import com.pcbwx.pcbmis.dao.PcbReportCertificationMapper;
import com.pcbwx.pcbmis.dao.PcbReportDetailMapper;
import com.pcbwx.pcbmis.dao.PcbReportIntegrityMapper;
import com.pcbwx.pcbmis.dao.PcbReportIntroMapper;
import com.pcbwx.pcbmis.dao.PcbReportSpecialDimensionMapper;
import com.pcbwx.pcbmis.dao.PcbReportSpecialImpedanceMapper;
import com.pcbwx.pcbmis.dao.PcbReportVcutMapper;
import com.pcbwx.pcbmis.dao.PcbUnqualifiedMapper;
import com.pcbwx.pcbmis.dao.ProductOrderMapper;
import com.pcbwx.pcbmis.enums.CheckContentEnum;
import com.pcbwx.pcbmis.enums.CheckStateEnum;
import com.pcbwx.pcbmis.enums.DictionaryEnum;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.enums.InstorageOrderTypeEnum;
import com.pcbwx.pcbmis.enums.ReportStateEnum;
import com.pcbwx.pcbmis.model.Dictionary;
import com.pcbwx.pcbmis.model.EdaGuest;
import com.pcbwx.pcbmis.model.FactoryInfo;
import com.pcbwx.pcbmis.model.JoinBoardOrder;
import com.pcbwx.pcbmis.model.PcbCheckOrder;
import com.pcbwx.pcbmis.model.PcbCheckReport;
import com.pcbwx.pcbmis.model.PcbInStorageOrder;
import com.pcbwx.pcbmis.model.PcbReceiveOrder;
import com.pcbwx.pcbmis.model.PcbUnqualified;
import com.pcbwx.pcbmis.model.ProductOrder;
import com.pcbwx.pcbmis.model.WxtbUser;
import com.pcbwx.pcbmis.service.InStorageService;
import com.pcbwx.pcbmis.service.ReceiveOrderService;
import com.pcbwx.pcbmis.service.UtilService;
import com.pcbwx.pcbmis.utils.DateTimeUtil;
import com.pcbwx.pcbmis.utils.EnumUtil;
import com.pcbwx.pcbmis.utils.JsonUtil;
import com.pcbwx.pcbmis.utils.PcbmisUtil;

@Service("receiveService")
public class ReceiveOrderServiceImpl implements ReceiveOrderService {

	@Autowired
	private PcbReceiveOrderMapper pcbReceiveOrderMapper;
	@Autowired
	private ProductOrderMapper productOrderMapper;
	@Autowired
	private PcbInStorageOrderMapper pcbInStorageOrderMapper;
	@Autowired
	private JoinBoardOrderMapper joinBoardOrderMapper;
	@Autowired
	private PcbCheckOrderMapper pcbCheckOrderMapper;
	@Autowired
	private PcbCheckReportMapper pcbCheckReportMapper;
	@Autowired
	private PcbReportIntroMapper pcbReportIntroMapper;
	@Autowired
	private PcbReportCertificationMapper pcbReportCertificationMapper;
	@Autowired
	private PcbReportApertureCheckMapper pcbReportApertureCheckMapper;
	@Autowired
	private PcbReportDetailMapper pcbReportDetailMapper;
	@Autowired
	private PcbReportIntegrityMapper pcbReportIntegrityMapper;
	@Autowired
	private PcbReportSpecialImpedanceMapper pcbReportSpecialImpedanceMapper;
	@Autowired
	private PcbReportSpecialDimensionMapper pcbReportSpecialDimensionMapper;
	@Autowired
	private PcbReportVcutMapper pcbReportVcutMapper;
	@Autowired
	private PcbUnqualifiedMapper pcbUnqualifiedMapper;

	@Autowired
	private CacheService cacheService;
	@Autowired
	private UtilService utilService;
	@Autowired
	private InStorageService inStorageService;

	@Override
	public Map<String, Object> goForReceiveOrderList(String orderNum, String guestName, Integer factoryId,
			Integer gradeId, Integer productNumSet, Integer productNumPcs, Integer contentId, Integer receiveNum,
			Integer receiveType, Date receiveDateStart, Date receiveDateEnd, String userCode, Integer rows,
			Integer page) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<String> guestCodes = utilService.getEdaGuest(guestName);
		Integer start = rows * (page - 1);
		List<PcbReceiveOrder> pcbReceiveOrders = pcbReceiveOrderMapper.selectByParam(orderNum, guestCodes, factoryId,
				gradeId, productNumSet, productNumPcs, contentId == null ? null : contentId.toString(), receiveNum,
				receiveType, receiveDateStart, receiveDateEnd, userCode, start, rows);
		Integer total = pcbReceiveOrderMapper.countByParam(orderNum, guestCodes, factoryId, gradeId, productNumSet,
				productNumPcs, contentId == null ? null : contentId.toString(), receiveNum, receiveType,
				receiveDateStart, receiveDateEnd, userCode);
		if (total == 0) {
			response.put("total", 0);
			response.put("rows", new ArrayList<>());
			return response;
		}
		List<String> orderNums = new ArrayList<String>();
		List<String> joinBoards = new ArrayList<String>();
		List<String> inStorageNums = new ArrayList<String>();
		for (PcbReceiveOrder pcbReceiveOrder : pcbReceiveOrders) {
			orderNums.add(pcbReceiveOrder.getOrderNum());
			inStorageNums.add(pcbReceiveOrder.getOrderNumber());
		}
		List<ProductOrder> productOrders = productOrderMapper.listByOrderNum(orderNums);
		Map<String, ProductOrder> pcbOrders = new HashMap<String, ProductOrder>();
		for (ProductOrder productOrder : productOrders) {
			pcbOrders.put(productOrder.getOrderNum(), productOrder);
		}
		List<PcbInStorageOrder> pcbInStorageOrders = pcbInStorageOrderMapper.selectByInOrderNums(inStorageNums);
		Map<String, PcbInStorageOrder> pcbInstorageOrderMap = new HashMap<>();
		for (PcbInStorageOrder pcbInStorageOrder : pcbInStorageOrders) {
			pcbInstorageOrderMap.put(pcbInStorageOrder.getOrderNumber(), pcbInStorageOrder);
			if (Objects.equals("JoinBoardOrder", pcbInStorageOrder.getOrderType())) {
				joinBoards.add(pcbInStorageOrder.getJoinBoardOrderCode());
			}
		}
		Map<String, JoinBoardOrder> joinMap = new HashMap<String, JoinBoardOrder>();
		if (!joinBoards.isEmpty()) {
			List<JoinBoardOrder> joinBoardOrders = joinBoardOrderMapper.listByCodes(joinBoards);
			if (joinBoardOrders != null && joinBoardOrders.size() != 0) {
				for (JoinBoardOrder joinBoardOrder : joinBoardOrders) {
					joinMap.put(joinBoardOrder.getJoinBoardCode(), joinBoardOrder);
				}
			}
		}
		List<PcbReceiveOrderResponse> pcbReceiveOrderResponses = new ArrayList<>();
		PcbReceiveOrderResponse pcbReceiveOrderResponse = null;
		for (PcbReceiveOrder pcbReceiveOrder : pcbReceiveOrders) {
			pcbReceiveOrderResponse = new PcbReceiveOrderResponse();
			pcbReceiveOrderResponses.add(pcbReceiveOrderResponse);
			pcbReceiveOrderResponse.setId(pcbReceiveOrder.getId());
			pcbReceiveOrderResponse.setOrderNum(pcbReceiveOrder.getOrderNum());
			FactoryInfo factory = cacheService.getFactory(pcbReceiveOrder.getFactoryId());
			if (factory != null) {
				pcbReceiveOrderResponse.setFactoryName(factory.getFactoryName());
			}
			Dictionary dictGrade = cacheService.getDictionary(DictionaryEnum.CATEGORY_GRADE,
					pcbReceiveOrder.getCategoryGradeId());
			if (dictGrade != null) {
				pcbReceiveOrderResponse.setGrade(dictGrade.getValueStr());
			}
			pcbReceiveOrderResponse.setProductNumSET(pcbReceiveOrder.getProductionNumSet());
			pcbReceiveOrderResponse.setProductNumPCS(pcbReceiveOrder.getProductionNumPcs());
			String content = utilService.getContent(pcbReceiveOrder.getContentId());
			pcbReceiveOrderResponse.setContentIds(PcbmisUtil.str2list(pcbReceiveOrder.getContentId()));
			pcbReceiveOrderResponse.setReceiveTypeId(pcbReceiveOrder.getReceiveType());
			pcbReceiveOrderResponse.setContent(content);
			pcbReceiveOrderResponse.setReceiveNum(pcbReceiveOrder.getSpotCheckNumPcs());
			InstorageOrderTypeEnum instorageOrderTypeEnum = EnumUtil.getEnumByCode(InstorageOrderTypeEnum.class,
					"getCode", pcbReceiveOrder.getReceiveType().toString());
			pcbReceiveOrderResponse.setReceiveType(instorageOrderTypeEnum.getDescr());
			String receiveDate = DateTimeUtil.date2dateStr(pcbReceiveOrder.getReceiveTime());
			pcbReceiveOrderResponse.setReceiveDate(receiveDate);
			String receiveTime = DateTimeUtil.date2dateTimeStr(pcbReceiveOrder.getReceiveTime(), "HH:mm");
			pcbReceiveOrderResponse.setReceiveTime(receiveTime);
			WxtbUser wxtbUser = cacheService.getWxtbUser(pcbReceiveOrder.getReceiver());
			if (wxtbUser != null) {
				pcbReceiveOrderResponse.setReceiver(wxtbUser.getUsername());
			}
			ProductOrder productOrder = pcbOrders.get(pcbReceiveOrder.getOrderNum());
			if (productOrder == null) {
				continue;
			}
			PcbInStorageOrder pcbInStorageOrder = pcbInstorageOrderMap.get(pcbReceiveOrder.getOrderNumber());
			if (pcbInStorageOrder == null) {
				continue;
			}
			String boardPly = productOrder.getBoardPly() == null ? "-" : productOrder.getBoardPly();
			String boardLong = "-";
			String boardWide = "-";
			// 如果检验单为拼版，则取对应拼版的长宽
			if (Objects.equals("JoinBoardOrder", pcbInStorageOrder.getOrderType())) {
				String joinCode = pcbInStorageOrder.getJoinBoardOrderCode();
				JoinBoardOrder joinBoardOrder = joinMap.get(joinCode);
				if (joinBoardOrder != null) {
					BigDecimal boardLongDec = joinBoardOrder.getJoinBoardLong();
					if (boardLongDec != null) {
						boardLong = boardLongDec.toString();
					}
					BigDecimal boardWideDec = joinBoardOrder.getJoinBoardWide();
					if (boardWideDec != null) {
						boardWide = boardWideDec.toString();
					}
				}
			} else {
				BigDecimal boardLongDec = productOrder.getBoardLong();
				if (boardLongDec != null) {
					boardLong = boardLongDec.toString();
				}
				BigDecimal boardWideDec = productOrder.getBoardWide();
				if (boardWideDec != null) {
					boardWide = boardWideDec.toString();
				}
			}
			pcbReceiveOrderResponse.setSize(boardLong + "*" + boardWide + "*" + boardPly);
			final EdaGuest guest = cacheService.getGuest(productOrder.getGuestCode());
			if (guest != null) {
				pcbReceiveOrderResponse.setGuestName(guest.getShortNameCn());
			}
		}
		response.put("total", total);
		response.put("rows", pcbReceiveOrderResponses);
		return response;
	}

	@Override
	public Map<String, Object> modifyReceiveOrder(AkAuthUser wxtbUser, Integer id, Integer receiveOrderType, List<Integer> contentIds) {
		Map<String, Object> response = new HashMap<>();
		PcbReceiveOrder pcbReceiveOrder = pcbReceiveOrderMapper.selectByPrimaryKey(id);
		if (pcbReceiveOrder == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", ErrorCodeEnum.SYSTEM_ERROR.getDescr());
			return response;
		}
//		pcbReceiveOrder.setSpotCheckNumPcs(receiveProductBody.getInStoragePcs());
		String receiveCode = wxtbUser.getUserCode();
		Date receiveDate = new Date();
		pcbReceiveOrder.setReceiver(receiveCode);
		pcbReceiveOrder.setReceiveTime(receiveDate);
		pcbReceiveOrder.setReceiveType(receiveOrderType);
		if (contentIds == null) {
			contentIds = new ArrayList<>();
		}
		ProductOrder productOrder = productOrderMapper.selectByOrderNum(pcbReceiveOrder.getOrderNum());
		if (productOrder == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", "缺少工单数据");
			return response;
		}
		PcbInStorageOrder pcbInStorageOrder = pcbInStorageOrderMapper
				.selectByOrderNumber(pcbReceiveOrder.getOrderNumber());
		if (pcbInStorageOrder == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", "缺少入库单数据");
			return response;
		}
		JoinBoardOrder joinBoardOrder = null;
		String joinBoardCode = pcbInStorageOrder.getJoinBoardOrderCode();
		if (!StringUtil.isBlank(joinBoardCode)) {
			List<String> joinBoardCodes = new ArrayList<>();
			joinBoardCodes.add(joinBoardCode);
			List<JoinBoardOrder> joinBoardOrders = joinBoardOrderMapper.listByCodes(joinBoardCodes);
			if (joinBoardOrders != null && !joinBoardOrders.isEmpty()) {
				joinBoardOrder = joinBoardOrders.get(0);
			}
		}
		Date now = new Date();
		String contentIdsJson = JsonUtil.obj2json(contentIds);
		pcbReceiveOrder.setContentId(contentIdsJson);
		// 更新检验单
		PcbCheckOrder record = pcbCheckOrderMapper.selectByCheckNum(pcbReceiveOrder.getCheckNum());
		if (record == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", ErrorCodeEnum.SYSTEM_ERROR.getDescr());
			return response;
		}
		record.setContentId(contentIdsJson);
		if (contentIds.contains(CheckContentEnum.CHECK.getCode())) {
			// 如果检验内容包含检验，并且之前检验单状态为不检验,改变状态为待检验
			if (record.getCheckStateId() == CheckStateEnum.NO_CHECK.getCode()) {
				record.setCheckState(CheckStateEnum.TO_CHECK.getDescr());
				record.setCheckStateId(CheckStateEnum.TO_CHECK.getCode());
			}
		} else {
			// 如果检验内容包含不检验，则设置状态为不检验
			record.setCheckState(CheckStateEnum.NO_CHECK.getDescr());
			record.setCheckStateId(CheckStateEnum.NO_CHECK.getCode());
			PcbUnqualified pcbUnqualified = pcbUnqualifiedMapper.selectByCheckNumber(pcbReceiveOrder.getCheckNum()); 
			if (pcbUnqualified != null) {
				pcbUnqualified.setEnable(0);
				pcbUnqualified.setUpdateTime(now);
				pcbUnqualifiedMapper.updateByPrimaryKeySelective(pcbUnqualified);
			}
		}
//		record.setInAmountPcs(receiveProductBody.getInStoragePcs());
		record.setReceiveDate(receiveDate);
		record.setUpdateTime(null);
		pcbCheckOrderMapper.updateByPrimaryKeySelective(record);
		// 更新报告单
		PcbCheckReport pcbCheckReport = pcbCheckReportMapper.selectByReportNum(pcbReceiveOrder.getReportNum());
		if (pcbCheckReport == null) {
			// 如果检验内容包含报告，生成报告单，状态为待创建
			if (contentIds.contains(CheckContentEnum.REPORT.getCode())) {
				Map<String, Object> res = inStorageService.recordCheckReport(pcbInStorageOrder, productOrder,
						joinBoardOrder, now, record.getCheckNum());
				pcbCheckReport = (PcbCheckReport) res.get("order");
				if (pcbCheckReport != null) {
					pcbCheckReportMapper.insertSelective(pcbCheckReport);
					pcbReceiveOrder.setReportNum(pcbCheckReport.getReportNum());
				} else {
					response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
					response.put("msg", res.get("msg"));
					return response;
				}
			}
		} else if (!contentIds.contains(CheckContentEnum.REPORT.getCode())) {
			// 如果检验内容不包含报告，并且之前生成过报告，删除以前记录
			String reportNum = pcbReceiveOrder.getReportNum();
			pcbReportIntroMapper.deleteByReportNum(reportNum);
			pcbReportApertureCheckMapper.deleteByReportNum(reportNum);
			pcbReportCertificationMapper.deleteByReportNum(reportNum);
			pcbReportDetailMapper.deleteByReportNum(reportNum);
			pcbReportIntegrityMapper.deleteByReportNum(reportNum);
			pcbReportIntroMapper.deleteByReportNum(reportNum);
			pcbReportSpecialDimensionMapper.deleteByReportNum(reportNum);
			pcbReportVcutMapper.deleteByReportNum(reportNum);
			pcbReportSpecialImpedanceMapper.deleteByReportNum(reportNum);
			pcbCheckReportMapper.deleteByPrimaryKey(pcbCheckReport.getId());
			pcbReceiveOrder.setReportNum(null);
		} 
		pcbReceiveOrder.setUpdateTime(now);
		pcbReceiveOrderMapper.updateByPrimaryKey(pcbReceiveOrder);
		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		return response;
	}

	@Override
	public Map<String, Object> deleteReceiveOrder(Integer id) {
		Map<String, Object> response = new HashMap<>();
		PcbReceiveOrder pcbReceiveOrder =  pcbReceiveOrderMapper.selectByPrimaryKey(id);
		if (pcbReceiveOrder == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", ErrorCodeEnum.SYSTEM_ERROR.getDescr());
			return response;
		}
		PcbCheckOrder record = pcbCheckOrderMapper.selectByCheckNum(pcbReceiveOrder.getCheckNum());
		PcbCheckReport pcbCheckReport = pcbCheckReportMapper.selectByReportNum(pcbReceiveOrder.getReportNum());
		if (record == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", ErrorCodeEnum.SYSTEM_ERROR.getDescr());
			return response;
		}
		Integer checkStateId = record.getCheckStateId();
		if (Objects.equals(checkStateId, CheckStateEnum.TO_AUDIT.getCode())
				|| Objects.equals(checkStateId, CheckStateEnum.AUDITING.getCode())
				|| Objects.equals(checkStateId, CheckStateEnum.COMPLETE.getCode())) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", "检验单处在" + record.getCheckState() + "状态");
			return response;
		} 
		if (pcbCheckReport != null) {
			Integer reportStateId = pcbCheckReport.getStatusId();
			if (Objects.equals(reportStateId, ReportStateEnum.TO_PROVE.getCode())
					|| Objects.equals(reportStateId, ReportStateEnum.PROVING.getCode())
					|| Objects.equals(reportStateId, ReportStateEnum.COMPLETE.getCode())) {
				response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
				response.put("msg", "检验报告处在" + pcbCheckReport.getStatus() + "状态");
				return response;
			} 
			pcbCheckReport.setEnable(false);
			pcbCheckReport.setUpdateTime(null);
			pcbCheckReportMapper.updateByPrimaryKeySelective(pcbCheckReport);
		}
		record.setEnable(false);
		record.setUpdateTime(null);
		pcbCheckOrderMapper.updateByPrimaryKeySelective(record);
		pcbReceiveOrder.setEnable(false);
		pcbReceiveOrder.setUpdateTime(null);
		pcbReceiveOrderMapper.updateByPrimaryKeySelective(pcbReceiveOrder);
		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		response.put("msg", "删除成功");
		return response;
	}

}
