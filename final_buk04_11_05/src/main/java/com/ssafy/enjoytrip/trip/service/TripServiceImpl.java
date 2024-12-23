package com.ssafy.enjoytrip.trip.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.trip.dao.TripDao;
import com.ssafy.enjoytrip.trip.model.MonthlyDto;
import com.ssafy.enjoytrip.trip.model.PlanDto;
import com.ssafy.enjoytrip.trip.model.SidoDto;
import com.ssafy.enjoytrip.trip.model.TripDto;
import com.ssafy.enjoytrip.trip.model.TripSearchDto;
import com.ssafy.enjoytrip.user.model.UserDto;

@Service
public class TripServiceImpl implements TripService {

	private final TripDao tripDao;
	public TripServiceImpl( TripDao tripDao) {
		this.tripDao = tripDao;
	}

	@Override
	public List<SidoDto> sidoList() {
		return tripDao.sidoList();
	}

	@Override
	public List<TripDto> tripSearchList(TripSearchDto tripSearchDto) {
		List<TripDto> list = tripDao.tripSearchList(tripSearchDto);
		for (TripDto trip: list) {
			trip.setOverview(tripDao.tripDetailSearch(trip.getContentId()));
		}
		return list;
	}
	
	@Override
	public int updateReadCount(int contentId) {
		return tripDao.updateReadCount(contentId);
	}
	
	
	@Override
	public TripDto tripPlanDtoToTripDtoList(String title){
		return tripDao.tripPlanDtoToTripDtoList(title);
	}
	
	@Override
	public List<TripDto> rankList() {
		
		List<TripDto> list = tripDao.rankList();
		List<TripDto> result = new ArrayList<>();
	
		sort(list);	
		
		for (int i = list.size() - 1; i > list.size() - 11; i-- ) {
			result.add(list.get(i));
		}
		return result;
	}
	
	
	
	@Override
	public int tripPlanDelete(UserDto userDto) {
		return tripDao.tripPlanDelete(userDto);
	}
	
	public static <T extends Comparable<T>> void sort(List<T> list) {
        if (list == null || list.size() <= 1) {
            return;
        }
        quickSort(list, 0, list.size() - 1);
    }

    private static <T extends Comparable<T>> void quickSort(List<T> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);

            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private static <T extends Comparable<T>> int partition(List<T> list, int low, int high) {
        T pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j).compareTo(pivot) <= 0) {
                i++;

                T temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        T temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }

	@Override
	public List<MonthlyDto> monthlyList() {
		return tripDao.monthlyList();
	}

	@Override
	public MonthlyDto detail(int idx) {
		List<MonthlyDto> list = tripDao.monthlyList();
		MonthlyDto monthlyDto = null;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getIdx() == idx) {
				 monthlyDto = list.get(i);
			}
		}
		return monthlyDto;
	}

	@Override
	public TripDto tripDetail(int contentId) {
		return tripDao.tripDetail(contentId);
	}

	@Override
	public int registPlan(PlanDto planDto) {
		int res = tripDao.registPlan(planDto);
		for (int i = 0; i < planDto.getPlanList().size(); i++) {
			int contentId = planDto.getPlanList().get(i);
			tripDao.updateReadCount(contentId);	// readCount 증가
			//plan_detail에 등록
			Map<String , Object> map = new HashMap<>();
			map.put("planId", planDto.getPlanId());
			map.put("contentId", contentId);
			map.put("order", i+1);
			tripDao.registPlanDetail(map);
		}
		
		return res;
	}

	@Override
	public List<PlanDto> listPlan(String userId) {
		return tripDao.listPlan(userId);
	}

	@Override
	public List<TripDto> detailPlan(int planId) {
		List<TripDto> list = new ArrayList<>();
		List<Integer> contentList =tripDao.detailPlan(planId); 
		for (int i : contentList) {
			list.add(tripDao.tripDetail(i));
		}
		return list;
	}

	@Override
	public int deletePlan(int planId) {
		return tripDao.deletePlan(planId);
	}
}
