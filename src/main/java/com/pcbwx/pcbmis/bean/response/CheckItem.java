package com.pcbwx.pcbmis.bean.response;

public class CheckItem {
	private String require;
	private String boardTolerance;
	private String result;
	private String badNum;
	private String judgeResult;
	public String getRequire() {
		return require;
	}
	public void setRequire(String require) {
		this.require = require;
	}
	public String getBoardTolerance() {
		return boardTolerance;
	}
	public void setBoardTolerance(String boardTolerance) {
		this.boardTolerance = boardTolerance;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getBadNum() {
		return badNum;
	}
	public void setBadNum(String badNum) {
		this.badNum = badNum;
	}
	public String getJudgeResult() {
		return judgeResult;
	}
	public void setJudgeResult(String judgeResult) {
		this.judgeResult = judgeResult;
	}
}
