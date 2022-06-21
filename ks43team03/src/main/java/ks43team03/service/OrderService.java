package ks43team03.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import ks43team03.dto.Order;
import ks43team03.mapper.OrderMapper;

@Service
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

	
	
//	private void orderRollback(String userId) {
//		TransactionSynchronizationManager.registerSynchronization(
//				new TransactionSynchronizationAdapter() {
//				@Override
//				public void afterCompletion(int status) {
//					if(status == STATUS_ROLLED_BACK) {
//						
//					}
//				}
//			});
//	}
	
	
	//주문 취소 

	
}
