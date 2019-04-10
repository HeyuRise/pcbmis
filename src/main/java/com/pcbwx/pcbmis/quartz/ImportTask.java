package com.pcbwx.pcbmis.quartz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.pcbwx.pcbmis.bean.channel.CmsGuests;
import com.pcbwx.pcbmis.bean.channel.ColOrderChannel;
import com.pcbwx.pcbmis.bean.channel.OissInStorageOrder;
import com.pcbwx.pcbmis.bean.channel.PcbBasemAterial;
import com.pcbwx.pcbmis.bean.channel.PcbBoardPlyTolerance;
import com.pcbwx.pcbmis.bean.channel.PcbCharacter;
import com.pcbwx.pcbmis.bean.channel.PcbCharacterColour;
import com.pcbwx.pcbmis.bean.channel.PcbCompany;
import com.pcbwx.pcbmis.bean.channel.PcbCraftInfo;
import com.pcbwx.pcbmis.bean.channel.PcbFactory;
import com.pcbwx.pcbmis.bean.channel.PcbFrameTolerances;
import com.pcbwx.pcbmis.bean.channel.PcbGrade;
import com.pcbwx.pcbmis.bean.channel.PcbJoinBoardOrder;
import com.pcbwx.pcbmis.bean.channel.PcbJoinBoardRequire;
import com.pcbwx.pcbmis.bean.channel.PcbJoinBoardWay;
import com.pcbwx.pcbmis.bean.channel.PcbPreventSmt;
import com.pcbwx.pcbmis.bean.channel.PcbPreventSmtColour;
import com.pcbwx.pcbmis.bean.channel.PcbProductOrder;
import com.pcbwx.pcbmis.bean.channel.PcbProductionBatch;
import com.pcbwx.pcbmis.bean.channel.PcbSurfaceProcess;
import com.pcbwx.pcbmis.bean.channel.SmtOrderChannel;
import com.pcbwx.pcbmis.bean.channel.UmsDepartment;
import com.pcbwx.pcbmis.bean.channel.UmsUser;
import com.pcbwx.pcbmis.common.ConfigProperties;
import com.pcbwx.pcbmis.dao.BasemAterialMapper;
import com.pcbwx.pcbmis.dao.BoardPlyToleranceMapper;
import com.pcbwx.pcbmis.dao.ColOrderMapper;
import com.pcbwx.pcbmis.dao.CompanyInfoMapper;
import com.pcbwx.pcbmis.dao.CraftInfoMapper;
import com.pcbwx.pcbmis.dao.DictionaryMapper;
import com.pcbwx.pcbmis.dao.EdaGuestMapper;
import com.pcbwx.pcbmis.dao.FactoryInfoMapper;
import com.pcbwx.pcbmis.dao.FrameToleranceMapper;
import com.pcbwx.pcbmis.dao.JoinBoardOrderMapper;
import com.pcbwx.pcbmis.dao.JoinBoardWayMapper;
import com.pcbwx.pcbmis.dao.PcbCheckOrderMapper;
import com.pcbwx.pcbmis.dao.PcbCheckReportMapper;
import com.pcbwx.pcbmis.dao.PcbInStorageOrderMapper;
import com.pcbwx.pcbmis.dao.ProductNoteMapper;
import com.pcbwx.pcbmis.dao.ProductOrderMapper;
import com.pcbwx.pcbmis.dao.SmtOrderMapper;
import com.pcbwx.pcbmis.dao.SurfaceProcessMapper;
import com.pcbwx.pcbmis.dao.WxtbDepartmentMapper;
import com.pcbwx.pcbmis.dao.WxtbUserMapper;
import com.pcbwx.pcbmis.enums.ConfigEnum;
import com.pcbwx.pcbmis.enums.DictionaryEnum;
import com.pcbwx.pcbmis.model.BasemAterial;
import com.pcbwx.pcbmis.model.BoardPlyTolerance;
import com.pcbwx.pcbmis.model.ColOrder;
import com.pcbwx.pcbmis.model.CompanyInfo;
import com.pcbwx.pcbmis.model.CraftInfo;
import com.pcbwx.pcbmis.model.Dictionary;
import com.pcbwx.pcbmis.model.EdaGuest;
import com.pcbwx.pcbmis.model.FactoryInfo;
import com.pcbwx.pcbmis.model.FrameTolerance;
import com.pcbwx.pcbmis.model.JoinBoardOrder;
import com.pcbwx.pcbmis.model.JoinBoardWay;
import com.pcbwx.pcbmis.model.PcbInStorageOrder;
import com.pcbwx.pcbmis.model.ProductNote;
import com.pcbwx.pcbmis.model.ProductOrder;
import com.pcbwx.pcbmis.model.RecordUtils;
import com.pcbwx.pcbmis.model.SmtOrder;
import com.pcbwx.pcbmis.model.SurfaceProcess;
import com.pcbwx.pcbmis.model.WxtbDepartment;
import com.pcbwx.pcbmis.model.WxtbUser;
import com.pcbwx.pcbmis.service.ConfigService;
import com.pcbwx.pcbmis.service.DataService;
import com.pcbwx.pcbmis.service.SupportService;
import com.pcbwx.pcbmis.utils.CommonUtil;
import com.pcbwx.pcbmis.utils.DataUtil;
import com.pcbwx.pcbmis.utils.DateCalcUtil;
import com.pcbwx.pcbmis.utils.DateTimeUtil;
import com.pcbwx.pcbmis.utils.HttpUtil;
import com.pcbwx.pcbmis.utils.JsonUtil;

public class ImportTask {
	private static Logger logger = Logger.getLogger(ImportTask.class);

	@Autowired
	private ConfigService configService;
	@Autowired
	private SupportService supportService;
	@Autowired
	private DataService dataService;

	@Autowired
	private ProductOrderMapper productOrderMapper;
	@Autowired
	private CompanyInfoMapper companyInfoMapper;
	@Autowired
	private EdaGuestMapper edaGuestMapper;
	@Autowired
	private FactoryInfoMapper factoryInfoMapper;
	@Autowired
	private JoinBoardOrderMapper joinBoardOrderMapper;
	@Autowired
	private JoinBoardWayMapper joinBoardWayMapper;
	@Autowired
	private SurfaceProcessMapper surfaceProcessMapper;
	@Autowired
	private PcbInStorageOrderMapper pcbInStorageOrderMapper;
	@Autowired
	private WxtbUserMapper wxtbUserMapper;
	@Autowired
	private WxtbDepartmentMapper wxtbDepartmentMapper;
	@Autowired
	private DictionaryMapper dictionaryMapper;
	@Autowired
	private BoardPlyToleranceMapper boardPlyToleranceMapper;
	@Autowired
	private BasemAterialMapper basemAterialMapper;
	@Autowired
	private FrameToleranceMapper frameToleranceMapper;
	@Autowired
	private CraftInfoMapper craftInfoMapper;
	@Autowired
	private ProductNoteMapper productNoteMapper;
	@Autowired
	private PcbCheckOrderMapper pcbCheckOrderMapper;
	@Autowired
	private PcbCheckReportMapper pcbCheckReportMapper;
	@Autowired
	private SmtOrderMapper smtOrderMapper;
	@Autowired
	private ColOrderMapper colOrderMapper;

