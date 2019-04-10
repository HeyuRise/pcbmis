package com.pcbwx.pcbmis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.PcbTemplate;

public interface PcbTemplateMapper extends BaseMapper<PcbTemplate>{
	
	/**
	 * 按名字查找个数
	 * @param name
	 * @return
	 */
	Integer countByName(@Param("name") String name);
	
	/**
	 * 按名字查找个数
	 * @param name
	 * @return
	 */
	Integer countLikeByName(@Param("name") String name);
	
	/**
	 * 按名字分页查找
	 * @param name
	 * @param start
	 * @param pageSize
	 * @return
	 */
	List<PcbTemplate> selectByName(@Param("name") String name, 
			@Param("start") Integer start, 
			@Param("size") Integer size);
	
	/**
	 * 获取id,name集合
	 * @return
	 */
	List<PcbTemplate> loadEnable();
}