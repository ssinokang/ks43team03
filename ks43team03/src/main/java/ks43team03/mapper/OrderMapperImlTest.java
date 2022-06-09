package ks43team03.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ks43team03.dto.Order;

public class OrderMapperImlTest implements OrderMapper {
	
	Map<String, Order> orderMap = new HashMap<>();
	String oCode = "ocode";
	int cnt = 0;
	
	@Override
	public Order addOrder(Order order) {
		order.setOrderCd("ocode" + cnt++);
		orderMap.put(order.getOrderCd(), order);
		return order;
	}
	
	@Override
	public Order getOrderByCode(String orderCd) {
		return orderMap.get(orderCd);
	}
	
	@Override
	public List<Order> getOrderAll() {
		return new ArrayList<>(orderMap.values());
	}
	
	@Override
	public List<Order> getOrderByMember(String userId) {
		
		return orderMap.values().stream()
					.filter(o -> o.getUserId().equals(userId))
					.toList();
	}
	
	public void clear() {
		orderMap.clear();
	}
	
}
