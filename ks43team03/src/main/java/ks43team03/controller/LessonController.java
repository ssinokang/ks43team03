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
	
	@GetMapping("/LessonList")
	public String lessonList(Model model) {
		
		String facilityCd = (String) model.getAttribute("facilityCd");
		System.out.println(facilityCd + "fac@@@@@@@");
		List<Lesson> lessonList = lessonService.getLessonList(facilityCd);
		model.addAttribute("lessonList", lessonList);
		log.info("lessonList = {}", lessonList);
		return "lesson/lessonList";
	}
	
	@PostMapping("/addLesson")
	public String addLesson(Lesson lesson) {
		log.info("!!!lesson : {}", lesson);
		System.out.println("!!!!!!!!!!!"+lesson);
		
		return "/lesson/LessonList";
		
	}
	@GetMapping("/addLesson")
	public String addLesson() {
		return "lesson/addLesson";
	}
}
