package ks43team03.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseGoods {
	private FacilityGoods facilityGoods;
	private int price;
	
}
