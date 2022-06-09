package ks43team03.service;

import org.springframework.stereotype.Service;

import ks43team03.dto.Order;
import ks43team03.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderMapper orderMapper;
	
	public String addOrder(String userId, String goodsCtgCd, int orderPayPrice) {
		Order order = Order.createOrder(userId, goodsCtgCd, orderPayPrice);
		orderMapper.addOrder(order);
		return order.getOrderCd();
	}
}
