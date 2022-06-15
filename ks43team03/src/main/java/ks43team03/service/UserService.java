package ks43team03.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	/**
	 * 시설 내 회원 목록
	 * @return
	 */
	public Map<String, Object> getFacilityUserList(int currentPage, String facilityCd){
		
		int rowPerPage = 10;
		
		double rowCount = userMapper.getFacilityUserCount();
		
		int lastPage = (int)Math.ceil(rowCount/rowPerPage);
		
		int startRow = (currentPage - 1)*rowPerPage;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("startRow", startRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("facilityCd", facilityCd);
		
		int startPageNum = 1;
		int endPageNum = 10;
		
		if(lastPage > 10) {
			if(currentPage >= 6) {
				startPageNum = currentPage - 4;
				endPageNum = currentPage + 5;
				
				if(endPageNum >= lastPage) {
					startPageNum = lastPage - 9;
					endPageNum = lastPage;
				}
			}
		}else {
			endPageNum = lastPage;
		}
		
		log.info("paramMap : {}", paramMap);
		
		List<Map<String, Object>> facilityUserList = userMapper.getFacilityUserList(paramMap);
		
		if(facilityUserList != null) {
			for(Map<String, Object> userMap : facilityUserList) {
				String facilityUserLevel = userMap.get("facilityUserLevel").toString();
				String facilityApproveState = userMap.get("facilityApproveState").toString();
				
				//equals 는 String에서 쓸 수 있으므로 null 인지 확인
				if(facilityUserLevel != null && facilityApproveState != null) {
					
					if("3".equals(facilityUserLevel)) {
						userMap.put("facilityUserLevel", "시설 운영자 대리인");
					}else if("4".equals(facilityUserLevel)) {
						userMap.put("facilityUserLevel", "트레이너");
					}else {
						userMap.put("facilityUserLevel", "일반회원");
					}
					
					if("Y".equals(facilityApproveState)) {
						userMap.put("facilityApproveState", "승인");
					}else {
						userMap.put("facilityApproveState", "미승인");
					}
				}
			}
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lastPage", 			lastPage);
		resultMap.put("facilityUserList",	facilityUserList);
		resultMap.put("startPageNum",		startPageNum);
		resultMap.put("endPageNum",			endPageNum);
		
		return resultMap;
		
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
	 * 회원 전체 목록
	 * @return
	 */
	public Map<String, Object> getUserList(int currentPage){
		
		int rowPerPage = 10;
		
		double rowCount = userMapper.getUserCount();
		
		int lastPage = (int)Math.ceil(rowCount/rowPerPage);
		
		int startRow = (currentPage - 1)*rowPerPage;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("startRow", startRow);
		paramMap.put("rowPerPage", rowPerPage);
		
		int startPageNum = 1;
		int endPageNum = 10;
		
		if(lastPage > 10) {
			if(currentPage >= 6) {
				startPageNum = currentPage - 4;
				endPageNum = currentPage + 5;
				
				if(endPageNum >= lastPage) {
					startPageNum = lastPage - 9;
					endPageNum = lastPage;
				}
			}
		}else {
			endPageNum = lastPage;
		}
		
		log.info("paramMap : {}", paramMap);
		
		List<Map<String, Object>> userList = userMapper.getUserList(paramMap);
		
		if(userList != null) {
			for(Map<String, Object> userMap : userList) {
				String userLevel = userMap.get("userLevel").toString();
				//equals 는 String에서 쓸 수 있으므로 null 인지 확인
				if(userLevel != null) {
					if("1".equals(userLevel)) {
						userMap.put("userLevel", "관리자");
					}else if("2".equals(userLevel)) {
						userMap.put("userLevel", "공공및사설시설운영자");
					}else if("3".equals(userLevel)) {
						userMap.put("userLevel", "시설 운영자 대리인");
					}else if("4".equals(userLevel)) {
						userMap.put("userLevel", "트레이너");
					}else {
						userMap.put("userLevel", "일반회원");
					}
				}
			}
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lastPage", 		lastPage);
		resultMap.put("userList",		userList);
		resultMap.put("startPageNum",	startPageNum);
		resultMap.put("endPageNum",		endPageNum);
		
		return resultMap;
		
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
