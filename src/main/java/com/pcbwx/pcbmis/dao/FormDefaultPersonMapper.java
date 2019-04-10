package com.pcbwx.pcbmis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.FormDefaultPerson;

public interface FormDefaultPersonMapper extends BaseMapper<FormDefaultPerson>{
	
	/**
	 * 获取所有
	 * @return
	 */
	List<FormDefaultPerson> load();
	
	/**
	 * 获取所有的个数
	 * @return
	 */
	Integer countAll();
	
	/**
	 * 分页查找
	 * @return
	 */
	List<FormDefaultPerson> selectByPage(@Param("start") Integer start,
			   @Param("pageSize") Integer pageSize);
	
	/**
	 * 按类型查找
	 * @param type
	 * @return
	 */
	FormDefaultPerson selectByType(@Param("fieldType") String type);
}