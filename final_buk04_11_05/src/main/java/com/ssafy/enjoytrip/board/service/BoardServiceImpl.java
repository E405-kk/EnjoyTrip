package com.ssafy.enjoytrip.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.board.dao.BoardDao;
import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.BoardListDto;

@Service
public class BoardServiceImpl implements BoardService{
	
	private final BoardDao boardDao;
	
	public BoardServiceImpl(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	@Override
	public int regist(BoardDto boardDto) {
		return boardDao.regist(boardDto);
	}

	@Override
	public BoardListDto list(Map<String, String> map) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));
		int start = currentPage * sizePerPage - sizePerPage;
		param.put("start", start);
		param.put("listsize", sizePerPage);

		String key = map.get("key");
		param.put("key", key == null ? "" : key);
		List<BoardDto> list = boardDao.list(param);

		int totalArticleCount = boardDao.getTotalArticleCount(param);
		int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

		BoardListDto boardListDto = new BoardListDto();
		boardListDto.setArticles(list);
		boardListDto.setCurrentPage(currentPage);
		boardListDto.setTotalPageCount(totalPageCount);

		return boardListDto;
	}

	@Override
	public BoardDto detail(int articleNo) {
		return boardDao.detail(articleNo);
	}

	@Override
	public int updateHit(int articleNo) {
		return boardDao.updateHit(articleNo);
	}

	@Override
	public int modify(BoardDto boardDto) {
		return boardDao.modify(boardDto);
	}

	@Override
	public int remove(int articleNo) {
		return boardDao.remove(articleNo);
	}

	@Override
	public List<String> getSlang() {
		return boardDao.getSlang();
	}

	



}
