package ks43team03.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks43team03.dto.Pass;
import ks43team03.service.FacilityGoodsService;
import ks43team03.service.PassService;

@Controller
@RequestMapping("/pass")
public class PassController {
	
	
	private static final Logger log = LoggerFactory.getLogger(PassController.class);

	private final FacilityGoodsService facilityGoodsService;
	private final PassService passService;
	
	public PassController(FacilityGoodsService facilityGoodsService,PassService passService) {
		this.facilityGoodsService = facilityGoodsService;
		this.passService = passService;
	}
	
	
//	@GetMapping("/addPass")
//	public String addPass(Model model) {
//		
//		model.addAttribute("facility", "ss_35011740_01");
//		
//		return "pass/adminAddPass";
//	}

	@PostMapping("/addPass")
	public String addPass(Pass pass) {
		passService.addPass(pass);
		return "redirect:/pass/adminPassList";
	}
	
	
	@GetMapping("/addPass2")
	public String addPass2(@RequestParam(name = "userId", required = false)String userId) {
		
		
		
		return "pass/adminAddPass2";
	}
	
	@GetMapping("/admin/passList")
	public String passList(Model model) {
		List<Pass> passList = passService.getPassAll();
		model.addAttribute("title", "이용권리스트 관리자");
		model.addAttribute("passList", passList);
		return "pass/adminPassList";
	}
	
	
	
	@GetMapping("/admin/passDetail")
	public String passDetail(@RequestParam(name = "passCd")String passCd,@RequestParam(name = "facilityGoodsCd", required = false)String facilityGoodsCd) {
		log.info("화면에서 받은 facilityGoodsCd 데이터 :  {}", facilityGoodsCd);
		log.info("화면에서 받은 passCd 데이터 :  {}", passCd);
		
		return "pass/passDetail";
	}
	
	
//	@PostMapping("/addPass2")
//	public String addPass2(Pass pass) {
//		log.info("pass값에 담긴갑 : {}", pass.getGoodsCtgCd());
//		log.info("abGoods test", pass);
//		
//		
//		return null;
//	}
	
	
}
