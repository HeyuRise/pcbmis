package com.pcbwx.pcbmis.bean.channel;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pcbwx.pcbmis.annotation.FieldMap;
import com.pcbwx.pcbmis.map.String2Date;
import com.pcbwx.pcbmis.map.String2DateTime;
import com.pcbwx.pcbmis.map.String2Integer;

/**
 * pcb工单
 * @author wanghl
 *
 */
public class PcbProductOrder {
	@JsonProperty("orderNum")
	@FieldMap
	private String orderNum; // 工单号
	
	@JsonProperty("boardName")
	@FieldMap
	private String boardName; // 板名
	
	@JsonProperty("guest_code")
	@FieldMap
	private String guestCode;	// 客户的系统编号
	
	@JsonProperty("contact_code")
	@FieldMap
	private String contactCode;	// 联系人的系统编号
	
	@JsonProperty("department_code")
	@FieldMap
	private String departmentCode;	// 部门的系统编号
	
	@JsonProperty("seller_code")
	@FieldMap
	private String sellerCode;	// 销售人员的系统编号
	
	@JsonProperty("order_date")
	@FieldMap(using = String2Date.class)
	private String orderDate;	// 下单日期
	
	@JsonProperty("product_date")
	@FieldMap(using = String2Date.class)
	private String productDate;	// 投产日期
	
	@JsonProperty("hand_over_date")
	@FieldMap(using = String2Date.class)
	private String handOverDate;	// 交付日期
	
	@JsonProperty("deliver_good_date")
	@FieldMap(using = String2Date.class)
	private String deliverGoodDate;	// 交货日期
	
//	private String cs_group_code;	// 客服组的系统编号
	
	@JsonProperty("cs_code")
	@FieldMap
	private String csCode;		// 拟制人的系统编号
	
//	private String create_cs_code;	// 创建人的系统编号
//	private String cad_design;	// CAD设计

	@JsonProperty("floor_num")
	@FieldMap(using = String2Integer.class)
	private String floorNum;	// 层数

	@JsonProperty("board_ply")
	@FieldMap
	private String boardPly;	// 板厚

	@JsonProperty("board_ply_tolerance_id")
	@FieldMap
	private Integer boardPlyToleranceId;	// 板厚公差
	
//	private Integer last_copper_ply_id;	// 最终铜厚out
	
	@JsonProperty("factory_id")
	@FieldMap
	private Integer factoryId;	// 生产厂家

	@JsonProperty("new_old_product_id")
	@FieldMap
	private Integer newOldProductId;	// 新老品
	
	@JsonProperty("craft_id")
	@FieldMap
	private Integer craftId;	// 工艺
	
//	private Integer make_board_id;	// 制版	
//	private Integer join_board;	// 拼板	
//	private String join_board_name;	// 拼板板名
	
	@JsonProperty("join_board_require_id")
	@FieldMap
	private Integer joinBoardRequireId;	// 拼板要求
	
	@JsonProperty("join_board_way_id")	
	@FieldMap
	private Integer joinBoardWayId;	// 拼板方式
	
//	private Integer step_num_id;	// 阶数
//	private String mai_mang_kong;	// 埋盲孔方式
//	private String smt;	// 焊接
//	private String cold_board;	// 冷板
//	private String prevent_smt;	// 阻焊的系统编号
	
	@JsonProperty("prevent_smt_colour_id")
	@FieldMap	
	private Integer preventSmtColorId;	// 阻焊颜色
	
	@JsonProperty("character")
	@FieldMap
	private String boardCharacter;	// 字符

	@JsonProperty("character_colour_id")
	@FieldMap
	private Integer characterColourId;	// 字符颜色
	
	@JsonProperty("production_num_set")
	@FieldMap
	private Integer productionNumSet;	// 生产数量set
	
	@JsonProperty("production_num_pcs")
	@FieldMap
	private Integer productionNumPcs;	// 生产数量PCS
	
	@JsonProperty("grade_id")
	@FieldMap
	private Integer categoryGradeId;	// 等级
	
	@JsonProperty("metallography_require")
	@FieldMap
	private String metallographyRequire;	// 金相要求
	
//	private Integer metallography_test_id;	// 金相测试
	
