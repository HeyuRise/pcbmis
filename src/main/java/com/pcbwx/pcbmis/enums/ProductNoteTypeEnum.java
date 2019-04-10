package com.pcbwx.pcbmis.enums;

/**
 * 生产备注类型
 * 
 * @author 孙贺宇
 *
 */
public enum ProductNoteTypeEnum {
	
	IN(0, "生产备注内部"),
	OUT(1, "生产备注外部");
	
	private int code;
	private String descr;
	
	private ProductNoteTypeEnum(int code, String descr) {
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
