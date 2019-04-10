/**
 * 
 */
package com.pcbwx.pcbmis.control;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.authkit.callback.AuthService;
import com.pcbwx.pcbmis.enums.ActionTypeEnum;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.service.LogService;
import com.pcbwx.pcbmis.service.OrderService;
import com.pcbwx.pcbmis.service.ReportService;
import com.pcbwx.pcbmis.service.SupportService;
import com.pcbwx.pcbmis.utils.StringUtil;

/**
 * 
 * @author 孙贺宇
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

	//private static Logger logger = Logger.getLogger(OrderController.class);

	@Autowired
	private AuthService authService;
	@Autowired
	private LogService logService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private SupportService supportService;

	/**
	 * 获取待检验工单列表
	 * @param orderNum	工单号
	 */
	@RequestMapping(value = { "/preCheck" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPreCheckOrderList(HttpServletRequest request,
			@RequestParam( value = "orderNum", required = false) String orderNum,
			@RequestParam( value = "rows", defaultValue = "10", required = false) Integer rows,
			@RequestParam( value = "page", defaultValue = "1", required = false) Integer page)
			throws ServletException, IOException {
		final AkAuthUser wxtbUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<String, Object>();
		// 行为记录
		logService.addAction("", ActionTypeEnum.PRE_CHECK.getCode(), wxtbUser.getUserCode(), "");
		supportService.doSystemLog(request, wxtbUser.getUsername(), "获取待检验工单列表", 0);
		response = orderService.selectPreCheckAndAudit(wxtbUser.getUserCode(), orderNum, 1, page, rows);
		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		return response;
	}
	
	
	/**
	 * 获取待处理报告
	 * @param orderNum	工单号
	 */
	@RequestMapping( value = { "/preReport" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPreReport(HttpServletRequest request,
			@RequestParam( value = "orderNum", required = false) String orderNum,
			@RequestParam(value = "rows", defaultValue = "10", required = false) Integer rows,
			@RequestParam(value = "page", defaultValue = "1", required = false) Integer page){
		final AkAuthUser wxtbUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<String, Object>();
		logService.addAction("", ActionTypeEnum.PRE_CHECK.getCode(), wxtbUser.getUserCode(), "");
		supportService.doSystemLog(request, wxtbUser.getUsername(), "获取待处理报告", 0);
		response = reportService.getPreCheckAndAudit(wxtbUser, orderNum, 1, page, rows);
		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		return response;
	}

	/**
	 * 获取待审核工单列表
	 * @param orderNum	工单号
	 */
	@RequestMapping( value = { "/preAudit" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPreAuditOrder(HttpServletRequest request,
			@RequestParam( value = "orderNum", required = false) String orderNum,
			@RequestParam(value = "rows", defaultValue = "10", required = false) Integer rows,
			@RequestParam(value = "page", defaultValue = "1", required = false) Integer page){
		final AkAuthUser wxtbUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<String, Object>();
		logService.addAction("", ActionTypeEnum.PRE_AUDIT.getCode(), wxtbUser.getUserCode(), "");
		supportService.doSystemLog(request, wxtbUser.getUsername(), "获取待审核工单列表", 0);
		response = orderService.selectPreCheckAndAudit(wxtbUser.getUserCode(), orderNum, 3, page, rows);
		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		return response;
	}
	
	/**
	 * 获取待审核报告
	 * @param orderNum	工单号
	 */
	@RequestMapping( value = { "/preAuditReport" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPreAuditReport(HttpServletRequest request,
			@RequestParam( value = "orderNum", required = false) String orderNum,
			@RequestParam(value = "rows", defaultValue = "10", required = false) Integer rows,
			@RequestParam(value = "page", defaultValue = "1", required = false) Integer page){
		final AkAuthUser wxtbUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<String, Object>();
		logService.addAction("", ActionTypeEnum.PRE_AUDIT_REPORT.getCode(), wxtbUser.getUserCode(), "");
		supportService.doSystemLog(request, wxtbUser.getUsername(), "获取待审核报告", 0);
		response = reportService.getPreCheckAndAudit(wxtbUser, orderNum, 3, page, rows);
		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		return response;
	}

	/**
	 * 获取工单列表
	 * @param orderNum	工单号
	 * @param guest		客户
	 * @param factoryId	生产厂家id
	 * @return
	 */
	@RequestMapping( value = {"/orderList"} , method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getOrderListByCondition(HttpServletRequest request,
			@RequestParam( value = "list_gong", required = false) String orderNum, 
			@RequestParam( value = "list_customer", required = false) String guest,
			@RequestParam( value = "factoryId", required = false) Integer factoryId,
			@RequestParam(value = "rows", defaultValue = "20", required = false) Integer rows,
			@RequestParam(value = "page", defaultValue = "1", required = false) Integer page){
		final AkAuthUser wxtbUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<String, Object>();
		logService.addAction("", ActionTypeEnum.ORDER_LIST.getCode(), wxtbUser.getUserCode(), orderNum, guest, null);
		supportService.doSystemLog(request, wxtbUser.getUsername(), "获取工单列表", 0);
		response = orderService.getOrderListByCondition(orderNum, guest, factoryId, page, rows);
		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		return response;
	}
	
	/**
	 * 获取检验单列表
	 */
	@RequestMapping( value = {"/checkList"}, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCheckList(HttpServletRequest request,
			@RequestParam( value = "orderNum", required = false) String orderNum,
			@RequestParam( value = "guest", required = false) String guest,
			@RequestParam( value = "gradeId", required = false) Integer gradeId,
			@RequestParam( value = "content", required = false) Integer content,
			@RequestParam( value = "boardName", required = false) String boardName,
			@RequestParam( value = "factoryId", required = false) Integer factoryId,
			@RequestParam( value = "statusId", required = false) Integer statusId,
			@RequestParam( value = "checkStart", required = false) String checkStart,
			@RequestParam( value = "checkEnd", required = false) String checkEnd,
			@RequestParam( value = "auditStart", required = false) String auditStart,
			@RequestParam( value = "auditEnd", required = false) String auditEnd,
			@RequestParam(value = "rows", defaultValue = "20", required = false) Integer rows,
			@RequestParam(value = "page", defaultValue = "1", required = false) Integer page){
		if (StringUtil.isEmpty(orderNum)) {
			orderNum = null;
		}
		if (StringUtil.isEmpty(guest)) {
			guest = null;
		}
		if (StringUtil.isEmpty(boardName)) {
			boardName = null;
		}
		final AkAuthUser wxtbUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<String, Object>();
		logService.addAction("", ActionTypeEnum.CHECK_LIST.getCode(), wxtbUser.getUserCode(), orderNum);
		supportService.doSystemLog(request, wxtbUser.getUsername(), "获取检验单列表", 0);
		response = orderService.getCheckListNew(wxtbUser, orderNum, guest, gradeId,
				content, boardName, factoryId, statusId, checkStart, checkEnd,
				auditStart, auditEnd, page, rows);
		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		return response;
	}

	/**
	 * 获取检验单详情新
	 */
	@RequestMapping( value = { "/detailNew" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPreCheckOrderDetailNew(HttpServletRequest request,
			@RequestParam("checkNum") String checkNum,
			@RequestParam(value = "templateId", required = false) Integer templateId,
			@RequestParam( value = "type", required = false, defaultValue = "1") Integer type)
			throws ServletException, IOException {
		final AkAuthUser wxtbUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<String, Object>();
		if (checkNum == null || checkNum.equals("")) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			return response;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map = orderService.goForOrderDetail(wxtbUser, checkNum, type, templateId);
		logService.addAction("", ActionTypeEnum.DETAIL.getCode(), wxtbUser.getUserCode(), checkNum);
		supportService.doSystemLog(request, wxtbUser.getUsername(), "获取工单详情", 0);
		response.put("auditDetail", map);
		if (map == null || map.isEmpty()) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", ErrorCodeEnum.SYSTEM_ERROR.getDescr());
		} else {
			response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		}
		return response;
	}
	
	
	/**
	 * 审核详情
	 * @param request
	 * @param checkNum 检验单号
	 */
	@RequestMapping( value = {"/auditDetail"}, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAuditDetail(HttpServletRequest request,
			@RequestParam("checkNum") String checkNum, 
			@RequestParam(value = "type", required = false, defaultValue = "0") Integer type){
		final AkAuthUser wxtbUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<String, Object>();
		if (checkNum == null || checkNum.equals("")) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			return response;
		}
		Map<String, Object> map = orderService.goForAuditOrder(wxtbUser, checkNum, type);
		logService.addAction("", ActionTypeEnum.AUDIT_DETAIL.getCode(), wxtbUser.getUserCode(), checkNum);
		supportService.doSystemLog(request, wxtbUser.getUsername(), "审核详情", 0);
		response.put("auditDetail", map);
		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		return response;
	}

	
	/**
	 * 报告列表
	 */
	@RequestMapping( value = {"/checkReport"}, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCheckReport(HttpServletRequest request,
			@RequestParam( value = "orderNum", required = false) String orderNum,
			@RequestParam( value = "guest", required = false) String guest,
			@RequestParam( value = "boardName", required = false) String boardName,
			@RequestParam( value = "gradeId", required = false) Integer gradeId,
			@RequestParam( value = "factoryId", required = false) Integer factoryId,
			@RequestParam( value = "statusId", required = false) Integer statusId,
			@RequestParam( value = "makeTimeStart", required = false) String makeTimeStart,
			@RequestParam( value = "makeTimeEnd", required = false) String makeTimeEnd,
			@RequestParam( value = "auditTimeStart", required = false) String auditTimeStart,
			@RequestParam( value = "auditTimeEnd", required = false) String auditTimeEnd,
			@RequestParam(value = "rows", defaultValue = "20", required = false) Integer rows,
			@RequestParam(value = "page", defaultValue = "1", required = false) Integer page) {
		if (StringUtil.isEmpty(orderNum)) {
			orderNum = null;
		}
		if (StringUtil.isEmpty(guest)) {
			guest = null;
		}
		if (StringUtil.isEmpty(boardName)) {
			boardName = null;
		}
		final AkAuthUser wxtbUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<String, Object>();
		logService.addAction("", ActionTypeEnum.CHECK_REPORT.getCode(), wxtbUser.getUserCode(), null);
		supportService.doSystemLog(request, wxtbUser.getUsername(), "报告列表", 0);
		response = reportService.getCheckReports(wxtbUser, orderNum, guest, boardName,
				gradeId, factoryId, statusId, makeTimeStart, makeTimeEnd,
				auditTimeStart, auditTimeEnd, page, rows);
		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		return response;
	}

	/**
	 * 获取报告详情
	 * @param reportNum
	 * @param type = 1 编辑详情或审核详情，>1 提交后的跳转详情和已检验详情
	 */
	@RequestMapping( value = {"/reportDetail"}, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getReportDetail(HttpServletRequest request,
			@RequestParam("reportNum") String reportNum,
			@RequestParam(value = "templateId", required = false) Integer templateId,
			@RequestParam( value = "type", required = false) Integer type){
		final AkAuthUser wxtbUser = authService.getLoginUser();
		Map<String, Object> response = new HashMap<String, Object>();
		if (reportNum == null || reportNum.equals("")) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
		}
		supportService.doSystemLog(request, wxtbUser.getUsername(), "获取报告详情", 0);
		Map<String, Object> detailMap = null;
		if (type == null || type <= 1) {
			type = 1;
			logService.addAction("", ActionTypeEnum.REPORT_AUDIT_DETAIL.getCode(), wxtbUser.getUserCode(), "");
		}else {
			logService.addAction("", ActionTypeEnum.REPORT_DETAIL.getCode(), wxtbUser.getUserCode(), "");
		}
		detailMap = reportService.goForReportDetail(wxtbUser, reportNum, type, templateId);
		response.put("reportDetail", detailMap);
		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		return response;
	}
	
	/**
	 * 获取最新动态
	 * @param request
	 * @return
	 */
	@RequestMapping( value = {"/getLatelyOperate"}, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getLaterlyOperate(HttpServletRequest request,
			@RequestParam(value = "rows", defaultValue = "20", required = false) Integer rows,
			@RequestParam(value = "page", defaultValue = "1",required = false) Integer page){
		Map<String, Object> response = new HashMap<String, Object>();
		response = orderService.getLatelyOperate(page, rows);
		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		return response;
	}

}
