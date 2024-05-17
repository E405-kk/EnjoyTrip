package com.ssafy.enjoytrip.notice.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.BoardListDto;

public interface NoticeService {
	int regist(BoardDto boardDto);
	BoardListDto list(Map<String, String> map);
	BoardDto detail(int articleNo);
	int updateHit(int articleNo);
	int modify(BoardDto boardDto);
	int remove(int articleNo);
	List<String> getSlang();
}
