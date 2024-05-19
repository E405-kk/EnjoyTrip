package com.ssafy.enjoytrip.comment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.comment.dao.CommentDao;
import com.ssafy.enjoytrip.comment.model.CommentDto;
import com.ssafy.enjoytrip.comment.model.CommentListDto;

@Service
public class CommentServiceImpl implements CommentService {
private final CommentDao commentDao;
	
	public CommentServiceImpl(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Override
	public int regist(CommentDto commentDto) {
		return commentDao.regist(commentDto);
	}

	@Override
	public CommentListDto list(Map<String, String> map) {
		Map<String, Object> param = new HashMap<String, Object>();
		int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : (String)map.get("pgno"));
		int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : (String)map.get("spp"));
		int start = currentPage * sizePerPage - sizePerPage;
		param.put("start", start);
		param.put("listsize", sizePerPage);
		param.put("articleno", map.get("articleno"));
		List<CommentDto> list = commentDao.list(param);
		int totalArticleCount = commentDao.getTotalArticleCount(param);
		int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

		CommentListDto commentListDto = new CommentListDto();
		commentListDto.setArticles(list);
		commentListDto.setCurrentPage(currentPage);
		commentListDto.setTotalPageCount(totalPageCount);

		return commentListDto;
	}

	@Override
	public int modify(CommentDto commentDto) {
		return commentDao.modify(commentDto);
	}

	@Override
	public int remove(int idx) {
		return commentDao.remove(idx);
	}

	@Override
	public List<String> getSlang() {
		return commentDao.getSlang();
	}
}
