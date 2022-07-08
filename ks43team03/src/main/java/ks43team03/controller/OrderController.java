package ks43team03.controller;


import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ks43team03.dto.Order;
import ks43team03.dto.ResponseGoods;
import ks43team03.service.FacilityGoodsService;
import ks43team03.service.OrderService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/order")
@Slf4j
public class OrderController {

	private final OrderService orderService;
	private final FacilityGoodsService facilityGoodsService;
	
	
	public OrderController(OrderService orderService,FacilityGoodsService facilityGoodsService) {
		this.orderService = orderService;
		this.facilityGoodsService = facilityGoodsService;
	}
	
	
	@PostMapping("/addOrder")
	public ResponseEntity<Order> addOrder(@RequestBody Order.Request req) {
		
		
		log.info("화면에서 받은 데이터 : {}", req.getGoodsName());
		
		log.info("데이터 userId 요청 : {}", req.getUserId());
		log.info("데이터 facilityGoodsCd 요청 : {}", req.getFacilityGoodsCd());
		
		// order 저장 
		ResponseGoods responseGoods = facilityGoodsService.getFacilityGoodsCd(req.getFacilityGoodsCd());
		String goodsCode = responseGoods.getFacilityGoods().getFacilityGoodsCd();
		 
		
		return ResponseEntity.ok(orderService.addOrder(req, responseGoods));
	}
	
	
	

	
	
	//==회원의 주문상세내역 조회==//
	@GetMapping("/orderDetail/{id}")
	public String orderDetail(@PathVariable("id") String userId,Model model
							 ,@RequestParam(name = "orderCd")String orderCd) {
		log.info("화면에서 받은  orderCd 데이터 : {}", orderCd);
		log.info("화면에서 받은 userId 데이터 : {}", userId);
		Order order = orderService.getOrderByCode(orderCd);
		
		// 상품 코드 goodsService vs orderService 에서 
		
		model.addAttribute("title", userId + "님의 구매하신 상품상세정보");
		model.addAttribute("order", order);
		
		return "order/orderDetail";
	}
	
	
	@GetMapping("/orders/{id}")
	public String orders(@PathVariable("id") String userId, Model model,
						 @RequestParam(name = "currentPage", required = false, defaultValue = "1")int currentPage) {
		
		Map<String,Object> orderList = orderService.getOrdersByUser(currentPage,userId);
		
		model.addAttribute("orderList", orderList.get("orderList"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", orderList.get("lastPage"));
		model.addAttribute("startPage", orderList.get("startPage"));
		model.addAttribute("endPage", orderList.get("endPage"));
		model.addAttribute("userId", userId);
		
		
		model.addAttribute("title", "회원님의 주문내역입니다.");
		return "order/회원한명주문리스트";
	}
	
	@GetMapping("/orderAfter")
	public String orderinfo() {
		return "order/orderAfter";
	}
	
	
	/*
	 * orderUUID로 주문 완전 삭제 메소드 
	 */
	@PostMapping("/removeOrder")
	@ResponseBody
	public void removeOrder(@RequestParam("orderUUID") String orderUUID) {
		log.info("orderUUID : {}", orderUUID);
		orderService.removeOrderByOrderUUID(orderUUID);
	}
	
	//==판매자 주문예약/결제 정보 조회==//
	
	
	
	
	
	
	
	
	//== 화면연결 임시메소드 ==//
	/**
	 *  화면연결 임시메소드   
	 */
	@GetMapping("/goods")
	public String goodsRead() {
		return "goods/goodsRead";
	}
	
	@GetMapping("/tempgoods")
	public String goodsTemp() {
		return "goods/하나의 상품페이지";
	}
}
