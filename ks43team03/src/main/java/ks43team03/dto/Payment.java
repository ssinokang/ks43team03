package ks43team03.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Payment {

	
	private PayType payType;
	
	public enum PayType{
		CARD, KAKAO, NAVER, DEPOSIT
	}
}
