package ks43team03.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	// 즐겨찾기 내역 조회
	/*
	 * @GetMapping("/boomkList") public String boomkList(Model
	 * model, @RequestParam(name = "userId", required = false) String userId,
	 * HttpSession session) {
	 * 
	 * userId = "id010"; Boomk boomk = boomkService.getBoomkInfoById(userId);
	 * 
	 * log.info("회원 아이디 : {}", userId);
	 * 
	 * model.addAttribute("title", "회원"); model.addAttribute("boomk", boomk);
	 * 
	 * session.setAttribute("SID" , userId);
	 * 
	 * return "boomk/boomkList"; }
	 */

	// 즐겨찾기 내역 조회
	@GetMapping("/boomkList")
	public String getBoomkInfoById(Model model, @RequestParam(name = "userId", required = false) String userId) {

		/* userId = [[${session.SID]]; */
		Boomk boomk = boomkService.getBoomkInfoById(userId);

		log.info("즐겨찾기 내역 조회 아이디 : {}", userId);

		model.addAttribute("title", "회원정보");
		model.addAttribute("boomk", boomk);

		return "boomk/boomkList";
	}

	// 즐겨찾기 등록

	// 즐겨찾기 등록 페이지 이동

}
