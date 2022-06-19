package ks43team03.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.AreaCityTown;
import ks43team03.dto.Facility;
import ks43team03.dto.Sports;
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
	
	
	
	/*구장단가등록*/
	public int addStadiumPrice(StadiumPrice stadiumPrice) {
		/* facility.setUserId(sessionId); */
		int result = stadiumMapper.addStadiumPrice(stadiumPrice);
		return result;
	}
	
	/*구장등록*/
	public int addStadium(Stadium stadium) {
		/* facility.setUserId(sessionId); */
		int result = stadiumMapper.addStadium(stadium);
		return result;
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
