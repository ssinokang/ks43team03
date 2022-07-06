package ks43team03.service;


import java.time.LocalDate;
import java.time.LocalDateTime;
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
			throw new CustomException(ErrorMessage.NOT_EXITS_PAYMENT_TYPE_ERROR);
		}
		
		if(orderPayPrice < 0 || orderPayPrice > goods.getPrice()) {
			throw new CustomException(ErrorMessage.ORDER_ERROR_ORDER_PRICE);
		}
		
		// 상품정보있는지 없는지 체크 한다.
		
		// 예외처리
		User user;
		try {
			user = userMapper.getUserInfoById(userId);
			
			if(user == null) {
				throw new CustomException(ErrorMessage.USER_ERROR_USER_NOT_FOUND);
			}else {
				// 있다면 포인트 내역 조회한다 후 
				log.info("userName : {} , " , user.getUserName());
				
				//포인트 테이블 포인트 차감 하고 저장한다.
				
				//
			}
		}catch(Exception e){
			throw new CustomException(ErrorMessage.DATABASE_ERROR);
		}
		
		
		//String code = commonMapper.createNewCode(COLUMN_NAME, TABLE_NAME);
		Order order = createOrder(req, goods );
		orderMapper.addOrder(order);
		order.setPayName(paytype);
		order.setUserName(user.getUserName());
		return order;
	}

	
	private Order createOrder(Order.Request req, ResponseGoods goods) {
		return Order.builder()
				.facilityGoodsCd(goods.getFacilityGoods().getFacilityGoodsCd())
				.goodsCtgCd(goods.getFacilityGoods().getGoodsCtgCd())
				.orderPayPrice(req.getOrderPayPrice())
				.orderUUID((UUID.randomUUID().toString()+ LocalDate.now()).replaceAll("-", ""))
				.orderPrice(req.getOrderPrice())
				.userId(req.getUserId())
				.usedPoint(req.getUsedPoint())
				.goodsName(req.getGoodsName())
				.orderPayState(OrderState.ORDER.getCode())
				.payType(req.getPayType())
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
		Order order = orderMapper.getOrderByCode(orderCd).orElseThrow(()-> new CustomException(ErrorMessage.NOT_FOUND_ORDER));
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
			throw new CustomException(ErrorMessage.NOT_FOUND_ORDER);
		}
		
		
		return order;
	}

	
	
	/**
	 * orderUUID로 주문 완전 삭제 메소드 
	 */
	public void removeOrderByOrderUUID(String orderUUID){
		log.info("uuid 주문 정보 삭제 log");
		orderMapper.removeOrderByOrderUUID(orderUUID);
	}
	
	
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
