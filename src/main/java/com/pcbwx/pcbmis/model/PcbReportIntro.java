package com.pcbwx.pcbmis.model;

import java.util.Date;

public class PcbReportIntro {
    private Integer id;

    private String reportNum;

    private String orderNum;

    private String guestCode;

    private String boardName;

    private String number;

    private String unit;

    private String productPeriod;

    private String acceptanceStandard;

    private Date dispatchDate;

    private String serialNumber;

    private String audit;

    private String newDateKey;

    private String newDateValue;

    private Date creatTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportNum() {
        return reportNum;
    }

    public void setReportNum(String reportNum) {
        this.reportNum = reportNum == null ? null : reportNum.trim();
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public String getGuestCode() {
        return guestCode;
    }

    public void setGuestCode(String guestCode) {
        this.guestCode = guestCode == null ? null : guestCode.trim();
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName == null ? null : boardName.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getProductPeriod() {
        return productPeriod;
    }

    public void setProductPeriod(String productPeriod) {
        this.productPeriod = productPeriod == null ? null : productPeriod.trim();
    }

    public String getAcceptanceStandard() {
        return acceptanceStandard;
    }

    public void setAcceptanceStandard(String acceptanceStandard) {
        this.acceptanceStandard = acceptanceStandard == null ? null : acceptanceStandard.trim();
    }

    public Date getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(Date dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit == null ? null : audit.trim();
    }

    public String getNewDateKey() {
        return newDateKey;
    }

    public void setNewDateKey(String newDateKey) {
        this.newDateKey = newDateKey == null ? null : newDateKey.trim();
    }

    public String getNewDateValue() {
        return newDateValue;
    }

    public void setNewDateValue(String newDateValue) {
        this.newDateValue = newDateValue == null ? null : newDateValue.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}