	@JsonProperty("surface_process_id")
	@FieldMap
	private Integer surfaceProcessId;	// 表面处理
	
//	private String gold_finger;	// 金手指
//	private String gild_num;	// 镀金数量
//	private Integer gold_finger_require_id;	// 金手指倒角要求

	@JsonProperty("impedance_require")
	@FieldMap
	private String impedanceRequire;	// 阻抗要求
	
//	private Integer impedance_tolerance_id;	// 阻抗公差
//	private Integer impedance_test_id;	// 阻抗测试
	
	@JsonProperty("long")
	@FieldMap
	private BigDecimal boardLong;	// 尺寸长
	@JsonProperty("wide")
	@FieldMap
	private BigDecimal boardWide;	// 尺寸宽

//	private BigDecimal area;	// 面积

	@JsonProperty("prevent_smt")
	@FieldMap
	private String preventSmt;	// 阻焊

	@JsonProperty("frame_tolerance_id")
	@FieldMap
	private Integer frameToleranceId;	// 边框公差
	
//	private Integer base_copper_ply_id;	// 基铜厚度
//	private Integer copper_ply_id;	// 铜厚inter
//	private Integer electric_test_require_id;	// 电测试要求
//	private String integerernet_resistance;	// 互联电阻
//	private Integer integerernet_resistance_test_id;	// 互联电阻测试

	@JsonProperty("batch_production_id")
	@FieldMap
	private Integer batchProductionId;	// 生产批号
	
	@JsonProperty("delivery_mode")
	@FieldMap
	private String deliveryMode;	// 出货方式
	
//	private Integer manner_of_packing_id;	// 包装方式

	@JsonProperty("production_notes")
	@FieldMap
	private String productionNotes;	// 生产备注
	
//	private String crimping_device;	// 压接器件
//	private String crimping_aperture;	// 压接孔径
//	private Integer crimping_aperture_tolerance_id;	// 压接孔径公差
	
	@JsonProperty("through_hole_treatment")
	@FieldMap
	private String throughHoleTreatment;	// 过孔处理
	
	@JsonProperty("forming_way_id")
	@FieldMap
	private Integer formingWayId;	// 成型方式
	
	@JsonProperty("cam_tip")
	@FieldMap
	private String camTip;	// CAM指示
//	private Integer job_requirement_id;	// 工单要求
//	private BigDecimal total_area;	// 总面积
//	private Integer preliminary_id;	// 预审人员
//	private Integer file_format_id;	// 文件格式
	
	@JsonProperty("created_at")
	@FieldMap(using = String2DateTime.class)
	private String innerCreateTime;	// 创建时间
	
	@JsonProperty("updated_at")	
	@FieldMap(using = String2DateTime.class)
	private String innerUpdateTime;	// 更新时间

	@JsonProperty("market_department_name")
	@FieldMap
	private String marketDepartmentName;	// 销售部门
	
//	private Integer cam_process_id;	// CAM处理
	
	@JsonProperty("project_status_id")
	@FieldMap
	private Integer projectStatusId;	// 生产单状态
	
//	private String cad_num;	// CAD设计号
//	private Integer board_name_id;	// 板名id
//	private Boolean file_change;	// 文件是否修改
//	private Integer product_status_id;	// 无效字段
//	private Integer sell_department_manager_id;	// 销售的部门经理
//	private String pin;	// pin数
//	private String special_board_num;	// 特殊板材张数
	
	@JsonProperty("ed_product_date")
	@FieldMap(using = String2DateTime.class)
	private String edProductDate;	// 工程部投产时间
	
//	private String chedan_reasons;	// 撤单理由
//	private String guest_sp_code;	// 客户特殊编号
//	private String gild_area;	// 镀金面积
//	private Integer repreliminary_id;	// 复检人员

	@JsonProperty("business_notes")
	@FieldMap
	private String businessNotes;	// 商务备注

	@JsonProperty("shipments_notes")
	@FieldMap
	private String shipmentsNotes;	// 发货备注
	
//	private Boolean ready_product;	// 是否为新品预下单
//	private String serial_number;	// 流水号
//	private Integer guest_model_id;	// 502所产保版本
//	private Integer temper_test_id;	// 温循测试
	
	@JsonProperty("standard_period")
	@FieldMap
	private Integer standardPeriod;	// 标准周期
	
//	private Integer laminate;	// 层压次数
	
//	private Integer actualProducePeriod;	// 实际生产周期
	
//	private Integer guest_require_cycle;	// 客户要求周期
//	private Boolean is_batch_manage;	// 是否需要批次管理
//	private Boolean is_freeze;	// 是否冻结
//	private String special_craft_content;	// 特殊工艺内容
	
