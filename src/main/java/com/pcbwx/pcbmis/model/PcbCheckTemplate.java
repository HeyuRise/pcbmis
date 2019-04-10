package com.pcbwx.pcbmis.model;

import java.util.Date;

public class PcbCheckTemplate {
    private Integer id;

    private Integer templateId;

    private String templateName;

    private String burrs;

    private String gap;

    private String exposedCopper;

    private String fabricTexture;

    private String pitVoid;

    private String spotCrack;

    private String delaminationFoaming;

    private String foreignImpurity;

    private String coverageAdhesion;

    private String coincidenceDegree;

    private String foamingLayering;

    private String corrugation;

    private String falseExposedCopper;

    private String falseBridgeDam;

    private String chromaticAberration;

    private String identificationAdhesion;

    private String nodulesBurrs;

    private String darkOfHoleTinLead;

    private String padCocked;

    private String haloRing;

    private String outerRingWidth;

    private String solderInHole;

    private String clogHole;

    private String lineWidthSpacing;

    private String printedBoardElse;

    private String baseMaterialElse;

    private String solderMaskElse;

    private String specialBoardNum;

    private String markElse;

    private String conductivePatternElse;

    private String boardLongResult;

    private String boardWideResult;

    private String boardPlyResult;

    private String smoothnessResult;

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

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public String getBurrs() {
        return burrs;
    }

    public void setBurrs(String burrs) {
        this.burrs = burrs == null ? null : burrs.trim();
    }

    public String getGap() {
        return gap;
    }

    public void setGap(String gap) {
        this.gap = gap == null ? null : gap.trim();
    }

    public String getExposedCopper() {
        return exposedCopper;
    }

    public void setExposedCopper(String exposedCopper) {
        this.exposedCopper = exposedCopper == null ? null : exposedCopper.trim();
    }

    public String getFabricTexture() {
        return fabricTexture;
    }

    public void setFabricTexture(String fabricTexture) {
        this.fabricTexture = fabricTexture == null ? null : fabricTexture.trim();
    }

    public String getPitVoid() {
        return pitVoid;
    }

    public void setPitVoid(String pitVoid) {
        this.pitVoid = pitVoid == null ? null : pitVoid.trim();
    }

    public String getSpotCrack() {
        return spotCrack;
    }

    public void setSpotCrack(String spotCrack) {
        this.spotCrack = spotCrack == null ? null : spotCrack.trim();
    }

    public String getDelaminationFoaming() {
        return delaminationFoaming;
    }

    public void setDelaminationFoaming(String delaminationFoaming) {
        this.delaminationFoaming = delaminationFoaming == null ? null : delaminationFoaming.trim();
    }

    public String getForeignImpurity() {
        return foreignImpurity;
    }

    public void setForeignImpurity(String foreignImpurity) {
        this.foreignImpurity = foreignImpurity == null ? null : foreignImpurity.trim();
    }

    public String getCoverageAdhesion() {
        return coverageAdhesion;
    }

    public void setCoverageAdhesion(String coverageAdhesion) {
        this.coverageAdhesion = coverageAdhesion == null ? null : coverageAdhesion.trim();
    }

    public String getCoincidenceDegree() {
        return coincidenceDegree;
    }

    public void setCoincidenceDegree(String coincidenceDegree) {
        this.coincidenceDegree = coincidenceDegree == null ? null : coincidenceDegree.trim();
    }

    public String getFoamingLayering() {
        return foamingLayering;
    }

    public void setFoamingLayering(String foamingLayering) {
        this.foamingLayering = foamingLayering == null ? null : foamingLayering.trim();
    }

    public String getCorrugation() {
        return corrugation;
    }

    public void setCorrugation(String corrugation) {
        this.corrugation = corrugation == null ? null : corrugation.trim();
    }

    public String getFalseExposedCopper() {
        return falseExposedCopper;
    }

    public void setFalseExposedCopper(String falseExposedCopper) {
        this.falseExposedCopper = falseExposedCopper == null ? null : falseExposedCopper.trim();
    }

    public String getFalseBridgeDam() {
        return falseBridgeDam;
    }

    public void setFalseBridgeDam(String falseBridgeDam) {
        this.falseBridgeDam = falseBridgeDam == null ? null : falseBridgeDam.trim();
    }

    public String getChromaticAberration() {
        return chromaticAberration;
    }

