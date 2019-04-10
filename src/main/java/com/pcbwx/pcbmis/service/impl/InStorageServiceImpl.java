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
import com.pcbwx.pcbmis.bean.InStorageBean;
import com.pcbwx.pcbmis.bean.ReceiveProductBody;
import com.pcbwx.pcbmis.bean.request.ReceiveProductBodyNew;
import com.pcbwx.pcbmis.component.CacheService;
import com.pcbwx.pcbmis.dao.JoinBoardOrderMapper;
import com.pcbwx.pcbmis.dao.PcbCheckOrderMapper;
import com.pcbwx.pcbmis.dao.PcbCheckReportMapper;
import com.pcbwx.pcbmis.dao.PcbInStorageOrderMapper;
import com.pcbwx.pcbmis.dao.PcbReceiveOrderMapper;
import com.pcbwx.pcbmis.dao.PcbReportCertificationMapper;
import com.pcbwx.pcbmis.dao.PcbReportIntroMapper;
import com.pcbwx.pcbmis.dao.PcbUnqualifiedMapper;
import com.pcbwx.pcbmis.dao.ProductOrderMapper;
import com.pcbwx.pcbmis.enums.CheckContentEnum;
import com.pcbwx.pcbmis.enums.CheckStateEnum;
import com.pcbwx.pcbmis.enums.DictionaryEnum;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.enums.ReportStateEnum;
import com.pcbwx.pcbmis.model.Dictionary;
import com.pcbwx.pcbmis.model.EdaGuest;
import com.pcbwx.pcbmis.model.FactoryInfo;
import com.pcbwx.pcbmis.model.JoinBoardOrder;
import com.pcbwx.pcbmis.model.JoinBoardWay;
import com.pcbwx.pcbmis.model.PcbCheckOrder;
import com.pcbwx.pcbmis.model.PcbCheckReport;
import com.pcbwx.pcbmis.model.PcbInStorageOrder;
import com.pcbwx.pcbmis.model.PcbReceiveOrder;
import com.pcbwx.pcbmis.model.ProductOrder;
import com.pcbwx.pcbmis.model.WxtbUser;
import com.pcbwx.pcbmis.service.InStorageService;
import com.pcbwx.pcbmis.service.SupportService;
import com.pcbwx.pcbmis.service.UtilService;
import com.pcbwx.pcbmis.utils.DateTimeUtil;
import com.pcbwx.pcbmis.utils.JsonUtil;
import com.pcbwx.pcbmis.utils.StringUtil;

@Service("inStorageService")
public class InStorageServiceImpl implements InStorageService{
	
	private Logger logger = Logger.getLogger(InStorageServiceImpl.class);
	
	@Autowired
	private CacheService cacheService;
	@Autowired
	private SupportService supportService;
	@Autowired
	private UtilService utilService;
	
	@Autowired
	private PcbInStorageOrderMapper pcbInStorageOrderMapper;
	@Autowired
	private ProductOrderMapper productOrderMapper;
	@Autowired
	private JoinBoardOrderMapper JoinBoardOrderMapper;
	@Autowired
	private PcbCheckOrderMapper pcbCheckOrderMapper;
	@Autowired
	private PcbCheckReportMapper pcbCheckReportMapper;
	@Autowired
	private PcbReceiveOrderMapper pcbReceiveOrderMapper;
	@Autowired
	private PcbUnqualifiedMapper pcbUnqualifiedMapper;
	@Autowired
	private PcbReportCertificationMapper pcbReportCertificationMapper;
	@Autowired
	private PcbReportIntroMapper pcbReportIntroMapper;

	@Override
	public Map<String, Object>getInStorageList(String orderNum, String guestName,
			String boardName, Integer inAmountNum, String inStorageDateStartStr,
			String inStorageDateEndStr, Integer factoryId, Integer gradeId, Integer page, Integer rows) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<String> guestCodes = utilService.getEdaGuest(guestName);
		Date inStorageDateStart = DateTimeUtil.dateStr2date(inStorageDateStartStr);
		Date inStorageDateEnd = DateTimeUtil.dateStr2date(inStorageDateEndStr);
		inStorageDateEnd = DateTimeUtil.getTheDayLastTime(inStorageDateEnd);
		Integer start = rows * (page - 1);
		List<PcbInStorageOrder> pcbInStorageOrders = pcbInStorageOrderMapper
				.selectByParam(orderNum, boardName, inAmountNum,
						inStorageDateStart, inStorageDateEnd, factoryId,
						gradeId, guestCodes, start, rows);
		Integer total = pcbInStorageOrderMapper.getSelectByParamNum(orderNum,
				boardName, inAmountNum, inStorageDateStart, inStorageDateEnd,
				factoryId, gradeId, guestCodes);
		if (total == 0) {
			response.put("total", total);
			response.put("rows", new ArrayList<>());
			return response;
		}
		List<String> orderNums = new ArrayList<String>();
		List<String> joinBoards = new ArrayList<String>();
		Set<String> inStorageNums = new HashSet<String>();
		for (PcbInStorageOrder pcbInStorageOrder : pcbInStorageOrders) {
			orderNums.add(pcbInStorageOrder.getProductOrderNum());
			inStorageNums.add(pcbInStorageOrder.getOrderNumber());
			if (Objects.equals("JoinBoardOrder", pcbInStorageOrder.getOrderType())) {
				joinBoards.add(pcbInStorageOrder.getJoinBoardOrderCode());
			}
		}
		List<ProductOrder> productOrders = productOrderMapper.listByOrderNum(orderNums);
		Map<String, ProductOrder> pcbOrders = new HashMap<String, ProductOrder>();
		for (ProductOrder productOrder : productOrders) {
			pcbOrders.put(productOrder.getOrderNum(), productOrder);
		}

