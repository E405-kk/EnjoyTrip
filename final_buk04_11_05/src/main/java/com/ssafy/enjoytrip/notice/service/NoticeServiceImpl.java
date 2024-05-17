package com.ssafy.enjoytrip.notice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.BoardListDto;
import com.ssafy.enjoytrip.notice.dao.NoticeDao;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	private final NoticeDao noticeDao;
	
	public NoticeServiceImpl(NoticeDao noticeDao) {
		super();
		this.noticeDao = noticeDao;
	}

	@Override
	public int regist(BoardDto boardDto) {
		return noticeDao.regist(boardDto);
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
		List<BoardDto> list = noticeDao.list(param);

		int totalArticleCount = noticeDao.getTotalArticleCount(param);
		int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

		BoardListDto boardListDto = new BoardListDto();
		boardListDto.setArticles(list);
		boardListDto.setCurrentPage(currentPage);
		boardListDto.setTotalPageCount(totalPageCount);

		return boardListDto;
	}

	@Override
	public BoardDto detail(int articleNo) {
		return noticeDao.detail(articleNo);
	}

	@Override
	public int updateHit(int articleNo) {
		return noticeDao.updateHit(articleNo);
	}

	@Override
	public int modify(BoardDto boardDto) {
		return noticeDao.modify(boardDto);
	}

	@Override
	public int remove(int articleNo) {
		return noticeDao.remove(articleNo);
	}

	@Override
	public List<String> getSlang() {
		return noticeDao.getSlang();
	}
}
