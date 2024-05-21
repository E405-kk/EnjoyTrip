package com.ssafy.enjoytrip.trip.model;

import java.util.List;

public class PlanDto {
	private int planId;
	private String userId;
	private String startDate;
	private String endDate;
	private String comment;
	private List<Integer> planList;
	public PlanDto() {}
	public PlanDto(int planId, String userId, String startDate, String endDate, String comment,
			List<Integer> planList) {
		super();
		this.planId = planId;
		this.userId = userId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.comment = comment;
		this.planList = planList;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<Integer> getPlanList() {
		return planList;
	}
	public void setPlanList(List<Integer> planList) {
		this.planList = planList;
	}
	@Override
	public String toString() {
		return "PlanDto [planId=" + planId + ", userId=" + userId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", comment=" + comment + ", planList=" + planList + "]";
	}
	
	
}
