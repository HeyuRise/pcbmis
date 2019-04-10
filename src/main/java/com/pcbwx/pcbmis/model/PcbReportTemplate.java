package com.pcbwx.pcbmis.model;

import java.util.Date;

public class PcbReportTemplate {
    private Integer id;

    private Integer templateId;

    private String templateName;

    private String baseMaterialAppearance;

    private String conductivePattern;

    private String preventSmtAppearance;

    private String characterAppearance;

    private String externalCoatingAdhesion;

    private String preventSmtCharacterAdhesion;

    private String solderability;

    private String preventSmtType;

    private String characterType;

    private String aperture;

    private String specialDimension;

    private String circuit;

    private String specialImpedance;

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

    public String getBaseMaterialAppearance() {
        return baseMaterialAppearance;
    }

    public void setBaseMaterialAppearance(String baseMaterialAppearance) {
        this.baseMaterialAppearance = baseMaterialAppearance == null ? null : baseMaterialAppearance.trim();
    }

    public String getConductivePattern() {
        return conductivePattern;
    }

    public void setConductivePattern(String conductivePattern) {
        this.conductivePattern = conductivePattern == null ? null : conductivePattern.trim();
    }

    public String getPreventSmtAppearance() {
        return preventSmtAppearance;
    }

    public void setPreventSmtAppearance(String preventSmtAppearance) {
        this.preventSmtAppearance = preventSmtAppearance == null ? null : preventSmtAppearance.trim();
    }

    public String getCharacterAppearance() {
        return characterAppearance;
    }

    public void setCharacterAppearance(String characterAppearance) {
        this.characterAppearance = characterAppearance == null ? null : characterAppearance.trim();
    }

    public String getExternalCoatingAdhesion() {
        return externalCoatingAdhesion;
    }

    public void setExternalCoatingAdhesion(String externalCoatingAdhesion) {
        this.externalCoatingAdhesion = externalCoatingAdhesion == null ? null : externalCoatingAdhesion.trim();
    }

    public String getPreventSmtCharacterAdhesion() {
        return preventSmtCharacterAdhesion;
    }

    public void setPreventSmtCharacterAdhesion(String preventSmtCharacterAdhesion) {
        this.preventSmtCharacterAdhesion = preventSmtCharacterAdhesion == null ? null : preventSmtCharacterAdhesion.trim();
    }

    public String getSolderability() {
        return solderability;
    }

    public void setSolderability(String solderability) {
        this.solderability = solderability == null ? null : solderability.trim();
    }

    public String getPreventSmtType() {
        return preventSmtType;
    }

    public void setPreventSmtType(String preventSmtType) {
        this.preventSmtType = preventSmtType == null ? null : preventSmtType.trim();
    }

    public String getCharacterType() {
        return characterType;
    }

    public void setCharacterType(String characterType) {
        this.characterType = characterType == null ? null : characterType.trim();
    }

    public String getAperture() {
        return aperture;
    }

    public void setAperture(String aperture) {
        this.aperture = aperture == null ? null : aperture.trim();
    }

    public String getSpecialDimension() {
        return specialDimension;
    }

    public void setSpecialDimension(String specialDimension) {
        this.specialDimension = specialDimension == null ? null : specialDimension.trim();
    }

    public String getCircuit() {
        return circuit;
    }

    public void setCircuit(String circuit) {
        this.circuit = circuit == null ? null : circuit.trim();
    }

    public String getSpecialImpedance() {
        return specialImpedance;
    }

    public void setSpecialImpedance(String specialImpedance) {
        this.specialImpedance = specialImpedance == null ? null : specialImpedance.trim();
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