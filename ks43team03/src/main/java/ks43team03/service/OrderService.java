package ks43team03.service;


import ks43team03.dto.Order;
import ks43team03.mapper.OrderMapper;


public class OrderService {

	private final OrderMapper orderMapper;
	
	
	
	public OrderService(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}



	//주문 하기
	public String addOrder(String userId, String goodsCtgCd, int orderPayPrice) {
		Order order = Order.createOrder(userId, goodsCtgCd, orderPayPrice);
		orderMapper.addOrder(order);
		return order.getOrderCd();
	}

	
	
	//주문 취소 

	
}
