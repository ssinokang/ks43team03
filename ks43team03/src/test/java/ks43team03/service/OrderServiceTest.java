package ks43team03.service;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ks43team03.dto.Order;
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
//	 	List<Order>list = orderService.getOrdersByUser("id002");
//	 	
//	 	log.info("리스트에 담긴 회원의 주문내역 데이터 : {}", list);
	}
	
	
	
	
	
}
