package com.pcbwx.pcbmis.enums;
/**
 * 系统配置枚举类
 * @author 王海龙
 * @version 1.0
 *
 */
public enum DictionaryEnum {

	PROJECT_STATUS("project_status","工单状态"),
	NEW_OLD_PRODUCT("new_old_product","新老品"),
	CATEGORY_GRADE("category_grade","军民品等级"),
	PREVENT_SMT_COLOR("prevent_smt_color", "阻焊颜色"),
	JOIN_BOARD_REQUIRE("join_board_require", "拼板要求"),
	
	CHARACTER("character", "字符"),
	CHARACTER_COLOUR("character_colour", "字符颜色"),
	PREVENT_SMT("prevent_smt", "阻焊"),
	PRODUCTION_BATCH("production_batch", "生产批次"),
	CHECK_STANDARD("check_standard", "检验要求"),
	WARPING_DEGREE("warping_degree", "翘曲度"),
	
	CHECK_STATE("check_state", "检验状态"),
	REPORT_STATE("report_state", "报表状态"),
	
	CHECK_CONTENT("check_content", "检验内容");

	private String code;
	private String descr;
	
	private DictionaryEnum(String code, String descr) {
		this.code = code;
		this.descr = descr;
	}

	public String getCode() {
		return code;
	}

	public String getDescr() {
		return descr;
	}

}
