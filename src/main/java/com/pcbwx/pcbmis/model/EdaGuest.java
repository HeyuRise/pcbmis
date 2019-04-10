package com.pcbwx.pcbmis.model;

import java.util.Date;

public class EdaGuest {
    private Integer id;

    private Integer innerId;

    private String guestCode;

    private String guestName;

    private String shortNameCn;

    private String shortNameEn;

    private String sellDepart;

    private String sellerCode;

    private String sellerEmail;

    private String place;

    private String sellCompany;

    private String guestType;

    private Integer guestState;

    private String csCode;

    private Integer checkType;

    private Date createTime;

    private Date innerCreateTime;

    private Date innerUpdateTime;

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

    public String getGuestCode() {
        return guestCode;
    }

    public void setGuestCode(String guestCode) {
        this.guestCode = guestCode == null ? null : guestCode.trim();
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName == null ? null : guestName.trim();
    }

    public String getShortNameCn() {
        return shortNameCn;
    }

    public void setShortNameCn(String shortNameCn) {
        this.shortNameCn = shortNameCn == null ? null : shortNameCn.trim();
    }

    public String getShortNameEn() {
        return shortNameEn;
    }

    public void setShortNameEn(String shortNameEn) {
        this.shortNameEn = shortNameEn == null ? null : shortNameEn.trim();
    }

    public String getSellDepart() {
        return sellDepart;
    }

    public void setSellDepart(String sellDepart) {
        this.sellDepart = sellDepart == null ? null : sellDepart.trim();
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode == null ? null : sellerCode.trim();
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail == null ? null : sellerEmail.trim();
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public String getSellCompany() {
        return sellCompany;
    }

    public void setSellCompany(String sellCompany) {
        this.sellCompany = sellCompany == null ? null : sellCompany.trim();
    }

    public String getGuestType() {
        return guestType;
    }

    public void setGuestType(String guestType) {
        this.guestType = guestType == null ? null : guestType.trim();
    }

    public Integer getGuestState() {
        return guestState;
    }

    public void setGuestState(Integer guestState) {
        this.guestState = guestState;
    }

    public String getCsCode() {
        return csCode;
    }

    public void setCsCode(String csCode) {
        this.csCode = csCode == null ? null : csCode.trim();
    }

    public Integer getCheckType() {
        return checkType;
    }

    public void setCheckType(Integer checkType) {
        this.checkType = checkType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}