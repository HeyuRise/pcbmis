package com.pcbwx.pcbmis.bean;
/**
 * 检验报告V_cut检验
 * @author 孙贺宇
 *
 */
public class ReportVcut {
    private String requireAngle;			// &要求   										
    private String requireH;				// h要求
    private String requireB;				// b要求
    private String requireBtolerance;		// b要求公差
    private String realCheakAngle;			// &实测
    private String realCheckH;				// h实测
    private String realCheckB;				// b实测
    private String judgeAngle;				// &判定
    private String judgeH;					// h判定
    private String judgeB;					// b判定
	public String getRequireAngle() {
		return requireAngle;
	}
	public void setRequireAngle(String requireAngle) {
		this.requireAngle = requireAngle;
	}
	public String getRequireH() {
		return requireH;
	}
	public void setRequireH(String requireH) {
		this.requireH = requireH;
	}
	public String getRequireB() {
		return requireB;
	}
	public void setRequireB(String requireB) {
		this.requireB = requireB;
	}
	public String getRequireBtolerance() {
		return requireBtolerance;
	}
	public void setRequireBtolerance(String requireBtolerance) {
		this.requireBtolerance = requireBtolerance;
	}
	public String getRealCheakAngle() {
		return realCheakAngle;
	}
	public void setRealCheakAngle(String realCheakAngle) {
		this.realCheakAngle = realCheakAngle;
	}
	public String getRealCheckH() {
		return realCheckH;
	}
	public void setRealCheckH(String realCheckH) {
		this.realCheckH = realCheckH;
	}
	public String getRealCheckB() {
		return realCheckB;
	}
	public void setRealCheckB(String realCheckB) {
		this.realCheckB = realCheckB;
	}
	public String getJudgeAngle() {
		return judgeAngle;
	}
	public void setJudgeAngle(String judgeAngle) {
		this.judgeAngle = judgeAngle;
	}
	public String getJudgeH() {
		return judgeH;
	}
	public void setJudgeH(String judgeH) {
		this.judgeH = judgeH;
	}
	public String getJudgeB() {
		return judgeB;
	}
	public void setJudgeB(String judgeB) {
		this.judgeB = judgeB;
	}
	
}
