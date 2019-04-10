package com.pcbwx.pcbmis.model;

import java.math.BigDecimal;
import java.util.Date;

public class BoardPlyTolerance {
    private Integer id;

    private Integer innerId;

    private String toleranceName;

    private String boardPlyType;

    private BigDecimal maxBoardPlyTolerance;

    private BigDecimal minBoardPlyTolerance;

    private Integer enable;

    private Date innerCreateTime;

    private Date innerUpdateTime;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInnerId() {
        return innerId;
    }

    public void setInnerId(Integer innerId) {
        this.innerId = innerId;
    }

    public String getToleranceName() {
        return toleranceName;
    }

    public void setToleranceName(String toleranceName) {
        this.toleranceName = toleranceName == null ? null : toleranceName.trim();
    }

    public String getBoardPlyType() {
        return boardPlyType;
    }

    public void setBoardPlyType(String boardPlyType) {
        this.boardPlyType = boardPlyType == null ? null : boardPlyType.trim();
    }

    public BigDecimal getMaxBoardPlyTolerance() {
        return maxBoardPlyTolerance;
    }

    public void setMaxBoardPlyTolerance(BigDecimal maxBoardPlyTolerance) {
        this.maxBoardPlyTolerance = maxBoardPlyTolerance;
    }

    public BigDecimal getMinBoardPlyTolerance() {
        return minBoardPlyTolerance;
    }

    public void setMinBoardPlyTolerance(BigDecimal minBoardPlyTolerance) {
        this.minBoardPlyTolerance = minBoardPlyTolerance;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Date getInnerCreateTime() {
        return innerCreateTime;
    }

    public void setInnerCreateTime(Date innerCreateTime) {
        this.innerCreateTime = innerCreateTime;
    }

    public Date getInnerUpdateTime() {
        return innerUpdateTime;
    }

    public void setInnerUpdateTime(Date innerUpdateTime) {
        this.innerUpdateTime = innerUpdateTime;
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