package com.pcbwx.pcbmis.enums;

/**
 * 工单类型
 * 
 * @author 孙贺宇
 *
 */
public enum OrderTypeEnum {
	PRODUCT_ORDER("ProductOrder", "工单"),
	OUT_IN_STORAGE_INFORMATION("OutInStorageInformation", ""),
	JOIN_BOARD_ORDER("JoinBoardOrder", "子板");

	private String code;
	private String descr;
	
	private OrderTypeEnum(String code, String descr) {
		this.code = code;
		this.descr = descr;
	}

	public String getCode() {
		return code;
	}

	public String getDescr() {
		return descr;
	}

}
