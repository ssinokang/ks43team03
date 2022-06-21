package ks43team03.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.Facility;
import ks43team03.dto.Stadium;
import ks43team03.dto.StadiumPrice;
import ks43team03.mapper.FileMapper;
import ks43team03.mapper.StadiumMapper;

@Service
@Transactional
public class StadiumService {
	
	private static final Logger log = LoggerFactory.getLogger(StadiumService.class);
	private final StadiumMapper stadiumMapper;
	private final FileMapper fileMapper;
	
	public StadiumService(StadiumMapper stadiumMapper, FileMapper fileMapper) {
		this.stadiumMapper = stadiumMapper;
		this.fileMapper = fileMapper;
	}
	
	
	/*회원이 구장 상세정보 조회*/
	public Stadium getStadiumInfoByCd(String facilityStadiumCd) {
		Stadium stadium = stadiumMapper.getStadiumInfoByCd(facilityStadiumCd);
		
		return stadium;
	}
	
	

	/*회원이 구장 전체 조회*/
	public Map<String, Object> getStadiumList(int currentPage) {
		int rowPerPage = 9;

		double rowCount = stadiumMapper.getStadiumCount();

		int lastPage = (int) Math.ceil(rowCount / rowPerPage);

		int startRow = (currentPage - 1) * rowPerPage;

		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("startRow", startRow);
		paramMap.put("rowPerPage", rowPerPage);

		int startPageNum = 1;
		int endPageNum = 10;

		if (lastPage > 10) {
			if (currentPage >= 6) {
				startPageNum = currentPage - 4;
				endPageNum = currentPage + 5;

				if (endPageNum >= lastPage) {
					startPageNum = lastPage - 9;
					endPageNum = lastPage;
				}
			}
		} else {
			endPageNum = lastPage;
		}

		log.info("paramMap : {}", paramMap);

		List<Map<String, Object>> stadiumList = stadiumMapper.getStadiumList(paramMap);

	
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lastPage", lastPage);
		resultMap.put("stadiumList", stadiumList);
		resultMap.put("startPageNum", startPageNum);
		resultMap.put("endPageNum", endPageNum);
		return resultMap;
	}	
	
	
	/*단가 수정*/
	public int modifyStadiumPrice(StadiumPrice stadiumPrice) {
		log.info("단가 수정 서비스", stadiumPrice);
		int result = stadiumMapper.modifyStadiumPrice(stadiumPrice);
		return result;
	}
	
	/*구장코드별 가격상세정보조회*/
	public StadiumPrice getAdminStadiumPriceInfoByCd(String facilityStadiumCd) {
		StadiumPrice stadiumPrice = stadiumMapper.getAdminStadiumPriceInfoByCd(facilityStadiumCd);

		return stadiumPrice;
	}
	
	/*구장 수정*/
	public int modifyStadium(Stadium stadium) {
		return stadiumMapper.modifyStadium(stadium);
	}
	
	/* 시설코드별상세정보조회 */
	public Stadium getAdminStadiumInfoByCd(String facilityStadiumCd) {
		Stadium stadium = stadiumMapper.getAdminStadiumInfoByCd(facilityStadiumCd);

		return stadium;
	}
	
	/*구장단가등록*/
	public int addStadiumPrice(StadiumPrice stadiumPrice) {
		int result = stadiumMapper.addStadiumPrice(stadiumPrice);
		return result;
	}
	
	/*구장등록*/
	public int addStadium(Stadium stadium) {
		/* facility.setUserId(sessionId); */
		int result = stadiumMapper.addStadium(stadium);
		return result;
	}
	
	/*아이디별 시설 조회*/
	public List<Facility> getFacilityListById(String userId) {
		List<Facility> facilityListById = stadiumMapper.getFacilityListById(userId);
		
		log.info("서비스", facilityListById);
		log.info("서비스", userId);
		
		return facilityListById;
		
	}
	
	/*시설 내 구장 조회*/
	public List<Stadium> getAdminStadiumListByCd(String facilityCd) {
		List<Stadium> adminStadiumListByCd = stadiumMapper.getAdminStadiumListByCd(facilityCd);
		log.info("서비스", adminStadiumListByCd);
		return adminStadiumListByCd;
		
	}

	
	
	
	/*구장 전체 조회*/
	public Map<String, Object> getAdminStadiumList(int currentPage) {
		int rowPerPage = 10;

		double rowCount = stadiumMapper.getStadiumCount();

		int lastPage = (int) Math.ceil(rowCount / rowPerPage);

		int startRow = (currentPage - 1) * rowPerPage;

		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("startRow", startRow);
		paramMap.put("rowPerPage", rowPerPage);

		int startPageNum = 1;
		int endPageNum = 10;

		if (lastPage > 10) {
			if (currentPage >= 6) {
				startPageNum = currentPage - 4;
				endPageNum = currentPage + 5;

				if (endPageNum >= lastPage) {
					startPageNum = lastPage - 9;
					endPageNum = lastPage;
				}
			}
		} else {
			endPageNum = lastPage;
		}

		log.info("paramMap : {}", paramMap);

		List<Map<String, Object>> adminStadiumList = stadiumMapper.getAdminStadiumList(paramMap);

	
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lastPage", lastPage);
		resultMap.put("adminStadiumList", adminStadiumList);
		resultMap.put("startPageNum", startPageNum);
		resultMap.put("endPageNum", endPageNum);
		return resultMap;
	}





}
