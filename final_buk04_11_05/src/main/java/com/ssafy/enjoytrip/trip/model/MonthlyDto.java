package com.ssafy.enjoytrip.trip.model;

public class MonthlyDto {
	int mon;
	String region;
	String title;
	String addr1;
	String addr2;
	String firstImage;
	double latitude;
	double longitude;
	String overview;
	public int getMon() {
		return mon;
	}
	public void setMon(int mon) {
		this.mon = mon;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getFirstImage() {
		return firstImage;
	}
	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	@Override
	public String toString() {
		return "MonthlyDto [mon=" + mon + ", region=" + region + ", title=" + title + ", addr1=" + addr1 + ", addr2="
				+ addr2 + ", firstImage=" + firstImage + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", overview=" + overview + "]";
	}
	
}
