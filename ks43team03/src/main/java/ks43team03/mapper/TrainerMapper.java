package ks43team03.mapper;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.TrainerProfile;

@Mapper
public interface TrainerMapper {
	
	//트레이너 등록
	public int addUser(TrainerProfile trainerProfile);
	
	//회원 아이디 중복 체크
	public boolean isNicknameCheck(String trainerNickname);
}
