package com.pcbwx.pcbmis.enums;

/**
 * 工单状态枚举类
 * @author 孙贺宇
 * @version 1.0
 *
 */
public enum ColSmtReportEnum {

	GENERATED(1, "已生成"),
	INVALID(2, "作废");
	
	private int code;
	private String descr;
	
	private ColSmtReportEnum(int code, String descr) {
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
