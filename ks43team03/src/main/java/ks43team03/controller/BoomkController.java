package ks43team03.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks43team03.dto.Boomk;
import ks43team03.service.BoomkService;

@Controller
@RequestMapping("/boomk")
public class BoomkController {

	private static final Logger log = LoggerFactory.getLogger(BoomkController.class);

	private final BoomkService boomkService;

	public BoomkController(BoomkService boomkService) {
		this.boomkService = boomkService;
	}

	// 즐겨찾기 내역
	@GetMapping("/boomkList")
	public String getBoomkList(Model model, HttpSession session) {

		String sessionId = (String) session.getAttribute("SID");

		log.info("회원 아이디 : {}", sessionId);
		

		if (Objects.isNull(sessionId)) {

			return "boomk/boomkList";
			
		}
		
		List<Boomk> boomkList = boomkService.getBoomkList(sessionId);
		
		model.addAttribute("title", "회원정보");
		model.addAttribute("boomkList", boomkList);
		model.addAttribute("sessionId", sessionId);		
		model.addAttribute("title", "회원 정보");
		log.info("boomkList : {}",boomkList);
		
		return "boomk/boomkList";
	}

	// 즐겨찾기 등록

	// 즐겨찾기 등록 페이지 이동

	
	// 즐겨찾기 취소
	@GetMapping("/modifyBoomk")
	public String modifyBoomk(Boomk boomk, Model model) {

		log.info("즐겨찾기 취소:::::::: {}", boomk);
		
		boomkService.modifyBoomk(boomk);
		
		return "redirect:/boomk/boomkList";
	}
	

	
	
	
	
	
	
	
	
	
	
	
}
