package com.pcbwx.pcbmis.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.pcbwx.pcbmis.model.UserRoleRelation;

public interface UserRoleRelationMapper extends BaseMapper<UserRoleRelation> {
	List<UserRoleRelation> load();
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from user_role_relation")
	Date selectLastRecordTime();
	
	List<UserRoleRelation> selectByUserCodes(@Param("userCodes") List<String> userCodes);
	List<UserRoleRelation> selectByIds(@Param("ids") List<Integer> ids);
	
//	Integer deleteByUserCodeAndRoleId(@Param("userCode") String userCode, @Param("roleId") Integer roleId);
	List<UserRoleRelation> selectByUserCode(@Param("userCode") String userCode);
}