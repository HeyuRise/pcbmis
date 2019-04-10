/**
 * 
 */
package com.pcbwx.pcbmis.enums;

/**
 * 报告模板字段枚举类
 * 
 * @author 孙贺宇
 *
 */
public enum ReportOptionEnum {
	
	// 模板信息
	BASE_MATERIAL_APPEARANCE("base_material_appearance", "基材外观"),
	CONDUCTIVE_PATTERN("conductive_pattern", "导电图形"),
	PREVENT_SMT_TYPE("prevent_smt_type", "阻焊类型"),
	PREVENT_SMT_APPEARANCE("prevent_smt_appearance", "阻焊外观"),
	CHARACTER_TYPE("character_type", "字符类型"),
	CHARACTER_APPEARANCE("character_appearance", "字符外观"),
	EXTERNAL_COATING_ADHESION("EXTERNAL_COATING_ADHESION", "外镀层附着力"),
	PREVENT_SMT_CHARACTER_ADHESION("prevent_smt_character_adhesion", "阻焊/字符附着力"),
	APERTURE("aperture", "孔径"),
	V_CUT("v_cut", "特殊尺寸"),
	CIRCUIT_CONNECTIVITY("circuit_connectivity", "电路连通性"),
	CIRCUIT_INSULATIVITY("circuit_insulativity", "电路绝缘性"),
	SPECIAL_IMPEDANCE("special_impedance", "特性阻抗"),
	SOLDERABILITY("solderability", "可焊性"),
	// 非模板信息
	BASE_MATERIAL_TYPES("base_material_types", "基材类型"),
	SURFACE_PROCESS("surface_process", "表面处理"),
	REGULAR_LABEL("regular_label", "常规标记"),
	PRODUCE_PERIOD("produce_period", "表面处理"),
	MIN_LINE_WIDTH("min_line_width", "最小线宽"),
	MIN_LINE_DISTANCE("min_line_distance", "最小间距"),
	MIN_ANNULAR_RING("min_annular_ring", "最小环宽"),
	BOARD_PLY("board_ply", "板厚"),
	WARPING_DEGREE("warping_degree", "翘曲度"),
	BOARD_LONG("board_long", "尺寸长"),
	BOARD_WIDE("board_wide", "尺寸宽"),
	MICROSECTIONING("microsectioning", "显微剖切");
	
	private String code;
	private String desc;

	private ReportOptionEnum(String code, String desc) {
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
