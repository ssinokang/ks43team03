package ks43team03.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks43team03.service.AdminFacilityService;


@Controller
public class MainController {
	
	private final AdminFacilityService adminFacilityService;

	public MainController(AdminFacilityService adminFacilityService) {
		this.adminFacilityService = adminFacilityService;
	}
	
	
	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	
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

	
	/**
	 * 임시 map 컨트롤러
	 */
	@GetMapping("/map")
	public String searchMap() {
		return "map/map";
	}
	
	@GetMapping("/map2")
	public String searchMap2(Model model
			  				,@RequestParam(name = "currentPage", required = false, defaultValue = "1") int currentPage){
		
		Map<String, Object> resultMap = adminFacilityService.getAdminFacilityList(currentPage);
		
		log.info("resultMap : {}",resultMap);
		log.info("resultMap.get(\"adminFacilityList\") : {}",resultMap.get("adminFacilityList"));
		
		model.addAttribute("resultMap", 			resultMap);
		model.addAttribute("currentPage", 			currentPage);
		model.addAttribute("adminFacilityList",		resultMap.get("adminFacilityList"));
		model.addAttribute("lastPage", 				resultMap.get("lastPage"));
		model.addAttribute("startPageNum", 			resultMap.get("startPageNum"));
		model.addAttribute("endPageNum", 			resultMap.get("endPageNum"));
		model.addAttribute("title", "전체 시설 목록 조회");
		return "map/map2";
  }
	
	
	@GetMapping("/cal")
	public String cal() {
		return "order/cal";
  }
	@GetMapping("/admin/ex")
	public String ex() {
		return "ex";

	}
}
