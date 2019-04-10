package com.pcbwx.pcbmis.bean.user;

import java.util.List;

public class UserRoleEnableNew {
	private String enable;
	private List<Integer> userRoleId;
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public List<Integer> getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(List<Integer> userRoleId) {
		this.userRoleId = userRoleId;
	}

}
