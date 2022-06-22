package ks43team03.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks43team03.service.SafetyService;

@Controller
@RequestMapping("/safety")
public class SafetyController {
	private static final Logger log = LoggerFactory.getLogger(SafetyController.class);
	private final SafetyService safetyService;
	
	public SafetyController(SafetyService safetyService) {
		this.safetyService = safetyService;
	}

	/*안전점검 등록*/
	@GetMapping("/addSafety")
	public String addSafety(Model model) {
		
		model.addAttribute("title", "안전점검 등록");
		
		return "safety/addSafety";
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	