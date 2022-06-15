package ks43team03.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.Area;
import ks43team03.dto.AreaCity;
import ks43team03.dto.AreaCityTown;
import ks43team03.dto.Facility;
import ks43team03.dto.FacilityUse;
import ks43team03.dto.MainCtg;
import ks43team03.mapper.AdminFacilityMapper;


@Service
@Transactional
public class AdminFacilityService {

	private final AdminFacilityMapper adminFacilityMapper;
	
	public AdminFacilityService(AdminFacilityMapper adminFacilityMapper) {
		this.adminFacilityMapper = adminFacilityMapper;
	}
	
	
	/*시설 수정 */
	public int modifyFacility(Facility facility) {
		return adminFacilityMapper.modifyFacility(facility);
	}
	
	
	
	/*시설등록*/
	public int addFacility(Facility facility) {
		int result = adminFacilityMapper.addFacility(facility);
		return result;
	}
	
	
	/*시설상세정보조회*/
	public Facility getAdminFacilityInfoByCd(String facilityCd) {
		Facility facility = adminFacilityMapper.getAdminFacilityInfoByCd(facilityCd);
		
		return facility;
	}
	
	/*시도조회*/
	public List<Area> getAreaList(){
		List<Area> areaList = adminFacilityMapper.getAreaList();
		
		return areaList;
	}
	/*시군구 조회*/
	public List<AreaCity> getAreaCityList(){
		List<AreaCity> areaCityList = adminFacilityMapper.getAreaCityList();
		
		return areaCityList;
	}
	/*읍면동 조회*/
	public List<AreaCityTown> getAreaCityTownList(){
		List<AreaCityTown> areaCityTownList = adminFacilityMapper.getAreaCityTownList();
		
		return areaCityTownList;
	}
	
	
	/*시설메인카테고리 조회*/
	public List<MainCtg> getMainCtgList(){
		List<MainCtg> mainCtgList = adminFacilityMapper.getMainCtgList();
		
		return mainCtgList;
	}
	
	/*시설용도조회*/
	public List<FacilityUse> getFacilityUseList(){
		List<FacilityUse> facilityUseList = adminFacilityMapper.getFacilityUseList();
		
		return facilityUseList;
	}
	
	/*시설조회*/
	public List<Facility> getAdminFacilityList(){
		List<Facility> adminFacilityList = adminFacilityMapper.getAdminFacilityList();
		
		if(adminFacilityList != null) {
			
			//향상된 for문
			for(Facility facility : adminFacilityList) {
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
		
		
		return adminFacilityList;
		
	}
	
	
	
	
}
