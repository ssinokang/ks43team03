package ks43team03.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks43team03.dto.Facility;
import ks43team03.dto.FacilityUse;
import ks43team03.dto.MainCtg;
import  ks43team03.service.AdminFacilityService;

@Controller
@RequestMapping("/adminFacility")
public class AdminFacilityController {
	
	private static final Logger log = LoggerFactory.getLogger(AdminFacilityController.class);

	private final AdminFacilityService adminFacilityService;
	
	public AdminFacilityController(AdminFacilityService adminFacilityService) {
		this.adminFacilityService = adminFacilityService;
	}
	
	
	
	

	
	 @PostMapping("/modifyFacility") public String modifyFacility(Facility
	 facility) {
  
	 log.info("시설 정보 수정 폼 입력값 : {}", facility);
	 
	 adminFacilityService.modifyFacility(facility);
	 
	 return "redirect:/adminFacility/adminFacilityList";
	 }
	/*시설수정화면*/
	@GetMapping("/modifyFacility")
	public String modifyFacility(@RequestParam(name="facilityCd", required=false) String facilityCd
								,Model model) {
		log.info("화면에서 입력받은 data:{}", facilityCd);
		Facility facility = adminFacilityService.getAdminFacilityInfoByCd(facilityCd);
		List<FacilityUse> facilityUseList = adminFacilityService.getFacilityUserList();
		List<MainCtg> mainCtgList = adminFacilityService.getMainCtgList();
		model.addAttribute("facilityUseList", facilityUseList);
		model.addAttribute("mainCtgList", mainCtgList);
		
		
		 return "adminFacility/modifyFacility"; 
	}
	
	
	/*시설등록*/
	@GetMapping("/addFacility")
	public String addFacility(Model model) {
		List<FacilityUse> facilityUseList = adminFacilityService.getFacilityUserList();
		List<MainCtg> mainCtgList = adminFacilityService.getMainCtgList();
		model.addAttribute("facilityUseList", facilityUseList);
		model.addAttribute("mainCtgList", mainCtgList);
		
		return "adminFacility/addFacility";
	}
	/*시설등록 처리*/
	@PostMapping("/addFacility")
	public String addFacility(HttpSession session
							 , Facility facility
							 , FacilityUse facilityUse
							 , MainCtg mainCtg
							 ,@RequestParam(name="facilityCd", required = false) String facilityCd
							 ,HttpServletRequest request) {
		
		adminFacilityService.addFacility(facility);
		return "redirect:/adminFacility/adminFacilityList";
	}
	

	//관리자가 시설목록조회
	@GetMapping("/adminFacilityList")
	public String getAdminFacilityList(Model model) {
		List<Facility> adminFacilityList = adminFacilityService.getAdminFacilityList();
		model.addAttribute("adminFacilityList", adminFacilityList);
		return "adminFacility/adminFacilityList";
	}
	
}