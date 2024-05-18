package com.ssafy.enjoytrip.comment.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.comment.model.CommentDto;

@Mapper
public interface CommentDao {
	int regist(CommentDto commentDto);
	List<CommentDto> list(Map<String, Object> param);
	int getTotalArticleCount(Map<String, Object> map);
	CommentDto detail(int articleNo);
	int modify(CommentDto boardDto);
	int remove(int articleNo);
	List<String> getSlang();
}
