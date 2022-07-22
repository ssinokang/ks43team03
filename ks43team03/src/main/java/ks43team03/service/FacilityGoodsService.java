package ks43team03.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.FacilityGoods;
import ks43team03.dto.ResponseGoods;
import ks43team03.exception.CustomException;
import ks43team03.exception.ErrorMessage;
import ks43team03.mapper.FacilityGoodsMapper;
import ks43team03.mapper.FacilityMapper;

@Service
@Transactional
public class FacilityGoodsService {

	
	private static final Logger log = LoggerFactory.getLogger(FacilityGoodsService.class);

	private final FacilityGoodsMapper facilityGoodsMapper;
	private final FacilityMapper facilityMapper;
	
	public FacilityGoodsService(FacilityGoodsMapper facilityGoodsMapper,FacilityMapper facilityMapper) {
		this.facilityGoodsMapper = facilityGoodsMapper;
		this.facilityMapper = facilityMapper;
	}
	

	
	/**
	 * 
	 * @param facilityGoods  lesson pass stadium 레슨 이용권 대관
	 * @return 
	 */
	
	public String addGoodsCode(FacilityGoods facilityGoods) {
		log.info("addPass에서 받은 값 : {}", facilityGoods);
		// 시설조회
		facilityMapper.getFacilityInfoByCd(facilityGoods.getFacilityGoodsCd());
		
		//있다면 상품코드 생성 
		
		facilityGoodsMapper.addFacilityGoods(facilityGoods);
		return facilityGoods.getFacilityGoodsCd();
		
	}
	
	// 상폼하나 조회 
	public ResponseGoods getFacilityGoodsCd(String facilityGoodsCd, String goodsCtgCd ) {
		ResponseGoods responseGoods = null;

		switch (goodsCtgCd) {
		case "lesson":
			
			break;
		case "stadium":
			
			break;
		case "pass":
			FacilityGoods facilityGoods = facilityGoodsMapper.getFacilityGoodsPassCd(facilityGoodsCd);
			responseGoods = new ResponseGoods(
					facilityGoods, 
					facilityGoods.getPass()
					.getPassNm(),
					facilityGoods.getPass().getPassPrice()
					);
			break;
		default: 
			throw new CustomException(ErrorMessage.NOT_FOUND_GOODS);
		}
		
		return responseGoods;
		
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
