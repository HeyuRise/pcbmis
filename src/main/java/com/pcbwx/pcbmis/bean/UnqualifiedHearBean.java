package com.pcbwx.pcbmis.bean;

public class UnqualifiedHearBean {

    Integer unqualifiedId;

    /**
     * 审核意见
     */
    private String adjudicatingOpinions;

    /**
     * 处置方式
     */
    private int[] disposalMethod;

    /**
     * 审理日期
     */
    private String trialTime;

    /**
     * 批准日期
     */
    private String approvalTime;

    /**
     * 审核人员
     */
    private String trialPersonnel;

    /**
     * 批准人员
     */
    private String approvalPersonnel;

    public Integer getUnqualifiedId() {
        return unqualifiedId;
    }

    public void setUnqualifiedId(Integer unqualifiedId) {
        this.unqualifiedId = unqualifiedId;
    }

    public String getAdjudicatingOpinions() {
        return adjudicatingOpinions;
    }

    public void setAdjudicatingOpinions(String adjudicatingOpinions) {
        this.adjudicatingOpinions = adjudicatingOpinions;
    }

    public int[] getDisposalMethod() {
        return disposalMethod;
    }

    public void setDisposalMethod(int[] disposalMethod) {
        this.disposalMethod = disposalMethod;
    }

    public String getTrialTime() {
        return trialTime;
    }

    public void setTrialTime(String trialTime) {
        this.trialTime = trialTime;
    }

    public String getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(String approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getTrialPersonnel() {
        return trialPersonnel;
    }

    public void setTrialPersonnel(String trialPersonnel) {
        this.trialPersonnel = trialPersonnel;
    }

    public String getApprovalPersonnel() {
        return approvalPersonnel;
    }

    public void setApprovalPersonnel(String approvalPersonnel) {
        this.approvalPersonnel = approvalPersonnel;
    }
}
