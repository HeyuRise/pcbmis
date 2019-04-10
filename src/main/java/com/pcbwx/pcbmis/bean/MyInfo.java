package com.pcbwx.pcbmis.bean;

public class MyInfo {
	private String userName;
	private String departmentCode;
	private String departmentName;
	private String mobile;
	private String email;
	private int guestNum;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getGuestNum() {
		return guestNum;
	}
	public void setGuestNum(int guestNum) {
		this.guestNum = guestNum;
	}
}
