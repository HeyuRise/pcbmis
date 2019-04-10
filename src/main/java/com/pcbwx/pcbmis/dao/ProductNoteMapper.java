package com.pcbwx.pcbmis.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.ProductNote;

public interface ProductNoteMapper extends BaseMapper<ProductNote>{
	
	/**
	 * 按工单号删除
	 * @param orderNum
	 * @return
	 */
	Integer deleteByOrderNum(@Param("orderNums") Set<String> orderNum);
	
	/**
	 * 批量插入
	 * @param list
	 * @return
	 */
	Integer insertBatch(@Param("list") List<ProductNote> list);
	/**
	 * 按工单和备注类型查找
	 * @param orderNum
	 * @param type
	 * @return
	 */
	List<ProductNote>  selectByOrderNumAndType(@Param("orderNum") String orderNum, @Param("type") Integer type);
	
	/**
	 * 按工单集合和备注类型查找
	 * @param orderNums
	 * @param type
	 * @return
	 */
	List<ProductNote> listByOrderNumAndType(@Param("orderNums") Set<String> orderNums, @Param("type") Integer type);
}