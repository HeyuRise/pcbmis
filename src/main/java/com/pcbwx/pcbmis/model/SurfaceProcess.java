package com.pcbwx.pcbmis.model;

import java.util.Date;

public class SurfaceProcess {
    private Integer id;

    private Integer innerId;

    private String processName;

    private Integer enable;

    private Integer isGoldPlated;

    private Integer position;

    private Integer isGoldConvert;

    private Integer isAddDelivery;

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

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName == null ? null : processName.trim();
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getIsGoldPlated() {
        return isGoldPlated;
    }

    public void setIsGoldPlated(Integer isGoldPlated) {
        this.isGoldPlated = isGoldPlated;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getIsGoldConvert() {
        return isGoldConvert;
    }

    public void setIsGoldConvert(Integer isGoldConvert) {
        this.isGoldConvert = isGoldConvert;
    }

    public Integer getIsAddDelivery() {
        return isAddDelivery;
    }

    public void setIsAddDelivery(Integer isAddDelivery) {
        this.isAddDelivery = isAddDelivery;
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