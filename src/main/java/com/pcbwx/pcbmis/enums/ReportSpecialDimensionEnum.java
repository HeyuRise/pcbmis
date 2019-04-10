package com.pcbwx.pcbmis.enums;
/**
 * 检验报告特殊尺寸项目名称
 * @author 孙贺宇
 *
 */
public enum ReportSpecialDimensionEnum {
	
	V_CUT_RESIDUAL_THICKNESS("vCutResidualThickness", "v_cut残厚"),
	V_CUT_DEVIATION("vCutDeviation", "v_cut偏差"),
	GOLDFINGER_BEVEL_DEPTH("goldfingerBevelDepth", "金手指倒角深度"),
	GOLDFINGER_RESIDUAL_THICKNESS("goldfingerResidualThickness", "金手指残厚"),
	STEP_WIDTH("stepWidth", "台阶宽度"),
	STEP_RESIDUAL_THICKNESS("stepResidualThickness", "台阶残厚"),
	STEPPED_HOLE_APERTURE("steppedHoleAperture", "台阶孔孔径"),
	STEPPED_HOLE_HOLE_DEPTH("steppedHoleHoleDepth", "台阶孔孔径");

	private String code;
	private String descr;
	
	private ReportSpecialDimensionEnum(String code, String descr) {
		this.code = code;
		this.descr = descr;
	}

	public String getCode() {
		return code;
	}

	public String getDescr() {
		return descr;
	}
}
