package com.pcbwx.pcbmis.enums;

/**
 * 按钮
 * @author 孙贺宇
 * @version 1.0
 *
 */
public enum ButtonEnum {

	MODIFY_CHECK_REPORT(7, "修改检验单报告");
	
	private int code;
	private String descr;
	
	private ButtonEnum(int code, String descr) {
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
