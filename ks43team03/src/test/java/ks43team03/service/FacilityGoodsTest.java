package ks43team03.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ks43team03.dto.FacilityGoods;

@SpringBootTest
public class FacilityGoodsTest {

	@Autowired
	private FacilityGoodsService fGoodserv;
	
	
	@Test
	void addFacilityGoods() {
		FacilityGoods fg = new FacilityGoods();
		
		fg.setFacilityCd("ss_35011600_04");
		fg.setGoodsCtgCd("lesson");
		fg.setSportsCd("sp04");
		fg.setUserId("id002");
		
		fGoodserv.addGoodsCode(fg);
		
		System.out.println(fg.getFacilityGoodsCd());
	}
}