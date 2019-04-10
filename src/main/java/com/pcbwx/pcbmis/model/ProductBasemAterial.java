package com.pcbwx.pcbmis.model;

import java.util.Date;

public class ProductBasemAterial {
    private Integer id;

    private String orderNum;

    private Integer basemAterialId;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public Integer getBasemAterialId() {
        return basemAterialId;
    }

    public void setBasemAterialId(Integer basemAterialId) {
        this.basemAterialId = basemAterialId;
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