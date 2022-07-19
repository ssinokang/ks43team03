package ks43team03.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ks43team03.dto.Lesson;
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
	@PostMapping("/scheduleData")
	public String scheduleData(@RequestBody Map<String, String> scheduleDate) {
		String scheduleJson = "";
		log.info("lessonDate : {}" ,scheduleDate);

		Lesson scheduleInfo = calenderService.findSearch(scheduleDate);
		
		ObjectMapper om = new ObjectMapper();
		try {
			scheduleJson = om.writeValueAsString(scheduleInfo);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info(scheduleJson);
		return scheduleJson;
	}
}
