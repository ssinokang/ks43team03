package ks43team03.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.Safety;



@Mapper
public interface SafetyMapper {
	
	
	//관리자의 안전점검 등록 목록 조회
	
	//시설 관리자의 안전점검 등록 목록 조회(페이징)					              
	public List<Map<String, Object>> getSafetyListById(Map<String, Object> paramMap);	
	
	//시설 관리자의 안전점검 등록
	public int addSafety(Safety safety);


	
	
	//안전점검 등록 시설 목록 조회(전체)(페이징)
	public List<Map<String, Object>> getSafetyList(Map<String, Object> paramMap);	
	
	//안전점검 등록 시설 목록 총 row(튜플) 수
	public int getSafetyListCount();	
	
	
	
	//안전점검 등록 삭제
	
	//안전점검 수정
	public int modifySafety(Safety safety);
	
	//안전점검 결과 목록 조회
	
	//시설 관리자의 안전점검 결과 목록 조회 


	
	
	
	
}
