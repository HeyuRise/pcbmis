package com.pcbwx.pcbmis.enums;

/**
 * 检验单状态
 * @author 孙贺宇
 * @version 1.0
 *
 */
public enum CheckStateEnum {
	TO_CHECK(1, "待检验"),
	CHECKING(2, "检验中"),
	TO_AUDIT(3, "待审核"),
	AUDITING(4, "审核中"),
	COMPLETE(5, "已检验"),
	NO_CHECK(6, "不检验");
	
	private int code;
	private String descr;
	
	private CheckStateEnum(int code, String descr) {
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
