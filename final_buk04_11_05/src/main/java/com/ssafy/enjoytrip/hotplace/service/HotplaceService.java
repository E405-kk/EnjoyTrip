package com.ssafy.enjoytrip.hotplace.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.hotplace.model.HotplaceDto;
import com.ssafy.enjoytrip.hotplace.model.HotplaceListDto;

public interface HotplaceService {
	int regist(HotplaceDto hotplaceDto);
	HotplaceListDto list(Map<String, String> map);
	HotplaceDto detail(int articleNo);
	int updateHit(int articleNo);
	int modify(HotplaceDto hotplaceDto);
	int remove(int articleNo);
	List<String> getSlang();
}
