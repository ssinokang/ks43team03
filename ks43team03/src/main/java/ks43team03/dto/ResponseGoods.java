package ks43team03.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseGoods {
	private FacilityGoods facilityGoods;
	
	private String goodsName;
	private int price;
	
}
