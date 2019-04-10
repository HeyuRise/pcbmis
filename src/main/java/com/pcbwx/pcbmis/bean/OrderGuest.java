package com.pcbwx.pcbmis.bean;

import com.pcbwx.pcbmis.model.EdaGuest;

public class OrderGuest {

    private String guestCode;

    private String guestName;

    private String shortNameCn;
    
    private String sellerEmail;

    public OrderGuest(EdaGuest edaGuest) {
		this.setGuestCode(edaGuest.getGuestCode());
		this.setGuestName(edaGuest.getGuestName());
		this.setShortNameCn(edaGuest.getShortNameCn());
//		this.setShortNameCn(edaGuest.getGuestName());
		this.setSellerEmail(edaGuest.getSellerEmail());    	
    }
    
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

	public String getShortNameCn() {
		return shortNameCn;
	}

	public void setShortNameCn(String shortNameCn) {
		this.shortNameCn = shortNameCn;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

}
