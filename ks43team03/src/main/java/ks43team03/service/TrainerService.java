package ks43team03.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ks43team03.dto.TrainerProfile;
import ks43team03.mapper.TrainerMapper;

@Service
public class TrainerService {
	
	private final TrainerMapper trainerMapper;
	
	public TrainerService(TrainerMapper trainerMapper) {
		this.trainerMapper = trainerMapper;
	}
	
	
	/**
	 * 아이디로 트레이너 정보 조회
	 * @param userId
	 * @return
	 */
	public TrainerProfile getTrainerInfoById(String userId) {
		
		TrainerProfile trainerProfile = trainerMapper.getTrainerInfoById(userId);
		
		return trainerProfile;
	}
	
	public List<TrainerProfile> getTrainerList() {
		
		List<TrainerProfile> trainerList = trainerMapper.getTrainerList();
		
		return trainerList;
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
