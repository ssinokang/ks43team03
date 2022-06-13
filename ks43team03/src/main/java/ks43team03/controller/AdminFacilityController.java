package ks43team03.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks43team03.dto.Facility;
import ks43team03.dto.FacilityUse;
import  ks43team03.service.AdminFacilityService;

@Controller
@RequestMapping("/adminFacility")
public class AdminFacilityController {
	
	private static final Logger log = LoggerFactory.getLogger(AdminFacilityController.class);

	private final AdminFacilityService adminFacilityService;
	
	public AdminFacilityController(AdminFacilityService adminFacilityService) {
		this.adminFacilityService = adminFacilityService;
	}
	
	
	@GetMapping("/addFacility")
	public String addFacility(Model model) {
		List<FacilityUse> facilityUseList = adminFacilityService.getFacilityUserList();
		
		model.addAttribute("facilityUseList", facilityUseList);
		
		return "facility/addFacility";
	}

	//관리자가 시설목록조회
	@GetMapping("/adminFacilityList")
	public String getAdminFacilityList(Model model) {
		List<Facility> adminFacilityList = adminFacilityService.getAdminFacilityList();
		model.addAttribute("adminFacilityList", adminFacilityList);
		return "adminFacility/adminFacilityList";
	}
	
}
