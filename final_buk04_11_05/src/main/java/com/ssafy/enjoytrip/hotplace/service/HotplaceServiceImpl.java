package com.ssafy.enjoytrip.hotplace.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Autowired
	private ServletContext servletContext;

	public HotplaceServiceImpl(HotplaceDao hotplaceDao) {
		this.hotplaceDao = hotplaceDao;
	}

	@Override
	public int regist(HotplaceDto hotplaceDto) {
		int articleNo = hotplaceDao.regist(hotplaceDto);
		hotplaceDto.setArticleNo(hotplaceDto.getArticleNo());
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
			if (fileInfoDto != null) {
				list.get(i).setFileInfo(fileInfoDto);
			}
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
		FileInfoDto fileInfo = hotplaceDao.fileInfo(hotplaceDto.getArticleNo());
		if (fileInfo.getSaveFile() != "about-bg.jpg") {
			String realPath = servletContext.getRealPath("/");
			String projectPath = realPath.replace(File.separator + "final_buk04_11_05" + File.separator + "src" + File.separator + "main" + File.separator + "webapp", "");
			String saveFolder = projectPath  + File.separator + "final_05" + File.separator + "src" + File.separator + "assets" + File.separator + "upload";
			String filePath = saveFolder + File.separator + fileInfo.getSaveFolder() + File.separator + fileInfo.getSaveFile();
			File file = new File(filePath);
			System.out.println(hotplaceDto.getFileInfo().getSaveFile());
			System.out.println(fileInfo.getSaveFile());
			if (!hotplaceDto.getFileInfo().getSaveFile().equals(fileInfo.getSaveFile())) {
				file.delete();
				System.out.println("파일이 성공적으로 삭제되었습니다.");
			} else {
				System.out.println("파일을 삭제하는 데 문제가 발생했습니다.");
			}

		}
		fileInfo = hotplaceDto.getFileInfo();
		fileInfo.setArticleNo(hotplaceDto.getArticleNo());
		hotplaceDao.update(fileInfo);
		return hotplaceDao.modify(hotplaceDto);
	}

	@Override
	public int remove(int articleNo) {
		FileInfoDto fileInfo = hotplaceDao.fileInfo(articleNo);
		if (fileInfo.getSaveFile() != "about-bg.jpg") {
			String realPath = servletContext.getRealPath("/");
			String projectPath = realPath.replace(File.separator + "final_buk04_11_05" + File.separator + "src" + File.separator + "main" + File.separator + "webapp", "");
			String saveFolder = projectPath  + File.separator + "final_05" + File.separator + "src" + File.separator + "assets" + File.separator + "upload";
			String filePath = saveFolder + File.separator + fileInfo.getSaveFolder() + File.separator + fileInfo.getSaveFile();
			File file = new File(filePath);
			if (file.delete()) {
				System.out.println("파일이 성공적으로 삭제되었습니다.");
			} else {
				System.out.println("파일을 삭제하는 데 문제가 발생했습니다.");
			}
		}
		hotplaceDao.deleteFile(articleNo);
		return hotplaceDao.remove(articleNo);
	}

	@Override
	public List<String> getSlang() {
		return hotplaceDao.getSlang();
	}

	@Override
	public int good(Map<String, String> map) {
		return hotplaceDao.good(map);
	}
	
	@Override
	public int updateGood(Map<String, String> map) {
		int articleNo = Integer.parseInt(map.get("articleNo"));
		int num = 0;
		System.out.println(map);
		Map<String, Integer> param = new HashMap<>();
		param.put("articleNo", articleNo);
		String check = map.get("check");
		System.out.println(check);
		if (check.equals("plus")) {
			num = 1;
			param.put("num", num);
			hotplaceDao.updateGoodArticle(param);
			hotplaceDao.addGood(map);
		}
		else {
			num = -1;
			param.put("num", num);
			hotplaceDao.updateGoodArticle(param);
			hotplaceDao.deleteGood(map);
		}
		return num;
	}

	
	


}
