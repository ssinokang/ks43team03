package ks43team03.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ks43team03.dto.type.OrderState;

public class OrderStateTest {

	
	@Test
	@DisplayName("enumType변화 확인")
	void enumTypeTest() {
		System.out.println(OrderState.getEnumByCode("주문완료"));
		enumTest(OrderState.CENCEL);
	}
	
	
	void enumTest(OrderState orders) {
		System.out.println(orders.getCode());
		
	}
}
