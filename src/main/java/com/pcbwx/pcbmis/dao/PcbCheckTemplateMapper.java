package com.pcbwx.pcbmis.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.pcbwx.pcbmis.model.PcbCheckTemplate;

public interface PcbCheckTemplateMapper extends BaseMapper<PcbCheckTemplate> {
	List<PcbCheckTemplate> load();
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from pcb_check_template")
	Date selectLastRecordTime();
	
	/**
	 * 按templateId获取模板
	 * @param templateId
	 * @return
	 */
	PcbCheckTemplate selectByTemplateId(@Param("templateId") Integer templateId);
}