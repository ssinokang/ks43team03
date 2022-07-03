package ks43team03.service;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.Order;
import ks43team03.dto.ResponseGoods;
import ks43team03.dto.User;
import ks43team03.dto.type.OrderState;
import ks43team03.dto.type.PayType;
import ks43team03.exception.CustomException;
import ks43team03.exception.ErrorMessage;
import ks43team03.mapper.OrderMapper;
import ks43team03.mapper.UserMapper;

@Service
@Transactional
public class OrderService {

	private final OrderMapper orderMapper;
	private final UserMapper userMapper;
	
	
	private static final Logger log = LoggerFactory.getLogger(OrderService.class);

	
//	private final static String COLUMN_NAME = "order_cd";
//	private final static String TABLE_NAME = "goods_order";
	
	
	public OrderService(OrderMapper orderMapper,UserMapper userMapper) {
		this.orderMapper = orderMapper;
		this.userMapper = userMapper;
	}



	//주문 하기
	// toss 결제전에 주문내역을 넣는다.
	public Order addOrder(Order.Request req, ResponseGoods goods) {
		
		log.info("요청 데이터의 PayType : {}", req.getPayType());
		
		String paytype = req.getPayType().getName();
		String userId = req.getUserId();
		int orderPayPrice = req.getOrderPayPrice();
		
		
		
		
		if(!paytype.equals("카드") && !paytype.equals("가상계좌")) {
			
		}
		
		if(orderPayPrice < 0 || orderPayPrice > req.getOrderPrice()) {
			//exception
		}
		
		// 상품정보있는지 없는지 체크 한다.
		
		
		// 예외처리 
		try {
			User user = userMapper.getUserInfoById(userId);
			if(user == null) {
				// 예외 발생
			}else {
				// 있다면 포인트 내역 조회한다 
			}
		}catch(Exception e){
			
		}
		
		
		//String code = commonMapper.createNewCode(COLUMN_NAME, TABLE_NAME);
		Order order = createOrder(req, goods );
		orderMapper.addOrder(order);
		order.setPayName(paytype);
		return order;
	}

	
	private Order createOrder(Order.Request req, ResponseGoods goods) {
		return Order.builder()
				.facilityGoodsCd(goods.getFacilityGoods().getFacilityGoodsCd())
				.goodsCtgCd(goods.getFacilityGoods().getGoodsCtgCd())
				.orderPayPrice(req.getOrderPayPrice())
				.orderPrice(req.getOrderPrice())
				.userId(req.getUserId())
				.goodsName(req.getGoodsName())
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
		Order order = orderMapper.getOrderByCode(orderCd).orElseThrow(()-> new CustomException(ErrorMessage.ORDER_NOT_FOUND));
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
			throw new CustomException(ErrorMessage.ORDER_NOT_FOUND);
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
