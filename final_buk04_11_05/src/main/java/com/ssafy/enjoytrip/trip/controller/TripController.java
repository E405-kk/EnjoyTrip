package com.ssafy.enjoytrip.trip.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.trip.model.MonthlyDto;
import com.ssafy.enjoytrip.trip.model.SidoDto;
import com.ssafy.enjoytrip.trip.model.TripDto;
import com.ssafy.enjoytrip.trip.model.TripPlanDto;
import com.ssafy.enjoytrip.trip.model.TripSearchDto;
import com.ssafy.enjoytrip.trip.service.TripService;
import com.ssafy.enjoytrip.user.model.UserDto;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
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
	public ResponseEntity<?> tripSearch(@ModelAttribute TripSearchDto tripSearchDto){
		
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
	
	@DeleteMapping("/tripPlanDelete/{userId}")
	public ResponseEntity<?> tripPlanDelete(@PathVariable String userId){
		UserDto userDto = new UserDto();
		userDto.setUserId(userId);
		System.out.println("tripPlanDelete: "+userDto);
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
	public ResponseEntity<?> userTripPlanList(@ModelAttribute UserDto userDto){
		// 유저가 등록한 여행계획 들고오기
		TripPlanDto userTripPlan = tripService.userTripPlan(userDto);
		List<TripDto> result  = new ArrayList<>();

		//tripPlanDto에 있는 planList를 전부 검색해서 List<TripDto>로 변환하는 코드 작성
		if (userTripPlan == null) {
			return ResponseEntity.ok(result);
		}
		String[] titles = userTripPlan.getPlanList2().split(",");
		
		
		for (String title : titles) {
			result.add(tripService.tripPlanDtoToTripDtoList(title));
		}
		
		//클라이언트로 리턴
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/monthlyList")
	public ResponseEntity<?> monthlyList(){
		
		List<MonthlyDto> list = tripService.monthlyList();
		ObjectMapper mapper = new ObjectMapper();
		String result;
		try {
			result = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
//			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/monthlyDetail/{idx}")
	public ResponseEntity<?> detail(@PathVariable int idx){
		MonthlyDto monthlyDto = tripService.detail(idx);
		return ResponseEntity.ok(monthlyDto);
	}
	
	@GetMapping("/rankList")
	public ResponseEntity<?> rankList() {
		
		List<TripDto> list = tripService.rankList();
		
		ObjectMapper mapper = new ObjectMapper();
		String result;
		try {
			result = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
//			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(result);
	}
	
	
}
