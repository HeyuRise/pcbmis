package com.pcbwx.pcbmis.model;

import java.math.BigDecimal;
import java.util.Date;

public class ProductOrder {
    private Integer id;

    private String orderNum;

    private Integer batchProductionId;

    private Integer belongCompanyId;

    private String boardCharacter;

    private String boardName;

    private BigDecimal boardLong;

    private String boardPly;

    private Integer boardPlyToleranceId;

    private BigDecimal boardWide;

    private String businessNotes;

    private Integer categoryGradeId;

    private String camTip;

    private Integer characterColourId;

    private String contactCode;

    private Integer craftId;

    private String csCode;

    private String departmentCode;

    private Date deliverGoodDate;

    private String deliveryMode;

    private Date edProductDate;

    private Integer factoryId;

    private Integer floorNum;

    private Integer formingWayId;

    private Integer frameToleranceId;

    private String guestCode;

    private Date handOverDate;

    private String impedanceRequire;

    private Integer joinBoardRequireId;

    private Integer joinBoardWayId;

    private String marketDepartmentName;

    private String metallographyRequire;

    private BigDecimal minLineDistance;

    private BigDecimal minLineWidth;

    private Integer newOldProductId;

    private String offerOrderNum;

    private Date orderDate;

    private String preventSmt;

    private Integer preventSmtColorId;

    private Date productDate;

    private String productionNotes;

    private Integer productionNumPcs;

    private Integer productionNumSet;

    private Integer projectStatusId;

    private String sellerCode;

    private String shipmentsNotes;

    private Integer standardPeriod;

    private Date startPreliminaryDate;

    private Integer surfaceProcessId;

    private String throughHoleTreatment;

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

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public Integer getBatchProductionId() {
        return batchProductionId;
    }

    public void setBatchProductionId(Integer batchProductionId) {
        this.batchProductionId = batchProductionId;
    }

    public Integer getBelongCompanyId() {
        return belongCompanyId;
    }

    public void setBelongCompanyId(Integer belongCompanyId) {
        this.belongCompanyId = belongCompanyId;
    }

    public String getBoardCharacter() {
        return boardCharacter;
    }

    public void setBoardCharacter(String boardCharacter) {
        this.boardCharacter = boardCharacter == null ? null : boardCharacter.trim();
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName == null ? null : boardName.trim();
    }

    public BigDecimal getBoardLong() {
        return boardLong;
    }

    public void setBoardLong(BigDecimal boardLong) {
        this.boardLong = boardLong;
    }

    public String getBoardPly() {
        return boardPly;
    }

    public void setBoardPly(String boardPly) {
        this.boardPly = boardPly == null ? null : boardPly.trim();
    }

    public Integer getBoardPlyToleranceId() {
        return boardPlyToleranceId;
    }

    public void setBoardPlyToleranceId(Integer boardPlyToleranceId) {
        this.boardPlyToleranceId = boardPlyToleranceId;
    }

    public BigDecimal getBoardWide() {
        return boardWide;
    }

    public void setBoardWide(BigDecimal boardWide) {
        this.boardWide = boardWide;
    }

    public String getBusinessNotes() {
        return businessNotes;
    }

    public void setBusinessNotes(String businessNotes) {
        this.businessNotes = businessNotes == null ? null : businessNotes.trim();
    }

    public Integer getCategoryGradeId() {
        return categoryGradeId;
    }

    public void setCategoryGradeId(Integer categoryGradeId) {
        this.categoryGradeId = categoryGradeId;
    }

    public String getCamTip() {
        return camTip;
    }

    public void setCamTip(String camTip) {
        this.camTip = camTip == null ? null : camTip.trim();
    }

    public Integer getCharacterColourId() {
        return characterColourId;
    }

    public void setCharacterColourId(Integer characterColourId) {
        this.characterColourId = characterColourId;
    }

    public String getContactCode() {
        return contactCode;
    }

    public void setContactCode(String contactCode) {
        this.contactCode = contactCode == null ? null : contactCode.trim();
    }

    public Integer getCraftId() {
        return craftId;
    }

    public void setCraftId(Integer craftId) {
        this.craftId = craftId;
    }

    public String getCsCode() {
        return csCode;
    }

    public void setCsCode(String csCode) {
        this.csCode = csCode == null ? null : csCode.trim();
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode == null ? null : departmentCode.trim();
    }

    public Date getDeliverGoodDate() {
        return deliverGoodDate;
    }

