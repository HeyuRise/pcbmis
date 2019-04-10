package com.pcbwx.pcbmis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.RoleAuth;

public interface RoleAuthMapper extends BaseMapper<RoleAuth>{
    List<RoleAuth> load();
    
    List<RoleAuth> selectByRoleId(@Param("roleId") Integer roleId);
    
    Integer deleteByRoleId(@Param("roleId") Integer roleId);
    
    List<RoleAuth> selectByAuthIds(@Param("authIds") List<Integer> authIds);
}