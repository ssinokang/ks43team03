package ks43team03.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ks43team03.dto.LessonReservatioin;
import ks43team03.service.CalenderService;

@RestController
@RequestMapping("/calendar")
public class CalenderController {
	
	
	private static final Logger log = LoggerFactory.getLogger(CalenderController.class);
	private final CalenderService calenderService;
	
	public CalenderController(CalenderService calenderService) {
		this.calenderService = calenderService;
	}

	/**
	 *  캘린더 일정 보내기
	 **/
	@PostMapping("/lessonReservationData")
	@ResponseBody
	public List<LessonReservatioin> lessonReservationData(@RequestBody Map<String, String> scheduleDate) {
		log.info("lessonDate : {}" ,scheduleDate);

		List<LessonReservatioin> scheduleInfo = calenderService.findSearch(scheduleDate);
		
		log.info("scheduleInfo : {}", scheduleInfo);
		return scheduleInfo;
	}
}