	@JsonProperty("belong_company_id")
	@FieldMap
	private Integer belongCompanyId;	// 所属公司
	
//	private Boolean is_process_promptly;	// 是否处理及时
	
	@JsonProperty("start_preliminary_date")
	@FieldMap(using = String2Date.class)
	private String startPreliminaryDate;	// 开始预审日期
	
//	private String gild_ply;	// 金厚
//	private BigDecimal max_gild_ply	;	// 金厚最大值
//	private Integer design_modify_orders_number;	// 底片替换数量
//	private Integer total_back_drilling_num;	// 总背钻数量
//	private Integer single_board_back_drilling_num;	// 单板背钻数量
//	private Integer false_layer_num;	// 假层层数
//	private String bga_pitch;	// BGA节距
//	private String hole_density;	// 孔密度
//	private String line_width_space_code;	// 线宽间距的系统编号

	@JsonProperty("basem_aterials")
	List<Integer> basemAterials;	// 生产单对应的基材的id组成的数组
	
//	List<Integer> special_crafts;	// 生产单对应的特殊工艺的id组成的数组
	
//	private List<JoinBoardOrder> join_board_orders;	//生产单的子板信息组成的数组
						
//	semi_curing_film_orders	array	生产单的半固化片组成的数组，每个数组元素都是一个hash对象
			
//	special_board_orders	array	生产单的特殊板材组成的数组，每个数组元素都是一个hash对象
		
//	private Integer plugging_resin_num;	// 树脂塞孔次数
//	private Integer metallography_amount;	// 金相数量
//	private Integer copper_ply;	// 铜厚inter
//	private String cover_electromagnetic_film;	// 覆盖电磁膜
//	private Integer electromagnetic_film_num;	// 电磁膜面数
//	private String hot_oil_test;	// 热油测试

	@JsonProperty("offer_order_orderNum")
	@FieldMap
	private String offerOrderNum;	// 报价单号

//	private Integer cap_num;	// cap次数
//	private Integer copper_paste_jack_num;	// 铜膏塞孔次数
//	private String special_green_jack_code;	// 特殊绿油塞孔的系统编号
//	private Integer back_drilling_way;	// 背钻孔方式（民品）
//	private Integer hole_copper_thickness;	// 孔铜厚度
//	private Integer single_board_hole_num;	// 单板孔数量
//	private String min_aperture;	// 最小孔径
//	private Integer six_mil_num;	// 6mil孔数（民品）
//	private Integer eight_mil_num;	// 8mil孔数(民品)
//	private String milling_half_hole_code;	// 铣半孔的系统编号
//	private BigDecimal gold_finger_thickness;	// 金手指金厚
//	private BigDecimal gold_finger_area;	// 金手指镀金总面积
//	private String gold_plated_way_code;	// 镀金方式的系统编号
//	private String metal_rim_code;	// 金属包边的系统编号
//	private BigDecimal single_gold_finger_area;	// 单个金手指面积
//	private Integer milling_flutes;	// 铣盲槽个数（种）
//	private String milling_exposed_figure_code;	// 盲槽中是否裸露图形的系统编号
//	private String board_milling_thin_code;	// 板边铣薄的系统编号
//	private BigDecimal gong_length;	// 锣程长度
//	private String board_exposed_figure_code;	// 板边铣薄区域是否裸露图形的系统编号
//	private Integer cavity_num;	// 空腔板,内层有挖槽，不露出表面（种）
//	private String hard_different_thick_code;	// 硬板不等厚的系统编号
//	private String soft_different_length_code;	// 软板不等长的系统编号
//	private Integer metallization_num;	// 柔性区上有金属化孔(次)
//	private Integer electromagnetic_shielding_film;	// 柔性区贴电磁屏蔽膜含纯软板（张）
//	private String reinforcing_plate_code;	// 柔性区加补强板的系统编号
	
	@JsonProperty("min_line_width")
	@FieldMap
	private BigDecimal minLineWidth;	// 最小线宽
	
