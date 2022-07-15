package ks43team03.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks43team03.dto.Order;
import ks43team03.service.OrderService;

@Controller
@RequestMapping("/admin")
public class AdminOrderController {

	private final OrderService orderService;
	
	
	private static final Logger log = LoggerFactory.getLogger(AdminOrderController.class);

	
	public AdminOrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("/order/orderList")
	public String adminOrderList(Model model) {
		List<Order> orderList = orderService.getOrderAll();
		model.addAttribute("orderList", orderList);
		return "admin/order/adminOrderList";
	}
	
	
	/*
	 * 주문내역 
	 */
	@GetMapping("/order/orderDetail")
	public String orderDetail(@RequestParam(name = "orderCd")String orderCd,
							@RequestParam(name = "goodsCtgCd", required = false)String goodsCtgCd,
							Model model) {
		log.info("화면에서 받은  orderCd 데이터 : {}",orderCd);
		log.info("화면에서 받은  goodsCtgCd 데이터 : {}",goodsCtgCd);
		Order order = orderService.getOrderDetail(orderCd);
		model.addAttribute("order", order);
		return "admin/order/orderDetail";
	}
	
	
	@GetMapping("/order/search")
	public String orderSearch() {
		return "redirect:/";
	}
}
