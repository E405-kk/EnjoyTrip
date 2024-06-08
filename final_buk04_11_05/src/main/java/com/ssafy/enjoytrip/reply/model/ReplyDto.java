package com.ssafy.enjoytrip.reply.model;

public class ReplyDto {
	private int idx;
	private int parentIdx;
	private String userId;
	private String content;
	private String registerTime;
	private String img;

	public ReplyDto(int idx, int parentIdx, String userId, String content, String registerTime, String img) {
		this.idx = idx;
		this.parentIdx = parentIdx;
		this.userId = userId;
		this.content = content;
		this.registerTime = registerTime;
		this.img = img;
	}
	public ReplyDto(){}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getParentIdx() {
		return parentIdx;
	}

	public void setParentIdx(int parentIdx) {
		this.parentIdx = parentIdx;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "ReplyDto{" +
				"idx=" + idx +
				", parentIdx=" + parentIdx +
				", userId='" + userId + '\'' +
				", content='" + content + '\'' +
				", registerTime='" + registerTime + '\'' +
				", img='" + img + '\'' +
				'}';
	}
}
