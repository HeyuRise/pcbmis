package com.pcbwx.pcbmis.bean.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pcbwx.pcbmis.annotation.FieldMap;
import com.pcbwx.pcbmis.map.Boolean2Integer;
import com.pcbwx.pcbmis.map.String2DateTime;

public class PcbCompany {
	@JsonProperty("id")
	@FieldMap
	private Integer innerId;	// 主键ID

	@JsonProperty("code")
	@FieldMap
	private String companyCode;	// 
	
	@JsonProperty("org_code")
	@FieldMap
	private String orgCode;	// 
	
	@JsonProperty("isActive")
	@FieldMap(using = Boolean2Integer.class)
	private Boolean isActive;	// 是否启用
	
	@JsonProperty("name")
	@FieldMap
	private String companyName;	// 公司名称
	
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

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
