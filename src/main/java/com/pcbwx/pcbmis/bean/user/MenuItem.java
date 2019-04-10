package com.pcbwx.pcbmis.bean.user;

import java.util.List;

public class MenuItem {
	private String title;
	private List<MenuAndUrl> secondMenu;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<MenuAndUrl> getSecondMenu() {
		return secondMenu;
	}
	public void setSecondMenu(List<MenuAndUrl> secondMenu) {
		this.secondMenu = secondMenu;
	}
	
}
