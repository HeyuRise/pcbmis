package com.pcbwx.pcbmis.model;

import java.util.Date;

public class SystemLog {
    private Integer id;

    private String userName;

    private String description;

    private Integer funcType;

    private String userBrowserVer;

    private String userIp;

    private String serviceIp;

    private Integer isRecord;

    private Date creatTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getFuncType() {
        return funcType;
    }

    public void setFuncType(Integer funcType) {
        this.funcType = funcType;
    }

    public String getUserBrowserVer() {
        return userBrowserVer;
    }

    public void setUserBrowserVer(String userBrowserVer) {
        this.userBrowserVer = userBrowserVer == null ? null : userBrowserVer.trim();
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp == null ? null : userIp.trim();
    }

    public String getServiceIp() {
        return serviceIp;
    }

    public void setServiceIp(String serviceIp) {
        this.serviceIp = serviceIp == null ? null : serviceIp.trim();
    }

    public Integer getIsRecord() {
        return isRecord;
    }

    public void setIsRecord(Integer isRecord) {
        this.isRecord = isRecord;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}