package ks43team03.service;


import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ks43team03.dto.Order;
import ks43team03.dto.Payload;
import ks43team03.dto.Payment;
import ks43team03.dto.PaymentResDto;
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
	
	
	
	
	
	public void order(Long amount, String orderId) {

		orderMapper.getOrderByCode(orderId)
			.ifPresent(o ->{
				if(o.getOrderPayPrice() == amount) {
					log.info("맞음0");
				}else {
					log.info("맞지않음");
				}
			});
		
		
	}
	
	public PaymentResDto confirmPayment(String paymentKey, String orderId, Long amount) throws JsonProcessingException {
		
		//주문 정보 확인 한다. 
		Order order = orderMapper.getOrderByCode(orderId)
				.orElseThrow(() -> new IllegalStateException("주문정보를 찾지 못하였습니다."));
		
		
		// 대관과 레슨의 예약 정보를 확인 확인해야겠네
		
		
		// 주문정보에 결제 타입을 넣어야겠군 
		
		
		// toss 결제 최종 확인을한다.
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper objectMapper = new ObjectMapper();
		
		HttpHeaders headers = new HttpHeaders();
		
		String secretKey = SECRET_KEY + ":";
		String encodingAuth = new String(Base64.getEncoder().encode(secretKey.getBytes()));
		headers.setBasicAuth(encodingAuth);
//        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((SECRET_KEY + ":").getBytes()));
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("orderId", orderId);
        payloadMap.put("amount", String.valueOf(amount));
        log.info("payloadMap data : {}", payloadMap);
        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(payloadMap), headers);
        
        PaymentResDto paymentResDto = null;
        try {
        	paymentResDto= restTemplate.postForEntity(
        			"https://api.tosspayments.com/v1/payments/" + paymentKey, 
        			request, 
        			PaymentResDto.class
        			).getBody();
        	
        	if(paymentResDto == null) {
        		log.info("결제 정보를 찾지 못함 ");
        	}
        }catch (Exception e) {
        	e.getMessage();
        }
        
        // 확인이 완료되면 최종 결제 정보를 결제테이블에 정보를 db에 쌓야야겠다.
        
        
        
        return paymentResDto;
        
	}
	
	
	public void handleVirtualAccountCallback(Payload payload) {
		
		String status = payload.getStatus();
		String orderId = payload.getOrderId();
		String secret = payload.getSecret();
		
		// 주문정보 조회 
		orderMapper.getOrderByCode(orderId)
			.orElseThrow(() -> new IllegalStateException("주문정보를 찾지 못하였습니다."));
		
		// 엥 ?? 여기는 결제가 끝나고 정보를 입력하는곳인가
		
		if("DONE".equals(status)) {
			// 입금이 확인되면 결제상태 변경
		}else if("CANCELED".equals(status)) {
			// 입금을 취소시 
		}
	}
	
	public void failPayment(String orderId) {
		
		// 주문정보를 조회후 주문 취소를 해야한다  주문 등록 -> 결제 하기 때문
		
		
	}
	
	
	
	public List<Payment> getAllPayment(){
		
		return null;
	}
	
	
	//회원이 조회한 결제정보
	public List<Payment> getAllPaymentByUserId(String userId){
		
		
		return null; 
	}
	
	
	
}
