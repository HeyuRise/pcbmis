package com.pcbwx.pcbmis.bean;

public class BaseReportInfo {
	private String reportNum;				//报告单号
	private String orderNum;				//工单号
	private String boardName;				//板名
    private String guestName;				//客户
    private String factoryName;				//生产厂家
    private String categoryGrade;			//等级
    private String craft;					//工艺
    private String metallography_require;	//金相要求
    private String productDate;				//投产日期
    private Integer productionNumSet;		//生产数量SET
    private Integer productionNumPcs;		//生产数量PCS
    private Integer amountCheckoutPcs;		//来料数量（块）
    private Integer spotCheckNumPcs;		//抽样数量（块）
    private String deliveryMan;				//送板人
    private String deliveryDate;            //送板时间
	public String getReportNum() {
		return reportNum;
	}
	public void setReportNum(String reportNum) {
		this.reportNum = reportNum;
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
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public String getCategoryGrade() {
		return categoryGrade;
	}
	public void setCategoryGrade(String categoryGrade) {
		this.categoryGrade = categoryGrade;
	}
	public String getCraft() {
		return craft;
	}
	public void setCraft(String craft) {
		this.craft = craft;
	}
	public String getMetallography_require() {
		return metallography_require;
	}
	public void setMetallography_require(String metallography_require) {
		this.metallography_require = metallography_require;
	}
	public String getProductDate() {
		return productDate;
	}
	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}
	public Integer getProductionNumSet() {
		return productionNumSet;
	}
	public void setProductionNumSet(Integer productionNumSet) {
		this.productionNumSet = productionNumSet;
	}
	public Integer getProductionNumPcs() {
		return productionNumPcs;
	}
	public void setProductionNumPcs(Integer productionNumPcs) {
		this.productionNumPcs = productionNumPcs;
	}
	public Integer getAmountCheckoutPcs() {
		return amountCheckoutPcs;
	}
	public void setAmountCheckoutPcs(Integer amountCheckoutPcs) {
		this.amountCheckoutPcs = amountCheckoutPcs;
	}
	public Integer getSpotCheckNumPcs() {
		return spotCheckNumPcs;
	}
	public void setSpotCheckNumPcs(Integer spotCheckNumPcs) {
		this.spotCheckNumPcs = spotCheckNumPcs;
	}
	public String getDeliveryMan() {
		return deliveryMan;
	}
	public void setDeliveryMan(String deliveryMan) {
		this.deliveryMan = deliveryMan;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

}
