package com.pcbwx.pcbmis.service;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.pcbwx.pcbmis.bean.CheckInfo;
import com.pcbwx.pcbmis.bean.CommonEnumBean;
import com.pcbwx.pcbmis.bean.CommonStrEnumBean;
import com.pcbwx.pcbmis.model.PcbCheckOrder;

import java.util.List;

public interface UtilService {
	
	/**
	 * 获取角色下拉框
	 * @param request
	 * @return
	 */
	JSONArray getRoles(HttpServletRequest request);
	
	/**
	 * 获取权限下拉框
	 * @param request
	 * @return
	 */
	JSONArray getAuths(HttpServletRequest request);
	
	/**
	 * 获取生产厂家下拉框
	 * @return
	 */
	JSONArray getFactoryList();
	
	/**
	 * 获取等级下拉框
	 * @return
	 */
	JSONArray getGrade();
	
	/**
	 * 获取检验内容下拉框
	 * @return
	 */
	JSONArray getCheckContent();
	
	/**
	 * 获取检验状态下拉框
	 * @return
	 */
	JSONArray getCheckStateList();
	
	/**
	 * 获取报告状态下拉框
	 * @return
	 */
	JSONArray getReportStateList();
 	
	/**
	 * 获取边框公差
	 * @param id
	 * @return
	 */
	String getFrameTolerance(Integer frameId);
	
	/**
	 * 获取板厚公差
	 * @param id
	 * @return
	 */
	String getBoardPlyTolerance(Integer boardPlyId);
	
	/**
	 * 获取生产配注
	 * @param orderNum
	 * @param type
	 * @return
	 */
	List<String> getProductNotes(String orderNum, Integer type);
	
	/**
	 * 模糊搜索获取客户code集合
	 * @param guest
	 * @return
	 */
	List<String> getEdaGuest(String guest);
	
	/**
	 * 印制板导出补全数据
	 * @param pcbCheckOrders
	 * @param checkInfos
	 * @return
	 */
	List<CheckInfo> addAll(List<PcbCheckOrder> pcbCheckOrders, List<CheckInfo> checkInfos);
	
	/**
	 * 获取检验内容
	 * @param contentIds
	 * @return
	 */
	String getContent(String contentIds);


	List<CommonEnumBean> getDisposalMethodList();

	List<CommonEnumBean> getProductTypeList();

	List<CommonEnumBean> getUnqualifiedSourceList();

	List<CommonEnumBean> getProductLevelList();
	
	List<CommonEnumBean> getTemplateList();
	
	List<CommonStrEnumBean> getUserList();
	
	List<CommonEnumBean> getSMTOrderStatus();
	
	List<CommonEnumBean> getCOLOrderStatus();
	
	List<CommonEnumBean> getReportStatus();
}
