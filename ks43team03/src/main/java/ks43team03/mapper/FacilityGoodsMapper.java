package ks43team03.mapper;



import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.FacilityGoods;

@Mapper
public interface FacilityGoodsMapper {
	
	
	public void addFacilityGoods(FacilityGoods facilityGoods);
	
	
	// 상폼하나 조회 
	public FacilityGoods getFacilityGoodsCd(String facilityGoodsCd);
	
	
	public FacilityGoods getFacilityGoodsPassCd(String facilityGoodsCd);
	
}
