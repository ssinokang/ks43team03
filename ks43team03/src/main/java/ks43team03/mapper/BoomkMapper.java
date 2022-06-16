package ks43team03.mapper;


import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.Boomk;

@Mapper
public interface BoomkMapper {
	
	/*
	 * //즐겨찾기 취소 public int removeBoomkById(String userId);
	 */
	
	//즐겨찾기 조회
	public Boomk getBoomkInfoById(String userId);
	
}
