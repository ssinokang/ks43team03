package ks43team03.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FacilityGoods {
	
	/**
	 * 시설 내 상품카테고리 코드	시설 대표 코드	카테고리 코드	종목 코드	등록자 아이디	등록일자	상품 활성화 상태
	facility_goods_cd	facility_cd	goods_ctg_cd	sports_cd	user_id	facility_goods_reg_date	facility_goods_state
	 */
	
	private String facilityGoodsCd;
	private String facilityCd; // 시설대표코드
	private String goodsCtgCd; //카테고리코드
	private String sportsCd; //종목코드
	private String userId; //등록자아이디
	private String facilityRegDate; //등록날짜
	private String faclityGoodsState; //상품활성화상태

}
