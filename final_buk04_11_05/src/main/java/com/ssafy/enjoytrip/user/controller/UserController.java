package com.ssafy.enjoytrip.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.user.model.UserDto;
import com.ssafy.enjoytrip.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController{
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
	@GetMapping("/register")
	public String register() {
		return "user/register";
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserDto userDto) {
		int result = userService.register(userDto);
		return ResponseEntity.ok(result);
	}
//	@GetMapping("/login")
//	public String login() {
//		return "user/login";
//	}
	@GetMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpSession session, HttpServletRequest request) {
		UserDto user = userService.login(userDto);
		if (userDto != null) {
			// session 설정
//			user.setUserPwd(userDto.getUserPwd());
//			session.setAttribute("userinfo", user);
			
			// session에 로그인 정보 담아서 index페이지로 이동 
			return ResponseEntity.ok(user);
			
		} else {	// 로그인 실패 
//			model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요.");
			return ResponseEntity.noContent().build();
		}
	}
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session, HttpServletRequest request) {
		session.invalidate();
//		return "redirect:" + request.getContextPath() + "/";
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/manage")
	public String manage() {
		return "user/manage";
	}
	
	@GetMapping("/modify")
	public String modify() {
		return "user/modify";
	}
	
	@PutMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody UserDto userDto, HttpSession session) {
		System.out.println("postmodify");
		int result = userService.modify(userDto);
		session.setAttribute("userinfo", userDto);
		return ResponseEntity.ok(result);
	}
	@DeleteMapping("/remove/{userId}")
	public ResponseEntity<?> remove(@PathVariable String userId) {
		int result = userService.remove(userId);
		return ResponseEntity.ok(result);
	}
//	@GetMapping("/findpwd")
//	public String findpwd() {
//		return "user/findpwd";
//	}
	
	@GetMapping("/findpwd")
	public ResponseEntity<?> findpwd(@RequestBody UserDto userDto) {
		if (userService.findpwd(userDto) > 0) {	// 비밀번호 찾기 성공 
			return ResponseEntity.ok(userDto);
		} else {
//			model.addAttribute("msg", "일치하는 회원정보가 없습니다. 이름 혹은 아이디를 다시 입력하세요.");
//			return "user/findpwd";
			return ResponseEntity.noContent().build();
		}
	}
	
	@PutMapping("/changepwd")
	public ResponseEntity<?> changepwd(@RequestBody UserDto userDto) {
		int result = userService.changepwd(userDto);
		return ResponseEntity.ok(result);
	}
}
