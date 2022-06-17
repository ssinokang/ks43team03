package ks43team03.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.Area;
import ks43team03.dto.AreaCity;
import ks43team03.dto.AreaCityTown;
import ks43team03.dto.Facility;
import ks43team03.dto.FacilityUse;
import ks43team03.dto.MainCtg;
import ks43team03.mapper.FacilityMapper;


@Service
@Transactional
public class FacilityService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	private final FacilityMapper facilityMapper;
	
	public FacilityService(FacilityMapper facilityMapper) {
		this.facilityMapper = facilityMapper;
	}


	
	/*시설 상세 정보 조회*/
	public Facility getFacilityDetail(String facilityCd) {
		Facility facilityDetail = facilityMapper.getFacilityInfoByCd(facilityCd);
		

		return facilityDetail;
	}
	
	
	
	/*시도조회*/
	public List<Area> getAreaList(){
		List<Area> areaList = facilityMapper.getAreaList();
		
		return areaList;
	}
	/*시군구 조회*/
	public List<AreaCity> getAreaCityList(){
		List<AreaCity> areaCityList = facilityMapper.getAreaCityList();
		
		return areaCityList;
	}
	/*읍면동 조회*/
	public List<AreaCityTown> getAreaCityTownList(){
		List<AreaCityTown> areaCityTownList = facilityMapper.getAreaCityTownList();
		
		return areaCityTownList;
	}
	
	
	/*시설메인카테고리 조회*/
	public List<MainCtg> getMainCtgList(){
		List<MainCtg> mainCtgList = facilityMapper.getMainCtgList();
		
		return mainCtgList;
	}
	
	/*시설용도조회*/
	public List<FacilityUse> getFacilityUseList(){
		List<FacilityUse> facilityUseList = facilityMapper.getFacilityUseList();
		
		return facilityUseList;
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
