package com.ssafy.enjoytrip.reply.model;

import java.util.List;

public class ReplyListDto {

	private List<ReplyDto> articles;
	private int currentPage;
	private int totalPageCount;
	
	public ReplyListDto() {
		super();
	}
	public ReplyListDto(List<ReplyDto> articles, int currentPage, int totalPageCount) {
		super();
		this.articles = articles;
		this.currentPage = currentPage;
		this.totalPageCount = totalPageCount;
	}
	public List<ReplyDto> getArticles() {
		return articles;
	}
	public void setArticles(List<ReplyDto> articles) {
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
		return "ReplyListDto [articles=" + articles + ", currentPage=" + currentPage + ", totalPageCount="
				+ totalPageCount + "]";
	}
	

}
