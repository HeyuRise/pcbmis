package com.pcbwx.pcbmis.bean.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pcbwx.pcbmis.annotation.FieldMap;
import com.pcbwx.pcbmis.map.Boolean2Integer;
import com.pcbwx.pcbmis.map.String2DateTime;

/**
 * 字符
 * @author wanghl
 *
 */
public class PcbCharacter {
	@JsonProperty("id")
	@FieldMap
	private Integer innerId;	// 主键ID

	@JsonProperty("code")
	@FieldMap
	private String innerCode;	// 
	
	@JsonProperty("name")
	@FieldMap
	private String valueStr;	// 参数名称
	
	@JsonProperty("num")
	@FieldMap
	private Integer valueInt;	// 
	
	@JsonProperty("enable")
	@FieldMap(using = Boolean2Integer.class)
	private Boolean enable;	// 是否启用
	
	@JsonProperty("created_at")
	@FieldMap(using = String2DateTime.class)
	private String innerCreateTime; // 创建时间
	
	@JsonProperty("updated_at")
	@FieldMap(using = String2DateTime.class)
	private String innerUpdateTime;	//	更新时间

	public Integer getInnerId() {
		return innerId;
	}

	public void setInnerId(Integer innerId) {
		this.innerId = innerId;
	}

	public String getInnerCode() {
		return innerCode;
	}

	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
	}

	public String getValueStr() {
		return valueStr;
	}

	public void setValueStr(String valueStr) {
		this.valueStr = valueStr;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Integer getValueInt() {
		return valueInt;
	}

	public void setValueInt(Integer valueInt) {
		this.valueInt = valueInt;
	}

	public String getInnerCreateTime() {
		return innerCreateTime;
	}

	public void setInnerCreateTime(String innerCreateTime) {
		this.innerCreateTime = innerCreateTime;
	}

	public String getInnerUpdateTime() {
		return innerUpdateTime;
	}

	public void setInnerUpdateTime(String innerUpdateTime) {
		this.innerUpdateTime = innerUpdateTime;
	}
}