    public void setChromaticAberration(String chromaticAberration) {
        this.chromaticAberration = chromaticAberration == null ? null : chromaticAberration.trim();
    }

    public String getIdentificationAdhesion() {
        return identificationAdhesion;
    }

    public void setIdentificationAdhesion(String identificationAdhesion) {
        this.identificationAdhesion = identificationAdhesion == null ? null : identificationAdhesion.trim();
    }

    public String getNodulesBurrs() {
        return nodulesBurrs;
    }

    public void setNodulesBurrs(String nodulesBurrs) {
        this.nodulesBurrs = nodulesBurrs == null ? null : nodulesBurrs.trim();
    }

    public String getDarkOfHoleTinLead() {
        return darkOfHoleTinLead;
    }

    public void setDarkOfHoleTinLead(String darkOfHoleTinLead) {
        this.darkOfHoleTinLead = darkOfHoleTinLead == null ? null : darkOfHoleTinLead.trim();
    }

    public String getPadCocked() {
        return padCocked;
    }

    public void setPadCocked(String padCocked) {
        this.padCocked = padCocked == null ? null : padCocked.trim();
    }

    public String getHaloRing() {
        return haloRing;
    }

    public void setHaloRing(String haloRing) {
        this.haloRing = haloRing == null ? null : haloRing.trim();
    }

    public String getOuterRingWidth() {
        return outerRingWidth;
    }

    public void setOuterRingWidth(String outerRingWidth) {
        this.outerRingWidth = outerRingWidth == null ? null : outerRingWidth.trim();
    }

    public String getSolderInHole() {
        return solderInHole;
    }

    public void setSolderInHole(String solderInHole) {
        this.solderInHole = solderInHole == null ? null : solderInHole.trim();
    }

    public String getClogHole() {
        return clogHole;
    }

    public void setClogHole(String clogHole) {
        this.clogHole = clogHole == null ? null : clogHole.trim();
    }

    public String getLineWidthSpacing() {
        return lineWidthSpacing;
    }

    public void setLineWidthSpacing(String lineWidthSpacing) {
        this.lineWidthSpacing = lineWidthSpacing == null ? null : lineWidthSpacing.trim();
    }

    public String getPrintedBoardElse() {
        return printedBoardElse;
    }

    public void setPrintedBoardElse(String printedBoardElse) {
        this.printedBoardElse = printedBoardElse == null ? null : printedBoardElse.trim();
    }

    public String getBaseMaterialElse() {
        return baseMaterialElse;
    }

    public void setBaseMaterialElse(String baseMaterialElse) {
        this.baseMaterialElse = baseMaterialElse == null ? null : baseMaterialElse.trim();
    }

    public String getSolderMaskElse() {
        return solderMaskElse;
    }

    public void setSolderMaskElse(String solderMaskElse) {
        this.solderMaskElse = solderMaskElse == null ? null : solderMaskElse.trim();
    }

    public String getSpecialBoardNum() {
        return specialBoardNum;
    }

    public void setSpecialBoardNum(String specialBoardNum) {
        this.specialBoardNum = specialBoardNum == null ? null : specialBoardNum.trim();
    }

    public String getMarkElse() {
        return markElse;
    }

    public void setMarkElse(String markElse) {
        this.markElse = markElse == null ? null : markElse.trim();
    }

    public String getConductivePatternElse() {
        return conductivePatternElse;
    }

    public void setConductivePatternElse(String conductivePatternElse) {
        this.conductivePatternElse = conductivePatternElse == null ? null : conductivePatternElse.trim();
    }

    public String getBoardLongResult() {
        return boardLongResult;
    }

    public void setBoardLongResult(String boardLongResult) {
        this.boardLongResult = boardLongResult == null ? null : boardLongResult.trim();
    }

    public String getBoardWideResult() {
        return boardWideResult;
    }

    public void setBoardWideResult(String boardWideResult) {
        this.boardWideResult = boardWideResult == null ? null : boardWideResult.trim();
    }

    public String getBoardPlyResult() {
        return boardPlyResult;
    }

    public void setBoardPlyResult(String boardPlyResult) {
        this.boardPlyResult = boardPlyResult == null ? null : boardPlyResult.trim();
    }

    public String getSmoothnessResult() {
        return smoothnessResult;
    }

    public void setSmoothnessResult(String smoothnessResult) {
        this.smoothnessResult = smoothnessResult == null ? null : smoothnessResult.trim();
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