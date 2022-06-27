package ks43team03.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.Order;

@Mapper
public interface OrderMapper {
	
	// 주문 생성메소드
	public void addOrder(Order order);
	
	// 주문 상세정보를 보는 메소드
	public Optional<Order> getOrderByCode(String orderCd);
	
	//대관 주문내역 상세
	public Order getOrderDetailWithStadium(String orderCd);
	//레슨 주문내역 상세
	public Order getOrderDetailWithLesson(String orderCd);
	//이용권 주문내역 상세
	public Order getOrderDetailWithPass(String orderCd);
	
	
	// 관리자페이지의 전체 주문리스트
	public List<Order>getOrderAll();
	
	// 회원 한명의 주문내역 조회 메소드
	public List<Order> getOrdersByUser(String userId);
}
