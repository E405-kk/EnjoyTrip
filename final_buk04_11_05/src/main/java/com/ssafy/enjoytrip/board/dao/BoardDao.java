package com.ssafy.enjoytrip.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.board.model.BoardDto;

@Mapper
public interface BoardDao {
	int regist(BoardDto boardDto);
	List<BoardDto> list(Map<String, Object> param);
	int getTotalArticleCount(Map<String, Object> map);
	BoardDto detail(int articleNo);
	int updateHit(int articleNo);
	int modify(BoardDto boardDto);
	int remove(int articleNo);
	List<String> getSlang();
}
