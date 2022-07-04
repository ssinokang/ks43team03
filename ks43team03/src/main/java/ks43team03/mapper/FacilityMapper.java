package ks43team03.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


import ks43team03.dto.Facility;
import ks43team03.dto.FacilityUser;
import ks43team03.dto.Lesson;
import ks43team03.dto.Review;



@Mapper
public interface FacilityMapper {
	
	//시설 후기 갯수
	public int getReviewCountByCd(String facilityCd);
	
	//시설 후기 
	public List<Review> getReviewList(String facilityCd);

	//시설에 가입한 회원 체크
	public boolean isUserCheck(String userId, String facilityCd);
	
	//시설에 회원 가입
	public int addFacilityUser(FacilityUser facilityUser);
	
	//시설 페이지 내에서 보여줄 레슨 목록
	public List<Lesson> getLessonList(String facilityCd);
	
	//시설 상세 정보
	public Facility getFacilityInfoByCd(String facilityCd);
	
	//사용자가 시설 조회
	public List<Map<String, Object>> getFacilityList(Map<String, Object> paramMap);
	
	//시설 테이블 총 row(튜플) 수
	public int getFacilityCount();
	
}
