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
import org.springframework.web.multipart.MultipartFile;

import ks43team03.dto.Facility;
import ks43team03.dto.FacilityGoods;
import ks43team03.dto.Sports;
import ks43team03.dto.Stadium;
import ks43team03.dto.StadiumPrice;
import ks43team03.service.StadiumService;

@Controller
@RequestMapping("/stadium")
public class StadiumController {
	
	private static final Logger log = LoggerFactory.getLogger(StadiumController.class);
	
	private final StadiumService stadiumService;
	
	public StadiumController(StadiumService stadiumService) {
		this.stadiumService = stadiumService;
	}
	

	/*회원이 구장 상세정보 조회*/
	@GetMapping("stadiumDetail")
	public String getStadiumInfoByCdForUser(Model model
											,@RequestParam(name="facilityStadiumCd", required = false) String facilityStadiumCd) {
		Stadium stadiumDetail = stadiumService.getStadiumInfoByCd(facilityStadiumCd);
		
		model.addAttribute("title", "구장 상세 정보");
		model.addAttribute("stadiumDetail", stadiumDetail);
		return "stadium/stadiumDetail";
	}
	
	
	
	/*회원이 구장 리스트 조회*/
	@GetMapping("/stadiumList")
	public String getStadiumList(Model model
			,@RequestParam(name = "currentPage", required = false, defaultValue = "1") int currentPage){
		
		Map<String, Object> resultMap = stadiumService.getStadiumList(currentPage);
		
		log.info("resultMap : {}",resultMap);
		log.info("resultMap.get(\"stadiumList\") : {}",resultMap.get("stadiumList"));
		
		model.addAttribute("resultMap", 			resultMap);
		model.addAttribute("currentPage", 			currentPage);
		model.addAttribute("stadiumList",		resultMap.get("stadiumList"));
		model.addAttribute("lastPage", 				resultMap.get("lastPage"));
		model.addAttribute("startPageNum", 			resultMap.get("startPageNum"));
		model.addAttribute("endPageNum", 			resultMap.get("endPageNum"));
		model.addAttribute("title", "구장 조회");
		return "stadium/stadiumList";
	}
	
	/*구장 단가 수정 처리*/
	@PostMapping("/modifyStadiumPrice") 
	public String modifyStadiumPrice(StadiumPrice stadiumPrice) {
		log.info("구장단가 수정 화면에서 입력한 data : {}", stadiumPrice);
		
		stadiumService.modifyStadiumPrice(stadiumPrice); 
		return "redirect:/stadium/adminStadiumListByCd"; 
	}
	
	
	/*구장 단가 수정화면*/
	@GetMapping("/modifyStadiumPrice")
	public String modifyStadiumPrice(@RequestParam(name="facilityStadiumCd", required=false) String facilityStadiumCd
								,Model model) {
		StadiumPrice stadiumPrice = stadiumService.getAdminStadiumPriceInfoByCd(facilityStadiumCd);
		List<Sports> sportsList = stadiumService.getSportsList();

		log.info("구장 단가 수정 화면");
		
		model.addAttribute("title", "구장 단가 수정");
		model.addAttribute("stadiumPrice",stadiumPrice);
		model.addAttribute("sportsList", sportsList);
		
		return "stadium/modifyStadiumPrice";
	}
	
	/*구장수정 처리*/
	@PostMapping("/modifyStadium") 
	public String modifyStadium(Stadium stadium) {
		
		log.info("구장수정 처리 화면에서 입력한 data : {}", stadium);

		stadiumService.modifyStadium(stadium); 
		
		return "redirect:/stadium/modifyStadiumPrice?" + "facilityStadiumCd="+ stadium.getFacilityStadiumCd();
	}
	
	
	/*구장수정화면*/
	@GetMapping("/modifyStadium")
	public String modifyStadium(@RequestParam(name="facilityStadiumCd", required=false) String facilityStadiumCd
			,Model model) {
		Stadium stadium = stadiumService.getAdminStadiumInfoByCd(facilityStadiumCd);
		
		log.info("구장수정 화면");
		List<Sports> sportsList = stadiumService.getSportsList();
	
		model.addAttribute("title", "구장 수정");
		model.addAttribute("stadium",stadium);
		model.addAttribute("sportsList",sportsList);
		log.info("구장수정화면 끝");
		return "stadium/modifyStadium";
	}
	
