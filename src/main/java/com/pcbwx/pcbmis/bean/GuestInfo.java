package com.pcbwx.pcbmis.bean;

public class GuestInfo {
	private String guestCode;
	private String guestName;
	private Integer isSubscribed;		//1-已关注 0-未关注
	public String getGuestCode() {
		return guestCode;
	}
	public void setGuestCode(String guestCode) {
		this.guestCode = guestCode;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public Integer getIsSubscribed() {
		return isSubscribed;
	}
	public void setIsSubscribed(Integer isSubscribed) {
		this.isSubscribed = isSubscribed;
	}
	
	
}
