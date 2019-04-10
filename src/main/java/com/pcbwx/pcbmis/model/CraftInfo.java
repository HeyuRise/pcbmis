package com.pcbwx.pcbmis.model;

import java.util.Date;

public class CraftInfo {
    private Integer id;

    private Integer innerId;

    private String craftName;

    private Integer enable;

    private Integer exsitUnequalThickness;

    private Integer position;

    private Integer isSpecialOrder;

    private Integer isSoftHard;

    private Integer isMaimang;

    private Integer isHdi;

    private Date innerCreateTime;

    private Date innerUpdateTime;

    private Date createTime;

    private Date udpateTime;

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

    public String getCraftName() {
        return craftName;
    }

    public void setCraftName(String craftName) {
        this.craftName = craftName == null ? null : craftName.trim();
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getExsitUnequalThickness() {
        return exsitUnequalThickness;
    }

    public void setExsitUnequalThickness(Integer exsitUnequalThickness) {
        this.exsitUnequalThickness = exsitUnequalThickness;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getIsSpecialOrder() {
        return isSpecialOrder;
    }

    public void setIsSpecialOrder(Integer isSpecialOrder) {
        this.isSpecialOrder = isSpecialOrder;
    }

    public Integer getIsSoftHard() {
        return isSoftHard;
    }

    public void setIsSoftHard(Integer isSoftHard) {
        this.isSoftHard = isSoftHard;
    }

    public Integer getIsMaimang() {
        return isMaimang;
    }

    public void setIsMaimang(Integer isMaimang) {
        this.isMaimang = isMaimang;
    }

    public Integer getIsHdi() {
        return isHdi;
    }

    public void setIsHdi(Integer isHdi) {
        this.isHdi = isHdi;
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

    public Date getUdpateTime() {
        return udpateTime;
    }

    public void setUdpateTime(Date udpateTime) {
        this.udpateTime = udpateTime;
    }
}