package com.ssafy.enjoytrip.notice.service;

import java.util.List;

import com.ssafy.enjoytrip.board.model.BoardDto;

public interface NoticeService {
	int regist(BoardDto boardDto);
	List<BoardDto> list();
	BoardDto detail(int articleNo);
	int updateHit(int articleNo);
	int modify(BoardDto boardDto);
	int remove(int articleNo);
	List<String> getSlang();
}
