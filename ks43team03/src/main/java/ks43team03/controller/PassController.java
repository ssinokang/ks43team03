package ks43team03.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import ks43team03.dto.FacilityGoods;

@Controller
public class PassController {
	
	
	private static final Logger log = LoggerFactory.getLogger(PassController.class);

	
	@GetMapping("/addPass")
	public String addPass() {
		
		return "pass/addAdminPass";
	}
	

}
