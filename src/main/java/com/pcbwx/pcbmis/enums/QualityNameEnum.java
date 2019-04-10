/**
 * 
 */
package com.pcbwx.pcbmis.enums;

/**
 * 质量记录枚举类
 * 
 * @author 孙贺宇
 *
 */
public enum QualityNameEnum {
	SMT_REPORT("smt_report", "电子装联产品检验报告"), 
	COL_REPORT("col_report", "冷板产品检验报告"), 
	COL_SIZE_WARPING("col_size_warping", "冷板尺寸检验记录表"),
	PRE_AUDIT_REPORT("pre_audit_report", "获取待审核报告");

	private String code; // 日志代码
	private String desc; // 日志描述

	private QualityNameEnum(String code, String desc) {
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
