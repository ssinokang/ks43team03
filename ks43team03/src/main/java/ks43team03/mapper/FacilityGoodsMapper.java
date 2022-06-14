package ks43team03.mapper;


import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.FacilityGoods;

@Mapper
public interface FacilityGoodsMapper {
	
	public FacilityGoods addFaciliryGoods(FacilityGoods facilityGoods);
}
