package com.ssafy.enjoytrip.hotplace.controller;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.hotplace.model.FileInfoDto;
import com.ssafy.enjoytrip.hotplace.model.HotplaceDto;
import com.ssafy.enjoytrip.hotplace.model.HotplaceListDto;
import com.ssafy.enjoytrip.hotplace.service.HotplaceService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequestMapping("/hotplace")
public class HotplaceController {

	private final HotplaceService hotplaceService;

	@Autowired
	private ServletContext servletContext;

	public HotplaceController(HotplaceService hotplaceService) {
		super();
		this.hotplaceService = hotplaceService;
	}

	@PostMapping("/regist")
	public ResponseEntity<?> regist(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestPart HotplaceDto hotplaceDto) throws IllegalStateException, IOException, URISyntaxException{
		List<String> slangs = hotplaceService.getSlang();
		String full = hotplaceDto.getSubject() + " " +  hotplaceDto.getContent();
		String slangFinded = null;		// 발견한 욕설(첫번째)
		for (String slang : slangs) {
			if (kmp(full, slang)) { // 욕설 있을 경우
				slangFinded = slang;
				break;
			}
		}
		String realPath = servletContext.getRealPath("/");
		String projectPath = realPath.replace(File.separator + "final_buk04_11_05" + File.separator + "src" + File.separator + "main" + File.separator + "webapp", "");
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		String saveFolder = projectPath  + File.separator + "final_05" + File.separator + "src" + File.separator + "assets" + File.separator + "upload" + File.separator + today;
		if (slangFinded == null) {
			if (file != null) {
				File folder = new File(saveFolder);
				if (!folder.exists())
					folder.mkdirs();
				FileInfoDto fileInfoDto = new FileInfoDto();
				String originalFileName = file.getOriginalFilename();
				if (!originalFileName.isEmpty()) {
					String saveFileName = UUID.randomUUID().toString()
							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
					fileInfoDto.setSaveFolder(today);
					fileInfoDto.setOriginalFile(originalFileName);
					fileInfoDto.setSaveFile(saveFileName);
					file.transferTo(new File(folder, saveFileName));
				}
				hotplaceDto.setFileInfo(fileInfoDto);
				
			}
			else {
				FileInfoDto fileInfoDto = new FileInfoDto();
				fileInfoDto.setSaveFile("about-bg.jpg");
				fileInfoDto.setSaveFolder("");
				fileInfoDto.setOriginalFile("");
				hotplaceDto.setFileInfo(fileInfoDto);
			}
			int result = hotplaceService.regist(hotplaceDto);
			
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else {	// 욕설 있을 경우
			String msg = "["+ slangFinded + "] 는 부적절한 단어입니다. 내용을 수정해주세요.";
			return ResponseEntity.ok(msg);
		}
	}

	@GetMapping("/detail/{articleNo}")
	public ResponseEntity<?> detail(@PathVariable int articleNo){
		HotplaceDto hotplaceDto = hotplaceService.detail(articleNo);
		hotplaceService.updateHit(articleNo);
		return ResponseEntity.ok(hotplaceDto);
	}
	@GetMapping("/modify/{articleNo}")
	public ResponseEntity<?> getModifyArticle(@PathVariable int articleNo){
		return ResponseEntity.ok(hotplaceService.detail(articleNo));
	}

	@PutMapping("/modify")
	public ResponseEntity<?> modify(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestPart HotplaceDto hotplaceDto, HttpServletRequest request) throws IllegalStateException, IOException{
		List<String> slangs = hotplaceService.getSlang();
		String full = hotplaceDto.getSubject() + " " +  hotplaceDto.getContent();
		String slangFinded = null;		// 발견한 욕설(첫번째)
		for (String slang : slangs) {
			if (kmp(full, slang)) { // 욕설 있을 경우
				slangFinded = slang;
				break;
			}
		}
		String realPath = servletContext.getRealPath("/");
		String projectPath = realPath.replace(File.separator + "final_buk04_11_05" + File.separator + "src" + File.separator + "main" + File.separator + "webapp", "");
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		String saveFolder = projectPath  + File.separator + "final_05" + File.separator + "src" + File.separator + "assets" + File.separator + "upload" + File.separator + today;
		if (slangFinded == null) {
			if (file != null) {
				File folder = new File(saveFolder);
				if (!folder.exists())
					folder.mkdirs();
				FileInfoDto fileInfoDto = new FileInfoDto();
				String originalFileName = file.getOriginalFilename();
				if (!originalFileName.isEmpty()) {
					String saveFileName = UUID.randomUUID().toString()
							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
					fileInfoDto.setSaveFolder(today);
					fileInfoDto.setOriginalFile(originalFileName);
					fileInfoDto.setSaveFile(saveFileName);
					file.transferTo(new File(folder, saveFileName));
				}
				hotplaceDto.setFileInfo(fileInfoDto);
				
			}
			else if (hotplaceDto.getFileInfo() == null){
				FileInfoDto fileInfoDto = new FileInfoDto();
				fileInfoDto.setSaveFile("about-bg.jpg");
				fileInfoDto.setSaveFolder("");
				fileInfoDto.setOriginalFile("");
				hotplaceDto.setFileInfo(fileInfoDto);
			}
			
			int result = hotplaceService.modify(hotplaceDto);
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {	// 욕설 있을 경우
			String msg = "["+ slangFinded + "] 는 부적절한 단어입니다. 내용을 수정해주세요.";
			return ResponseEntity.ok(msg);
		}
		
	}

	@DeleteMapping("/remove/{articleNo}")
	public ResponseEntity<?> remove(@PathVariable int articleNo){
		int result = hotplaceService.remove(articleNo);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/list")
	public ResponseEntity<?> list(@RequestParam Map<String, String> map){
		HotplaceListDto hotplaceListDto = hotplaceService.list(map);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return ResponseEntity.ok().headers(header).body(hotplaceListDto);
	}
	
	@GetMapping("/good")
	public ResponseEntity<?> good(@RequestParam Map<String, String> map){
		int result = hotplaceService.good(map);
		System.out.println(map);
		if (result == 0) {
			return ResponseEntity.ok(false);
		}
		else {
			return ResponseEntity.ok(true);
		}
	}
	
	@PutMapping("/updateGood")
	public ResponseEntity<?> updateGood(@RequestBody Map<String, String> map){
		System.out.println(map);
		int result = hotplaceService.updateGood(map);
		return ResponseEntity.ok(result);
	}
	
	private boolean kmp(String full, String slang) {

		char[] T = full.toCharArray();
		char[] P = slang.toCharArray();

		int tLength = T.length; // 본문 길이
		int pLength = P.length; // 패턴 길이

		// 1단계: 부분일치 테이블 만들기 (패턴 분석하기)
		int[] pi = new int[pLength]; // pi 테이블

		// i: 패턴의 접미사 포인터
		// j: 패턴의 접두사 포인터
		for (int i = 1, j = 0; i < pLength; i++) {
			// 현재 비교하는 문자가 불일치하면
			while (j > 0 && P[i] != P[j]) {
				// 패턴의 접미사와 패턴의 접두사가
				// 일치하도록 j 포인터를 옮긴다.
				// (처음부터 비교하지 않고 접두사 길이만큼 생략하여 그다음부터 비교)
				j = pi[j - 1];
			}
			// 일치하는 부분 발견
			// 연속적으로 일치하게되면 접두사 혹은 접미사의 길이가 길어짐
			if (P[i] == P[j]) {
				pi[i] = ++j;
			}
			// 불일치하면 처음부터 비교하기 위한 인덱스 번호 0 넣기
			else {
				pi[i] = 0;
			}
		}

		// 2단계: 부분일치 테이블을 활용하여 텍스트와 패턴 비교

		// i: 텍스트의 접미사 포인터
		// j: 패턴의 접두사 포인터
		for (int i = 0, j = 0; i < tLength; i++) {

			// 현재 비교하는 문자가 불일치하면
			while (j > 0 && T[i] != P[j]) {
				// 텍스트의 접미사와 패턴의 접두사가
				// 일치하도록 j 포인터를 옮긴다.
				// (처음부터 비교하지 않고 접두사 길이만큼 생략하여 그다음부터 비교)
				j = pi[j - 1];
			}

			// 두 글자 일치
			if (T[i] == P[j]) {

				// j가 패턴의 마지막 인덱스라면 패턴과 일치하는 단어 찾기 완료!
				if (j == pLength - 1) {
					return true;
				}
				// j가 패턴의 마지막 인덱스가 아닐 경우는 다음 패턴 인덱스로 이동
				else {
					j++;
				}
			}
		}
		return false;
	}
}
