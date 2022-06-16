package ks43team03.controller;

import java.util.Objects;

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

import ks43team03.dto.TrainerProfile;
import ks43team03.service.TrainerService;

@Controller
@RequestMapping("/trainer")
public class TrainerController {
	
	private final TrainerService trainerService;
	
	public TrainerController(TrainerService trainerService) {
		this.trainerService = trainerService;
	}
	
	private static final Logger log = LoggerFactory.getLogger(TrainerController.class);
	
	//프로필 조회 페이지 이동
	@GetMapping("/trainerDetail")
	public String getUserDetail(Model model
							   ,HttpSession session) {
		
		String sessionId = (String)session.getAttribute("SID");
		
		log.info("트레이너 정보조회 아이디 : {}", sessionId);
		
		model.addAttribute("title", "트레이너 정보");
		
		if(Objects.isNull(sessionId)) {
			
			return "trainer/trainerDetail";
		}
		
		TrainerProfile tainerProfile = trainerService.getTrainerInfoById(sessionId);
		
		model.addAttribute("tainerProfile", tainerProfile);
		model.addAttribute("sessionId", sessionId);
		model.addAttribute("title", "트레이너 정보");
		
		return "trainer/trainerDetail";
	}
	
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
	
	//경력 등록 페이지 이동
	@GetMapping("/addLicense")
	public String addLicense(Model model
							,HttpSession session) {
		
		String sessionId = (String)session.getAttribute("SID");
		
		model.addAttribute("sessionId", sessionId);
		
		return "trainer/addLicense";
	}
	
	//경력 등록 페이지 이동
	@GetMapping("/addCareer")
	public String addCareer(Model model
							,HttpSession session) {
		
		String sessionId = (String)session.getAttribute("SID");
		
		model.addAttribute("sessionId", sessionId);
		
		return "trainer/addCareer";
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
