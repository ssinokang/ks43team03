package ks43team03.dto;

import ks43team03.dto.type.OrderState;
import ks43team03.dto.type.PayType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Order {

	private String orderCd;
	private String userId;
	private String facilityGoodsCd; //예약신청코드 
	private String goodsCtgCd; //카테고리코드 이것도 
	private int orderPrice;//가격
	private int userPoint; //회원이보유한 포인트 point
	private int usedPoint;
	private int orderPayPrice; // 실결제 금액
	private String orderRegDate; //  주문일자
	private OrderState orderPayState; // 주문/결제상태 enum테스트
	
	
	//User객체를 + 테스트하기위해 만든 메소드
	
	
	
	@Getter
	@Setter
	public static class Request{
		private String userId;
		private String facilityGoodsCd; 
		private int orderPrice;
		private int userPoint;
		private int usedPoint;
		private int orderPayPrice;
		private PayType payType;
		
	}
}
