package com.ssafy.enjoytrip.user.model;

public class UserDto {
	private String userName;
	private String userId;
	private String userPwd;
	private String userEmail;
	private String joinDate;
	private int isAdmin;
	private String img;
	private String refreshToken;
	
	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public UserDto() {}

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

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "UserDto [userName=" + userName + ", userId=" + userId + ", userPwd=" + userPwd + ", userEmail="
				+ userEmail + ", joinDate=" + joinDate + ", isAdmin=" + isAdmin + ", img=" + img + ", refreshToken="
				+ refreshToken + "]";
	}

	public UserDto(String userName, String userId, String userPwd, String userEmail, String joinDate, int isAdmin,
			String img, String refreshToken) {
		super();
		this.userName = userName;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userEmail = userEmail;
		this.joinDate = joinDate;
		this.isAdmin = isAdmin;
		this.img = img;
		this.refreshToken = refreshToken;
	}

	
	
}
