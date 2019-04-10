package com.pcbwx.pcbmis.bean.user;

import java.util.List;

public class UserRoleEnable {
	private Integer enable;
	private List<UserRoleItem> roleEnable;
	public Integer getEnable() {
		return enable;
	}
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	public List<UserRoleItem> getRoleEnable() {
		return roleEnable;
	}
	public void setRoleEnable(List<UserRoleItem> roleEnable) {
		this.roleEnable = roleEnable;
	}
	
}
