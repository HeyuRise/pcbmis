package com.pcbwx.pcbmis.model;

import java.util.Date;

public class PcbCheckDetail {
    private Integer id;

    private String checkNum;

    private String orderNum;

    private String templateCode;

    private String optionName;

    private String checkRequire;

    private String boardTolerance;

    private String checkResult;

    private Integer badNum;

    private Integer judgeResult;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum == null ? null : checkNum.trim();
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode == null ? null : templateCode.trim();
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

    public Integer getBadNum() {
        return badNum;
    }

    public void setBadNum(Integer badNum) {
        this.badNum = badNum;
    }

    public Integer getJudgeResult() {
        return judgeResult;
    }

    public void setJudgeResult(Integer judgeResult) {
        this.judgeResult = judgeResult;
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