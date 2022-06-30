package ks43team03.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks43team03.service.OfferService;

@Controller
@RequestMapping("/offer")
public class OfferController {
	
	private final OfferService offerService;
	
	public OfferController(OfferService offerService) {
		this.offerService = offerService;
	}
	
	
	//== 구인 리스트 user ==//
	@GetMapping("/offerList")
	public String offerList(Model model) {
		List<Map<String,Object>> offerList = offerService.getOfferList();
		
		model.addAttribute("title", "트레이너가 즐겁게 일하도록");
		model.addAttribute("offerList", offerList);
		
		return "offer/offerList";
	}
	
	
	//== 트레이너 구인 상세 페이지 user ==//
	@GetMapping("/offerDetail")
	public String offerDetail() {
		
		return "offer/offerDetail";
	}
	//== 구인 폼 ==//
	
	
	
	
	
}