	/*구장 단가 등록 처리*/
	@PostMapping("/addStadiumPrice") 
	  public String addStadiumPrice(StadiumPrice stadiumPrice
			  					,@RequestParam(name="facilityStadiumCd", required = false) String facilityStadiumCd
			  					,HttpServletRequest request) {
		  log.info("구장단가등록화면에서 입력한 data : {}", stadiumPrice);
		  
		  stadiumService.addStadiumPrice(stadiumPrice); 
		  return "redirect:/stadium/adminStadiumListByCd"; 
	  }
	
	
	
	/*구장 단가 등록 화면*/
	@GetMapping("/addStadiumPrice")
	public String addStadiumPrice(Model model) {
		List<Sports> sportsList = stadiumService.getSportsList();
		model.addAttribute("title", "구장 등록");
		model.addAttribute("sportsList", sportsList);
		
		return "stadium/addStadiumPrice";
	}
	
	/*구장등록 처리*/
	@PostMapping("/addStadium") 
	public String addStadium(FacilityGoods facilityGoods
							,Stadium stadium
							,@RequestParam(name="facilityStadiumCd", required = false) String facilityStadiumCd
							,@RequestParam MultipartFile[] stadiumImgFile, Model model
							,HttpServletRequest request) {
		stadium.setFacilityGoods(facilityGoods);
		log.info("구장등록화면에서 입력한 data : {}", stadium);
		
		String serverName = request.getServerName();
		String fileRealPath = "";
		if("localhost".equals(serverName)) {				
			fileRealPath = System.getProperty("user.dir") + "/src/main/resources/static/";
			//fileRealPath = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/static/");
		}else {
			fileRealPath = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/static/");
		}
		
		stadiumService.addStadium(stadium, stadiumImgFile, fileRealPath);
		
		return "redirect:/stadium/addStadiumPrice?" + "facilityStadiumCd="+ stadium.getFacilityStadiumCd();
		
	}
	
	
	/*구장등록화면*/
	@GetMapping("/addStadium")
	public String addStadium(Model model
							,HttpSession session) {
		
		String sessionId = (String) session.getAttribute("SID");
		
		List<Facility> facilityListById = stadiumService.getFacilityListById(sessionId);
		List<Sports> sportsList = stadiumService.getSportsList();
		log.info("컨트롤러", facilityListById);
		log.info("컨트롤러", sessionId);
		
		model.addAttribute("title", "구장 등록");
		model.addAttribute("facilityListById", facilityListById);
		model.addAttribute("sportsList", sportsList);
		
		
		return "stadium/addStadium";
	}
	
	
	
	//본인 시설 내 구장 조회
	@GetMapping("/adminStadiumListByCd")
	public String getAdminStadiumListById(Model model
										,HttpSession session) {
		
		String sessionId = (String) session.getAttribute("SID");
		log.info("구장조회 아이디 : {}", sessionId);
		
		List<Stadium> adminStadiumListByCd = stadiumService.getAdminStadiumListByCd(sessionId);
		model.addAttribute("title", "시설 내 구장 정보");
		model.addAttribute("adminStadiumListByCd", adminStadiumListByCd);
		log.info("컨드롤러", adminStadiumListByCd);
		return "stadium/adminStadiumListByCd";
		}
	
	
	
	
	//관리자가 구장 목록조회
	@GetMapping("/adminStadiumList")
	public String getAdminStadiumList(Model model
									  ,@RequestParam(name = "currentPage", required = false, defaultValue = "1") int currentPage){
		
		Map<String, Object> resultMap = stadiumService.getAdminStadiumList(currentPage);
		
		log.info("resultMap : {}",resultMap);
		log.info("resultMap.get(\"adminStadiumList\") : {}",resultMap.get("adminStadiumList"));
		
		model.addAttribute("resultMap", 			resultMap);
		model.addAttribute("currentPage", 			currentPage);
		model.addAttribute("adminStadiumList",		resultMap.get("adminStadiumList"));
		model.addAttribute("lastPage", 				resultMap.get("lastPage"));
		model.addAttribute("startPageNum", 			resultMap.get("startPageNum"));
		model.addAttribute("endPageNum", 			resultMap.get("endPageNum"));
		model.addAttribute("title", "전체 구장 목록 조회");
		return "stadium/adminStadiumList";
	}
}
