package com.pcbwx.pcbmis.model;

import java.util.Date;

public class PcbReportDetail {
    private Integer id;

    private String reportNum;

    private String orderNum;

    private String optionName;

    private String checkRequire;

    private String boardTolerance;

    private String checkResult;

    private Integer judgeResult;

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

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName == null ? null : optionName.trim();
    }

    public String getCheckRequire() {
        return checkRequire;
    }

    public void setCheckRequire(String checkRequire) {
        this.checkRequire = checkRequire == null ? null : checkRequire.trim();
    }

    public String getBoardTolerance() {
        return boardTolerance;
    }

    public void setBoardTolerance(String boardTolerance) {
        this.boardTolerance = boardTolerance == null ? null : boardTolerance.trim();
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult == null ? null : checkResult.trim();
    }

    public Integer getJudgeResult() {
        return judgeResult;
    }

    public void setJudgeResult(Integer judgeResult) {
        this.judgeResult = judgeResult;
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