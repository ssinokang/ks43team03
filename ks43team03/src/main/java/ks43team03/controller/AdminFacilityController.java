package ks43team03.controller;

import java.util.List;
import java.util.Map;

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
	
	
	/*검색*/
	@PostMapping("/adminFacilityList")
	public String getSearchFacilityList(@RequestParam(name="searchKey")String searchKey
									 ,@RequestParam(name="searchValue", required = false)String searchValue
									 ,Model model) {
		
		log.info("searchKey : {}", searchKey);
		log.info("searchValue : {}", searchValue);
		if("memberId".equals(searchKey)) {
			searchKey = "m.m_id";
		}else if("memberLevel".equals(searchKey)) {
			searchKey = "m.m_level";
			
		}else if("memberName".equals(searchKey)) {
			searchKey = "m.m_name";
			
		}else {
			searchKey = "m.m_email";
			
		}
		List<Facility> searchFacilityList = adminFacilityService.getSearchFacilityList(searchKey, searchValue);
		
		if(searchFacilityList != null) model.addAttribute("searchFacilityList", searchFacilityList);
		
		return "adminFacility/adminFacilityList";
	}
	
	
	
	
	/*시설삭제화면*/
	@GetMapping("removeFacility")
	public String removeMember(Model model
							   ,@RequestParam(name = "userId", required = false) String userId
							   ,@RequestParam(name = "result", required = false) String result) {

		model.addAttribute("userId", userId);
		if(result != null) model.addAttribute("result", result);
		return "adminFacility/removeFacility";
		}
	

	/*시설수정처리*/
	 @PostMapping("/modifyFacility") 
	 public String modifyFacility(Facility facility) {
  
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
		
		model.addAttribute("title", "시설 수정");
		model.addAttribute("facility", facility);	
		model.addAttribute("facilityUseList", facilityUseList);	
		model.addAttribute("mainCtgList", mainCtgList);
		model.addAttribute("areaList", areaList);
		model.addAttribute("areaCityList", areaCityList);
		model.addAttribute("areaCityTownList", areaCityTownList);
		
		 return "adminFacility/modifyFacility"; 
	}
	
	/*시설등록처리*/
	/* String sessionId = (String) session.getAttribute("SID") */
	/* ,MultipartHttpServletRequest mhsr */
	  @PostMapping("/addFacility") 
	  public String addFacility(Facility facility
			  					,@RequestParam(name="facilityCd", required = false) String facilityCd
			  					,HttpServletRequest request) {
		  log.info("시설등록화면에서 입력한 data : {}", facility);
		  
		  adminFacilityService.addFacility(facility); 
		  return "redirect:/adminFacility/adminFacilityListById"; 
	  }

	
	/*시설등록화면*/
	@GetMapping("/addFacility")
	public String addFacility(Model model) {
		
		List<MainCtg> mainCtgList = adminFacilityService.getMainCtgList();
		List<Area> areaList = adminFacilityService.getAreaList();
		List<AreaCity> areaCityList = adminFacilityService.getAreaCityList();
		List<AreaCityTown> areaCityTownList = adminFacilityService.getAreaCityTownList();
		List<FacilityUse> facilityUseList = adminFacilityService.getFacilityUseList();
		
		model.addAttribute("title", "시설 등록");
		model.addAttribute("mainCtgList", mainCtgList);
		model.addAttribute("areaList", areaList);
		model.addAttribute("areaCityList", areaCityList);
		model.addAttribute("areaCityTownList", areaCityTownList);
		model.addAttribute("facilityUseList", facilityUseList);
		
		return "adminFacility/addFacility";
	}
	
	//시설관리자가 본인시설조회
	@GetMapping("/adminFacilityListById")
	public String getAdminFacilityListById(Model model
										 ,HttpSession session) {
		String sessionId = (String) session.getAttribute("SID");
		log.info("시설조회 아이디 : {}", sessionId);
		
		List<Facility> adminFacilityListById = adminFacilityService.getAdminFacilityListById(sessionId);
		model.addAttribute("title", "내 시설 정보");
		model.addAttribute("adminFacilityListById", adminFacilityListById);
		return "adminFacility/adminFacilityListById";
	}
	
	
	//관리자가 시설목록조회
	@GetMapping("/adminFacilityList")
	public String getAdminFacilityList(Model model
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
		return "adminFacility/adminFacilityList";
	}

}
