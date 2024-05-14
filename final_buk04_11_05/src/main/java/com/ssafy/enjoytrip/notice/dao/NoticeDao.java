package com.ssafy.enjoytrip.notice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.board.model.BoardDto;

@Mapper
public interface NoticeDao {
	int regist(BoardDto boardDto);
	List<BoardDto> list();
	BoardDto detail(int articleNo);
	int updateHit(int articleNo);
	int modify(BoardDto boardDto);
	int remove(int articleNo);
	List<String> getSlang();
}
