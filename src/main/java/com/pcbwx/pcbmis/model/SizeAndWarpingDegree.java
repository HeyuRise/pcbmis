package com.pcbwx.pcbmis.model;

import java.math.BigDecimal;
import java.util.Date;

public class SizeAndWarpingDegree {
    private Integer id;

    private String checkNum;

    private String orderNum;

    private String boardNum;

    private BigDecimal boardLong;

    private BigDecimal boardWide;

    private Double boardPly;

    private String layHeight;

    private String warpHeight;

    private String warpingDegree;

    private Integer judge;

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

    public String getBoardNum() {
        return boardNum;
    }

    public void setBoardNum(String boardNum) {
        this.boardNum = boardNum == null ? null : boardNum.trim();
    }

    public BigDecimal getBoardLong() {
        return boardLong;
    }

    public void setBoardLong(BigDecimal boardLong) {
        this.boardLong = boardLong;
    }

    public BigDecimal getBoardWide() {
        return boardWide;
    }

    public void setBoardWide(BigDecimal boardWide) {
        this.boardWide = boardWide;
    }

    public Double getBoardPly() {
        return boardPly;
    }

    public void setBoardPly(Double boardPly) {
        this.boardPly = boardPly;
    }

    public String getLayHeight() {
        return layHeight;
    }

    public void setLayHeight(String layHeight) {
        this.layHeight = layHeight == null ? null : layHeight.trim();
    }

    public String getWarpHeight() {
        return warpHeight;
    }

    public void setWarpHeight(String warpHeight) {
        this.warpHeight = warpHeight == null ? null : warpHeight.trim();
    }

    public String getWarpingDegree() {
        return warpingDegree;
    }

    public void setWarpingDegree(String warpingDegree) {
        this.warpingDegree = warpingDegree == null ? null : warpingDegree.trim();
    }

    public Integer getJudge() {
        return judge;
    }

    public void setJudge(Integer judge) {
        this.judge = judge;
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