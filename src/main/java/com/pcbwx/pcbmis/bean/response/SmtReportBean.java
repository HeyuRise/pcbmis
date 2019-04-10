package com.pcbwx.pcbmis.bean.response;

public class SmtReportBean {

	private Integer id;
	
	private String serialNumber;
	
	private String orderNumber;
	
	private String guestName;
	
	private Integer productNum;
	
	private String boardName;
	
	private String contactName;
	
	private String productDate;
	
	private String inspector;
	
	private String reInspector;
	
	private String orderStatus;
	
	private String reportStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public Integer getProductNum() {
		return productNum;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getProductDate() {
		return productDate;
	}

	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getReInspector() {
		return reInspector;
	}

	public void setReInspector(String reInspector) {
		this.reInspector = reInspector;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}

	@Override
	public String toString() {
		return "SmtReportBean [id=" + id + ", serialNumber=" + serialNumber + ", orderNumber=" + orderNumber
				+ ", guestName=" + guestName + ", productNum=" + productNum + ", boardName=" + boardName
				+ ", contactName=" + contactName + ", productDate=" + productDate + ", inspector=" + inspector
				+ ", reInspector=" + reInspector + ", orderStatus=" + orderStatus + ", reportStatus=" + reportStatus
				+ "]";
	}
	
}
