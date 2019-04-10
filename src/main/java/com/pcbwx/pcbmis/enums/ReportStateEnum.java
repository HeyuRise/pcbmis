package com.pcbwx.pcbmis.enums;

/**
 * 报告状态
 * @author 孙贺宇
 * @version 1.0
 *
 */
public enum ReportStateEnum {
	
	TO_CREATE(1, "待创建"),
	EDITING(2, "编辑中"),
	TO_PROVE(3, "待审核"),
	PROVING(4, "审核中"),
	COMPLETE(5, "报告已出");
	
	private int code;
	private String descr;
	
	private ReportStateEnum(int code, String descr) {
		this.code = code;
		this.descr = descr;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	
}
