package com.ssafy.enjoytrip.hotplace.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.hotplace.model.FileInfoDto;
import com.ssafy.enjoytrip.hotplace.model.HotplaceDto;

@Mapper
public interface HotplaceDao {
	int regist(HotplaceDto hotplaceDto);
	List<HotplaceDto> list(Map<String, Object> param);
	int getTotalArticleCount(Map<String, Object> map);
	HotplaceDto detail(int articleNo);
	int updateHit(int articleNo);
	int modify(HotplaceDto hotplaceDto);
	int remove(int articleNo);
	List<String> getSlang();
	HotplaceDto getArticle(int articleNo);
	int registerFile(HotplaceDto hotplaceDto) ;
	int update(FileInfoDto fileInfo);
	int deleteFile(int articleNo);
	FileInfoDto fileInfo(int articleNo);
}
