package com.pcbwx.pcbmis.model;

import java.util.Date;

public class JoinBoardWay {
    private Integer id;

    private Integer innerId;

    private Integer enable;

    private String joinName;

    private Integer orderTypeNum;

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

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getJoinName() {
        return joinName;
    }

    public void setJoinName(String joinName) {
        this.joinName = joinName == null ? null : joinName.trim();
    }

    public Integer getOrderTypeNum() {
        return orderTypeNum;
    }

    public void setOrderTypeNum(Integer orderTypeNum) {
        this.orderTypeNum = orderTypeNum;
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