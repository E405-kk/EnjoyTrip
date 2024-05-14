package com.ssafy.enjoytrip.notice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.board.model.BoardDto;
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
	public List<BoardDto> list() {
		return noticeDao.list();
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
