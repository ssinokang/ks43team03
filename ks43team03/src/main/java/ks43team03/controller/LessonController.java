package ks43team03.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ks43team03.dto.Area;
import ks43team03.dto.AreaCity;
import ks43team03.dto.AreaCityTown;
import ks43team03.dto.Facility;
import ks43team03.dto.FacilityGoods;
import ks43team03.dto.Lesson;
import ks43team03.dto.Search;
import ks43team03.dto.Sports;
import ks43team03.service.CommonService;
import ks43team03.service.LessonService;
import ks43team03.service.SearchService;
import ks43team03.strategy.enumeration.SearchStrategyName;

@Controller
@RequestMapping("/lesson")
public class LessonController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	private final LessonService lessonService;
	private final CommonService commonService;
	private final SearchService searchService;
	public LessonController(LessonService lessonService, CommonService commonService, SearchService searchService) {
		this.lessonService = lessonService;
		this.commonService = commonService;
		this.searchService = searchService;
	}
	/**
	 *  회원이 보는 레슨 리스트 
	 **/
	@GetMapping("/detailLessonForUser")
	public String detailLessonForUser(@RequestParam(name		  = "lessonCd"
													,required 	  = false
													,defaultValue = "error") String lessonCd
									 ,Model model) {
		
		Lesson lesson = lessonService.getLessonInfoByCd(lessonCd);
		
		model.addAttribute("lesson", lesson);
		model.addAttribute("title" , "상품상세보기");
		
		log.info("model : {}", model);
		
		return "lesson/detailLessonForUser";
	}
	
	@GetMapping("/lessonListForUser")
	public String lessonListforUser(Model model
									,@RequestParam(name="searchCtg", required = false) String searchCtg
									,@RequestParam(name = "currentPage", required = false, defaultValue = "1") int currentPage){
		Map<String, Object> searchMap	  = new HashMap<>();
		SearchStrategyName searchName = SearchStrategyName.valueOf(searchCtg);
		
		
		List<Area> areaList = commonService.getAreaList();
		
		searchMap.put("searchCtg", searchCtg);
		Map<String, Object> resultMap = searchService.findSearch(searchName, searchMap, currentPage);
		
		model.addAttribute("title", 			"레슨 목록");
		model.addAttribute("lessonList"			, resultMap.get("LessonListForUser"));
		model.addAttribute("lessonList"			, resultMap.get("LessonListForUser"));
		model.addAttribute("lastPage"			, resultMap.get("lastPage"));
		model.addAttribute("startPageNum"		, resultMap.get("startPageNum"));
		model.addAttribute("endPageNum"			, resultMap.get("endPageNum"));
		model.addAttribute("currentPage"		, currentPage);
		model.addAttribute("areaList"			, areaList);
		model.addAttribute("title", 			"레슨 목록");
		return "lesson/lessonListForUser";
	};
	/**
	 * 레슨 상세 조회 
	 **/
	@GetMapping("/detailLesson")
	public String detailLesson(@RequestParam(name="lessonCd") String lessonCd
								,Model model) {
		
		Lesson lesson = lessonService.getLessonInfoByCd(lessonCd);
		model.addAttribute(lesson);
		model.addAttribute("title", "레슨상세조회");
		log.info("model : {}", model);
		return "lesson/detailLesson";
	}
	/**
	 * 레슨 수정
	 **/
	@PostMapping("/modyfyLesson")
	public String modifyLesson(Lesson lesson
							   ,Model model) {
		int result = lessonService.modifyLesson(lesson);
		model.addAttribute("facilityCd", lesson.getFacilityCd());
		return "lesson/facilitylessonList";
	}
	@GetMapping("/modifyLesson")
	public String modifyLesson(Model model
								,@RequestParam(name="lessonCd") String lessonCd) {
		Lesson lesson = lessonService.getLessonInfoByCd(lessonCd);
		model.addAttribute("title", "레슨수정");
		model.addAttribute("lesson", lesson);
		return "lesson/modifyLesson";
	}
	/**
	 * 시설 내에 등록된 레슨 리스트
	 **/
	@GetMapping("/facilityLessonList")
	public String facilityLessonList(@RequestParam(name="facilityCd") String facilityCd
							 ,Model model) {
		
		
		System.out.println(facilityCd + "lessonController/facilityLesosnLilst");
		List<Lesson> lessonList = lessonService.getfacilityLessonList(facilityCd);
		model.addAttribute("lessonList", lessonList);
		model.addAttribute("title", "레슨리스트");
		log.info("lessonList = {}", lessonList);
		return "lesson/lessonList";
	}
	
	@PostMapping("/addLesson")
	public String addLesson(
			FacilityGoods facilityGoods,
			Lesson lesson,
			@RequestParam MultipartFile[] lessonImgFile,
			Model model, 
			HttpServletRequest request) {
		lesson.setFacilityGoods(facilityGoods);
		log.info("LessonController addLesson/facilityGoods : {}", facilityGoods);
		log.info("LessonController addLesson/lesson : {}", lesson);
		log.info("lesson : {}", lesson);
		
		String serverName = request.getServerName();
		String fileRealPath = "";
		if("localhost".equals(serverName)) {				
			fileRealPath = System.getProperty("user.dir") + "/src/main/resources/static/";
			//fileRealPath = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/static/");
		}else {
			fileRealPath = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/static/");
		}
		
		lessonService.addLesson(lesson, lessonImgFile, fileRealPath);
		
		
		return "redirect:/lesson/facilityLessonList?" + "facilityCd="+ lesson.getFacilityCd();

		
	}

}
