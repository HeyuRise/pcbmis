package com.pcbwx.pcbmis.enums;
/**
 * 系统配置枚举类
 * @author 王海龙
 * @version 1.0
 *
 */
public enum ConfigEnum {

	//------------------ 配置文件配置项 -------------------------------
	SERVICE_MAIN_URL("service.main.url", "服务地址前缀"),
	DEBUG_WITHOUT_LOGIN("debug.without.login", "是否调试(免登录)"),
	HISOTRY_RECORD_MONTHS("hisotry.record.months", "获取历史记录月数"),
	IN_STORAGE_HISOTRY_RECORD_MONTHS("in.storage.hisotry.record.months", "获取入库单历史记录月数"),
	REPORT_STORE_PATH("report.store.path", "报表文件存储路径"),
	REPORT_FILE_URL("report.file.url", "报表文件访问url"),
	SYSTEMS_URL("systems.url", "重新选择系统地址"),
	SYSTEM_CODE("system.code", "系统编号"),
	BEHAVIOUR_RECORD_URL("behaviour.record.url", "用户行为记录ip地址"),
	NOT_BEHAVIOUR_RECORD("not.behaviour.record", "是否调试（用户行为记录）"),
	LOCAL_FILE_PATH("local.file.path", "本地文件存储路径"),
	ONLINE_HELP_URL("online.help.url", "在线帮助地址s"),
	
	PCB_SERVICE_ADDRESS("pcb.service.address", "pcb系统url地址"),
	OISS_SERVICE_ADDRESS("oiss.service.address", "oiss系统url地址"),
	UMS_SERVICE_ADDRESS("ums.service.address", "ums系统url地址"),
	CMS_SERVICE_ADDRESS("cms.service.address", "cms系统url地址"),
	QRGS_SERVICE_ADDRESS("qrgs.service.address", "qms系统url地址"),
	SMT_SERVICE_ADDRESS("smt.service.address", "smt系统url地址"),
	COL_SERVICE_ADDRESS("col.service.address", "col系统url地址"),
	//------------------ config表配置项 -------------------------------
	DATA_SYNC_FLAG("data.sync.flag", "数据同步标志"),
	PLACE_CODE("place.code", "地区代码"),
	INFOR_SYSTEM_CODE("infor.system.code", "系统编码"),
	//------------------ record_utils表记录项 -------------------------
	LAST_IN_STORAGE_ORDER_IMPORT_TIME("last.in.storage.order.import.time", "上次入库单导入时间"),
	LAST_COMPANY_IMPORT_TIME("last.company.import.time", "上次公司导入时间"),
	LAST_GUEST_IMPORT_TIME("last.guest.import.time", "上次客户导入时间"),
	LAST_FACTORY_IMPORT_TIME("last.factory.import.time", "上次生产厂家导入时间"),
	
	LAST_GRADE_IMPORT_TIME("last.grade.import.time", "上次军民品等级导入时间"),
	LAST_CHARACTER_IMPORT_TIME("last.character.import.time", "上次字符导入时间"),
	LAST_CHARACTER_COLOUR_IMPORT_TIME("last.character.colour.import.time", "上次字符颜色导入时间"),
	LAST_PREVENT_SMT_IMPORT_TIME("last.prevent.smt.import.time", "上次阻焊导入时间"),
	LAST_PRODUCTION_BATCH_IMPORT_TIME("last.production.batch.import.time", "上次生产批次导入时间"),
	
	LAST_PREVENT_SMT_COLOR_IMPORT_TIME("last.prevent.smt.color.import.time", "上次阻焊颜色导入时间"),
	LAST_JOIN_BOARD_REQUIRE_IMPORT_TIME("last.join.board.require.import.time", "上次拼板要求导入时间"),
	LAST_JOIN_BOARD_ORDER_IMPORT_TIME("last.join.board.order.import.time", "上次子板要求导入时间"),
	LAST_JOIN_BOARD_WAY_IMPORT_TIME("last.join.board.way.import.time", "上次拼板方式导入时间"),	
	LAST_PRODUCT_ORDER_IMPORT_TIME("last.product.order.import.time", "上次pcb工单导入时间"),
	LAST_SURFACE_PROCESS_IMPORT_TIME("last.surface.process.import.time", "上次表面处理导入时间"),
	LAST_BOARD_PLY_TOLERANCE_IMPORT_TIME("last.board.ply.tolerance.import.time", "上次板厚公差导入时间"),
	LAST_BASEM_ATERIAL_IMPORT_TIME("last.basem.aterial.import.time", "上次基材信息导入时间"),
	LAST_FRAME_TOLERANCE_IMPORT_TIME("last.frame.tolerance.import.time", "上次边框公差导入时间"),
	LAST_CRAFT_IMPORT_TIME("last.craft.import.time", "上次生产工艺妓导入时间"),
	LAST_USER_IMPORT_TIME("last.user.import.time", "上次用户信息导入时间"),
	LAST_DEPARTMENT_IMPORT_TIME("last.department.import.time", "上次部门信息导入时间"),
	LAST_SMT_ORDER_IMPORT_TIME("last.smt.order.import.time", "上次Smt订单导入时间"),
	LAST_COL_ORDER_IMPORT_TIME("last.col.order.import.time", "上次col订单导入时间"),
	LAST_BEHAVIOUR_TIME("last.behaviour.time", "上次行为记录时间"),
	
	LAST_REPORT_TIME("last_report_time", "上次报表生成时间"),
	LAST_RELOAD_TIME("last_reload_time", "上次缓存载入时间");
	
	private String code;
	private String descr;
	
	private ConfigEnum(String code, String descr) {
		this.code = code;
		this.descr = descr;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
}
