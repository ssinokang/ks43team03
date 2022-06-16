package ks43team03.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.FacilityGoods;
import ks43team03.dto.Pass;
import ks43team03.mapper.FacilityGoodsMapper;

@Service
@Transactional
public class FacilityGoodsService {

	
	private static final Logger log = LoggerFactory.getLogger(FacilityGoodsService.class);

	private final FacilityGoodsMapper facilityGoodsMapper;
	
	
	public FacilityGoodsService(FacilityGoodsMapper facilityGoodsMapper) {
		this.facilityGoodsMapper = facilityGoodsMapper;
	}
	

	public String addPass(Pass pass) {
		String goodsCode = addGoodsCode(pass);
		
		return goodsCode;
	}
	
	/**
	 * 
	 * @param facilityGoods  lesson pass stadium 레슨 이용권 대관
	 * @return 
	 */
	
	public String addGoodsCode(FacilityGoods facilityGoods) {
		log.info("addPass에서 받은 값 : {}", facilityGoods);
		facilityGoodsMapper.addFaciliryGoods(facilityGoods);
		return facilityGoods.getFacilityGoodsCd();
		
	}
	
	
	
	
	
	/*
	 * Admin 전체조회
	 */
	public List<FacilityGoods> getFacilityGoodsList(){
		return null;
	}
	
	
	/**
	 * 화면에 뿌릴 리스트
	 */
	public List<FacilityGoods> tempGoodsList(){
		return null;
	}
	
	
	
}
