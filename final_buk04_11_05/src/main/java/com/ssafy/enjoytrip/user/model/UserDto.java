package com.ssafy.enjoytrip.user.model;

public class UserDto {
	private String userName;
	private String userId;
	private String userPwd;
	private String userEmail;
	private String joinDate;
	public UserDto() {}
	public UserDto(String userName, String userId, String userPwd, String userEmail, String joinDate) {
		super();
		this.userName = userName;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userEmail = userEmail;
		this.joinDate = joinDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", userId=" + userId + ", userPwd=" + userPwd + ", userEmail=" + userEmail
				+ ", joinDate=" + joinDate + "]";
	}
	
}
