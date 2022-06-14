package ks43team03.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ks43team03.dto.Facility;
import ks43team03.dto.FacilityUse;
import ks43team03.dto.MainCtg;
import ks43team03.mapper.AdminFacilityMapper;


@Service
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
	
	
	/*시설메인카테고리 조회*/
	public List<MainCtg> getMainCtgList(){
		List<MainCtg> mainCtgList = adminFacilityMapper.getMainCtgList();
		
		return mainCtgList;
	}
	
	/*시설용도조회*/
	public List<FacilityUse> getFacilityUserList(){
		List<FacilityUse> facilityUserList = adminFacilityMapper.getFacilityUserList();
		
		return facilityUserList;
	}
	
	/*시설조회*/
	public List<Facility> getAdminFacilityList(){
		List<Facility> adminFacilityList = adminFacilityMapper.getAdminFacilityList();
		
		return adminFacilityList;
	}
	
	
	
	
}
