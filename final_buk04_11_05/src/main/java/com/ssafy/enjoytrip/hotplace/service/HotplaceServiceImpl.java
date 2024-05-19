package com.ssafy.enjoytrip.hotplace.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.hotplace.dao.HotplaceDao;
import com.ssafy.enjoytrip.hotplace.model.FileInfoDto;
import com.ssafy.enjoytrip.hotplace.model.HotplaceDto;
import com.ssafy.enjoytrip.hotplace.model.HotplaceListDto;

import jakarta.servlet.ServletContext;

@Service
public class HotplaceServiceImpl implements HotplaceService{
	
	private final HotplaceDao hotplaceDao;
	
	public HotplaceServiceImpl(HotplaceDao hotplaceDao) {
		this.hotplaceDao = hotplaceDao;
	}

	@Override
	public int regist(HotplaceDto hotplaceDto) {
		int articleNo = hotplaceDao.regist(hotplaceDto);
		hotplaceDto.setArticleNo(articleNo);
		return hotplaceDao.registerFile(hotplaceDto);
	}

	@Override
	public HotplaceListDto list(Map<String, String> map) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));
		int start = currentPage * sizePerPage - sizePerPage;
		param.put("start", start);
		param.put("listsize", sizePerPage);

		String key = map.get("key");
		param.put("key", key == null ? "" : key);
		List<HotplaceDto> list = hotplaceDao.list(param);
		for (int i = 0; i < list.size(); i++) {
			FileInfoDto fileInfoDto = hotplaceDao.fileInfo(list.get(i).getArticleNo());
			list.get(i).setFileInfo(fileInfoDto);
		}
		
		int totalArticleCount = hotplaceDao.getTotalArticleCount(param);
		int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

		HotplaceListDto hotplaceListDto = new HotplaceListDto();
		hotplaceListDto.setArticles(list);
		hotplaceListDto.setCurrentPage(currentPage);
		hotplaceListDto.setTotalPageCount(totalPageCount);

		return hotplaceListDto;
	}

	@Override
	public HotplaceDto detail(int articleNo) {
		FileInfoDto fileInfoDto = hotplaceDao.fileInfo(articleNo);
		HotplaceDto hotplaceDto = hotplaceDao.getArticle(articleNo);
		hotplaceDto.setFileInfo(fileInfoDto);
		return hotplaceDto;
	}

	@Override
	public int updateHit(int articleNo) {
		return hotplaceDao.updateHit(articleNo);
	}

	@Override
	public int modify(HotplaceDto hotplaceDto) {
		return hotplaceDao.modify(hotplaceDto);
	}

	@Override
	public int remove(int articleNo) {
		return hotplaceDao.remove(articleNo);
	}

	@Override
	public List<String> getSlang() {
		return hotplaceDao.getSlang();
	}


}
