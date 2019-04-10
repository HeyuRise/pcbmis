package com.pcbwx.pcbmis.enums;

/**
 * 权限枚举类
 * 
 * @author 王海龙
 *
 */
public enum PcbStatusEnum {
	CREATING(1, "创建中"),
	PREPARE(2, "预审待分配"),
//	project_status(3, "数据预审中"),
//	project_status(4, "驳回数据填写中"),
//	project_status(5, "驳回修改中"),
//	project_status(6, "EQ确认中"),
//	project_status(7, "电话沟通中"),
	HAVE_PRODUCTED(8, "已投产"),
	HAVE_DELIVERY(9, "已发货"),
//	project_status(10, "退单"),
//	project_status(11, "余板交付"),
//	project_status(12, "退单修改中"),
//	project_status(13, "索回修改中"),
//	project_status(14, "作废审核中"),
	INVALID(15, "作废"),
//	project_status(16, "完成投产"),
//	project_status(17, "暂停审核中"),
	PAUSE(18, "暂停"),
//	project_status(19, "预审通过，待生成加投通知单"),
//	project_status(20, "出入库"),
//	project_status(21, "待入库"),
//	project_status(22, "待发货"),
//	project_status(23, "信息变更中"),
//	project_status(24, "暂停变更中"),
//	project_status(25, "暂停变更修改中"),
//	project_status(26, "数据变更预审中"),
//	project_status(27, "数据变更预审待分配"),
//	project_status(28, "信息变更预审待分配"),
//	project_status(29, "信息变更预审中"),
//	project_status(30, "再生产"),
//	project_status(31, "撤单"),
//	project_status(32, "恢复生产审核中"),
//	project_status(33, "预下单变更中"),
//	project_status(34, "预下单变更预审待分配"),
//	project_status(35, "预下单变更预审中"),
//	project_status(36, "预审通过，待复检"),
//	project_status(38, "复查中"),
//	project_status(39, "索回审核中"),
//	project_status(41, "待数据预审"),
//	project_status(42, "预审暂停中"),
//	project_status(43, "数据复检中"),
	JN_EQ_CONFIRMING(44, "JN-EQ确认中"),
	COMPLETE(1000, "已完成");
	
//	PLAINING(10, "设计待安排"), 
//	SIZE_DESIGNING(11, "建库中"), 
//	SIZE_CHECKING(12, "封装库待校对"), 
//	POS_DESIGNING(13,"布局中"), 
//	POS_CHECKING(14, "布局待校对"), 
//	LINE_DESIGNING(15, "布线中"), 
//	LINE_CHECKING(16, "布线待校对"),
//	CHECK_OVER(17, "校对完毕待投产"), 
//	DESIGN_FINISH(0, "PCB设计完成"), 
//	DESIGN_PAUSE(1, "设计暂停"), 
//	DESIGN_STOP(2, "设计终止");
	
	private int code; 		// 代码
	private String desc; 	// 描述

	private PcbStatusEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
}
