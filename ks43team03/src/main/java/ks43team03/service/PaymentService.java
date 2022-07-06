package ks43team03.service;


import java.net.URI;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ks43team03.dto.Order;
import ks43team03.dto.Pay;
import ks43team03.dto.Payload;
import ks43team03.dto.PaymentCardResDto;
import ks43team03.dto.PaymentResDto;
import ks43team03.dto.PaymentVirtualResDto;
import ks43team03.dto.type.OrderState;
import ks43team03.dto.type.PayStatus;
import ks43team03.dto.type.PayType;
import ks43team03.exception.CustomException;
import ks43team03.exception.ErrorMessage;
import ks43team03.mapper.OrderMapper;
import ks43team03.mapper.PayMapper;


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
	
	
	
	 // TEST_SECRTE_KEY 노출 x
	@Value("${test.secret.api.key}")
    private String SECRET_KEY;
	
	private final OrderMapper orderMapper;
	private final PayMapper payMapper;
	
	public PaymentService(OrderMapper orderMapper,PayMapper payMapper) {
		this.orderMapper = orderMapper;
		this.payMapper = payMapper;
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
	
	
	/*
	 * 결제 후 토스 에서 결제 승인데이터를 받고 결제 테이블에 등록하는 메소드 
	 */
	public PaymentResDto confirmPayment(String paymentKey, String orderId, Long amount) throws JsonProcessingException {
		
		//주문 정보 확인 한다. 
		Order order = orderMapper.getOrderByOrderUUID(orderId)
				.orElseThrow(()-> new CustomException(ErrorMessage.NOT_FOUND_ORDER));
		
		
		
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
        
        log.info("headers data : {}", headers);
        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("orderId", orderId);
        payloadMap.put("amount", String.valueOf(amount));
        log.info("payloadMap data : {}", payloadMap);
        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(payloadMap), headers);
        
        PaymentResDto paymentResDto= restTemplate.postForEntity(
    			"https://api.tosspayments.com/v1/payments/" + paymentKey, 
    			request, 
    			PaymentResDto.class
    			).getBody();
    	
    	if(paymentResDto == null) {
    		log.info("결제 정보를 찾지 못함 ");
    		throw new CustomException(ErrorMessage.NOT_FOUND_PAYMENT);
    	}
        log.info("토스 응답데이터 :  {}", paymentResDto);
    	
        // 확인이 완료되면 최종 결제 정보를 결제테이블에 정보를 db에 쌓야야겠다.
        
        String status = paymentResDto.getStatus().getMessage();
        //카드또는 가상계좌 종류 이므로 if문으로 비교 후 카드로 결제했다면 주문완료 와 결제 완료 표시 
        // 여기서 CARD는 임의의 카드   테이블에 칼럼추가하거 아니면 응답데이터로 비교 함...
        
        
        
        if(PayStatus.DONE.equals( paymentResDto.getStatus())) {
    		order.setOrderPayState(OrderState.COMPLETE.getCode());
    	}else if(PayStatus.CANCELED.equals( paymentResDto.getStatus())) {
			order.setOrderPayState(OrderState.CANCEL.getCode());
		}
        
        Pay pay = toPay(paymentResDto, order);
        orderMapper.modifyOrder(order);
        
        payMapper.addPay(pay);
        
        if(pay.getPayCd() == null) {
        	throw new CustomException(ErrorMessage.DATABASE_ERROR);
        }
        
        String payMethod = paymentResDto.getMethod();
        
        if(PayType.CARD.equals(order.getPayType())) {
        	log.info("===========카드로 결제===========");
        	// 간편결제시 삼성폐이 = card  
        	if( paymentResDto.getCard() != null) {
        		//==  카드 정보가 있다면 ==// 
        		PaymentCardResDto res = paymentResDto.getCard();
        		pay.setPayCardType(res.getCardType());
        		pay.setPayCompany(res.getCompany());
        		pay.setPayCardNumber(res.getNumber());
        		payMapper.addPayCardInfo(pay);
        	}else if(paymentResDto.getVirtualAccount() != null) {
        		//==  가상계좌 정보가 있다면 ==// 
        		PaymentVirtualResDto res = paymentResDto.getVirtualAccount();
            	pay.setPayBank(res.getBank());
            	pay.setPayUserName(res.getCustomerName());
            	pay.setAccountNumber(res.getAccountNumber());
            	pay.setPayDueDate(res.getDueDate());
            	payMapper.addPayVirtualAccount(pay);
        	}
        }else if(PayType.VIRTUAL_ACCOUNT.equals(order.getPayType())) {
        	log.info("===========가상계좌로 결제===========");
        	PaymentVirtualResDto res = paymentResDto.getVirtualAccount();
        	pay.setPayBank(res.getBank());
        	pay.setPayUserName(res.getCustomerName());
        	pay.setAccountNumber(res.getAccountNumber());
        	pay.setPayDueDate(res.getDueDate());
        	payMapper.addPayVirtualAccount(pay);
        }
        // 계좌 입금으로 처리할시 결제상태와 주문상태 주문중 상태
        
        //최종적으로 if문 통과시 가상계좌 or 카드 결과 셋팅 ex 카드는 카드번호 카드회사  가상계좌는 계좌은행 계좌번호 예금주
        

        //통과후 토스 응답데이터 리턴
        return paymentResDto;
        
	}
	
	private Pay toPay(PaymentResDto payRes, Order order) {
		return Pay.builder()
				.orderCd(order.getOrderCd())
				.payMethod(payRes.getMethod())
				.payPrice(order.getOrderPrice())
				.orderUUID(payRes.getOrderId())
				.payTotalPrice(payRes.getTotalAmount())
				.userId(order.getUserId())
				.payUsedPoint(order.getUsedPoint())
				.paymentKey(payRes.getPaymentKey())
				.payState(payRes.getStatus().getMessage())
				.build();
				
	}
	
	/*
	 * 
	 */
	
	public void handleVirtualAccountCallback(Payload payload) {
		
		String status = payload.getStatus();
		String orderId = payload.getOrderId();
		String secret = payload.getSecret();
		
		// 주문정보 조회 
		Order order = orderMapper.getOrderByOrderUUID(orderId)
				.orElseThrow(()-> new CustomException(ErrorMessage.NOT_FOUND_ORDER));
		
		
		if("DONE".equals(status)) {
			// 입금이 확인되면 결제상태 변경
		}else if("CANCELED".equals(status)) {
			// 입금을 취소시 
		}
	}
	
	
	/*
	 * 결제 실패 메소드
	 */
	public void failPayment(String orderId) {
		
		// 주문정보를 조회후 주문 취소를 해야한다  주문 등록 -> 결제 하기 때문
		
		
		//order정보 조회
		Order order = orderMapper.getOrderByOrderUUID(orderId)
				.orElseThrow(()-> new CustomException(ErrorMessage.NOT_FOUND_ORDER));
		
		// order 상태 yn   y세팅하거나 삭제
		//삭제 또는 업데이트 처리 <<
		
	}
	
	/*
	 * 결제 후 데이터 조회 메소드
	 */
	public PaymentResDto findTossPaymentsbyOrderId(String orderId) {
		
		
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper objectMapper = new ObjectMapper();
		
		HttpHeaders headers = new HttpHeaders();
		
		String secretKey = SECRET_KEY + ":";
		
		String encodingAuth = new String(Base64.getEncoder().encode(secretKey.getBytes()));
		headers.setBasicAuth(encodingAuth);
//        headers.setContentType(MediaType.APPLICATION_JSON);
        
		//PaymentResDto paymentResDto= restTemplate.getForEntity("https://api.tosspayments.com/v1/payments/orders/" + orderId, PaymentResDto.class).getBody();
		HttpEntity<String> request = new HttpEntity<>(headers);
		log.info("request 데이터 : {}", request);
		
		URI uri = URI.create("https://api.tosspayments.com/v1/payments/orders/" + orderId);
		log.info("uri ============================ : {}",uri);
		
		PaymentResDto paymentResDto = restTemplate.exchange(
				  uri,
				  HttpMethod.GET,
				  request,
				  PaymentResDto.class
				).getBody();
		
		if(paymentResDto == null) {
			throw new CustomException(ErrorMessage.NOT_FOUND_PAYMENT);
		}
		
		log.info("조회 요청 데이터 : {}", paymentResDto);
		return paymentResDto;
	}
	
	
	public List<Pay> getAllPayment(){
		
		return null;
	}
	
	
	//회원이 조회한 결제정보
	public List<Pay> getAllPaymentByUserId(String userId){
		
		
		return null; 
	}
	
	
	
	
	
}
