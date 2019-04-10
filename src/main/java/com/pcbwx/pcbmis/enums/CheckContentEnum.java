package com.pcbwx.pcbmis.enums;

/**
 * 检验内容枚举类
 * 
 * @author 孙贺宇
 *
 */
public enum CheckContentEnum {
	
	CHECK(1, "检验"),
	REPORT(2, "报告"),
	CERTIFICATION(3, "合格证"),
	TAG(4, "标签");
	
	private int code;
	private String descr;
	
	private CheckContentEnum(int code, String descr) {
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
