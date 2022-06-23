package ks43team03.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks43team03.dto.Safety;
import ks43team03.service.SafetyService;

@Controller
@RequestMapping("/safety")
public class SafetyController {
	private static final Logger log = LoggerFactory.getLogger(SafetyController.class);
	private final SafetyService safetyService;
	
	public SafetyController(SafetyService safetyService) {
		this.safetyService = safetyService;
	}

	//안전점검 등록
	@PostMapping("/addSafety")
	public String addSafety(Safety safety) {
		
		log.info("안전점검 등록 시작");
		
		log.info("안전점검 등록에서 입력받은 데이터:{}", safety);		
		
		int result = safetyService.addSafety(safety);
		
		return "redirect:/safety/addSafety";
	}
	
	
	
	// 안전점검 등록 페이지 이동
	@GetMapping("/addSafety")
	public String addSafety(Model model) {
		
		model.addAttribute("title", "안전점검 등록");
		
		return "safety/addSafety";
	}
	
	// 안전점검 등록 시설 목록 조회(전체)
	@GetMapping("/safetyList")
	public String getSafetyList(@RequestParam(name = "currentPage", required = false, defaultValue = "1") int currentPage
			  ,Model model) {

		Map<String, Object> resultMap = safetyService.getSafetyList(currentPage);
		resultMap.get("lastPage");
		
		model.addAttribute("title", "안전점검 등록 시설 목록");
		model.addAttribute("resultMap", 		resultMap);
		model.addAttribute("currentPage", 		currentPage);
		model.addAttribute("safetyList", 		resultMap.get("safetyList"));
		model.addAttribute("lastPage", 			resultMap.get("lastPage"));
		model.addAttribute("startPageNum", 		resultMap.get("startPageNum"));
		model.addAttribute("endPageNum", 		resultMap.get("endPageNum"));
		System.out.println(model + "model!!!!!!!!!!");
		return "safety/safetyList";
}	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	