package ks43team03.controller;

import java.util.List;
import java.util.Map;

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
import ks43team03.dto.LessonOffer;
import ks43team03.dto.Sports;
import ks43team03.service.CommonService;
import ks43team03.service.LessonOfferService;

@Controller
@RequestMapping("/offer")
public class LessonOfferController {
	
	private final LessonOfferService offerService;
	private final CommonService commonService;
	
	private static final Logger log = LoggerFactory.getLogger(LessonOfferController.class);

	public LessonOfferController(LessonOfferService offerService, CommonService commonService) {
		this.offerService = offerService;
		this.commonService = commonService;
	}
	
	
	//== 구인 리스트 user ==//
	@GetMapping("/offerList")
	public String offerList(Model model) {
		List<LessonOffer> offerList = offerService.getOfferList();
		List<Area> areaList = commonService.getAreaList();
		List<Sports> sportsList = commonService.getSportsList();
		
		model.addAttribute("title", "트레이너가 즐겁게 일하도록");
		model.addAttribute("offerList", offerList);
		model.addAttribute("sportsList", sportsList);
		model.addAttribute("areaList", areaList);
		return "offer/LessonOfferList";
	}
	
	
	/*
	 * ajax 셀렉트박스 할때마다 검색
	 */
	@GetMapping("/offers/city")
	public String offers(@RequestParam(name = "areaCd", required = false)String areaCd,
						 @RequestParam(name = "sportsName", required = false)String sportsName, Model model){
		log.info("request AreaCd : {}", areaCd);
		log.info("request sportsName : {}", sportsName);
		
		
		List<LessonOffer> lessonOfferList = offerService.getLessonOfferCityOrSports(areaCd,sportsName);
		
		model.addAttribute("offerList", lessonOfferList);
		
		
		return "/offer/LessonOfferList :: #offerList";
	}
	
	/*
	 * Lesson Offer 등록화면 이동
	 */
	@GetMapping("/addOffer")
	public String addLessonOffer(HttpSession session, Model model) {
		
		String level = (String)session.getAttribute("SLEVEL");
		String userId = (String)session.getAttribute("SID");
		
		// 시설조회
		
		
		// 레슨조회
		
		return "offer/addOffer";
	}
	//addOffer
	@PostMapping("/addOffer")
	public String addLessonOffer(LessonOffer lessonOffer) {
		return "redirect:/";
	}
	
	
	//== 트레이너 구인 상세 페이지 user 이동  ==//
	@GetMapping("/offerDetail")
	public String offerDetail(@RequestParam(name = "lessonOfferCd")String lessonOfferCd, Model model) {
		log.info("lessonOfferCd data : {}", lessonOfferCd);
		
		
		return "offer/LessonOfferDetail";
	}
	//== 구인 폼 ==//
	
	
	
	
	
}
