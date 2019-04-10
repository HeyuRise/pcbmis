package com.pcbwx.pcbmis.bean;

public class CertificateInfo {
	private String packageDate;			// 包装日期
	private String expirationDate;		// 保质期
	private String batchNumber;			// 批号
	private String orderNumName;		// 工单号前缀名字
	public String getPackageDate() {
		return packageDate;
	}
	public void setPackageDate(String packageDate) {
		this.packageDate = packageDate;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	public String getOrderNumName() {
		return orderNumName;
	}
	public void setOrderNumName(String orderNumName) {
		this.orderNumName = orderNumName;
	}
}
