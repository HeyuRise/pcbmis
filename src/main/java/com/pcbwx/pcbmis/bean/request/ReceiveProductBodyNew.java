package com.pcbwx.pcbmis.bean.request;

import java.util.List;

public class ReceiveProductBodyNew {
	private Integer inStoragePcs;
	private Integer receiveOrderType;
	private List<Integer> contentIds;
	public Integer getInStoragePcs() {
		return inStoragePcs;
	}
	public void setInStoragePcs(Integer inStoragePcs) {
		this.inStoragePcs = inStoragePcs;
	}
	public Integer getReceiveOrderType() {
		return receiveOrderType;
	}
	public void setReceiveOrderType(Integer receiveOrderType) {
		this.receiveOrderType = receiveOrderType;
	}
	public List<Integer> getContentIds() {
		return contentIds;
	}
	public void setContentIds(List<Integer> contentIds) {
		this.contentIds = contentIds;
	}
	
}
