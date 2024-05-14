package com.ssafy.enjoytrip.trip.model;

public class TripSearchDto {
	int sidoCode;
	int contentTypeId;
	String keyword;
	
	public TripSearchDto() {}
	public TripSearchDto(int sidoCode, int contentTypeId, String keyword) {
		super();
		this.sidoCode = sidoCode;
		this.contentTypeId = contentTypeId;
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public int getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}
	public int getContentTypeId() {
		return contentTypeId;
	}
	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}
	@Override
	public String toString() {
		return "TripSearchDto [sidoCode=" + sidoCode + ", contentTypeId=" + contentTypeId + ", keyword=" + keyword
				+ "]";
	}
	
	
}
