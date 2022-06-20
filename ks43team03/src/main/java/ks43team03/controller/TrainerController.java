package ks43team03.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ks43team03.dto.TrainerCareer;
import ks43team03.dto.TrainerLicense;
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
	
	//트레이너 정보 수정 페이지 이동
	@GetMapping("/modifyTrainer")
	public String modifyTrainer(Model model
							   ,@RequestParam (value = "trainerCd", required = false)String trainerCd) {
		
		log.info("트레이너 정보조회 코드 : {}", trainerCd);
		
		TrainerProfile trainerProfile = trainerService.getTrainerProfileByTrainerCd(trainerCd);
		
		model.addAttribute("trainerProfile", trainerProfile);
		model.addAttribute("title", "트레이너 정보 수정");
		
		return "trainer/modifyTrainer";
	}
	
	//프로필 조회 페이지 이동
	@GetMapping("/trainerDetail")
	public String getTrainerDetail(Model model
								  ,@RequestParam (value = "trainerCd")String trainerCd) {
		
		
		log.info("트레이너 정보조회 코드 : {}", trainerCd);
		
		Map<String, Object> trainerMap = trainerService.getTrainerInfoByTrainerCd(trainerCd);
		
		
		log.info("트레이너 정보 : {}",		trainerMap.get("trainerProfile"));
		log.info("트레이너 경력 정보 : {}",	trainerMap.get("trainerCareer"));
		log.info("트레이너 자격증 정보 : {}",	trainerMap.get("trainerLicense"));
		
		model.addAttribute("trainerProfile",	trainerMap.get("trainerProfile"));
		model.addAttribute("trainerCareer",		trainerMap.get("trainerCareer"));
		model.addAttribute("trainerLicense",	trainerMap.get("trainerLicense"));
		model.addAttribute("title",				"트레이너 정보");
		
		return "trainer/trainerDetail";
	}
	
	//트레이너 리스트 페이지 이동
	@GetMapping("/trainerList")
	public String getTrainerList(Model model) {
		
		List<TrainerProfile> trainerList = trainerService.getTrainerList();
		
		model.addAttribute("trainerList",	trainerList);
		model.addAttribute("title",			"트레이너 리스트");
		
		return "trainer/trainerList";
	}
	
	// 닉네임 중복체크 여부
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
	
	//자격증 등록
	@ResponseBody
	@PostMapping("/addLicense")
	public boolean addLicense(@RequestBody List<TrainerLicense> trainerLicense) {
		
		log.info("trainerCareer : {}",trainerLicense);
		//값만 받아옴 
		return true;
	}
	
	//자격증 등록 페이지 이동
	@GetMapping("/addLicense")
	public String addLicense(Model model) {
		
		model.addAttribute("title", 	"자격증 등록");
		
		return "trainer/addLicense";
	}
	
	//경력 등록
	@ResponseBody
	@PostMapping("/addCareer")
	public boolean addCareer(@RequestBody List<TrainerCareer> trainerCareer) {
		
		log.info("trainerCareer : {}",trainerCareer);
		//값만 받아옴 
		return true;
	}
	
	//경력 등록 페이지 이동
	@GetMapping("/addCareer")
	public String addCareer(Model model
						   ,@RequestParam(value = "trainerCd") String trainerCd) {
		
		model.addAttribute("trainerCd", trainerCd);
		model.addAttribute("title", 	"경력 등록");
		
		return "trainer/addCareer";
	}
	
	//트레이너 등록
	@PostMapping("/addTrainer")
	public String addTrainer(TrainerProfile trainerProfile
							,RedirectAttributes reAttr) {
		
		log.info("trainerProfile : {}",trainerProfile);
		
		String trainerCd = trainerService.addtrainer(trainerProfile);
		log.info("trainerCd : {}",trainerCd);
		
		reAttr.addAttribute("trainerCd", trainerCd);
		
		return "redirect:/trainer/addCareer";
	}	
	
	//트레이너 등록 페이지 이동
	@GetMapping("/addTrainer")
	public String addTrainer(Model model) {
		
		model.addAttribute("title", 	"트레이너 등록");
		
		return "trainer/addTrainer";
	}
}
