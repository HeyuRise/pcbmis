package com.pcbwx.pcbmis.bean.user;

import java.util.List;

public class UserAuthEnable {
	private String roleName;
	private Integer roleEnable;
	private List<UserAuthItem> authEnable;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getRoleEnable() {
		return roleEnable;
	}
	public void setRoleEnable(Integer roleEnable) {
		this.roleEnable = roleEnable;
	}
	public List<UserAuthItem> getAuthEnable() {
		return authEnable;
	}
	public void setAuthEnable(List<UserAuthItem> authEnable) {
		this.authEnable = authEnable;
	}

}
