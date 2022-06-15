package ks43team03.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ks43team03.service.TrainerService;

@Controller
@RequestMapping("/trainer")
public class TrainerController {
	
	private final TrainerService trainerService;
	
	public TrainerController(TrainerService trainerService) {
		this.trainerService = trainerService;
	}
	
	private static final Logger log = LoggerFactory.getLogger(TrainerController.class);

	// 아이디 중복체크 여부
	@PostMapping("/nicknameCheck")
	@ResponseBody
	public boolean isIdCheck(@RequestParam(value = "trainerNickname") String trainerNickname) {
		boolean nicknameCheck = false;
		log.info("아이디중복체크 클릭시 요청받은 userId의 값: {}", trainerNickname);
		
		boolean result = trainerService.isNicknameCheck(trainerNickname);
		if(result) nicknameCheck = true;
		
		log.info("아이디중복체크 여부 : {}", result);
		
		return nicknameCheck;
	}
		
	//트레이너 등록 페이지 이동
	@GetMapping("/addTrainer")
	public String addTrainer(Model model
							,HttpSession session) {
		
		String sessionId = (String)session.getAttribute("SID");
		
		model.addAttribute("sessionId", sessionId);
		
		return "trainer/addTrainer";
	}
}
