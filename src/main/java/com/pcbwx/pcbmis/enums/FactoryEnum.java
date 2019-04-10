package com.pcbwx.pcbmis.enums;

/**
 * 角色枚举类
 * @author 王海龙
 * @version 1.0
 *
 */
public enum FactoryEnum {

	JN(2, "JN所");
	
	private int code;
	private String descr;
	
	private FactoryEnum(int code, String descr) {
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
