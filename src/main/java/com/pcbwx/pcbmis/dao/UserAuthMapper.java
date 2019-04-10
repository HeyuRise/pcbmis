package com.pcbwx.pcbmis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.UserAuth;

public interface UserAuthMapper extends BaseMapper<UserAuth>{
	List<UserAuth> load();
    List<UserAuth> selectByAuthType(@Param("authType") Integer  authType);
    
    List<UserAuth> selectByAuthName(@Param("authName") String authName);
}