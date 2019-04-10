package com.pcbwx.pcbmis.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.pcbwx.pcbmis.model.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole> {
	List<UserRole> load();
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from user_role")
	Date selectLastRecordTime();
	List<UserRole> getRoles();
	
	Integer getSelectByKeyWordNum(@Param("keyWord") String keyWord,
									@Param("userCodes") List<String> userCodes,
									@Param("roleName") String roleName,
									@Param("roleIds") List<Integer> roleIds,
									@Param("enable") Integer enable);
	
	List<UserRole> selectByKeyWord(@Param("keyWord") String keyWord,
									@Param("roleName") String roleName,
									@Param("userCodes") List<String> userCodes,
									@Param("roleIds") List<Integer> roleIds,
									@Param("enable") Integer enable,
									@Param("start") Integer start,
									@Param("pageSize") Integer pageSize);
	
}