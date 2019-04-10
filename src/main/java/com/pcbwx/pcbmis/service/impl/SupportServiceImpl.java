package com.pcbwx.pcbmis.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.authkit.bean.AkRoleAuth;
import com.pcbwx.authkit.bean.AkUserAuth;
import com.pcbwx.authkit.bean.AkUserRole;
import com.pcbwx.authkit.callback.IAuthCallback;
import com.pcbwx.ohkit.callback.IOpinionCallback;
import com.pcbwx.pcbmis.bean.InvalidSerialNumber;
import com.pcbwx.pcbmis.bean.SerialNumber;
import com.pcbwx.pcbmis.bean.user.RoleInfo;
import com.pcbwx.pcbmis.bean.user.UserAuthEnable;
import com.pcbwx.pcbmis.bean.user.UserAuthItem;
import com.pcbwx.pcbmis.bean.user.UserManageInfo;
import com.pcbwx.pcbmis.bean.user.UserRoleEnable;
import com.pcbwx.pcbmis.bean.user.UserRoleItem;
import com.pcbwx.pcbmis.common.ConfigProperties;
import com.pcbwx.pcbmis.component.CacheService;
import com.pcbwx.pcbmis.dao.BasemAterialMapper;
import com.pcbwx.pcbmis.dao.BoardPlyToleranceMapper;
import com.pcbwx.pcbmis.dao.CompanyInfoMapper;
import com.pcbwx.pcbmis.dao.ConfigMapper;
import com.pcbwx.pcbmis.dao.CraftInfoMapper;
import com.pcbwx.pcbmis.dao.DictionaryMapper;
import com.pcbwx.pcbmis.dao.EdaGuestMapper;
import com.pcbwx.pcbmis.dao.FactoryInfoMapper;
import com.pcbwx.pcbmis.dao.FormDefaultPersonMapper;
import com.pcbwx.pcbmis.dao.FrameToleranceMapper;
import com.pcbwx.pcbmis.dao.JoinBoardWayMapper;
import com.pcbwx.pcbmis.dao.MenuMapper;
import com.pcbwx.pcbmis.dao.OperateLogMapper;
import com.pcbwx.pcbmis.dao.PcbCheckOrderMapper;
import com.pcbwx.pcbmis.dao.PcbCheckReportMapper;
import com.pcbwx.pcbmis.dao.PcbCheckTemplateMapper;
import com.pcbwx.pcbmis.dao.PcbReportTemplateMapper;
import com.pcbwx.pcbmis.dao.RecordUtilsMapper;
import com.pcbwx.pcbmis.dao.RoleAuthMapper;
import com.pcbwx.pcbmis.dao.SurfaceProcessMapper;
import com.pcbwx.pcbmis.dao.SystemLogMapper;
import com.pcbwx.pcbmis.dao.UserAuthMapper;
import com.pcbwx.pcbmis.dao.UserRoleMapper;
import com.pcbwx.pcbmis.dao.UserRoleRelationMapper;
import com.pcbwx.pcbmis.dao.WxtbDepartmentMapper;
import com.pcbwx.pcbmis.dao.WxtbUserMapper;
import com.pcbwx.pcbmis.enums.ConfigEnum;
import com.pcbwx.pcbmis.exception.SerialNumberException;
import com.pcbwx.pcbmis.model.BasemAterial;
import com.pcbwx.pcbmis.model.BoardPlyTolerance;
import com.pcbwx.pcbmis.model.CompanyInfo;
import com.pcbwx.pcbmis.model.CraftInfo;
import com.pcbwx.pcbmis.model.Dictionary;
import com.pcbwx.pcbmis.model.EdaGuest;
import com.pcbwx.pcbmis.model.FactoryInfo;
import com.pcbwx.pcbmis.model.FormDefaultPerson;
import com.pcbwx.pcbmis.model.FrameTolerance;
import com.pcbwx.pcbmis.model.JoinBoardWay;
import com.pcbwx.pcbmis.model.Menu;
import com.pcbwx.pcbmis.model.OperateLog;
import com.pcbwx.pcbmis.model.PcbCheckOrder;
import com.pcbwx.pcbmis.model.PcbCheckReport;
import com.pcbwx.pcbmis.model.PcbCheckTemplate;
import com.pcbwx.pcbmis.model.PcbReportTemplate;
import com.pcbwx.pcbmis.model.RecordUtils;
import com.pcbwx.pcbmis.model.RoleAuth;
import com.pcbwx.pcbmis.model.SurfaceProcess;
import com.pcbwx.pcbmis.model.SystemLog;
import com.pcbwx.pcbmis.model.UserAuth;
import com.pcbwx.pcbmis.model.UserRole;
import com.pcbwx.pcbmis.model.UserRoleRelation;
import com.pcbwx.pcbmis.model.WxtbDepartment;
import com.pcbwx.pcbmis.model.WxtbUser;
import com.pcbwx.pcbmis.service.ConfigService;
import com.pcbwx.pcbmis.service.SupportService;
import com.pcbwx.pcbmis.utils.DataUtil;
import com.pcbwx.pcbmis.utils.DateCalcUtil;
import com.pcbwx.pcbmis.utils.DateCalcUtil.DateParam;
import com.pcbwx.pcbmis.utils.DateTimeUtil;
import com.pcbwx.pcbmis.utils.HttpConnectionManager;
import com.pcbwx.pcbmis.utils.HttpUtil;
import com.pcbwx.pcbmis.utils.JsonUtil;
import com.pcbwx.pcbmis.utils.LogHistory;
import com.pcbwx.pcbmis.utils.PcbmisUtil;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;

/**
 * 日志接口实现类
 * 
 * @author 王海龙
 *
 */
@Service("supportService")
public class SupportServiceImpl implements SupportService, IOpinionCallback, IAuthCallback {

	private static Logger logger = Logger.getLogger(SupportServiceImpl.class);

	@Autowired
	private CacheService cacheService;
	@Autowired
	private DictionaryMapper dictionaryMapper;
	@Autowired
	private ConfigService configService;

	@Autowired
	private ConfigMapper configMapper;
	@Autowired
	private RecordUtilsMapper recordUtilsMapper;

	@Autowired
	private EdaGuestMapper edaGuestMapper;
	@Autowired
	private WxtbDepartmentMapper wxtbDepartmentMapper;
	@Autowired
	private JoinBoardWayMapper joinBoardWayMapper;
	@Autowired
	private FactoryInfoMapper factoryInfoMapper;
	@Autowired
	private SurfaceProcessMapper surfaceProcessMapper;
	@Autowired
	private CompanyInfoMapper companyInfoMapper;
	@Autowired
	private BoardPlyToleranceMapper boardPlyToleranceMapper;
	@Autowired
	private BasemAterialMapper basemAterialMapper;
	@Autowired
	private FrameToleranceMapper frameToleranceMapper;
	@Autowired
	private CraftInfoMapper craftInfoMapper;
	@Autowired
	private PcbCheckOrderMapper pcbCheckOrderMapper;
	@Autowired
	private PcbCheckReportMapper pcbCheckReportMapper;

