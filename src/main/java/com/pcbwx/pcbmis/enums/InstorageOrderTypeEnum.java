package com.pcbwx.pcbmis.enums;

/**
 * 来料类型
 * @author 孙贺宇
 * @version 1.0
 *
 */
public enum InstorageOrderTypeEnum {

	
	URGENT(1, "急件"),
	NORMAL(2, "正常板"),
	TWICE(3, "二次来板"),
	THIRD(4, "三次来板"),
	FORTH(5, "四次来板"),
	FIFTH(6, "五次来板"),
	SIXTH(7, "六次来板");
	
	private int code;
	private String descr;
	
	private InstorageOrderTypeEnum(int code, String descr) {
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
