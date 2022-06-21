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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import ks43team03.dto.FacilityGoods;
import ks43team03.dto.AreaCity;
import ks43team03.dto.AreaCityTown;
import ks43team03.dto.Facility;
import ks43team03.dto.Lesson;
import ks43team03.dto.Sports;
import ks43team03.service.LessonService;

@Controller
@RequestMapping("/lesson")
public class LessonController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	private final LessonService lessonService;
	
	public LessonController(LessonService lessonService) {
		this.lessonService = lessonService;
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
		
		log.info("lesson : {}", lesson);
		
		return "lesson/detailLessonForUser";
	}
	@GetMapping("/lessonListForUser")
	public String lessonListforUser(
			Lesson 		  lesson
			,Facility 	  facility
			,AreaCity 	  areaCity
			,AreaCityTown areaCityTown
			,Sports 	  sports
			,Model 		  model) {
		
		log.info("facility : {}", facility);
		log.info("areaCity : {}", areaCity);
		log.info("areaCityTown : {}", areaCityTown);
		log.info("sports : {}", sports);
		
		lesson.setAreaCityTown(areaCityTown);
		lesson.setFacility(facility);
		lesson.setAreaCity(areaCity);
		lesson.setSports(sports);
		
		log.info("lesson : {}", lesson);
		
		List<Lesson> lessonList = lessonService.getLessonListForUser(lesson);
		model.addAttribute("lessonList", lessonList);
		
		log.info("lessonList : {}", lessonList);
		return "lesson/lessonListforUser";
	}
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
			@RequestParam MultipartFile[] lessonImgFile, Model model, 
			HttpServletRequest request) {
		lesson.setFacilityGoods(facilityGoods);
		log.info("LessonController addLesson/facilityGoods : {}", facilityGoods);
		log.info("LessonController addLesson/lesson : {}", lesson);
		log.info("LessonController addLesson/multipartHttpServletRequest : {}", lessonImgFile);
		
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
	@GetMapping("/addLesson")
	public String addLesson(Model model) {
		model.addAttribute("title", "레슨등록");
		return "lesson/addLesson";
	}
}
