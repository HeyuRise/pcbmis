package com.pcbwx.pcbmis.bean;

import java.util.List;

import com.pcbwx.pcbmis.bean.request.PcbReportNoteBean;

public class ReportDetail {
	private ReportIntro reportIntro;													// 检验报告
	private List<ReportApertureCheck> reportApertureChecks;								// 孔径检测
	private ReportSpecialDimension reportSpecialDimension;								// 特殊尺寸
	private ReportVcut reportVcut;														// V_cut检验
	private ReportIntegrity reportIntegrity;											// 完整性报告
	private List<ReportSpecialImpedance> single_ended_impedances;						// 单端阻抗
	private List<ReportSpecialImpedance> differential_characteristic_impedance;			// 差分阻抗
	private ReportCertification reportCertification;									// 合格证
	private BaseReportInfo baseReportInfo;
	private PcbReportNoteBean note;
	private ReportItem base_material_types; 				//基材类型
	private ReportItem base_material_appearance;			//基材外观
	private ReportItem conductive_pattern;					//导电图形
	private ReportItem prevent_smt_type;					//阻焊类型
	private ReportItem prevent_smt_appearance;				//阻焊外观
	private ReportItem character_type;						//字符类型
	private ReportItem character_appearance;				//字符外观
	private ReportItem surface_process;						//表面处理
	private ReportItem regular_label;						//常规标记
	private ReportItem produce_period;						//生产周期
	private ReportItem external_coating_adhesion;			//外镀层附着力
	private ReportItem prevent_smt_character_adhesion;		//阻焊/字符附着力
	private ReportItem min_line_width;						//最小线宽
	private ReportItem min_line_distance;					//最小间距
	private ReportItem min_annular_ring;					//最小环宽
	private ReportItem board_ply;							//成品板厚
	private ReportItem warping_degree;						//翘曲度
	private ReportItem board_long;							//板长
	private ReportItem board_wide;							//板宽
	private ReportItem aperture;							//孔径
	private ReportItem v_cut;								//V-CUT 余厚
	private ReportItem circuit_connectivity;				//电路连通性
	private ReportItem circuit_insulativity;				//电路绝缘性
	private ReportItem special_impedance;					//特性阻抗
	private ReportItem solderability;						//可焊性
	private ReportItem microsectioning;						//显微剖切
	
