package com.pcbwx.pcbmis.model;

import java.math.BigDecimal;
import java.util.Date;

public class PcbCheckReport {
    private Integer id;

    private Integer templateId;

    private String reportNum;

    private String checkNum;

    private String orderNum;

    private String orderNumber;

    private String guestCode;

    private String boardName;

    private Integer factoryId;

    private Integer categoryGradeId;

    private BigDecimal boardLong;

    private BigDecimal boardWide;

    private String boardPly;

    private String orderType;

    private String joinBoardOrderCode;

    private Integer productionNumPcs;

    private String reportMakerCode;

    private Date makeTime;

    private String reportAuditorCode;

    private Date auditDate;

    private Integer statusId;

    private String status;

    private Date innerCreateTime;

    private String serialNumber;

    private String revision;

    private String documentNumber;

    private Boolean enable;

    private String note;

    private String mistakeNote;

    private String supplierMistakeNote;

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

    public String getReportNum() {
        return reportNum;
    }

    public void setReportNum(String reportNum) {
        this.reportNum = reportNum == null ? null : reportNum.trim();
    }

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum == null ? null : checkNum.trim();
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getGuestCode() {
        return guestCode;
    }

    public void setGuestCode(String guestCode) {
        this.guestCode = guestCode == null ? null : guestCode.trim();
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName == null ? null : boardName.trim();
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public Integer getCategoryGradeId() {
        return categoryGradeId;
    }

    public void setCategoryGradeId(Integer categoryGradeId) {
        this.categoryGradeId = categoryGradeId;
    }

    public BigDecimal getBoardLong() {
        return boardLong;
    }

    public void setBoardLong(BigDecimal boardLong) {
        this.boardLong = boardLong;
    }

    public BigDecimal getBoardWide() {
        return boardWide;
    }

    public void setBoardWide(BigDecimal boardWide) {
        this.boardWide = boardWide;
    }

    public String getBoardPly() {
        return boardPly;
    }

    public void setBoardPly(String boardPly) {
        this.boardPly = boardPly == null ? null : boardPly.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getJoinBoardOrderCode() {
        return joinBoardOrderCode;
    }

    public void setJoinBoardOrderCode(String joinBoardOrderCode) {
        this.joinBoardOrderCode = joinBoardOrderCode == null ? null : joinBoardOrderCode.trim();
    }

    public Integer getProductionNumPcs() {
        return productionNumPcs;
    }

    public void setProductionNumPcs(Integer productionNumPcs) {
        this.productionNumPcs = productionNumPcs;
    }

    public String getReportMakerCode() {
        return reportMakerCode;
    }

    public void setReportMakerCode(String reportMakerCode) {
        this.reportMakerCode = reportMakerCode == null ? null : reportMakerCode.trim();
    }

    public Date getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(Date makeTime) {
        this.makeTime = makeTime;
    }

    public String getReportAuditorCode() {
        return reportAuditorCode;
    }

    public void setReportAuditorCode(String reportAuditorCode) {
        this.reportAuditorCode = reportAuditorCode == null ? null : reportAuditorCode.trim();
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getInnerCreateTime() {
        return innerCreateTime;
    }

    public void setInnerCreateTime(Date innerCreateTime) {
        this.innerCreateTime = innerCreateTime;
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

    public String getSupplierMistakeNote() {
        return supplierMistakeNote;
    }

    public void setSupplierMistakeNote(String supplierMistakeNote) {
        this.supplierMistakeNote = supplierMistakeNote == null ? null : supplierMistakeNote.trim();
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