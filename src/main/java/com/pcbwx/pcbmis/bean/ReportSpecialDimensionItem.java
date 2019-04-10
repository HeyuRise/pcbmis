package com.pcbwx.pcbmis.bean;

import com.pcbwx.pcbmis.model.PcbReportSpecialDimension;
import com.pcbwx.pcbmis.utils.PcbmisUtil;

public class ReportSpecialDimensionItem {
	private Integer id;				// id
	private String itemName;		// 项目名字
	private String require;			// 要求
	private String unit;			// 单位
	private String realCheck;		// 实测
	private String judge;			// 判定

	public ReportSpecialDimensionItem() {
		super();
	}

	public ReportSpecialDimensionItem(PcbReportSpecialDimension pcb){
		this.id = pcb.getApertureId();
		this.itemName = pcb.getItemKey();
		this.require = pcb.getCheckRequire();
		this.unit = pcb.getUnit();
		this.realCheck = pcb.getRealCheck();
		this.judge = PcbmisUtil.int2judge(pcb.getJudge());
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getRequire() {
		return require;
	}
	public void setRequire(String require) {
		this.require = require;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getRealCheck() {
		return realCheck;
	}
	public void setRealCheck(String realCheck) {
		this.realCheck = realCheck;
	}
	public String getJudge() {
		return judge;
	}
	public void setJudge(String judge) {
		this.judge = judge;
	}
	
}
