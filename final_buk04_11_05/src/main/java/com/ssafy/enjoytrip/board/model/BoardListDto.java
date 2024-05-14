package com.ssafy.enjoytrip.board.model;

import java.util.List;

public class BoardListDto {

	private List<BoardDto> articles;
	private int currentPage;
	private int totalPageCount;
	
	public BoardListDto() {
		super();
	}
	public BoardListDto(List<BoardDto> articles, int currentPage, int totalPageCount) {
		super();
		this.articles = articles;
		this.currentPage = currentPage;
		this.totalPageCount = totalPageCount;
	}
	public List<BoardDto> getArticles() {
		return articles;
	}
	public void setArticles(List<BoardDto> articles) {
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
