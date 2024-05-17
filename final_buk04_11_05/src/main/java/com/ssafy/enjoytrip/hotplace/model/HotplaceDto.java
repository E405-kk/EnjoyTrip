package com.ssafy.enjoytrip.hotplace.model;

public class HotplaceDto {
	private int articleNo;
	private String userId;
	private String subject;
	private String content;
	private int hit;
	private String registerTime;
	private int commentCnt;
	private int like;
	private FileInfoDto fileInfo;
	
	public FileInfoDto getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfoDto fileInfo) {
		this.fileInfo = fileInfo;
	}

	public int getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	@Override
	public String toString() {
		return "HotplaceDto [articleNo=" + articleNo + ", userId=" + userId + ", subject=" + subject + ", content="
				+ content + ", hit=" + hit + ", registerTime=" + registerTime + ", commentCnt=" + commentCnt + ", like="
				+ like + ", fileInfo=" + fileInfo + "]";
	}

}
