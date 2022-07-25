package ks43team03.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks43team03.dto.Area;
import ks43team03.dto.Search;
import ks43team03.dto.Sports;
import ks43team03.service.CommonService;
import ks43team03.service.SearchService;
import ks43team03.strategy.enumeration.SearchStrategyName;

@Controller
public class SearchController {
	
	private static final Logger log = LoggerFactory.getLogger(SearchController.class);
	
	private CommonService commonService;
	private SearchService searchService;
	
	public SearchController(CommonService commonService, SearchService searchService) {
		this.commonService = commonService;
		this.searchService = searchService;
	}
	
	@PostMapping("/search")
	public String searchfsorUser(
			 @RequestParam(name = "mainCtgCd"	,required = false, defaultValue = "") String mainCtgCd
			,@RequestParam(name = "areaCd"		,required = false, defaultValue = "") String areaCd
			,@RequestParam(name = "CityCd"		,required = false, defaultValue = "") String CityCd
			,@RequestParam(name = "searchCtg"	,required = false, defaultValue = "") String searchCtg
			,@RequestParam(name = "sportsCd"	,required = false, defaultValue = "") String sportsCd
			,@RequestParam(name = "sv"			,required = false, defaultValue = "") String sv
			,@RequestParam(name = "currentPage"	,required = false, defaultValue = "1") int currentPage 
			,Model 		  model																			){
		
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		
		searchMap.put("CityCd"		, CityCd);
		searchMap.put("mainCtgCd"	, mainCtgCd);
		searchMap.put("areaCd"		, areaCd);
		searchMap.put("sportsCd"	, sportsCd);

		SearchStrategyName searchName = SearchStrategyName.valueOf(searchCtg);
		
		log.info("searchMap : {}", searchMap);
		searchMap.put("searchValue", sv);
		
		Map<String, Object> resultMap = searchService.findSearch(searchName, searchMap, currentPage);
		List<Area> 	 areaList		  = commonService.getAreaList();
		List<Sports> sportsList		  = commonService.getSportsList();
		
		// 트레이너 검색 일 경우
		if(searchCtg.equals("TrainerSearch")) {
			model.addAttribute("sportsList"			, sportsList);
			model.addAttribute("areaList"			, areaList);
			model.addAttribute("trainerList"		, resultMap.get("trainerList"));
			model.addAttribute("lastPage"			, resultMap.get("lastPage"));
			model.addAttribute("startPageNum"		, resultMap.get("startPageNum"));
			model.addAttribute("endPageNum"			, resultMap.get("endPageNum"));
			model.addAttribute("currentPage"		, currentPage);
			model.addAttribute("title", 			"트레이너 목록");
			
			return "trainer/trainerList";
		}
		
		model.addAttribute("sportsList"			, sportsList);
		model.addAttribute("areaList"			, areaList);
		model.addAttribute("lessonList"			, resultMap.get("LessonListForUser"));
		model.addAttribute("lastPage"			, resultMap.get("lastPage"));
		model.addAttribute("startPageNum"		, resultMap.get("startPageNum"));
		model.addAttribute("endPageNum"			, resultMap.get("endPageNum"));
		model.addAttribute("currentPage"		, currentPage);
		model.addAttribute("title", 			  resultMap.get("title"));
		
		log.info((String)resultMap.get("path") , "무사 통과");
		

		return (String)resultMap.get("path");
	}
}
