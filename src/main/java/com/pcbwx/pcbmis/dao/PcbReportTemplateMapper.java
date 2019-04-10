package com.pcbwx.pcbmis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.PcbReportTemplate;

public interface PcbReportTemplateMapper extends BaseMapper<PcbReportTemplate>{
	
	List<PcbReportTemplate> load();
	
//    PcbReportTemplate selectByTemplateName(@Param("templateName") String templateName);
    
    PcbReportTemplate selectByTemplateId(@Param("templateId") Integer templateId);
}