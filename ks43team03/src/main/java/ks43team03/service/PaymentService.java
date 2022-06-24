package ks43team03.service;

import org.springframework.beans.factory.annotation.Value;

import ks43team03.dto.Payment;

public class PaymentService {
	
//	토스페이먼츠에서는 다음과 같은 변경 사항에 대해 하위 호환을 지원하는 것으로 판단합니다. 따라서 새로운 버전 릴리즈 없이 기존 API에 반영합니다.
//
//	새로운 API 엔드포인트 추가
//	API 요청에 새로운 선택 파라미터 추가
//	API 요청에서 사용하는 필수 파라미터를 선택 파라미터로 변경
//	API 응답에 새로운 필드 추가
//	새로운 ENUM 추가
//	에러 메시지 변경
//	새로운 에러 코드 추가
//	새로운 웹훅 이벤트 추가
	
	//@Value("${test.client.api.key}")
	private String testClientApiKey;
	
	//@Value("${test.secret.api.key}")
	private String testSecretApiKey;
	//@Value("${success.call.back.url}")
	private String successCallBackUrl;
//	@Value("${fail.call.back.url}")
	private String failCallBackUrl;
	
	//private String tossOriginUrl;
	
	
	public void createPay(Payment.PaymentReq req) {
		
		int price = req.getAmount();
	}
	
}
