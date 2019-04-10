package com.pcbwx.pcbmis.model;

import java.util.Date;

public class PcbReceiveOrder {
    private Integer id;

    private String orderNum;

    private String orderNumber;

    private String checkNum;

    private String reportNum;

    private Integer factoryId;

    private Integer categoryGradeId;

    private Integer productionNumSet;

    private Integer productionNumPcs;

    private String contentId;

    private Integer spotCheckNumPcs;

    private Integer receiveType;

    private Date receiveTime;

    private String receiver;

    private Boolean enable;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum == null ? null : checkNum.trim();
    }

    public String getReportNum() {
        return reportNum;
    }

    public void setReportNum(String reportNum) {
        this.reportNum = reportNum == null ? null : reportNum.trim();
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public Integer getCategoryGradeId() {
        return categoryGradeId;
    }

    public void setCategoryGradeId(Integer categoryGradeId) {
        this.categoryGradeId = categoryGradeId;
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

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId == null ? null : contentId.trim();
    }

    public Integer getSpotCheckNumPcs() {
        return spotCheckNumPcs;
    }

    public void setSpotCheckNumPcs(Integer spotCheckNumPcs) {
        this.spotCheckNumPcs = spotCheckNumPcs;
    }

    public Integer getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(Integer receiveType) {
        this.receiveType = receiveType;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
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