package com.ssafy.enjoytrip.hotplace.model;

public class HotplaceDto {
	private int articleNo;
	private String userId;
	private String userName;
	private String subject;
	private String content;
	private int hit;
	private String registerTime;
	private int commentCnt;
	private int good;
	private String img;
	private String visitDate;
	private String latitude;
	private String longitude;
	private String addr;
	private FileInfoDto fileInfo;

	public HotplaceDto() {
	}

	public HotplaceDto(int articleNo, String userId, String userName, String subject, String content, int hit, String registerTime, int commentCnt, int good, String img, String visitDate, String latitude, String longitude, String addr, FileInfoDto fileInfo) {
		this.articleNo = articleNo;
		this.userId = userId;
		this.userName = userName;
		this.subject = subject;
		this.content = content;
		this.hit = hit;
		this.registerTime = registerTime;
		this.commentCnt = commentCnt;
		this.good = good;
		this.img = img;
		this.visitDate = visitDate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.addr = addr;
		this.fileInfo = fileInfo;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public int getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}

	public int getGood() {
		return good;
	}

	public void setGood(int good) {
		this.good = good;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public FileInfoDto getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfoDto fileInfo) {
		this.fileInfo = fileInfo;
	}

	@Override
	public String toString() {
		return "HotplaceDto{" +
				"articleNo=" + articleNo +
				", userId='" + userId + '\'' +
				", userName='" + userName + '\'' +
				", subject='" + subject + '\'' +
				", content='" + content + '\'' +
				", hit=" + hit +
				", registerTime='" + registerTime + '\'' +
				", commentCnt=" + commentCnt +
				", good=" + good +
				", img='" + img + '\'' +
				", visitDate='" + visitDate + '\'' +
				", latitude='" + latitude + '\'' +
				", longitude='" + longitude + '\'' +
				", addr='" + addr + '\'' +
				", fileInfo=" + fileInfo +
				'}';
	}
}
