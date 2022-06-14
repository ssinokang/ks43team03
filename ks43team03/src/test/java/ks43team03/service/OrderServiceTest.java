package ks43team03.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
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
	@DisplayName("주문하기")
	void createOrder() {
		User u = createUser();
		String ctgCode = "lesson";
		int payPrice = 40000;
		
		String code = orderService.addOrder(u.getUserId(), ctgCode, payPrice);
		
		Order o = orderMapper.getOrderByCode(code);
		
		assertThat(code).isEqualTo(o.getOrderCd());
		assertThat(ctgCode).isEqualTo(o.getGoodsCtgCd());
		assertThat(payPrice).isEqualTo(o.getOrderPayPrice());
	}
	
	@Test
	@DisplayName("한명의 여러개의 주문리스트")
	void findOne() {
		String userId = "id001";
		String ctgCode = "lesson";
		int payPrice = 40000;
		
		orderService.addOrder(userId, ctgCode, payPrice);
		orderService.addOrder(userId, ctgCode, ++payPrice);
		orderService.addOrder(userId, ctgCode, ++payPrice);
			
		orderService.addOrder("id002", ctgCode, payPrice);
		List<Order> orders = orderMapper.getOrderByMember(userId);
		
		assertThat(userId).isEqualTo(orders.get(0).getUserId());
		assertThat(orders.size()).isEqualTo(3);
		assertThat(orders.get(1).getOrderPayPrice()).isEqualTo(40001);
	}
	
	
	private User createUser() {
		User u = new User();
		u.setUserId("hello");
		return u;
	}
}
