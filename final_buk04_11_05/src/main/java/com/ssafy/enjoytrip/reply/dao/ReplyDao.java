package com.ssafy.enjoytrip.reply.dao;

import com.ssafy.enjoytrip.reply.model.ReplyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface ReplyDao {
    int regist(ReplyDto replyDto);
    List<ReplyDto> list(Map<String, Object> param);
    int getTotalArticleCount(Map<String, Object> map);
    int modify(ReplyDto replyDto);
    int remove(int idx);
    List<String> getSlang();
    int plusCount(int parentIdx);
    int minusCount(int idx);
}
