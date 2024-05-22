package com.ssafy.enjoytrip.comment.model;

public class CommentDto {
	private int idx;
	private int articleNo;
	private String userId;
	private String content;
	private String registerTime;
	private String img;
	
	public CommentDto(int idx, int articleNo, String userId, String content, String registerTime, String img) {
		super();
		this.idx = idx;
		this.articleNo = articleNo;
		this.userId = userId;
		this.content = content;
		this.registerTime = registerTime;
		this.img = img;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public CommentDto() {}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	@Override
	public String toString() {
		return "CommentDto [idx=" + idx + ", articleNo=" + articleNo + ", userId=" + userId + ", content=" + content
				+ ", registerTime=" + registerTime + ", img=" + img + "]";
	}
}
