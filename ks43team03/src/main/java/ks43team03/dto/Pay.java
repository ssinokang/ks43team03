package ks43team03.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pay {

	
	private String payCd;
	private String orderCd;
	private String userId;
	private int payUsedPoint; // 사용포인트
	private String payMethod; // 결제수단정보
	private String paymentKey; // 결제 정보 환불 조회시 필요
	private String orderUUID;  // 주문 UUID
	private int payPrice;  // 상품가격
	private String payTotalPrice; // 총결제금액
	private String payRegDate; // 결제일시
	private String payState; // 결제 상태 결제성공 결제 취소  결제진행중 
	private String payDelYN; // 결제 삭제상태
	private String payDelDate; // 결제 삭제일시 
	
	
	
	
	
	
}
