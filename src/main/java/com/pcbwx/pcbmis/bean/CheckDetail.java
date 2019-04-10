package com.pcbwx.pcbmis.bean;

import java.util.List;

import com.pcbwx.pcbmis.bean.request.CheckDataItem;
import com.pcbwx.pcbmis.bean.request.PcbCheckNoteBean;

public class CheckDetail {
	private String factory_result;
	private Integer factory_bad_num;
	private String factory_judge_result;
	private String board_prevent_smt_result;
	private Integer board_prevent_smt_bad_num;
	private String board_prevent_smt_judge_result;
	private String prevent_smt_color_result;
	private Integer prevent_smt_color_bad_num;
	private String prevent_smt_color_judge_result;
	private String board_character_result;
	private Integer board_character_bad_num;
	private String board_character_judge_result;
	private String character_color_result;
	private Integer character_color_bad_num;
	private String character_color_judge_result;
	private String logo_result;
	private Integer logo_bad_num;
	private String logo_judge_result;
	private String batch_number_result;
	private Integer batch_number_bad_num;
	private String batch_number_judge_result;
	private String surface_process_result;
	private Integer surface_process_bad_num;
	private String surface_process_judge_result;
	// -1.2新增过孔处理
	private CheckDataItem viaHoleProcess;
	// -
	private String boardWide_result;
	private Integer boardWide_bad_num;
	private String boardWide_judge_result;
	private String boardLong_result;
	private Integer boardLong_bad_num;
	private String boardLong_judge_result;
	private String boardPly_result;
	private Integer boardPly_bad_num;
	private String boardPly_judge_result;
	private String lay_height_result;
	private Integer lay_height_bad_num;
	private String lay_height_judge_result;
	private String warp_height_result;
	private Integer warp_height_bad_num;
	private String warp_height_judge_result;
	private String burrs_require; 				//-----------
	private String burrs_result;
	private Integer burrs_bad_num;
	private String burrs_judge_result;
	private String gap_require;//-----------
	private String gap_result;
	private Integer gap_bad_num;
	private String gap_judge_result;
	private String exposedCopper_require;//-----------
	private String exposedCopper_result;
	private Integer exposedCopper_bad_num;
	private String exposedCopper_judge_result;
	private String fabricTexture_require;//-----------
	private String fabricTexture_result;
	private Integer fabricTexture_bad_num;
	private String fabricTexture_judge_result;
	private String pitVoid_require;//-----------
	private String pitVoid_result;
	private Integer pitVoid_bad_num;
	private String pitVoid_judge_result;
	private String spotCrack_require;//-----------
	private String spotCrack_result;
	private Integer spotCrack_bad_num;
	private String spotCrack_judge_result;
	private String delaminationFoaming_require;//-----------
	private String delaminationFoaming_result;
	private Integer delaminationFoaming_bad_num;
	private String delaminationFoaming_judge_result;
	private String foreignImpurity_require;//-----------
	private String foreignImpurity_result;
	private Integer foreignImpurity_bad_num;
	private String foreignImpurity_judge_result;
	private String coverageAdhesion_require;//-----------
	private String coverageAdhesion_result;
	private Integer coverageAdhesion_bad_num;
	private String coverageAdhesion_judge_result;
	private String coincidenceDegree_require;//-----------
	private String coincidenceDegree_result;
	private Integer coincidenceDegree_bad_num;
	private String coincidenceDegree_judge_result;
	private String foamingLayering_require;//-----------
	private String foamingLayering_result;
	private Integer foamingLayering_bad_num;
	private String foamingLayering_judge_result;
	private String corrugation_require;//-----------
	private String corrugation_result;
	private Integer corrugation_bad_num;
	private String corrugation_judge_result;
	private String falseExposedCopper_require;//-----------
	private String falseExposedCopper_result;
	private Integer falseExposedCopper_bad_num;
	private String falseExposedCopper_judge_result;
	private String falseBridgeDam_require;//-----------
	private String falseBridgeDam_result;
	private Integer falseBridgeDam_bad_num;
	private String falseBridgeDam_judge_result;
	private String chromaticAberration_require;//-----------
	private String chromaticAberration_result;
	private Integer chromaticAberration_bad_num;
	private String chromaticAberration_judge_result;
	private String identificationAdhesion_require;//-----------
	private String identificationAdhesion_result;
	private Integer identificationAdhesion_bad_num;
	private String identificationAdhesion_judge_result;
	private String nodulesBurrs_require;//-----------
	private String nodulesBurrs_result;
	private Integer nodulesBurrs_bad_num;
	private String nodulesBurrs_judge_result;
	private String darkOfHoleTinLead_require;//-----------
	private String darkOfHoleTinLead_result;
	private Integer darkOfHoleTinLead_bad_num;
	private String darkOfHoleTinLead_judge_result;
	private String padCocked_require;//-----------
	private String padCocked_result;
	private Integer padCocked_bad_num;
	private String padCocked_judge_result;
	private String haloRing_require;//-----------
	private String haloRing_result;
	private Integer haloRing_bad_num;
	private String haloRing_judge_result;
	private String outerRingWidth_require;//-----------
	private String outerRingWidth_result;
	private Integer outerRingWidth_bad_num;
	private String outerRingWidth_judge_result;
	private String solderInHole_require;//-----------
	private String solderInHole_result;
	private Integer solderInHole_bad_num;
	private String solderInHole_judge_result;
	private String clogHole_require;//-----------
	private String clogHole_result;
	private Integer clogHole_bad_num;
	private String clogHole_judge_result;
	private String lineWidthSpacing_require;//-----------
	private String lineWidthSpacing_result;
	private Integer lineWidthSpacing_bad_num;
	private String lineWidthSpacing_judge_result;
	
