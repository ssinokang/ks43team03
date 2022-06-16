package ks43team03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks43team03.dto.Order;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/order")
@Slf4j
public class OrderController {

	
	
	@GetMapping("/order")
	public String order() {
		return "order/orderForm";
	}
	
	@PostMapping("/addOrder")
	public String addOrder(Order order) {
		log.info("order의 담긴 값 : {}", order);
		
		
		// order 저장 
		
		String orderCode = "oderCdoe1111112";
		return "redirect:/order/order-info?orderCd="+orderCode;
	}
	
	
	
	@GetMapping("/order-info")	//@RequestParam(name = "orderCd")String orderCd
	public String orderInfo() {
		// 주문완료 후 주문코드 조회 후주문내역 조회 
		
		// 
		
		// 이용권 내역 조회
		
		return "order/orderInfo";
	}
	
	
	//==회원의 주문상세내역 조회==//
	@GetMapping("/orderDetail")
	public String orderDetail() {
		return "order/orderDetail";
	}
	
	
	
	
	//==판매자 주문예약/결제 정보 조회==//
	
	
	
	
	
	
	
	
	
	@GetMapping("/goods")
	public String goodsRead() {
		return "goods/goodsRead";
	}
	
	@GetMapping("/tempgoods")
	public String goodsTemp() {
		return "goods/tempGoods";
	}
}
