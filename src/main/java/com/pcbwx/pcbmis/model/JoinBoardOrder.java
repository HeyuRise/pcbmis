package com.pcbwx.pcbmis.model;

import java.math.BigDecimal;
import java.util.Date;

public class JoinBoardOrder {
    private Integer id;

    private String joinBoardCode;

    private String productOrderNum;

    private String joinBoardName;

    private BigDecimal joinBoardLong;

    private BigDecimal joinBoardWide;

    private BigDecimal joinBoardArea;

    private String preventSmt;

    private String boardChar;

    private Integer singleBoardHoleNum;

    private Integer minAperture;

    private BigDecimal gongLength;

    private BigDecimal minLineWidth;

    private BigDecimal minLineDistance;

    private Integer productNum;

    private Integer optimalProductNum;

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

    public String getJoinBoardCode() {
        return joinBoardCode;
    }

    public void setJoinBoardCode(String joinBoardCode) {
        this.joinBoardCode = joinBoardCode == null ? null : joinBoardCode.trim();
    }

    public String getProductOrderNum() {
        return productOrderNum;
    }

    public void setProductOrderNum(String productOrderNum) {
        this.productOrderNum = productOrderNum == null ? null : productOrderNum.trim();
    }

    public String getJoinBoardName() {
        return joinBoardName;
    }

    public void setJoinBoardName(String joinBoardName) {
        this.joinBoardName = joinBoardName == null ? null : joinBoardName.trim();
    }

    public BigDecimal getJoinBoardLong() {
        return joinBoardLong;
    }

    public void setJoinBoardLong(BigDecimal joinBoardLong) {
        this.joinBoardLong = joinBoardLong;
    }

    public BigDecimal getJoinBoardWide() {
        return joinBoardWide;
    }

    public void setJoinBoardWide(BigDecimal joinBoardWide) {
        this.joinBoardWide = joinBoardWide;
    }

    public BigDecimal getJoinBoardArea() {
        return joinBoardArea;
    }

    public void setJoinBoardArea(BigDecimal joinBoardArea) {
        this.joinBoardArea = joinBoardArea;
    }

    public String getPreventSmt() {
        return preventSmt;
    }

    public void setPreventSmt(String preventSmt) {
        this.preventSmt = preventSmt == null ? null : preventSmt.trim();
    }

    public String getBoardChar() {
        return boardChar;
    }

    public void setBoardChar(String boardChar) {
        this.boardChar = boardChar == null ? null : boardChar.trim();
    }

    public Integer getSingleBoardHoleNum() {
        return singleBoardHoleNum;
    }

    public void setSingleBoardHoleNum(Integer singleBoardHoleNum) {
        this.singleBoardHoleNum = singleBoardHoleNum;
    }

    public Integer getMinAperture() {
        return minAperture;
    }

    public void setMinAperture(Integer minAperture) {
        this.minAperture = minAperture;
    }

    public BigDecimal getGongLength() {
        return gongLength;
    }

    public void setGongLength(BigDecimal gongLength) {
        this.gongLength = gongLength;
    }

    public BigDecimal getMinLineWidth() {
        return minLineWidth;
    }

    public void setMinLineWidth(BigDecimal minLineWidth) {
        this.minLineWidth = minLineWidth;
    }

    public BigDecimal getMinLineDistance() {
        return minLineDistance;
    }

    public void setMinLineDistance(BigDecimal minLineDistance) {
        this.minLineDistance = minLineDistance;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Integer getOptimalProductNum() {
        return optimalProductNum;
    }

    public void setOptimalProductNum(Integer optimalProductNum) {
        this.optimalProductNum = optimalProductNum;
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