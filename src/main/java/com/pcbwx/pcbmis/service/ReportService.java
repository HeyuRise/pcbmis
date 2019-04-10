package com.pcbwx.pcbmis.service;

import java.util.Map;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.pcbmis.bean.ReportDetail;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;

public interface ReportService {
	
	/**
	 * 获取检验报告列表
	 * @param keyWord
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Map<String, Object> getCheckReports(AkAuthUser wxtbUser, String orderNum,
			String guest, String boardName, Integer gradeId, Integer factoryId,
			Integer statusId, String makeTimeStart, String makeTimeEnd,
			String auditTimeStart, String auditTimeEnd, Integer page,
			Integer pageSize);
	
	/**
	 * 获取报告详情
	 * @param check
	 * @return
	 */
	Map<String, Object> goForReportDetail(AkAuthUser wxtbUser, String check, Integer type, Integer templateId);
	
	/**
	 * 上传报告详情
	 * @param reportNum
	 * @param reportDetail
	 * @return
	 */
	ErrorCodeEnum postReportDetail(AkAuthUser wxtbUser, String reportNum, ReportDetail reportDetail, Integer templateId);
	
	/**
	 * 判断报告审核是否通过
	 * @param reportNum
	 * @param pass
	 * @return
	 */
	Integer judgeReportPass(AkAuthUser wxtbUser, String reportNum, Integer pass);
	
	/**
	 * 获取待处理报告
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Map<String, Object> getPreCheckAndAudit(AkAuthUser wxtbUser, String productNum, Integer stateId, Integer page, Integer pageSize);
	
	/**
	 * 获取打印合格证详情
	 * @param checkNum
	 * @return
	 */
	Map<String, Object> goforCertificateInfo(String checkNum, AkAuthUser wxtbUser);
}
