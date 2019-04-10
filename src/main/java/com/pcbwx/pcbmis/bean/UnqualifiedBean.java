package com.pcbwx.pcbmis.bean;

public class UnqualifiedBean {

    private Integer unqualifiedId;

    private String orderNumber;

    private String unqualifiedBatch;

    private String unqualifiedDesc;

    private String inspector;

    private String unqualifiedSource;

    private String submitTime;

    private String productType;

    private Integer productLevel;

    private int[] images;

    public String getUnqualifiedDesc() {
        return unqualifiedDesc;
    }

    public void setUnqualifiedDesc(String unqualifiedDesc) {
        this.unqualifiedDesc = unqualifiedDesc;
    }

    public int[] getImages() {
        return images;
    }

    public void setImages(int[] images) {
        this.images = images;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getProductLevel() {
        return productLevel;
    }

    public void setProductLevel(Integer productLevel) {
        this.productLevel = productLevel;
    }

    public String getUnqualifiedSource() {
        return unqualifiedSource;
    }

    public void setUnqualifiedSource(String unqualifiedSource) {
        this.unqualifiedSource = unqualifiedSource;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getUnqualifiedId() {
        return unqualifiedId;
    }

    public void setUnqualifiedId(Integer unqualifiedId) {
        this.unqualifiedId = unqualifiedId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getUnqualifiedBatch() {
        return unqualifiedBatch;
    }

    public void setUnqualifiedBatch(String unqualifiedBatch) {
        this.unqualifiedBatch = unqualifiedBatch;
    }
}
