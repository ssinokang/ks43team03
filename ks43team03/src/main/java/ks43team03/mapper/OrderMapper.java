package ks43team03.mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.Order;

@Mapper
public interface OrderMapper {
	
	// 주문 생성메소드
	public void addOrder(Order order);
	
	// 주문기본키로  주문정보를 보는 메소드
	public Optional<Order> getOrderByCode(String orderCd);
	
	// 주문 uuid로 주문정보를 보는 메소드 
	public Optional<Order> getOrderByOrderUUID(String orderId);
	
	
	//대관 주문내역 상세
	public Order getOrderDetailWithStadium(String orderCd);
	//레슨 주문내역 상세
	public Order getOrderDetailWithLesson(String orderCd);
	//이용권 주문내역 상세
	public Order getOrderDetailWithPass(String orderCd);
	
	public List<Order> orderInfomationByCategory(String category, String userId);
	
	// 주문정보 수정
	public void modifyOrder(Order order);
	
	
	
	// 회원 한명의 주문내역 조회 메소드
	public List<Order> getOrdersByUser(Map<String, Object> maps);
	
	// 주문 삭제 uuid 
	public void removeOrderByOrderUUID(String orderUUID);
	
	// 주문삭제 기본키
	public void removeOrderByOrderCd(String orderCd);
	
	// 회원한명의 주문카운트
	public int getOrderByUserCount(String userId);
	
	/**
	 * 관리자
	 */
	
	// 시설의 주문정보를 조회
	public List<Order> getOrderInfoForFacility(String faciltiyCd);
	
	
	// 관리자페이지의 전체 주문리스트
	public List<Order>getOrderAll();
	
}
