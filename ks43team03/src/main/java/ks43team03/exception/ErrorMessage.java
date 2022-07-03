package ks43team03.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
	
	ORDER_NOT_FOUND("해당 주문내역이 없습니다."),
	GOODS_NOT_FOUND("해당 상품이 없습니다."),
	GOODS_ERROR_PASS_NOT_FOUND("조회하신 이용권정보가 없습니다.");
	
	private final String message;
}
