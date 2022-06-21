package ks43team03.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.FacilityGoods;
import ks43team03.dto.ResponseGoods;
import ks43team03.mapper.FacilityGoodsMapper;

@Service
@Transactional
public class FacilityGoodsService {

	
	private static final Logger log = LoggerFactory.getLogger(FacilityGoodsService.class);

	private final FacilityGoodsMapper facilityGoodsMapper;
	
	
	public FacilityGoodsService(FacilityGoodsMapper facilityGoodsMapper) {
		this.facilityGoodsMapper = facilityGoodsMapper;
	}
	

	
	/**
	 * 
	 * @param facilityGoods  lesson pass stadium 레슨 이용권 대관
	 * @return 
	 */
	
	public String addGoodsCode(FacilityGoods facilityGoods) {
		log.info("addPass에서 받은 값 : {}", facilityGoods);
		facilityGoodsMapper.addFacilityGoods(facilityGoods);
		return facilityGoods.getFacilityGoodsCd();
		
	}
	
	// 상폼하나 조회 
	public ResponseGoods getFacilityGoodsCd(String facilityGoodsCd) {
		FacilityGoods facilityGoods = facilityGoodsMapper.getFacilityGoodsCd(facilityGoodsCd);
		String categoryCode = facilityGoods.getGoodsCtgCd();
		ResponseGoods responceGoods = getFacilityGoodsCd(facilityGoodsCd,categoryCode);

		return responceGoods;
	}
	
	
	
	/**
	 * 
	 * @param facilityGoods
	 * @return ResponceGoods ResponceGoods
	 * 
	 * 상품코드 조회 레슨, 이용권, 상품
	 */
	private ResponseGoods getFacilityGoodsCd(String failityGoodscd, String categoryCode) {
		int price = 0;
		FacilityGoods facilityGoods = null;

		if("lesson".equals(categoryCode)) {

		}else if("pass".equals(categoryCode)) {
			facilityGoods = facilityGoodsMapper.getFacilityGoodsPassCd(failityGoodscd);
			price = facilityGoods.getPass().getPassPrice();
		}else if("stadium".equals(categoryCode)) {
			
		}
		
		return ResponseGoods.builder()
				.facilityGoods(facilityGoods)
				.price(price)
				.build();
	}
	
	
	
	private ResponseGoods getFacilityGoodsPassCd(FacilityGoods facilityGoods) {
		return ResponseGoods.builder()
				.facilityGoods(facilityGoods)
				.price(facilityGoods.getPass().getPassPrice())
				.build();
	}
	
	private ResponseGoods getFacilityGoodsLessonCd(FacilityGoods facilityGoods) {
		return ResponseGoods.builder()
				.facilityGoods(facilityGoods)
				.price(facilityGoods.getLesson().getLessonPrice())
				.build();
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
