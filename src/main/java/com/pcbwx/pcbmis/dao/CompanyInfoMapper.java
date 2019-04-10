package com.pcbwx.pcbmis.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.pcbwx.pcbmis.model.CompanyInfo;

public interface CompanyInfoMapper extends BaseMapper<CompanyInfo> {
	List<CompanyInfo> load();
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from company_info")
	Date selectLastRecordTime();
	
	List<CompanyInfo> listByCodes(List<String> codes);
	
}