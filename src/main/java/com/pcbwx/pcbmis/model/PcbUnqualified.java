package com.pcbwx.pcbmis.model;

import java.util.Date;

public class PcbUnqualified {
    private Integer id;

    private String serialNumber;

    private String revision;

    private String documentNumber;

    private String checkNum;

    private String orderNumber;

    private String orderType;

    private Integer belongCompanyId;

    private String boardName;

    private String guestCode;

    private String unqualifiedBatch;

    private Integer unqualifiedNumber;

    private String unqualifiedDesc;

    private Integer factoryId;

    private String unqualifiedSource;

    private String productType;

    private Integer productLevel;

    private String inspector;

    private Date submitTime;

    private Date createTime;

    private String adjudicatingOpinions;

    private String disposalMethod;

    private Date trialTime;

    private Date approvalTime;

    private String approvalPersonnel;

    private String trialPersonnel;

    private Date updateTime;

    private Integer enable;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision == null ? null : revision.trim();
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber == null ? null : documentNumber.trim();
    }

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum == null ? null : checkNum.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public Integer getBelongCompanyId() {
        return belongCompanyId;
    }

    public void setBelongCompanyId(Integer belongCompanyId) {
        this.belongCompanyId = belongCompanyId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName == null ? null : boardName.trim();
    }

    public String getGuestCode() {
        return guestCode;
    }

    public void setGuestCode(String guestCode) {
        this.guestCode = guestCode == null ? null : guestCode.trim();
    }

    public String getUnqualifiedBatch() {
        return unqualifiedBatch;
    }

    public void setUnqualifiedBatch(String unqualifiedBatch) {
        this.unqualifiedBatch = unqualifiedBatch == null ? null : unqualifiedBatch.trim();
    }

    public Integer getUnqualifiedNumber() {
        return unqualifiedNumber;
    }

    public void setUnqualifiedNumber(Integer unqualifiedNumber) {
        this.unqualifiedNumber = unqualifiedNumber;
    }

    public String getUnqualifiedDesc() {
        return unqualifiedDesc;
    }

    public void setUnqualifiedDesc(String unqualifiedDesc) {
        this.unqualifiedDesc = unqualifiedDesc == null ? null : unqualifiedDesc.trim();
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public String getUnqualifiedSource() {
        return unqualifiedSource;
    }

    public void setUnqualifiedSource(String unqualifiedSource) {
        this.unqualifiedSource = unqualifiedSource == null ? null : unqualifiedSource.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public Integer getProductLevel() {
        return productLevel;
    }

    public void setProductLevel(Integer productLevel) {
        this.productLevel = productLevel;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector == null ? null : inspector.trim();
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAdjudicatingOpinions() {
        return adjudicatingOpinions;
    }

    public void setAdjudicatingOpinions(String adjudicatingOpinions) {
        this.adjudicatingOpinions = adjudicatingOpinions == null ? null : adjudicatingOpinions.trim();
    }

    public String getDisposalMethod() {
        return disposalMethod;
    }

    public void setDisposalMethod(String disposalMethod) {
        this.disposalMethod = disposalMethod == null ? null : disposalMethod.trim();
    }

    public Date getTrialTime() {
        return trialTime;
    }

    public void setTrialTime(Date trialTime) {
        this.trialTime = trialTime;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getApprovalPersonnel() {
        return approvalPersonnel;
    }

    public void setApprovalPersonnel(String approvalPersonnel) {
        this.approvalPersonnel = approvalPersonnel == null ? null : approvalPersonnel.trim();
    }

    public String getTrialPersonnel() {
        return trialPersonnel;
    }

    public void setTrialPersonnel(String trialPersonnel) {
        this.trialPersonnel = trialPersonnel == null ? null : trialPersonnel.trim();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}