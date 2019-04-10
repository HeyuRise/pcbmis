package com.pcbwx.pcbmis.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.pcbwx.pcbmis.model.JoinBoardOrder;

public interface JoinBoardOrderMapper extends BaseMapper<JoinBoardOrder> {
	List<JoinBoardOrder> load();
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from join_board_order")
	Date selectLastRecordTime();

	List<JoinBoardOrder> listByCodes(List<String> codes);
	List<JoinBoardOrder> selectByBoardName(@Param("boardName") String boardName);
}