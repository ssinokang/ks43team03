package ks43team03.service;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import ks43team03.dto.Order;
import ks43team03.dto.User;
import ks43team03.mapper.OrderMapperImlTest;

public class OrderServiceTest {

	private OrderMapperImlTest orderMapper = new OrderMapperImlTest();
	
	private OrderService orderService = new OrderService(orderMapper);
	
	@AfterEach
	public void clear() {
		orderMapper.clear();
	}
	
	@Test
	void createOrder() {
		User u = new User();
		u.setUserId("hello");
		String ctgCode = "lesson";
		int payPrice = 40000;
		
		String code = orderService.addOrder(u.getUserId(), ctgCode, payPrice);
		
		Order o = orderMapper.getOrderByCode(code);
		
		assertThat(code).isEqualTo(o.getOrderCd());
		assertThat(ctgCode).isEqualTo(o.getGoodsCtgCd());
		assertThat(payPrice).isEqualTo(o.getOrderPayPrice());
	}
}
