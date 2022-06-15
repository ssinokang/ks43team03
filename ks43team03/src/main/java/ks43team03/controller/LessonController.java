package ks43team03.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import ks43team03.dto.Lesson;
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
	 * 레슨 수정
	 **/
	@GetMapping("/modifyLesson")
	public String modifyLesson(@RequestParam(name="lessonCd") String lessonCd) {
		
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
		log.info("lessonList = {}", lessonList);
		return "lesson/lessonList";
	}
	
	@PostMapping("/addLesson")
	public String addLesson(Lesson lesson,
		    MultipartHttpServletRequest multipartHttpServletRequest) {
		log.info("!!!lesson : {}", lesson);
		System.out.println("!!!!!!!!!!!"+lesson);
		System.out.println(multipartHttpServletRequest);
		
		lessonService.addLesson(lesson, multipartHttpServletRequest);
		
		return "redirect:/lesson/facilityLessonList?" + "facilityCd="+ lesson.getFacilityCd();
		
	}
	@GetMapping("/addLesson")
	public String addLesson() {
		return "lesson/addLesson";
	}
}
