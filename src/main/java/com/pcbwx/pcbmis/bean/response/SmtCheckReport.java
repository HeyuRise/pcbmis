package com.pcbwx.pcbmis.bean.response;

public class SmtCheckReport {
	
    private String serialNumber;

    private String revision;

    private String documentNumber;
    
    private String guestName;
    
    private String boardName;
    
    private String productDate;
    
    private Integer productNumRequire;
    
    private Integer productNumResult;
    
    private String batchNumberResult;
    
    private String xRayRequire;
    
    private String xRayResult;
    
    private Integer samplingNumRequire;
    
    private String samplingNumResult;
    
    private String inspector;

    private String reInspector;
    
    private String inspectDate;
    
    private String reInspectDate;

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

	public Integer getProductNumRequire() {
		return productNumRequire;
	}

	public void setProductNumRequire(Integer productNumRequire) {
		this.productNumRequire = productNumRequire;
	}

	public Integer getProductNumResult() {
		return productNumResult;
	}

	public void setProductNumResult(Integer productNumResult) {
		this.productNumResult = productNumResult;
	}

	public String getBatchNumberResult() {
		return batchNumberResult;
	}

	public void setBatchNumberResult(String batchNumberResult) {
		this.batchNumberResult = batchNumberResult;
	}

	public String getxRayRequire() {
		return xRayRequire;
	}

	public void setxRayRequire(String xRayRequire) {
		this.xRayRequire = xRayRequire;
	}

	public String getxRayResult() {
		return xRayResult;
	}

	public void setxRayResult(String xRayResult) {
		this.xRayResult = xRayResult;
	}

	public Integer getSamplingNumRequire() {
		return samplingNumRequire;
	}

	public void setSamplingNumRequire(Integer samplingNumRequire) {
		this.samplingNumRequire = samplingNumRequire;
	}

	public String getSamplingNumResult() {
		return samplingNumResult;
	}

	public void setSamplingNumResult(String samplingNumResult) {
		this.samplingNumResult = samplingNumResult;
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

	@Override
	public String toString() {
		return "SmtCheckReport [serialNumber=" + serialNumber + ", revision=" + revision + ", documentNumber="
				+ documentNumber + ", guestName=" + guestName + ", boardName=" + boardName + ", productDate="
				+ productDate + ", productNumRequire=" + productNumRequire + ", productNumResult=" + productNumResult
				+ ", batchNumberResult=" + batchNumberResult + ", xRayRequire=" + xRayRequire + ", xRayResult="
				+ xRayResult + ", samplingNumRequire=" + samplingNumRequire + ", samplingNumResult=" + samplingNumResult
				+ ", inspector=" + inspector + ", reInspector=" + reInspector + ", inspectDate=" + inspectDate
				+ ", reInspectDate=" + reInspectDate + "]";
	}
    
}
