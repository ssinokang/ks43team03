package ks43team03.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.TrainerProfile;

@Mapper
public interface TrainerMapper {
	
	//아이디로 트레이너 정보 조회
	public TrainerProfile getTrainerInfoById(String userId);
	
	//트레이너 리스트
	public List<TrainerProfile> getTrainerList();
	
	//트레이너 등록
	public int addtrainer(TrainerProfile trainerProfile);
	
	//회원 아이디 중복 체크
	public boolean isNicknameCheck(String trainerNickname);
}
