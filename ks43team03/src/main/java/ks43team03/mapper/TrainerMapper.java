package ks43team03.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.TrainerCareer;
import ks43team03.dto.TrainerLicense;
import ks43team03.dto.TrainerProfile;

@Mapper
public interface TrainerMapper {
	
	//트레이너 정보 수정
	public int modifyTrainerProfile(TrainerProfile trainerProfile);
	
	//트레이너 코드로 트레이너 자격증 조회
	public TrainerProfile getTrainerLicenseInfoByTrainerCd(String trainerCd);
	//트레이너 코드로 트레이너 경력 조회
	public TrainerProfile getTrainerCareerInfoByTrainerCd(String trainerCd);
	//트레이너 코드로 트레이너 정보 조회
	public TrainerProfile getTrainerProfileInfoByTrainerCd(String trainerCd);
	
	//트레이너 리스트
	public List<TrainerProfile> getTrainerList();
	
	//트레이너 자격증 등록
	public int addTrainerLicense(List<TrainerLicense> trainerLicenseList);
	
	//트레이너 경력 등록
	public int addTrainerCareer(List<TrainerCareer> trainerCareerList);
	
	//아이디로 트레이너 정보 조회
	public TrainerProfile getTrainerProfileInfoByUserId(String userId);
	
	//회원 권한 업데이트
	public int modifyUserLevel(String userId);
	
	//트레이너 등록
	public int addtrainer(TrainerProfile trainerProfile);
	
	//회원 아이디 중복 체크
	public boolean isNicknameCheck(String trainerNickname);
}
