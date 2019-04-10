package com.pcbwx.pcbmis.bean;

import com.pcbwx.pcbmis.model.EdaContact;

public class OrderContact {

    private String contactName;
    private String contactCode;
    private String contactMobile;
    private String email;

    public OrderContact(EdaContact edaContact) {
    	this.setContactCode(edaContact.getContactCode());
    	this.setContactMobile(edaContact.getContactMobile());
    	this.setContactName(edaContact.getContactName());
    	this.setEmail(edaContact.getContactEmail());
    }
    
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactCode() {
		return contactCode;
	}

	public void setContactCode(String contactCode) {
		this.contactCode = contactCode;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
