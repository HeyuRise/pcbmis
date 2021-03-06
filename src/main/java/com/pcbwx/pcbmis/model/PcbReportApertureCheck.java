package com.pcbwx.pcbmis.model;

import java.util.Date;

public class PcbReportApertureCheck {
    private Integer id;

    private String reportNum;

    private String orderNum;

    private Integer apertureId;

    private String pn;

    private String checkRequire;

    private String checkRequireTolerance;

    private String realCheck;

    private Integer judge;

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

    public Integer getApertureId() {
        return apertureId;
    }

    public void setApertureId(Integer apertureId) {
        this.apertureId = apertureId;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn == null ? null : pn.trim();
    }

    public String getCheckRequire() {
        return checkRequire;
    }

    public void setCheckRequire(String checkRequire) {
        this.checkRequire = checkRequire == null ? null : checkRequire.trim();
    }

    public String getCheckRequireTolerance() {
        return checkRequireTolerance;
    }

    public void setCheckRequireTolerance(String checkRequireTolerance) {
        this.checkRequireTolerance = checkRequireTolerance == null ? null : checkRequireTolerance.trim();
    }

    public String getRealCheck() {
        return realCheck;
    }

    public void setRealCheck(String realCheck) {
        this.realCheck = realCheck == null ? null : realCheck.trim();
    }

    public Integer getJudge() {
        return judge;
    }

    public void setJudge(Integer judge) {
        this.judge = judge;
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