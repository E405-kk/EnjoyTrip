package com.ssafy.enjoytrip.user.service;


import com.ssafy.enjoytrip.user.model.UserDto;

public interface UserService {
	int register(UserDto userDto);
	UserDto login(UserDto userDto);
	int modify(UserDto userDto); 
	int remove(String userId);
	int findpwd(UserDto userDto);
	int changepwd(UserDto userDto);
	UserDto userInfo(String userId);
}
