package ks43team03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/admin")
	public String adminIndex() {
		return "admin/adminIndex";
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	/**
	 *임시 addGoods 컨트롤러  
	 ***/
	@GetMapping("/admin/addGoods")
	public String addGoods() {
		return "admin/addGoods";
	}
	
	
	/**
	 * 캘린더 테스트
	 */
	@GetMapping("/cal")
	public String cal() {
		return "order/cal";
	}
}
