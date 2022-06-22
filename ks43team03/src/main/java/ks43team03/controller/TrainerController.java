package ks43team03.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
	
	//자격증 수정 페이지 이동
	@GetMapping("/modifyLicense")
	public String modifyLicense(Model model
							   ,@RequestParam(name = "trainerLicenseCd") String trainerLicenseCd) {
		
		TrainerProfile trainerLicense = trainerService.getTrainerLicenseByTrainerLicenseCd(trainerLicenseCd);
		log.info("trainerLicense : {}", trainerLicense);
		
		model.addAttribute("trainerLicense",	trainerLicense);		
		model.addAttribute("title",				"자격증 수정");
		
		return "trainer/modifyLicense";
	}
	
	//경력 수정 페이지 이동
	@GetMapping("/modifyCareer")
	public String modifyCareer(Model model
							  ,@RequestParam(name = "trainerCareerCd") String trainerCareerCd) {
		
		TrainerProfile trainerCareer = trainerService.getTrainerCareerByTrainerCareerCd(trainerCareerCd);
		log.info("trainerCareer : {}", trainerCareer);
		
		model.addAttribute("trainerCareer",	trainerCareer);	
		model.addAttribute("title",			"경력 수정");
		
		return "trainer/modifyCareer";
	}
	
	//트레이너 정보 수정
	@PostMapping("/modifyTrainer")
	public String modifyTrainer(TrainerProfile trainerProfile
							   ,HttpSession session) {
		
		String userId = (String)session.getAttribute("SID");
		trainerProfile.setUserId(userId);
		
		log.info("trainerProfile : {}", trainerProfile);
		trainerService.modifyTrainerProfile(trainerProfile);
		
		return "redirect:/trainer/modifyTrainer";
	}
	
	//트레이너 정보 수정 페이지 이동
	@GetMapping("/modifyTrainer")
	public String modifyTrainer(Model model
							   ,HttpSession session) {
		
		String userId = (String)session.getAttribute("SID");
		
		log.info("트레이너 정보조회 코드 : {}", userId);
		
		TrainerProfile trainerProfile = trainerService.getTrainerProfileByUserId(userId);
		
		model.addAttribute("trainerProfile",	trainerProfile);
		model.addAttribute("title",				"트레이너 정보 수정");
		
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
		log.info("닉네임중복체크 클릭시 요청받은 userId의 값: {}", trainerNickname);
		
		boolean result = trainerService.isNicknameCheck(trainerNickname);
		if(result) nicknameCheck = true;
		
		log.info("닉네임중복체크 여부 : {}", result);
		
		return nicknameCheck;
	}
	
	//자격증 등록
	@ResponseBody
	@PostMapping("/addLicense")
	public boolean addLicense(@RequestBody List<TrainerLicense> trainerLicenseList) {
		
		boolean addLicenseCheck = false;
		
		log.info("trainerLicenseList : {}",trainerLicenseList);
		
		int result = trainerService.addTrainerLicense(trainerLicenseList);
		
		if(result>0) addLicenseCheck = true;
		
		return addLicenseCheck;
	}
	
	//자격증 등록 페이지 이동
	@GetMapping("/addLicense")
	public String addLicense(Model model
							,@RequestParam(value = "trainerCd") String trainerCd) {
		
		model.addAttribute("trainerCd", trainerCd);
		model.addAttribute("title", 	"자격증 등록");
		
		return "trainer/addLicense";
	}
	
	//경력 등록
	@ResponseBody
	@PostMapping("/addCareer")
	public boolean addCareer(@RequestBody List<TrainerCareer> trainerCareerList) {
		
		boolean addCareerCheck = false;
		
		log.info("trainerCareerList : {}",trainerCareerList);
		
		int result = trainerService.addTrainerCareer(trainerCareerList);
		
		if(result > 0) addCareerCheck = true;
		
		return addCareerCheck;
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
