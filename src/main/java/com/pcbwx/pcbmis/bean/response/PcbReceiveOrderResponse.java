package com.pcbwx.pcbmis.bean.response;

import java.util.List;

public class PcbReceiveOrderResponse {
	private Integer id;
	private String orderNum;		
	private String guestName;
	private String factoryName;
	private String grade;
	private String size;
	private Integer productNumSET;
	private Integer productNumPCS;
	private String content;
	private Integer receiveNum;
	private String receiveType;
	private String receiveDate;
	private String receiveTime;
	private String receiver;
	
	private Integer receiveTypeId;
	private List<Integer> contentIds;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Integer getProductNumSET() {
		return productNumSET;
	}
	public void setProductNumSET(Integer productNumSET) {
		this.productNumSET = productNumSET;
	}
	public Integer getProductNumPCS() {
		return productNumPCS;
	}
	public void setProductNumPCS(Integer productNumPCS) {
		this.productNumPCS = productNumPCS;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getReceiveNum() {
		return receiveNum;
	}
	public void setReceiveNum(Integer receiveNum) {
		this.receiveNum = receiveNum;
	}
	public String getReceiveType() {
		return receiveType;
	}
	public void setReceiveType(String receiveType) {
		this.receiveType = receiveType;
	}
	public String getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public Integer getReceiveTypeId() {
		return receiveTypeId;
	}
	public void setReceiveTypeId(Integer receiveTypeId) {
		this.receiveTypeId = receiveTypeId;
	}
	public List<Integer> getContentIds() {
		return contentIds;
	}
	public void setContentIds(List<Integer> contentIds) {
		this.contentIds = contentIds;
	}
	
}
