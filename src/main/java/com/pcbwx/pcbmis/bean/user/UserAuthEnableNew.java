package com.pcbwx.pcbmis.bean.user;

import java.util.List;

public class UserAuthEnableNew {
	private String roleName;
	private String roleEnable;
	private List<Integer> userAuthId;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleEnable() {
		return roleEnable;
	}
	public void setRoleEnable(String roleEnable) {
		this.roleEnable = roleEnable;
	}
	public List<Integer> getUserAuthId() {
		return userAuthId;
	}
	public void setUserAuthId(List<Integer> userAuthId) {
		this.userAuthId = userAuthId;
	}

}