	private String special_board_num_require;
	private String special_board_num_result;
	private Integer special_board_num_bad_num;
	private String special_board_num_judge_result;
	private String printed_board_else_require;
	private String printed_board_else_result;
	private Integer printed_board_else_bad_num;
	private String printed_board_else_judge_result;
	private String base_material_else_require;
	private String base_material_else_result;
	private Integer base_material_else_bad_num;
	private String base_material_else_judge_result;
	private String soldermask_else_require;
	private String soldermask_else_result;
	private Integer soldermask_else_bad_num;
	private String soldermask_else_judge_result;
	private String mark_else_require;
	private String mark_else_result;
	private Integer mark_else_bad_num;
	private String mark_else_judge_result;
	private String conductive_pattern_else_require;
	private String conductive_pattern_else_result;
	private Integer conductive_pattern_else_bad_num;
	private String conductive_pattern_else_judge_result;
	private String min_boardLong_tolerance;
	private String max_boardLong_tolerance;
	private String min_boardWide_tolerance;
	private String max_boardWide_tolerance;
	private String min_boardPly_tolerance;
	private String max_boardPly_tolerance;
	private PcbCheckNoteBean note;
	private List<SizeAndWarpDegree> sizeAndWarpDegree;
	public String getFactory_result() {
		return factory_result;
	}
	public void setFactory_result(String factory_result) {
		this.factory_result = factory_result;
	}
	public Integer getFactory_bad_num() {
		return factory_bad_num;
	}
	public void setFactory_bad_num(Integer factory_bad_num) {
		this.factory_bad_num = factory_bad_num;
	}
	public String getFactory_judge_result() {
		return factory_judge_result;
	}
	public void setFactory_judge_result(String factory_judge_result) {
		this.factory_judge_result = factory_judge_result;
	}
	public String getBoard_prevent_smt_result() {
		return board_prevent_smt_result;
	}
	public void setBoard_prevent_smt_result(String board_prevent_smt_result) {
		this.board_prevent_smt_result = board_prevent_smt_result;
	}
	public Integer getBoard_prevent_smt_bad_num() {
		return board_prevent_smt_bad_num;
	}
	public void setBoard_prevent_smt_bad_num(Integer board_prevent_smt_bad_num) {
		this.board_prevent_smt_bad_num = board_prevent_smt_bad_num;
	}
	public String getBoard_prevent_smt_judge_result() {
		return board_prevent_smt_judge_result;
	}
	public void setBoard_prevent_smt_judge_result(
			String board_prevent_smt_judge_result) {
		this.board_prevent_smt_judge_result = board_prevent_smt_judge_result;
	}
	public String getPrevent_smt_color_result() {
		return prevent_smt_color_result;
	}
	public void setPrevent_smt_color_result(String prevent_smt_color_result) {
		this.prevent_smt_color_result = prevent_smt_color_result;
	}
	public Integer getPrevent_smt_color_bad_num() {
		return prevent_smt_color_bad_num;
	}
	public void setPrevent_smt_color_bad_num(Integer prevent_smt_color_bad_num) {
		this.prevent_smt_color_bad_num = prevent_smt_color_bad_num;
	}
	public String getPrevent_smt_color_judge_result() {
		return prevent_smt_color_judge_result;
	}
	public void setPrevent_smt_color_judge_result(
			String prevent_smt_color_judge_result) {
		this.prevent_smt_color_judge_result = prevent_smt_color_judge_result;
	}
	public String getBoard_character_result() {
		return board_character_result;
	}
	public void setBoard_character_result(String board_character_result) {
		this.board_character_result = board_character_result;
	}
	public Integer getBoard_character_bad_num() {
		return board_character_bad_num;
	}
	public void setBoard_character_bad_num(Integer board_character_bad_num) {
		this.board_character_bad_num = board_character_bad_num;
	}
	public String getBoard_character_judge_result() {
		return board_character_judge_result;
	}
	public void setBoard_character_judge_result(String board_character_judge_result) {
		this.board_character_judge_result = board_character_judge_result;
	}
	public String getCharacter_color_result() {
		return character_color_result;
	}
	public void setCharacter_color_result(String character_color_result) {
		this.character_color_result = character_color_result;
	}
	public Integer getCharacter_color_bad_num() {
		return character_color_bad_num;
	}
	public void setCharacter_color_bad_num(Integer character_color_bad_num) {
		this.character_color_bad_num = character_color_bad_num;
	}
	public String getCharacter_color_judge_result() {
		return character_color_judge_result;
	}
	public void setCharacter_color_judge_result(String character_color_judge_result) {
		this.character_color_judge_result = character_color_judge_result;
	}
	public String getLogo_result() {
		return logo_result;
	}
	public void setLogo_result(String logo_result) {
		this.logo_result = logo_result;
	}
	public Integer getLogo_bad_num() {
		return logo_bad_num;
	}
	public void setLogo_bad_num(Integer logo_bad_num) {
		this.logo_bad_num = logo_bad_num;
	}
	public String getLogo_judge_result() {
		return logo_judge_result;
	}
	public void setLogo_judge_result(String logo_judge_result) {
		this.logo_judge_result = logo_judge_result;
	}
	public String getBatch_number_result() {
		return batch_number_result;
	}
	public void setBatch_number_result(String batch_number_result) {
		this.batch_number_result = batch_number_result;
	}
	public Integer getBatch_number_bad_num() {
		return batch_number_bad_num;
	}
	public void setBatch_number_bad_num(Integer batch_number_bad_num) {
		this.batch_number_bad_num = batch_number_bad_num;
	}
	public String getBatch_number_judge_result() {
		return batch_number_judge_result;
	}
	public void setBatch_number_judge_result(String batch_number_judge_result) {
		this.batch_number_judge_result = batch_number_judge_result;
	}
	public String getSurface_process_result() {
		return surface_process_result;
	}
	public void setSurface_process_result(String surface_process_result) {
		this.surface_process_result = surface_process_result;
	}
	public Integer getSurface_process_bad_num() {
		return surface_process_bad_num;
	}
	public void setSurface_process_bad_num(Integer surface_process_bad_num) {
		this.surface_process_bad_num = surface_process_bad_num;
	}
	public String getSurface_process_judge_result() {
		return surface_process_judge_result;
	}
	public void setSurface_process_judge_result(String surface_process_judge_result) {
		this.surface_process_judge_result = surface_process_judge_result;
	}
	public String getBoardWide_result() {
		return boardWide_result;
	}
	public void setBoardWide_result(String boardWide_result) {
		this.boardWide_result = boardWide_result;
	}
	public Integer getBoardWide_bad_num() {
		return boardWide_bad_num;
	}
	public void setBoardWide_bad_num(Integer boardWide_bad_num) {
		this.boardWide_bad_num = boardWide_bad_num;
	}
	public String getBoardWide_judge_result() {
		return boardWide_judge_result;
	}
	public void setBoardWide_judge_result(String boardWide_judge_result) {
		this.boardWide_judge_result = boardWide_judge_result;
	}
	public String getBoardLong_result() {
		return boardLong_result;
	}
	public void setBoardLong_result(String boardLong_result) {
		this.boardLong_result = boardLong_result;
	}
	public Integer getBoardLong_bad_num() {
		return boardLong_bad_num;
	}
	public void setBoardLong_bad_num(Integer boardLong_bad_num) {
		this.boardLong_bad_num = boardLong_bad_num;
	}
	public String getBoardLong_judge_result() {
		return boardLong_judge_result;
	}
	public void setBoardLong_judge_result(String boardLong_judge_result) {
		this.boardLong_judge_result = boardLong_judge_result;
	}
	public String getBoardPly_result() {
		return boardPly_result;
	}
	public void setBoardPly_result(String boardPly_result) {
		this.boardPly_result = boardPly_result;
	}
	public Integer getBoardPly_bad_num() {
		return boardPly_bad_num;
	}
	public void setBoardPly_bad_num(Integer boardPly_bad_num) {
		this.boardPly_bad_num = boardPly_bad_num;
	}
	public String getBoardPly_judge_result() {
		return boardPly_judge_result;
	}
	public void setBoardPly_judge_result(String boardPly_judge_result) {
		this.boardPly_judge_result = boardPly_judge_result;
	}
	public String getLay_height_result() {
		return lay_height_result;
	}
	public void setLay_height_result(String lay_height_result) {
		this.lay_height_result = lay_height_result;
	}
	public Integer getLay_height_bad_num() {
		return lay_height_bad_num;
	}
	public void setLay_height_bad_num(Integer lay_height_bad_num) {
		this.lay_height_bad_num = lay_height_bad_num;
	}
	public String getLay_height_judge_result() {
		return lay_height_judge_result;
	}
	public void setLay_height_judge_result(String lay_height_judge_result) {
		this.lay_height_judge_result = lay_height_judge_result;
	}
	public String getWarp_height_result() {
		return warp_height_result;
	}
	public void setWarp_height_result(String warp_height_result) {
		this.warp_height_result = warp_height_result;
	}
	public Integer getWarp_height_bad_num() {
		return warp_height_bad_num;
	}
	public void setWarp_height_bad_num(Integer warp_height_bad_num) {
		this.warp_height_bad_num = warp_height_bad_num;
	}
	public String getWarp_height_judge_result() {
		return warp_height_judge_result;
	}
	public void setWarp_height_judge_result(String warp_height_judge_result) {
		this.warp_height_judge_result = warp_height_judge_result;
	}
	public String getBurrs_result() {
		return burrs_result;
	}
	public void setBurrs_result(String burrs_result) {
		this.burrs_result = burrs_result;
	}
	public Integer getBurrs_bad_num() {
		return burrs_bad_num;
	}
	public void setBurrs_bad_num(Integer burrs_bad_num) {
		this.burrs_bad_num = burrs_bad_num;
	}
	public String getBurrs_judge_result() {
		return burrs_judge_result;
	}
	public void setBurrs_judge_result(String burrs_judge_result) {
		this.burrs_judge_result = burrs_judge_result;
	}
	public String getGap_result() {
		return gap_result;
	}
	public void setGap_result(String gap_result) {
		this.gap_result = gap_result;
	}
	public Integer getGap_bad_num() {
		return gap_bad_num;
	}
	public void setGap_bad_num(Integer gap_bad_num) {
		this.gap_bad_num = gap_bad_num;
	}
	public String getGap_judge_result() {
		return gap_judge_result;
	}
	public void setGap_judge_result(String gap_judge_result) {
		this.gap_judge_result = gap_judge_result;
	}
	public String getExposedCopper_result() {
		return exposedCopper_result;
	}
	public void setExposedCopper_result(String exposedCopper_result) {
		this.exposedCopper_result = exposedCopper_result;
	}
	public Integer getExposedCopper_bad_num() {
		return exposedCopper_bad_num;
	}
	public void setExposedCopper_bad_num(Integer exposedCopper_bad_num) {
		this.exposedCopper_bad_num = exposedCopper_bad_num;
	}
	public String getExposedCopper_judge_result() {
		return exposedCopper_judge_result;
	}
	public void setExposedCopper_judge_result(String exposedCopper_judge_result) {
		this.exposedCopper_judge_result = exposedCopper_judge_result;
	}
	public String getFabricTexture_result() {
		return fabricTexture_result;
	}
	public void setFabricTexture_result(String fabricTexture_result) {
		this.fabricTexture_result = fabricTexture_result;
	}
	public Integer getFabricTexture_bad_num() {
		return fabricTexture_bad_num;
	}
	public void setFabricTexture_bad_num(Integer fabricTexture_bad_num) {
		this.fabricTexture_bad_num = fabricTexture_bad_num;
	}
	public String getFabricTexture_judge_result() {
		return fabricTexture_judge_result;
	}
	public void setFabricTexture_judge_result(String fabricTexture_judge_result) {
		this.fabricTexture_judge_result = fabricTexture_judge_result;
	}
	public String getPitVoid_result() {
		return pitVoid_result;
	}
	public void setPitVoid_result(String pitVoid_result) {
		this.pitVoid_result = pitVoid_result;
	}
	public Integer getPitVoid_bad_num() {
		return pitVoid_bad_num;
	}
	public void setPitVoid_bad_num(Integer pitVoid_bad_num) {
		this.pitVoid_bad_num = pitVoid_bad_num;
	}
	public String getPitVoid_judge_result() {
		return pitVoid_judge_result;
	}
	public void setPitVoid_judge_result(String pitVoid_judge_result) {
		this.pitVoid_judge_result = pitVoid_judge_result;
	}
	public String getSpotCrack_result() {
		return spotCrack_result;
	}
	public void setSpotCrack_result(String spotCrack_result) {
		this.spotCrack_result = spotCrack_result;
	}
	public Integer getSpotCrack_bad_num() {
		return spotCrack_bad_num;
	}
	public void setSpotCrack_bad_num(Integer spotCrack_bad_num) {
		this.spotCrack_bad_num = spotCrack_bad_num;
	}
	public String getSpotCrack_judge_result() {
		return spotCrack_judge_result;
	}
	public void setSpotCrack_judge_result(String spotCrack_judge_result) {
		this.spotCrack_judge_result = spotCrack_judge_result;
	}
	public String getDelaminationFoaming_result() {
		return delaminationFoaming_result;
	}
	public void setDelaminationFoaming_result(String delaminationFoaming_result) {
		this.delaminationFoaming_result = delaminationFoaming_result;
	}
	public Integer getDelaminationFoaming_bad_num() {
		return delaminationFoaming_bad_num;
	}
	public void setDelaminationFoaming_bad_num(Integer delaminationFoaming_bad_num) {
		this.delaminationFoaming_bad_num = delaminationFoaming_bad_num;
	}
	public String getDelaminationFoaming_judge_result() {
		return delaminationFoaming_judge_result;
	}
	public void setDelaminationFoaming_judge_result(
			String delaminationFoaming_judge_result) {
		this.delaminationFoaming_judge_result = delaminationFoaming_judge_result;
	}
	public String getForeignImpurity_result() {
		return foreignImpurity_result;
	}
	public void setForeignImpurity_result(String foreignImpurity_result) {
		this.foreignImpurity_result = foreignImpurity_result;
	}
	public Integer getForeignImpurity_bad_num() {
		return foreignImpurity_bad_num;
	}
	public void setForeignImpurity_bad_num(Integer foreignImpurity_bad_num) {
		this.foreignImpurity_bad_num = foreignImpurity_bad_num;
	}
	public String getForeignImpurity_judge_result() {
		return foreignImpurity_judge_result;
	}
	public void setForeignImpurity_judge_result(String foreignImpurity_judge_result) {
		this.foreignImpurity_judge_result = foreignImpurity_judge_result;
	}
	public String getCoverageAdhesion_result() {
		return coverageAdhesion_result;
	}
	public void setCoverageAdhesion_result(String coverageAdhesion_result) {
		this.coverageAdhesion_result = coverageAdhesion_result;
	}
	public Integer getCoverageAdhesion_bad_num() {
		return coverageAdhesion_bad_num;
	}
	public void setCoverageAdhesion_bad_num(Integer coverageAdhesion_bad_num) {
		this.coverageAdhesion_bad_num = coverageAdhesion_bad_num;
	}
	public String getCoverageAdhesion_judge_result() {
		return coverageAdhesion_judge_result;
	}
	public void setCoverageAdhesion_judge_result(
			String coverageAdhesion_judge_result) {
		this.coverageAdhesion_judge_result = coverageAdhesion_judge_result;
	}
	public String getCoincidenceDegree_result() {
		return coincidenceDegree_result;
	}
	public void setCoincidenceDegree_result(String coincidenceDegree_result) {
		this.coincidenceDegree_result = coincidenceDegree_result;
	}
	public Integer getCoincidenceDegree_bad_num() {
		return coincidenceDegree_bad_num;
	}
	public void setCoincidenceDegree_bad_num(Integer coincidenceDegree_bad_num) {
		this.coincidenceDegree_bad_num = coincidenceDegree_bad_num;
	}
	public String getCoincidenceDegree_judge_result() {
		return coincidenceDegree_judge_result;
	}
	public void setCoincidenceDegree_judge_result(
			String coincidenceDegree_judge_result) {
		this.coincidenceDegree_judge_result = coincidenceDegree_judge_result;
	}
	public String getFoamingLayering_result() {
		return foamingLayering_result;
	}
	public void setFoamingLayering_result(String foamingLayering_result) {
		this.foamingLayering_result = foamingLayering_result;
	}
	public Integer getFoamingLayering_bad_num() {
		return foamingLayering_bad_num;
	}
	public void setFoamingLayering_bad_num(Integer foamingLayering_bad_num) {
		this.foamingLayering_bad_num = foamingLayering_bad_num;
	}
	public String getFoamingLayering_judge_result() {
		return foamingLayering_judge_result;
	}
	public void setFoamingLayering_judge_result(String foamingLayering_judge_result) {
		this.foamingLayering_judge_result = foamingLayering_judge_result;
	}
	public String getCorrugation_result() {
		return corrugation_result;
	}
	public void setCorrugation_result(String corrugation_result) {
		this.corrugation_result = corrugation_result;
	}
	public Integer getCorrugation_bad_num() {
		return corrugation_bad_num;
	}
	public void setCorrugation_bad_num(Integer corrugation_bad_num) {
		this.corrugation_bad_num = corrugation_bad_num;
	}
	public String getCorrugation_judge_result() {
		return corrugation_judge_result;
	}
	public void setCorrugation_judge_result(String corrugation_judge_result) {
		this.corrugation_judge_result = corrugation_judge_result;
	}
	public String getFalseExposedCopper_result() {
		return falseExposedCopper_result;
	}
	public void setFalseExposedCopper_result(String falseExposedCopper_result) {
		this.falseExposedCopper_result = falseExposedCopper_result;
	}
	public Integer getFalseExposedCopper_bad_num() {
		return falseExposedCopper_bad_num;
	}
	public void setFalseExposedCopper_bad_num(Integer falseExposedCopper_bad_num) {
		this.falseExposedCopper_bad_num = falseExposedCopper_bad_num;
	}
	public String getFalseExposedCopper_judge_result() {
		return falseExposedCopper_judge_result;
	}
	public void setFalseExposedCopper_judge_result(
			String falseExposedCopper_judge_result) {
		this.falseExposedCopper_judge_result = falseExposedCopper_judge_result;
	}
	public String getFalseBridgeDam_result() {
		return falseBridgeDam_result;
	}
	public void setFalseBridgeDam_result(String falseBridgeDam_result) {
		this.falseBridgeDam_result = falseBridgeDam_result;
	}
	public Integer getFalseBridgeDam_bad_num() {
		return falseBridgeDam_bad_num;
	}
	public void setFalseBridgeDam_bad_num(Integer falseBridgeDam_bad_num) {
		this.falseBridgeDam_bad_num = falseBridgeDam_bad_num;
	}
	public String getFalseBridgeDam_judge_result() {
		return falseBridgeDam_judge_result;
	}
	public void setFalseBridgeDam_judge_result(String falseBridgeDam_judge_result) {
		this.falseBridgeDam_judge_result = falseBridgeDam_judge_result;
	}
	public String getChromaticAberration_result() {
		return chromaticAberration_result;
	}
	public void setChromaticAberration_result(String chromaticAberration_result) {
		this.chromaticAberration_result = chromaticAberration_result;
	}
	public Integer getChromaticAberration_bad_num() {
		return chromaticAberration_bad_num;
	}
	public void setChromaticAberration_bad_num(Integer chromaticAberration_bad_num) {
		this.chromaticAberration_bad_num = chromaticAberration_bad_num;
	}
	public String getChromaticAberration_judge_result() {
		return chromaticAberration_judge_result;
	}
	public void setChromaticAberration_judge_result(
			String chromaticAberration_judge_result) {
		this.chromaticAberration_judge_result = chromaticAberration_judge_result;
	}
	public String getIdentificationAdhesion_result() {
		return identificationAdhesion_result;
	}
	public void setIdentificationAdhesion_result(
			String identificationAdhesion_result) {
		this.identificationAdhesion_result = identificationAdhesion_result;
	}
	public Integer getIdentificationAdhesion_bad_num() {
		return identificationAdhesion_bad_num;
	}
	public void setIdentificationAdhesion_bad_num(
			Integer identificationAdhesion_bad_num) {
		this.identificationAdhesion_bad_num = identificationAdhesion_bad_num;
	}
	public String getIdentificationAdhesion_judge_result() {
		return identificationAdhesion_judge_result;
	}
	public void setIdentificationAdhesion_judge_result(
			String identificationAdhesion_judge_result) {
		this.identificationAdhesion_judge_result = identificationAdhesion_judge_result;
	}
	public String getNodulesBurrs_result() {
		return nodulesBurrs_result;
	}
	public void setNodulesBurrs_result(String nodulesBurrs_result) {
		this.nodulesBurrs_result = nodulesBurrs_result;
	}
	public Integer getNodulesBurrs_bad_num() {
		return nodulesBurrs_bad_num;
	}
	public void setNodulesBurrs_bad_num(Integer nodulesBurrs_bad_num) {
		this.nodulesBurrs_bad_num = nodulesBurrs_bad_num;
	}
	public String getNodulesBurrs_judge_result() {
		return nodulesBurrs_judge_result;
	}
	public void setNodulesBurrs_judge_result(String nodulesBurrs_judge_result) {
		this.nodulesBurrs_judge_result = nodulesBurrs_judge_result;
	}
	public String getDarkOfHoleTinLead_result() {
		return darkOfHoleTinLead_result;
	}
	public void setDarkOfHoleTinLead_result(String darkOfHoleTinLead_result) {
		this.darkOfHoleTinLead_result = darkOfHoleTinLead_result;
	}
	public Integer getDarkOfHoleTinLead_bad_num() {
		return darkOfHoleTinLead_bad_num;
	}
	public void setDarkOfHoleTinLead_bad_num(Integer darkOfHoleTinLead_bad_num) {
		this.darkOfHoleTinLead_bad_num = darkOfHoleTinLead_bad_num;
	}
	public String getDarkOfHoleTinLead_judge_result() {
		return darkOfHoleTinLead_judge_result;
	}
	public void setDarkOfHoleTinLead_judge_result(
			String darkOfHoleTinLead_judge_result) {
		this.darkOfHoleTinLead_judge_result = darkOfHoleTinLead_judge_result;
	}
	public String getPadCocked_result() {
		return padCocked_result;
	}
	public void setPadCocked_result(String padCocked_result) {
		this.padCocked_result = padCocked_result;
	}
	public Integer getPadCocked_bad_num() {
		return padCocked_bad_num;
	}
	public void setPadCocked_bad_num(Integer padCocked_bad_num) {
		this.padCocked_bad_num = padCocked_bad_num;
	}
	public String getPadCocked_judge_result() {
		return padCocked_judge_result;
	}
	public void setPadCocked_judge_result(String padCocked_judge_result) {
		this.padCocked_judge_result = padCocked_judge_result;
	}
	public String getHaloRing_result() {
		return haloRing_result;
	}
	public void setHaloRing_result(String haloRing_result) {
		this.haloRing_result = haloRing_result;
	}
	public Integer getHaloRing_bad_num() {
		return haloRing_bad_num;
	}
	public void setHaloRing_bad_num(Integer haloRing_bad_num) {
		this.haloRing_bad_num = haloRing_bad_num;
	}
	public String getHaloRing_judge_result() {
		return haloRing_judge_result;
	}
	public void setHaloRing_judge_result(String haloRing_judge_result) {
		this.haloRing_judge_result = haloRing_judge_result;
	}
	public String getOuterRingWidth_result() {
		return outerRingWidth_result;
	}
	public void setOuterRingWidth_result(String outerRingWidth_result) {
		this.outerRingWidth_result = outerRingWidth_result;
	}
	public Integer getOuterRingWidth_bad_num() {
		return outerRingWidth_bad_num;
	}
	public void setOuterRingWidth_bad_num(Integer outerRingWidth_bad_num) {
		this.outerRingWidth_bad_num = outerRingWidth_bad_num;
	}
	public String getOuterRingWidth_judge_result() {
		return outerRingWidth_judge_result;
	}
	public void setOuterRingWidth_judge_result(String outerRingWidth_judge_result) {
		this.outerRingWidth_judge_result = outerRingWidth_judge_result;
	}
	public String getSolderInHole_result() {
		return solderInHole_result;
	}
	public void setSolderInHole_result(String solderInHole_result) {
		this.solderInHole_result = solderInHole_result;
	}
	public Integer getSolderInHole_bad_num() {
		return solderInHole_bad_num;
	}
	public void setSolderInHole_bad_num(Integer solderInHole_bad_num) {
		this.solderInHole_bad_num = solderInHole_bad_num;
	}
	public String getSolderInHole_judge_result() {
		return solderInHole_judge_result;
	}
	public void setSolderInHole_judge_result(String solderInHole_judge_result) {
		this.solderInHole_judge_result = solderInHole_judge_result;
	}
	public String getClogHole_result() {
		return clogHole_result;
	}
	public void setClogHole_result(String clogHole_result) {
		this.clogHole_result = clogHole_result;
	}
	public Integer getClogHole_bad_num() {
		return clogHole_bad_num;
	}
	public void setClogHole_bad_num(Integer clogHole_bad_num) {
		this.clogHole_bad_num = clogHole_bad_num;
	}
	public String getClogHole_judge_result() {
		return clogHole_judge_result;
	}
	public void setClogHole_judge_result(String clogHole_judge_result) {
		this.clogHole_judge_result = clogHole_judge_result;
	}
	public String getLineWidthSpacing_result() {
		return lineWidthSpacing_result;
	}
	public void setLineWidthSpacing_result(String lineWidthSpacing_result) {
		this.lineWidthSpacing_result = lineWidthSpacing_result;
	}
	public Integer getLineWidthSpacing_bad_num() {
		return lineWidthSpacing_bad_num;
	}
	public void setLineWidthSpacing_bad_num(Integer lineWidthSpacing_bad_num) {
		this.lineWidthSpacing_bad_num = lineWidthSpacing_bad_num;
	}
	public String getLineWidthSpacing_judge_result() {
		return lineWidthSpacing_judge_result;
	}
	public void setLineWidthSpacing_judge_result(
			String lineWidthSpacing_judge_result) {
		this.lineWidthSpacing_judge_result = lineWidthSpacing_judge_result;
	}
	public String getSpecial_board_num_require() {
		return special_board_num_require;
	}
	public void setSpecial_board_num_require(String special_board_num_require) {
		this.special_board_num_require = special_board_num_require;
	}
	public String getSpecial_board_num_result() {
		return special_board_num_result;
	}
	public void setSpecial_board_num_result(String special_board_num_result) {
		this.special_board_num_result = special_board_num_result;
	}
	public Integer getSpecial_board_num_bad_num() {
		return special_board_num_bad_num;
	}
	public void setSpecial_board_num_bad_num(Integer special_board_num_bad_num) {
		this.special_board_num_bad_num = special_board_num_bad_num;
	}
	public String getSpecial_board_num_judge_result() {
		return special_board_num_judge_result;
	}
	public void setSpecial_board_num_judge_result(
			String special_board_num_judge_result) {
		this.special_board_num_judge_result = special_board_num_judge_result;
	}
	public String getPrinted_board_else_require() {
		return printed_board_else_require;
	}
	public void setPrinted_board_else_require(String printed_board_else_require) {
		this.printed_board_else_require = printed_board_else_require;
	}
	public String getPrinted_board_else_result() {
		return printed_board_else_result;
	}
	public void setPrinted_board_else_result(String printed_board_else_result) {
		this.printed_board_else_result = printed_board_else_result;
	}
	public Integer getPrinted_board_else_bad_num() {
		return printed_board_else_bad_num;
	}
	public void setPrinted_board_else_bad_num(Integer printed_board_else_bad_num) {
		this.printed_board_else_bad_num = printed_board_else_bad_num;
	}
	public String getPrinted_board_else_judge_result() {
		return printed_board_else_judge_result;
	}
	public void setPrinted_board_else_judge_result(
			String printed_board_else_judge_result) {
		this.printed_board_else_judge_result = printed_board_else_judge_result;
	}
	public String getBase_material_else_require() {
		return base_material_else_require;
	}
	public void setBase_material_else_require(String base_material_else_require) {
		this.base_material_else_require = base_material_else_require;
	}
	public String getBase_material_else_result() {
		return base_material_else_result;
	}
	public void setBase_material_else_result(String base_material_else_result) {
		this.base_material_else_result = base_material_else_result;
	}
	public Integer getBase_material_else_bad_num() {
		return base_material_else_bad_num;
	}
	public void setBase_material_else_bad_num(Integer base_material_else_bad_num) {
		this.base_material_else_bad_num = base_material_else_bad_num;
	}
	public String getBase_material_else_judge_result() {
		return base_material_else_judge_result;
	}
	public void setBase_material_else_judge_result(
			String base_material_else_judge_result) {
		this.base_material_else_judge_result = base_material_else_judge_result;
	}
	public String getSoldermask_else_require() {
		return soldermask_else_require;
	}
	public void setSoldermask_else_require(String soldermask_else_require) {
		this.soldermask_else_require = soldermask_else_require;
	}
	public String getSoldermask_else_result() {
		return soldermask_else_result;
	}
	public void setSoldermask_else_result(String soldermask_else_result) {
		this.soldermask_else_result = soldermask_else_result;
	}
	public Integer getSoldermask_else_bad_num() {
		return soldermask_else_bad_num;
	}
	public void setSoldermask_else_bad_num(Integer soldermask_else_bad_num) {
		this.soldermask_else_bad_num = soldermask_else_bad_num;
	}
	public String getSoldermask_else_judge_result() {
		return soldermask_else_judge_result;
	}
	public void setSoldermask_else_judge_result(String soldermask_else_judge_result) {
		this.soldermask_else_judge_result = soldermask_else_judge_result;
	}
	public String getMark_else_require() {
		return mark_else_require;
	}
	public void setMark_else_require(String mark_else_require) {
		this.mark_else_require = mark_else_require;
	}
	public String getMark_else_result() {
		return mark_else_result;
	}
	public void setMark_else_result(String mark_else_result) {
		this.mark_else_result = mark_else_result;
	}
	public Integer getMark_else_bad_num() {
		return mark_else_bad_num;
	}
	public void setMark_else_bad_num(Integer mark_else_bad_num) {
		this.mark_else_bad_num = mark_else_bad_num;
	}
	public String getMark_else_judge_result() {
		return mark_else_judge_result;
	}
	public void setMark_else_judge_result(String mark_else_judge_result) {
		this.mark_else_judge_result = mark_else_judge_result;
	}
	public String getConductive_pattern_else_require() {
		return conductive_pattern_else_require;
	}
	public void setConductive_pattern_else_require(
			String conductive_pattern_else_require) {
		this.conductive_pattern_else_require = conductive_pattern_else_require;
	}
	public String getConductive_pattern_else_result() {
		return conductive_pattern_else_result;
	}
	public void setConductive_pattern_else_result(
			String conductive_pattern_else_result) {
		this.conductive_pattern_else_result = conductive_pattern_else_result;
	}
	public Integer getConductive_pattern_else_bad_num() {
		return conductive_pattern_else_bad_num;
	}
	public void setConductive_pattern_else_bad_num(
			Integer conductive_pattern_else_bad_num) {
		this.conductive_pattern_else_bad_num = conductive_pattern_else_bad_num;
	}
	public String getConductive_pattern_else_judge_result() {
		return conductive_pattern_else_judge_result;
	}
	public void setConductive_pattern_else_judge_result(
			String conductive_pattern_else_judge_result) {
		this.conductive_pattern_else_judge_result = conductive_pattern_else_judge_result;
	}
	
