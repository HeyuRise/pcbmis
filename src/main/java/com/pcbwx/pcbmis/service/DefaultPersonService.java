package com.pcbwx.pcbmis.service;

import java.util.Map;

import com.pcbwx.pcbmis.enums.ErrorCodeEnum;

public interface DefaultPersonService {

	/**
	 * 获取列表
	 * @param page
	 * @param rows
	 * @return
	 */
	Map<String, Object> getList(Integer page, Integer rows);
	
	/**
	 * 修改
	 * @param id
	 * @return
	 */
	ErrorCodeEnum modify(Integer id, String userCode);
}
