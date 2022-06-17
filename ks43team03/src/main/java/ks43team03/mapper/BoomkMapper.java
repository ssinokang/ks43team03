package ks43team03.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.Boomk;
import ks43team03.dto.Facility;

@Mapper
public interface BoomkMapper {
	
	/*
	 * //즐겨찾기 취소 public int removeBoomkById(String userId);
	 */
	
	//즐겨찾기 조회	
	public List<Boomk> getBoomkList(String userId);  
	
	//즐겨찾기 취소
	public int modifyBoomk(Boomk boomk);	
	
	//시설 조회
	public List<Facility> getFacilityList();


	
	
	
	
}
