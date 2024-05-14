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
	public int idCheck(String userId) {
		return userDao.idCheck(userId);
	}

	@Override
	public int register(UserDto userDto) {
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
		return userDao.remove(userId);
	}

	@Override
	public int findpwd(UserDto userDto) {
		return userDao.findpwd(userDto);
	}

	@Override
	public int changepwd(UserDto userDto) {
		return userDao.changepwd(userDto);
	}


}
