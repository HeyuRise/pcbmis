package com.pcbwx.pcbmis.bean.channel;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pcbwx.pcbmis.annotation.FieldMap;
import com.pcbwx.pcbmis.map.String2Date;

public class ColOrderChannel {
	
	@JsonProperty("order_number")
	@FieldMap
	private String orderNumber;
	
	@JsonProperty("company_code")
	@FieldMap
    private String companyCode;
	
	@JsonProperty("col_board_name")
	@FieldMap
    private String colBoardName;
	
	@JsonProperty("guest_code")
	@FieldMap
    private String guestCode;
	
	@JsonProperty("contact_code")
	@FieldMap
    private String contactCode;
	
	@JsonProperty("contact_name")
	@FieldMap
    private String contactName;
	
	@JsonProperty("market_department_code")
	@FieldMap
    private String marketDepartmentCode;
	
	@JsonProperty("factory_name")
	@FieldMap
    private String factoryName;
	
	@JsonProperty("cs_code")
	@FieldMap
    private String csCode;
	
	@JsonProperty("product_date")
	@FieldMap(using = String2Date.class)
    private String productDate;
	
	@JsonProperty("demand_delivery_date")
	@FieldMap(using = String2Date.class)
    private String demandDeliveryDate;
	
	@JsonProperty("state")
	@FieldMap
    private String state;
	
	@JsonProperty("amount")
	@FieldMap
    private Integer amount;
	
	@JsonProperty("surface_process_name")
	@FieldMap
    private String surfaceProcessName;
	
	@JsonProperty("counterbore_type_name")
	@FieldMap
    private String counterboreTypeName;
	
	@JsonProperty("board_long")
	@FieldMap
    private BigDecimal boardLong;
	
	@JsonProperty("board_wide")
	@FieldMap
    private BigDecimal boardWide;
	
	@JsonProperty("board_ply")
	@FieldMap
    private BigDecimal boardPly;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getColBoardName() {
		return colBoardName;
	}

	public void setColBoardName(String colBoardName) {
		this.colBoardName = colBoardName;
	}

	public String getGuestCode() {
		return guestCode;
	}

	public void setGuestCode(String guestCode) {
		this.guestCode = guestCode;
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

	public String getMarketDepartmentCode() {
		return marketDepartmentCode;
	}

	public void setMarketDepartmentCode(String marketDepartmentCode) {
		this.marketDepartmentCode = marketDepartmentCode;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getCsCode() {
		return csCode;
	}

	public void setCsCode(String csCode) {
		this.csCode = csCode;
	}

	public String getProductDate() {
		return productDate;
	}

	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}

	public String getDemandDeliveryDate() {
		return demandDeliveryDate;
	}

	public void setDemandDeliveryDate(String demandDeliveryDate) {
		this.demandDeliveryDate = demandDeliveryDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getSurfaceProcessName() {
		return surfaceProcessName;
	}

	public void setSurfaceProcessName(String surfaceProcessName) {
		this.surfaceProcessName = surfaceProcessName;
	}

	public String getCounterboreTypeName() {
		return counterboreTypeName;
	}

	public void setCounterboreTypeName(String counterboreTypeName) {
		this.counterboreTypeName = counterboreTypeName;
	}

	public BigDecimal getBoardLong() {
		return boardLong;
	}

	public void setBoardLong(BigDecimal boardLong) {
		this.boardLong = boardLong;
	}

	public BigDecimal getBoardWide() {
		return boardWide;
	}

	public void setBoardWide(BigDecimal boardWide) {
		this.boardWide = boardWide;
	}

	public BigDecimal getBoardPly() {
		return boardPly;
	}

	public void setBoardPly(BigDecimal boardPly) {
		this.boardPly = boardPly;
	}
    
}
