package com.pcbwx.pcbmis.model;

import java.util.Date;

public class BoardTolerance {
    private Integer id;

    private String checkNum;

    private String orderNum;

    private String minBoardlongTolerance;

    private String maxBoardlongTolerance;

    private String minBoardwideTolerance;

    private String maxBoardwideTolerance;

    private String minBoardplyTolerance;

    private String maxBoardplyTolerance;

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

    public String getMinBoardlongTolerance() {
        return minBoardlongTolerance;
    }

    public void setMinBoardlongTolerance(String minBoardlongTolerance) {
        this.minBoardlongTolerance = minBoardlongTolerance == null ? null : minBoardlongTolerance.trim();
    }

    public String getMaxBoardlongTolerance() {
        return maxBoardlongTolerance;
    }

    public void setMaxBoardlongTolerance(String maxBoardlongTolerance) {
        this.maxBoardlongTolerance = maxBoardlongTolerance == null ? null : maxBoardlongTolerance.trim();
    }

    public String getMinBoardwideTolerance() {
        return minBoardwideTolerance;
    }

    public void setMinBoardwideTolerance(String minBoardwideTolerance) {
        this.minBoardwideTolerance = minBoardwideTolerance == null ? null : minBoardwideTolerance.trim();
    }

    public String getMaxBoardwideTolerance() {
        return maxBoardwideTolerance;
    }

    public void setMaxBoardwideTolerance(String maxBoardwideTolerance) {
        this.maxBoardwideTolerance = maxBoardwideTolerance == null ? null : maxBoardwideTolerance.trim();
    }

    public String getMinBoardplyTolerance() {
        return minBoardplyTolerance;
    }

    public void setMinBoardplyTolerance(String minBoardplyTolerance) {
        this.minBoardplyTolerance = minBoardplyTolerance == null ? null : minBoardplyTolerance.trim();
    }

    public String getMaxBoardplyTolerance() {
        return maxBoardplyTolerance;
    }

    public void setMaxBoardplyTolerance(String maxBoardplyTolerance) {
        this.maxBoardplyTolerance = maxBoardplyTolerance == null ? null : maxBoardplyTolerance.trim();
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