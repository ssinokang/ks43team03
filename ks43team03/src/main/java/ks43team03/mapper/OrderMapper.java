package ks43team03.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.Order;

@Mapper
public interface OrderMapper {
	
	// 주문 생성메소드
	public void addOrder(Order order);
	
	// 주문 상세정보를 보는 메소드
	public Order getOrderByCode(String orderCd);
	
	public Order getOrderDetailWithGoods(String orderCd);
	 
	// 관리자페이지의 전체 주문리스트
	public List<Order>getOrderAll();
	
	// 회원 한명의 주문내역 조회 메소드
	public List<Order> getOrderByUser(String userId);
}