	public ReportIntro getReportIntro() {
		return reportIntro;
	}
	public void setReportIntro(ReportIntro reportIntro) {
		this.reportIntro = reportIntro;
	}
	public List<ReportApertureCheck> getReportApertureChecks() {
		return reportApertureChecks;
	}
	public void setReportApertureChecks(List<ReportApertureCheck> reportApertureChecks) {
		this.reportApertureChecks = reportApertureChecks;
	}
	public ReportSpecialDimension getReportSpecialDimension() {
		return reportSpecialDimension;
	}
	public void setReportSpecialDimension(ReportSpecialDimension reportSpecialDimension) {
		this.reportSpecialDimension = reportSpecialDimension;
	}
	public ReportVcut getReportVcut() {
		return reportVcut;
	}
	public void setReportVcut(ReportVcut reportVcut) {
		this.reportVcut = reportVcut;
	}
	public ReportIntegrity getReportIntegrity() {
		return reportIntegrity;
	}
	public void setReportIntegrity(ReportIntegrity reportIntegrity) {
		this.reportIntegrity = reportIntegrity;
	}
	public List<ReportSpecialImpedance> getSingle_ended_impedances() {
		return single_ended_impedances;
	}
	public void setSingle_ended_impedances(List<ReportSpecialImpedance> single_ended_impedances) {
		this.single_ended_impedances = single_ended_impedances;
	}
	public List<ReportSpecialImpedance> getDifferential_characteristic_impedance() {
		return differential_characteristic_impedance;
	}
	public void setDifferential_characteristic_impedance(
			List<ReportSpecialImpedance> differential_characteristic_impedance) {
		this.differential_characteristic_impedance = differential_characteristic_impedance;
	}
	public ReportCertification getReportCertification() {
		return reportCertification;
	}
	public void setReportCertification(ReportCertification reportCertification) {
		this.reportCertification = reportCertification;
	}
	public BaseReportInfo getBaseReportInfo() {
		return baseReportInfo;
	}
	public void setBaseReportInfo(BaseReportInfo baseReportInfo) {
		this.baseReportInfo = baseReportInfo;
	}
	public PcbReportNoteBean getNote() {
		return note;
	}
	public void setNote(PcbReportNoteBean note) {
		this.note = note;
	}
	public ReportItem getBase_material_types() {
		return base_material_types;
	}
	public void setBase_material_types(ReportItem base_material_types) {
		this.base_material_types = base_material_types;
	}
	public ReportItem getBase_material_appearance() {
		return base_material_appearance;
	}
	public void setBase_material_appearance(ReportItem base_material_appearance) {
		this.base_material_appearance = base_material_appearance;
	}
	public ReportItem getConductive_pattern() {
		return conductive_pattern;
	}
	public void setConductive_pattern(ReportItem conductive_pattern) {
		this.conductive_pattern = conductive_pattern;
	}
	public ReportItem getPrevent_smt_type() {
		return prevent_smt_type;
	}
	public void setPrevent_smt_type(ReportItem prevent_smt_type) {
		this.prevent_smt_type = prevent_smt_type;
	}
	public ReportItem getPrevent_smt_appearance() {
		return prevent_smt_appearance;
	}
	public void setPrevent_smt_appearance(ReportItem prevent_smt_appearance) {
		this.prevent_smt_appearance = prevent_smt_appearance;
	}
	public ReportItem getCharacter_type() {
		return character_type;
	}
	public void setCharacter_type(ReportItem character_type) {
		this.character_type = character_type;
	}
	public ReportItem getCharacter_appearance() {
		return character_appearance;
	}
	public void setCharacter_appearance(ReportItem character_appearance) {
		this.character_appearance = character_appearance;
	}
	public ReportItem getSurface_process() {
		return surface_process;
	}
	public void setSurface_process(ReportItem surface_process) {
		this.surface_process = surface_process;
	}
	public ReportItem getRegular_label() {
		return regular_label;
	}
	public void setRegular_label(ReportItem regular_label) {
		this.regular_label = regular_label;
	}
	public ReportItem getProduce_period() {
		return produce_period;
	}
	public void setProduce_period(ReportItem produce_period) {
		this.produce_period = produce_period;
	}
	public ReportItem getExternal_coating_adhesion() {
		return external_coating_adhesion;
	}
	public void setExternal_coating_adhesion(ReportItem external_coating_adhesion) {
		this.external_coating_adhesion = external_coating_adhesion;
	}
	public ReportItem getPrevent_smt_character_adhesion() {
		return prevent_smt_character_adhesion;
	}
	public void setPrevent_smt_character_adhesion(ReportItem prevent_smt_character_adhesion) {
		this.prevent_smt_character_adhesion = prevent_smt_character_adhesion;
	}
	public ReportItem getMin_line_width() {
		return min_line_width;
	}
	public void setMin_line_width(ReportItem min_line_width) {
		this.min_line_width = min_line_width;
	}
	public ReportItem getMin_line_distance() {
		return min_line_distance;
	}
	public void setMin_line_distance(ReportItem min_line_distance) {
		this.min_line_distance = min_line_distance;
	}
	public ReportItem getMin_annular_ring() {
		return min_annular_ring;
	}
	public void setMin_annular_ring(ReportItem min_annular_ring) {
		this.min_annular_ring = min_annular_ring;
	}
	public ReportItem getBoard_ply() {
		return board_ply;
	}
	public void setBoard_ply(ReportItem board_ply) {
		this.board_ply = board_ply;
	}
	public ReportItem getWarping_degree() {
		return warping_degree;
	}
	public void setWarping_degree(ReportItem warping_degree) {
		this.warping_degree = warping_degree;
	}
	public ReportItem getBoard_long() {
		return board_long;
	}
	public void setBoard_long(ReportItem board_long) {
		this.board_long = board_long;
	}
	public ReportItem getBoard_wide() {
		return board_wide;
	}
	public void setBoard_wide(ReportItem board_wide) {
		this.board_wide = board_wide;
	}
	public ReportItem getAperture() {
		return aperture;
	}
	public void setAperture(ReportItem aperture) {
		this.aperture = aperture;
	}
	public ReportItem getV_cut() {
		return v_cut;
	}
	public void setV_cut(ReportItem v_cut) {
		this.v_cut = v_cut;
	}
	public ReportItem getCircuit_connectivity() {
		return circuit_connectivity;
	}
	public void setCircuit_connectivity(ReportItem circuit_connectivity) {
		this.circuit_connectivity = circuit_connectivity;
	}
	public ReportItem getCircuit_insulativity() {
		return circuit_insulativity;
	}
	public void setCircuit_insulativity(ReportItem circuit_insulativity) {
		this.circuit_insulativity = circuit_insulativity;
	}
	public ReportItem getSpecial_impedance() {
		return special_impedance;
	}
	public void setSpecial_impedance(ReportItem special_impedance) {
		this.special_impedance = special_impedance;
	}
	public ReportItem getSolderability() {
		return solderability;
	}
	public void setSolderability(ReportItem solderability) {
		this.solderability = solderability;
	}
	public ReportItem getMicrosectioning() {
		return microsectioning;
	}
	public void setMicrosectioning(ReportItem microsectioning) {
		this.microsectioning = microsectioning;
	}
	
}
