package com.pcbwx.pcbmis.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SerialNumber {
	@JsonProperty("document_number")
	private String documentNumber;		// 编号

	@JsonProperty("sn")
	private String sn;		// 流水号

	@JsonProperty("state_code")
	private String stateCode;	
	
	@JsonProperty("revision")
	private String revision;	// 版次

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}
}
