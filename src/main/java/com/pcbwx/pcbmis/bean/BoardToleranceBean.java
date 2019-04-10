package com.pcbwx.pcbmis.bean;

import com.pcbwx.pcbmis.model.BoardTolerance;

public class BoardToleranceBean {
	private String minBoardlongTolerance;
    private String maxBoardlongTolerance;
    private String minBoardwideTolerance;
    private String maxBoardwideTolerance;
    private String minBoardplyTolerance;
    private String maxBoardplyTolerance;
	public BoardToleranceBean() {
		super();
	}
	public BoardToleranceBean(BoardTolerance boardTolerance) {
		this.minBoardlongTolerance = boardTolerance.getMinBoardlongTolerance();
		this.maxBoardlongTolerance = boardTolerance.getMaxBoardlongTolerance();
		this.minBoardwideTolerance = boardTolerance.getMinBoardwideTolerance();
		this.maxBoardwideTolerance = boardTolerance.getMaxBoardwideTolerance();
		this.minBoardplyTolerance = boardTolerance.getMinBoardplyTolerance();
		this.maxBoardplyTolerance = boardTolerance.getMaxBoardplyTolerance();
	}
	public String getMinBoardlongTolerance() {
		return minBoardlongTolerance;
	}
	public void setMinBoardlongTolerance(String minBoardlongTolerance) {
		this.minBoardlongTolerance = minBoardlongTolerance;
	}
	public String getMaxBoardlongTolerance() {
		return maxBoardlongTolerance;
	}
	public void setMaxBoardlongTolerance(String maxBoardlongTolerance) {
		this.maxBoardlongTolerance = maxBoardlongTolerance;
	}
	public String getMinBoardwideTolerance() {
		return minBoardwideTolerance;
	}
	public void setMinBoardwideTolerance(String minBoardwideTolerance) {
		this.minBoardwideTolerance = minBoardwideTolerance;
	}
	public String getMaxBoardwideTolerance() {
		return maxBoardwideTolerance;
	}
	public void setMaxBoardwideTolerance(String maxBoardwideTolerance) {
		this.maxBoardwideTolerance = maxBoardwideTolerance;
	}
	public String getMinBoardplyTolerance() {
		return minBoardplyTolerance;
	}
	public void setMinBoardplyTolerance(String minBoardplyTolerance) {
		this.minBoardplyTolerance = minBoardplyTolerance;
	}
	public String getMaxBoardplyTolerance() {
		return maxBoardplyTolerance;
	}
	public void setMaxBoardplyTolerance(String maxBoardplyTolerance) {
		this.maxBoardplyTolerance = maxBoardplyTolerance;
	}

}
