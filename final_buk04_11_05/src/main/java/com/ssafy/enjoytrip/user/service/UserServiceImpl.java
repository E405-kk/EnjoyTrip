package com.ssafy.enjoytrip.user.service;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.user.dao.UserDao;
import com.ssafy.enjoytrip.user.model.UserDto;

@Service
public class UserServiceImpl implements UserService{

	private final UserDao userDao;
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public int register(UserDto userDto) {
		if (userDao.idCheck(userDto.getUserId()) > 0) {
			return -1;
		}
		return userDao.register(userDto);
	}

	@Override
	public UserDto login(UserDto userDto) {
		return userDao.login(userDto);
	}

	@Override
	public int modify(UserDto userDto) {
		return userDao.modify(userDto);
	}

	@Override
	public int remove(String userId) {
		userDao.userBoardRemove(userId);
		int res = userDao.remove(userId);
		return res;
	}

	@Override
	public int findpwd(UserDto userDto) {
		return userDao.findpwd(userDto);
	}

	@Override
	public int changepwd(UserDto userDto) {
		return userDao.changepwd(userDto);
	}

	@Override
	public UserDto userInfo(String userId) {
		return userDao.userInfo(userId);
	}


}
