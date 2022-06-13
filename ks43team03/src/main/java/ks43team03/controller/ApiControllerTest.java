package ks43team03.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ks43team03.dto.FacilityGoods;

@RestController
public class ApiControllerTest {

	
	private static final Logger log = LoggerFactory.getLogger(ApiControllerTest.class);

	
	@PostMapping("/api/pass")
	public FacilityGoods addGoodsCode(@RequestBody FacilityGoods facilityGoods) {
		log.info("아이디 코드 data : {}",facilityGoods.getUserId());
		log.info("카테고리 코드 data : {}",facilityGoods.getGoodsCtgCd());
		log.info("종목코드 코드 data : {}",facilityGoods.getSportsCd());
		log.info("시설코드 코드 data : {}",facilityGoods.getFacilityCd());
		facilityGoods.setFacilityGoodsCd("goodsCode--1111");
		return facilityGoods;
	}
}
