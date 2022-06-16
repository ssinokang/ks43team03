package ks43team03.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks43team03.dto.FacilityGoods;
import ks43team03.dto.Pass;

@Controller
@RequestMapping("/pass")
public class PassController {
	
	
	private static final Logger log = LoggerFactory.getLogger(PassController.class);

	
	@GetMapping("/addPass")
	public String addPass() {
		
		return "pass/adminAddPass";
	}
	
	@GetMapping("/addPass2")
	public String addPass2() {
		return "pass/adminAddPass2";
	}
	

	
	
	@PostMapping("/addPass2")
	public String addPass2(Pass pass) {
		log.info("facilityGoods값에 담긴갑 : {}", pass.getSportsCd());
		log.info("pass값에 담긴갑 : {}", pass.getGoodsCtgCd());
		log.info("abGoods test", pass);
		
		
		return null;
	}
	
	
}
