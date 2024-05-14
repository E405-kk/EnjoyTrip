package com.ssafy.enjoytrip.trip.model;

import java.sql.PreparedStatement;
import java.util.List;

public class TripPlanDto {
	private String userId;
	private List<String> planList;
	private String planList2;
	
	public String getPlanList2() {
		StringBuilder sb = new StringBuilder();
		if (planList == null) {
			return planList2;
		} else {
			for(int i=0;i<planList.size();i++) {
				sb.append(planList.get(i));
				if(i != planList.size()-1) {
					sb.append(",");
				}
			}
			return sb.toString();
		}
		
	}

	public void setPlanList2(String planList2) {
		this.planList2 = planList2;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getPlanList() {
		return planList;
	}

	public void setPlanList(List<String> planList) {
		this.planList = planList;
	}

	public TripPlanDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TripPlanDto(List<String> planList) {
		super();
		this.planList = planList;
	}

	@Override
	public String toString() {
		return "TripPlanDto [userId=" + userId + ", planList=" + planList + "]";
	}

	
	
	
}
