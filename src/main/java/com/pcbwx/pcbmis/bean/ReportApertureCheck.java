package com.pcbwx.pcbmis.bean;

public class ReportApertureCheck {
	private Integer ID;
    private String pN;
    private String require;
    private String requireTolerance;
    private String realCheck;
    private String judge;
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getpN() {
		return pN;
	}
	public void setpN(String pN) {
		this.pN = pN;
	}
	public String getRequire() {
		return require;
	}
	public void setRequire(String require) {
		this.require = require;
	}
	public String getRequireTolerance() {
		return requireTolerance;
	}
	public void setRequireTolerance(String requireTolerance) {
		this.requireTolerance = requireTolerance;
	}
	public String getRealCheck() {
		return realCheck;
	}
	public void setRealCheck(String realCheck) {
		this.realCheck = realCheck;
	}
	public String getJudge() {
		return judge;
	}
	public void setJudge(String judge) {
		this.judge = judge;
	}
    
}
