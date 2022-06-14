package ks43team03.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public List<User> getUserList() {

		List<User> userList = userMapper.getUserList();

		return userList;
	}

	/**
	 * 로그인 이력 조회 (페이징 처리)
	 */
	public Map<String, Object> getLoginHistory(int currentPage){
		// 몇개 행 노출
		int rowPerPage = 5;
		int startPageNum = 1;
		int endPageNum = 10;
		
		// 총 행의 갯수
		double rowCount = userMapper.getLoginHistoryCount();
		
		// 마지막페이지
		int lastPage = (int) Math.ceil(rowCount/rowPerPage); /*더블로 되어있어서 int*/
		
		// 페이징 처리
		int startRow = (currentPage - 1) * rowPerPage;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startRow", startRow);
		paramMap.put("rowPerPage", rowPerPage);
		
		List<Map<String, Object>> loginHistoryList = userMapper.getLoginHistory(paramMap);
		
		//동적 페이지번호 - currentPage가 뭐뭐 보다 클때 고정되어있던 페이지 번호가 움직인다.
		if(currentPage > 6) {
			startPageNum = currentPage - 5;
			endPageNum = currentPage + 4;
			
			if(endPageNum >= lastPage) {
				startPageNum = lastPage - 9;
				endPageNum = lastPage;
			}
		}
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lastPage", lastPage);
		resultMap.put("loginHistoryList", loginHistoryList);
		resultMap.put("startPageNum", startPageNum);
		resultMap.put("endPageNum", endPageNum);
		
		return resultMap;
	}
				 

	/**
	 * 회원 상세정보
	 */
	public User getUserInfoById(String userId) {

		User user = userMapper.getUserInfoById(userId);

		return user;
	}
}
