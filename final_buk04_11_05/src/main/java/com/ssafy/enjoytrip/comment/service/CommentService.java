package com.ssafy.enjoytrip.comment.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.comment.model.CommentDto;
import com.ssafy.enjoytrip.comment.model.CommentListDto;

public interface CommentService {

	int regist(CommentDto commentDto);
	CommentListDto list(Map<String, String> map);
	CommentDto detail(int articleNo);
	int modify(CommentDto commentDto);
	int remove(int articleNo);
	List<String> getSlang();
}
