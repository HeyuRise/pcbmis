package com.pcbwx.pcbmis.model;

import java.util.Date;

public class UserRole {
    private Integer id;

    private String roleName;

    private String creatorCode;

    private String updateorCode;

    private Integer enable;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getCreatorCode() {
        return creatorCode;
    }

    public void setCreatorCode(String creatorCode) {
        this.creatorCode = creatorCode == null ? null : creatorCode.trim();
    }

    public String getUpdateorCode() {
        return updateorCode;
    }

    public void setUpdateorCode(String updateorCode) {
        this.updateorCode = updateorCode == null ? null : updateorCode.trim();
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
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