    public void setDeliverGoodDate(Date deliverGoodDate) {
        this.deliverGoodDate = deliverGoodDate;
    }

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode == null ? null : deliveryMode.trim();
    }

    public Date getEdProductDate() {
        return edProductDate;
    }

    public void setEdProductDate(Date edProductDate) {
        this.edProductDate = edProductDate;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public Integer getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(Integer floorNum) {
        this.floorNum = floorNum;
    }

    public Integer getFormingWayId() {
        return formingWayId;
    }

    public void setFormingWayId(Integer formingWayId) {
        this.formingWayId = formingWayId;
    }

    public Integer getFrameToleranceId() {
        return frameToleranceId;
    }

    public void setFrameToleranceId(Integer frameToleranceId) {
        this.frameToleranceId = frameToleranceId;
    }

    public String getGuestCode() {
        return guestCode;
    }

    public void setGuestCode(String guestCode) {
        this.guestCode = guestCode == null ? null : guestCode.trim();
    }

    public Date getHandOverDate() {
        return handOverDate;
    }

    public void setHandOverDate(Date handOverDate) {
        this.handOverDate = handOverDate;
    }

    public String getImpedanceRequire() {
        return impedanceRequire;
    }

    public void setImpedanceRequire(String impedanceRequire) {
        this.impedanceRequire = impedanceRequire == null ? null : impedanceRequire.trim();
    }

    public Integer getJoinBoardRequireId() {
        return joinBoardRequireId;
    }

    public void setJoinBoardRequireId(Integer joinBoardRequireId) {
        this.joinBoardRequireId = joinBoardRequireId;
    }

    public Integer getJoinBoardWayId() {
        return joinBoardWayId;
    }

    public void setJoinBoardWayId(Integer joinBoardWayId) {
        this.joinBoardWayId = joinBoardWayId;
    }

    public String getMarketDepartmentName() {
        return marketDepartmentName;
    }

    public void setMarketDepartmentName(String marketDepartmentName) {
        this.marketDepartmentName = marketDepartmentName == null ? null : marketDepartmentName.trim();
    }

    public String getMetallographyRequire() {
        return metallographyRequire;
    }

    public void setMetallographyRequire(String metallographyRequire) {
        this.metallographyRequire = metallographyRequire == null ? null : metallographyRequire.trim();
    }

    public BigDecimal getMinLineDistance() {
        return minLineDistance;
    }

    public void setMinLineDistance(BigDecimal minLineDistance) {
        this.minLineDistance = minLineDistance;
    }

    public BigDecimal getMinLineWidth() {
        return minLineWidth;
    }

    public void setMinLineWidth(BigDecimal minLineWidth) {
        this.minLineWidth = minLineWidth;
    }

    public Integer getNewOldProductId() {
        return newOldProductId;
    }

    public void setNewOldProductId(Integer newOldProductId) {
        this.newOldProductId = newOldProductId;
    }

    public String getOfferOrderNum() {
        return offerOrderNum;
    }

    public void setOfferOrderNum(String offerOrderNum) {
        this.offerOrderNum = offerOrderNum == null ? null : offerOrderNum.trim();
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getPreventSmt() {
        return preventSmt;
    }

    public void setPreventSmt(String preventSmt) {
        this.preventSmt = preventSmt == null ? null : preventSmt.trim();
    }

    public Integer getPreventSmtColorId() {
        return preventSmtColorId;
    }

    public void setPreventSmtColorId(Integer preventSmtColorId) {
        this.preventSmtColorId = preventSmtColorId;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public String getProductionNotes() {
        return productionNotes;
    }

    public void setProductionNotes(String productionNotes) {
        this.productionNotes = productionNotes == null ? null : productionNotes.trim();
    }

    public Integer getProductionNumPcs() {
        return productionNumPcs;
    }

    public void setProductionNumPcs(Integer productionNumPcs) {
        this.productionNumPcs = productionNumPcs;
    }

    public Integer getProductionNumSet() {
        return productionNumSet;
    }

    public void setProductionNumSet(Integer productionNumSet) {
        this.productionNumSet = productionNumSet;
    }

    public Integer getProjectStatusId() {
        return projectStatusId;
    }

    public void setProjectStatusId(Integer projectStatusId) {
        this.projectStatusId = projectStatusId;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode == null ? null : sellerCode.trim();
    }

    public String getShipmentsNotes() {
        return shipmentsNotes;
    }

    public void setShipmentsNotes(String shipmentsNotes) {
        this.shipmentsNotes = shipmentsNotes == null ? null : shipmentsNotes.trim();
    }

    public Integer getStandardPeriod() {
        return standardPeriod;
    }

    public void setStandardPeriod(Integer standardPeriod) {
        this.standardPeriod = standardPeriod;
    }

    public Date getStartPreliminaryDate() {
        return startPreliminaryDate;
    }

    public void setStartPreliminaryDate(Date startPreliminaryDate) {
        this.startPreliminaryDate = startPreliminaryDate;
    }

    public Integer getSurfaceProcessId() {
        return surfaceProcessId;
    }

    public void setSurfaceProcessId(Integer surfaceProcessId) {
        this.surfaceProcessId = surfaceProcessId;
    }

    public String getThroughHoleTreatment() {
        return throughHoleTreatment;
    }

    public void setThroughHoleTreatment(String throughHoleTreatment) {
        this.throughHoleTreatment = throughHoleTreatment == null ? null : throughHoleTreatment.trim();
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