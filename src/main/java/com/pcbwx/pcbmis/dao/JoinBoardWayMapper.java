package com.pcbwx.pcbmis.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.pcbwx.pcbmis.model.JoinBoardWay;

public interface JoinBoardWayMapper extends BaseMapper<JoinBoardWay> {
	List<JoinBoardWay> load();
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from join_board_way")
	Date selectLastRecordTime();

	List<JoinBoardWay> listByInnerIds(List<Integer> ids);
}