/**
 * 
 */
package com.pcbwx.pcbmis.enums;

/**
 * 检验模板字段枚举类
 * 
 * @author 孙贺宇
 *
 */
public enum CheckTemplateOptionEnum {
	
	BURRS("burrs", "毛刺"),
	GAP("gap", "缺口"),
	EXPOSEDCOPPER("exposedCopper", "露铜"),
	FABRICTEXTURE("fabricTexture", "露织物、显布纹"),
	PITVOID("pitVoid", "麻点和空洞"),
	SPOTCRACK("spotCrack", "白斑、微裂纹"),
	DELAMINATIONFOAMING("delaminationFoaming", "分层、起泡"),
	FOREIGNIMPURITY("foreignImpurity", "外来夹杂物"),
	COVERAGEADHESION("coverageAdhesion", "覆盖度、附着力"),
	COINCIDENCEDEGREE("coincidenceDegree", "重合度"),
	FOAMINGLAYERING("foamingLayering", "起泡、分层"),
	CORRUGATION("corrugation", "波纹、皱褶"),
	FALSEEXPOSEDCOPPER("falseExposedCopper", "假性露铜"),
	FALSEBRIDGEDAM("falseBridgeDam", "掉桥、掉坝"),
	CHROMATICABERRATION("chromaticAberration", "色差"),
	IDENTIFICATIONADHESION("identificationAdhesion", "辨识度、附着力"),
	NODULESBURRS("nodulesBurrs", "结瘤、毛刺"),
	DARKOFHOLETINLEAD("darkOfHoleTinLead", "孔内铅锡发暗"),
	PADCOCKED("padCocked", "焊盘起翘"),
	HALORING("haloRing", "晕圈"),
	OUTERRINGWIDTH("outerRingWidth", "外层环宽"),
	SOLDERINHOLE("solderInHole", "锡珠入孔"),
	CLOGHOLE("clogHole", "堵孔"),
	LINEWIDTHSPACING("lineWidthSpacing", "线宽、间距"),

	SPECIAL_BOARD_NUM("special_board_num", "标记特殊板号"),
	PRINTED_BOARD_ELSE("printed_board_else", "印制板边缘其他缺陷"),
	BASE_MATERIAL_ELSE("base_material_else", "基材其他缺陷"),
	SOLDERMASK_ELSE("soldermask_else", "阻焊模其缺陷"),
	MARK_ELSE("mark_else", "标记其他缺陷"),
	CONDUCTIVE_PATTERN_ELSE("conductive_pattern_else", "导电图形其他缺陷"),
	BOARDLONG("boardLong", "板长"),
	BOARDWIDE("boardWide", "板宽"),
	BOARDPLY("boardPly", "板厚"),
	LAY_HEIGHT("Lay_height", "弓区高度"),
	WARP_HEIGHT("warp_height", "扭曲高度");

	private String code;
	private String desc;

	private CheckTemplateOptionEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