	public String getMin_boardLong_tolerance() {
		return min_boardLong_tolerance;
	}
	public void setMin_boardLong_tolerance(String min_boardLong_tolerance) {
		this.min_boardLong_tolerance = min_boardLong_tolerance;
	}
	public String getMax_boardLong_tolerance() {
		return max_boardLong_tolerance;
	}
	public void setMax_boardLong_tolerance(String max_boardLong_tolerance) {
		this.max_boardLong_tolerance = max_boardLong_tolerance;
	}
	public String getMin_boardWide_tolerance() {
		return min_boardWide_tolerance;
	}
	public void setMin_boardWide_tolerance(String min_boardWide_tolerance) {
		this.min_boardWide_tolerance = min_boardWide_tolerance;
	}
	public String getMax_boardWide_tolerance() {
		return max_boardWide_tolerance;
	}
	public void setMax_boardWide_tolerance(String max_boardWide_tolerance) {
		this.max_boardWide_tolerance = max_boardWide_tolerance;
	}
	public String getMin_boardPly_tolerance() {
		return min_boardPly_tolerance;
	}
	public void setMin_boardPly_tolerance(String min_boardPly_tolerance) {
		this.min_boardPly_tolerance = min_boardPly_tolerance;
	}
	public String getMax_boardPly_tolerance() {
		return max_boardPly_tolerance;
	}
	public void setMax_boardPly_tolerance(String max_boardPly_tolerance) {
		this.max_boardPly_tolerance = max_boardPly_tolerance;
	}
	public List<SizeAndWarpDegree> getSizeAndWarpDegree() {
		return sizeAndWarpDegree;
	}
	public void setSizeAndWarpDegree(List<SizeAndWarpDegree> sizeAndWarpDegree) {
		this.sizeAndWarpDegree = sizeAndWarpDegree;
	}
	public CheckDataItem getViaHoleProcess() {
		return viaHoleProcess;
	}
	public void setViaHoleProcess(CheckDataItem viaHoleProcess) {
		this.viaHoleProcess = viaHoleProcess;
	}
	public PcbCheckNoteBean getNote() {
		return note;
	}
	public void setNote(PcbCheckNoteBean note) {
		this.note = note;
	}
	public String getBurrs_require() {
		return burrs_require;
	}
	public void setBurrs_require(String burrs_require) {
		this.burrs_require = burrs_require;
	}
	public String getGap_require() {
		return gap_require;
	}
	public void setGap_require(String gap_require) {
		this.gap_require = gap_require;
	}
	public String getExposedCopper_require() {
		return exposedCopper_require;
	}
	public void setExposedCopper_require(String exposedCopper_require) {
		this.exposedCopper_require = exposedCopper_require;
	}
	public String getFabricTexture_require() {
		return fabricTexture_require;
	}
	public void setFabricTexture_require(String fabricTexture_require) {
		this.fabricTexture_require = fabricTexture_require;
	}
	public String getPitVoid_require() {
		return pitVoid_require;
	}
	public void setPitVoid_require(String pitVoid_require) {
		this.pitVoid_require = pitVoid_require;
	}
	public String getSpotCrack_require() {
		return spotCrack_require;
	}
	public void setSpotCrack_require(String spotCrack_require) {
		this.spotCrack_require = spotCrack_require;
	}
	public String getDelaminationFoaming_require() {
		return delaminationFoaming_require;
	}
	public void setDelaminationFoaming_require(String delaminationFoaming_require) {
		this.delaminationFoaming_require = delaminationFoaming_require;
	}
	public String getForeignImpurity_require() {
		return foreignImpurity_require;
	}
	public void setForeignImpurity_require(String foreignImpurity_require) {
		this.foreignImpurity_require = foreignImpurity_require;
	}
	public String getCoverageAdhesion_require() {
		return coverageAdhesion_require;
	}
	public void setCoverageAdhesion_require(String coverageAdhesion_require) {
		this.coverageAdhesion_require = coverageAdhesion_require;
	}
	public String getCoincidenceDegree_require() {
		return coincidenceDegree_require;
	}
	public void setCoincidenceDegree_require(String coincidenceDegree_require) {
		this.coincidenceDegree_require = coincidenceDegree_require;
	}
	public String getFoamingLayering_require() {
		return foamingLayering_require;
	}
	public void setFoamingLayering_require(String foamingLayering_require) {
		this.foamingLayering_require = foamingLayering_require;
	}
	public String getCorrugation_require() {
		return corrugation_require;
	}
	public void setCorrugation_require(String corrugation_require) {
		this.corrugation_require = corrugation_require;
	}
	public String getFalseExposedCopper_require() {
		return falseExposedCopper_require;
	}
	public void setFalseExposedCopper_require(String falseExposedCopper_require) {
		this.falseExposedCopper_require = falseExposedCopper_require;
	}
	public String getFalseBridgeDam_require() {
		return falseBridgeDam_require;
	}
	public void setFalseBridgeDam_require(String falseBridgeDam_require) {
		this.falseBridgeDam_require = falseBridgeDam_require;
	}
	public String getChromaticAberration_require() {
		return chromaticAberration_require;
	}
	public void setChromaticAberration_require(String chromaticAberration_require) {
		this.chromaticAberration_require = chromaticAberration_require;
	}
	public String getIdentificationAdhesion_require() {
		return identificationAdhesion_require;
	}
	public void setIdentificationAdhesion_require(String identificationAdhesion_require) {
		this.identificationAdhesion_require = identificationAdhesion_require;
	}
	public String getNodulesBurrs_require() {
		return nodulesBurrs_require;
	}
	public void setNodulesBurrs_require(String nodulesBurrs_require) {
		this.nodulesBurrs_require = nodulesBurrs_require;
	}
	public String getDarkOfHoleTinLead_require() {
		return darkOfHoleTinLead_require;
	}
	public void setDarkOfHoleTinLead_require(String darkOfHoleTinLead_require) {
		this.darkOfHoleTinLead_require = darkOfHoleTinLead_require;
	}
	public String getPadCocked_require() {
		return padCocked_require;
	}
	public void setPadCocked_require(String padCocked_require) {
		this.padCocked_require = padCocked_require;
	}
	public String getHaloRing_require() {
		return haloRing_require;
	}
	public void setHaloRing_require(String haloRing_require) {
		this.haloRing_require = haloRing_require;
	}
	public String getOuterRingWidth_require() {
		return outerRingWidth_require;
	}
	public void setOuterRingWidth_require(String outerRingWidth_require) {
		this.outerRingWidth_require = outerRingWidth_require;
	}
	public String getSolderInHole_require() {
		return solderInHole_require;
	}
	public void setSolderInHole_require(String solderInHole_require) {
		this.solderInHole_require = solderInHole_require;
	}
	public String getClogHole_require() {
		return clogHole_require;
	}
	public void setClogHole_require(String clogHole_require) {
		this.clogHole_require = clogHole_require;
	}
	public String getLineWidthSpacing_require() {
		return lineWidthSpacing_require;
	}
	public void setLineWidthSpacing_require(String lineWidthSpacing_require) {
		this.lineWidthSpacing_require = lineWidthSpacing_require;
	}

}
