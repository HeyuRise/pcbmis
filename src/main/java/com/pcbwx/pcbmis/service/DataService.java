package com.pcbwx.pcbmis.service;

import java.util.List;

/**
 * eda信息获取接口
 * 
 * @author 王海龙
 *
 */
public interface DataService {	
	/**
	 * 获取待检验工单
	 * @param page
	 * @param pageSize
	 * @return
	 */
	boolean recordProductBasemAterials(String productOrderNum, List<Integer> basemAterials);	
 }

