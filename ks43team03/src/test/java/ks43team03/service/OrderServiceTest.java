package ks43team03.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
public class OrderServiceTest {

	@Autowired
	OrderService orderService;
	
	
	
	@Test
	@DisplayName("주문하기")
	void createOrder() {
//		Order o = orderService.getOrderByCode("order_5");
//		log.info("데이터가 들어오는가? {}", o.getGoodsCtgCd());
	}
	
	@Test
	@DisplayName("한명의 여러개의 주문리스트")
	void findOne() {
		
	}
	
	
	
}
