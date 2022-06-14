package ks43team03.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Pass extends FacilityGoods{
	
	private String passCd;
	private String facilityGoodsCode;
	private String passNm;
	private int passUnit;
	private int passPrice;
	private String passState;
	private int passEndDate;
	
	
	
	
}
