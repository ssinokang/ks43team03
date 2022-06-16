package ks43team03.service;


import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import ks43team03.common.FileUtils;
import ks43team03.dto.Area;
import ks43team03.dto.AreaCity;
import ks43team03.dto.AreaCityTown;
import ks43team03.dto.Facility;
import ks43team03.dto.FacilityUse;
import ks43team03.dto.MainCtg;
import ks43team03.mapper.AdminFacilityMapper;
import ks43team03.mapper.FileMapper;


@Service
@Transactional
public class AdminFacilityService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	private final AdminFacilityMapper adminFacilityMapper;
	private final FileMapper fileMapper;
	
	public AdminFacilityService(AdminFacilityMapper adminFacilityMapper, FileMapper fileMapper) {
		this.adminFacilityMapper = adminFacilityMapper;
		this.fileMapper = fileMapper;
	}
	
	
	
	
	
	
	
	/*시설 검색*/
	public List<Facility> getSearchFacilityList(String searchKey, String searchValue){
		List<Facility> searchFacilityList = adminFacilityMapper.getSearchFacilityList(searchKey, searchValue);
		return searchFacilityList;
				
	}
	
	/*시설 삭제*/
	public boolean removeFacility(String FacilityCd) {
		
		boolean facilityCheck = false;
		
		return facilityCheck;
		
	}
	
	
	
	
	/*시설 수정 */
	public int modifyFacility(Facility facility) {
		return adminFacilityMapper.modifyFacility(facility);
	}
	
	
	
	/*시설등록*/
	public int addFacility(Facility facility, MultipartHttpServletRequest mhsr) {
		/* facility.setUserId(sessionId); */
		int result = adminFacilityMapper.addFacility(facility);
		
		if (Objects.nonNull(mhsr)) {
			FileUtils fu = new FileUtils(mhsr, facility.getUserId());
			List<Map<String, String>> dtoFileList = fu.parseFileInfo();
			// 1. t_file 테이블에 삽입
			System.out.println(dtoFileList + "LessonService/addLesson");
			fileMapper.uploadFile(dtoFileList);
			
			/***
			 * test code: start
			 ***/
			
			//facilityGoodsMapper.getFacilityGoods(lesson.getLessonCd());
			
			/***
			 * test code: end
			 ***/
			// 2. lesson 테이블에 삽입
			//lessonMapper.addLesson(lesson);
			System.out.println(facility + "AdminFacilityService/addFacility/Facility");
			
			// 3. 릴레이션 테이블에 삽입
			/*
			String facilityGoodsCd = "test";
			for(Map<String, String> m : dtoFileList) {
				m.put("facilityGoodsCd", facilityGoodsCd);
				fileMapper.uploadRelationFile(m);
			}
			*/
			
			
			/****************
			 *				*
			 *  log 찍어보기	*
			 * 				*
			 ****************/
			
	    }
		return result;
	}
		
		
	
	
	
	/*아이디별상세정보조회*/
	public List<Facility> getAdminFacilityListById(String userId) {
		List<Facility> adminFacilityListById = adminFacilityMapper.getAdminFacilityListById(userId);
		
			if(adminFacilityListById != null) {
			
			//향상된 for문
			for(Facility facility : adminFacilityListById) {
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
				
		return adminFacilityListById;
	}
	
	
	
	/*시설코드별상세정보조회*/
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
