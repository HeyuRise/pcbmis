package com.pcbwx.pcbmis.model;

import java.util.Date;

public class OutStorageOrder {
    private Integer id;

    private Integer orderId;

    private String state;

    private String productType;

    private Date outStorageDate;

    private Integer outSum;

    private String orderNumber;

    private String boardName;

    private Boolean isSuccess;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Date getOutStorageDate() {
        return outStorageDate;
    }

    public void setOutStorageDate(Date outStorageDate) {
        this.outStorageDate = outStorageDate;
    }

    public Integer getOutSum() {
        return outSum;
    }

    public void setOutSum(Integer outSum) {
        this.outSum = outSum;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
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