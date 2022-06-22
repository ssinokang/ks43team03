package ks43team03.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import ks43team03.dto.Order;
import ks43team03.dto.ResponseGoods;
import ks43team03.dto.type.OrderState;
import ks43team03.mapper.OrderMapper;

@Service
public class OrderService {

	private final OrderMapper orderMapper;
	
	
	
	public OrderService(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}



	//주문 하기
	public Order addOrder(Order.Request req, ResponseGoods goods) {
		
		
		Order o = createOrder(req, goods);
		orderMapper.addOrder(o);
		return o;
	}

	
	private Order createOrder(Order.Request req, ResponseGoods goods) {
		return Order.builder()
					.facilityGoodsCd(goods.getFacilityGoods().getFacilityGoodsCd())
					.goodsCtgCd(goods.getFacilityGoods().getGoodsCtgCd())
					.orderPayPrice(req.getOrderPayPrice())
					.orderPrice(req.getOrderPrice())
					.userId(req.getUserId())
					.orderPayState(OrderState.ORDER)
					.build();
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
