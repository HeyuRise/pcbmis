package com.pcbwx.pcbmis.model;

import java.util.Date;

public class PcbReportVcut {
    private Integer id;

    private String reportNum;

    private String orderNum;

    private String requireAngle;

    private String requireH;

    private String requireB;

    private String requireBtolerance;

    private String realCheakAngle;

    private String realCheckH;

    private String realCheckB;

    private Integer judgeAngle;

    private Integer judgeH;

    private Integer judgeB;

    private Date ceateTime;

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

    public String getRequireAngle() {
        return requireAngle;
    }

    public void setRequireAngle(String requireAngle) {
        this.requireAngle = requireAngle == null ? null : requireAngle.trim();
    }

    public String getRequireH() {
        return requireH;
    }

    public void setRequireH(String requireH) {
        this.requireH = requireH == null ? null : requireH.trim();
    }

    public String getRequireB() {
        return requireB;
    }

    public void setRequireB(String requireB) {
        this.requireB = requireB == null ? null : requireB.trim();
    }

    public String getRequireBtolerance() {
        return requireBtolerance;
    }

    public void setRequireBtolerance(String requireBtolerance) {
        this.requireBtolerance = requireBtolerance == null ? null : requireBtolerance.trim();
    }

    public String getRealCheakAngle() {
        return realCheakAngle;
    }

    public void setRealCheakAngle(String realCheakAngle) {
        this.realCheakAngle = realCheakAngle == null ? null : realCheakAngle.trim();
    }

    public String getRealCheckH() {
        return realCheckH;
    }

    public void setRealCheckH(String realCheckH) {
        this.realCheckH = realCheckH == null ? null : realCheckH.trim();
    }

    public String getRealCheckB() {
        return realCheckB;
    }

    public void setRealCheckB(String realCheckB) {
        this.realCheckB = realCheckB == null ? null : realCheckB.trim();
    }

    public Integer getJudgeAngle() {
        return judgeAngle;
    }

    public void setJudgeAngle(Integer judgeAngle) {
        this.judgeAngle = judgeAngle;
    }

    public Integer getJudgeH() {
        return judgeH;
    }

    public void setJudgeH(Integer judgeH) {
        this.judgeH = judgeH;
    }

    public Integer getJudgeB() {
        return judgeB;
    }

    public void setJudgeB(Integer judgeB) {
        this.judgeB = judgeB;
    }

    public Date getCeateTime() {
        return ceateTime;
    }

    public void setCeateTime(Date ceateTime) {
        this.ceateTime = ceateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}