	@Autowired
	private PcbCheckTemplateMapper pcbCheckTemplateMapper;
	@Autowired
	private PcbReportTemplateMapper pcbReportTemplateMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private UserRoleRelationMapper userRoleRelationMapper;
	@Autowired
	private WxtbUserMapper wxtbUserMapper;
	@Autowired
	private RoleAuthMapper roleAuthMapper;
	@Autowired
	private UserAuthMapper userAuthMapper;
	@Autowired
	private SystemLogMapper systemLogMapper;
	@Autowired
	private OperateLogMapper operateLogMapper;
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private FormDefaultPersonMapper formDefaultPersonMapper;

	@PostConstruct
	public void reloadCacheInfo() {
		logger.info("启动载入缓存...");

		configService.setUtilRecord(ConfigEnum.LAST_RELOAD_TIME, new Date(), "");
		reloadDictionary();

		reloadEdaGuest();
		reloadWxtbDepartment();
		reloadJoinBoardWay();
		reloadFactory();
		reloadSurfaceProcess();
		reloadCompany();
		reloadCraftInfo();
		reloadPcbCheckTemplate();
		reloadPcbReportTemplate();
		reloadUserRole();
		reloadUserRoleRelation();
		reloadWxtbUser();
		reloadUserAuth();
		reloadRoleAuth();
		reloadMenu();
		reloadFormDefaultPerson();
	}

	public void reloadDictionary() {
		List<Dictionary> dictionarys = dictionaryMapper.load();
		cacheService.reloadDictionary(dictionarys);
	}

	private void reloadEdaGuest() {
		List<EdaGuest> edaGuests = edaGuestMapper.load();
		cacheService.reloadEdaGuest(edaGuests);
	}

	private void reloadWxtbDepartment() {
		List<WxtbDepartment> wxtbDepartments = wxtbDepartmentMapper.load();
		cacheService.reloadWxtbDepartment(wxtbDepartments);
	}

	private void reloadJoinBoardWay() {
		List<JoinBoardWay> records = joinBoardWayMapper.load();
		cacheService.reloadJoinBoardWay(records);
	}

	private void reloadFactory() {
		List<FactoryInfo> records = factoryInfoMapper.load();
		cacheService.reloadFactory(records);
	}

	private void reloadSurfaceProcess() {
		List<SurfaceProcess> records = surfaceProcessMapper.load();
		cacheService.reloadSurfaceProcess(records);
	}

	private void reloadCompany() {
		List<CompanyInfo> records = companyInfoMapper.load();
		cacheService.reloadCompany(records);
	}

	private void reloadBoardPlyTolerance() {
		List<BoardPlyTolerance> records = boardPlyToleranceMapper.load();
		cacheService.reloadBoardPlyTolerance(records);
	}

	private void reloadBasemAterial() {
		List<BasemAterial> records = basemAterialMapper.load();
		cacheService.reloadBasemAterial(records);
	}

	private void reloadFrameTolerance() {
		List<FrameTolerance> records = frameToleranceMapper.load();
		cacheService.reloadFrameTolerance(records);
	}

	private void reloadCraftInfo() {
		List<CraftInfo> records = craftInfoMapper.load();
		cacheService.reloadCraftInfo(records);
	}

	private void reloadPcbCheckTemplate() {
		List<PcbCheckTemplate> records = pcbCheckTemplateMapper.load();
		cacheService.reloadPcbCheckTemplate(records);
	}

	private void reloadPcbReportTemplate() {
		List<PcbReportTemplate> records = pcbReportTemplateMapper.load();
		cacheService.reloadPcbReportTemplate(records);
	}

	private void reloadUserRole() {
		List<UserRole> records = userRoleMapper.load();
		logger.info(records.size() + "");
		cacheService.reloadUserRole(records);
	}

	private void reloadUserRoleRelation() {
		List<UserRoleRelation> records = userRoleRelationMapper.load();
		cacheService.reloadUserRoleRelation(records);
	}

	private void reloadWxtbUser() {
		List<WxtbUser> records = wxtbUserMapper.load();
		cacheService.reloadWxtbUser(records);
	}

	private void reloadUserAuth() {
		List<UserAuth> userAuths = userAuthMapper.load();
		cacheService.reloadUserAuth(userAuths);
	}

	private void reloadRoleAuth() {
		List<RoleAuth> roleAuths = roleAuthMapper.load();
		cacheService.reloadRoleAuth(roleAuths);
	}

	private void reloadMenu() {
		List<Menu> menus = menuMapper.load();
		cacheService.reloadMenu(menus);
	}

	@Override
	public void reloadFormDefaultPerson() {
		List<FormDefaultPerson> formDefaultPersons = formDefaultPersonMapper.load();
		cacheService.reloadFormDefaultPerson(formDefaultPersons);
	}

	@Override
	public synchronized boolean doReloadCache() {
		logger.info("载入...");
		Date lastDate = configService.getUtilDate(ConfigEnum.LAST_RELOAD_TIME);
		Date now = new Date();

		Date lastRecordTime = null;
		boolean haveReload = false;
		lastRecordTime = dictionaryMapper.selectLastRecordTime();
		if (lastDate == null || (lastRecordTime != null && lastDate.before(lastRecordTime))) {
			reloadDictionary();
			haveReload = true;
			logger.info("字典表重载完成...");
		}
		// -------------cad-----------
		lastRecordTime = edaGuestMapper.selectLastRecordTime();
		if (lastDate == null || (lastRecordTime != null && lastDate.before(lastRecordTime))) {
			reloadEdaGuest();
			haveReload = true;
		}
		lastRecordTime = wxtbDepartmentMapper.selectLastRecordTime();
		if (lastDate == null || (lastRecordTime != null && lastDate.before(lastRecordTime))) {
			reloadWxtbDepartment();
			haveReload = true;
		}
		lastRecordTime = joinBoardWayMapper.selectLastRecordTime();
		if (lastDate == null || (lastRecordTime != null && lastDate.before(lastRecordTime))) {
			reloadJoinBoardWay();
			haveReload = true;
		}
		lastRecordTime = factoryInfoMapper.selectLastRecordTime();
		if (lastDate == null || (lastRecordTime != null && lastDate.before(lastRecordTime))) {
			reloadFactory();
			haveReload = true;
		}
		lastRecordTime = surfaceProcessMapper.selectLastRecordTime();
		if (lastDate == null || (lastRecordTime != null && lastDate.before(lastRecordTime))) {
			reloadSurfaceProcess();
			haveReload = true;
		}
		lastRecordTime = companyInfoMapper.selectLastRecordTime();
		if (lastDate == null || (lastRecordTime != null && lastDate.before(lastRecordTime))) {
			reloadCompany();
			haveReload = true;
		}
		lastRecordTime = boardPlyToleranceMapper.selectLastRecordTime();
		if (lastDate == null || (lastRecordTime != null && lastDate.before(lastRecordTime))) {
			reloadBoardPlyTolerance();
			haveReload = true;
		}
		lastRecordTime = basemAterialMapper.selectLastRecordTime();
		if (lastDate == null || (lastRecordTime != null && lastDate.before(lastRecordTime))) {
			reloadBasemAterial();
			haveReload = true;
		}
		lastRecordTime = frameToleranceMapper.selectLastRecordTime();
		if (lastDate == null || (lastRecordTime != null && lastDate.before(lastRecordTime))) {
			reloadFrameTolerance();
			haveReload = true;
		}
		lastRecordTime = craftInfoMapper.selectLastRecordTime();
		if (lastDate == null || (lastRecordTime != null && lastDate.before(lastRecordTime))) {
			reloadCraftInfo();
			haveReload = true;
		}

		lastRecordTime = pcbCheckTemplateMapper.selectLastRecordTime();
		if (lastDate == null || (lastRecordTime != null && lastDate.before(lastRecordTime))) {
			reloadPcbCheckTemplate();
			haveReload = true;
		}
		lastRecordTime = userRoleMapper.selectLastRecordTime();
		if (lastDate == null || (lastRecordTime != null && lastDate.before(lastRecordTime))) {
			reloadUserRole();
			haveReload = true;
		}
		lastRecordTime = userRoleRelationMapper.selectLastRecordTime();
		if (lastDate == null || (lastRecordTime != null && lastDate.before(lastRecordTime))) {
			reloadUserRoleRelation();
			haveReload = true;
		}
		lastRecordTime = wxtbUserMapper.selectLastRecordTime();
		if (lastDate == null || (lastRecordTime != null && lastDate.before(lastRecordTime))) {
			reloadWxtbUser();
			haveReload = true;
		}

		lastRecordTime = configMapper.selectLastRecordTime();
		if (lastDate == null || (lastRecordTime != null && lastDate.before(lastRecordTime))) {
			configService.reloadConfig();
			haveReload = true;
		}

		if (haveReload) {
			configService.setUtilRecord(ConfigEnum.LAST_RELOAD_TIME, now, "");
		}

		return true;
	}

