package ks43team03.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import ks43team03.dto.TrainerProfile;
import ks43team03.dto.User;
import ks43team03.mapper.TrainerMapper;
import ks43team03.mapper.UserMapper;

@Service
public class TrainerService {
	
	private final TrainerMapper trainerMapper;
	private final UserMapper userMapper;
	
	public TrainerService(TrainerMapper trainerMapper, UserMapper userMapper) {
		this.trainerMapper = trainerMapper;
		this.userMapper = userMapper;
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
	 * 트레이너 등록 및 권한 변경 후 코드 반환
	 */
	public String addtrainer(TrainerProfile trainerProfile) {
		
		trainerMapper.addtrainer(trainerProfile);
		
		String userId = trainerProfile.getUserId();
		
		User user = userMapper.getUserInfoById(userId);
		
		int userLevel = Integer.parseInt(user.getUserLevel());
		if(userLevel > 4) trainerMapper.modifyUserLevel(userId);
		
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
