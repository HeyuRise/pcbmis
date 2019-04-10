/**
 * 
 */
package com.pcbwx.pcbmis.enums;

/**
 * 工单类型类型枚举类
 * 
 * @author 孙贺宇
 *
 */
public enum OutStorageOrderTypeEnum {
	SMT("SMT", "SMT"), 
	COL("COL", "COL"), 
	PCB("PCB", "PCB");

	private String code; 
	private String desc; 

	private OutStorageOrderTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
