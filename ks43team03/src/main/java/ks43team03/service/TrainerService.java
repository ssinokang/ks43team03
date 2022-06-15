package ks43team03.service;

import org.springframework.stereotype.Service;

import ks43team03.mapper.TrainerMapper;

@Service
public class TrainerService {
	
	private final TrainerMapper trainerMapper;
	
	public TrainerService(TrainerMapper trainerMapper) {
		this.trainerMapper = trainerMapper;
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
