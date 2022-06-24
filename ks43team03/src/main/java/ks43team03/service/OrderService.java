package ks43team03.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.Order;
import ks43team03.dto.ResponseGoods;
import ks43team03.dto.type.OrderState;
import ks43team03.exception.NotFoundOrderException;
import ks43team03.mapper.CommonMapper;
import ks43team03.mapper.OrderMapper;

@Service
@Transactional
public class OrderService {

	private final OrderMapper orderMapper;
	private final CommonMapper commonMapper;
	
	private final static String COLUMN_NAME = "order_cd";
	private final static String TABLE_NAME = "goods_order";
	
	
	public OrderService(OrderMapper orderMapper,CommonMapper commonMapper) {
		this.orderMapper = orderMapper;
		this.commonMapper = commonMapper;
	}



	//주문 하기
	public Order addOrder(Order.Request req, ResponseGoods goods) {
		
		String code = commonMapper.createNewCode(COLUMN_NAME, TABLE_NAME);
		Order order = createOrder(req, goods, code);
		orderMapper.addOrder(order);
		return order;
	}

	
	private Order createOrder(Order.Request req, ResponseGoods goods,String code) {
		return Order.builder()
				.orderCd(code)
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
		Order order = orderMapper.getOrderByCode(orderCd).orElseThrow(()->{
			throw new NotFoundOrderException("주문한 내역이 없습니다.");
		});
		return order;
	}
	
	//== 회원이 주문한 내역 ==//
	@Transactional(readOnly = true)
	public List<Order> getOrdersByUser(String userId){
		List<Order> orderList = orderMapper.getOrdersByUser(userId);
		return orderList;
	}
	
	
	public Order getOrderDetail(String orderCd) {

		Order order = getOrderByCode(orderCd);
		String goodsCtgCd = order.getGoodsCtgCd();
		switch (goodsCtgCd) {
		case "lesson":
			order = orderMapper.getOrderDetailWithLesson(orderCd);
			break;
		case "stadium":
			order = orderMapper.getOrderDetailWithStadium(orderCd);
			break;
		case "pass":
			order = orderMapper.getOrderDetailWithPass(orderCd);
			break;
		default: 
			throw new NotFoundOrderException("주문한 내역이 없습니다.");
		}
		
		
		return order;
	}
	
	
	//주문 취소 

	
	
	/**
	 * 관리자 주문리스트 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Order> getOrderAll(){
		List<Order> orderList =  orderMapper.getOrderAll();
		return orderList;
	}
	
}
