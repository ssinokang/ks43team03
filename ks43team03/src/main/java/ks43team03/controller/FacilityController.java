package ks43team03.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

	/*
	 * 시설 상세 조회
	 * 
	 * @GetMapping("/facilityDetail") public String get
	 */

	/* 사용자 시설 조회 */
	@GetMapping("/facilityList")
	public String getFacilityList(Model model) {
		List<Facility> facilityList = facilityService.getFacilityList();

		model.addAttribute("facilityList", facilityList);
		return "facility/facilityList";
	}

}
