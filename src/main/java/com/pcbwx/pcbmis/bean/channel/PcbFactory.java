package com.pcbwx.pcbmis.bean.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pcbwx.pcbmis.annotation.FieldMap;
import com.pcbwx.pcbmis.map.Boolean2Integer;
import com.pcbwx.pcbmis.map.String2DateTime;

public class PcbFactory {
	@JsonProperty("id")
	@FieldMap
	private Integer innerId;	// 主键ID
	
	@JsonProperty("recipient")
	@FieldMap
	private String recipient;	// 参数名称

	@JsonProperty("name")
	@FieldMap
	private String factoryName;	// 参数名称
	
	@JsonProperty("enable")
	@FieldMap(using = Boolean2Integer.class)
	private Boolean enable;	// 是否启用
	
	@JsonProperty("e_mail")
	@FieldMap
	private String email;	// 邮箱
	
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

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
