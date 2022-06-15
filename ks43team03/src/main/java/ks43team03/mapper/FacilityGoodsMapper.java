package ks43team03.mapper;


import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.FacilityGoods;

@Mapper
public interface FacilityGoodsMapper {
	
	public void addFaciliryGoods(FacilityGoods facilityGoods);

	public void getFacilityGoods(String lessonCd);
	
}
