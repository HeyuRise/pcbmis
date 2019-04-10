package com.pcbwx.pcbmis.bean;

import java.util.List;

public class ReceiveProductBody {
	private String receiveDate;
	private List<Integer> contentIds;
	public String getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	public List<Integer> getContentIds() {
		return contentIds;
	}
	public void setContentIds(List<Integer> contentIds) {
		this.contentIds = contentIds;
	}
	
}
