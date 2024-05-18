package com.ssafy.enjoytrip.comment.model;

import java.util.List;

public class CommentListDto {

	private List<CommentDto> articles;
	private int currentPage;
	private int totalPageCount;
	
	public CommentListDto() {
		super();
	}
	public CommentListDto(List<CommentDto> articles, int currentPage, int totalPageCount) {
		super();
		this.articles = articles;
		this.currentPage = currentPage;
		this.totalPageCount = totalPageCount;
	}
	public List<CommentDto> getArticles() {
		return articles;
	}
	public void setArticles(List<CommentDto> articles) {
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
		return "CommentListDto [articles=" + articles + ", currentPage=" + currentPage + ", totalPageCount="
				+ totalPageCount + "]";
	}
	

}
