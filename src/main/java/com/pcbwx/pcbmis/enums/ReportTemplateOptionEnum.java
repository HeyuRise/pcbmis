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
public enum ReportTemplateOptionEnum {
	
	BASE_MATERIAL_APPEARANCE("base_material_appearance", "基材外观"),
	CONDUCTIVE_PATTERN("conductive_pattern", "导电图形"),
	PREVENT_SMT_TYPE("prevent_smt_type", "阻焊类型"),
	PREVENT_SMT_APPEARANCE("prevent_smt_appearance", "阻焊外观"),
	CHARACTER_TYPE("character_type", "字符类型"),
	CHARACTER_APPEARANCE("character_appearance", "字符外观"),
	EXTERNAL_COATING_ADHESION("external_coating_adhesion", "外镀层附着力"),
	PREVENT_SMT_CHARACTER_ADHESION("prevent_smt_character_adhesion", "阻焊/字符附着力"),
	APERTURE("aperture", "孔径"),
	V_CUT("v_cut", "特殊尺寸"),
	CIRCUIT_CONNECTIVITY("circuit_connectivity", "电路连通性"),
	CIRCUIT_INSULATIVITY("circuit_insulativity", "电路绝缘性"),
	SPECIAL_IMPEDANCE("special_impedance", "特性阻抗"),
	SOLDERABILITY("solderability", "可焊性");

	private String code;
	private String desc;

	private ReportTemplateOptionEnum(String code, String desc) {
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
