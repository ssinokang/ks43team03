package ks43team03.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks43team03.dto.Area;
import ks43team03.dto.AreaCity;
import ks43team03.dto.AreaCityTown;
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
	
	
	
	

	/*시설수정처리*/
	 @PostMapping("/modifyFacility") public String modifyFacility(Facility facility) {
  
	 log.info("시설 정보 수정 폼 입력값 : {}", facility);
	 
	 adminFacilityService.modifyFacility(facility);
	 
	 return "redirect:/adminFacility/adminFacilityList";
	 }
	 
	 
	/*시설수정화면*/
	@GetMapping("/modifyFacility")
	public String modifyFacility(@RequestParam(value="facilityCd", required=false) String facilityCd
								,Model model) {
		
		Facility facility = adminFacilityService.getAdminFacilityInfoByCd(facilityCd);
		
		List<FacilityUse> facilityUseList = adminFacilityService.getFacilityUseList();
		List<MainCtg> mainCtgList = adminFacilityService.getMainCtgList();
		List<Area> areaList = adminFacilityService.getAreaList();
		List<AreaCity> areaCityList = adminFacilityService.getAreaCityList();
		List<AreaCityTown> areaCityTownList = adminFacilityService.getAreaCityTownList();
		
		model.addAttribute("facility", facility);	
		model.addAttribute("facilityUseList", facilityUseList);	
		model.addAttribute("mainCtgList", mainCtgList);
		model.addAttribute("areaList", areaList);
		model.addAttribute("areaCityList", areaCityList);
		model.addAttribute("areaCityTownList", areaCityTownList);
		
		 return "adminFacility/modifyFacility"; 
	}
	
		/*시설등록처리*/
	  @PostMapping("/addFacility") 
	  public String addFacility(Facility facility
			  					,@RequestParam(name="facilityCd", required = false) String facilityCd
			  					,HttpServletRequest request) {
		  log.info("시설등록화면에서 입력한 data : {}", facility);
	  
	  adminFacilityService.addFacility(facility); 
	  return "redirect:/adminFacility/adminFacilityList"; 
	  }

	
	/*시설등록화면*/
	@GetMapping("/addFacility")
	public String addFacility(Model model) {
		
		List<MainCtg> mainCtgList = adminFacilityService.getMainCtgList();
		List<Area> areaList = adminFacilityService.getAreaList();
		List<AreaCity> areaCityList = adminFacilityService.getAreaCityList();
		List<AreaCityTown> areaCityTownList = adminFacilityService.getAreaCityTownList();
		List<FacilityUse> facilityUseList = adminFacilityService.getFacilityUseList();
		
		model.addAttribute("mainCtgList", mainCtgList);
		model.addAttribute("areaList", areaList);
		model.addAttribute("areaCityList", areaCityList);
		model.addAttribute("areaCityTownList", areaCityTownList);
		model.addAttribute("facilityUseList", facilityUseList);
		
		return "adminFacility/addFacility";
	}
	
	//시설관리자가 본인시설조회
	@GetMapping("/adminFacilityListById")
	public String getAdminFacilityListById(Model model) {
		List<Facility> adminFacilityListById = adminFacilityService.getAdminFacilityListById();
		model.addAttribute("adminFacilityListById", adminFacilityListById);
		return "adminFacility/adminFacilityListById";
	}
	
	//관리자가 시설목록조회
	@GetMapping("/adminFacilityList")
	public String getAdminFacilityList(Model model) {
		List<Facility> adminFacilityList = adminFacilityService.getAdminFacilityList();
		model.addAttribute("adminFacilityList", adminFacilityList);
		return "adminFacility/adminFacilityList";
	}
	
}
