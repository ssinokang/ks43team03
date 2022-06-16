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
	/* public Boomk getBoomkList(String userId); */
	/* public Boomk getBoomkInfoById(String userId); */
	
	public List<Boomk> getBoomkList(String userId);  
	
	//시설 조회
	public List<Facility> getFacilityList();
	
	
	
	
}
