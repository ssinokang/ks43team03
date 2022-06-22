package ks43team03.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


import ks43team03.dto.Facility;


@Mapper
public interface FacilityMapper {

	
	
	//시설 상세 정보
	public Facility getFacilityInfoByCd(String facilityCd);
	
	//사용자가 시설 조회
	public List<Map<String, Object>> getFacilityList(Map<String, Object> paramMap);
	
	//시설 테이블 총 row(튜플) 수
	public int getFacilityCount();
	
}
