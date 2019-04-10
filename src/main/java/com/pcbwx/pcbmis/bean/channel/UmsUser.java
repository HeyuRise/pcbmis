package com.pcbwx.pcbmis.bean.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pcbwx.pcbmis.annotation.FieldMap;
import com.pcbwx.pcbmis.map.Boolean2Integer;
import com.pcbwx.pcbmis.map.String2DateTime;

public class UmsUser {
	@JsonProperty("id")
	@FieldMap
	private Integer innerId;	// 主键ID

	@JsonProperty("code")
	@FieldMap
	private String innerCode;	// 系统code
	
	@JsonProperty("account")
	@FieldMap
	private String account;		// 账号
	
	@JsonProperty("hashed_password")
	@FieldMap
	private String hashedPassword;		// 密码
	
	@JsonProperty("enabled")
	@FieldMap(using = Boolean2Integer.class)
	private Boolean enable;	// 是否启用
	
//	@JsonProperty("last_login_at")
//	private Date lastLoginTime;		// 上次登录时间
//	@JsonProperty("createdBy_code")
//	private String creatorCode;	// 创建人系统编号	
//	@JsonProperty("updatedBy_code")
//	private String updatedCode;	// 更新人系统编号
	
	@JsonProperty("created_at")
	@FieldMap(using = String2DateTime.class)
	private String innerCreateTime;	// 创建时间
	
	@JsonProperty("updated_at")
	@FieldMap(using = String2DateTime.class)
	private String innrtUpdateTime;	// 更新时间
	
	@JsonProperty("name")
	@FieldMap
	private String username;	// 用户名称
	
//	@JsonProperty("code")
//	private String code;	// 系统编号
	@JsonProperty("department_code")
	@FieldMap
	private String departmentCode;	// 所属部门的系统编号
	
	@JsonProperty("status_code")
	@FieldMap
	private String statusCode;	// 所属就职状态的系统编号
	
	@JsonProperty("jobNumber")
	@FieldMap
	private String userCode;	// 工号
	
//	@JsonProperty("job_code")
//	private String jobCode;	// 所属职位的系统编号
	
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
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
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
	public String getInnrtUpdateTime() {
		return innrtUpdateTime;
	}
	public void setInnrtUpdateTime(String innrtUpdateTime) {
		this.innrtUpdateTime = innrtUpdateTime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
}
