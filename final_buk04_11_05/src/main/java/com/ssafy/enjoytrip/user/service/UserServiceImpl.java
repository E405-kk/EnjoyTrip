package com.ssafy.enjoytrip.user.service;

import com.ssafy.enjoytrip.user.dao.UserDao;
import com.ssafy.enjoytrip.user.model.UserDto;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

	private final UserDao userDao;
	private final MailService mailService;

	@Autowired
	private ServletContext servletContext;

	public UserServiceImpl(UserDao userDao, MailService mailService) {
		this.userDao = userDao;
		this.mailService = mailService;
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
		if (userInfo.getImg()!=null && !userDto.getImg().equals(userInfo.getImg())) {
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
		int result = userDao.findpwd(userDto);
		if (result > 0) {
			String newPassword = generateRandomPassword();
			userDto.setUserPwd(newPassword);
			userDao.changepwd(userDto);
			mailService.sendMail(userDto.getUserEmail(), "Your New Password", "Your new password is: " + newPassword);
		}
		return result;
	}

	private String generateRandomPassword() {
		// 랜덤 비밀번호 생성 로직 (예시)
		return UUID.randomUUID().toString().substring(0, 8);
	}

	@Override
	public UserDto userInfo(String userId) {
		return userDao.userInfo(userId);
	}

	@Override
	public void saveRefreshToken(String userId, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", refreshToken);
		userDao.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userId) throws Exception {
		return userDao.getRefreshToken(userId);
	}

	@Override
	public void deleRefreshToken(String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", null);
		userDao.deleteRefreshToken(map);
	}
}
