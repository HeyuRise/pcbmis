package com.pcbwx.pcbmis.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.pcbwx.pcbmis.model.WxtbDepartment;

public interface WxtbDepartmentMapper extends BaseMapper<WxtbDepartment> {
	List<WxtbDepartment> load();
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from wxtb_department")
	Date selectLastRecordTime();

	List<WxtbDepartment> listByCodes(List<String> codes);
	
	List<WxtbDepartment> selectByKeyWord(@Param("keyWord") String keyWord);
}