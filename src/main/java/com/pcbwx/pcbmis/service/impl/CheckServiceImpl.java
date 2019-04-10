package com.pcbwx.pcbmis.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.pcbmis.bean.CheckDetail;
import com.pcbwx.pcbmis.bean.CheckItemRequest;
import com.pcbwx.pcbmis.bean.OrderDetail;
import com.pcbwx.pcbmis.bean.SerialNumber;
import com.pcbwx.pcbmis.bean.SizeAndWarpDegree;
import com.pcbwx.pcbmis.bean.request.PcbCheckNoteBean;
import com.pcbwx.pcbmis.component.CacheService;
import com.pcbwx.pcbmis.dao.PcbCheckDetailMapper;
import com.pcbwx.pcbmis.dao.PcbCheckOrderMapper;
import com.pcbwx.pcbmis.dao.PcbCheckReportMapper;
import com.pcbwx.pcbmis.dao.ProductOrderMapper;
import com.pcbwx.pcbmis.dao.SizeAndWarpingDegreeMapper;
import com.pcbwx.pcbmis.enums.CheckStateEnum;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.enums.ReportStateEnum;
import com.pcbwx.pcbmis.model.CompanyInfo;
import com.pcbwx.pcbmis.model.PcbCheckDetail;
import com.pcbwx.pcbmis.model.PcbCheckOrder;
import com.pcbwx.pcbmis.model.PcbCheckReport;
import com.pcbwx.pcbmis.model.ProductOrder;
import com.pcbwx.pcbmis.model.SizeAndWarpingDegree;
import com.pcbwx.pcbmis.service.CheckService;
import com.pcbwx.pcbmis.service.OrderService;
import com.pcbwx.pcbmis.service.SupportService;
import com.pcbwx.pcbmis.utils.DataUtil;
import com.pcbwx.pcbmis.utils.PcbmisUtil;
import com.pcbwx.pcbmis.utils.StringUtil;

@Service("检验单服务")
public class CheckServiceImpl implements CheckService {

	private static Logger logger = Logger.getLogger(CheckServiceImpl.class);

	@Autowired
	private PcbCheckOrderMapper pcbCheckOrderMapper;
	@Autowired
	private SizeAndWarpingDegreeMapper sizeAndWarpingDegreeMapper;
	@Autowired
	private ProductOrderMapper productOrderMapper;
	@Autowired
	private PcbCheckDetailMapper pcbCheckDetailMapper;
	@Autowired
	private PcbCheckReportMapper pcbCheckReportMapper;

	@Autowired
	private OrderService orderService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private SupportService supportService;

