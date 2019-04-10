package com.pcbwx.pcbmis.model;

import java.util.Date;

public class ColReport {
    private Integer id;

    private Integer orderId;

    private String orderNumber;

    private Integer status;

    private String inspector;

    private String inspectorUserCode;

    private String reInspector;

    private String reInspectorUserCode;

    private Integer outSum;

    private Date outStorageDate;

    private String serialNumber;

    private String revision;

    private String documentNumber;

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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public String getInspectorUserCode() {
        return inspectorUserCode;
    }

    public void setInspectorUserCode(String inspectorUserCode) {
        this.inspectorUserCode = inspectorUserCode;
    }

    public String getReInspector() {
        return reInspector;
    }

    public void setReInspector(String reInspector) {
        this.reInspector = reInspector;
    }

    public String getReInspectorUserCode() {
        return reInspectorUserCode;
    }

    public void setReInspectorUserCode(String reInspectorUserCode) {
        this.reInspectorUserCode = reInspectorUserCode;
    }

    public Integer getOutSum() {
        return outSum;
    }

    public void setOutSum(Integer outSum) {
        this.outSum = outSum;
    }

    public Date getOutStorageDate() {
        return outStorageDate;
    }

    public void setOutStorageDate(Date outStorageDate) {
        this.outStorageDate = outStorageDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
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