	public void importRecords() {
		final Integer dataSyncFlag = configService.readIntConfig(ConfigEnum.DATA_SYNC_FLAG);
		if (!DataUtil.equals(dataSyncFlag, 1)) {
			return;
		}
		this.reloadCache();

		Date now = new Date();
		// 公司
		try {
			importCompanys(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// 客户
		try {
			importGuests(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// 生产厂家
		try {
			importFactories(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// 军民品等级
		try {
			importGrades(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// 阻焊颜色
		try {
			importPreventSmtColors(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// 拼板要求
		try {
			importJoinBoardRequires(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// 字符
		try {
			importCharacter(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// 字符颜色
		try {
			importCharacterColour(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// 阻焊
		try {
			importPreventSmt(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// 生产批号
		try {
			importProductionBatch(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// 子板信息
		try {
			importJoinBoardOrders(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// 拼板方式
		try {
			importJoinBoardWays(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// 表面处理
		try {
			importSurfaceProcesses(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// 板厚公差
		try {
			importBoardPlyTolerance(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// 基材
		try {
			importBasemAterial(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// 边框公差
		try {
			importFrameTolerance(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// 工艺
		try {
			importCraft(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		try {
			importUsers(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		try {
			importDepartments(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// pcb工单
		try {
			importProductOrders(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// 入库单
		try {
			importInStorageOrders(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// smt工单
		try {
			importSmtOrders(now);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// col工单
		try {
			importColOrders(now);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.clearCache();
	}

	private void reloadCache() {
	}

	private void clearCache() {
	}

	private void importProductOrders(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_PRODUCT_ORDER_IMPORT_TIME;
		Date startTime = null;
		RecordUtils config = configService.getUtilRecord(lastExportTime);
		if (config != null) {
			startTime = config.getValueTime();
			startTime = DateCalcUtil.subTime(startTime, Calendar.MINUTE, 10);
		}
		Date endTime = date;

		List<PcbProductOrder> datas = new ArrayList<PcbProductOrder>();
		if (startTime == null) {
			startTime = DateCalcUtil.subTime(endTime, Calendar.MONTH,
					ConfigProperties.getHisotryRecordMonths());
			for (int i = 0; i < ConfigProperties.getHisotryRecordMonths(); i++) {
				Date stepEndTime = null;
				if (i + 1 < ConfigProperties.getHisotryRecordMonths()) {
					stepEndTime = DateCalcUtil.addTime(startTime, Calendar.MONTH, 1);
				}
				final List<PcbProductOrder> productOrders = getProductOrders(startTime, stepEndTime);
				if (productOrders != null && !productOrders.isEmpty()) {
					datas.addAll(productOrders);
				}
				startTime = stepEndTime;
			}
		} else {
			final List<PcbProductOrder> productOrders = getProductOrders(startTime, endTime);
			if (productOrders != null && !productOrders.isEmpty()) {
				datas.addAll(productOrders);
			}
		}

		// final String pcbServiceUrl =
		// ConfigProperties.getProperty(ConfigEnum.PCB_SERVICE_ADDRESS);
		// StringBuilder sbUrl = new StringBuilder();
		// sbUrl.append(pcbServiceUrl)
		// .append("/ccs_web_service/get_prodcut_orders_by_updated_at?end_updated_at=")
		// .append(DateTimeUtil.date2dateTimeStr(endTime, "yyyyMMddHHmmss"));
		// if (startTime == null) {
		// startTime = DateCalcUtil.subTime(endTime, Calendar.MONTH,
		// ConfigProperties.getHisotryRecordMonths());
		// }
		// sbUrl.append("&start_updated_at=" +
		// DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss"));
		//
		// String json = HttpUtil.sendGet(sbUrl.toString());
		// if (StringUtils.isBlank(json)) {
		// return;
		// }
		// JSONObject objRows = JSONObject.parseObject(json);
		// if (objRows == null) {
		// return;
		// }
		// String jsonRows = objRows.getString("rows");
		// List<PcbProductOrder> datas = JsonUtil.json2array(jsonRows,
		// PcbProductOrder.class);
		// if (datas == null || datas.isEmpty()) {
		// return;
		// }

		List<String> codes = null;
		try {
			codes = DataUtil.fetchField2list(datas, PcbProductOrder.class, "orderNum");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		if (codes == null || codes.isEmpty()) {
			logger.info("没有新的pcb工单数据.");
			return;
		}

		Map<String, ProductOrder> dbRecordMap = this.getProductOrderMap(codes);
		List<ProductNote> productNoteList = new ArrayList<ProductNote>();
		Set<String> orderNumSet = new HashSet<String>();
		List<String> notesIn = new ArrayList<String>();
		List<String> notesOut = new ArrayList<String>();
		ProductNote productNote = null;
		Date now = new Date();
		for (PcbProductOrder data : datas) {
			String orderNum = data.getOrderNum();
			orderNumSet.add(orderNum);
			notesIn = data.getOrderNotesIn();
			if (notesIn != null && !notesIn.isEmpty()) {
				for (String string : notesIn) {
					productNote = new ProductNote();
					productNote.setOrderNum(orderNum);
					productNote.setNoteType(0);
					productNote.setProductNote(string);
					productNote.setCreateTime(now);
					productNoteList.add(productNote);
				}
			}
			notesOut = data.getOrderNotesOut();
			if (notesOut != null && !notesOut.isEmpty()) {
				for (String string : notesOut) {
					productNote = new ProductNote();
					productNote.setOrderNum(orderNum);
					productNote.setNoteType(1);
					productNote.setProductNote(string);
					productNote.setCreateTime(now);
					productNoteList.add(productNote);
				}
			}
			try {
				ProductOrder record = (ProductOrder) CommonUtil.fetchMapFields(data, ProductOrder.class);

				ProductOrder dbRecord = dbRecordMap.get(record.getOrderNum());
				if (dbRecord == null) {
//					if (!DataUtil.equals(record.getProjectStatusId(), PcbStatusEnum.HAVE_PRODUCTED.getCode())) {
//						continue;
//					}
					record.setCreateTime(new Date());
					productOrderMapper.insertSelective(record);
				} else if (dbRecord.getInnerUpdateTime() == null
						|| record.getInnerUpdateTime().after(dbRecord.getInnerUpdateTime())) { // 更新
					record.setId(dbRecord.getId());
					productOrderMapper.updateByPrimaryKeySelective(record);
//					pcbCheckReportMapper.updatePcbBoardName(record.getProductionNumPcs(), record.getOrderNum(),
//							record.getBoardName());
//					if (!StringUtils.equals(dbRecord.getBoardName(), record.getBoardName())) {
//						pcbInStorageOrderMapper.updatePcbBoardName(record.getOrderNum(),
//								record.getBoardName());
//						pcbCheckOrderMapper.updatePcbBoardName(record.getOrderNum(),
//								record.getBoardName());
//					}
				} else {
					continue;
				}
				dataService.recordProductBasemAterials(record.getOrderNum(), data.getBasemAterials());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				logger.error(e.getMessage(), e);
			}
		}
		if (!orderNumSet.isEmpty()) {
			productNoteMapper.deleteByOrderNum(orderNumSet);
		}
		if (!productNoteList.isEmpty()) {
			productNoteMapper.insertBatch(productNoteList);
		}
		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步pcb工单结束.");
	}

	private List<PcbProductOrder> getProductOrders(Date startTime, Date endTime) {
		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.PCB_SERVICE_ADDRESS);
		StringBuilder sbUrl = new StringBuilder();
		sbUrl.append(pcbServiceUrl).append("/pcbmis_web_service/get_product_order?updated_at_start=")
				.append(DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss"));
		if (endTime != null) {
			sbUrl.append("&updated_at_end=" + DateTimeUtil.date2dateTimeStr(endTime, "yyyyMMddHHmmss"));
		}

		String serviceUrl = sbUrl.toString();
		logger.info("获取pcb工单信息url >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return null;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return null;
		}
		String jsonRows = objRows.getString("rows");
		List<PcbProductOrder> datas = JsonUtil.json2array(jsonRows, PcbProductOrder.class);
		return datas;
	}

	private Map<String, ProductOrder> getProductOrderMap(List<String> codes) {
		final List<List<String>> subCodesGroup = DataUtil.subList(codes, 1000);
		if (subCodesGroup == null || subCodesGroup.isEmpty()) {
			return null;
		}

		Map<String, ProductOrder> dbRecordMap = new HashMap<String, ProductOrder>();
		for (List<String> subCodes : subCodesGroup) {
			if (subCodes == null || subCodes.isEmpty()) {
				continue;
			}
			try {
				List<ProductOrder> dbRecords = productOrderMapper.listByOrderNum(subCodes);
				if (dbRecords == null || dbRecords.isEmpty()) {
					continue;
				}
				final Map<String, ProductOrder> list2map = DataUtil.list2map(dbRecords, ProductOrder.class,
						"orderNum");
				dbRecordMap.putAll(list2map);
			} catch (NoSuchMethodException | SecurityException e) {
				logger.error(e.getMessage(), e);
			}
		}

		return dbRecordMap;
	}

	private void importInStorageOrders(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_IN_STORAGE_ORDER_IMPORT_TIME;
		Date startTime = null;
		RecordUtils config = configService.getUtilRecord(lastExportTime);
		if (config != null) {
			startTime = config.getValueTime();
			startTime = DateCalcUtil.subTime(startTime, Calendar.MINUTE, 10);
		}
		Date endTime = date;

		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.OISS_SERVICE_ADDRESS);
		StringBuilder sbUrl = new StringBuilder();
		sbUrl.append(pcbServiceUrl).append("/pcbmis_web_service/get_in_storage_orders");
		if (startTime == null) {
			startTime = DateCalcUtil.subTime(endTime, Calendar.MONTH,
					ConfigProperties.getInStorageHisotryRecordMonths());
		}
		sbUrl.append("?updated_at=" + DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss"));

		String serviceUrl = sbUrl.toString();
		logger.info("获取入库单信息url >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return;
		}
		String jsonRows = objRows.getString("rows");
		List<OissInStorageOrder> datas = JsonUtil.json2array(jsonRows, OissInStorageOrder.class);
		if (datas == null || datas.isEmpty()) {
			logger.info("没有新的入库单数据.");
			return;
		}

		List<String> codes = null;
		List<String> productCodes = null;
		List<String> joinOrderCodes = null;
		try {
			codes = DataUtil.fetchField2list(datas, OissInStorageOrder.class, "orderNumber");
			productCodes = DataUtil.fetchField2listNoDuplicate(datas, OissInStorageOrder.class,
					"productOrderNum");
			joinOrderCodes = DataUtil.fetchField2listNoDuplicate(datas, OissInStorageOrder.class,
					"joinBoardOrderCode");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		if (codes == null || codes.isEmpty()) {
			logger.info("提取入库单code为空.");
			return;
		}
		if (productCodes == null || productCodes.isEmpty()) {
			logger.info("提取工单code为空.");
			return;
		}
		Map<String, PcbInStorageOrder> dbRecordMap = new HashMap<String, PcbInStorageOrder>();
		try {
			List<PcbInStorageOrder> dbRecords = pcbInStorageOrderMapper.selectByInOrderNums(codes);
			if (dbRecords != null && !dbRecords.isEmpty()) {
				dbRecordMap = DataUtil.list2map(dbRecords, PcbInStorageOrder.class, "orderNumber");
			}
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}

		Map<String, ProductOrder> productOrderMap = new HashMap<String, ProductOrder>();
		try {
			final List<ProductOrder> productOrders = productOrderMapper.listByOrderNum(productCodes);
			if (productOrders != null && !productOrders.isEmpty()) {
				productOrderMap = DataUtil.list2map(productOrders, ProductOrder.class, "orderNum");
			}
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		if (productOrderMap == null || productOrderMap.isEmpty()) {
			logger.error("找不到对应的工单:" + productCodes);
			return;
		}

		Map<String, JoinBoardOrder> joinOrderMap = new HashMap<String, JoinBoardOrder>();
		if (joinOrderCodes != null && !joinOrderCodes.isEmpty()) {
			List<JoinBoardOrder> joinBoardOrders = joinBoardOrderMapper.listByCodes(joinOrderCodes);
			if (joinBoardOrders != null && !joinBoardOrders.isEmpty()) {
				try {
					joinOrderMap = DataUtil.list2map(joinBoardOrders, JoinBoardOrder.class, "joinBoardCode");
				} catch (NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage(), e);
				}
			}
		}

		for (OissInStorageOrder data : datas) {
			try {
				PcbInStorageOrder record = (PcbInStorageOrder) CommonUtil.fetchMapFields(data,
						PcbInStorageOrder.class);
				record.setBoardNameNew(record.getBoardName());
				PcbInStorageOrder dbRecord = dbRecordMap.get(record.getOrderNumber());
				if (dbRecord == null) {
					final ProductOrder productOrder = productOrderMap.get(record.getProductOrderNum());
					JoinBoardOrder joinBoardOrder = null;
					if (!StringUtils.isBlank(record.getJoinBoardOrderCode())) {
						joinBoardOrder = joinOrderMap.get(record.getJoinBoardOrderCode());
					}
					if (productOrder == null) {
						logger.error("没有找到对应的pcb工单.order num=" + record.getProductOrderNum());
					}
					record.setCreateTime(new Date());
					if (joinBoardOrder != null) {
						record.setBoardName(joinBoardOrder.getJoinBoardName());
						record.setBoardNameNew(joinBoardOrder.getJoinBoardName());
					} else if (productOrder != null) {
						record.setBoardName(productOrder.getBoardName());
						record.setBoardNameNew(productOrder.getBoardName());
					}
					pcbInStorageOrderMapper.insertSelective(record);
				} else if (dbRecord.getInnerUpdateTime() == null
						|| record.getInnerUpdateTime().after(dbRecord.getInnerUpdateTime())) { // 更新
					record.setId(dbRecord.getId());
					pcbInStorageOrderMapper.updateByPrimaryKeySelective(record);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				logger.error(e.toString() + ",order json=" + JsonUtil.obj2json(data));
			}
		}

		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步入库单结束.");
	}

	private void importCompanys(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_COMPANY_IMPORT_TIME;
		Date endTime = date;

		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.CMS_SERVICE_ADDRESS);
		String serviceUrl = pcbServiceUrl + "/ccs_web_service/get_companies";

		logger.info("获取公司信息url >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return;
		}
		String jsonRows = objRows.getString("rows");
		List<PcbCompany> datas = JsonUtil.json2array(jsonRows, PcbCompany.class);
		if (datas == null || datas.isEmpty()) {
			logger.info("没有新的公司数据.");
			return;
		}

		List<String> codes = null;
		try {
			codes = DataUtil.fetchField2list(datas, PcbCompany.class, "companyCode");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		if (codes == null || codes.isEmpty()) {
			logger.info("提供取code为空.");
			return;
		}
		Map<String, CompanyInfo> dbRecordMap = new HashMap<String, CompanyInfo>();
		try {
			List<CompanyInfo> dbRecords = companyInfoMapper.listByCodes(codes);
			if (dbRecords != null && !dbRecords.isEmpty()) {
				dbRecordMap = DataUtil.list2map(dbRecords, CompanyInfo.class, "companyCode");
			}
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}

		for (PcbCompany data : datas) {
			try {
				CompanyInfo record = (CompanyInfo) CommonUtil.fetchMapFields(data, CompanyInfo.class);
				CompanyInfo dbRecord = dbRecordMap.get(record.getCompanyCode());
				if (dbRecord == null) {
					record.setCreateTime(new Date());
					companyInfoMapper.insertSelective(record);
				} else if (dbRecord.getInnerUpdateTime() == null
						|| record.getInnerUpdateTime().after(dbRecord.getInnerUpdateTime())) { // 更新
					record.setId(dbRecord.getId());
					companyInfoMapper.updateByPrimaryKeySelective(record);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				logger.error(e.getMessage(), e);
			}
		}

		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步公司信息结束.");
	}

	private void importGuests(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_GUEST_IMPORT_TIME;

		Date startTime = null;
		RecordUtils config = configService.getUtilRecord(lastExportTime);
		if (config != null) {
			startTime = config.getValueTime();
			startTime = DateCalcUtil.subTime(startTime, Calendar.MINUTE, 10);
		}
		Date endTime = date;

		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.CMS_SERVICE_ADDRESS);
		String serviceUrl = pcbServiceUrl + "/ccs_web_service/get_guests";
		if (startTime != null) {
			serviceUrl += "?updated_at=" + DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss");
		}

		logger.info("获取客户信息url >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return;
		}
		String jsonRows = objRows.getString("rows");
		List<CmsGuests> datas = JsonUtil.json2array(jsonRows, CmsGuests.class);
		if (datas == null || datas.isEmpty()) {
			logger.info("没有新的客户数据.");
			return;
		}

		List<String> codes = null;
		try {
			codes = DataUtil.fetchField2list(datas, CmsGuests.class, "guestCode");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		if (codes == null || codes.isEmpty()) {
			logger.info("提供取code为空.");
			return;
		}
		Map<String, EdaGuest> dbRecordMap = new HashMap<String, EdaGuest>();
		try {
			List<EdaGuest> dbRecords = edaGuestMapper.listByCodes(codes);
			if (dbRecords != null && !dbRecords.isEmpty()) {
				dbRecordMap = DataUtil.list2map(dbRecords, EdaGuest.class, "guestCode");
			}
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}

		for (CmsGuests data : datas) {
			try {
				EdaGuest record = (EdaGuest) CommonUtil.fetchMapFields(data, EdaGuest.class);
				EdaGuest dbRecord = dbRecordMap.get(record.getGuestCode());
				if (dbRecord == null) {
					record.setCreateTime(new Date());
					edaGuestMapper.insertSelective(record);
				} else if (dbRecord.getInnerUpdateTime() == null
						|| record.getInnerUpdateTime().after(dbRecord.getInnerUpdateTime())) { // 更新
					record.setId(dbRecord.getId());
					edaGuestMapper.updateByPrimaryKeySelective(record);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				logger.error(e.getMessage(), e);
			}
		}

		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步客户信息结束.");
	}

	private void importFactories(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_FACTORY_IMPORT_TIME;
		Date endTime = date;

		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.PCB_SERVICE_ADDRESS);
		String serviceUrl = pcbServiceUrl + "/ccs_web_service/get_factories";

		logger.info("获取生产厂家信息url >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return;
		}
		String jsonRows = objRows.getString("rows");
		List<PcbFactory> datas = JsonUtil.json2array(jsonRows, PcbFactory.class);
		if (datas == null || datas.isEmpty()) {
			logger.info("没有新的生产工厂数据.");
			return;
		}

		List<Integer> codes = null;
		try {
			codes = DataUtil.fetchField2list(datas, PcbFactory.class, "innerId");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		if (codes == null || codes.isEmpty()) {
			return;
		}
		Map<Integer, FactoryInfo> dbRecordMap = new HashMap<Integer, FactoryInfo>();
		try {
			List<FactoryInfo> dbRecords = factoryInfoMapper.listByInnerIds(codes);
			if (dbRecords != null && !dbRecords.isEmpty()) {
				dbRecordMap = DataUtil.list2map(dbRecords, FactoryInfo.class, "innerId");
			}
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}

		for (PcbFactory data : datas) {
			try {
				FactoryInfo record = (FactoryInfo) CommonUtil.fetchMapFields(data, FactoryInfo.class);
				FactoryInfo dbRecord = dbRecordMap.get(record.getInnerId());
				if (dbRecord == null) {
					record.setCreateTime(new Date());
					factoryInfoMapper.insertSelective(record);
				} else if (dbRecord.getInnerUpdateTime() == null
						|| record.getInnerUpdateTime().after(dbRecord.getInnerUpdateTime())) { // 更新
					record.setId(dbRecord.getId());
					factoryInfoMapper.updateByPrimaryKeySelective(record);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				logger.error(e.getMessage(), e);
			}
		}

		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步生产厂家结束.");
	}

	private void importGrades(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_GRADE_IMPORT_TIME;
		Date startTime = null;
		RecordUtils config = configService.getUtilRecord(lastExportTime);
		if (config != null) {
			startTime = config.getValueTime();
			startTime = DateCalcUtil.subTime(startTime, Calendar.MINUTE, 10);
		}
		Date endTime = date;

		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.PCB_SERVICE_ADDRESS);
		String serviceUrl = pcbServiceUrl + "/ccs_web_service/get_grades";
		if (startTime != null) {
			serviceUrl += "?updated_at_start=" + DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss");
		}

		logger.info("获取军民品等级url >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return;
		}
		String jsonRows = objRows.getString("rows");
		List<PcbGrade> datas = JsonUtil.json2array(jsonRows, PcbGrade.class);
		if (datas == null || datas.isEmpty()) {
			logger.info("没有新的军民品等级数据.");
			return;
		}
		List<Dictionary> dbRecords = dictionaryMapper.loadByType(DictionaryEnum.CATEGORY_GRADE.getCode());
		Map<Integer, Dictionary> dbRecordMap = new HashMap<Integer, Dictionary>();
		if (dbRecords != null && !dbRecords.isEmpty()) {
			try {
				dbRecordMap = DataUtil.list2map(dbRecords, Dictionary.class, "innerId");
			} catch (NoSuchMethodException | SecurityException e) {
				logger.error(e.getMessage(), e);
			}
		}
		for (PcbGrade data : datas) {
			Dictionary dbRecord = dbRecordMap.get(data.getInnerId());
			Dictionary record;
			try {
				record = (Dictionary) CommonUtil.fetchMapFields(data, Dictionary.class);
				record.setType(DictionaryEnum.CATEGORY_GRADE.getCode());

				if (dbRecord == null) {
					record.setCreateTime(new Date());
					dictionaryMapper.insertSelective(record);
				} else if (dbRecord.getInnerUpdateTime() == null
						|| record.getInnerUpdateTime().after(dbRecord.getInnerUpdateTime())) { // 更新
					record.setId(dbRecord.getId());
					dictionaryMapper.updateByPrimaryKeySelective(record);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				logger.error(e.getMessage(), e);
			}
		}

		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步军民品等级结束.");
	}

	private void importPreventSmtColors(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_PREVENT_SMT_COLOR_IMPORT_TIME;
		Date startTime = null;
		RecordUtils config = configService.getUtilRecord(lastExportTime);
		if (config != null) {
			startTime = config.getValueTime();
			startTime = DateCalcUtil.subTime(startTime, Calendar.MINUTE, 10);
		}
		Date endTime = date;

		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.PCB_SERVICE_ADDRESS);
		String serviceUrl = pcbServiceUrl + "/params_api/getPreventSmtColour";
		if (startTime != null) {
			serviceUrl += "?updated_at_start=" + DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss");
		}

		logger.info("获取阻焊颜色url >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return;
		}
		String jsonRows = objRows.getString("rows");
		List<PcbPreventSmtColour> datas = JsonUtil.json2array(jsonRows, PcbPreventSmtColour.class);
		if (datas == null || datas.isEmpty()) {
			logger.info("没有新的阻焊颜色数据.");
			return;
		}
		List<Dictionary> dbRecords = dictionaryMapper.loadByType(DictionaryEnum.PREVENT_SMT_COLOR.getCode());
		Map<Integer, Dictionary> dbRecordMap = new HashMap<Integer, Dictionary>();
		if (dbRecords != null && !dbRecords.isEmpty()) {
			try {
				dbRecordMap = DataUtil.list2map(dbRecords, Dictionary.class, "innerId");
			} catch (NoSuchMethodException | SecurityException e) {
				logger.error(e.getMessage(), e);
			}
		}
		for (PcbPreventSmtColour data : datas) {
			Dictionary dbRecord = dbRecordMap.get(data.getInnerId());
			Dictionary record;
			try {
				record = (Dictionary) CommonUtil.fetchMapFields(data, Dictionary.class);
				record.setType(DictionaryEnum.PREVENT_SMT_COLOR.getCode());

				if (dbRecord == null) {
					record.setCreateTime(new Date());
					dictionaryMapper.insertSelective(record);
				} else if (dbRecord.getInnerUpdateTime() == null
						|| record.getInnerUpdateTime().after(dbRecord.getInnerUpdateTime())) { // 更新
					record.setId(dbRecord.getId());
					dictionaryMapper.updateByPrimaryKeySelective(record);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				logger.error(e.getMessage(), e);
			}
		}

		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步阻焊颜色结束.");
	}

	private void importJoinBoardRequires(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_JOIN_BOARD_REQUIRE_IMPORT_TIME;
		Date startTime = null;
		RecordUtils config = configService.getUtilRecord(lastExportTime);
		if (config != null) {
			startTime = config.getValueTime();
			startTime = DateCalcUtil.subTime(startTime, Calendar.MINUTE, 10);
		}
		Date endTime = date;

		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.PCB_SERVICE_ADDRESS);
		String serviceUrl = pcbServiceUrl + "/ccs_web_service/get_join_board_requires";
		if (startTime != null) {
			serviceUrl += "?start_updated_at=" + DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss");
		}

		logger.info("获取拼板要求url >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return;
		}
		String jsonRows = objRows.getString("rows");
		List<PcbJoinBoardRequire> datas = JsonUtil.json2array(jsonRows, PcbJoinBoardRequire.class);
		if (datas == null || datas.isEmpty()) {
			logger.info("没有新的拼板要求数据.");
			return;
		}
		List<Dictionary> dbRecords = dictionaryMapper.loadByType(DictionaryEnum.JOIN_BOARD_REQUIRE.getCode());
		Map<Integer, Dictionary> dbRecordMap = new HashMap<Integer, Dictionary>();
		if (dbRecords != null && !dbRecords.isEmpty()) {
			try {
				dbRecordMap = DataUtil.list2map(dbRecords, Dictionary.class, "innerId");
			} catch (NoSuchMethodException | SecurityException e) {
				logger.error(e.getMessage(), e);
			}
		}
		for (PcbJoinBoardRequire data : datas) {
			Dictionary dbRecord = dbRecordMap.get(data.getInnerId());
			Dictionary record;
			try {
				record = (Dictionary) CommonUtil.fetchMapFields(data, Dictionary.class);
				record.setType(DictionaryEnum.JOIN_BOARD_REQUIRE.getCode());

				if (dbRecord == null) {
					record.setCreateTime(new Date());
					dictionaryMapper.insertSelective(record);
				} else if (dbRecord.getInnerUpdateTime() == null
						|| record.getInnerUpdateTime().after(dbRecord.getInnerUpdateTime())) { // 更新
					record.setId(dbRecord.getId());
					dictionaryMapper.updateByPrimaryKeySelective(record);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				logger.error(e.getMessage(), e);
			}
		}

		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步拼板要求结束.");
	}

	private void importCharacter(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_CHARACTER_IMPORT_TIME;
		Date startTime = null;
		RecordUtils config = configService.getUtilRecord(lastExportTime);
		if (config != null) {
			startTime = config.getValueTime();
			startTime = DateCalcUtil.subTime(startTime, Calendar.MINUTE, 10);
		}
		Date endTime = date;

		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.PCB_SERVICE_ADDRESS);
		String serviceUrl = pcbServiceUrl + "/params_api/get_characters_by_updated_at";
		if (startTime != null) {
			serviceUrl += "?updated_at_start=" + DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss");
		}

		logger.info("获取字符url >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return;
		}
		String jsonRows = objRows.getString("rows");
		List<PcbCharacter> datas = JsonUtil.json2array(jsonRows, PcbCharacter.class);
		if (datas == null || datas.isEmpty()) {
			logger.info("没有新的字符数据.");
			return;
		}
		List<Dictionary> dbRecords = dictionaryMapper.loadByType(DictionaryEnum.CHARACTER.getCode());
		Map<Integer, Dictionary> dbRecordMap = new HashMap<Integer, Dictionary>();
		if (dbRecords != null && !dbRecords.isEmpty()) {
			try {
				dbRecordMap = DataUtil.list2map(dbRecords, Dictionary.class, "innerId");
			} catch (NoSuchMethodException | SecurityException e) {
				logger.error(e.getMessage(), e);
			}
		}
		for (PcbCharacter data : datas) {
			Dictionary dbRecord = dbRecordMap.get(data.getInnerId());
			Dictionary record;
			try {
				record = (Dictionary) CommonUtil.fetchMapFields(data, Dictionary.class);
				record.setType(DictionaryEnum.CHARACTER.getCode());

				if (dbRecord == null) {
					record.setCreateTime(new Date());
					dictionaryMapper.insertSelective(record);
				} else if (dbRecord.getInnerUpdateTime() == null
						|| record.getInnerUpdateTime().after(dbRecord.getInnerUpdateTime())) { // 更新
					record.setId(dbRecord.getId());
					dictionaryMapper.updateByPrimaryKeySelective(record);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				logger.error(e.getMessage(), e);
			}
		}

		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步字符结束.");
	}

	private void importCharacterColour(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_CHARACTER_COLOUR_IMPORT_TIME;
		Date startTime = null;
		RecordUtils config = configService.getUtilRecord(lastExportTime);
		if (config != null) {
			startTime = config.getValueTime();
			startTime = DateCalcUtil.subTime(startTime, Calendar.MINUTE, 10);
		}
		Date endTime = date;

		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.PCB_SERVICE_ADDRESS);
		String serviceUrl = pcbServiceUrl + "/params_api/get_character_colours_by_updated_at";
		if (startTime != null) {
			serviceUrl += "?updated_at_start=" + DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss");
		}

		logger.info("获取字符颜色url >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return;
		}
		String jsonRows = objRows.getString("rows");
		List<PcbCharacterColour> datas = JsonUtil.json2array(jsonRows, PcbCharacterColour.class);
		if (datas == null || datas.isEmpty()) {
			logger.info("没有新的字符颜色数据.");
			return;
		}
		List<Dictionary> dbRecords = dictionaryMapper.loadByType(DictionaryEnum.CHARACTER_COLOUR.getCode());
		Map<Integer, Dictionary> dbRecordMap = new HashMap<Integer, Dictionary>();
		if (dbRecords != null && !dbRecords.isEmpty()) {
			try {
				dbRecordMap = DataUtil.list2map(dbRecords, Dictionary.class, "innerId");
			} catch (NoSuchMethodException | SecurityException e) {
				logger.error(e.getMessage(), e);
			}
		}
		for (PcbCharacterColour data : datas) {
			Dictionary dbRecord = dbRecordMap.get(data.getInnerId());
			Dictionary record;
			try {
				record = (Dictionary) CommonUtil.fetchMapFields(data, Dictionary.class);
				record.setType(DictionaryEnum.CHARACTER_COLOUR.getCode());

				if (dbRecord == null) {
					record.setCreateTime(new Date());
					dictionaryMapper.insertSelective(record);
				} else if (dbRecord.getInnerUpdateTime() == null
						|| record.getInnerUpdateTime().after(dbRecord.getInnerUpdateTime())) { // 更新
					record.setId(dbRecord.getId());
					dictionaryMapper.updateByPrimaryKeySelective(record);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				logger.error(e.getMessage(), e);
			}
		}

		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步字符颜色结束.");
	}

	private void importPreventSmt(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_PREVENT_SMT_IMPORT_TIME;
		Date startTime = null;
		RecordUtils config = configService.getUtilRecord(lastExportTime);
		if (config != null) {
			startTime = config.getValueTime();
			startTime = DateCalcUtil.subTime(startTime, Calendar.MINUTE, 10);
		}
		Date endTime = date;

		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.PCB_SERVICE_ADDRESS);
		String serviceUrl = pcbServiceUrl + "/params_api/get_prevent_smts_by_updated_at";
		if (startTime != null) {
			serviceUrl += "?updated_at_start=" + DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss");
		}

		logger.info("获取阻焊url >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return;
		}
		String jsonRows = objRows.getString("rows");
		List<PcbPreventSmt> datas = JsonUtil.json2array(jsonRows, PcbPreventSmt.class);
		if (datas == null || datas.isEmpty()) {
			logger.info("没有新的阻焊数据.");
			return;
		}
		List<Dictionary> dbRecords = dictionaryMapper.loadByType(DictionaryEnum.PREVENT_SMT.getCode());
		Map<Integer, Dictionary> dbRecordMap = new HashMap<Integer, Dictionary>();
		if (dbRecords != null && !dbRecords.isEmpty()) {
			try {
				dbRecordMap = DataUtil.list2map(dbRecords, Dictionary.class, "innerId");
			} catch (NoSuchMethodException | SecurityException e) {
				logger.error(e.getMessage(), e);
			}
		}
		for (PcbPreventSmt data : datas) {
			Dictionary dbRecord = dbRecordMap.get(data.getInnerId());
			Dictionary record;
			try {
				record = (Dictionary) CommonUtil.fetchMapFields(data, Dictionary.class);
				record.setType(DictionaryEnum.PREVENT_SMT.getCode());

				if (dbRecord == null) {
					record.setCreateTime(new Date());
					dictionaryMapper.insertSelective(record);
				} else if (dbRecord.getInnerUpdateTime() == null
						|| record.getInnerUpdateTime().after(dbRecord.getInnerUpdateTime())) { // 更新
					record.setId(dbRecord.getId());
					dictionaryMapper.updateByPrimaryKeySelective(record);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				logger.error(e.getMessage(), e);
			}
		}

		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步阻焊结束.");
	}

	private void importProductionBatch(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_PRODUCTION_BATCH_IMPORT_TIME;
		Date startTime = null;
		RecordUtils config = configService.getUtilRecord(lastExportTime);
		if (config != null) {
			startTime = config.getValueTime();
			startTime = DateCalcUtil.subTime(startTime, Calendar.MINUTE, 10);
		}
		Date endTime = date;

		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.PCB_SERVICE_ADDRESS);
		String serviceUrl = pcbServiceUrl + "/params_api/getBatchProduction";
		if (startTime != null) {
			serviceUrl += "?updated_at_start=" + DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss");
		}

		logger.info("获取生产批号url >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return;
		}
		String jsonRows = objRows.getString("rows");
		List<PcbProductionBatch> datas = JsonUtil.json2array(jsonRows, PcbProductionBatch.class);
		if (datas == null || datas.isEmpty()) {
			logger.info("没有新的生产批号数据.");
			return;
		}
		List<Dictionary> dbRecords = dictionaryMapper.loadByType(DictionaryEnum.PRODUCTION_BATCH.getCode());
		Map<Integer, Dictionary> dbRecordMap = new HashMap<Integer, Dictionary>();
		if (dbRecords != null && !dbRecords.isEmpty()) {
			try {
				dbRecordMap = DataUtil.list2map(dbRecords, Dictionary.class, "innerId");
			} catch (NoSuchMethodException | SecurityException e) {
				logger.error(e.getMessage(), e);
			}
		}
		for (PcbProductionBatch data : datas) {
			Dictionary dbRecord = dbRecordMap.get(data.getInnerId());
			Dictionary record;
			try {
				record = (Dictionary) CommonUtil.fetchMapFields(data, Dictionary.class);
				record.setType(DictionaryEnum.PRODUCTION_BATCH.getCode());

				if (dbRecord == null) {
					record.setCreateTime(new Date());
					dictionaryMapper.insertSelective(record);
				} else if (dbRecord.getInnerUpdateTime() == null
						|| record.getInnerUpdateTime().after(dbRecord.getInnerUpdateTime())) { // 更新
					record.setId(dbRecord.getId());
					dictionaryMapper.updateByPrimaryKeySelective(record);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				logger.error(e.getMessage(), e);
			}
		}

		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步生产批号结束.");
	}

	private void importJoinBoardOrders(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_JOIN_BOARD_ORDER_IMPORT_TIME;
		Date startTime = null;
		RecordUtils config = configService.getUtilRecord(lastExportTime);
		if (config != null) {
			startTime = config.getValueTime();
			startTime = DateCalcUtil.subTime(startTime, Calendar.MINUTE, 10);
		}
		Date endTime = date;

		List<PcbJoinBoardOrder> datas = new ArrayList<PcbJoinBoardOrder>();
		if (startTime == null) {
			startTime = DateCalcUtil.subTime(endTime, Calendar.MONTH,
					ConfigProperties.getHisotryRecordMonths());
			for (int i = 0; i < ConfigProperties.getHisotryRecordMonths(); i++) {
				Date stepEndTime = null;
				if (i + 1 < ConfigProperties.getHisotryRecordMonths()) {
					stepEndTime = DateCalcUtil.addTime(startTime, Calendar.MONTH, 1);
				}
				final List<PcbJoinBoardOrder> joinBoardOrders = getJoinBoardOrders(startTime, stepEndTime);
				if (joinBoardOrders != null && !joinBoardOrders.isEmpty()) {
					datas.addAll(joinBoardOrders);
				}
				startTime = stepEndTime;
			}
		} else {
			final List<PcbJoinBoardOrder> joinBoardOrders = getJoinBoardOrders(startTime, endTime);
			if (joinBoardOrders != null && !joinBoardOrders.isEmpty()) {
				datas.addAll(joinBoardOrders);
			}
		}

		// final String pcbServiceUrl =
		// ConfigProperties.getProperty(ConfigEnum.PCB_SERVICE_ADDRESS);
		// String serviceUrl = pcbServiceUrl +
		// "/pcbmis_web_service/get_join_board_order?updated_at_end="
		// + DateTimeUtil.date2dateTimeStr(endTime, "yyyyMMddHHmmss");
		// serviceUrl += "&updated_at_start=" +
		// DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss");
		//
		// String json = HttpUtil.sendGet(serviceUrl);
		// if (StringUtils.isBlank(json)) {
		// return;
		// }
		// JSONObject objRows = JSONObject.parseObject(json);
		// if (objRows == null) {
		// return;
		// }
		// String jsonRows = objRows.getString("rows");
		// List<PcbJoinBoardOrder> datas = JsonUtil.json2array(jsonRows,
		// PcbJoinBoardOrder.class);
		// if (datas == null || datas.isEmpty()) {
		// return;
		// }

		List<String> codes = null;
		try {
			codes = DataUtil.fetchField2list(datas, PcbJoinBoardOrder.class, "joinBoardCode");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		if (codes == null || codes.isEmpty()) {
			logger.info("没有新的子板数据.");
			return;
		}
		final List<List<String>> subCodesGroup = DataUtil.subList(codes, 1000);
		if (subCodesGroup == null || subCodesGroup.isEmpty()) {
			return;
		}

		Map<String, JoinBoardOrder> dbRecordMap = new HashMap<String, JoinBoardOrder>();
		for (List<String> subCodes : subCodesGroup) {
			if (subCodes == null || subCodes.isEmpty()) {
				continue;
			}
			try {
				List<JoinBoardOrder> dbRecords = joinBoardOrderMapper.listByCodes(subCodes);
				if (dbRecords == null || dbRecords.isEmpty()) {
					continue;
				}
				final Map<String, JoinBoardOrder> list2map = DataUtil.list2map(dbRecords,
						JoinBoardOrder.class, "joinBoardCode");
				// for (Map.Entry<String, JoinBoardOrder> entry :
				// list2map.entrySet()) {
				// dbRecordMap.put(entry.getKey(), entry.getValue());
				// }
				dbRecordMap.putAll(list2map);
			} catch (NoSuchMethodException | SecurityException e) {
				logger.error(e.getMessage(), e);
			}
		}

		for (PcbJoinBoardOrder data : datas) {
			try {
				JoinBoardOrder record = (JoinBoardOrder) CommonUtil
						.fetchMapFields(data, JoinBoardOrder.class);
				JoinBoardOrder dbRecord = dbRecordMap.get(record.getJoinBoardCode());
				if (dbRecord == null) {
					record.setCreateTime(new Date());
					joinBoardOrderMapper.insertSelective(record);
				} else if (dbRecord.getInnerUpdateTime() == null
						|| record.getInnerUpdateTime().after(dbRecord.getInnerUpdateTime())) { // 更新
					record.setId(dbRecord.getId());
					joinBoardOrderMapper.updateByPrimaryKeySelective(record);
					if (!StringUtils.equals(dbRecord.getJoinBoardName(), record.getJoinBoardName())) {
						pcbInStorageOrderMapper.updateJoinBoardName(record.getJoinBoardCode(),
								record.getJoinBoardName());
						pcbCheckOrderMapper.updateJoinBoardName(record.getJoinBoardCode(),
								record.getJoinBoardName());
						pcbCheckReportMapper.updateJoinBoardName(record.getJoinBoardCode(),
								record.getJoinBoardName());
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				logger.error(e.getMessage(), e);
			}
		}

		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步子板信息结束.");
	}

	private List<PcbJoinBoardOrder> getJoinBoardOrders(Date startTime, Date endTime) {
		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.PCB_SERVICE_ADDRESS);
		String serviceUrl = pcbServiceUrl + "/pcbmis_web_service/get_join_board_order?updated_at_start="
				+ DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss");
		if (endTime != null) {
			serviceUrl += "&updated_at_end=" + DateTimeUtil.date2dateTimeStr(endTime, "yyyyMMddHHmmss");
		}

		logger.info("获取子板信息url >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return null;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return null;
		}
		String jsonRows = objRows.getString("rows");
		List<PcbJoinBoardOrder> datas = JsonUtil.json2array(jsonRows, PcbJoinBoardOrder.class);
		return datas;
	}

	private void importJoinBoardWays(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_JOIN_BOARD_WAY_IMPORT_TIME;
		Date startTime = null;
		RecordUtils config = configService.getUtilRecord(lastExportTime);
		if (config != null) {
			startTime = config.getValueTime();
			startTime = DateCalcUtil.subTime(startTime, Calendar.MINUTE, 10);
		}
		Date endTime = date;

		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.PCB_SERVICE_ADDRESS);
		String serviceUrl = pcbServiceUrl + "/ccs_web_service/get_join_board_ways";
		if (startTime != null) {
			serviceUrl += "?updated_at_start=" + DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss");
		}

		logger.info("获取拼板方式url >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return;
		}
		String jsonRows = objRows.getString("rows");
		List<PcbJoinBoardWay> datas = JsonUtil.json2array(jsonRows, PcbJoinBoardWay.class);
		if (datas == null || datas.isEmpty()) {
			logger.info("没有新的拼板方式数据.");
			return;
		}

		List<Integer> codes = null;
		try {
			codes = DataUtil.fetchField2list(datas, PcbJoinBoardWay.class, "innerId");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		if (codes == null || codes.isEmpty()) {
			return;
		}
		Map<Integer, JoinBoardWay> dbRecordMap = new HashMap<Integer, JoinBoardWay>();
		try {
			List<JoinBoardWay> dbRecords = joinBoardWayMapper.listByInnerIds(codes);
			if (dbRecords != null && !dbRecords.isEmpty()) {
				dbRecordMap = DataUtil.list2map(dbRecords, JoinBoardWay.class, "innerId");
			}
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}

		for (PcbJoinBoardWay data : datas) {
			try {
				JoinBoardWay record = (JoinBoardWay) CommonUtil.fetchMapFields(data, JoinBoardWay.class);
				JoinBoardWay dbRecord = dbRecordMap.get(record.getInnerId());
				if (dbRecord == null) {
					record.setCreateTime(new Date());
					joinBoardWayMapper.insertSelective(record);
				} else if (dbRecord.getInnerUpdateTime() == null
						|| record.getInnerUpdateTime().after(dbRecord.getInnerUpdateTime())) { // 更新
					record.setId(dbRecord.getId());
					joinBoardWayMapper.updateByPrimaryKeySelective(record);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				logger.error(e.getMessage(), e);
			}
		}

		supportService.doReloadCache();

		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步拼板方式结束.");
	}

	private void importSurfaceProcesses(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_SURFACE_PROCESS_IMPORT_TIME;
		Date startTime = null;
		RecordUtils config = configService.getUtilRecord(lastExportTime);
		if (config != null) {
			startTime = config.getValueTime();
			startTime = DateCalcUtil.subTime(startTime, Calendar.MINUTE, 10);
		}
		Date endTime = date;

		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.PCB_SERVICE_ADDRESS);
		String serviceUrl = pcbServiceUrl + "/ccs_web_service/get_surface_processes";
		if (startTime != null) {
			serviceUrl += "?updated_at_start=" + DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss");
		}

		logger.info("获取表面处理url >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return;
		}
		String jsonRows = objRows.getString("rows");
		List<PcbSurfaceProcess> datas = JsonUtil.json2array(jsonRows, PcbSurfaceProcess.class);
		if (datas == null || datas.isEmpty()) {
			logger.info("没有新的表面处理数据.");
			return;
		}

		List<Integer> codes = null;
		try {
			codes = DataUtil.fetchField2list(datas, PcbSurfaceProcess.class, "innerId");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		if (codes == null || codes.isEmpty()) {
			return;
		}
		Map<Integer, SurfaceProcess> dbRecordMap = new HashMap<Integer, SurfaceProcess>();
		try {
			List<SurfaceProcess> dbRecords = surfaceProcessMapper.listByInnerIds(codes);
			if (dbRecords != null && !dbRecords.isEmpty()) {
				dbRecordMap = DataUtil.list2map(dbRecords, SurfaceProcess.class, "innerId");
			}
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}

		for (PcbSurfaceProcess data : datas) {
			try {
				SurfaceProcess record = (SurfaceProcess) CommonUtil
						.fetchMapFields(data, SurfaceProcess.class);
				SurfaceProcess dbRecord = dbRecordMap.get(record.getInnerId());
				if (dbRecord == null) {
					record.setCreateTime(new Date());
					surfaceProcessMapper.insertSelective(record);
				} else if (dbRecord.getInnerUpdateTime() == null
						|| record.getInnerUpdateTime().after(dbRecord.getInnerUpdateTime())) { // 更新
					record.setId(dbRecord.getId());
					surfaceProcessMapper.updateByPrimaryKeySelective(record);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				logger.error(e.getMessage(), e);
			}
		}

		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步表面处理结束.");
	}

	private void importBoardPlyTolerance(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_BOARD_PLY_TOLERANCE_IMPORT_TIME;
		Date startTime = null;
		RecordUtils config = configService.getUtilRecord(lastExportTime);
		if (config != null) {
			startTime = config.getValueTime();
			startTime = DateCalcUtil.subTime(startTime, Calendar.MINUTE, 10);
		}
		Date endTime = date;

		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.PCB_SERVICE_ADDRESS);
		String serviceUrl = pcbServiceUrl + "/ccs_web_service/get_board_ply_tolerances";
		if (startTime != null) {
			serviceUrl += "?updated_at_start=" + DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss");
		}

		logger.info("获取板厚公差url >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return;
		}
		String jsonRows = objRows.getString("rows");
		List<PcbBoardPlyTolerance> datas = JsonUtil.json2array(jsonRows, PcbBoardPlyTolerance.class);
		if (datas == null || datas.isEmpty()) {
			logger.info("没有新的板厚工差数据.");
			return;
		}

		List<Integer> codes = null;
		try {
			codes = DataUtil.fetchField2list(datas, PcbBoardPlyTolerance.class, "innerId");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		if (codes == null || codes.isEmpty()) {
			return;
		}
		Map<Integer, BoardPlyTolerance> dbRecordMap = new HashMap<Integer, BoardPlyTolerance>();
		try {
			List<BoardPlyTolerance> dbRecords = boardPlyToleranceMapper.listByInnerIds(codes);
			if (dbRecords != null && !dbRecords.isEmpty()) {
				dbRecordMap = DataUtil.list2map(dbRecords, BoardPlyTolerance.class, "innerId");
			}
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}

		for (PcbBoardPlyTolerance data : datas) {
			try {
				BoardPlyTolerance record = (BoardPlyTolerance) CommonUtil.fetchMapFields(data,
						BoardPlyTolerance.class);
				BoardPlyTolerance dbRecord = dbRecordMap.get(record.getInnerId());
				if (dbRecord == null) {
					record.setCreateTime(new Date());
					boardPlyToleranceMapper.insertSelective(record);
				} else if (dbRecord.getInnerUpdateTime() == null
						|| record.getInnerUpdateTime().after(dbRecord.getInnerUpdateTime())) { // 更新
					record.setId(dbRecord.getId());
					boardPlyToleranceMapper.updateByPrimaryKeySelective(record);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				logger.error(e.getMessage(), e);
			}
		}

		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步板厚公差结束.");
	}

	private void importBasemAterial(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_BASEM_ATERIAL_IMPORT_TIME;
		Date startTime = null;
		RecordUtils config = configService.getUtilRecord(lastExportTime);
		if (config != null) {
			startTime = config.getValueTime();
			startTime = DateCalcUtil.subTime(startTime, Calendar.MINUTE, 10);
		}
		Date endTime = date;

		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.PCB_SERVICE_ADDRESS);
		String serviceUrl = pcbServiceUrl + "/params_api/getBasemAterial";
		if (startTime != null) {
			serviceUrl += "?updated_at_start=" + DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss");
		}

		logger.info("获取基材信息url >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return;
		}
		String jsonRows = objRows.getString("rows");
		List<PcbBasemAterial> datas = JsonUtil.json2array(jsonRows, PcbBasemAterial.class);
		if (datas == null || datas.isEmpty()) {
			logger.info("没有新的基材数据.");
			return;
		}

		List<Integer> codes = null;
		try {
			codes = DataUtil.fetchField2list(datas, PcbBasemAterial.class, "innerId");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		if (codes == null || codes.isEmpty()) {
			return;
		}
		Map<Integer, BasemAterial> dbRecordMap = new HashMap<Integer, BasemAterial>();
		try {
			List<BasemAterial> dbRecords = basemAterialMapper.listByInnerIds(codes);
			if (dbRecords != null && !dbRecords.isEmpty()) {
				dbRecordMap = DataUtil.list2map(dbRecords, BasemAterial.class, "innerId");
			}
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}

		for (PcbBasemAterial data : datas) {
			try {
				BasemAterial record = (BasemAterial) CommonUtil.fetchMapFields(data, BasemAterial.class);
				BasemAterial dbRecord = dbRecordMap.get(record.getInnerId());
				if (dbRecord == null) {
					record.setCreateTime(new Date());
					basemAterialMapper.insertSelective(record);
				} else if (dbRecord.getInnerUpdateTime() == null
						|| record.getInnerUpdateTime().after(dbRecord.getInnerUpdateTime())) { // 更新
					record.setId(dbRecord.getId());
					basemAterialMapper.updateByPrimaryKeySelective(record);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				logger.error(e.getMessage(), e);
			}
		}

		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步基材信息结束.");
	}

	private void importFrameTolerance(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_FRAME_TOLERANCE_IMPORT_TIME;
		Date startTime = null;
		RecordUtils config = configService.getUtilRecord(lastExportTime);
		if (config != null) {
			startTime = config.getValueTime();
			startTime = DateCalcUtil.subTime(startTime, Calendar.MINUTE, 10);
		}
		Date endTime = date;

		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.PCB_SERVICE_ADDRESS);
		String serviceUrl = pcbServiceUrl + "/params_api/getFrameTolerance";
		if (startTime != null) {
			serviceUrl += "?updated_at_start=" + DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss");
		}

		logger.info("获取边框公差url >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return;
		}
		String jsonRows = objRows.getString("rows");
		List<PcbFrameTolerances> datas = JsonUtil.json2array(jsonRows, PcbFrameTolerances.class);
		if (datas == null || datas.isEmpty()) {
			logger.info("没有新的边框公差数据.");
			return;
		}

		List<Integer> codes = null;
		try {
			codes = DataUtil.fetchField2list(datas, PcbFrameTolerances.class, "innerId");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		if (codes == null || codes.isEmpty()) {
			return;
		}
		Map<Integer, FrameTolerance> dbRecordMap = new HashMap<Integer, FrameTolerance>();
		try {
			List<FrameTolerance> dbRecords = frameToleranceMapper.listByInnerIds(codes);
			if (dbRecords != null && !dbRecords.isEmpty()) {
				dbRecordMap = DataUtil.list2map(dbRecords, BasemAterial.class, "innerId");
			}
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}

		for (PcbFrameTolerances data : datas) {
			try {
				FrameTolerance record = (FrameTolerance) CommonUtil
						.fetchMapFields(data, FrameTolerance.class);
				FrameTolerance dbRecord = dbRecordMap.get(record.getInnerId());
				if (dbRecord == null) {
					record.setCreateTime(new Date());
					frameToleranceMapper.insertSelective(record);
				} else if (dbRecord.getInnerUpdateTime() == null
						|| record.getInnerUpdateTime().after(dbRecord.getInnerUpdateTime())) { // 更新
					record.setId(dbRecord.getId());
					frameToleranceMapper.updateByPrimaryKeySelective(record);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				logger.error(e.getMessage(), e);
			}
		}

		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步边框公差结束.");
	}

	private void importCraft(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_CRAFT_IMPORT_TIME;
		Date startTime = null;
		RecordUtils config = configService.getUtilRecord(lastExportTime);
		if (config != null) {
			startTime = config.getValueTime();
			startTime = DateCalcUtil.subTime(startTime, Calendar.MINUTE, 10);
		}
		Date endTime = date;

		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.PCB_SERVICE_ADDRESS);
		String serviceUrl = pcbServiceUrl + "/ccs_web_service/get_crafts";
		if (startTime != null) {
			serviceUrl += "?updated_at_start=" + DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss");
		}

		logger.info("获取生产工艺url >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return;
		}
		String jsonRows = objRows.getString("rows");
		List<PcbCraftInfo> datas = JsonUtil.json2array(jsonRows, PcbCraftInfo.class);
		if (datas == null || datas.isEmpty()) {
			logger.info("没有新的生产工艺数据.");
			return;
		}

		List<Integer> codes = null;
		try {
			codes = DataUtil.fetchField2list(datas, PcbCraftInfo.class, "innerId");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		if (codes == null || codes.isEmpty()) {
			return;
		}
		Map<Integer, CraftInfo> dbRecordMap = new HashMap<Integer, CraftInfo>();
		try {
			List<CraftInfo> dbRecords = craftInfoMapper.listByInnerIds(codes);
			if (dbRecords != null && !dbRecords.isEmpty()) {
				dbRecordMap = DataUtil.list2map(dbRecords, CraftInfo.class, "innerId");
			}
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}

		for (PcbCraftInfo data : datas) {
			try {
				CraftInfo record = (CraftInfo) CommonUtil.fetchMapFields(data, CraftInfo.class);
				CraftInfo dbRecord = dbRecordMap.get(record.getInnerId());
				if (dbRecord == null) {
					record.setCreateTime(new Date());
					craftInfoMapper.insertSelective(record);
				} else if (dbRecord.getInnerUpdateTime() == null
						|| record.getInnerUpdateTime().after(dbRecord.getInnerUpdateTime())) { // 更新
					record.setId(dbRecord.getId());
					craftInfoMapper.updateByPrimaryKeySelective(record);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				logger.error(e.getMessage(), e);
			}
		}

		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步生产工艺结束.");
	}

	private void importUsers(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_USER_IMPORT_TIME;
		Date startTime = null;
		RecordUtils config = configService.getUtilRecord(lastExportTime);
		if (config != null) {
			startTime = config.getValueTime();
			startTime = DateCalcUtil.subTime(startTime, Calendar.MINUTE, 10);
		}
		Date endTime = date;

		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.UMS_SERVICE_ADDRESS);
		StringBuilder sbUrl = new StringBuilder();
		sbUrl.append(pcbServiceUrl).append("/ccs_web_service/get_users");
		if (startTime != null) {
			sbUrl.append("?updated_at=").append(DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss"));
		}

		String serviceUrl = sbUrl.toString();
		logger.info("获取用户信息url >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return;
		}
		String jsonRows = objRows.getString("rows");
		List<UmsUser> datas = JsonUtil.json2array(jsonRows, UmsUser.class);
		if (datas == null || datas.isEmpty()) {
			logger.info("没有新的用户数据.");
			return;
		}

		List<String> codes = null;
		try {
			codes = DataUtil.fetchField2list(datas, UmsUser.class, "userCode");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		if (codes == null || codes.isEmpty()) {
			return;
		}
		Map<String, WxtbUser> dbRecordMap = new HashMap<String, WxtbUser>();
		try {
			List<WxtbUser> dbRecords = wxtbUserMapper.listByCodes(codes);
			if (dbRecords != null && !dbRecords.isEmpty()) {
				dbRecordMap = DataUtil.list2map(dbRecords, WxtbUser.class, "userCode");
			}
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}

		for (UmsUser data : datas) {
			try {
				WxtbUser record = (WxtbUser) CommonUtil.fetchMapFields(data, WxtbUser.class);
				WxtbUser dbRecord = dbRecordMap.get(record.getUserCode());
				if (dbRecord == null) {
					record.setCreateTime(new Date());
					wxtbUserMapper.insertSelective(record);
				} else if (dbRecord.getInnerUpdateTime() == null
						|| record.getInnerUpdateTime().after(dbRecord.getInnerUpdateTime())) { // 更新
					record.setId(dbRecord.getId());
					wxtbUserMapper.updateByPrimaryKeySelective(record);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				logger.error(e.toString() + ",order json=" + JsonUtil.obj2json(data));
			}
		}

		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步用户结束.");
	}

	private void importDepartments(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_DEPARTMENT_IMPORT_TIME;
		Date startTime = null;
		RecordUtils config = configService.getUtilRecord(lastExportTime);
		if (config != null) {
			startTime = config.getValueTime();
			startTime = DateCalcUtil.subTime(startTime, Calendar.MINUTE, 10);
		}
		Date endTime = date;

		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.UMS_SERVICE_ADDRESS);
		StringBuilder sbUrl = new StringBuilder();
		sbUrl.append(pcbServiceUrl).append("/ccs_web_service/get_departments");
		if (startTime != null) {
			sbUrl.append("?updated_at=").append(DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss"));
		}

		String serviceUrl = sbUrl.toString();
		logger.info("获取部门信息url >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return;
		}
		String jsonRows = objRows.getString("rows");
		List<UmsDepartment> datas = JsonUtil.json2array(jsonRows, UmsDepartment.class);
		if (datas == null || datas.isEmpty()) {
			logger.info("没有新的部门数据.");
			return;
		}

		List<String> codes = null;
		try {
			codes = DataUtil.fetchField2list(datas, UmsDepartment.class, "departmentCode");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		if (codes == null || codes.isEmpty()) {
			return;
		}
		Map<String, WxtbDepartment> dbRecordMap = new HashMap<String, WxtbDepartment>();
		try {
			List<WxtbDepartment> dbRecords = wxtbDepartmentMapper.listByCodes(codes);
			if (dbRecords != null && !dbRecords.isEmpty()) {
				dbRecordMap = DataUtil.list2map(dbRecords, WxtbDepartment.class, "departmentCode");
			}
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}

		for (UmsDepartment data : datas) {
			try {
				WxtbDepartment record = (WxtbDepartment) CommonUtil
						.fetchMapFields(data, WxtbDepartment.class);
				WxtbDepartment dbRecord = dbRecordMap.get(record.getDepartmentCode());
				if (dbRecord == null) {
					record.setCreateTime(new Date());
					wxtbDepartmentMapper.insertSelective(record);
				} else if (dbRecord.getInnerUpdateTime() == null
						|| record.getInnerUpdateTime().after(dbRecord.getInnerUpdateTime())) { // 更新
					record.setId(dbRecord.getId());
					wxtbDepartmentMapper.updateByPrimaryKeySelective(record);
				}
			} catch (Exception e) {
				logger.error(e.toString() + ",order json=" + JsonUtil.obj2json(data));
				logger.error(e.getMessage(), e);
			}
		}

		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步部门结束.");
	}
	
	/**
	 * 导入smt订单
	 * @param date
	 */
	private void importSmtOrders(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_SMT_ORDER_IMPORT_TIME;
		Date startTime = null;
		RecordUtils config = configService.getUtilRecord(lastExportTime);
		if (config != null) {
			startTime = config.getValueTime();
			startTime = DateCalcUtil.subTime(startTime, Calendar.MINUTE, 10);
		}
		Date endTime = date;
		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.SMT_SERVICE_ADDRESS);
		StringBuilder sbUrl = new StringBuilder();
		sbUrl.append(pcbServiceUrl).append("/oiss_web_service/get_smt_orders");
		if (startTime == null) {
			startTime = DateCalcUtil.subTime(date, Calendar.MONTH, 6);
		}
		sbUrl.append("?updated_at=").append(DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss"));
		String serviceUrl = sbUrl.toString();
		logger.info("获取SMT工单 >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
//		String json = FileUtil.readFileString("D:/aaa/json/smt.txt");
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return;
		}
		String jsonRows = objRows.getString("rows");
		List<SmtOrderChannel> datas = JsonUtil.json2array(jsonRows, SmtOrderChannel.class);
		if (datas == null || datas.isEmpty()) {
			logger.info("没有新的smt数据.");
			return;
		}
		List<String> orderNumbers = null;
		try {
			orderNumbers = DataUtil.fetchField2list(datas, SmtOrderChannel.class, "orderNumber");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage());
		}
		if (orderNumbers == null || orderNumbers.isEmpty()) {
			return;
		}
		
		Map<String, SmtOrder> dbRecordMap = new HashMap<>();
		try {
			List<SmtOrder> dbRecords = smtOrderMapper.listByCodes(orderNumbers);
			if (dbRecords != null && !dbRecords.isEmpty()) {
				dbRecordMap = DataUtil.list2map(dbRecords, SmtOrder.class, "orderNumber");
			}
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		for (SmtOrderChannel data : datas) {
			try {
				SmtOrder record = (SmtOrder) CommonUtil
						.fetchMapFields(data, SmtOrder.class);
				SmtOrder dbRecord = dbRecordMap.get(data.getOrderNumber());
				if (dbRecord == null) {
					record.setCreateTime(new Date());
					smtOrderMapper.insertSelective(record);
				} else { // 更新
					record.setId(dbRecord.getId());
					smtOrderMapper.updateByPrimaryKeySelective(record);
				}
			} catch (Exception e) {
				logger.error(e.toString() + ",order json=" + JsonUtil.obj2json(data));
				logger.error(e.getMessage(), e);
			}
		}
		
		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步smt工单结束.");
	}
	
	/**
	 * 导入col订单
	 * @param date
	 */
	private void importColOrders(Date date) {
		ConfigEnum lastExportTime = ConfigEnum.LAST_COL_ORDER_IMPORT_TIME;
		Date startTime = null;
		RecordUtils config = configService.getUtilRecord(lastExportTime);
		if (config != null) {
			startTime = config.getValueTime();
			startTime = DateCalcUtil.subTime(startTime, Calendar.MINUTE, 10);
		}
		Date endTime = date;
		final String pcbServiceUrl = ConfigProperties.getProperty(ConfigEnum.COL_SERVICE_ADDRESS);
		StringBuilder sbUrl = new StringBuilder();
		sbUrl.append(pcbServiceUrl).append("/oiss_web_service/get_col_orders");
		if (startTime == null) {
			startTime = DateCalcUtil.subTime(date, Calendar.MONTH, 6);
		}
		sbUrl.append("?updated_at=").append(DateTimeUtil.date2dateTimeStr(startTime, "yyyyMMddHHmmss"));
		String serviceUrl = sbUrl.toString();
		logger.info("获取COL工单 >> " + serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
//		String json = FileUtil.readFileString("D:/aaa/json/col.txt");
		if (StringUtils.isBlank(json)) {
			logger.info("调用数据接口返回json为空");
			return;
		}
		JSONObject objRows = JSONObject.parseObject(json);
		if (objRows == null) {
			logger.error("json解板失败:" + json);
			return;
		}
		String jsonRows = objRows.getString("rows");
		List<ColOrderChannel> datas = JsonUtil.json2array(jsonRows, ColOrderChannel.class);
		if (datas == null || datas.isEmpty()) {
			logger.info("没有新的col数据.");
			return;
		}
		List<String> orderNumbers = null;
		try {
			orderNumbers = DataUtil.fetchField2list(datas, ColOrderChannel.class, "orderNumber");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage());
		}
		if (orderNumbers == null || orderNumbers.isEmpty()) {
			return;
		}
		Map<String, ColOrder> dbRecordMap = new HashMap<>();
		try {
			List<ColOrder> dbRecords = colOrderMapper.listByCodes(orderNumbers);
			if (dbRecords != null && !dbRecords.isEmpty()) {
				dbRecordMap = DataUtil.list2map(dbRecords, ColOrder.class, "orderNumber");
			}
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		for (ColOrderChannel data : datas) {
			try {
				ColOrder record = (ColOrder) CommonUtil
						.fetchMapFields(data, ColOrder.class);
				ColOrder dbRecord = dbRecordMap.get(data.getOrderNumber());
				if (dbRecord == null) {
					record.setCreateTime(new Date());
					colOrderMapper.insertSelective(record);
				} else { // 更新
					record.setId(dbRecord.getId());
					colOrderMapper.updateByPrimaryKeySelective(record);
				}
			} catch (Exception e) {
				logger.error(e.toString() + ",order json=" + JsonUtil.obj2json(data));
				logger.error(e.getMessage(), e);
			}
		}
		
		configService.setUtilRecord(lastExportTime, endTime, "");
		logger.info("同步col工单结束.");
		
	}
}
