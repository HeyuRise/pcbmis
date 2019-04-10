/**
 * 
 */
package com.pcbwx.pcbmis.enums;

/**
 * 行为类型枚举类
 * 
 * @author 王海龙
 *
 */
public enum ActionTypeEnum {
	PRE_CHECK("pre_check", "获取待检验工单列表"), 
	PRE_REPORT("pre_report", "获取待处理报告"), 
	PRE_AUDIT("pre_audit", "获取待审核工单列表"),
	PRE_AUDIT_REPORT("pre_audit_report", "获取待审核报告"),
	ORDER_LIST("order_list", "获取工单列表"), 
	CHECK_LIST("check_list", "获取检验单列表"),
	BAD_RECORD("bad_record", "获取不良记录列表"),
	// ---------不合格品-------------------
	UPLOAD_FILE("upload_file", "上传图片或文件"),
	SEE_FILE("see_file", "查看文件"),
	CREATE_UNQUALIFIED("create_unqualified", "创建不合格品处置单"),
	PUT_UNQUALIFIED("put_unqualified", "创建不合格品处置单"),
	GET_UNQUALIFIED("get_unqualified", "获取不合格品处置单详情"),
	DOWNLOAD_FILE("download_file", "下载文件"),
	GET_UNQUALIFIED_LIST("put_unqualified", "获取不合格品处置单列表"),
	PUT_UNQUALIFIED_HEAR("put_unqualified_hear", "保存审理结果"),
	PUT_UNQUALIFIED_HEAR_FILE("put_unqualified_hear_file", "获取不合格品处置单的附件列表"),
	OUT_PUT_UNQUALIFIED_DETAIL("out_put_unqualified_detail", "导出不合格品处置单"),
	// -----------检验报告---------------
	DETAIL("detail", "获取工单详情新"),
	AUDIT_DETAIL("audit_detail", "审核详情"),
	BAD_RECORD_DETAIL("bad_record_detail", "不良数量详情"),
	CHECK_REPORT("check_report", "报告列表"), 
	REPORT_DETAIL("report_detail", "获取报告详情"),  
	REPORT_AUDIT_DETAIL("report_audit_detail", "获取报告审核详情"),  
	GET_LATELY_OPERATE("get_lately_operate", "获取最新动态"), 
	AUDIT_SUBMIT("audit_submit", "检验结果提交审核"), 
	EDIT_ORDER("edit_order", "编辑单个工单"),
	BOARD_TOLERANCE("board_tolerance", "上传公差数据"),
	CHECK_DATE("check_date", "上传检验数据"),
	SIZE("size", "修改尺寸翘曲度"),
	REPORT_AUDIT_SUBMIT("report_audit_submit", "提交报告是否审核通过"),
	EDIT_IS_REPORT("edit_is_report", "编辑是否出报"),
	POST_REPORT("post_report", "接受报告数据"),
	PRINT_CERTIFICATE("print_certificate", "获取打印合格证信息"),
	// -----------入库单-----------------
	INSTORAGE_LIST("inStorage_list", "获取入库单列表"),
	RECEIVE_PRODUCT("receive_product", "入库单接收产品"),
	MODIFY_BOARD_NAME("modify_board_name", "修改板名"),
	
	// -----------接收单-----------------
	RECEIVE_LIST("receive_list", "获取接受单列表"),
	RECEIVE_MODIFY("receive_modify", "修改接受单"),
	RECEIVE_DELETE("receive_delete", "删除接受单"),
	// -----------pcb模板-----------------
	TEMPLATE_LIST("template_list", "模板列表"),
	TEMPLATE_ADD("template_add", "添加模板"),
	TEMPLATE_ENABLE("template_enable", "修改可用不可用"),
	TEMPLATE_CHECK("template_check", "查看检验单模板"),
	TEMPLATE_CHECK_MODIFY("template_check_modify", "修改检验单模板"),
	TEMPLATE_REPORT("template_report", "查看报告单模板单"),
	TEMPLATE_REPORT_MODIFY("template_report_modify", "修改报告单模板"),
	
	DOWN_TEST_NUM("downTestNum", "印制板检验记录表"),
	DOWN_WRITE_REPORT("down_write_report", "印制板检验报告"),
	DOWN_UNQUALIFIED_LIST("down_unqualified_list", "导出不合格品处置单列表"),

	GET_USER_DETAIL("get_user_detail", "获取用户详情"),
	USER_LIST("user_list", "获取用户列表"),
	EDIT_USER("edit_user", "编辑用户"),
	ROLE_LIST("role_list", "获取角色列表"),
	EDIT_ROLE("edit_role", "编辑角色"),
	GET_LOG_LIST("get_log_list", "获取日志列表"),
	ADD_ROLE("add_role", "添加角色"), 
	DELETE_ROLE("delete_role", "删除角色"),
	// -----------修改数据-----------------
	MODIFY_CHECK("modify_check", "印制板检验记录表"),
	MODIFY_REPORT("modify_report", "印制板检验报告");

	private String code; // 日志代码
	private String desc; // 日志描述

	private ActionTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
