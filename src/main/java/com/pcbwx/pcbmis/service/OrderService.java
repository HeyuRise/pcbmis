package com.pcbwx.pcbmis.service;

import java.util.Map;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.pcbmis.bean.BoardToleranceBean;
import com.pcbwx.pcbmis.bean.OrderDetail;


/**
 * 检验单信息获取接口
 * 
 * @author 孙贺宇
 *
 */
public interface OrderService {
	
	/**
	 * 获取待检验工单
	 */
	Map<String, Object> selectPreCheckAndAudit(String userCode,
			String orderNum, Integer checkStartId, Integer page,
			Integer pageSize);
	
	/**
	 * 获取印制板生产单列表
	 * @param orderNum
	 * @param guest
	 * @param factory
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Map<String, Object> getOrderListByCondition(String orderNum, String guest, Integer factoryId, Integer page, Integer pageSize);
	
	/**
	 * 获取检验单审核详情
	 * @param checkNum
	 * @return
	 */
	Map<String, Object> goForAuditOrder(AkAuthUser wxtbUser, String checkNum, Integer type);
	
	/**
	 * 审核检验单判断
	 * @param orderNum
	 * @param isPass
	 */
	Integer judgeAuditPass(AkAuthUser wxtbUser, String orderNum, Integer isPass); 
	
//	/**
//	 * 上传检验详情
//	 * @param checkNum
//	 * @param orderNum
//	 * @param checkDetail 接收的数据
//	 */
//	ErrorCodeEnum postDetail(AkAuthUser wxtbUser, String checkNum, String orderNum, CheckDetail checkDetail);
	
	/**
	 * 获取检验单模板信息
	 * @param checkNum
	 * @return
	 */
	OrderDetail selectOrderDetail(String checkNum, Integer templateId);
	
	/**
	 * 获取检验单详情新
	 * @param wxtbUser
	 * @param checkNum	检验单号
	 * @param type		1检验，2查看，3下载
	 * @return
	 */
	Map<String, Object> goForOrderDetail(AkAuthUser wxtbUser, String checkNum, Integer type, Integer templateId);
	
	/**
	 * 检验单列表
	 */
	Map<String, Object> getCheckListNew(AkAuthUser wxtbUser, String orderNum, String guest,
			Integer gradeId, Integer content, String boardName,
			Integer factoryId, Integer statusId, String checkStart,
			String checkEnd, String auditStart, String auditEnd, Integer page,
			Integer pageSize);
	
	/**
	 * 获取检验单尺寸翘曲度信息
	 */
	@Deprecated
	BoardToleranceBean goForBoardToleranceBean(AkAuthUser wxtbUser, String checkNum);
 	
	/**
	 * 获取最新动态
	 */
	Map<String, Object> getLatelyOperate(Integer page, Integer pageSize);
		
 }

