package ks43team03.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class FacilityGoodsTest {
	
	
	@Test
	void 데이터전송_테스트() {
		Pass pass = new Pass();
		pass.setUserId("id001");
		pass.setGoodsCtgCd("lesson");
		pass.setSportsCd("soccer");
		pass.setFacilityCd("어쩌다한번씩체육관");
		
		FacilityGoods f = pass;
		
		Assertions.assertThat(pass.getUserId()).isEqualTo(f.getUserId());
		Assertions.assertThat(pass.getGoodsCtgCd()).isEqualTo(f.getGoodsCtgCd());
		Assertions.assertThat(pass.getSportsCd()).isEqualTo(f.getSportsCd());
	}
}
