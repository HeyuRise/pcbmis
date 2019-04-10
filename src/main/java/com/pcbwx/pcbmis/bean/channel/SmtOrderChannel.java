package com.pcbwx.pcbmis.bean.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pcbwx.pcbmis.annotation.FieldMap;
import com.pcbwx.pcbmis.map.String2Date;

public class SmtOrderChannel {

	@JsonProperty("order_number")
	@FieldMap
	private String orderNumber;

	@JsonProperty("serial_number")
	@FieldMap
    private String serialNumber;
	
	@JsonProperty("board_name")
	@FieldMap
    private String boardName;
	
	@JsonProperty("guest_code")
	@FieldMap
    private String guestCode;
	
	@JsonProperty("company_code")
	@FieldMap
	private String companyCode;

	@JsonProperty("contact_code")
	@FieldMap
    private String contactCode;

	@JsonProperty("contact_name")
	@FieldMap
    private String contactName;

	@JsonProperty("department_code")
	@FieldMap
    private String departmentCode;
	
	@JsonProperty("factory_name")
	@FieldMap
    private String factoryName;
	
	@JsonProperty("creater_code")
	@FieldMap
    private String createrCode;
	
	@JsonProperty("product_date")
	@FieldMap(using = String2Date.class)
    private String productDate;
	
	@JsonProperty("deliver_date")
	@FieldMap(using = String2Date.class)
    private String deliverDate;
	
	@JsonProperty("state")
	@FieldMap
    private String state;
	
	@JsonProperty("smt_amount")
	@FieldMap
    private Integer smtAmount;
	
	@JsonProperty("grade_name")
	@FieldMap
    private String gradeName;
	
	@JsonProperty("disposal_way_code")
	@FieldMap
    private String disposalWayCode;
	
	@JsonProperty("shipment_notes")
	@FieldMap
    private String shipmentNotes;
	
	@JsonProperty("pcb_batch_number")
	@FieldMap
    private String pcbBatchNumber;
	
	@JsonProperty("x_ray")
	@FieldMap
    private Integer xRay;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getGuestCode() {
		return guestCode;
	}

	public void setGuestCode(String guestCode) {
		this.guestCode = guestCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getContactCode() {
		return contactCode;
	}

	public void setContactCode(String contactCode) {
		this.contactCode = contactCode;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getCreaterCode() {
		return createrCode;
	}

	public void setCreaterCode(String createrCode) {
		this.createrCode = createrCode;
	}

	public String getProductDate() {
		return productDate;
	}

	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}

	public String getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(String deliverDate) {
		this.deliverDate = deliverDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getSmtAmount() {
		return smtAmount;
	}

	public void setSmtAmount(Integer smtAmount) {
		this.smtAmount = smtAmount;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getDisposalWayCode() {
		return disposalWayCode;
	}

	public void setDisposalWayCode(String disposalWayCode) {
		this.disposalWayCode = disposalWayCode;
	}

	public String getShipmentNotes() {
		return shipmentNotes;
	}

	public void setShipmentNotes(String shipmentNotes) {
		this.shipmentNotes = shipmentNotes;
	}

	public String getPcbBatchNumber() {
		return pcbBatchNumber;
	}

	public void setPcbBatchNumber(String pcbBatchNumber) {
		this.pcbBatchNumber = pcbBatchNumber;
	}

	public Integer getxRay() {
		return xRay;
	}

	public void setxRay(Integer xRay) {
		this.xRay = xRay;
	}

}
