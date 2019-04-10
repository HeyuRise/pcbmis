package com.pcbwx.pcbmis.bean;

public class CheckHead {
	
	private String serialNumber;          // 流水号
	private String revision;			  // 版次
	private String document_number;		  // 编号
	private String productOrderNum;		  // 工单号
	private String checkStandard;		  // 验收标准
	private Integer inAmountSet;          // 来料数量
	private Integer spotCheckNum;         // 抽检数量
	private String guestName;			  // 顾客单位
	private String boardName;			  // 板名
	private Integer templateId;            // 模板Id
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getRevision() {
		return revision;
	}
	public void setRevision(String revision) {
		this.revision = revision;
	}
	public String getDocument_number() {
		return document_number;
	}
	public void setDocument_number(String document_number) {
		this.document_number = document_number;
	}
	public String getProductOrderNum() {
		return productOrderNum;
	}
	public void setProductOrderNum(String productOrderNum) {
		this.productOrderNum = productOrderNum;
	}
	public String getCheckStandard() {
		return checkStandard;
	}
	public void setCheckStandard(String checkStandard) {
		this.checkStandard = checkStandard;
	}
	public Integer getInAmountSet() {
		return inAmountSet;
	}
	public void setInAmountSet(Integer inAmountSet) {
		this.inAmountSet = inAmountSet;
	}
	public Integer getSpotCheckNum() {
		return spotCheckNum;
	}
	public void setSpotCheckNum(Integer spotCheckNum) {
		this.spotCheckNum = spotCheckNum;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
}
