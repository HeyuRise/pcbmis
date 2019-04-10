package com.pcbwx.pcbmis.model;

import java.util.Date;

public class PcbRelationFile {
    private Integer id;

    private Integer unqualifiedId;

    private Integer fileId;

    private Date createTime;

    private Date updateTime;

    private Integer enable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUnqualifiedId() {
        return unqualifiedId;
    }

    public void setUnqualifiedId(Integer unqualifiedId) {
        this.unqualifiedId = unqualifiedId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
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

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}