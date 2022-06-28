package ks43team03.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks43team03.service.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	
	private static final Logger log = LoggerFactory.getLogger(ReviewController.class);
	
	private final ReviewService reviewService;
	
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@GetMapping("/adminReviewList")
	public String getAdminReviewList(Model model
								,@RequestParam(name = "currentPage", required = false, defaultValue = "1") int currentPage) {
		
		Map<String, Object> resultMap = reviewService.getAdminReviewList(currentPage);
				
		log.info("resultMap : {}",resultMap);
		log.info("resultMap.get(\"adminReviewList\") : {}",resultMap.get("adminReviewList"));
		
		model.addAttribute("resultMap", 			resultMap);
		model.addAttribute("currentPage", 			currentPage);
		model.addAttribute("adminReviewList",			resultMap.get("adminReviewList"));
		model.addAttribute("lastPage", 				resultMap.get("lastPage"));
		model.addAttribute("startPageNum", 			resultMap.get("startPageNum"));
		model.addAttribute("endPageNum", 			resultMap.get("endPageNum"));
		model.addAttribute("title", "후기 전체 조회");
		return "review/adminReviewList";
	}
	
}
