package com.ssafy.enjoytrip.trip.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.trip.model.MonthlyDto;
import com.ssafy.enjoytrip.trip.model.PlanDto;
import com.ssafy.enjoytrip.trip.model.SidoDto;
import com.ssafy.enjoytrip.trip.model.TripDto;
import com.ssafy.enjoytrip.trip.model.TripSearchDto;
import com.ssafy.enjoytrip.user.model.UserDto;
@Mapper
public interface TripDao {

	List<SidoDto> sidoList();
	List<TripDto> tripSearchList(TripSearchDto tripSearchDto);
	int updateReadCount(int contentId);
	TripDto tripPlanDtoToTripDtoList(String title);
	List<TripDto> rankList();
	int tripPlanDelete(UserDto userDto);
	String tripDetailSearch(int contentId);
	List<MonthlyDto> monthlyList();
	TripDto tripDetail(int contentId);
	int registPlan(PlanDto planDto);
	int registPlanDetail(Map<String, Object> map);
	List<PlanDto> listPlan(String userId);
	List<Integer> detailPlan(int planId);
	int deletePlan(int planId);
}
