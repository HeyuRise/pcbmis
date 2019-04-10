package com.pcbwx.pcbmis.model;

import java.math.BigDecimal;
import java.util.Date;

public class FrameTolerance {
    private Integer id;

    private Integer innerId;

    private String innerCode;

    private String ftName;

    private Integer enable;

    private BigDecimal minLongTolerance;

    private BigDecimal maxLongTolerance;

    private BigDecimal minWideTolerance;

    private BigDecimal maxWideTolerance;

    private Integer position;

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

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode == null ? null : innerCode.trim();
    }

    public String getFtName() {
        return ftName;
    }

    public void setFtName(String ftName) {
        this.ftName = ftName == null ? null : ftName.trim();
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public BigDecimal getMinLongTolerance() {
        return minLongTolerance;
    }

    public void setMinLongTolerance(BigDecimal minLongTolerance) {
        this.minLongTolerance = minLongTolerance;
    }

    public BigDecimal getMaxLongTolerance() {
        return maxLongTolerance;
    }

    public void setMaxLongTolerance(BigDecimal maxLongTolerance) {
        this.maxLongTolerance = maxLongTolerance;
    }

    public BigDecimal getMinWideTolerance() {
        return minWideTolerance;
    }

    public void setMinWideTolerance(BigDecimal minWideTolerance) {
        this.minWideTolerance = minWideTolerance;
    }

    public BigDecimal getMaxWideTolerance() {
        return maxWideTolerance;
    }

    public void setMaxWideTolerance(BigDecimal maxWideTolerance) {
        this.maxWideTolerance = maxWideTolerance;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
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