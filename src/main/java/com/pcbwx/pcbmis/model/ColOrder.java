package com.pcbwx.pcbmis.model;

import java.math.BigDecimal;
import java.util.Date;

public class ColOrder {
    private Integer id;

    private String orderNumber;

    private String companyCode;

    private String colBoardName;

    private String guestCode;

    private String contactCode;

    private String contactName;

    private String marketDepartmentCode;

    private String factoryName;

    private String csCode;

    private Date productDate;

    private Date demandDeliveryDate;

    private String state;

    private Integer amount;

    private String surfaceProcessName;

    private String counterboreTypeName;

    private BigDecimal boardLong;

    private BigDecimal boardWide;

    private BigDecimal boardPly;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Date getDemandDeliveryDate() {
        return demandDeliveryDate;
    }

    public void setDemandDeliveryDate(Date demandDeliveryDate) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}