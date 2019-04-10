package com.pcbwx.pcbmis.enums;
/**
 * COL工单状态
 * 
 * @author 孙贺宇
 *
 */
public enum ColOrderEnum {
	
	PRODUCT(1, "已投产"),
	INVALID(2, "已作废"),
	TIME_OUT(3, "暂停"),
	CHANGE_REVIEW(4, "变更审核中"),
	TENTATIVE_PRODUCTION(5, "暂停后再生产");
	
	private int code;
	private String descr;
	
	private ColOrderEnum(int code, String descr) {
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
