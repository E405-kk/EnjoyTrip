package com.ssafy.enjoytrip.trip.service;

import java.util.List;

import com.ssafy.enjoytrip.trip.model.MonthlyDto;
import com.ssafy.enjoytrip.trip.model.SidoDto;
import com.ssafy.enjoytrip.trip.model.TripDto;
import com.ssafy.enjoytrip.trip.model.TripPlanDto;
import com.ssafy.enjoytrip.trip.model.TripSearchDto;
import com.ssafy.enjoytrip.user.model.UserDto;

public interface TripService {

	List<SidoDto> sidoList();
	List<TripDto> tripSearchList(TripSearchDto tripSearchDto);
	int updateReadCount(String title);
	int	tripPlanSave(TripPlanDto tripPlanDto);
	TripPlanDto userTripPlan(UserDto userDto);
	TripDto tripPlanDtoToTripDtoList(String title);
	List<TripDto> rankList();
	int tripPlanDelete(UserDto userDto);
	List<MonthlyDto> monthlyList();
	MonthlyDto detail(int idx);
	TripDto tripDetail(int contentId);
}
