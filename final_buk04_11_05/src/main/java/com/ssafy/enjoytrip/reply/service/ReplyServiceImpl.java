package com.ssafy.enjoytrip.reply.service;

import com.ssafy.enjoytrip.reply.dao.ReplyDao;
import com.ssafy.enjoytrip.reply.model.ReplyDto;
import com.ssafy.enjoytrip.reply.model.ReplyListDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReplyServiceImpl implements ReplyService {

private final ReplyDao replyDao;
	
	public ReplyServiceImpl(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	@Override
	public int regist(ReplyDto replyDto) {
		replyDao.plusCount(replyDto.getParentIdx());
		return replyDao.regist(replyDto);
	}

	@Override
	public ReplyListDto list(Map<String, String> map) {
		Map<String, Object> param = new HashMap<String, Object>();
		int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : (String)map.get("pgno"));
		int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : (String)map.get("spp"));
		int start = currentPage * sizePerPage - sizePerPage;
		param.put("start", start);
		param.put("listsize", sizePerPage);
		param.put("articleno", map.get("articleno"));
		List<ReplyDto> list = replyDao.list(param);
		int totalArticleCount = replyDao.getTotalArticleCount(param);
		int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;
		System.out.println(list);
		ReplyListDto replyListDto = new ReplyListDto();
		replyListDto.setArticles(list);
		replyListDto.setCurrentPage(currentPage);
		replyListDto.setTotalPageCount(totalPageCount);

		return replyListDto;
	}

	@Override
	public int modify(ReplyDto replyDto) {
		return replyDao.modify(replyDto);
	}

	@Override
	public int remove(int idx) {
		replyDao.minusCount(idx);
		return replyDao.remove(idx);
	}

	@Override
	public List<String> getSlang() {
		return replyDao.getSlang();
	}
}
