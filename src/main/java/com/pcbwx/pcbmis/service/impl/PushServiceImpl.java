package com.pcbwx.pcbmis.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.pcbwx.pcbmis.bean.SerialNumber;
import com.pcbwx.pcbmis.bean.channel.ColOrderChannel;
import com.pcbwx.pcbmis.bean.channel.OutStorage;
import com.pcbwx.pcbmis.bean.channel.SmtOrderChannel;
import com.pcbwx.pcbmis.common.ConfigProperties;
import com.pcbwx.pcbmis.component.CacheService;
import com.pcbwx.pcbmis.dao.ColOrderMapper;
import com.pcbwx.pcbmis.dao.ColReportMapper;
import com.pcbwx.pcbmis.dao.ColSizeWarpingItemMapper;
import com.pcbwx.pcbmis.dao.ColSizeWarpingMapper;
import com.pcbwx.pcbmis.dao.OutStorageOrderMapper;
import com.pcbwx.pcbmis.dao.SmtOrderMapper;
import com.pcbwx.pcbmis.dao.SmtReportMapper;
import com.pcbwx.pcbmis.enums.ColSmtReportEnum;
import com.pcbwx.pcbmis.enums.ConfigEnum;
import com.pcbwx.pcbmis.enums.FormFieldTypeEnum;
import com.pcbwx.pcbmis.enums.OutStorageEnum;
import com.pcbwx.pcbmis.enums.OutStorageOrderTypeEnum;
import com.pcbwx.pcbmis.enums.QualityNameEnum;
import com.pcbwx.pcbmis.exception.SerialNumberException;
import com.pcbwx.pcbmis.model.ColOrder;
import com.pcbwx.pcbmis.model.ColReport;
import com.pcbwx.pcbmis.model.ColSizeWarping;
import com.pcbwx.pcbmis.model.ColSizeWarpingItem;
import com.pcbwx.pcbmis.model.CompanyInfo;
import com.pcbwx.pcbmis.model.FormDefaultPerson;
import com.pcbwx.pcbmis.model.OutStorageOrder;
import com.pcbwx.pcbmis.model.SmtOrder;
import com.pcbwx.pcbmis.model.SmtReport;
import com.pcbwx.pcbmis.model.WxtbUser;
import com.pcbwx.pcbmis.service.PushService;
import com.pcbwx.pcbmis.service.SupportService;
import com.pcbwx.pcbmis.utils.CommonUtil;
import com.pcbwx.pcbmis.utils.HttpUtil;
import com.pcbwx.pcbmis.utils.JsonUtil;
import com.pcbwx.pcbmis.utils.PcbmisUtil;
import com.pcbwx.pcbmis.utils.StringUtil;

@Service("pushService")
public class PushServiceImpl implements PushService {

	private static Logger logger = Logger.getLogger(PushServiceImpl.class);

	private static final String INVALIDREASON = "作废";

	@Autowired
	private ColReportMapper colReportMapper;
	@Autowired
	private ColOrderMapper colOrderMapper;
	@Autowired
	private SmtReportMapper smtReportMapper;
	@Autowired
	private SmtOrderMapper smtOrderMapper;
	@Autowired
	private ColSizeWarpingMapper colSizeWarpingMapper;
	@Autowired
	private ColSizeWarpingItemMapper colSizeWarpingItemMapper;
	@Autowired
	private OutStorageOrderMapper outStorageOrderMapper;

	@Autowired
	private CacheService cacheService;
	@Autowired
	private SupportService supportService;
	
