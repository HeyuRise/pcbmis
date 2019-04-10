package com.pcbwx.pcbmis.model;

import java.util.Date;

public class BasemAterial {
    private Integer id;

    private Integer innerId;

    private String innerCode;

    private String basemAterialName;

    private Integer isSpecialBoard;

    private Integer softBoardPly;

    private String mediumPly;

    private Integer enable;

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

    public String getBasemAterialName() {
        return basemAterialName;
    }

    public void setBasemAterialName(String basemAterialName) {
        this.basemAterialName = basemAterialName == null ? null : basemAterialName.trim();
    }

    public Integer getIsSpecialBoard() {
        return isSpecialBoard;
    }

    public void setIsSpecialBoard(Integer isSpecialBoard) {
        this.isSpecialBoard = isSpecialBoard;
    }

    public Integer getSoftBoardPly() {
        return softBoardPly;
    }

    public void setSoftBoardPly(Integer softBoardPly) {
        this.softBoardPly = softBoardPly;
    }

    public String getMediumPly() {
        return mediumPly;
    }

    public void setMediumPly(String mediumPly) {
        this.mediumPly = mediumPly == null ? null : mediumPly.trim();
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
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