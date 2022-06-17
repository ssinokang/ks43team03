package ks43team03.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.Area;
import ks43team03.dto.AreaCity;
import ks43team03.dto.AreaCityTown;
import ks43team03.dto.Facility;
import ks43team03.dto.FacilityUse;
import ks43team03.dto.MainCtg;

@Mapper
public interface FacilityMapper {

	
	
	//시설 상세 정보
	public Facility getFacilityInfoByCd(String facilityCd);
	
	//사용자가 시설 조회
	public List<Map<String, Object>> getFacilityList(Map<String, Object> paramMap);
	
	//시설 테이블 총 row(튜플) 수
	public int getFacilityCount();
	
	//시도 조회
	public List<Area> getAreaList();
	
	//시군구 조회
	public List<AreaCity> getAreaCityList();
	
	//읍면동 조회
	public List<AreaCityTown> getAreaCityTownList();
	
	//시설카테고리조회
	public List<MainCtg> getMainCtgList();
	
	//시설 용도 조회
	public List<FacilityUse> getFacilityUseList();
}
