package com.ssafy.enjoytrip.trip.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.enjoytrip.trip.model.SidoDto;
import com.ssafy.enjoytrip.trip.model.TripDto;
import com.ssafy.enjoytrip.trip.model.TripPlanDto;
import com.ssafy.enjoytrip.trip.model.TripSearchDto;
import com.ssafy.enjoytrip.trip.service.TripService;
import com.ssafy.enjoytrip.user.model.UserDto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/trip")
public class TripController {
	private final TripService tripService;
	
	public TripController(TripService tripService) {
		this.tripService = tripService;
	}

	@GetMapping("/trip")
	public ResponseEntity<?> trip() {
		List<SidoDto> sidoList = tripService.sidoList();
		return ResponseEntity.ok(sidoList);
	}
	
	@GetMapping("/tripSearch")
	public ResponseEntity<?> tripSearch(@RequestBody TripSearchDto tripSearchDto){
		List<TripDto> tripSearchList = tripService.tripSearchList(tripSearchDto);
		ObjectMapper mapper = new ObjectMapper();
		String result;
		try {
			result = mapper.writeValueAsString(tripSearchList);
		} catch (JsonProcessingException e) {
//			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(result);
	}
		
	@GetMapping("/tripPlan")
	public ResponseEntity<?> tripPlan() {
		List<SidoDto> sidoList = tripService.sidoList();
		return ResponseEntity.ok(sidoList);
	}
	
	@DeleteMapping("/tripPlanDelete")
	public ResponseEntity<?> tripPlanDelete(@RequestBody UserDto userDto){
		int result = tripService.tripPlanDelete(userDto);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/tripPlanSave")
	public ResponseEntity<?> tripPlanSave(@RequestBody TripPlanDto tripPlanDto){
		
		if (tripPlanDto.getPlanList().size() >= 10) {
			String msg = "10개 이하의 계획만 가능합니다. (임시)";
			return ResponseEntity.ok(msg);
		} else {
			for (int i = 0; i < tripPlanDto.getPlanList().size(); i++) {
				tripService.updateReadCount(tripPlanDto.getPlanList().get(i));
			}
			int result = tripService.tripPlanSave(tripPlanDto);
			return ResponseEntity.ok(result);
		}
	}
	
	@GetMapping("/userTripPlanList")
	public ResponseEntity<?> userTripPlanList(@RequestBody UserDto userDto){
		// 유저가 등록한 여행계획 들고오기
		TripPlanDto userTripPlan = tripService.userTripPlan(userDto);

		//tripPlanDto에 있는 planList를 전부 검색해서 List<TripDto>로 변환하는 코드 작성
		String[] titles = userTripPlan.getPlanList2().split(",");
		System.out.println(Arrays.toString(titles));
		List<TripDto> result  = new ArrayList<>();
		for (String title : titles) {
			result.add(tripService.tripPlanDtoToTripDtoList(title));
		}
		
		//클라이언트로 리턴
		return ResponseEntity.ok(result);
	}

}
