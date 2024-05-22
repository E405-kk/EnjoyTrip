package com.ssafy.enjoytrip.user.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.user.dao.UserDao;
import com.ssafy.enjoytrip.user.model.UserDto;

import jakarta.servlet.ServletContext;

@Service
public class UserServiceImpl implements UserService{

	private final UserDao userDao;

	@Autowired
	private ServletContext servletContext;

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
		UserDto userInfo = userDao.userInfo(userDto.getUserId());
		String realPath = servletContext.getRealPath("/");
		String projectPath = realPath.replace(File.separator + "final_buk04_11_05" + File.separator + "src" + File.separator + "main" + File.separator + "webapp", "");
		String saveFolder = projectPath  + File.separator + "final_05" + File.separator + "src" + File.separator + "assets" + File.separator + "users";
		String filePath = saveFolder + File.separator + userInfo.getImg();
		File file = new File(filePath);
		if (!userDto.getImg().equals(userInfo.getImg())) {
			file.delete();
		}
		userDao.modifyBoardImg(userDto);
		userDao.modifyCommentImg(userDto);
		userDao.modifyHotplaceImg(userDto);
		return userDao.modify(userDto);
	}

	@Override
	public int remove(String userId) {
		UserDto userInfo = userDao.userInfo(userId);
		String realPath = servletContext.getRealPath("/");
		String projectPath = realPath.replace(File.separator + "final_buk04_11_05" + File.separator + "src" + File.separator + "main" + File.separator + "webapp", "");
		String saveFolder = projectPath  + File.separator + "final_05" + File.separator + "src" + File.separator + "assets" + File.separator + "users";
		String filePath = saveFolder + File.separator + userInfo.getImg();
		File file = new File(filePath);
		file.delete();
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
