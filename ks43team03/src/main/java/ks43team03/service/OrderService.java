package ks43team03.service;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.Order;
import ks43team03.dto.PageDto;
import ks43team03.dto.ResponseGoods;
import ks43team03.dto.User;
import ks43team03.dto.type.GoodsType;
import ks43team03.dto.type.OrderState;
import ks43team03.exception.CustomException;
import static ks43team03.exception.ErrorMessage.*;
import ks43team03.mapper.OrderMapper;
import ks43team03.mapper.UserMapper;

@Service
@Transactional
public class OrderService {

	private final OrderMapper orderMapper;
	private final UserMapper userMapper;
	
	
	private static final Logger log = LoggerFactory.getLogger(OrderService.class);

	
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
			throw new CustomException(NOT_EXITS_PAYMENT_TYPE_ERROR);
		}
		
		if(orderPayPrice < 0 || orderPayPrice > goods.getPrice()) {
			throw new CustomException(ORDER_ERROR_ORDER_PRICE);
		}
		
		if(Strings.isEmpty(userId)) {
			throw new CustomException(IS_EMPTY_USER);
		}
		// 예외처리
		User user;
			
		try {
			user = userMapper.getUserInfoById(userId);
			
			if(user == null) {
				throw new CustomException(USER_ERROR_USER_NOT_FOUND);
			}else {
				log.info("userName : {} , " , user.getUserName());
				
				//포인트 테이블 포인트 차감 하고 저장한다.
				
				//
			}
		}catch(Exception e){
			throw new CustomException(DATABASE_ERROR);
		}
		
		//String code = commonMapper.createNewCode(COLUMN_NAME, TABLE_NAME);
		Order order = createOrder(req, goods );
		orderMapper.addOrder(order);
		order.setPayName(paytype);
		order.setUserName(user.getUserName());
		return order;
	}

	/**
	 * request데이터 order로 변환 메소드 
	 */
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
	
	
	/**
	 * 
	 * @return
	 */
	
	//== 주문 상세 조회 ==//
	public Order getOrderByCode(String orderCd) {
		Order order = orderMapper.getOrderByCode(orderCd).orElseThrow(()-> new CustomException(NOT_FOUND_ORDER));
		return order;
	}
	
	//== 회원이 주문한 내역 ==//
	/**
	 *  페이징 처리 개선이 필요하다.
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> getOrdersByUser(int currentPage, String userId){
		
		
		double rowCount = orderMapper.getOrderByUserCount(userId);

		PageDto page = new PageDto(rowCount, currentPage, 6,userId);
		List<Order> orderList = orderMapper.getOrdersByUser(page);
		
		log.info("db 조회 데이터 : {}", orderList);
		Map<String, Object> resultMap = new HashMap<>();
		log.info("dto에담긴값 : {}", page);
//		resultMap.put("lastPage", lastPage);
//		resultMap.put("orderList", orderList);
//		resultMap.put("startPage", startPage);
//		resultMap.put("endPage", endPage);

		resultMap.put("lastPage", page.getLastPage());
		resultMap.put("orderList", orderList);
		resultMap.put("startPage", page.getStartPage());
		resultMap.put("endPage", page.getEndPage());
		return resultMap;
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
			throw new CustomException(NOT_FOUND_ORDER);
		}
		return order;
	}
	
	/**
	 * orderUUID로 주문 완전 삭제 메소드 
	 */
	public void removeOrderByOrderUUID(String orderUUID){
		try {
			log.info("uuid 주문 정보 삭제 log");
			int result = orderMapper.removeOrderByOrderUUID(orderUUID);
			if(result == 0) {
				throw new CustomException(ORDER_DELETE_ERROR);
			}
		}catch (Exception e) {
			throw new CustomException(DATABASE_ERROR);
		}
	}
	
	
	public List<Order> orderInfomationByCategory(GoodsType category, String userId) {
		log.info("category data : {}", category.getCode());
		List<Order> orderList = orderMapper.orderInfomationByCategory(category.getCode(), userId);
		return orderList;
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
	
	/**
	 * 시실의 주문리스트
	 */
	public List<Order> getOrderInfoForFacility(String facilityCd){
		List<Order> orderList = orderMapper.getOrderInfoForFacility(facilityCd);
		return orderList;
	}
	
}
