package ks43team03.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ks43team03.dto.TrainerCareer;
import ks43team03.dto.TrainerLicense;
import ks43team03.dto.TrainerProfile;
import ks43team03.dto.User;
import ks43team03.mapper.TrainerMapper;
import ks43team03.mapper.UserMapper;

@Service
public class TrainerService {
	
	
	private static final Logger log = LoggerFactory.getLogger(TrainerService.class);

	
	private final TrainerMapper trainerMapper;
	private final UserMapper userMapper;
	
	public TrainerService(TrainerMapper trainerMapper, UserMapper userMapper) {
		this.trainerMapper = trainerMapper;
		this.userMapper = userMapper;
	}
	
	/**
	 * 트레이너 프로필 수정
	 */
	public int modifyTrainerProfile(TrainerProfile trainerProfile) {
		return 0;
	}
	
	/**
	 * 트레이너 코드로 트레이너 프로필 조회
	 */
	public TrainerProfile getTrainerProfileByTrainerCd(String trainerCd) {
		
		TrainerProfile	trainerProfile	= trainerMapper.getTrainerProfileInfoByTrainerCd(trainerCd);
		
		return trainerProfile;
	}
	
	/**
	 * 트레이너 코드로 트레이너 정보 조회
	 * @param trainerCd
	 * @return
	 */
	public Map<String, Object> getTrainerInfoByTrainerCd(String trainerCd) {
		
		Map<String, Object> trainerMap = new HashMap<String, Object>();
		TrainerProfile	trainerProfile	= trainerMapper.getTrainerProfileInfoByTrainerCd(trainerCd);
		TrainerProfile	trainerCareer	= trainerMapper.getTrainerCareerInfoByTrainerCd(trainerCd);
		TrainerProfile	trainerLicense	= trainerMapper.getTrainerLicenseInfoByTrainerCd(trainerCd);
		
		trainerMap.put("trainerProfile",	trainerProfile);
		trainerMap.put("trainerCareer",		trainerCareer);
		trainerMap.put("trainerLicense",	trainerLicense);
		
		return trainerMap;
	}
	
	/**
	 * 트레이너 리스트 조회
	 * @return
	 */
	public List<TrainerProfile> getTrainerList() {
		
		List<TrainerProfile> trainerList = trainerMapper.getTrainerList();
		
		return trainerList;
	}
	
	/**
	 * 트레이너 자격증 등록
	 */
	public int addTrainerLicense(List<TrainerLicense> trainerLicenseList) {
		
		int result = trainerMapper.addTrainerLicense(trainerLicenseList);
		log.info("result : {}", result);
		
		return result;
	}

	/**
	 * 트레이너 경력 등록
	 */
	public int addTrainerCareer(List<TrainerCareer> trainerCareerList) {
		
		int result = trainerMapper.addTrainerCareer(trainerCareerList);
		log.info("result : {}", result);
		
		return result;
	}
	
	/**
	 * 트레이너 등록 및 권한 변경 후 코드 반환
	 */
	public String addtrainer(TrainerProfile trainerProfile) {
		
		// 트레이너 등록 - 트레이너 코드 생성됨
		trainerMapper.addtrainer(trainerProfile);
		
		String userId = trainerProfile.getUserId();
		
		// userId로 User 맴버 확인
		User user = userMapper.getUserInfoById(userId);
		
		int userLevel = Integer.parseInt(user.getUserLevel());
		// User맴버에서 권한 변경
		if(userLevel > 4) trainerMapper.modifyUserLevel(userId);
		
		//트레이너 코드 등록된 트레이너 정보 조회
		trainerProfile = trainerMapper.getTrainerProfileInfoByUserId(userId);
		
		String trainerCd = trainerProfile.getTrainerCd();
		
		return trainerCd;
	}
	
	/**
	 * 닉네임 중복 체크
	 * @param userId
	 * @return
	 */
	public boolean isNicknameCheck(String trainerNickname) {
		
		boolean result = trainerMapper.isNicknameCheck(trainerNickname);
		
		return result;
	}
}
