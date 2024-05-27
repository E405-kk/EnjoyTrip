package com.ssafy.enjoytrip.user.dao;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.user.model.UserDto;

@Mapper
public interface UserDao {
	int idCheck(String userId);
	int register(UserDto userDto);
	UserDto login(UserDto userDto);
	int modify(UserDto userDto);
	int remove(String userId);
	int userBoardRemove(String userId);
	int findpwd(UserDto userDto);
	int changepwd(UserDto userDto);
	UserDto userInfo(String userId);
	void modifyBoardImg(UserDto userDto);
	void modifyCommentImg(UserDto userDto);
	void modifyHotplaceImg(UserDto userDto);
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	Object getRefreshToken(String userid) throws SQLException;
	void deleteRefreshToken(Map<String, String> map) throws SQLException;
}
