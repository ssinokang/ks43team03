package ks43team03.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ks43team03.dto.Pass;

@SpringBootTest
public class FacilityGoodsTest {

	@Autowired
	FacilityGoodsService facilityGoodsService;
	
	@Test
	@DisplayName("코드 생성과 저장을한다.")
	void save() {
		Pass pass = new Pass();
		pass.setFacilityCd("ss_35011740_01");
		pass.setUserId("id002");
		pass.setGoodsCtgCd("lesson");
		pass.setSportsCd("sp03");
		
		String code = facilityGoodsService.addPass(pass);
		System.out.println(code);
	}
	
}
