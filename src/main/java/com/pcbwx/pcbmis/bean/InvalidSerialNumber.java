package com.pcbwx.pcbmis.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvalidSerialNumber {

	@JsonProperty("status")
	private String status;
	
	@JsonProperty("msg")
	private String msg;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "InvalidSerialNumber [status=" + status + ", msg=" + msg + "]";
	}
	
}
