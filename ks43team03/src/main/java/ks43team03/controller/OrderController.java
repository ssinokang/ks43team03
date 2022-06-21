package ks43team03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks43team03.dto.FacilityGoods;
import ks43team03.dto.Order;
import ks43team03.dto.User;
import ks43team03.service.FacilityGoodsService;
import ks43team03.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/order")
@Slf4j
public class OrderController {

	private final UserService userService;
	private final FacilityGoodsService facilityGoodsService;
	

	public OrderController(UserService userService, FacilityGoodsService facilityGoodsService) {
		this.userService = userService;
		this.facilityGoodsService = facilityGoodsService;
	}
	
	// 주문 결제화면으로 이동 
	@GetMapping("/addOrder")
	public String order(Model model, @RequestParam(name = "userId", required = false)String userId,@RequestParam(name = "facilityGoodsCd" , required = false)String facilityGoodsCd) {
		
		
		log.info("화면에서 받은 goodsCode : {}", facilityGoodsCd);
		log.info("화면에서 받은 userId : {}", userId);
		
		facilityGoodsCd = "gg_35011750_02_lesson_04";
		userId = "id002";
		User user = userService.getUserInfoById(userId);
		FacilityGoods facilityGoods = facilityGoodsService.getFacilityGoodsCd(facilityGoodsCd);
		
		model.addAttribute("user", user);
		model.addAttribute("goods", facilityGoods);
		
		return "order/orderForm";
	}
	
	@PostMapping("/addOrder")
	public String addOrder(Order order) {
		log.info("order의 담긴 값 : {}", order);
		
		
		// order 저장 
		
		String orderCode = "oderCdoe1111112";
		return "redirect:/order/orderDetail?orderCd="+orderCode;
	}
	
	
	

	
	
	//==회원의 주문상세내역 조회==//
	@GetMapping("/orderDetail")
	public String orderDetail(@RequestParam(name = "orderCode")String orderCode) {
		
		return "order/orderDetail";
	}
	
	
	@GetMapping("/orders")
	public String orders() {
		return "order/회원한명주문리스트";
	}
	
	@GetMapping("/orderAfter")
	public String orderinfo() {
		return "order/orderAfter";
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
