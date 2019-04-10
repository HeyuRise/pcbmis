/**
 * 
 */
package com.pcbwx.pcbmis.enums;

/**
 * 模板类型枚举类
 * 
 * @author 孙贺宇
 *
 */
public enum TemplateBaseIdEnum {
	IPC_A_600H(1, "IPC-A-600H"),
	IPC_6013C(2, "IPC-6013C"),
	GJB_362B_2009(3, "GJB 362B-2009"),
	GJB_7548_2012(4, "GJB 7548-2012");

	private Integer code; // 日志代码
	private String desc; // 日志描述

	private TemplateBaseIdEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
