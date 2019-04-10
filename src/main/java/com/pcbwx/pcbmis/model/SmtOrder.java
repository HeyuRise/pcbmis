package com.pcbwx.pcbmis.model;

import java.util.Date;

public class SmtOrder {
    private Integer id;

    private String orderNumber;

    private String serialNumber;

    private String boardName;

    private String guestCode;

    private String companyCode;

    private String contactCode;

    private String contactName;

    private String departmentCode;

    private String factoryName;

    private String createrCode;

    private Date productDate;

    private Date deliverDate;

    private String state;

    private Integer smtAmount;

    private String gradeName;

    private String disposalWayCode;

    private String shipmentNotes;

    private String pcbBatchNumber;

    private Integer xRay;

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

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
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