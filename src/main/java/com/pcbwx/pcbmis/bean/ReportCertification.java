package com.pcbwx.pcbmis.bean;

import java.util.List;

public class ReportCertification {
	private String categoryGrade;
	private String orderNumName;
	private String orderNum;
	private String boardName;
	private String productDate;
	private String checkDate;
	private String packagingDate;
	private String inspector;
	private String expirationDate;
	private String numberPcs;
	private String batchNumber;
	private String size;
	private List<CertificationExtra> add;
	public String getCategoryGrade() {
		return categoryGrade;
	}
	public void setCategoryGrade(String categoryGrade) {
		this.categoryGrade = categoryGrade;
	}
	public String getOrderNumName() {
		return orderNumName;
	}
	public void setOrderNumName(String orderNumName) {
		this.orderNumName = orderNumName;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
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
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getPackagingDate() {
		return packagingDate;
	}
	public void setPackagingDate(String packagingDate) {
		this.packagingDate = packagingDate;
	}
	public String getInspector() {
		return inspector;
	}
	public void setInspector(String inspector) {
		this.inspector = inspector;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getNumberPcs() {
		return numberPcs;
	}
	public void setNumberPcs(String numberPcs) {
		this.numberPcs = numberPcs;
	}
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public List<CertificationExtra> getAdd() {
		return add;
	}
	public void setAdd(List<CertificationExtra> add) {
		this.add = add;
	}
	
}
