package ks43team03.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.User;
import ks43team03.dto.UserLevel;

@Mapper
public interface UserMapper {
	
	//회원 등급 조회
	public List<UserLevel> getUserLevelList();
		
	//회원 정보 수정
	public int modifyUser(User user);
	
	//회원 아이디 중복 체크
	public boolean isIdCheck(String userId);
	
	// 회원 목록 조회
	public List<User> getUserList();
	
	//로그인 이력 조회(페이징)
	public List<Map<String, Object>> getLoginHistory(Map<String, Object> paramMap);
	
	//로그인 이력테이블 총 row(튜플) 수
	public int getLoginHistoryCount();
	
	//회원 상세정보 조회
	public User getUserInfoById(String userId);
}
