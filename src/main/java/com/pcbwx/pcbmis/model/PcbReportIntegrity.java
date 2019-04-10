package com.pcbwx.pcbmis.model;

import java.util.Date;

public class PcbReportIntegrity {
    private Integer id;

    private String reportNum;

    private String orderNum;

    private String checkInstrument;

    private String checkVoltage;

    private String checkPointNum;

    private String netNum;

    private String connectedResistance;

    private String insulatedResistance;

    private String connectedNetNum;

    private String result;

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

    public String getCheckInstrument() {
        return checkInstrument;
    }

    public void setCheckInstrument(String checkInstrument) {
        this.checkInstrument = checkInstrument == null ? null : checkInstrument.trim();
    }

    public String getCheckVoltage() {
        return checkVoltage;
    }

    public void setCheckVoltage(String checkVoltage) {
        this.checkVoltage = checkVoltage == null ? null : checkVoltage.trim();
    }

    public String getCheckPointNum() {
        return checkPointNum;
    }

    public void setCheckPointNum(String checkPointNum) {
        this.checkPointNum = checkPointNum == null ? null : checkPointNum.trim();
    }

    public String getNetNum() {
        return netNum;
    }

    public void setNetNum(String netNum) {
        this.netNum = netNum == null ? null : netNum.trim();
    }

    public String getConnectedResistance() {
        return connectedResistance;
    }

    public void setConnectedResistance(String connectedResistance) {
        this.connectedResistance = connectedResistance == null ? null : connectedResistance.trim();
    }

    public String getInsulatedResistance() {
        return insulatedResistance;
    }

    public void setInsulatedResistance(String insulatedResistance) {
        this.insulatedResistance = insulatedResistance == null ? null : insulatedResistance.trim();
    }

    public String getConnectedNetNum() {
        return connectedNetNum;
    }

    public void setConnectedNetNum(String connectedNetNum) {
        this.connectedNetNum = connectedNetNum == null ? null : connectedNetNum.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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