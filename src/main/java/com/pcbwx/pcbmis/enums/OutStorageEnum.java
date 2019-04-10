package com.pcbwx.pcbmis.enums;

/**
 * 出库单推送状态
 * 
 * @author 孙贺宇
 * @since 2018-6-29
 * @version 1.0
 *
 */
public enum OutStorageEnum {
	
	CREATED("has_created","创建中"),
	REDLINED("has_redlined","作废"),
	DELIVERY("has_delivery","已物流出货");

	private String code;
	private String desc;
	
	private OutStorageEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
