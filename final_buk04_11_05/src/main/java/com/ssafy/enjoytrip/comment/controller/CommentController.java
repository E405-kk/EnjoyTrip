package com.ssafy.enjoytrip.comment.controller;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.comment.model.CommentDto;
import com.ssafy.enjoytrip.comment.model.CommentListDto;
import com.ssafy.enjoytrip.comment.service.CommentService;

@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 6000)
@RestController
@RequestMapping("/comment")
public class CommentController {

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}

	@PostMapping("/regist")
	public ResponseEntity<?> regist(@RequestBody CommentDto commentDto) {
		List<String> slangs = commentService.getSlang();
		String full = commentDto.getContent();
		String slangFinded = null; // 발견한 욕설(첫번째)
		for (String slang : slangs) {
			if (kmp(full, slang)) { // 욕설 있을 경우
				slangFinded = slang;
				break;
			}
		}
		if (slangFinded == null) {
			int result = commentService.regist(commentDto);

			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else { // 욕설 있을 경우
			String msg = "[" + slangFinded + "] 는 부적절한 단어입니다. 내용을 수정해주세요.";
			return ResponseEntity.ok(msg);
		}
	}
	
	@PutMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody CommentDto commentDto) {
		int result = commentService.modify(commentDto);
		System.out.println("modify: "+ commentDto);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/remove/{idx}")
	public ResponseEntity<?> remove(@PathVariable int idx) {
		int result = commentService.remove(idx);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/list")
	public ResponseEntity<?> list(@RequestParam Map<String, String> map) {
		CommentListDto commentListDto = commentService.list(map);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return ResponseEntity.ok().headers(header).body(commentListDto);
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