	@Override
	public OutStorageOrder getOutStorageNumber(OutStorage outStorage) {
		OutStorageOrder outStorageOrder = null;
		try {
			outStorageOrder = (OutStorageOrder) CommonUtil.fetchMapFields(outStorage, OutStorageOrder.class);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		String orderNumber = outStorageOrder.getOrderNumber();
		if (StringUtil.isEmpty(orderNumber)) {
			return null;
		}
		Integer orderId = outStorageOrder.getOrderId();
		if (orderId == null) {
			return null;
		}
		Integer outSum = outStorageOrder.getOutSum();
		if (outSum == null) {
			return null;
		}
		Date outStorageDate = outStorageOrder.getOutStorageDate();
		if (outStorageDate == null) {
			return null;
		}
		String state = outStorageOrder.getState();
		if (StringUtil.isEmpty(state)) {
			return null;
		}
		return outStorageOrder;
	}

	@Override
	public boolean operatePush(OutStorageOrder outStorage) {
		String orderType = outStorage.getProductType();
		String orderNumber = outStorage.getOrderNumber();
		Integer orderId = outStorage.getOrderId();
		boolean isSuccess = true;
		try {
			if (Objects.equals(OutStorageOrderTypeEnum.SMT.getCode(), orderType)) {
				isSuccess = operateSmtReport(outStorage, orderNumber, orderId);
			} else if (Objects.equals(OutStorageOrderTypeEnum.COL.getCode(), orderType)) {
				isSuccess = operateColReport(outStorage, orderNumber, orderId);
			}
		} catch (SerialNumberException e) {
			isSuccess = false;
			String errorMessage = e.getMessage();
			logger.error(errorMessage);
			// 强行回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return isSuccess;
	}

	/**
	 * 处理smt消息推送
	 * 
	 * @param outStorage
	 *            出库信息
	 * @param orderNumber
	 *            工单号
	 * @param orderId
	 *            出库单Id
	 * @return
	 */
	private boolean operateSmtReport(OutStorageOrder outStorage, String orderNumber, Integer orderId) {
		Date now = new Date();
		SmtOrder smtOrder = smtOrderMapper.selectByOrderNumber(orderNumber);
		if (smtOrder == null) {
			smtOrder = getSmtOrder(orderNumber);
		}
		if (smtOrder == null) {
			return false;
		}
		SmtReport smtReport = smtReportMapper.selectByOrderId(orderId);
		FormDefaultPerson inspector = cacheService.getFormDefaultPerson(FormFieldTypeEnum.SMT_INSPECTOR);
		FormDefaultPerson reInspector = cacheService.getFormDefaultPerson(FormFieldTypeEnum.SMT_RE_INSPECTOR);
		WxtbUser wxtbUserInspector = cacheService.getWxtbUser(inspector.getUserCode());
		String quarlityName = QualityNameEnum.SMT_REPORT.getDesc();
		if (smtReport == null) {
			if (Objects.equals(OutStorageEnum.REDLINED.getCode(), outStorage.getState())) {
				return true;
			}
			smtReport = new SmtReport();
			smtReport.setOrderId(orderId);
			smtReport.setInspector(inspector.getDefaultName());
			smtReport.setInspectorUserCode(inspector.getUserCode());
			smtReport.setReInspector(reInspector.getDefaultName());
			smtReport.setReInspectorUserCode(reInspector.getUserCode());
			smtReport.setOutSum(outStorage.getOutSum());
			smtReport.setOrderNumber(orderNumber);
			smtReport.setStatus(ColSmtReportEnum.GENERATED.getCode());
			// 质量流水号
			CompanyInfo companyInfo = cacheService.getCompany(smtOrder.getCompanyCode());
			if (companyInfo == null) {
				return false;
			}
			SerialNumber serialNumber = supportService.getQrgsSerialNumber(wxtbUserInspector.getInnerCode(),
					quarlityName, now, companyInfo.getOrgCode());
			if (serialNumber == null) {
				return false;
			}
			smtReport.setSerialNumber(serialNumber.getSn());
			smtReport.setRevision(serialNumber.getRevision());
			smtReport.setDocumentNumber(serialNumber.getDocumentNumber());
			smtReport.setOutStorageDate(outStorage.getOutStorageDate());
			smtReportMapper.insertSelective(smtReport);
		} else {
			if (Objects.equals(OutStorageEnum.REDLINED.getCode(), outStorage.getState())) {
				smtReport.setStatus(ColSmtReportEnum.INVALID.getCode());
				supportService.invalidSerialNumber(wxtbUserInspector.getInnerCode(), quarlityName,
						smtReport.getSerialNumber(), INVALIDREASON);
			}
			smtReport.setOutSum(outStorage.getOutSum());
			smtReport.setOutStorageDate(outStorage.getOutStorageDate());
			smtReport.setUpdateTime(null);
			smtReportMapper.updateByPrimaryKeySelective(smtReport);

		}
		return true;
	}

	/**
	 * 处理col消息推送
	 * 
	 * @param outStorage
	 *            出库信息
	 * @param orderNumber
	 *            工单号
	 * @param orderId
	 *            出库单Id
	 * @return
	 */
	private boolean operateColReport(OutStorageOrder outStorage, String orderNumber, Integer orderId) {
		Date now = new Date();
		ColOrder colOrder = colOrderMapper.selectByOrderNumber(orderNumber);
		if (colOrder == null) {
			colOrder = getColOrder(orderNumber);
		}
		if (colOrder == null) {
			return false;
		}
		ColReport colReport = colReportMapper.selectByOrderId(orderId);
		FormDefaultPerson inspector = cacheService.getFormDefaultPerson(FormFieldTypeEnum.COL_INSPECTOR);
		FormDefaultPerson reInspector = cacheService.getFormDefaultPerson(FormFieldTypeEnum.COL_RE_INSPECTOR);
		WxtbUser wxtbUserInspector = cacheService.getWxtbUser(inspector.getUserCode());
		if (colReport == null) {
			if (Objects.equals(OutStorageEnum.REDLINED.getCode(), outStorage.getState())) {
				return true;
			}
			colReport = new ColReport();
			colReport.setOrderId(orderId);
			colReport.setInspector(inspector.getDefaultName());
			colReport.setInspectorUserCode(inspector.getUserCode());
			colReport.setReInspector(reInspector.getDefaultName());
			colReport.setReInspectorUserCode(reInspector.getUserCode());
			colReport.setOutSum(outStorage.getOutSum());
			colReport.setOrderNumber(orderNumber);
			colReport.setStatus(ColSmtReportEnum.GENERATED.getCode());
			// 质量流水号
			CompanyInfo companyInfo = cacheService.getCompany(colOrder.getCompanyCode());
			if (companyInfo == null) {
				return false;
			}
			String quarlityName = QualityNameEnum.COL_REPORT.getDesc();
			SerialNumber serialNumber = supportService.getQrgsSerialNumber(wxtbUserInspector.getInnerCode(), quarlityName,
					now, companyInfo.getOrgCode());
			logger.info(serialNumber.getSn());
			colReport.setSerialNumber(serialNumber.getSn());
			colReport.setRevision(serialNumber.getRevision());
			colReport.setDocumentNumber(serialNumber.getDocumentNumber());
			colReport.setOutStorageDate(outStorage.getOutStorageDate());
			colReportMapper.insertSelective(colReport);
			// 生成尺寸翘曲度
			Integer colRepeortId = colReport.getId();
			if (colRepeortId == null) {
				return false;
			}
			generateColSizeAndWarping(colRepeortId, orderNumber, colOrder, wxtbUserInspector, companyInfo, now,
					outStorage.getOutSum());
		} else {
			Integer colReportId = colReport.getId();
			ColSizeWarping colSizeWarping = colSizeWarpingMapper.selectByColReportId(colReportId);
			if (colSizeWarping == null) {
				return false;
			}
			colSizeWarping.setCreateTime(outStorage.getCreateTime());
			if (Objects.equals(OutStorageEnum.REDLINED.getCode(), outStorage.getState())) {
				colReport.setStatus(ColSmtReportEnum.INVALID.getCode());
				String quarlityName = QualityNameEnum.COL_SIZE_WARPING.getDesc();
				colSizeWarping.setStatus(ColSmtReportEnum.INVALID.getCode());
				colSizeWarping.setUpdateTime(null);
				colSizeWarpingMapper.updateByPrimaryKeySelective(colSizeWarping);
				supportService.invalidSerialNumber(wxtbUserInspector.getInnerCode(), quarlityName, colSizeWarping.getSerialNumber(), INVALIDREASON);
			} else {
				Integer outNum = outStorage.getOutSum();
				if (!Objects.equals(outNum, colReport.getOutSum())) {
					Integer samplingNum = PcbmisUtil.getColSampling(outNum);
					colSizeWarpingItemMapper.deleteByColReportId(colReportId);
					generateSizeWarping(colOrder.getBoardLong(), colOrder.getBoardWide(), colOrder.getBoardPly(), samplingNum, colSizeWarping.getId(), colReportId);
				}
				colReport.setOutSum(outNum);
			}
			colReport.setOutStorageDate(outStorage.getOutStorageDate());
			colReport.setUpdateTime(null);
			colReportMapper.updateByPrimaryKeySelective(colReport);
		}
		
		return true;
	}

	/**
	 * 拉取smt订单信息插入数据库
	 * 
	 * @param orderNumber
	 *            工单号
	 * @return
	 */
	private SmtOrder getSmtOrder(String orderNumber) {
		String url = ConfigProperties.getProperty(ConfigEnum.SMT_SERVICE_ADDRESS)
				+ "/oiss_web_service/get_smt_order_by_order_number";
		String param = "order_number=" + orderNumber;
		String response = HttpUtil.sendGet(url, param);
		SmtOrderChannel smtOrderChannel = (SmtOrderChannel) JsonUtil.json2obj(response, SmtOrderChannel.class);
		SmtOrder smtOrder = null;
		try {
			smtOrder = (SmtOrder) CommonUtil.fetchMapFields(smtOrderChannel, SmtOrder.class);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		if (smtOrder == null || StringUtil.isEmpty(smtOrder.getOrderNumber())) {
			return null;
		}
		smtOrderMapper.insertSelective(smtOrder);
		return smtOrder;
	}

	/**
	 * 拉取col订单信息插入数据库
	 * 
	 * @param orderNumber
	 *            工单号
	 * @return
	 */
	private ColOrder getColOrder(String orderNumber) {
		String url = ConfigProperties.getProperty(ConfigEnum.COL_SERVICE_ADDRESS)
				+ "/oiss_web_service/get_col_order_by_order_number";
		String param = "order_number=" + orderNumber;
		String response = HttpUtil.sendGet(url, param);
		ColOrderChannel colOrderChannel = (ColOrderChannel) JsonUtil.json2obj(response, ColOrderChannel.class);
		ColOrder colOrder = null;
		try {
			colOrder = (ColOrder) CommonUtil.fetchMapFields(colOrderChannel, ColOrder.class);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		if (colOrder == null || StringUtil.isEmpty(colOrder.getOrderNumber())) {
			return null;
		}
		colOrderMapper.insertSelective(colOrder);
		return colOrder;
	}

	/**
	 * 生成尺寸翘曲度报告
	 * 
	 * @param reportId
	 *            冷板报告Id
	 * @param orderNumber
	 *            工单号
	 * @param colOrder
	 *            工单
	 * @param wxtbUserInspector
	 *            检验员
	 * @param companyInfo
	 *            公司信息
	 * @param now
	 *            时间
	 * @param outNum
	 *            出库数量
	 * @return
	 */
	private boolean generateColSizeAndWarping(Integer reportId, String orderNumber, ColOrder colOrder,
			WxtbUser wxtbUserInspector, CompanyInfo companyInfo, Date now, Integer outNum) {
		ColSizeWarping colSizeWarping = new ColSizeWarping();
		colSizeWarping.setColReportId(reportId);
		colSizeWarping.setStatus(ColSmtReportEnum.GENERATED.getCode());
		colSizeWarping.setOrderNumber(orderNumber);
		String quarlityName = QualityNameEnum.COL_SIZE_WARPING.getDesc();
		SerialNumber serialNumber = supportService.getQrgsSerialNumber(wxtbUserInspector.getInnerCode(), quarlityName,
				now, companyInfo.getOrgCode());
		colSizeWarping.setSerialNumber(serialNumber.getSn());
		colSizeWarping.setRevision(serialNumber.getRevision());
		colSizeWarping.setDocumentNumber(serialNumber.getDocumentNumber());
		colSizeWarpingMapper.insertSelective(colSizeWarping);
		// 生成尺寸翘曲度
		Integer colSizeWarpingId = colSizeWarping.getId();
		Integer samplingNum = PcbmisUtil.getColSampling(outNum);
		generateSizeWarping(colOrder.getBoardLong(), colOrder.getBoardWide(), colOrder.getBoardPly(), samplingNum,
				colSizeWarpingId, reportId);
		return true;
	}

	/**
	 * 生成尺寸翘曲度
	 * 
	 * @param boardLong
	 *            板长
	 * @param boardWide
	 *            板宽
	 * @param boardply
	 *            板厚
	 * @param size
	 *            生成数量
	 * @param colSizeWarpinpId
	 *            尺寸翘曲度Id
	 * @param colReportId
	 *            冷板报告Id
	 */
	private void generateSizeWarping(BigDecimal boardLong, BigDecimal boardWide, BigDecimal boardply, Integer size,
			Integer colSizeWarpinpId, Integer colReportId) {
		BigDecimal tolerance = new BigDecimal("0.3");
		BigDecimal percent = new BigDecimal("0.1");
		BigDecimal warpingMax = new BigDecimal("0.749");
		BigDecimal warpingMin = new BigDecimal("0.009");
		BigDecimal boardLongMax = boardLong.add(tolerance);
		BigDecimal boardLongMin = boardLong.subtract(tolerance);
		BigDecimal boardWideMax = boardWide.add(tolerance);
		BigDecimal boardWideMin = boardWide.subtract(tolerance);
		BigDecimal boardplyMax = boardply.add(boardply.multiply(percent));
		BigDecimal boardplyMin = boardply.subtract(boardply.multiply(percent));
		ColSizeWarpingItem colSizeWarpingItem = null;
		for (int i = 0; i < size; i++) {
			colSizeWarpingItem = new ColSizeWarpingItem();
			colSizeWarpingItem.setBoardLong(PcbmisUtil.nextBigDecimal(boardLongMax, boardLongMin, 3));
			colSizeWarpingItem.setBoardWide(PcbmisUtil.nextBigDecimal(boardWideMax, boardWideMin, 3));
			colSizeWarpingItem.setBoardPly(PcbmisUtil.nextBigDecimal(boardplyMax, boardplyMin, 3));
			colSizeWarpingItem.setWarping(PcbmisUtil.nextBigDecimal(warpingMax, warpingMin, 3));
			colSizeWarpingItem.setColSizeWarpingId(colSizeWarpinpId);
			colSizeWarpingItem.setColReportId(colReportId);
			colSizeWarpingItemMapper.insertSelective(colSizeWarpingItem);
		}
	}

	@Override
	public void insertPush(OutStorageOrder outStorage, boolean isSuccess) {
		outStorage.setIsSuccess(isSuccess);
		outStorageOrderMapper.insertSelective(outStorage);
	}

}
