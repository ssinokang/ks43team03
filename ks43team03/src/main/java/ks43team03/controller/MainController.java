package ks43team03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String addGoods(@RequestParam(name="goodsCtgCd", required = false) String goodsCtgCd,
					Model model) {
		
		model.addAttribute("goodsCtgCd", goodsCtgCd);
		
		return "admin/addGoods";
	}
	@GetMapping("/admin/ex")
	public String ex() {
		return "ex";
	}
}