	@Override
	public Map<String, Object> modifySize(String checkNum, List<SizeAndWarpDegree> sizeAndWarpDegrees) {
		Map<String, Object> response = new HashMap<>();
		PcbCheckOrder pcbCheckOrder = pcbCheckOrderMapper.selectByCheckNum(checkNum);
		if (pcbCheckOrder == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", ErrorCodeEnum.SYSTEM_ERROR.getDescr());
			return response;
		}
		Date date = new Date();
		String orderNum = pcbCheckOrder.getProductOrderNum();
		// 删除原来的尺寸翘曲度数据
		List<SizeAndWarpingDegree> sizeAndWarpingDegrees = sizeAndWarpingDegreeMapper.selectByCheckNum(checkNum);
		if (sizeAndWarpingDegrees != null && sizeAndWarpingDegrees.size() != 0) {
			for (SizeAndWarpingDegree sizeAndWarpingDegree : sizeAndWarpingDegrees) {
				sizeAndWarpingDegreeMapper.deleteByPrimaryKey(sizeAndWarpingDegree.getId());
			}
		}
		Integer spotNum = null;
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
				sizeAndWarpingDegree.setLayHeight(layHeight);
				sizeAndWarpingDegree.setWarpHeight(warpHeight);
				sizeAndWarpingDegree.setWarpingDegree(sizeAndWarpDegree.getWarpingDegree());
				sizeAndWarpingDegree.setJudge(judge);
				sizeAndWarpingDegree.setCreateTime(date);
				sizeAndWarpingDegreeMapper.insertSelective(sizeAndWarpingDegree);
			}
		}
		pcbCheckOrder.setSpotCheckNumPcs(spotNum);
		pcbCheckOrder.setUpdateTime(null);
		pcbCheckOrderMapper.updateByPrimaryKeySelective(pcbCheckOrder);
		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		response.put("msg", ErrorCodeEnum.SUCCESS.getDescr());
		return response;
	}

	@Override
	public ErrorCodeEnum postDetail(AkAuthUser wxtbUser, String checkNum, String orderNum, CheckDetail checkDetail,
			Integer templateId) {
		Map<String, CheckItemRequest> map = new HashMap<String, CheckItemRequest>();
		PcbCheckOrder pcbCheckOrder = pcbCheckOrderMapper.selectByCheckNum(checkNum);
		if (pcbCheckOrder == null) {
			return ErrorCodeEnum.SYSTEM_ERROR;
		}
		orderNum = pcbCheckOrder.getProductOrderNum();
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
		if (pcbCheckOrder.getSerialNumber() == null) {
			// No：
			String serialNum = null;
			// 版次：
			String banci = null;
			// 编号：
			String bianhao = null;
			SerialNumber serialNumber = supportService.getQrgsSerialNumber(wxtbUser.getInnerCode(), "印制板检验记录表",
					new Date(), companyCode);
			if (serialNumber == null || StringUtil.isEmpty(serialNumber.getDocumentNumber())
					|| StringUtil.isEmpty(serialNumber.getRevision()) || StringUtil.isEmpty(serialNumber.getSn())) {
				return ErrorCodeEnum.NO_SERIAL_NUMBER;
			}
			serialNum = serialNumber.getSn();
			banci = serialNumber.getRevision();
			bianhao = serialNumber.getDocumentNumber();
			pcbCheckOrder.setSerialNumber(serialNum);
			pcbCheckOrder.setRevision(banci);
			pcbCheckOrder.setDocumentNumber(bianhao);
		}
		if (pcbCheckOrder.getCheckStateId() != CheckStateEnum.CHECKING.getCode()) {
			return ErrorCodeEnum.REPETITION;
		}
		OrderDetail detail = orderService.selectOrderDetail(checkNum, templateId);
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
		if (pcbCheckDetails != null && pcbCheckDetails.size() != 0) {
			for (String key : map.keySet()) {
				CheckItemRequest checkItem = map.get(key);
				PcbCheckDetail pcbCheckDetail = optionMap.get(key);
				if (pcbCheckDetail == null || checkItem == null) {
					continue;
				}
				Integer badNum = checkItem.getBadNum();
				if (StringUtils.equals(checkItem.getRequire(), pcbCheckDetail.getCheckRequire())
						&& DataUtil.equals(badNum, pcbCheckDetail.getBadNum())
						&& StringUtils.equals(checkItem.getResult(), pcbCheckDetail.getCheckResult())
						&& DataUtil.equals(PcbmisUtil.judge2int(checkItem.getJudgeResult()),
								pcbCheckDetail.getJudgeResult())
						&& StringUtils.equals(checkItem.getBoardTolerance(), pcbCheckDetail.getBoardTolerance())) {
					continue;
				}
				pcbCheckDetail.setCheckRequire(checkItem.getRequire());
				pcbCheckDetail.setBoardTolerance(checkItem.getBoardTolerance());
				pcbCheckDetail.setCheckResult(checkItem.getResult());
				pcbCheckDetail.setBadNum(badNum);
				pcbCheckDetail.setJudgeResult(PcbmisUtil.judge2int(checkItem.getJudgeResult()));
				Integer judge = pcbCheckDetail.getJudgeResult();
				if (judge == 0) {
					badRecord = 1;
				}
				if (judge != null && judge == 1) {
					pcbCheckDetail.setBadNum(null);
				}
				pcbCheckDetail.setUpdateTime(date);
				pcbCheckDetailMapper.updateByPrimaryKey(pcbCheckDetail);
			}
		} else {
			for (String key : map.keySet()) {
				CheckItemRequest checkItem = map.get(key);
				if (checkItem == null) {
					checkItem = new CheckItemRequest();
				}
				Integer badNum = checkItem.getBadNum();
				PcbCheckDetail pcbCheckDetail = new PcbCheckDetail();
				pcbCheckDetail.setCheckNum(checkNum);
				pcbCheckDetail.setOrderNum(orderNum);
				pcbCheckDetail.setOptionName(key);
				pcbCheckDetail.setCheckRequire(checkItem.getRequire());
				pcbCheckDetail.setBoardTolerance(checkItem.getBoardTolerance());
				pcbCheckDetail.setCheckResult(checkItem.getResult());
				pcbCheckDetail.setBadNum(badNum);
				pcbCheckDetail.setJudgeResult(PcbmisUtil.judge2int(checkItem.getJudgeResult()));
				Integer judge = pcbCheckDetail.getJudgeResult();
				if (judge == 0) {
					badRecord = 1;
				}
				if (judge != null && judge == 1) {
					pcbCheckDetail.setBadNum(null);
				}
				pcbCheckDetail.setCreateTime(date);
				pcbCheckDetailMapper.insertSelective(pcbCheckDetail);
			}
		}
		// 插入日志
		PcbCheckNoteBean noteBean = checkDetail.getNote();
		if (noteBean != null) {
			pcbCheckOrder.setNote(noteBean.getNote());
		}
		// 删除原来的尺寸翘曲度数据
		List<SizeAndWarpingDegree> sizeAndWarpingDegrees = sizeAndWarpingDegreeMapper.selectByCheckNum(checkNum);
		if (sizeAndWarpingDegrees != null && sizeAndWarpingDegrees.size() != 0) {
			for (SizeAndWarpingDegree sizeAndWarpingDegree : sizeAndWarpingDegrees) {
				sizeAndWarpingDegreeMapper.deleteByPrimaryKey(sizeAndWarpingDegree.getId());
			}
		}
		Integer spotNum = null;
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
		String userCode = null;
		String userName = null;
		if (wxtbUser != null) {
			userCode = wxtbUser.getUserCode();
			userName = wxtbUser.getUsername();
		}
		pcbCheckOrder.setTemplateId(templateId);
		pcbCheckOrder.setSpotCheckNumPcs(spotNum);
		pcbCheckOrder.setInspector(userCode);
		pcbCheckOrder.setCheckDate(date);
		// 改变状态为待审核
		pcbCheckOrder.setCheckState(CheckStateEnum.TO_AUDIT.getDescr());
		pcbCheckOrder.setCheckStateId(CheckStateEnum.TO_AUDIT.getCode());
		pcbCheckOrder.setBadRecord(badRecord);
		pcbCheckOrder.setUpdateTime(null);
		pcbCheckOrderMapper.updateByPrimaryKeySelective(pcbCheckOrder);
		supportService.doOperateLog("检验", orderNum, "提交检验结果", userName);
		return ErrorCodeEnum.SUCCESS;
	}

	@Override
	public boolean getIsCanEdit(Integer type, String orderNum) {
		if (type == 1) {
			PcbCheckOrder pcbCheckOrder = pcbCheckOrderMapper.selectByCheckNum(orderNum);
			if (pcbCheckOrder == null) {
				return false;
			}
			if (Objects.equals(pcbCheckOrder.getCheckStateId(), CheckStateEnum.COMPLETE.getCode())) {
				return true;
			}
		} else if (type == 2) {
			PcbCheckReport pcbCheckReport = pcbCheckReportMapper.selectByReportNum(orderNum);
			if (pcbCheckReport == null) {
				return false;
			}
			if (Objects.equals(pcbCheckReport.getStatusId(), ReportStateEnum.COMPLETE.getCode())) {
				return true;
			}
		}
		return false;
	}

}
