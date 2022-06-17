package ks43team03.controller;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks43team03.dto.Facility;
import ks43team03.service.FacilityService;

@Controller
@RequestMapping("/facility")
public class FacilityController {
	private static final Logger log = LoggerFactory.getLogger(FacilityController.class);

	private final FacilityService facilityService;

	public FacilityController(FacilityService facilityService) {
		this.facilityService = facilityService;
	}

	
	/* 시설 상세 조회*/

	 @GetMapping("/facilityDetail")
	 public String getFacilityDetail(Model model
									 ,@RequestParam(name="facilityCd", required = false) String facilityCd){
										
			Facility facilityDetail = facilityService.getFacilityDetail(facilityCd);
			log.info(facilityCd);
			
			model.addAttribute("title", "시설 상세 정보");
			model.addAttribute("facilityDetail", facilityDetail);
			return "facility/facilityDetail";
			
										 }


	/* 사용자가 시설 조회 */
	@GetMapping("/facilityList")
	public String getFacilityList(Model model
								,@RequestParam(name = "currentPage", required = false, defaultValue = "1") int currentPage){
		Map<String, Object> resultMap = facilityService.getFacilityList(currentPage);
		
		log.info("resultMap : {}",resultMap);
		log.info("resultMap.get(\"facilityList\") : {}",resultMap.get("facilityList"));
		
		model.addAttribute("resultMap", 			resultMap);
		model.addAttribute("currentPage", 			currentPage);
		model.addAttribute("facilityList",		resultMap.get("facilityList"));
		model.addAttribute("lastPage", 				resultMap.get("lastPage"));
		model.addAttribute("startPageNum", 			resultMap.get("startPageNum"));
		model.addAttribute("endPageNum", 			resultMap.get("endPageNum"));
		model.addAttribute("title", "시설");
		return "facility/facilityList";
	}

}
