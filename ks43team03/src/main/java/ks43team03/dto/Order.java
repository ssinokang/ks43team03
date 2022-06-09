package ks43team03.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {

	private String orderCd;
	private String userId;
	private String facilityGoodsCode; //예약신청코드 
	private String goodsCtgCd; //카테고리코드 이것도 
	private int orderPrice;//가격
	private int userPoint; //회원이보유한 포인트 point
	private int usedPoint;
	private int orderPayPrice; // 실결제 금액
	private String orderRegDate; // rocalDateTime or Time 주문일자
	private String orderPayState; // 주문/결제상태 enum테스트
	
	
	//User객체를 + 테스트하기위해 만든 메소드
	public static Order createOrder(String userId, String goodsCtgCd, int orderPayPrice) {
		Order order = new Order();
		order.setUserId(userId);
		order.setGoodsCtgCd(goodsCtgCd);
		order.setOrderPayPrice(orderPayPrice);
		return order;
	}
}
