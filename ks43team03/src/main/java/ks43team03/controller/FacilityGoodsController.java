package ks43team03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks43team03.dto.FacilityGoods;
import ks43team03.dto.ResponseGoods;
import ks43team03.dto.User;
import ks43team03.service.FacilityGoodsService;
import ks43team03.service.UserService;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequestMapping("/goods")
public class FacilityGoodsController {
	
	private final FacilityGoodsService facilityGoodsService;
	private final UserService userService;
	
	
	
	public FacilityGoodsController(FacilityGoodsService facilityGoodsService, UserService userService) {
		this.facilityGoodsService = facilityGoodsService;
		this.userService = userService;
	}


	// 주문 결제화면으로 이동
	// goodsCtg 
	@GetMapping("/order")
	public String order(Model model, @RequestParam(name = "userId", required = false)String userId,
									 @RequestParam(name = "facilityGoodsCd" , required = false)String facilityGoodsCd) {
		
		
		log.info("화면에서 받은 goodsCode : {}", facilityGoodsCd);
		log.info("화면에서 받은 userId : {}", userId);
		
		facilityGoodsCd = "ss_35011600_04_pass_11";
		userId = "id002";
		User user = userService.getUserInfoById(userId);
		//ResponseGoods facilityGoods = facilityGoodsService.getFacilityGoodsCd(facilityGoodsCd);

		
		model.addAttribute("title", "결제 페이지");
		model.addAttribute("user", user);
		//model.addAttribute("goods", facilityGoods);
		
		return "order/orderForm";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//== html  임시경로  매핑 ==//
	@GetMapping("/goods")
	public String goodsRead() {
		return "goods/goodsRead";
	}
	
	
	//== html  임시경로  매핑 ==//
	@GetMapping("/tempgoods")
	public String goodsTemp() {
		return "goods/tempGoods";
	}

}
