package com.ssafy.enjoytrip.trip.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.enjoytrip.trip.model.*;
import com.ssafy.enjoytrip.trip.service.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 6000)
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
	public ResponseEntity<?> tripSearch(@ModelAttribute TripSearchDto tripSearchDto) {

		List<TripDto> tripSearchList = tripService.tripSearchList(tripSearchDto);
		ObjectMapper mapper = new ObjectMapper();
		String result;
		try {
			result = mapper.writeValueAsString(tripSearchList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(result);
	}


	@GetMapping("/monthlyList")
	public ResponseEntity<?> monthlyList() {

		List<MonthlyDto> list = tripService.monthlyList();
		ObjectMapper mapper = new ObjectMapper();
		String result;
		try {
			result = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/monthlyDetail/{idx}")
	public ResponseEntity<?> detail(@PathVariable int idx) {
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
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/tripDetail/{contentId}")
	public ResponseEntity<?> tripDetail(@PathVariable int contentId) {
		TripDto tripDto = tripService.tripDetail(contentId);
		System.out.println(tripDto);
		return ResponseEntity.ok(tripDto);
	}

	@PostMapping("/planRegist")
	public ResponseEntity<?> registPlan(@RequestBody PlanDto planDto) {
		System.out.println(planDto);
		int result = tripService.registPlan(planDto);

		return ResponseEntity.ok(result);

	}

	@GetMapping("/planList/{userId}")
	public ResponseEntity<?> listPlan(@PathVariable String userId) {
		List<PlanDto> list = tripService.listPlan(userId);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/planDetail/{planId}")
	public ResponseEntity<?> detailPlan(@PathVariable int planId) {
		List<TripDto> list = tripService.detailPlan(planId);
		return ResponseEntity.ok(list);
	}
	
	@DeleteMapping("/planDelete/{planId}")
	public ResponseEntity<?> deletePlan(@PathVariable int planId) {
		
		int result = tripService.deletePlan(planId);
		return ResponseEntity.ok(result);
	}
}
