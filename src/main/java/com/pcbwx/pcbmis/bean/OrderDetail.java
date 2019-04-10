package com.pcbwx.pcbmis.bean;

import java.math.BigDecimal;

import com.pcbwx.pcbmis.model.PcbCheckTemplate;

public class OrderDetail {
	private String orderNum;			//工单号
	private String factory;				//生产厂家
	private String board_prevent_smt;	//阻焊膜,印刷面
	private String prevent_smt_color;	//阻焊膜,颜色
	private String board_character;		//标记,印刷面
	private String character_color;		//标记,颜色
	private String logo;				//logo
	private String batch_number;		//批次号
	private String surface_process;		//表面处理
	private String viaHoleProcess;       // 过孔处理
	private BigDecimal boardLong;		//板长
    private BigDecimal boardWide;		//板宽
    private String boardPly;			//板厚
    private String boardLong_tolerance;			//公差
    private String boardWide_tolerance;			//公差
    private String boardPly_tolerance;			//公差
    private String Lay_height;					//弓曲高度
    private String warp_height;					//扭曲高度
//    private String templateName;				
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
    private String lineWidthSpacing;		//线宽、间距
    
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
    
    private Integer templateId;            // 模板Id
	public OrderDetail(PcbCheckTemplate pcbCheckTemplate) {
//		this.templateName = pcbCheckTemplate.getTemplateName();
		this.burrs = pcbCheckTemplate.getBurrs();
		this.gap = pcbCheckTemplate.getGap();
		this.exposedCopper = pcbCheckTemplate.getExposedCopper();
		this.fabricTexture = pcbCheckTemplate.getFabricTexture();
		this.pitVoid = pcbCheckTemplate.getPitVoid();
		this.spotCrack = pcbCheckTemplate.getSpotCrack();
		this.delaminationFoaming = pcbCheckTemplate.getDelaminationFoaming();
		this.foreignImpurity = pcbCheckTemplate.getForeignImpurity();
		this.coverageAdhesion = pcbCheckTemplate.getCoverageAdhesion();
		this.coincidenceDegree = pcbCheckTemplate.getCoincidenceDegree();
		this.foamingLayering = pcbCheckTemplate.getFoamingLayering();
		this.corrugation = pcbCheckTemplate.getCorrugation();
		this.falseExposedCopper = pcbCheckTemplate.getFalseExposedCopper();
		this.falseBridgeDam = pcbCheckTemplate.getFalseBridgeDam();
		this.chromaticAberration = pcbCheckTemplate.getChromaticAberration();
		this.identificationAdhesion = pcbCheckTemplate.getIdentificationAdhesion();
		this.nodulesBurrs = pcbCheckTemplate.getNodulesBurrs();
		this.darkOfHoleTinLead = pcbCheckTemplate.getDarkOfHoleTinLead();
		this.padCocked = pcbCheckTemplate.getPadCocked();
		this.haloRing = pcbCheckTemplate.getHaloRing();
		this.outerRingWidth = pcbCheckTemplate.getOuterRingWidth();
		this.solderInHole = pcbCheckTemplate.getSolderInHole();
		this.clogHole = pcbCheckTemplate.getClogHole();
		this.lineWidthSpacing = pcbCheckTemplate.getLineWidthSpacing();
		this.printedBoardElse = pcbCheckTemplate.getPrintedBoardElse();
		this.baseMaterialElse = pcbCheckTemplate.getBaseMaterialElse();
		this.solderMaskElse = pcbCheckTemplate.getSolderMaskElse();
		this.specialBoardNum = pcbCheckTemplate.getSpecialBoardNum();
		this.markElse = pcbCheckTemplate.getMarkElse();
		this.conductivePatternElse = pcbCheckTemplate.getConductivePatternElse();
		this.boardLongResult = pcbCheckTemplate.getBoardLongResult();
		this.boardWideResult = pcbCheckTemplate.getBoardWideResult();
		this.boardPlyResult = pcbCheckTemplate.getBoardPlyResult();
		this.smoothnessResult = pcbCheckTemplate.getSmoothnessResult();
		this.templateId = pcbCheckTemplate.getId();
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public String getBoard_prevent_smt() {
		return board_prevent_smt;
	}
	public void setBoard_prevent_smt(String board_prevent_smt) {
		this.board_prevent_smt = board_prevent_smt;
	}
	public String getPrevent_smt_color() {
		return prevent_smt_color;
	}
	public void setPrevent_smt_color(String prevent_smt_color) {
		this.prevent_smt_color = prevent_smt_color;
	}
	public String getBoard_character() {
		return board_character;
	}
	public void setBoard_character(String board_character) {
		this.board_character = board_character;
	}
	public String getCharacter_color() {
		return character_color;
	}
	public void setCharacter_color(String character_color) {
		this.character_color = character_color;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getBatch_number() {
		return batch_number;
	}
	public void setBatch_number(String batch_number) {
		this.batch_number = batch_number;
	}
	public String getSurface_process() {
		return surface_process;
	}
	public void setSurface_process(String surface_process) {
		this.surface_process = surface_process;
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
		this.boardPly = boardPly;
	}
	public String getBoardLong_tolerance() {
		return boardLong_tolerance;
	}
	public void setBoardLong_tolerance(String boardLong_tolerance) {
		this.boardLong_tolerance = boardLong_tolerance;
	}
	public String getBoardWide_tolerance() {
		return boardWide_tolerance;
	}
	public void setBoardWide_tolerance(String boardWide_tolerance) {
		this.boardWide_tolerance = boardWide_tolerance;
	}
	public String getBoardPly_tolerance() {
		return boardPly_tolerance;
	}
	public void setBoardPly_tolerance(String boardPly_tolerance) {
		this.boardPly_tolerance = boardPly_tolerance;
	}
	public String getLay_height() {
		return Lay_height;
	}
	public void setLay_height(String lay_height) {
		Lay_height = lay_height;
	}
	public String getWarp_height() {
		return warp_height;
	}
	public void setWarp_height(String warp_height) {
		this.warp_height = warp_height;
	}
//	public String getTemplateName() {
//		return templateName;
//	}
//	public void setTemplateName(String templateName) {
//		this.templateName = templateName;
//	}
	public String getBurrs() {
		return burrs;
	}
	public void setBurrs(String burrs) {
		this.burrs = burrs;
	}
	public String getGap() {
		return gap;
	}
	public void setGap(String gap) {
		this.gap = gap;
	}
	public String getExposedCopper() {
		return exposedCopper;
	}
	public void setExposedCopper(String exposedCopper) {
		this.exposedCopper = exposedCopper;
	}
	public String getFabricTexture() {
		return fabricTexture;
	}
	public void setFabricTexture(String fabricTexture) {
		this.fabricTexture = fabricTexture;
	}
	public String getPitVoid() {
		return pitVoid;
	}
	public void setPitVoid(String pitVoid) {
		this.pitVoid = pitVoid;
	}
	public String getSpotCrack() {
		return spotCrack;
	}
	public void setSpotCrack(String spotCrack) {
		this.spotCrack = spotCrack;
	}
	public String getDelaminationFoaming() {
		return delaminationFoaming;
	}
	public void setDelaminationFoaming(String delaminationFoaming) {
		this.delaminationFoaming = delaminationFoaming;
	}
	public String getForeignImpurity() {
		return foreignImpurity;
	}
	public void setForeignImpurity(String foreignImpurity) {
		this.foreignImpurity = foreignImpurity;
	}
	public String getCoverageAdhesion() {
		return coverageAdhesion;
	}
	public void setCoverageAdhesion(String coverageAdhesion) {
		this.coverageAdhesion = coverageAdhesion;
	}
	public String getCoincidenceDegree() {
		return coincidenceDegree;
	}
	public void setCoincidenceDegree(String coincidenceDegree) {
		this.coincidenceDegree = coincidenceDegree;
	}
	public String getFoamingLayering() {
		return foamingLayering;
	}
	public void setFoamingLayering(String foamingLayering) {
		this.foamingLayering = foamingLayering;
	}
	public String getCorrugation() {
		return corrugation;
	}
	public void setCorrugation(String corrugation) {
		this.corrugation = corrugation;
	}
	public String getFalseExposedCopper() {
		return falseExposedCopper;
	}
	public void setFalseExposedCopper(String falseExposedCopper) {
		this.falseExposedCopper = falseExposedCopper;
	}
	public String getFalseBridgeDam() {
		return falseBridgeDam;
	}
	public void setFalseBridgeDam(String falseBridgeDam) {
		this.falseBridgeDam = falseBridgeDam;
	}
	public String getChromaticAberration() {
		return chromaticAberration;
	}
	public void setChromaticAberration(String chromaticAberration) {
		this.chromaticAberration = chromaticAberration;
	}
	public String getIdentificationAdhesion() {
		return identificationAdhesion;
	}
	public void setIdentificationAdhesion(String identificationAdhesion) {
		this.identificationAdhesion = identificationAdhesion;
	}
	public String getNodulesBurrs() {
		return nodulesBurrs;
	}
	public void setNodulesBurrs(String nodulesBurrs) {
		this.nodulesBurrs = nodulesBurrs;
	}
	public String getDarkOfHoleTinLead() {
		return darkOfHoleTinLead;
	}
	public void setDarkOfHoleTinLead(String darkOfHoleTinLead) {
		this.darkOfHoleTinLead = darkOfHoleTinLead;
	}
	public String getPadCocked() {
		return padCocked;
	}
	public void setPadCocked(String padCocked) {
		this.padCocked = padCocked;
	}
	public String getHaloRing() {
		return haloRing;
	}
	public void setHaloRing(String haloRing) {
		this.haloRing = haloRing;
	}
	public String getOuterRingWidth() {
		return outerRingWidth;
	}
	public void setOuterRingWidth(String outerRingWidth) {
		this.outerRingWidth = outerRingWidth;
	}
	public String getSolderInHole() {
		return solderInHole;
	}
	public void setSolderInHole(String solderInHole) {
		this.solderInHole = solderInHole;
	}
	public String getClogHole() {
		return clogHole;
	}
	public void setClogHole(String clogHole) {
		this.clogHole = clogHole;
	}
	public String getLineWidthSpacing() {
		return lineWidthSpacing;
	}
	public void setLineWidthSpacing(String lineWidthSpacing) {
		this.lineWidthSpacing = lineWidthSpacing;
	}
	public String getViaHoleProcess() {
		return viaHoleProcess;
	}
	public void setViaHoleProcess(String viaHoleProcess) {
		this.viaHoleProcess = viaHoleProcess;
	}
	public String getPrintedBoardElse() {
		return printedBoardElse;
	}
	public void setPrintedBoardElse(String printedBoardElse) {
		this.printedBoardElse = printedBoardElse;
	}
	public String getBaseMaterialElse() {
		return baseMaterialElse;
	}
	public void setBaseMaterialElse(String baseMaterialElse) {
		this.baseMaterialElse = baseMaterialElse;
	}
	public String getSolderMaskElse() {
		return solderMaskElse;
	}
	public void setSolderMaskElse(String solderMaskElse) {
		this.solderMaskElse = solderMaskElse;
	}
	public String getSpecialBoardNum() {
		return specialBoardNum;
	}
	public void setSpecialBoardNum(String specialBoardNum) {
		this.specialBoardNum = specialBoardNum;
	}
	public String getMarkElse() {
		return markElse;
	}
	public void setMarkElse(String markElse) {
		this.markElse = markElse;
	}
	public String getConductivePatternElse() {
		return conductivePatternElse;
	}
	public void setConductivePatternElse(String conductivePatternElse) {
		this.conductivePatternElse = conductivePatternElse;
	}
	public String getBoardLongResult() {
		return boardLongResult;
	}
	public void setBoardLongResult(String boardLongResult) {
		this.boardLongResult = boardLongResult;
	}
	public String getBoardWideResult() {
		return boardWideResult;
	}
	public void setBoardWideResult(String boardWideResult) {
		this.boardWideResult = boardWideResult;
	}
	public String getBoardPlyResult() {
		return boardPlyResult;
	}
	public void setBoardPlyResult(String boardPlyResult) {
		this.boardPlyResult = boardPlyResult;
	}
	public String getSmoothnessResult() {
		return smoothnessResult;
	}
	public void setSmoothnessResult(String smoothnessResult) {
		this.smoothnessResult = smoothnessResult;
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
}
