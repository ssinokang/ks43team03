package ks43team03.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.User;

@Mapper
public interface UserMapper {
	
	// 회원 목록 조회
	public List<User> getUserList();
}
