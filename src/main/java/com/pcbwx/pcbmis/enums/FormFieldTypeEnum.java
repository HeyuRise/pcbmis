/**
 * 
 */
package com.pcbwx.pcbmis.enums;

/**
 * 默认人员类型枚举类
 * 
 * @author 孙贺宇
 *
 */
public enum FormFieldTypeEnum {
	
	SMT_INSPECTOR("smt_inspector", "SMT报告检验人员"), 
	SMT_RE_INSPECTOR("smt_re_inspector", "SMT报告复检人员"), 
	COL_INSPECTOR("col_inspector", "COL报告检验人员"),
	COL_RE_INSPECTOR("col_re_inspector", "COL报告复检人员");

	private String code; // 日志代码
	private String desc; // 日志描述

	private FormFieldTypeEnum(String code, String desc) {
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
