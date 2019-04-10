package com.pcbwx.pcbmis.service;

import java.util.Map;

import com.pcbwx.authkit.bean.AkAuthUser;

public interface UserService {
	
	
	/**
	 * 获取用户详情
	 * @param wxtbUser
	 * @return
	 */
	Map<String, Object> getUserDetail(AkAuthUser wxtbUser);
	
	/**
	 * 获取用户列表
	 * @param page
	 * @param rows
	 * @param keyWord
	 * @return
	 */
//	Map<String, Object> getUserList(Integer page, Integer rows, String keyWord);
	
	/**
	 * 编辑用户
	 * @param account
	 * @param userRoleEnable
	 * @return
	 */
//	Integer editUser(String account, UserRoleEnableNew userRoleEnableNew);
	
	/**
	 * 获取角色列表
	 * @param page
	 * @param rows
	 * @param keyWord
	 * @return
	 */
//	Map<String, Object> getRoleList(Integer page, Integer rows, String keyWord);
	
	/**
	 * 编辑角色
	 * @param roleName
	 * @param userAuthEnable
	 * @return
	 */
//	Integer editRole(AkAuthUser wxtbUser, String roleName, UserAuthEnableNew userAuthEnableNew);
	
	/**
	 * 获取日志列表
	 * @param keyWord
	 * @param startTime
	 * @param stopTime
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Map<String, Object> getlogList(String keyWord, String startTime, String stopTime, Integer page, Integer pageSize);
 	
	/**
	 * 添加角色
	 * @param userAuthEnable
	 * @return
	 */
//	Integer addRole(AkAuthUser wxtbUser, UserAuthEnableNew userAuthEnableNew);
	
	/**
	 * 删除角色
	 * @param roleName
	 * @return
	 */
//	Integer delete(String  roleName);
	
}	
