package com.ssafy.enjoytrip.board.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.BoardListDto;

public interface BoardService {
	int regist(BoardDto boardDto);
	BoardListDto list(Map<String, String> map);
	BoardDto detail(int articleNo);
	int updateHit(int articleNo);
	int modify(BoardDto boardDto);
	int remove(int articleNo);
	List<String> getSlang();
}
