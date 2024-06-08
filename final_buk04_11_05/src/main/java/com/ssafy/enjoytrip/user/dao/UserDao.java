package com.ssafy.enjoytrip.user.dao;

import com.ssafy.enjoytrip.user.model.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.Map;

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
	void modifyReplyImg(UserDto userDto);
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	Object getRefreshToken(String userid) throws SQLException;
	void deleteRefreshToken(Map<String, String> map) throws SQLException;
}