	@Override
	public SerialNumber getQrgsSerialNumber(String userInnerCode, String documentName, Date date, String companyCode) {
		final String qrgServiceUrl = ConfigProperties.getProperty(ConfigEnum.QRGS_SERVICE_ADDRESS);
		StringBuilder sbUrl = new StringBuilder();
		String docName = documentName;
		try {
			docName = URLEncoder.encode(documentName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}
		final DateParam dateParam = DateCalcUtil.getDateParam(date);
		sbUrl.append(qrgServiceUrl).append("/serial_number_web_service/special_get_serial_number?")
				.append("quality_document_name=").append(docName).append("&company_code=").append(companyCode)
				.append("&place_code=").append(configService.readStrConfig(ConfigEnum.PLACE_CODE)).append("&year=")
				.append(dateParam.getYear()).append("&month=").append(dateParam.getMonth()).append("&use_type=create")
				.append("&user_code=").append(userInnerCode)
				.append("&infor_system_code=").append(configService.readStrConfig(ConfigEnum.INFOR_SYSTEM_CODE));
		String serviceUrl = sbUrl.toString();
		logger.info(serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		SerialNumber snRecord = (SerialNumber) JsonUtil.json2obj(json, SerialNumber.class);
		if (snRecord == null || !StringUtils.equals(snRecord.getStateCode(), "1")) {
			throw new SerialNumberException("获取" + documentName + "流水号异常");
		}
		return snRecord;
	}

	@Override
	public boolean invalidSerialNumber(String userInnerCode, String documentName, String serialNumber, String reason) {
		final String qrgServiceUrl = ConfigProperties.getProperty(ConfigEnum.QRGS_SERVICE_ADDRESS);
		StringBuilder sbUrl = new StringBuilder();
		String docName = documentName;
		try {
			docName = URLEncoder.encode(documentName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}
		try {
			reason = URLEncoder.encode(reason, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}
		sbUrl.append(qrgServiceUrl).append("/serial_number_web_service/invalid_serial_number?")
				.append("quality_document_name=").append(docName).append("&sn=").append(serialNumber).append("&reason=")
				.append(reason).append("&user_code=").append(userInnerCode);
		String serviceUrl = sbUrl.toString();
		logger.info(serviceUrl);
		String json = HttpUtil.sendGet(serviceUrl);
		InvalidSerialNumber invalidSerialNumber = (InvalidSerialNumber) JsonUtil.json2obj(json, InvalidSerialNumber.class);
		if (invalidSerialNumber == null) {
			throw new SerialNumberException("作废" + documentName + "流水号" + serialNumber + "异常");
		} else if (StringUtils.equals(invalidSerialNumber.getStatus(), "1")
				|| StringUtils.equals(invalidSerialNumber.getStatus(), "2")) {
			return true;
		}
		return false;
	}

	@Override
	public String fetchPcbCheckOrderNum(Date date) {
		String dateString = DateTimeUtil.date2dateTimeStr(date, "yyMM");
		String monthlyKey = "check-num-" + dateString;
		Integer monthlyInt = configService.getUtilInt(monthlyKey);
		if (monthlyInt == null) {
			monthlyInt = 1;
		} else {
			monthlyInt++;
		}

		StringBuilder sbCheckNum = new StringBuilder();
		sbCheckNum.append("JYPCB").append(dateString);
		sbCheckNum.append(String.format("%04d", monthlyInt));

		configService.setUtilRecord(monthlyKey, "", monthlyInt, "");

		return sbCheckNum.toString();
	}

	@Override
	public String fetchPcbSubCheckOrderNum(Date date, String productOrderNum) {
		final List<PcbCheckOrder> inOrders = pcbCheckOrderMapper.listCheckOrderByProductOrder(productOrderNum);
		if (inOrders == null || inOrders.isEmpty()) {
			String checkNum = this.fetchPcbCheckOrderNum(date);
			return checkNum + "-1";
		}
		int maxNumSeq = 0;
		String pureOrderNum = "";
		for (PcbCheckOrder order : inOrders) {
			final String lastCheckNum = order.getCheckNum();
			if (StringUtils.isBlank(lastCheckNum)) {
				return "";
			}
			final String[] numItems = lastCheckNum.split("-");
			if (numItems == null) {
				return "";
			}
			if (numItems.length == 1) {
				pureOrderNum = numItems[0];
				continue;
			}
			int numSeq = Integer.valueOf(numItems[1]);
			if (numSeq > maxNumSeq) {
				maxNumSeq = numSeq;
				pureOrderNum = numItems[0];
			}
		}
		if (StringUtils.isBlank(pureOrderNum)) {
			return "";
		}
		return pureOrderNum + "-" + String.valueOf(maxNumSeq + 1);
	}

	@Override
	public String fetchPcbJoinSubCheckOrderNum(Date date, String joinOrderNum) {
		final List<PcbCheckOrder> inOrders = pcbCheckOrderMapper.listCheckOrderByJoinOrder(joinOrderNum);
		if (inOrders == null || inOrders.isEmpty()) {
			String checkNum = this.fetchPcbCheckOrderNum(date);
			return checkNum + "-1";
		}
		int maxNumSeq = 0;
		String pureOrderNum = "";
		for (PcbCheckOrder order : inOrders) {
			final String lastCheckNum = order.getCheckNum();
			if (StringUtils.isBlank(lastCheckNum)) {
				return "";
			}
			final String[] numItems = lastCheckNum.split("-");
			if (numItems == null || numItems.length != 2) {
				return "";
			}
			int numSeq = Integer.valueOf(numItems[1]);
			if (numSeq > maxNumSeq) {
				maxNumSeq = numSeq;
				pureOrderNum = numItems[0];
			}
		}
		if (StringUtils.isBlank(pureOrderNum)) {
			return "";
		}
		return pureOrderNum + "-" + String.valueOf(maxNumSeq + 1);
	}

	@Override
	public String fetchPcbReportNum(Date date) {
		String dateString = DateTimeUtil.date2dateTimeStr(date, "yyMM");
		String monthlyKey = "report-num-" + dateString;
		Integer monthlyInt = configService.getUtilInt(monthlyKey);
		if (monthlyInt == null) {
			monthlyInt = 1;
		} else {
			monthlyInt++;
		}

		StringBuilder sbCheckNum = new StringBuilder();
		sbCheckNum.append("BGPCB").append(dateString);
		sbCheckNum.append(String.format("%04d", monthlyInt));
		configService.setUtilRecord(monthlyKey, "", monthlyInt, "");
		return sbCheckNum.toString();
	}

	@Override
	public String fetchPcbSubReportOrderNum(Date date, String productOrderNum) {
		final List<PcbCheckReport> inOrders = pcbCheckReportMapper.listByOrderNum(productOrderNum);
		if (inOrders == null || inOrders.isEmpty()) {
			String reportNum = this.fetchPcbReportNum(date);
			return reportNum + "-1";
		}
		int maxNumSeq = 0;
		String pureOrderNum = "";
		for (PcbCheckReport order : inOrders) {
			final String lastReportNum = order.getReportNum();
			if (StringUtils.isBlank(lastReportNum)) {
				return "";
			}
			final String[] numItems = lastReportNum.split("-");
			if (numItems == null) {
				return "";
			}
			if (numItems.length == 1) {
				pureOrderNum = numItems[0];
				continue;
			}
			int numSeq = Integer.valueOf(numItems[1]);
			if (numSeq > maxNumSeq) {
				maxNumSeq = numSeq;
				pureOrderNum = numItems[0];
			}
		}
		if (StringUtils.isBlank(pureOrderNum)) {
			return "";
		}
		return pureOrderNum + "-" + String.valueOf(maxNumSeq + 1);
	}

	@Override
	public String fetchPcbJoinSubReportOrderNum(Date date, String joinOrderNum) {
		final List<PcbCheckReport> inOrders = pcbCheckReportMapper.listByJoinOrderNum(joinOrderNum);
		if (inOrders == null || inOrders.isEmpty()) {
			String reportNum = this.fetchPcbReportNum(date);
			return reportNum + "-1";
		}
		int maxNumSeq = 0;
		String pureOrderNum = "";
		for (PcbCheckReport order : inOrders) {
			final String lastReportNum = order.getReportNum();
			if (StringUtils.isBlank(lastReportNum)) {
				return "";
			}
			final String[] numItems = lastReportNum.split("-");
			if (numItems == null || numItems.length != 2) {
				return "";
			}
			int numSeq = Integer.valueOf(numItems[1]);
			if (numSeq > maxNumSeq) {
				maxNumSeq = numSeq;
				pureOrderNum = numItems[0];
			}
		}
		if (StringUtils.isBlank(pureOrderNum)) {
			return "";
		}
		return pureOrderNum + "-" + String.valueOf(maxNumSeq + 1);
	}
	// ----------------------------------------------------------------------

	@Override
	public boolean needToDoReport() {
		Date now = new Date();
		Date procStartTime = DateTimeUtil.truncateDateTime(now);
		// 凌晨3点以后开始执行
		procStartTime = DateCalcUtil.addTime(procStartTime, Calendar.HOUR, 3);
		if (now.compareTo(procStartTime) < 0) {
			return false;
		}

		RecordUtils config = recordUtilsMapper.selectByName(ConfigEnum.LAST_REPORT_TIME.getCode());
		if (config == null || config.getValueTime() == null) {
			return true;
		}
		int compare = config.getValueTime().compareTo(procStartTime);
		if (compare >= 0) {
			return false;
		}

		return true;
	}

	@Override
	public boolean recordDoReportTime() {
		RecordUtils record = new RecordUtils();
		record.setValueTime(new Date());
		record.setRecordName(ConfigEnum.LAST_REPORT_TIME.getCode());

		int updated = recordUtilsMapper.updateByName(record);
		if (updated == 0) {
			int added = recordUtilsMapper.insertSelective(record);
			if (added < 1) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean doDetailDailyReport(Date now) {
		Date procDate = DateTimeUtil.truncateDateTime(now);
		procDate = DateCalcUtil.subTime(procDate, Calendar.DAY_OF_YEAR, 1);
		this.executeDetailDailyReport(procDate);
		return true;
	}

	@Override
	public boolean executeDetailDailyReport(Date procDate) {
		logger.info("开始执行生成日报...");
		// callMapper.doDetailDailyTask(procDate);
		return true;
	}

	@Override
	public boolean doDetailWeeklyReport(Date now) {
		Date procDate = DateTimeUtil.truncateDateTime(now);
		procDate = DateCalcUtil.subTime(procDate, Calendar.DAY_OF_YEAR, 1);

		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 1) { // 周一
			this.executeDetailWeeklyReport(procDate);
		}
		return true;
	}

	@Override
	public boolean executeDetailWeeklyReport(Date procDate) {
		logger.info("开始执行生成周报...");
		// callMapper.doDetailWeeklyTask(procDate);
		return true;
	}

	@Override
	public void doSystemLog(HttpServletRequest request, String userName, String description, Integer funcType) {
		Browser browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser();
		Version version = browser.getVersion(request.getHeader("User-Agent"));
		String browserInfo = browser.getName() + "/" + version.getVersion();
		String hostIp = getHostIp();
		String userIp = getLocalIp(request);
		Date today = new Date();
		SystemLog systemLog = new SystemLog();
		systemLog.setCreatTime(today);
		systemLog.setDescription(description);
		systemLog.setFuncType(funcType);
		systemLog.setServiceIp(hostIp);
		systemLog.setUpdateTime(today);
		systemLog.setUserBrowserVer(browserInfo);
		systemLog.setUserIp(userIp);
		systemLog.setUserName(userName);
		systemLogMapper.insertSelective(systemLog);
	}

	public static String getHostIp() {
		String SERVER_IP = null;
		/* int i = 0; */
		try {
			@SuppressWarnings("rawtypes")
			Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			loop: while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
				Enumeration<InetAddress> inetAddresses = ni.getInetAddresses();
				// System.out.println("进入外层while....");
				while (inetAddresses.hasMoreElements()) {
					ip = inetAddresses.nextElement();
					/*
					 * i++; System.out.println("第" + i + "个ip:" + ip); System.out.println("第" + i +
					 * "个SERVER_IP:" + ip.getHostAddress()); System.out.println("第" + i +
					 * "个ip.isSiteLocalAddress():" + ip.isSiteLocalAddress());
					 * System.out.println("第" + i + "个ip.isLoopbackAddress():" +
					 * ip.isLoopbackAddress());
					 */
					if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
						SERVER_IP = ip.getHostAddress();
						break loop;
					} else {
						ip = null;
					}
				}
			}
		} catch (SocketException e) {
			logger.error(e.getMessage(), e);
		}
		return SERVER_IP;
	}

	/**
	 * 从Request对象中获得客户端IP，处理了HTTP代理服务器和Nginx的反向代理截取了ip
	 * 
	 * @param request
	 * @return ip
	 */
	public static String getLocalIp(HttpServletRequest request) {
		String remoteAddr = request.getRemoteAddr();
		String forwarded = request.getHeader("X-Forwarded-For");
		String realIp = request.getHeader("X-Real-IP");

		String ip = null;
		if (realIp == null) {
			if (forwarded == null) {
				ip = remoteAddr;
			} else {
				ip = remoteAddr + "/" + forwarded.split(",")[0];
			}
		} else {
			if (realIp.equals(forwarded)) {
				ip = realIp;
			} else {
				if (forwarded != null) {
					forwarded = forwarded.split(",")[0];
				}
				ip = realIp + "/" + forwarded;
			}
		}
		return ip;
	}

	@Override
	public void dobehaviour(HttpConnectionManager cm) {
		Date now = new Date();
		configService.setUtilRecord(ConfigEnum.LAST_BEHAVIOUR_TIME, now, "");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<SystemLog> systemLogs = systemLogMapper.selectByIsrecord();
		if (systemLogs == null || systemLogs.size() == 0) {
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("=====日志发送给用户行为记录系统开始=====" + new Date());

		map.put("userName", "testName");
		map.put("sysType", ConfigProperties.getSysTem()); // "TBOS1511170006"代表是绩效考核系统
		map.put("funcTime", sdf.format(new Date()));
		map.put("funcName", "functionName");
		map.put("funcType", "funcType");
		map.put("userBrowserVer", "userBrowserVer");
		map.put("userIP", "127.0.0.1");
		map.put("serverIP", "127.0.0.1");
		map.put("uawTime", String.valueOf(System.currentTimeMillis())); // 时间戳
		// 测试参数
		map.put("type", "clientTest");
		String result = LogHistory.upload(cm.getHttpClient(), map, ConfigProperties.getBehaviourRecordIp());
		JSONObject resultJson = (JSONObject) JSONObject.parse(result);
		if (resultJson == null || resultJson.isEmpty() || !resultJson.getBooleanValue("status")
				|| resultJson.getIntValue("content") != 0) {
			System.out.println("连接服务器失败！");
			return;
		}
		for (SystemLog systemLog : systemLogs) {
			map.put("userName", systemLog.getUserName() == null ? "" : systemLog.getUserName());
			map.put("sysType", ConfigProperties.getSysTem()); // "TBOS1511170006"代表是绩效考核系统
			map.put("funcTime", systemLog.getCreatTime() == null ? "" : sdf.format(systemLog.getCreatTime()));
			map.put("funcName", systemLog.getDescription());
			map.put("funcType", systemLog.getFuncType() == null ? "" : systemLog.getFuncType().toString());
			map.put("userBrowserVer", systemLog.getUserBrowserVer());
			map.put("userIP", systemLog.getUserIp());
			map.put("serverIP", systemLog.getServiceIp());
			map.put("uawTime", String.valueOf(System.currentTimeMillis() + systemLog.getId())); // 时间戳
			// 传输参数
			map.put("type", "client");
			result = LogHistory.upload(cm.getHttpClient(), map, ConfigProperties.getBehaviourRecordIp());
			resultJson = (JSONObject) JSONObject.parse(result);
			if (resultJson.getBooleanValue("status")) {
				systemLog.setIsRecord(1);
				systemLogMapper.updateByPrimaryKeySelective(systemLog);
			} else {
				System.out.println(resultJson.getString("content") + "错误");
			}
		}
	}

	@Override
	public void clickHelpAction() {
		logger.info("clickHelpAction...");
	}

	@Override
	public String getHelpUrl() {
		return ConfigProperties.getProperty(ConfigEnum.ONLINE_HELP_URL, "");
	}

	@Override
	public List<AkRoleAuth> getRoleAuths() {

		Set<String> defautAuth = new HashSet<>();
		defautAuth.add("/order/**");
		defautAuth.add("/html/**.html");
		defautAuth.add("/**.html");
		// defautAuth.add("");
		List<AkRoleAuth> roleAuthList = new ArrayList<AkRoleAuth>();

		List<UserRole> userRoles = userRoleMapper.getRoles();
		List<RoleAuth> roleAuths = roleAuthMapper.load();

		for (UserRole userRole : userRoles) {
			String roleName = userRole.getRoleName();
			List<Integer> authIds = new ArrayList<Integer>();
			for (RoleAuth roleAuth : roleAuths) {
				if (userRole.getId() == roleAuth.getRoleId()) {
					authIds.add(roleAuth.getAuthId());
				}
			}
			Set<String> urls = new HashSet<String>();
			for (Integer authId : authIds) {
				List<UserAuth> userAuths = userAuthMapper.selectByAuthType(authId);
				if (userAuths == null || userAuths.size() == 0) {
					continue;
				}
				for (UserAuth userAuth : userAuths) {
					urls.add(userAuth.getUrl());
				}
			}
			urls.addAll(defautAuth);
			AkRoleAuth roleAuthInfo = new AkRoleAuth();
			roleAuthInfo.setRoleName(roleName);
			roleAuthInfo.setUrls(new ArrayList<>(urls));
			roleAuthList.add(roleAuthInfo);
		}

		return roleAuthList;
	}

	@Override
	public AkAuthUser getUserByName(String username) {
		WxtbUser wxtbUser = null;
		try {
			wxtbUser = wxtbUserMapper.selectByAccount(username);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		if (wxtbUser == null) {
			return null;
		}
		AkAuthUser user = new AkAuthUser();
		user.setAccountNonExpired(wxtbUser.getAccountNonExpired());
		user.setAccountNonLocked(wxtbUser.getAccountNonLocked());
		user.setCredentialsNonExpired(wxtbUser.getCredentialsNonExpired());
		user.setEnable(wxtbUser.getEnable());
		user.setHashedPassword(wxtbUser.getHashedPassword());
		user.setUserCode(wxtbUser.getUserCode());
		user.setUsername(wxtbUser.getUsername());
		user.setInnerCode(wxtbUser.getInnerCode());
		user.setUserRoles(this.getUserRoles(wxtbUser.getUserCode()));

		return user;
	}

	private List<String> getUserRoles(String userCode) {
		final List<UserRole> userRoles = cacheService.getUserRole(userCode);
		List<String> roleNames = null;
		try {
			roleNames = DataUtil.fetchField2list(userRoles, UserRole.class, "roleName");
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		return roleNames;
	}

	@Override
	public boolean isDebugWithoutLogin() {
		// TODO Auto-generated method stub
		Integer debug = ConfigProperties.getPropertyInt(ConfigEnum.DEBUG_WITHOUT_LOGIN);
		return DataUtil.equals(debug, 1);
	}

	@Override
	public Integer editUser(AkAuthUser loginUser, String account, AkUserRole userRoleNew) {
		UserRoleEnable userRoleEnable = new UserRoleEnable();
		userRoleEnable.setEnable(userRoleNew.getEnable().equals("启用") ? 1 : 0);
		List<Integer> userRoleIds = userRoleNew.getUserRoleId();
		if (userRoleIds != null && userRoleIds.size() != 0) {
			List<UserRoleItem> userRoleItems = new ArrayList<UserRoleItem>();
			Map<Integer, UserRole> map = cacheService.getUserRoles();
			for (Integer id : map.keySet()) {
				boolean has = false;
				for (Integer idnew : userRoleIds) {
					if (id == idnew) {
						UserRoleItem userRoleItem = new UserRoleItem();
						userRoleItem.setEnable(1);
						userRoleItem.setUserRoleName(map.get(id).getRoleName());
						userRoleItems.add(userRoleItem);
						has = true;
						break;
					}
				}
				if (!has) {
					UserRoleItem userRoleItem = new UserRoleItem();
					userRoleItem.setEnable(0);
					userRoleItem.setUserRoleName(map.get(id).getRoleName());
					userRoleItems.add(userRoleItem);
				}
			}
			userRoleEnable.setRoleEnable(userRoleItems);
		}
		WxtbUser wxtbUser = wxtbUserMapper.selectByAccount(account);
		if (wxtbUser == null) {
			return 0;
		}
		Integer userEnable = userRoleEnable.getEnable();
		if (userEnable != null) {
			wxtbUser.setEnable(userEnable);
			wxtbUserMapper.updateByPrimaryKeySelective(wxtbUser);
			reloadWxtbUser();
		}
		List<UserRoleItem> userRole = userRoleEnable.getRoleEnable();
		if (userRole == null || userRole.size() == 0) {
			return 1;
		}
		Date today = new Date();
		List<UserRole> userRolesAll = userRoleMapper.load();
		Map<Integer, Integer> idEnable = new HashMap<Integer, Integer>();
		for (UserRole userRoleAll : userRolesAll) {
			for (UserRoleItem userRoleLit : userRole) {
				if (userRoleLit.getUserRoleName().equals(userRoleAll.getRoleName())) {
					idEnable.put(userRoleAll.getId(), userRoleLit.getEnable());
					break;
				}
			}
		}
		String userCode = wxtbUser.getUserCode();
		List<UserRoleRelation> userRoleRelations = userRoleRelationMapper.selectByUserCode(userCode);
		for (Integer roleId : idEnable.keySet()) {
			boolean isHas = false;
			for (UserRoleRelation userRoleRelation : userRoleRelations) {
				if (userRoleRelation.getRoleId() == roleId) {
					userRoleRelation.setEnable(idEnable.get(roleId));
					userRoleRelationMapper.updateByPrimaryKeySelective(userRoleRelation);
					isHas = true;
				}
			}
			if (!isHas && idEnable.get(roleId) == 1) {
				UserRoleRelation userRoleRelationNew = new UserRoleRelation();
				userRoleRelationNew.setCreateTime(today);
				userRoleRelationNew.setEnable(1);
				userRoleRelationNew.setRoleId(roleId);
				userRoleRelationNew.setUserCode(userCode);
				userRoleRelationNew.setUpdateTime(today);
				userRoleRelationMapper.insertSelective(userRoleRelationNew);
			}
		}
		this.reloadUserRelation();
		return 1;
	}

	public void reloadUserRelation() {
		List<UserRoleRelation> userRoleRelationsNew = userRoleRelationMapper.load();
		cacheService.reloadUserRoleRelation(userRoleRelationsNew);
	}

	@Override
	public Map<String, Object> getRoleList(AkAuthUser loginUser, Integer page, Integer rows, String keyWord) {
		Map<String, Object> response = new HashMap<String, Object>();
		Integer enable = null;
		if (keyWord != null) {
			if ("启用".contains(keyWord)) {
				enable = 1;
			} else if ("禁用".contains(keyWord)) {
				enable = 0;
			}
		}
		Map<String, WxtbUser> wxtbUserMap = cacheService.getWxtbUser();
		List<String> userCodes = new ArrayList<String>();
		if (keyWord != null) {
			for (String userCode : wxtbUserMap.keySet()) {
				WxtbUser wxtbUser = wxtbUserMap.get(userCode);
				if (wxtbUser.getUsername().contains(keyWord)) {
					userCodes.add(userCode);
				}
			}
		}
		if (userCodes.size() == 0) {
			userCodes = null;
		}
		List<Integer> authIdsSer = new ArrayList<Integer>();
		if (keyWord != null) {
			Map<Integer, UserAuth> userAuthMap = cacheService.getUserAuths();
			for (Integer authId : userAuthMap.keySet()) {
				String authNameSer = userAuthMap.get(authId).getAuthName();
				if (authNameSer.contains(keyWord)) {
					authIdsSer.add(authId);
				}
			}
		}
		if (authIdsSer.isEmpty()) {
			authIdsSer = null;
		}
		List<RoleAuth> roleAuths = roleAuthMapper.selectByAuthIds(authIdsSer);
		List<Integer> roleIds = null;
		if (roleAuths != null && !roleAuths.isEmpty()) {
			roleIds = new ArrayList<Integer>();
			for (RoleAuth roleAuth : roleAuths) {
				roleIds.add(roleAuth.getRoleId());
			}
		}
		Integer start = (page - 1) * rows;
		Integer total = userRoleMapper.getSelectByKeyWordNum(keyWord, userCodes, "游客", roleIds, enable);
		response.put("total", total);
		List<UserRole> userRoles = userRoleMapper.selectByKeyWord(keyWord, "游客", userCodes, roleIds, enable, start,
				rows);
		if (userRoles == null || userRoles.size() == 0) {
			return null;
		}
		List<RoleInfo> roleInfos = new ArrayList<RoleInfo>();
		Map<Integer, RoleAuth> roleAuthMap = cacheService.getRoleAuth();
		for (UserRole userRole : userRoles) {
			RoleInfo roleInfo = new RoleInfo();
			roleInfo.setRoleName(userRole.getRoleName());
			String creator = userRole.getCreatorCode();
			if (creator != null) {
				WxtbUser wxtbUser = cacheService.getWxtbUser(creator);
				if (wxtbUser != null) {
					roleInfo.setCreatorName(wxtbUser.getUsername());
				}
			}
			String updateor = userRole.getUpdateorCode();
			if (updateor != null) {
				WxtbUser wxtbUser = cacheService.getWxtbUser(updateor);
				if (wxtbUser != null) {
					roleInfo.setUpdateorName(wxtbUser.getUsername());
				}
			}
			roleInfo.setCreatTime(DateTimeUtil.date2dateStr(userRole.getCreateTime()));
			roleInfo.setUpdateTime(DateTimeUtil.date2dateStr(userRole.getUpdateTime()));
			roleInfo.setEnable(int2enable(userRole.getEnable()));
			Integer roleId = userRole.getId();
			List<Integer> authIds = new ArrayList<Integer>();
			for (Integer integer : roleAuthMap.keySet()) {
				RoleAuth roleAuth = roleAuthMap.get(integer);
				if (roleId == roleAuth.getRoleId()) {
					authIds.add(roleAuth.getAuthId());
				}
			}
			List<String> authNames = new ArrayList<String>();
			for (Integer id : authIds) {
				UserAuth userAuth = cacheService.getUserAuth(id);
				if (userAuth != null) {
					authNames.add(userAuth.getAuthName());
				}
			}
			String authName = "";
			for (int i = 0; i < authNames.size(); i++) {
				if (i == 0) {
					authName = authName + authNames.get(i);
				} else {
					authName = authName + "," + authNames.get(i);
				}
			}
			roleInfo.setRoleAuth(authName);
			roleInfos.add(roleInfo);

		}
		List<UserAuth> userAuths = userAuthMapper.load();
		Set<String> userAuthNames = new HashSet<String>();
		if (userAuths != null && userAuths.size() != 0) {
			for (UserAuth userAuth : userAuths) {
				userAuthNames.add(userAuth.getAuthName());
			}
		}
		response.put("rows", roleInfos);
		response.put("userAuthNames", userAuthNames);
		return response;
	}

	public String int2enable(Integer enable) {
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

	@Override
	public Map<String, Object> getUserList(AkAuthUser loginUser, Integer page, Integer rows, String keyWord) {
		Integer enable = null;
		if (keyWord != null) {
			if ("启用".contains(keyWord)) {
				enable = 1;
			} else if ("禁用".contains(keyWord)) {
				enable = 0;
			}
		}
		Map<String, Object> reponse = new HashMap<String, Object>();
		List<WxtbDepartment> wxtbDepartments = wxtbDepartmentMapper.selectByKeyWord(keyWord);
		List<String> departmentCodes = null;
		if (wxtbDepartments != null && wxtbDepartments.size() != 0) {
			departmentCodes = new ArrayList<String>();
			for (WxtbDepartment wxtbDepartment : wxtbDepartments) {
				departmentCodes.add(wxtbDepartment.getDepartmentCode());
			}
		}
		Map<Integer, UserRole> userRoleMap = cacheService.getUserRoles();
		List<String> userRoleNames = new ArrayList<String>();
		for (Integer id : userRoleMap.keySet()) {
			String userRoleName = userRoleMap.get(id).getRoleName();
			if (!userRoleName.equals("游客")) {
				userRoleNames.add(userRoleName);
			}
		}
		List<Integer> ids = new ArrayList<Integer>();
		List<String> userCodes = null;
		if (keyWord != null) {
			for (Integer id : userRoleMap.keySet()) {
				String userRoleName = userRoleMap.get(id).getRoleName();
				if (userRoleName.contains(keyWord)) {
					ids.add(id);
				}
			}
			if (ids.size() == 0) {
				ids = null;
			}
			List<UserRoleRelation> userRoleRelations = userRoleRelationMapper.selectByIds(ids);
			if (userRoleRelations != null && userRoleRelations.size() != 0) {
				userCodes = new ArrayList<String>();
				for (UserRoleRelation userRoleRelation : userRoleRelations) {
					userCodes.add(userRoleRelation.getUserCode());
				}
			}
		}
		if (userCodes == null || userCodes.size() == 0) {
			userCodes = null;
		}
		Integer start = (page - 1) * rows;
		List<WxtbUser> wxtbUsers = wxtbUserMapper.selectByKey(keyWord, departmentCodes, userCodes, enable, start, rows);

		Integer total = wxtbUserMapper.getSelectByKeyNum(keyWord, departmentCodes, userCodes, enable);
		if (wxtbUsers == null || wxtbUsers.size() == 0) {
			reponse.put("total", 0);
			reponse.put("rows", null);
			return reponse;
		}
		List<UserManageInfo> userManageInfos = new ArrayList<UserManageInfo>();
		for (WxtbUser wxtbUser : wxtbUsers) {
			String userCode = wxtbUser.getUserCode();
			UserManageInfo userManageInfo = new UserManageInfo();
			String departmentCode = wxtbUser.getDepartmentCode();
			if (departmentCode != null) {
				WxtbDepartment wxtbDepartment = cacheService.getDepartment(departmentCode);
				if (wxtbDepartment != null) {
					userManageInfo.setDepartment(wxtbDepartment.getDepartmentName());
				}
			}
			userManageInfo.setUserName(wxtbUser.getUsername());
			userManageInfo.setAccount(wxtbUser.getAccount());
			userManageInfo.setEnable(PcbmisUtil.int2enable(wxtbUser.getEnable()));

			if (userCode == null) {
				userManageInfos.add(userManageInfo);
				continue;
			}
			List<UserRole> userRoles = cacheService.getUserRole(userCode);
			if (userRoles == null || userRoles.size() == 0) {
				userManageInfos.add(userManageInfo);
				continue;
			}
			String userRoleStr = "";
			for (int i = 0; i < userRoles.size(); i++) {
				if (i == 0) {
					userRoleStr = userRoleStr + userRoles.get(i).getRoleName();
				} else {
					userRoleStr = userRoleStr + "," + userRoles.get(i).getRoleName();
				}
			}
			userManageInfo.setRole(userRoleStr);
			userManageInfos.add(userManageInfo);
		}
		reponse.put("total", total);
		reponse.put("rows", userManageInfos);
		reponse.put("userRoleNames", userRoleNames);
		return reponse;
	}

	@Override
	public Integer editRole(AkAuthUser loginUser, String roleName, AkUserAuth userAuthNew) {
		UserAuthEnable userAuthEnable = new UserAuthEnable();
		userAuthEnable.setRoleName(userAuthNew.getRoleName());
		userAuthEnable.setRoleEnable(userAuthNew.getRoleEnable().equals("启用") ? 1 : 0);
		List<Integer> userAuthids = userAuthNew.getUserAuthId();
		if (userAuthids != null && userAuthids.size() != 0) {
			List<UserAuthItem> userAuthItems = new ArrayList<UserAuthItem>();
			Map<Integer, UserAuth> map = cacheService.getUserAuths();
			for (Integer typeId : map.keySet()) {
				boolean has = false;
				for (Integer idNew : userAuthids) {
					if (typeId == idNew) {
						UserAuthItem userAuthItem = new UserAuthItem();
						userAuthItem.setEnable(1);
						userAuthItem.setUserAuthName(map.get(typeId).getAuthName());
						userAuthItems.add(userAuthItem);
						has = true;
						break;
					}
				}
				if (!has) {
					UserAuthItem userAuthItem = new UserAuthItem();
					userAuthItem.setEnable(0);
					userAuthItem.setUserAuthName(map.get(typeId).getAuthName());
					userAuthItems.add(userAuthItem);
				}
			}
			userAuthEnable.setAuthEnable(userAuthItems);
		}
		Map<Integer, UserRole> userRoleMap = cacheService.getUserRoles();
		if (userRoleMap == null || userRoleMap.size() == 0) {
			return 0;
		}
		String roleNameNew = userAuthEnable.getRoleName();
		UserRole userRole = null;
		Integer isDouble = 0;
		for (Integer id : userRoleMap.keySet()) {
			if (userRoleMap.get(id).getRoleName().equals(roleName)) {
				userRole = userRoleMap.get(id);
				continue;
			}
			if (userRoleMap.get(id).getRoleName().equals(roleNameNew)) {
				isDouble++;
			}
		}
		if (isDouble == 1) {
			return 2;
		}
		if (userRole == null) {
			return 0;
		}
		Date today = new Date();
		Integer userRoleEnable = userAuthEnable.getRoleEnable();

		if (userRoleEnable != null && roleNameNew != null) {
			userRole.setEnable(userRoleEnable);
			userRole.setRoleName(roleNameNew);
		}
		userRole.setUpdateorCode(loginUser.getUserCode());
		userRole.setUpdateTime(today);
		userRoleMapper.updateByPrimaryKeySelective(userRole);
		reloadUserRole();
		List<UserAuthItem> userAuthItems = userAuthEnable.getAuthEnable();
		if (userAuthItems == null || userAuthItems.size() == 0) {
			return 1;
		}
		List<UserAuth> userAuths = userAuthMapper.load();
		Map<Integer, Integer> idEnable = new HashMap<Integer, Integer>();
		for (UserAuthItem userAuthItem : userAuthItems) {
			for (UserAuth userAuth : userAuths) {
				if (userAuth.getAuthName().equals(userAuthItem.getUserAuthName())) {
					idEnable.put(userAuth.getAuthType(), userAuthItem.getEnable());
				}
			}
		}
		List<RoleAuth> roleAuths = roleAuthMapper.selectByRoleId(userRole.getId());
		for (Integer authType : idEnable.keySet()) {
			boolean isHas = false;
			for (RoleAuth roleAuth : roleAuths) {
				if (roleAuth.getAuthId() == authType) {
					roleAuth.setEnable(idEnable.get(authType));
					roleAuthMapper.updateByPrimaryKeySelective(roleAuth);
					isHas = true;
				}
			}
			if (!isHas && idEnable.get(authType) == 1) {
				RoleAuth roleAuth = new RoleAuth();
				roleAuth.setAuthId(authType);
				roleAuth.setCreateTime(today);
				roleAuth.setEnable(1);
				roleAuth.setRoleId(userRole.getId());
				roleAuth.setUpdateTime(today);
				roleAuthMapper.insertSelective(roleAuth);
			}
		}
		reloadRoleAuth();
		return 1;
	}

	@Override
	public Integer addRole(AkAuthUser loginUser, AkUserAuth userAuthNew) {
		UserAuthEnable userAuthEnable = new UserAuthEnable();
		userAuthEnable.setRoleName(userAuthNew.getRoleName());
		String roleEnable = userAuthNew.getRoleEnable();
		if (roleEnable != null && roleEnable.equals("启用")) {
			userAuthEnable.setRoleEnable(1);
		} else {
			userAuthEnable.setRoleEnable(0);
		}
		List<Integer> userAuthids = userAuthNew.getUserAuthId();
		if (userAuthids != null && userAuthids.size() != 0) {
			List<UserAuthItem> userAuthItems = new ArrayList<UserAuthItem>();
			Map<Integer, UserAuth> map = cacheService.getUserAuths();
			for (Integer typeId : map.keySet()) {
				boolean has = false;
				for (Integer idNew : userAuthids) {
					if (typeId == idNew) {
						UserAuthItem userAuthItem = new UserAuthItem();
						userAuthItem.setEnable(1);
						userAuthItem.setUserAuthName(map.get(typeId).getAuthName());
						userAuthItems.add(userAuthItem);
						has = true;
						break;
					}
				}
				if (!has) {
					UserAuthItem userAuthItem = new UserAuthItem();
					userAuthItem.setEnable(0);
					userAuthItem.setUserAuthName(map.get(typeId).getAuthName());
					userAuthItems.add(userAuthItem);
				}
			}
			userAuthEnable.setAuthEnable(userAuthItems);
		}
		String roleName = userAuthEnable.getRoleName();
		Map<Integer, UserRole> userRoles = cacheService.getUserRoles();
		if (userRoles == null || userRoles.size() == 0) {
			return 0;
		}
		Integer maxId = 1;
		UserRole userRole = new UserRole();
		for (Integer id : userRoles.keySet()) {
			if (id >= maxId) {
				maxId = id;
			}
			if (roleName.equals(userRoles.get(id).getRoleName())) {
				return 2;
			}
		}
		maxId++;
		Date today = new Date();
		userRole.setId(maxId);
		userRole.setCreateTime(today);
		userRole.setEnable(userAuthEnable.getRoleEnable());
		userRole.setRoleName(roleName);
		userRole.setCreatorCode(loginUser.getUserCode());
		if (loginUser != null) {
			userRole.setCreatorCode(loginUser.getUserCode());
		}
		userRoleMapper.insertSelective(userRole);
		reloadUserRole();
		List<UserAuthItem> userAuthItems = userAuthEnable.getAuthEnable();
		List<Integer> authType = new ArrayList<Integer>();
		for (UserAuthItem userAuthItem : userAuthItems) {
			if (userAuthItem.getEnable() == 0) {
				continue;
			}
			List<UserAuth> userAuths = userAuthMapper.selectByAuthName(userAuthItem.getUserAuthName());
			if (userAuths != null && userAuths.size() != 0) {
				authType.add(userAuths.get(0).getAuthType());
			}
		}
		for (Integer typeId : authType) {
			RoleAuth roleAuth = new RoleAuth();
			roleAuth.setAuthId(typeId);
			roleAuth.setRoleId(maxId);
			roleAuth.setCreateTime(today);
			roleAuth.setUpdateTime(today);
			roleAuth.setEnable(1);
			roleAuthMapper.insertSelective(roleAuth);
		}
		reloadRoleAuth();
		return 1;
	}

	@Override
	public Integer deleteRole(AkAuthUser loginUser, String roleName) {
		Map<Integer, UserRole> userRoleMap = cacheService.getUserRoles();
		if (userRoleMap == null || userRoleMap.size() == 0) {
			return 0;
		}
		Integer roleId = null;
		for (Integer id : userRoleMap.keySet()) {
			if (roleName.equals(userRoleMap.get(id).getRoleName())) {
				roleId = id;
			}
		}
		if (roleId == null) {
			return 0;
		}
		userRoleMapper.deleteByPrimaryKey(roleId);
		reloadUserRole();
		roleAuthMapper.deleteByRoleId(roleId);
		reloadRoleAuth();
		return 1;
	}

	@Override
	public JSONArray getRoles(AkAuthUser loginUser) {
		JSONArray jsonArray = new JSONArray();
		Map<Integer, UserRole> userMap = cacheService.getUserRoles();
		if (userMap == null || userMap.size() == 0) {
			return jsonArray;
		}
		for (Integer id : userMap.keySet()) {
			UserRole userRole = userMap.get(id);
			if (userRole.getRoleName().equals("游客") || userRole.getEnable() == 0) {
				continue;
			}
			JSONObject json = new JSONObject();
			json.put("p_id", id);
			json.put("p_text", userMap.get(id).getRoleName());
			jsonArray.add(json);
		}
		return jsonArray;
	}

	@Override
	public JSONArray getAuths(AkAuthUser loginUser) {
		JSONArray jsonArray = new JSONArray();
		Map<Integer, UserAuth> userauthMap = cacheService.getUserAuths();
		if (userauthMap == null || userauthMap.size() == 0) {
			return null;
		}
		for (Integer authType : userauthMap.keySet()) {
			JSONObject json = new JSONObject();
			json.put("p_id", authType);
			json.put("p_text", userauthMap.get(authType).getAuthName());
			jsonArray.add(json);
		}
		return jsonArray;
	}

	@Override
	public void doOperateLog(String operateName, String operateContent, String operateResult, String userName) {
		Date now = new Date();
		OperateLog operateLog = new OperateLog();
		operateLog.setCreatTime(now);
		operateLog.setOperateName(operateName);
		operateLog.setOperateContent(operateContent);
		operateLog.setUpdateTime(now);
		operateLog.setOperateResult(operateResult);
		operateLog.setUsername(userName);
		operateLogMapper.insert(operateLog);
	}

	@Override
	public Set<Integer> getUserAuths(String account) {
		List<UserRole> userRoles = cacheService.getUserRole(account);
		if (userRoles == null || userRoles.size() == 0) {
			return null;
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
		logger.info(allAuthId.size());
		return allAuthId;
	}

}
