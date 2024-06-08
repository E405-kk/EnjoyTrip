package com.ssafy.enjoytrip.reply.service;

import com.ssafy.enjoytrip.reply.model.ReplyDto;
import com.ssafy.enjoytrip.reply.model.ReplyListDto;

import java.util.List;
import java.util.Map;

public interface ReplyService {

	int regist(ReplyDto replyDto);
	ReplyListDto list(Map<String, String> map);
	int modify(ReplyDto replyDto);
	int remove(int idx);
	List<String> getSlang();
}
