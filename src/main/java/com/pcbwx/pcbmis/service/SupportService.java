package com.pcbwx.pcbmis.service;

import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.pcbwx.pcbmis.bean.SerialNumber;
import com.pcbwx.pcbmis.utils.HttpConnectionManager;

/**
 * 日志模块业务接口
 * 
 * @author 王海龙
 *
 */
public interface SupportService {
	boolean doReloadCache();
	
	void reloadFormDefaultPerson();

	/**
	 * 获取质量流水号
	 * @param userInnerCode    用户编号   TBU
	 * @param documentName     文档名字
	 * @param date			        日期
	 * @param companyCode      公司      TB，TBK
	 * @return
	 */
	SerialNumber getQrgsSerialNumber(String userInnerCode, String documentName, Date date, String companyCode);
	
	/**
	 * 作废质量流水号
	 * @param userInnerCode  用户编号   TBU
	 * @param documentName   文档名字
	 * @param serialNumber   流水号
	 * @param reason         作废原因
	 * @return
	 */
	boolean invalidSerialNumber(String userInnerCode, String documentName, String serialNumber, String reason);
	
	String fetchPcbCheckOrderNum(Date date);
	String fetchPcbSubCheckOrderNum(Date date, String productOrderNum);
	String fetchPcbJoinSubCheckOrderNum(Date date, String joinOrderNum);
	
	String fetchPcbReportNum(Date date);
	String fetchPcbSubReportOrderNum(Date date, String productOrderNum);
	String fetchPcbJoinSubReportOrderNum(Date date, String joinOrderNum);
	
	boolean needToDoReport();
	boolean recordDoReportTime();

	boolean doDetailDailyReport(Date now);
	boolean executeDetailDailyReport(Date procDate);
	
	boolean doDetailWeeklyReport(Date now);
	boolean executeDetailWeeklyReport(Date procDate);
	
	void doSystemLog(HttpServletRequest request, String userName, String description, Integer funcType);
	void dobehaviour(HttpConnectionManager cm);
	
	/**
	 * 操作记录
	 * @param operateName		操作项目
	 * @param operateContent	操作内容
	 * @param operateResult		操作结果
	 * @param userName			操作人
	 */
	void doOperateLog(String operateName, String operateContent, String operateResult, String userName);
	/**
	 * 获取用户的全部权限id集合
	 * @param account
	 * @return
	 */
	Set<Integer> getUserAuths(String account);	
}
