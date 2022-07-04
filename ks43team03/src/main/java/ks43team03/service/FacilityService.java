package ks43team03.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.Facility;
import ks43team03.dto.FacilityUser;
import ks43team03.dto.Lesson;
import ks43team03.dto.Review;
import ks43team03.dto.Stadium;
import ks43team03.mapper.FacilityMapper;
import ks43team03.mapper.StadiumMapper;


@Service
@Transactional
public class FacilityService {

	private static final Logger log = LoggerFactory.getLogger(FacilityService.class);
	
	private final FacilityMapper facilityMapper;
	private final StadiumMapper stadiumMapper;
	
	
	public FacilityService(FacilityMapper facilityMapper, StadiumMapper stadiumMapper) {
		this.facilityMapper = facilityMapper;
		this.stadiumMapper = stadiumMapper;
		
	}

	/*시설후기갯수*/
	public int getReviewCountByCd(String facilityCd) {
		int reviewCount = facilityMapper.getReviewCountByCd(facilityCd);
		return reviewCount;
	}
	
	/*시설 후기*/
	public List<Review> getReviewList(String facilityCd){
		List<Review> reviewList = facilityMapper.getReviewList(facilityCd);
		return reviewList;
	}
	
	/*시설 가입 중복 체크*/
	public boolean isUserCheck(String userId, String facilityCd) {
		boolean result = facilityMapper.isUserCheck(userId, facilityCd);
		return result;
	}
	
	
	
	/*시설에 회원 가입*/
	public int addFacilityUser(FacilityUser facilityUser) {
		int result = facilityMapper.addFacilityUser(facilityUser);
		
		return result;
	}
	
	
	/*시설 내 구장 목록*/
	public List<Stadium> getStadiumListByCd(String facilityCd) {
		List<Stadium> stadiumListByCd = stadiumMapper.getStadiumListByCd(facilityCd);
		
		log.info("stadiumListByCd", stadiumListByCd);
		return stadiumListByCd;
	}
	
	/*시설 내 레슨 목록*/
	public List<Lesson> getLessonList(String facilityCd) {
		List<Lesson> lessonList = facilityMapper.getLessonList(facilityCd);

		return lessonList;	
		}
	
	/*시설 상세 정보 조회*/
	public Facility getFacilityDetail(String facilityCd) {
		Facility facilityDetail = facilityMapper.getFacilityInfoByCd(facilityCd);

		
		return facilityDetail;
	}
	
	

	/*시설조회*/
	public Map<String, Object> getFacilityList(int currentPage){
		
		int rowPerPage = 9;
		int startPageNum = 1;
		int endPageNum = 10;
		
		double rowCount = facilityMapper.getFacilityCount();
		
		int lastPage = (int)Math.ceil(rowCount/rowPerPage);
		
		int startRow = (currentPage - 1) * rowPerPage;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("startRow", startRow);
		paramMap.put("rowPerPage", rowPerPage);
		
	
		
		if(lastPage > 10) {
			if(currentPage >= 6) {
				startPageNum = currentPage - 4;
				endPageNum = currentPage + 5;
				
				if(endPageNum >= lastPage) {
					startPageNum = lastPage - 9;
					endPageNum = lastPage;
				}
			}
		}else {
			endPageNum = lastPage;
		}
		List<Map<String, Object>> facilityList = facilityMapper.getFacilityList(paramMap);
		
		log.info("paramMap : {}", paramMap);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lastPage", 			lastPage);
		resultMap.put("facilityList",	facilityList);
		resultMap.put("startPageNum",		startPageNum);
		resultMap.put("endPageNum",			endPageNum);
		return resultMap;

	
	}

	
	
	
	
	
	
	
	
}
