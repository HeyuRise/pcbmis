package com.pcbwx.pcbmis.model;

import java.util.Date;

public class FactoryInfo {
    private Integer id;

    private Integer innerId;

    private String factoryName;

    private Integer enable;

    private String recipient;

    private String email;

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

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName == null ? null : factoryName.trim();
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient == null ? null : recipient.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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