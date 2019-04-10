package com.pcbwx.pcbmis.bean;

import java.util.List;
/**
 * 特殊尺寸
 * @author 孙贺宇
 *
 */
public class ReportSpecialDimension {
	private ReportSpecialDimensionItem vCutResidualThickness;			// v_cut残厚
	private ReportSpecialDimensionItem vCutDeviation;					// v_cut偏差
	private ReportSpecialDimensionItem goldfingerBevelDepth;			// 金手指倒角深度
	private ReportSpecialDimensionItem goldfingerResidualThickness;		// 金手指残厚
	private ReportSpecialDimensionItem stepWidth;						// 台阶宽度
	private ReportSpecialDimensionItem stepResidualThickness;			// 台阶残厚
	private ReportSpecialDimensionItem steppedHoleAperture;				// 台阶孔孔径
	private ReportSpecialDimensionItem steppedHoleHoleDepth;			// 台阶孔孔径
	private List<ReportSpecialDimensionItem> rests;
	public ReportSpecialDimensionItem getvCutResidualThickness() {
		return vCutResidualThickness;
	}
	public void setvCutResidualThickness(
			ReportSpecialDimensionItem vCutResidualThickness) {
		this.vCutResidualThickness = vCutResidualThickness;
	}
	public ReportSpecialDimensionItem getvCutDeviation() {
		return vCutDeviation;
	}
	public void setvCutDeviation(ReportSpecialDimensionItem vCutDeviation) {
		this.vCutDeviation = vCutDeviation;
	}
	public ReportSpecialDimensionItem getGoldfingerBevelDepth() {
		return goldfingerBevelDepth;
	}
	public void setGoldfingerBevelDepth(
			ReportSpecialDimensionItem goldfingerBevelDepth) {
		this.goldfingerBevelDepth = goldfingerBevelDepth;
	}
	public ReportSpecialDimensionItem getGoldfingerResidualThickness() {
		return goldfingerResidualThickness;
	}
	public void setGoldfingerResidualThickness(
			ReportSpecialDimensionItem goldfingerResidualThickness) {
		this.goldfingerResidualThickness = goldfingerResidualThickness;
	}
	public ReportSpecialDimensionItem getStepWidth() {
		return stepWidth;
	}
	public void setStepWidth(ReportSpecialDimensionItem stepWidth) {
		this.stepWidth = stepWidth;
	}
	public ReportSpecialDimensionItem getStepResidualThickness() {
		return stepResidualThickness;
	}
	public void setStepResidualThickness(
			ReportSpecialDimensionItem stepResidualThickness) {
		this.stepResidualThickness = stepResidualThickness;
	}
	public ReportSpecialDimensionItem getSteppedHoleAperture() {
		return steppedHoleAperture;
	}
	public void setSteppedHoleAperture(
			ReportSpecialDimensionItem steppedHoleAperture) {
		this.steppedHoleAperture = steppedHoleAperture;
	}
	public ReportSpecialDimensionItem getSteppedHoleHoleDepth() {
		return steppedHoleHoleDepth;
	}
	public void setSteppedHoleHoleDepth(
			ReportSpecialDimensionItem steppedHoleHoleDepth) {
		this.steppedHoleHoleDepth = steppedHoleHoleDepth;
	}
	public List<ReportSpecialDimensionItem> getRests() {
		return rests;
	}
	public void setRests(List<ReportSpecialDimensionItem> rests) {
		this.rests = rests;
	}
}
