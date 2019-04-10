package com.pcbwx.pcbmis.bean.response;

import java.util.List;

public class ColSizeWarpingBean {

	private String serialNumber;

	private String revision;

	private String documentNumber;

	private String guestName;

	private String boardName;

	private String productDate;

	private Integer productNum;

	private Integer samplingNum;
	
    private String inspector;

    private String reInspector;
    
    private String inspectDate;
    
    private String reInspectDate;
    
    private List<ColSizeWarpingItemBean> sizeWarpingItems;

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

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
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

	public String getProductDate() {
		return productDate;
	}

	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}

	public Integer getProductNum() {
		return productNum;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	public Integer getSamplingNum() {
		return samplingNum;
	}

	public void setSamplingNum(Integer samplingNum) {
		this.samplingNum = samplingNum;
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

	public String getInspectDate() {
		return inspectDate;
	}

	public void setInspectDate(String inspectDate) {
		this.inspectDate = inspectDate;
	}

	public String getReInspectDate() {
		return reInspectDate;
	}

	public void setReInspectDate(String reInspectDate) {
		this.reInspectDate = reInspectDate;
	}

	public List<ColSizeWarpingItemBean> getSizeWarpingItems() {
		return sizeWarpingItems;
	}

	public void setSizeWarpingItems(List<ColSizeWarpingItemBean> sizeWarpingItems) {
		this.sizeWarpingItems = sizeWarpingItems;
	}

	@Override
	public String toString() {
		return "ColSizeWarpingBean [serialNumber=" + serialNumber + ", revision=" + revision + ", documentNumber="
				+ documentNumber + ", guestName=" + guestName + ", boardName=" + boardName + ", productDate="
				+ productDate + ", productNum=" + productNum + ", samplingNum=" + samplingNum + "]";
	}
	
}
