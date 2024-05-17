package com.ssafy.enjoytrip.hotplace.model;

import java.util.List;

public class HotplaceListDto {

	private List<HotplaceDto> articles;
	private int currentPage;
	private int totalPageCount;
	
	public HotplaceListDto() {
		super();
	}
	public HotplaceListDto(List<HotplaceDto> articles, int currentPage, int totalPageCount) {
		super();
		this.articles = articles;
		this.currentPage = currentPage;
		this.totalPageCount = totalPageCount;
	}
	public List<HotplaceDto> getArticles() {
		return articles;
	}
	public void setArticles(List<HotplaceDto> articles) {
		this.articles = articles;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	@Override
	public String toString() {
		return "BoardListDto [articles=" + articles + ", currentPage=" + currentPage + ", totalPageCount="
				+ totalPageCount + "]";
	}
	
	

}
