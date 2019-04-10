package com.pcbwx.pcbmis.bean;

public class BaseOrderInfo {
	private String checkNum;				//检验单号
	private String orderNum;				//工单号
	private String boardName;				//板名
    private String guestName;				//客户
    private String factoryName;				//生产厂家
    private String productDate;				//投产日期
    private String categoryGrade;			//等级
    private String craft;					//工艺
    private String metallography_require;	//金相要求
    private String impedanceRequire;		//阻抗要求
    private Integer productionNumSet;		//生产数量SET
    private Integer productionNumPcs;		//生产数量PCS
    private Integer amountCheckoutPcs;		//来料数量（块）
    private Integer spotCheckNumPcs;		//抽样数量（块）
    private String deliveryMan;				//送板人
    private String deliveryDate;            //送板时间
    private String inspectionSpecification; //检验规范
    private String productionNote;			//生产备注
    private String deliveryNote;			//发货备注
    private String businessNote;			//商务备注
//    private String checkStanard;			//验收标准
    private String CAMGuide;				//CAM标准
	public String getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
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
	public String getProductDate() {
		return productDate;
	}
	public void setProductDate(String productDate) {
		this.productDate = productDate;
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
	public String getImpedanceRequire() {
		return impedanceRequire;
	}
	public void setImpedanceRequire(String impedanceRequire) {
		this.impedanceRequire = impedanceRequire;
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
	public String getInspectionSpecification() {
		return inspectionSpecification;
	}
	public void setInspectionSpecification(String inspectionSpecification) {
		this.inspectionSpecification = inspectionSpecification;
	}
	public String getProductionNote() {
		return productionNote;
	}
	public void setProductionNote(String productionNote) {
		this.productionNote = productionNote;
	}
	public String getDeliveryNote() {
		return deliveryNote;
	}
	public void setDeliveryNote(String deliveryNote) {
		this.deliveryNote = deliveryNote;
	}
	public String getBusinessNote() {
		return businessNote;
	}
	public void setBusinessNote(String businessNote) {
		this.businessNote = businessNote;
	}
//	public String getCheckStanard() {
//		return checkStanard;
//	}
//	public void setCheckStanard(String checkStanard) {
//		this.checkStanard = checkStanard;
//	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public String getCAMGuide() {
		return CAMGuide;
	}
	public void setCAMGuide(String cAMGuide) {
		CAMGuide = cAMGuide;
	}
}
