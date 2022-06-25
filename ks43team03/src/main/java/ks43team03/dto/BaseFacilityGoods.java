package ks43team03.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseFacilityGoods<T> {

	// 레슨 이용권 스타디움 data
	private T data;
	
	private FacilityGoods facilityGoods;
	
}
