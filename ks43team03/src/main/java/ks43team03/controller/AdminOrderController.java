package ks43team03.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks43team03.dto.Order;
import ks43team03.service.OrderService;

@Controller
@RequestMapping("/admin")
public class AdminOrderController {

	private final OrderService orderService;
	
	public AdminOrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("order/orderList")
	public String adminOrderList(Model model) {
		List<Order> orderList = orderService.getOrderAll();
		List<Order> testList = new ArrayList<>();
		model.addAttribute("orderList", testList);
		return "admin/order/adminOrderList";
	}
}
