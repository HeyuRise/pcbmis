package com.pcbwx.pcbmis.bean.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pcbwx.pcbmis.annotation.FieldMap;
import com.pcbwx.pcbmis.map.Boolean2Integer;
import com.pcbwx.pcbmis.map.String2Date;

public class UmsDepartment {
//    private Integer id;

	@JsonProperty("code")
	@FieldMap
    private String departmentCode;

	@JsonProperty("name")
	@FieldMap
    private String departmentName;	// 部门名称

	@JsonProperty("isActive")
	@FieldMap(using = Boolean2Integer.class)
    private Boolean enable;

	@JsonProperty("created_at")
	@FieldMap(using = String2Date.class)
    private String innerCreateTime;

	@JsonProperty("updated_at")
	@FieldMap(using = String2Date.class)
    private String innerUpdateTime;

//	@JsonProperty("cad_enabled")
//    private Boolean cadEnabled;		// 是否为cad部门

//    private String orgCode;
	
	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
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