		Map<String, JoinBoardOrder> joinMap = new HashMap<String, JoinBoardOrder>();
		if (!joinBoards.isEmpty()) {
			List<JoinBoardOrder> joinBoardOrders = JoinBoardOrderMapper.listByCodes(joinBoards);
			if (joinBoardOrders != null && joinBoardOrders.size() != 0) {
				for (JoinBoardOrder joinBoardOrder : joinBoardOrders) {
					joinMap.put(joinBoardOrder.getJoinBoardCode(), joinBoardOrder);
				}
			}
		}
		List<PcbCheckOrder> pcbCheckOrders = pcbCheckOrderMapper.listCheckOrderByInstorageNum(inStorageNums);
		Map<String, String> contentMap = new HashMap<String, String>();
		for (PcbCheckOrder pcbCheckOrder : pcbCheckOrders) {
			// 检验内容
			String content = utilService.getContent(pcbCheckOrder.getContentId());
			contentMap.put(pcbCheckOrder.getOrderNumber(), content);
		}
		List<InStorageBean> inStorageBeans = new ArrayList<InStorageBean>();
		for (PcbInStorageOrder pcbInStorageOrder : pcbInStorageOrders) {
			String productOrderNum = pcbInStorageOrder.getProductOrderNum();
			String orderNumber = pcbInStorageOrder.getOrderNumber();
			InStorageBean inStorageBean = new InStorageBean();
			inStorageBean.setInStorageNum(orderNumber);
			inStorageBean.setContent(contentMap.get(orderNumber));
			inStorageBean.setProductOrderNum(productOrderNum);
			inStorageBean.setBoardName(pcbInStorageOrder.getBoardName());
			inStorageBean.setBoardNameNew(pcbInStorageOrder.getBoardNameNew());
			inStorageBean.setInAmountPCS(pcbInStorageOrder.getInAmountPcs());
			String inStorageDateStr = DateTimeUtil.date2dateStr(pcbInStorageOrder.getInStorageDate());
			Date receiveDate = pcbInStorageOrder.getReceiveDate();
			String receiveDateStr = DateTimeUtil.date2dateStr(receiveDate);
			String receiveTime = DateTimeUtil.date2dateTimeStr(receiveDate, "HH:mm:ss");
			inStorageBean.setInStorageDate(inStorageDateStr);
			inStorageBean.setReceiveDate(receiveDateStr);
			inStorageBean.setReceiveTime(receiveTime);
			String receiver = pcbInStorageOrder.getReceiver();
			if (receiver != null) {
				WxtbUser wxtbUser = cacheService.getWxtbUser(receiver);
				if (wxtbUser != null) {
					inStorageBean.setReceiver(wxtbUser.getUsername());
				}
			}
			ProductOrder productOrder = pcbOrders.get(productOrderNum);
			if (productOrder == null) {
				continue;
			}
			final EdaGuest guest = cacheService.getGuest(productOrder.getGuestCode());
	    	if (guest != null) {
	    		inStorageBean.setGuestName(guest.getShortNameCn());
	    	}
	    	FactoryInfo factory = cacheService.getFactory(productOrder.getFactoryId());
	    	if (factory != null) {
	    		inStorageBean.setFactoryName(factory.getFactoryName());
	    	}
	    	Dictionary dictGrade = cacheService.getDictionary(DictionaryEnum.CATEGORY_GRADE, productOrder.getCategoryGradeId());
	    	if (dictGrade != null) {
	    		inStorageBean.setGrade(dictGrade.getValueStr());    		
	    	}
	    	inStorageBean.setProductNumPCS(productOrder.getProductionNumPcs());
	    	inStorageBean.setProductNumSet(productOrder.getProductionNumSet());
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
			}else {
				BigDecimal boardLongDec = productOrder.getBoardLong();
	    		if (boardLongDec != null) {
	    			boardLong = boardLongDec.toString();
				}
	    		BigDecimal boardWideDec = productOrder.getBoardWide();
	    		if (boardWideDec != null) {
	    			boardWide = boardWideDec.toString();
				}
			}
	    	inStorageBean.setBoardSize(boardLong + "*" + boardWide + "*" + boardPly);
	    	inStorageBeans.add(inStorageBean);
		}
		response.put("total", total);
		response.put("rows", inStorageBeans);
		return response;
	}


	@Override
	public Map<String, Object> receiveProductNew(AkAuthUser wxtbUser, String orderNumber,
			ReceiveProductBodyNew receiveProductBody) {
		Map<String, Object> response = new HashMap<>();
		PcbInStorageOrder pcbInStorageOrder = pcbInStorageOrderMapper.selectByOrderNumber(orderNumber);
		if (pcbInStorageOrder == null || receiveProductBody == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", ErrorCodeEnum.SYSTEM_ERROR.getDescr());
			return response;
		}
		String receiveCode = wxtbUser.getUserCode();
		Date ReceiveDate = new Date();
		pcbInStorageOrder.setReceiver(receiveCode);
		pcbInStorageOrder.setReceiveDate(ReceiveDate);
		List<Integer> contentIds = receiveProductBody.getContentIds();
		if (contentIds == null || contentIds.isEmpty()) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", "无检验内容");
			return response;
		}
		ProductOrder productOrder = productOrderMapper.selectByOrderNum(pcbInStorageOrder.getProductOrderNum());
		if (productOrder == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", "缺少工单数据");
			return response;
		}
		JoinBoardOrder joinBoardOrder = null;
		String joinBoardCode = pcbInStorageOrder.getJoinBoardOrderCode();
		if (!StringUtils.isBlank(joinBoardCode)) {
			List<String> joinBoardCodes = new ArrayList<>();
			joinBoardCodes.add(joinBoardCode);
			List<JoinBoardOrder> joinBoardOrders = JoinBoardOrderMapper.listByCodes(joinBoardCodes);
			if (joinBoardOrders != null && !joinBoardOrders.isEmpty()) {
				joinBoardOrder = joinBoardOrders.get(0);
			}
		}
		String msg = "";
		String contentIdsJson = JsonUtil.obj2json(contentIds);
		// 生成检验单
		Map<String, Object> res = generateCheck(pcbInStorageOrder, productOrder, joinBoardOrder, contentIdsJson, ReceiveDate, receiveProductBody.getInStoragePcs());
		PcbCheckOrder record = (PcbCheckOrder) res.get("order");
		if (record == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", res.get("msg"));
			return response;
		}
		// 如果检验内容包含检验，检验单内容为待检验
		if (contentIds.contains(CheckContentEnum.CHECK.getCode())){
			if (record.getCheckStateId() == CheckStateEnum.NO_CHECK.getCode()) {
				record.setCheckState(CheckStateEnum.TO_CHECK.getDescr());
				record.setCheckStateId(CheckStateEnum.TO_CHECK.getCode());
			}
		}
		pcbCheckOrderMapper.insertSelective(record);
		// 如果检验内容包含报告，生成报告单，状态为待创建
		PcbCheckReport pcbCheckReport = null;
		String reportNum = null;
		if (contentIds.contains(CheckContentEnum.REPORT.getCode())) {
			Map<String, Object> resReport = recordCheckReport(pcbInStorageOrder, productOrder, joinBoardOrder,
					ReceiveDate, record.getCheckNum());
			pcbCheckReport = (PcbCheckReport) resReport.get("order");
			if (pcbCheckReport != null) {
				reportNum = pcbCheckReport.getReportNum();
				pcbCheckReportMapper.insertSelective(pcbCheckReport);
			} else {
				response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
				response.put("msg", res.get("msg"));
				return response;
			}
		}
		pcbInStorageOrderMapper.updateByPrimaryKeySelective(pcbInStorageOrder);
		// 生成接收单
		generateReceiverOrder(productOrder, joinBoardOrder, pcbInStorageOrder ,record.getCheckNum(), reportNum,
				ReceiveDate, receiveCode, receiveProductBody.getReceiveOrderType(), contentIdsJson, receiveProductBody.getInStoragePcs());
		supportService.doOperateLog("接受", productOrder.getOrderNum(), "产品", wxtbUser.getUsername());
		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		response.put("msg", msg);
		return response;
	}
	

	@Override
	public ErrorCodeEnum modifyBoardName(String orderNumber, String boardname) {
		PcbInStorageOrder pcbInStorageOrder = pcbInStorageOrderMapper.selectByOrderNumber(orderNumber);
		if (pcbInStorageOrder == null) {
			return ErrorCodeEnum.SYSTEM_ERROR;
		}
		// 更新入库单
		pcbInStorageOrder.setBoardNameNew(boardname);
		pcbInStorageOrderMapper.updateByPrimaryKey(pcbInStorageOrder);
		// 更新检验单
		List<PcbCheckOrder> pcbCheckOrders = pcbCheckOrderMapper.selectByOrderNumer(orderNumber);
		for (PcbCheckOrder pcbCheckOrder : pcbCheckOrders) {
			pcbCheckOrder.setBoardName(boardname);
			pcbCheckOrderMapper.updateByPrimaryKeySelective(pcbCheckOrder);
		}
		// 更新不合格品处置单
		pcbUnqualifiedMapper.updateBoardNameByOrderNumber(boardname, orderNumber);
		// 更新报告单
		List<PcbCheckReport> pcbCheckReports = pcbCheckReportMapper.selectByOrderNumber(orderNumber);
		for (PcbCheckReport pcbCheckReport : pcbCheckReports) {
			pcbCheckReport.setBoardName(boardname);
			pcbCheckReportMapper.updateByPrimaryKeySelective(pcbCheckReport);
		}
		// 更新合格证
		pcbReportCertificationMapper.updateBoardNameByOrderNumber(boardname, orderNumber);
		// 更新检验报告
		pcbReportIntroMapper.updateBoardNameByOrderNumber(boardname, orderNumber);
		return ErrorCodeEnum.SUCCESS;
	}
	
	/*
	 * 生成接收单
	 */
	private void generateReceiverOrder(ProductOrder productOrder, JoinBoardOrder joinBoardOrder, PcbInStorageOrder pcbInStorageOrder,String checkNum,
			String reportNum, Date receiveDate, String receiverCode, Integer receiveOrderType, String contentJson, Integer spotNum) {
		PcbReceiveOrder pcbReceiveOrder = new PcbReceiveOrder();
		pcbReceiveOrder.setCategoryGradeId(productOrder.getCategoryGradeId());
		pcbReceiveOrder.setContentId(contentJson);
		pcbReceiveOrder.setCreateTime(receiveDate);
		pcbReceiveOrder.setFactoryId(productOrder.getFactoryId());
		pcbReceiveOrder.setOrderNum(productOrder.getOrderNum());
		pcbReceiveOrder.setProductionNumPcs(productOrder.getProductionNumPcs());
		pcbReceiveOrder.setProductionNumSet(productOrder.getProductionNumSet());
		pcbReceiveOrder.setReceiver(receiverCode);
		pcbReceiveOrder.setReceiveTime(receiveDate);
		pcbReceiveOrder.setReceiveType(receiveOrderType);
		pcbReceiveOrder.setSpotCheckNumPcs(spotNum);
		pcbReceiveOrder.setCheckNum(checkNum);
		pcbReceiveOrder.setReportNum(reportNum);
		pcbReceiveOrder.setOrderNumber(pcbInStorageOrder.getOrderNumber());
		pcbReceiveOrderMapper.insertSelective(pcbReceiveOrder);
	}
	
	/**
	 * 生成检验单
	 * @param pcbInStorageOrder	入库单
	 * @param productOrder		工单
	 * @param joinBoardOrder	拼版工单
	 * @param contentIds		检验内容id结合json数据
	 * @param userCode			创建人code
	 * @param now				现在时间
	 * @return
	 */
	@SuppressWarnings("unused")
	private Map<String, Object> generateCheckNew(PcbInStorageOrder pcbInStorageOrder, ProductOrder productOrder,
			JoinBoardOrder joinBoardOrder, String contentIds, Date now, Integer receiveNumber) {
		PcbCheckOrder record = new PcbCheckOrder();
		Integer inAmountPCS = pcbInStorageOrder.getInAmountPcs();
		record.setCheckState(CheckStateEnum.NO_CHECK.getDescr());
		record.setCheckStateId(CheckStateEnum.NO_CHECK.getCode());
		record.setAmountCheckoutPcs(pcbInStorageOrder.getAmountCheckoutPcs());
		record.setAmountCheckoutSet(pcbInStorageOrder.getAmountCheckoutSet());
		record.setBatchNumber(pcbInStorageOrder.getBatchNumber());
		record.setBoardName(pcbInStorageOrder.getBoardNameNew());
		record.setContentId(contentIds);
		record.setCreaterCode(pcbInStorageOrder.getCreaterCode());
		record.setCreateTime(now);
		record.setInAmountPcs(inAmountPCS);
		record.setInAmountSet(pcbInStorageOrder.getInAmountSet());
		record.setInStorageDate(pcbInStorageOrder.getInStorageDate());
		record.setJoinBoardOrderCode(pcbInStorageOrder.getJoinBoardOrderCode());
		record.setOrderNumber(pcbInStorageOrder.getOrderNumber());
		record.setOrderType(pcbInStorageOrder.getOrderType());
		record.setProductOrderNum(pcbInStorageOrder.getProductOrderNum());
		record.setProductPeriod(pcbInStorageOrder.getProductPeriod());
		record.setReceiveDate(pcbInStorageOrder.getReceiveDate());
		record.setSpotCheckNumPcs(receiveNumber);
		String msg = "";
		// 生成检验单号
		if (record.getInAmountPcs() != null && record.getInAmountPcs() > 0
				&& productOrder.getProductionNumPcs() != null) {
			// 几种拼入库
			if (!StringUtils.isBlank(record.getJoinBoardOrderCode())) {
				final JoinBoardWay joinBoardWay = cacheService.getJoinBoardWay(productOrder.getJoinBoardWayId());
				if (joinBoardWay != null && joinBoardWay.getOrderTypeNum() != null) {
						//&& joinBoardWay.getOrderTypeNum() > 1) {
					if (record.getInAmountPcs() * joinBoardWay.getOrderTypeNum() < productOrder.getProductionNumPcs()) { // 部分入库
						record.setCheckNum(supportService.fetchPcbJoinSubCheckOrderNum(now, record.getJoinBoardOrderCode()));
					} else {
						record.setCheckNum(supportService.fetchPcbCheckOrderNum(now));
					}
				} else {
					msg = "拼板方式信息异常.id=" + productOrder.getJoinBoardWayId();
				}
			} else if (record.getInAmountPcs() < productOrder.getProductionNumPcs()) { // 部分入库
				record.setCheckNum(supportService.fetchPcbSubCheckOrderNum(now, record.getProductOrderNum()));
			} else {
				record.setCheckNum(supportService.fetchPcbCheckOrderNum(now));
			}
		}else {
			msg = "入库数量为空";
		}
		Map<String, Object> response = new HashMap<String, Object>();
		if (StringUtil.isEmpty(msg)) {
			response.put("order", record);
		}else {
			response.put("msg", msg);
		}
		return response;
	}
	
	
	
	/**
	 * 生成报告单
	 * @param inStorageOrder	入库单
	 * @param productOrder		工单
	 * @param joinBoardOrder	拼版工单
	 * @param date				现在时间
	 * @param checkNum			检验单号
	 */
	@Override
	public Map<String, Object> recordCheckReport(PcbInStorageOrder inStorageOrder, ProductOrder productOrder,
			JoinBoardOrder joinBoardOrder, Date date, String checkNum) {
		String reportNum = null;
		if (inStorageOrder == null || inStorageOrder.getInAmountPcs() == null
				|| inStorageOrder.getInAmountPcs() <= 0 || productOrder.getProductionNumPcs() == null) {
			return null;
		}
		String msg = "";
		// 生成报告单
//			final JoinBoardWay joinBoardWay = cacheService.getJoinBoardWay(productOrder.getJoinBoardWayId());
//			if (joinBoardWay != null && joinBoardWay.getOrderTypeNum() != null) {
//					// && joinBoardWay.getOrderTypeNum() > 1
//				if (inStorageOrder.getInAmountPcs() * joinBoardWay.getOrderTypeNum() < productOrder
//						.getProductionNumPcs()) { // 部分入库
//					reportNum = supportService.fetchPcbJoinSubReportOrderNum(date,
//							inStorageOrder.getJoinBoardOrderCode());
//				} else {
//					reportNum = supportService.fetchPcbReportNum(date);
//				}
//			} else {
//				logger.info("拼板方式信息异常.id=" + productOrder.getJoinBoardWayId());
//				msg = "获取拼板方式信息失败";
//			}
//		} else if (inStorageOrder.getInAmountPcs() < productOrder.getProductionNumPcs()) { // 部分入库
		reportNum = supportService.fetchPcbSubReportOrderNum(date, inStorageOrder.getProductOrderNum());
//		} else {
//			reportNum = supportService.fetchPcbReportNum(date);
		if (StringUtil.isEmpty(reportNum)) {
			return null;
		}
		logger.info("生成报告单-" + reportNum);
		PcbCheckReport pcbCheckReport = new PcbCheckReport();
		pcbCheckReport.setOrderNumber(inStorageOrder.getOrderNumber());
		pcbCheckReport.setReportNum(reportNum);
		pcbCheckReport.setCheckNum(checkNum);
		pcbCheckReport.setOrderNum(inStorageOrder.getProductOrderNum());
		pcbCheckReport.setOrderType(inStorageOrder.getOrderType());
		pcbCheckReport.setJoinBoardOrderCode(inStorageOrder.getJoinBoardOrderCode());
		pcbCheckReport.setGuestCode(productOrder.getGuestCode());
		pcbCheckReport.setStatus(ReportStateEnum.TO_CREATE.getDescr());
		pcbCheckReport.setStatusId(ReportStateEnum.TO_CREATE.getCode());
		pcbCheckReport.setBoardName(inStorageOrder.getBoardNameNew());
		if (joinBoardOrder != null) {
			pcbCheckReport.setBoardLong(joinBoardOrder.getJoinBoardLong());
			pcbCheckReport.setBoardWide(joinBoardOrder.getJoinBoardWide());
		} else {
			pcbCheckReport.setBoardLong(productOrder.getBoardLong());
			pcbCheckReport.setBoardWide(productOrder.getBoardWide());
		}
		pcbCheckReport.setFactoryId(productOrder.getFactoryId());
		pcbCheckReport.setCategoryGradeId(productOrder.getCategoryGradeId());
		pcbCheckReport.setBoardPly(productOrder.getBoardPly());
		pcbCheckReport.setProductionNumPcs(productOrder.getProductionNumPcs());
		pcbCheckReport.setInnerCreateTime(inStorageOrder.getInnerCreateTime());
		pcbCheckReport.setCreateTime(date);
		Map<String, Object> response = new HashMap<String, Object>();
		if (StringUtil.isEmpty(msg)) {
			response.put("order", pcbCheckReport);
		}else {
			response.put("msg", msg);
		}
		return response;
	}
	
	/**
	 * 生成检验单
	 * @param pcbInStorageOrder	入库单
	 * @param productOrder		工单
	 * @param joinBoardOrder	拼版工单
	 * @param contentIds		检验内容id结合json数据
	 * @param userCode			创建人code
	 * @param now				现在时间
	 * @return
	 */
	private Map<String, Object> generateCheck(PcbInStorageOrder pcbInStorageOrder, ProductOrder productOrder,
			JoinBoardOrder joinBoardOrder, String contentIds, Date now, Integer inAmountPCS) {
		PcbCheckOrder record = new PcbCheckOrder();
		//Integer inAmountPCS = pcbInStorageOrder.getInAmountPcs();
		record.setCheckState(CheckStateEnum.NO_CHECK.getDescr());
		record.setCheckStateId(CheckStateEnum.NO_CHECK.getCode());
		record.setAmountCheckoutPcs(pcbInStorageOrder.getAmountCheckoutPcs());
		record.setAmountCheckoutSet(pcbInStorageOrder.getAmountCheckoutSet());
		record.setBatchNumber(pcbInStorageOrder.getBatchNumber());
		record.setBoardName(pcbInStorageOrder.getBoardNameNew());
		record.setContentId(contentIds);
		record.setCreaterCode(pcbInStorageOrder.getCreaterCode());
		record.setCreateTime(now);
		record.setInAmountPcs(inAmountPCS);
		record.setInAmountSet(pcbInStorageOrder.getInAmountSet());
		record.setInStorageDate(pcbInStorageOrder.getInStorageDate());
		record.setJoinBoardOrderCode(pcbInStorageOrder.getJoinBoardOrderCode());
		record.setOrderNumber(pcbInStorageOrder.getOrderNumber());
		record.setOrderType(pcbInStorageOrder.getOrderType());
		record.setProductOrderNum(pcbInStorageOrder.getProductOrderNum());
		record.setProductPeriod(pcbInStorageOrder.getProductPeriod());
		record.setReceiveDate(pcbInStorageOrder.getReceiveDate());
		Integer spotNum = null;
		String grade = "";
		Integer gradeId = productOrder.getCategoryGradeId();
		if (gradeId != null) {
			Dictionary dictionary = cacheService.getDictionary(DictionaryEnum.CATEGORY_GRADE, gradeId);
			if (dictionary != null) {
				grade = dictionary.getValueStr();
			}
		}
		// 计算抽检数量
		if (inAmountPCS != null && inAmountPCS != 0 && grade != null) {
			if (!grade.contains("民") && !grade.contains("-")) {
				if (inAmountPCS <= 5) {
					spotNum = inAmountPCS;
				}else if (inAmountPCS <= 50) {
					spotNum = 5;
				}else if (inAmountPCS <= 90) {
					spotNum = 7;
				}else if (inAmountPCS <= 150) {
					spotNum = 11;
				}else if (inAmountPCS <= 280) {
					spotNum = 13;
				}else if (inAmountPCS <= 500) {
					spotNum = 16;
				}else if (inAmountPCS <= 1200) {
					spotNum = 19;
				}else {
					spotNum = 23;
				}
			}else if (grade.contains("3")) {
				if (inAmountPCS <= 13) {
					spotNum = inAmountPCS;
				}else if (inAmountPCS <= 90) {
					spotNum = 13;
				}else if (inAmountPCS <= 150) {
					spotNum = 19;
				}else if (inAmountPCS <= 500) {
					spotNum = 29;
				}else if (inAmountPCS <= 1200) {
					spotNum = 34;
				}else if (inAmountPCS <= 3200){
					spotNum = 42;
				}else if (inAmountPCS <= 10000) {
					spotNum = 50;
				}else {
					spotNum = 60;
				}
			}else {
				if (inAmountPCS <= 8) {
					spotNum = inAmountPCS;
				}else if (inAmountPCS <= 50) {
					spotNum = 8;
				}else if (inAmountPCS <= 90) {
					spotNum = 13;
				}else if (inAmountPCS <= 280) {
					spotNum = 19;
				}else if (inAmountPCS <= 500) {
					spotNum = 21;
				}else if (inAmountPCS <= 1200) {
					spotNum = 27;
				}else if (inAmountPCS <= 3200){
					spotNum = 35;
				}else if (inAmountPCS <= 10000) {
					spotNum = 38;
				}else {
					spotNum = 46;
				}
			}
			record.setSpotCheckNumPcs(spotNum);
		}
		String msg = "";
		// 生成检验单号
		if (record.getInAmountPcs() != null && record.getInAmountPcs() > 0
				&& productOrder.getProductionNumPcs() != null) {
//			// 几种拼入库
//			if (!StringUtils.isBlank(record.getJoinBoardOrderCode())) {
//				final JoinBoardWay joinBoardWay = cacheService.getJoinBoardWay(productOrder.getJoinBoardWayId());
//				if (joinBoardWay != null && joinBoardWay.getOrderTypeNum() != null) {
//						//&& joinBoardWay.getOrderTypeNum() > 1) {
//					if (record.getInAmountPcs() * joinBoardWay.getOrderTypeNum() < productOrder.getProductionNumPcs()) { // 部分入库
//						record.setCheckNum(supportService.fetchPcbJoinSubCheckOrderNum(now, record.getJoinBoardOrderCode()));
//					} else {
//						record.setCheckNum(supportService.fetchPcbCheckOrderNum(now));
//					}
//				} else {
//					msg = "拼板方式信息异常.id=" + productOrder.getJoinBoardWayId();
//				}
//			} else if (record.getInAmountPcs() < productOrder.getProductionNumPcs()) { // 部分入库
			record.setCheckNum(supportService.fetchPcbSubCheckOrderNum(now, record.getProductOrderNum()));
//			} else {
//				record.setCheckNum(supportService.fetchPcbCheckOrderNum(now));
//			}
		}else {
			msg = "入库数量为空";
		}
		logger.info("生成检验单-" + record.getCheckNum());
		Map<String, Object> response = new HashMap<String, Object>();
		if (StringUtil.isEmpty(msg)) {
			response.put("order", record);
		}else {
			response.put("msg", msg);
		}
		return response;
	}
	

	@Deprecated
	@Override
	public Map<String, Object> getProductState(String orderNumber) {
		Map<String, Object> response = new HashMap<String, Object>();
//		String contentStr = null;
//		List<Integer> contentIdList = new ArrayList<Integer>();
//		List<String> contentList = new ArrayList<String>();
//		PcbCheckOrder pcbCheckOrder = pcbCheckOrderMapper.selectByOrderNumer(orderNumber);
//		PcbCheckReport pcbCheckReport = pcbCheckReportMapper.selectByOrderNumber(orderNumber);
//		String msg = "";
//		if (pcbCheckOrder != null) {
//			// 提示当前检验单状态
//			if (pcbCheckOrder.getCheckStateId() != CheckStateEnum.TO_CHECK.getCode() 
//					&& pcbCheckOrder.getCheckStateId() != CheckStateEnum.NO_CHECK.getCode()) {
//				msg = "检验单状态为" + pcbCheckOrder.getCheckState();
//			}
//			// 获取之前接受产品的检验内容
//			try {
//				String contentIds = pcbCheckOrder.getContentId();
//				Gson g = new Gson();
//				Type type = (Type) new TypeToken<List<Integer>>() {}.getType();
//				contentIdList = g.fromJson(contentIds, type);
//				Dictionary dictionary = null;
//				for (Integer integer : contentIdList) {
//					dictionary = cacheService.getDictionary(DictionaryEnum.CHECK_CONTENT, integer);
//					String content = dictionary.getValueStr();
//					contentList.add(content);
//					if (contentStr == null) {
//						contentStr = content;
//					}else {
//						contentStr = contentStr + "," + content;
//					}
//				}
//			} catch (Exception e) {
//			}
//		}
//		if (contentIdList == null) {
//			contentIdList = new ArrayList<Integer>();
//		}
//		if (pcbCheckReport != null) {
//			// 提示检验报告状态
//			if (pcbCheckReport.getStatusId() != ReportStateEnum.TO_CREATE.getCode()) {
//				if (StringUtil.isEmpty(msg)) {
//					msg = "检验报告状态为" + pcbCheckReport.getStatus();
//				}else {
//					msg = msg + ",检验报告状态为" + pcbCheckReport.getStatus();
//				}
//				
//			}
//		}
//		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
//		response.put("contentIds", contentIdList);
//		response.put("contents", contentList);
//		response.put("content", contentStr);
//		response.put("msg", msg);
		return response;
	}

	@Deprecated
	@Override
	public Map<String, Object> receiveProduct(AkAuthUser wxtbUser,
			String orderNumber, ReceiveProductBody receiveProductBody) {
		Map<String, Object> response = new HashMap<String, Object>();
//		PcbInStorageOrder pcbInStorageOrder = pcbInStorageOrderMapper.selectByOrderNumber(orderNumber);
//		if (pcbInStorageOrder == null || receiveProductBody == null) {
//			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
//			response.put("msg", ErrorCodeEnum.SYSTEM_ERROR.getDescr());
//			return response;
//		}
//		String receiveCode = wxtbUser.getUserCode();
//		String receiveDateStr = receiveProductBody.getReceiveDate();
//		Date receiveDate = DateTimeUtil.dateTimeStr2date(receiveDateStr);
//		pcbInStorageOrder.setReceiver(receiveCode);
//		pcbInStorageOrder.setReceiveDate(receiveDate);
//		List<Integer> contentIds = receiveProductBody.getContentIds();
//		if (contentIds == null || contentIds.isEmpty()) {
//			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
//			response.put("msg", "无检验内容");
//			return response;
//		}
//		ProductOrder productOrder = productOrderMapper.selectByOrderNum(pcbInStorageOrder.getProductOrderNum());
//		if (productOrder == null) {
//			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
//			response.put("msg", "缺少工单数据");
//			return response;
//		}
//		JoinBoardOrder joinBoardOrder = null;
//		String joinBoardCode = pcbInStorageOrder.getJoinBoardOrderCode();
//		if (!StringUtils.isBlank(joinBoardCode)) {
//			List<String> joinBoardCodes = new ArrayList<>();
//			joinBoardCodes.add(joinBoardCode);
//			List<JoinBoardOrder> joinBoardOrders = JoinBoardOrderMapper.listByCodes(joinBoardCodes);
//			if (joinBoardOrders != null && !joinBoardOrders.isEmpty()) {
//				joinBoardOrder = joinBoardOrders.get(0);
//			}
//		}
//		String msg = "";
//		Date now = new Date();
//		String contentIdsJson = JsonUtil.obj2json(contentIds);
//		PcbCheckOrder record = pcbCheckOrderMapper.selectByOrderNumer(orderNumber);
//		PcbCheckReport pcbCheckReport = pcbCheckReportMapper.selectByOrderNumber(orderNumber);
//		if (record == null) {
//			// 生成检验单，默认状态为不检验
//			Map<String, Object> res = generateCheck(pcbInStorageOrder, productOrder, joinBoardOrder, contentIdsJson, now);
//			record = (PcbCheckOrder) res.get("order");
//			if (record == null) {
//				response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
//				response.put("msg", res.get("msg"));
//				return response;
//			}
//			// 如果检验内容包含检验，检验单内容为待检验
//			if (contentIds.contains(CheckContentEnum.CHECK.getCode())){
//				if (record.getCheckStateId() == CheckStateEnum.NO_CHECK.getCode()) {
//					record.setCheckState(CheckStateEnum.TO_CHECK.getDescr());
//					record.setCheckStateId(CheckStateEnum.TO_CHECK.getCode());
//				}
//			}
//			pcbCheckOrderMapper.insertSelective(record);
//		}else {
//			record.setContentId(contentIdsJson);
//			if (contentIds.contains(CheckContentEnum.CHECK.getCode())){
//				// 如果检验内容包含检验，并且之前检验单状态为不检验,改变状态为待检验
//				if (record.getCheckStateId() == CheckStateEnum.NO_CHECK.getCode()) {
//					record.setCheckState(CheckStateEnum.TO_CHECK.getDescr());
//					record.setCheckStateId(CheckStateEnum.TO_CHECK.getCode());
//				}
//			}else {
//				// 如果检验内容包含不检验，则设置状态为不检验
//				record.setCheckState(CheckStateEnum.NO_CHECK.getDescr());
//				record.setCheckStateId(CheckStateEnum.NO_CHECK.getCode());
//			}
//			record.setReceiveDate(receiveDate);
//			pcbCheckOrderMapper.updateByPrimaryKeySelective(record);
//		}
//		if (pcbCheckReport == null) {
//			// 如果检验内容包含报告，生成报告单，状态为待创建
//			if (contentIds.contains(CheckContentEnum.REPORT.getCode())) {
//				Map<String, Object> res = recordCheckReport(pcbInStorageOrder, productOrder, joinBoardOrder, now, record.getCheckNum());
//				pcbCheckReport = (PcbCheckReport) res.get("order");
//				if (pcbCheckReport != null) {
//					pcbCheckReportMapper.insertSelective(pcbCheckReport);
//				}else {
//					response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
//					response.put("msg", res.get("msg"));
//					return response;
//				}
//			}
//		}else if (!contentIds.contains(CheckContentEnum.REPORT.getCode())) {
//			// 如果检验内容不包含报告，并且之前生成过报告，删除以前记录
//			String reportNum = pcbCheckReport.getReportNum();
//			pcbReportIntroMapper.deleteByReportNum(reportNum);
//			pcbReportApertureCheckMapper.deleteByReportNum(reportNum);
//			pcbReportCertificationMapper.deleteByReportNum(reportNum);
//			pcbReportDetailMapper.deleteByReportNum(reportNum);
//			pcbReportIntegrityMapper.deleteByReportNum(reportNum);
//			pcbReportIntroMapper.deleteByReportNum(reportNum);
//			pcbReportSpecialDimensionMapper.deleteByReportNum(reportNum);
//			pcbReportVcutMapper.deleteByReportNum(reportNum);
//			pcbReportSpecialImpedanceMapper.deleteByReportNum(reportNum);
//			pcbCheckReportMapper.deleteByPrimaryKey(pcbCheckReport.getId());
//		}
//		pcbInStorageOrderMapper.updateByPrimaryKeySelective(pcbInStorageOrder);
//		supportService.doOperateLog("接受", productOrder.getOrderNum(), "产品", wxtbUser.getUsername());
//		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
//		response.put("msg", msg);
		return response;
	}
	
}
