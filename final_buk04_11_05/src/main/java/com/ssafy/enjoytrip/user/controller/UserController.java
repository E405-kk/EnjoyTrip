package com.ssafy.enjoytrip.user.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.ssafy.enjoytrip.user.model.UserDto;
import com.ssafy.enjoytrip.user.service.UserService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequestMapping("/user")
public class UserController{

	private final UserService userService;

	@Autowired
	private ServletContext servletContext;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/login")
	public ResponseEntity<?> login(@ModelAttribute UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			UserDto user = userService.login(userDto);
			if (user != null) {
				resultMap.put("userId", user.getUserId());
				// session에 로그인 정보 담아서 index페이지로 이동 
				status = HttpStatus.CREATED;

			} else {	// 로그인 실패 
				resultMap.put("message", "아이디 또는 패스워드를 확인해 주세요.");
				status = HttpStatus.UNAUTHORIZED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestParam(value = "file", required = false) MultipartFile file, @RequestPart UserDto userDto) throws IllegalStateException, IOException {
		System.out.println(userDto);
		System.out.println(file);
		String realPath = servletContext.getRealPath("/");
		String projectPath = realPath.replace(File.separator + "final_buk04_11_05" + File.separator + "src" + File.separator + "main" + File.separator + "webapp", "");
		String saveFolder = projectPath  + File.separator + "final_05" + File.separator + "src" + File.separator + "assets" + File.separator + "users";
		if (file != null) {
			File folder = new File(saveFolder);
			if (!folder.exists())
				folder.mkdirs();
			String originalFileName = file.getOriginalFilename();
			if (!originalFileName.isEmpty()) {
				String saveFileName = UUID.randomUUID().toString()
						+ originalFileName.substring(originalFileName.lastIndexOf('.'));
				file.transferTo(new File(folder, saveFileName));
				userDto.setImg(saveFileName);
			}
		}
		System.out.println(userDto);
		int result = userService.register(userDto);
		
		HttpStatus status = HttpStatus.ACCEPTED;
		if (result > 0) {
			status = HttpStatus.CREATED;
		}
		else if (result == -1) {
			status = HttpStatus.NO_CONTENT;
		}
		else {
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Void>(status);
	}

	@GetMapping("/info/{userId}")
	public ResponseEntity<?> getInfo(
			@PathVariable("userId") String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			//	로그인 사용자 정보.
			UserDto userDto = userService.userInfo(userId);
			resultMap.put("user", userDto);
			status = HttpStatus.OK;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PutMapping("/modify")
	public ResponseEntity<?> modify(@RequestParam(value = "file", required = false) MultipartFile file,@RequestPart UserDto userDto) throws IllegalStateException, IOException {
		Map<String, Object> resultMap = new HashMap<>();
		String realPath = servletContext.getRealPath("/");
		String projectPath = realPath.replace(File.separator + "final_buk04_11_05" + File.separator + "src" + File.separator + "main" + File.separator + "webapp", "");
		String saveFolder = projectPath  + File.separator + "final_05" + File.separator + "src" + File.separator + "assets" + File.separator + "users";
		if (file != null) {
			File folder = new File(saveFolder);
			if (!folder.exists())
				folder.mkdirs();
			String originalFileName = file.getOriginalFilename();
			if (!originalFileName.isEmpty()) {
				String saveFileName = UUID.randomUUID().toString()
						+ originalFileName.substring(originalFileName.lastIndexOf('.'));
				file.transferTo(new File(folder, saveFileName));
				userDto.setImg(saveFileName);
			}
		}
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			int result = userService.modify(userDto);
			if(result > 0) {
				resultMap.put("message", "회원 정보 수정 성공");
				status = HttpStatus.CREATED;
			}
			else {
				resultMap.put("message", "회원 정보 수정 실패");
				status = HttpStatus.UNAUTHORIZED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@DeleteMapping("/remove/{userId}")
	public ResponseEntity<?> remove(@PathVariable String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		System.out.println("usercontroller - remove() : " + userId);
		try {
			int result = userService.remove(userId);
			System.out.println("usercontroller - remove : "+ result);
			if(result > 0) {
				resultMap.put("message", "회원 삭제 성공");
				status = HttpStatus.OK;
			}
			else {
				resultMap.put("message", "회원 삭제 실패");
				status = HttpStatus.UNAUTHORIZED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/findpwd")
	public ResponseEntity<?> findpwd(@ModelAttribute UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			System.out.println(userDto);
			int result = userService.findpwd(userDto);
			System.out.println(result);
			if(result > 0) {
				resultMap.put("message", "비밀번호 찾기 성공");
				status = HttpStatus.OK;
			}
			else {
				resultMap.put("message", "비밀번호 찾기 실패");
				status = HttpStatus.UNAUTHORIZED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PutMapping("/changepwd")
	public ResponseEntity<?> changepwd(@RequestBody UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			int result = userService.changepwd(userDto);
			if(result > 0) {
				resultMap.put("message", "비밀번호 변경 성공");
				status = HttpStatus.OK;
			}
			else {
				resultMap.put("message", "비밀번호 변경 실패");
				status = HttpStatus.UNAUTHORIZED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
