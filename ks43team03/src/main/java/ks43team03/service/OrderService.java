package ks43team03.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.Order;
import ks43team03.dto.ResponseGoods;
import ks43team03.dto.type.OrderState;
import ks43team03.mapper.OrderMapper;

@Service
@Transactional
public class OrderService {

	private final OrderMapper orderMapper;
	
	
	
	public OrderService(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}



	//주문 하기
	public Order addOrder(Order.Request req, ResponseGoods goods) {
		
		
		Order order = createOrder(req, goods);
		orderMapper.addOrder(order);
		return order;
	}

	
	private Order createOrder(Order.Request req, ResponseGoods goods) {
		return Order.builder()
				.facilityGoodsCd(goods.getFacilityGoods().getFacilityGoodsCd())
				.goodsCtgCd(goods.getFacilityGoods().getGoodsCtgCd())
				.orderPayPrice(req.getOrderPayPrice())
				.orderPrice(req.getOrderPrice())
				.userId(req.getUserId())
				.orderPayState(OrderState.ORDER.getCode())
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
	
	
	/**
	 * 
	 * @return
	 */
	
	//== 주문 상세 조회 ==//
	public Order getOrderByCode(String orderCd) {
		
		
		return orderMapper.getOrderByCode(orderCd);
	}
	
	
	//주문 취소 

	
	
	/**
	 * 관리자 주문리스트 
	 * @return
	 */
	public List<Order> getOrderAll(){
		List<Order> orderList =  orderMapper.getOrderAll();
		return orderList;
	}
	
}
