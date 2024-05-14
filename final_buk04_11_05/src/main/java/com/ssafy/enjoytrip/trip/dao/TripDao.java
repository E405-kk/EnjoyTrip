package com.ssafy.enjoytrip.trip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.trip.model.SidoDto;
import com.ssafy.enjoytrip.trip.model.TripDto;
import com.ssafy.enjoytrip.trip.model.TripPlanDto;
import com.ssafy.enjoytrip.trip.model.TripSearchDto;
import com.ssafy.enjoytrip.user.model.UserDto;
@Mapper
public interface TripDao {

	List<SidoDto> sidoList();
	List<TripDto> tripSearchList(TripSearchDto tripSearchDto);
	int updateReadCount(String title);
	int tripPlanSave(TripPlanDto tripPlanDto);
	TripPlanDto userTripPlan(UserDto userDto);
	TripDto tripPlanDtoToTripDtoList(String title);
	List<TripDto> rankList();
	int tripPlanDelete(UserDto userDto);
}
