package ks43team03.service;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.Safety;
import ks43team03.mapper.SafetyMapper;



@Service
@Transactional
public class SafetyService {
	
	private final SafetyMapper safetyMapper;
	
	public SafetyService(SafetyMapper safetyMapper) {
		this.safetyMapper = safetyMapper;
	}

	/**
	 * 안전점검 등록
	 */
	
	public int addSafety(Safety safety) {
		
		int result = safetyMapper.addSafety(safety);
		
		return result;
	}

	/**
	 * 관리자의 안전점검 등록 시설 목록 조회(전체) 
	 */
	
	public Map<String, Object> getSafetyList(int currentPage) {
		// 몇개 행 노출
		int rowPerPage = 5;
		int startPageNum = 1;
		int endPageNum = 10;
		
		// 총 행의 갯수
		double rowCount = safetyMapper.getSafetyListCount();
		
		// 마지막페이지
		int lastPage = (int) Math.ceil(rowCount/rowPerPage); /*더블로 되어있어서 int*/
		
		// 페이징 처리
		int startRow = (currentPage - 1) * rowPerPage;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startRow", startRow);
		paramMap.put("rowPerPage", rowPerPage);
		
		List<Map<String, Object>> safetyList = safetyMapper.getSafetyList(paramMap);
		
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
		resultMap.put("safetyList", safetyList);
		resultMap.put("startPageNum", startPageNum);
		resultMap.put("endPageNum", endPageNum);
		
		return resultMap;
	}
	
	/**
	 * 시설 관리자의 안전점검 등록 목록 조회
	 */
	
	public List<Safety> getSafetyListById(String userId) {
		List<Safety> safetyListById = safetyMapper.getSafetyListById(userId);
		
		return safetyListById;
	}
	
	/**
	 * 안전점검 등록 정보 수정
	 */
	
	public void modifySafety(Safety safety) {
		safetyMapper.modifySafety(safety);
	}

	/**
	 * 안전점검 등록 정보 삭제
	 */
	public int removeSafety(Safety safety) {
		
		int result = safetyMapper.removeSafety(safety);
		
		return result;
		
	}

	/**
	 * 시설 관리자의 안전점검 등록 정보 삭제
	 */
	public int removeSafety2(Safety safety) {
		
		int result = safetyMapper.removeSafety2(safety);
		
		return result;
		
	}
	
	
	/**
	 * 관리자의 안전점검 결과 목록 조회(전체) 
	 */
	
	public Map<String, Object> getSafetyResultList(int currentPage) {
		// 몇개 행 노출
		int rowPerPage = 5;
		int startPageNum = 1;
		int endPageNum = 10;
		
		// 총 행의 갯수
		double rowCount = safetyMapper.getSafetyResultListCount();
		
		// 마지막페이지
		int lastPage = (int) Math.ceil(rowCount/rowPerPage); /*더블로 되어있어서 int*/
		
		// 페이징 처리
		int startRow = (currentPage - 1) * rowPerPage;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startRow", startRow);
		paramMap.put("rowPerPage", rowPerPage);
		
		List<Map<String, Object>> safetyResultList = safetyMapper.getSafetyResultList(paramMap);
		
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
		resultMap.put("safetyResultList", safetyResultList);
		resultMap.put("startPageNum", startPageNum);
		resultMap.put("endPageNum", endPageNum);
		
		return resultMap;
	}
	
	/**
	 * 시설 관리자의 안전점검 결과 목록 조회 
	 */
	
	public List<Safety> getSafetyResultListById(String userId) {
		List<Safety> getSafetyResultListById = safetyMapper.getSafetyResultListById(userId);
		
		return getSafetyResultListById;
	}	
	
	
	
	

	
}