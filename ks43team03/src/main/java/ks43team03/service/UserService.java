package ks43team03.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.User;
import ks43team03.dto.UserLevel;
import ks43team03.mapper.UserMapper;

/**
 * @author ksmart
 * @RequiredArgsConstructor - lombok 의존성 주입
 */
@Service
@Transactional
public class UserService {

	private final UserMapper userMapper;
	
	public UserService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	
	/**
	 * 회원정보 변경
	 * @param user
	 * @return
	 */
	public int modifyUser(User user) {
		
		int result = userMapper.modifyUser(user);
		
		return result;
	}
	
	/**
	 * 아이디 중복 체크
	 * @param userId
	 * @return
	 */
	public boolean isIdCheck(String userId) {
		
		boolean result = userMapper.isIdCheck(userId);
		
		return result;
	}
	
	/**
	 * 회원 권한 목록
	 * @return
	 */
	public List<UserLevel> getMemberLevelList(){
		
		List<UserLevel> userLevelList = userMapper.getUserLevelList();
		
		return userLevelList;
	}
	
	/**
	 * 회원 목록
	 * @return
	 */
	public List<User> getUserList(){
		
		List<User> userList = userMapper.getUserList();
		
		if(userList != null) {
			for(User user : userList) {
				String userLevel = user.getUserLevel();
				//equals 는 String에서 쓸 수 있으므로 null 인지 확인
				if(userLevel != null) {
					if("1".equals(userLevel)) {
						user.setUserLevel("관리자");
					}else if("2".equals(userLevel)) {
						user.setUserLevel("공공및사설시설운영자");
					}else if("3".equals(userLevel)) {
						user.setUserLevel("시설 운영자 대리인");
					}else if("4".equals(userLevel)) {
						user.setUserLevel("트레이너");
					}else {
						user.setUserLevel("일반회원");
					}
				}
			}
		}
		
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
