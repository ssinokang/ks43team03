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
}
