package ks43team03.service;

import java.util.List;

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
	public List<Facility> getFacilityList(){
		List<Facility> FacilityList = facilityMapper.getFacilityList();
		
		if(FacilityList != null) {
			
			//향상된 for문
			for(Facility facility : FacilityList) {
				String mainCtg = facility.getMainCtgCd();
				if(mainCtg != null){
					if("gg".equals(mainCtg)) {
						facility.setMainCtgCd("공공시설");
					}else if("ss".equals(mainCtg)) {
						facility.setMainCtgCd("사설시설");		
					}
				}
			}
		}
		
		return FacilityList;
		
	}

	
	
	
	
	
	
	
	
}
