package com.pcbwx.pcbmis.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.pcbwx.pcbmis.model.WxtbUser;

public interface WxtbUserMapper extends BaseMapper<WxtbUser> {
	List<WxtbUser> load();
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from wxtb_user")
	Date selectLastRecordTime();
	WxtbUser selectByAccount(@Param("account") String account);

	List<WxtbUser> listByCodes(List<String> codes);
	
	
	List<WxtbUser> selectByKey(@Param("keyWord") String keyWord, 
							   @Param("departmentCodes") List<String> departmentCodes,
							   @Param("userCodes") List<String> userCodes,
							   @Param("enable") Integer enable,
							   @Param("start") Integer start,
							   @Param("pageSize") Integer pageSize);
	
	
	Integer getSelectByKeyNum(@Param("keyWord") String keyWord, 
			   				  @Param("departmentCodes") List<String> departmentCodes,
			   				  @Param("userCodes") List<String> userCodes,
			   				  @Param("enable") Integer enable);
}