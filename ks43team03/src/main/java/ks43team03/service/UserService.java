package ks43team03.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.User;
import ks43team03.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

/**
 * @author ksmart
 * @RequiredArgsConstructor - lombok 의존성 주입
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
	
	private final UserMapper userMapper;
	
	public List<User> getUserList(){
		
		List<User> userList = userMapper.getUserList();
		
		return userList;
	}
}
