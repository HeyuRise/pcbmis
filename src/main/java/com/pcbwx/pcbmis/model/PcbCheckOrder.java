package com.pcbwx.pcbmis.model;

import java.util.Date;

public class PcbCheckOrder {
    private Integer id;

    private Integer templateId;

    private String orderNumber;

    private String checkNum;

    private Integer productPeriod;

    private Date inStorageDate;

    private Integer inAmountPcs;

    private Integer inAmountSet;

    private String batchNumber;

    private String boardName;

    private String createrCode;

    private Integer amountCheckoutPcs;

    private Integer amountCheckoutSet;

    private String checkState;

    private Integer checkStateId;

    private String orderType;

    private String productOrderNum;

    private String joinBoardOrderCode;

    private Integer spotCheckNumPcs;

    private Date receiveDate;

    private String inspector;

    private Date checkDate;

    private String auditor;

    private Date auditDate;

    private String contentId;

    private Integer badRecord;

    private Date badRecordTime;

    private String serialNumber;

    private String revision;

    private String documentNumber;

    private Boolean enable;

    private String note;

    private String mistakeNote;

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

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum == null ? null : checkNum.trim();
    }

    public Integer getProductPeriod() {
        return productPeriod;
    }

    public void setProductPeriod(Integer productPeriod) {
        this.productPeriod = productPeriod;
    }

    public Date getInStorageDate() {
        return inStorageDate;
    }

    public void setInStorageDate(Date inStorageDate) {
        this.inStorageDate = inStorageDate;
    }

    public Integer getInAmountPcs() {
        return inAmountPcs;
    }

    public void setInAmountPcs(Integer inAmountPcs) {
        this.inAmountPcs = inAmountPcs;
    }

    public Integer getInAmountSet() {
        return inAmountSet;
    }

    public void setInAmountSet(Integer inAmountSet) {
        this.inAmountSet = inAmountSet;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber == null ? null : batchNumber.trim();
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName == null ? null : boardName.trim();
    }

    public String getCreaterCode() {
        return createrCode;
    }

    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    public Integer getAmountCheckoutPcs() {
        return amountCheckoutPcs;
    }

    public void setAmountCheckoutPcs(Integer amountCheckoutPcs) {
        this.amountCheckoutPcs = amountCheckoutPcs;
    }

    public Integer getAmountCheckoutSet() {
        return amountCheckoutSet;
    }

    public void setAmountCheckoutSet(Integer amountCheckoutSet) {
        this.amountCheckoutSet = amountCheckoutSet;
    }

    public String getCheckState() {
        return checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState == null ? null : checkState.trim();
    }

    public Integer getCheckStateId() {
        return checkStateId;
    }

    public void setCheckStateId(Integer checkStateId) {
        this.checkStateId = checkStateId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getProductOrderNum() {
        return productOrderNum;
    }

    public void setProductOrderNum(String productOrderNum) {
        this.productOrderNum = productOrderNum == null ? null : productOrderNum.trim();
    }

    public String getJoinBoardOrderCode() {
        return joinBoardOrderCode;
    }

    public void setJoinBoardOrderCode(String joinBoardOrderCode) {
        this.joinBoardOrderCode = joinBoardOrderCode == null ? null : joinBoardOrderCode.trim();
    }

    public Integer getSpotCheckNumPcs() {
        return spotCheckNumPcs;
    }

    public void setSpotCheckNumPcs(Integer spotCheckNumPcs) {
        this.spotCheckNumPcs = spotCheckNumPcs;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector == null ? null : inspector.trim();
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor == null ? null : auditor.trim();
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId == null ? null : contentId.trim();
    }

    public Integer getBadRecord() {
        return badRecord;
    }

    public void setBadRecord(Integer badRecord) {
        this.badRecord = badRecord;
    }

    public Date getBadRecordTime() {
        return badRecordTime;
    }

    public void setBadRecordTime(Date badRecordTime) {
        this.badRecordTime = badRecordTime;
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

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getMistakeNote() {
        return mistakeNote;
    }

    public void setMistakeNote(String mistakeNote) {
        this.mistakeNote = mistakeNote == null ? null : mistakeNote.trim();
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