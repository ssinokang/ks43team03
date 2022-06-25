package ks43team03.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.FacilityGoods;
import ks43team03.dto.Pass;
import ks43team03.mapper.FacilityGoodsMapper;
import ks43team03.mapper.PassMapper;

@Service
@Transactional
public class PassService {

	private final FacilityGoodsMapper facilityGoodsMapper;
	private final PassMapper passMapper;
	
	public PassService(FacilityGoodsMapper facilityGoodsMapper,PassMapper passMapper) {
		this.facilityGoodsMapper = facilityGoodsMapper;
		this.passMapper = passMapper;
	}
	
	public String addPass(Pass pass) {
		
		FacilityGoods facilityGoods = pass.toFacilityGoods();
		
		facilityGoodsMapper.addFacilityGoods(facilityGoods);
		
		pass.setFacilityGoodsCd(facilityGoods.getFacilityGoodsCd());
		
		passMapper.addPass(pass);
		
		return facilityGoods.getFacilityGoodsCd();
	}
	
	
	
	
	
	
}
