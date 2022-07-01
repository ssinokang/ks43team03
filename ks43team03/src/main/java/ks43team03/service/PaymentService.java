package ks43team03.service;

import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ks43team03.mapper.OrderMapper;


@Service
@Transactional
public class PaymentService {
	

	
	private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
	
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
	
	//@Value("${success.call.back.url}")
	private String successCallBackUrl;
//	@Value("${fail.call.back.url}")
	private String failCallBackUrl;
	
	//private String tossOriginUrl;
	
	
	 // TEST_SECRTE_KEY 노출 x
	@Value("${test.secret.api.key}")
    private String SECRET_KEY;
	
	private final OrderMapper orderMapper;
	
	public PaymentService(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}
	
	
	
	
	
	public void addPay(Long amount, String orderId, String paymentKey) {

		
		orderMapper.getOrderByCode(orderId)
			.ifPresent(o ->{
				if(o.getOrderPayPrice() == amount) {
					String ok = "맞음";
				}else {
					String fail = "맞지않음";
				}
			});
		
		
	}
	
	public String confirmPayment(String paymentKey, String orderId, Long amount) throws RestClientException, JsonProcessingException {

	    RestTemplate restTemplate = new RestTemplate();
	    
	    ObjectMapper objectMapper = new ObjectMapper();
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((SECRET_KEY + ":").getBytes()));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        
        HashMap<String, String> payloadMap = new HashMap<>();
        
        payloadMap.put("orderId", orderId);
        payloadMap.put("amount", String.valueOf(amount));
        
        log.info("payloadmap 데이터 : {} ", payloadMap);
        
        
        ResponseEntity<String> response = restTemplate.postForEntity( 
        			"https://api.tosspayments.com/v1/payments/confirm" + paymentKey,
        			new HttpEntity<>(objectMapper.writeValueAsString(payloadMap), headers), 
        			String.class);
	
		return response.getBody();
	}
	
	
}