	@JsonProperty("min_line_distance")
	@FieldMap
	private BigDecimal minLineDistance;	// 最小线距 
	
//	private Integer pth_groove;	// 铣PTH槽
//	private String sink_holes;	// 生产单的沉孔的系统编号组成的字符串，以“;”分隔
	
	@JsonProperty("order_notes_arr")		
	private List<String> orderNotesIn;			// 生产配注内部
	
	@JsonProperty("order_production_notes_arr")
	private List<String> orderNotesOut;			// 生产配注外部

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getGuestCode() {
		return guestCode;
	}

	public void setGuestCode(String guestCode) {
		this.guestCode = guestCode;
	}

	public String getContactCode() {
		return contactCode;
	}

	public void setContactCode(String contactCode) {
		this.contactCode = contactCode;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getSellerCode() {
		return sellerCode;
	}

	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getProductDate() {
		return productDate;
	}

	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}

	public String getHandOverDate() {
		return handOverDate;
	}

	public void setHandOverDate(String handOverDate) {
		this.handOverDate = handOverDate;
	}

	public String getDeliverGoodDate() {
		return deliverGoodDate;
	}

	public void setDeliverGoodDate(String deliverGoodDate) {
		this.deliverGoodDate = deliverGoodDate;
	}

	public String getCsCode() {
		return csCode;
	}

	public void setCsCode(String csCode) {
		this.csCode = csCode;
	}

