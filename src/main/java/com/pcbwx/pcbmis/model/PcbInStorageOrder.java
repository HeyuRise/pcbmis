package com.pcbwx.pcbmis.model;

import java.util.Date;

public class PcbInStorageOrder {
    private Integer id;

    private String orderNumber;

    private Integer productPeriod;

    private Date inStorageDate;

    private Integer inAmountPcs;

    private Integer inAmountSet;

    private String batchNumber;

    private String boardName;

    private String boardNameNew;

    private String createrCode;

    private Integer amountCheckoutPcs;

    private Integer amountCheckoutSet;

    private String state;

    private String orderType;

    private String productOrderNum;

    private String joinBoardOrderCode;

    private Date receiveDate;

    private String receiver;

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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public Integer getProductPeriod() {
        return productPeriod;
    }

    public void setProductPeriod(Integer productPeriod) {
        this.productPeriod = productPeriod;
    }

    public Date getInStorageDate() {
        return inStorageDate;
    }

    public void setInStorageDate(Date inStorageDate) {
        this.inStorageDate = inStorageDate;
    }

    public Integer getInAmountPcs() {
        return inAmountPcs;
    }

    public void setInAmountPcs(Integer inAmountPcs) {
        this.inAmountPcs = inAmountPcs;
    }

    public Integer getInAmountSet() {
        return inAmountSet;
    }

    public void setInAmountSet(Integer inAmountSet) {
        this.inAmountSet = inAmountSet;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber == null ? null : batchNumber.trim();
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName == null ? null : boardName.trim();
    }

    public String getBoardNameNew() {
        return boardNameNew;
    }

    public void setBoardNameNew(String boardNameNew) {
        this.boardNameNew = boardNameNew == null ? null : boardNameNew.trim();
    }

    public String getCreaterCode() {
        return createrCode;
    }

    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    public Integer getAmountCheckoutPcs() {
        return amountCheckoutPcs;
    }

    public void setAmountCheckoutPcs(Integer amountCheckoutPcs) {
        this.amountCheckoutPcs = amountCheckoutPcs;
    }

    public Integer getAmountCheckoutSet() {
        return amountCheckoutSet;
    }

    public void setAmountCheckoutSet(Integer amountCheckoutSet) {
        this.amountCheckoutSet = amountCheckoutSet;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getProductOrderNum() {
        return productOrderNum;
    }

    public void setProductOrderNum(String productOrderNum) {
        this.productOrderNum = productOrderNum == null ? null : productOrderNum.trim();
    }

    public String getJoinBoardOrderCode() {
        return joinBoardOrderCode;
    }

    public void setJoinBoardOrderCode(String joinBoardOrderCode) {
        this.joinBoardOrderCode = joinBoardOrderCode == null ? null : joinBoardOrderCode.trim();
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
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