	public Integer getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
	}

	public Integer getNewOldProductId() {
		return newOldProductId;
	}

	public void setNewOldProductId(Integer newOldProductId) {
		this.newOldProductId = newOldProductId;
	}

	public Integer getCraftId() {
		return craftId;
	}

	public void setCraftId(Integer craftId) {
		this.craftId = craftId;
	}

	public Integer getJoinBoardRequireId() {
		return joinBoardRequireId;
	}

	public void setJoinBoardRequireId(Integer joinBoardRequireId) {
		this.joinBoardRequireId = joinBoardRequireId;
	}

	public Integer getJoinBoardWayId() {
		return joinBoardWayId;
	}

	public void setJoinBoardWayId(Integer joinBoardWayId) {
		this.joinBoardWayId = joinBoardWayId;
	}

	public Integer getPreventSmtColorId() {
		return preventSmtColorId;
	}

	public void setPreventSmtColorId(Integer preventSmtColorId) {
		this.preventSmtColorId = preventSmtColorId;
	}

	public String getBoardCharacter() {
		return boardCharacter;
	}

	public void setBoardCharacter(String boardCharacter) {
		this.boardCharacter = boardCharacter;
	}

	public Integer getProductionNumSet() {
		return productionNumSet;
	}

	public void setProductionNumSet(Integer productionNumSet) {
		this.productionNumSet = productionNumSet;
	}

	public Integer getProductionNumPcs() {
		return productionNumPcs;
	}

	public void setProductionNumPcs(Integer productionNumPcs) {
		this.productionNumPcs = productionNumPcs;
	}

	public Integer getCategoryGradeId() {
		return categoryGradeId;
	}

	public void setCategoryGradeId(Integer categoryGradeId) {
		this.categoryGradeId = categoryGradeId;
	}

	public String getMetallographyRequire() {
		return metallographyRequire;
	}

	public void setMetallographyRequire(String metallographyRequire) {
		this.metallographyRequire = metallographyRequire;
	}

	public Integer getSurfaceProcessId() {
		return surfaceProcessId;
	}

	public void setSurfaceProcessId(Integer surfaceProcessId) {
		this.surfaceProcessId = surfaceProcessId;
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

	public String getPreventSmt() {
		return preventSmt;
	}

	public void setPreventSmt(String preventSmt) {
		this.preventSmt = preventSmt;
	}

	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public String getThroughHoleTreatment() {
		return throughHoleTreatment;
	}

	public void setThroughHoleTreatment(String throughHoleTreatment) {
		this.throughHoleTreatment = throughHoleTreatment;
	}

	public Integer getFormingWayId() {
		return formingWayId;
	}

	public void setFormingWayId(Integer formingWayId) {
		this.formingWayId = formingWayId;
	}

	public String getInnerCreateTime() {
		return innerCreateTime;
	}

	public void setInnerCreateTime(String innerCreateTime) {
		this.innerCreateTime = innerCreateTime;
	}

	public String getInnerUpdateTime() {
		return innerUpdateTime;
	}

	public void setInnerUpdateTime(String innerUpdateTime) {
		this.innerUpdateTime = innerUpdateTime;
	}

	public String getMarketDepartmentName() {
		return marketDepartmentName;
	}

	public void setMarketDepartmentName(String marketDepartmentName) {
		this.marketDepartmentName = marketDepartmentName;
	}

	public Integer getProjectStatusId() {
		return projectStatusId;
	}

	public void setProjectStatusId(Integer projectStatusId) {
		this.projectStatusId = projectStatusId;
	}

	public String getEdProductDate() {
		return edProductDate;
	}

	public void setEdProductDate(String edProductDate) {
		this.edProductDate = edProductDate;
	}

	public Integer getStandardPeriod() {
		return standardPeriod;
	}

	public void setStandardPeriod(Integer standardPeriod) {
		this.standardPeriod = standardPeriod;
	}

	public Integer getBelongCompanyId() {
		return belongCompanyId;
	}

	public void setBelongCompanyId(Integer belongCompanyId) {
		this.belongCompanyId = belongCompanyId;
	}

	public String getStartPreliminaryDate() {
		return startPreliminaryDate;
	}

	public void setStartPreliminaryDate(String startPreliminaryDate) {
		this.startPreliminaryDate = startPreliminaryDate;
	}

	public String getOfferOrderNum() {
		return offerOrderNum;
	}

	public void setOfferOrderNum(String offerOrderNum) {
		this.offerOrderNum = offerOrderNum;
	}

	public String getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(String floorNum) {
		this.floorNum = floorNum;
	}

	public String getBoardPly() {
		return boardPly;
	}

	public void setBoardPly(String boardPly) {
		this.boardPly = boardPly;
	}

	public Integer getBoardPlyToleranceId() {
		return boardPlyToleranceId;
	}

	public void setBoardPlyToleranceId(Integer boardPlyToleranceId) {
		this.boardPlyToleranceId = boardPlyToleranceId;
	}

	public Integer getCharacterColourId() {
		return characterColourId;
	}

	public void setCharacterColourId(Integer characterColourId) {
		this.characterColourId = characterColourId;
	}

	public String getImpedanceRequire() {
		return impedanceRequire;
	}

	public void setImpedanceRequire(String impedanceRequire) {
		this.impedanceRequire = impedanceRequire;
	}

	public Integer getFrameToleranceId() {
		return frameToleranceId;
	}

	public void setFrameToleranceId(Integer frameToleranceId) {
		this.frameToleranceId = frameToleranceId;
	}

	public List<Integer> getBasemAterials() {
		return basemAterials;
	}

	public void setBasemAterials(List<Integer> basemAterials) {
		this.basemAterials = basemAterials;
	}

	public BigDecimal getMinLineWidth() {
		return minLineWidth;
	}

	public void setMinLineWidth(BigDecimal minLineWidth) {
		this.minLineWidth = minLineWidth;
	}

	public BigDecimal getMinLineDistance() {
		return minLineDistance;
	}

	public void setMinLineDistance(BigDecimal minLineDistance) {
		this.minLineDistance = minLineDistance;
	}

	public Integer getBatchProductionId() {
		return batchProductionId;
	}

	public void setBatchProductionId(Integer batchProductionId) {
		this.batchProductionId = batchProductionId;
	}

	public String getProductionNotes() {
		return productionNotes;
	}

	public void setProductionNotes(String productionNotes) {
		this.productionNotes = productionNotes;
	}

	public String getBusinessNotes() {
		return businessNotes;
	}

	public void setBusinessNotes(String businessNotes) {
		this.businessNotes = businessNotes;
	}

	public String getShipmentsNotes() {
		return shipmentsNotes;
	}

	public void setShipmentsNotes(String shipmentsNotes) {
		this.shipmentsNotes = shipmentsNotes;
	}

	public String getCamTip() {
		return camTip;
	}

	public void setCamTip(String camTip) {
		this.camTip = camTip;
	}

	public List<String> getOrderNotesIn() {
		return orderNotesIn;
	}

	public void setOrderNotesIn(List<String> orderNotesIn) {
		this.orderNotesIn = orderNotesIn;
	}

	public List<String> getOrderNotesOut() {
		return orderNotesOut;
	}

	public void setOrderNotesOut(List<String> orderNotesOut) {
		this.orderNotesOut = orderNotesOut;
	}
	
}
