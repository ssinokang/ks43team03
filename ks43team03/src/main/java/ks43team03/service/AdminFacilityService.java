package ks43team03.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

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

	private static final Logger log = LoggerFactory.getLogger(AdminFacilityService.class);
	private final AdminFacilityMapper adminFacilityMapper;
	private final FileMapper fileMapper;

	public AdminFacilityService(AdminFacilityMapper adminFacilityMapper, FileMapper fileMapper) {
		this.adminFacilityMapper = adminFacilityMapper;
		this.fileMapper = fileMapper;
	}
	
	

	/* 시설 검색 */
	public Map<String, Object> getSearchFacilityList(String searchKey, String searchValue, int currentPage) {
		int rowPerPage = 10;

		double rowCount = adminFacilityMapper.getFacilityCount();

		int lastPage = (int) Math.ceil(rowCount / rowPerPage);

		int startRow = (currentPage - 1) * rowPerPage;

		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("startRow",	startRow);
		paramMap.put("rowPerPage",	rowPerPage);
		paramMap.put("searchKey",	searchKey);
		paramMap.put("searchValue",	searchValue);

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

		List<Map<String, Object>> adminFacilityList = adminFacilityMapper.getSearchFacilityList(paramMap);

		if (adminFacilityList != null) {
			// 향상된 for문
			for (Map<String, Object> facility : adminFacilityList) {
				String mainCtgCd = facility.get("mainCtgCd").toString();
				if (mainCtgCd != null) {
					if ("gg".equals(mainCtgCd)) {
						facility.put("mainCtgCd", "공공시설");
					} else if ("ss".equals(mainCtgCd)) {
						facility.put("mainCtgCd", "사설시설");
					}
				}
			}

		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lastPage", lastPage);
		resultMap.put("adminFacilityList", adminFacilityList);
		resultMap.put("startPageNum", startPageNum);
		resultMap.put("endPageNum", endPageNum);
		return resultMap;
	}

	/* 시설 삭제 */
	public boolean removeFacility(String FacilityCd) {

		boolean facilityCheck = false;

		return facilityCheck;

	}

	/* 시설 수정 */
	public int modifyFacility(Facility facility) {
		return adminFacilityMapper.modifyFacility(facility);
	}

	/* 시설등록 */
	public void addFacility(Facility facility, MultipartFile[] uploadfile, String fileRealPath) {
		
		//파일이 널이 아니라면
		if (!ObjectUtils.isEmpty(uploadfile)) {
			String uproaderId 	= facility.getUserId();
	
			/***
			 * test code: start
			 ***/
			
			FileUtils fu = new FileUtils(uploadfile, uproaderId, fileRealPath);
			List<Map<String, String>> dtoFileList = fu.parseFileInfo();
			// 1. t_file 테이블에 삽입
			System.out.println(dtoFileList + "AdminFacilityService/addFacility");
			fileMapper.uploadFile(dtoFileList);
			/***
			 * test code: end
			 ***/
			// 2. facility 테이블에 삽입

			System.out.println(facility + "AdminFacilityService/addFacility/facility");
			adminFacilityMapper.addFacility(facility);

	
	    }

	}

	/* 아이디별상세정보조회 */
	public List<Facility> getAdminFacilityListById(String userId) {
		List<Facility> adminFacilityListById = adminFacilityMapper.getAdminFacilityListById(userId);

		if (adminFacilityListById != null) {

			// 향상된 for문
			for (Facility facility : adminFacilityListById) {
				String mainCtg = facility.getMainCtgCd();
				if (mainCtg != null) {
					if ("gg".equals(mainCtg)) {
						facility.setMainCtgCd("공공시설");
					} else if ("ss".equals(mainCtg)) {
						facility.setMainCtgCd("사설시설");
					}
				}

			}
		}

		return adminFacilityListById;
	}

	/* 시설코드별상세정보조회 */
	public Facility getAdminFacilityInfoByCd(String facilityCd) {
		Facility facility = adminFacilityMapper.getAdminFacilityInfoByCd(facilityCd);

		return facility;
	}

	/* 시도조회 */
	public List<Area> getAreaList() {
		List<Area> areaList = adminFacilityMapper.getAreaList();

		return areaList;
	}

	/* 시군구 조회 */
	public List<AreaCity> getAreaCityList(String areaCd) {
		List<AreaCity> areaCityList = adminFacilityMapper.getAreaCityList(areaCd);

		return areaCityList;
	}

	/* 읍면동 조회 */
	public List<AreaCityTown> getAreaCityTownList(String cityCd) {
		List<AreaCityTown> areaCityTownList = adminFacilityMapper.getAreaCityTownList(cityCd);

		return areaCityTownList;
	}

	/* 시설메인카테고리 조회 */
	public List<MainCtg> getMainCtgList() {
		List<MainCtg> mainCtgList = adminFacilityMapper.getMainCtgList();

		return mainCtgList;
	}

	/* 시설용도조회 */
	public List<FacilityUse> getFacilityUseList() {
		List<FacilityUse> facilityUseList = adminFacilityMapper.getFacilityUseList();

		return facilityUseList;
	}

	/* 시설조회 */
	public Map<String, Object> getAdminFacilityList(int currentPage) {
		int rowPerPage = 10;

		double rowCount = adminFacilityMapper.getFacilityCount();

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

		List<Map<String, Object>> adminFacilityList = adminFacilityMapper.getAdminFacilityList(paramMap);

		if (adminFacilityList != null) {
			// 향상된 for문
			for (Map<String, Object> facility : adminFacilityList) {
				String mainCtgCd = facility.get("mainCtgCd").toString();
				if (mainCtgCd != null) {
					if ("gg".equals(mainCtgCd)) {
						facility.put("mainCtgCd", "공공시설");
					} else if ("ss".equals(mainCtgCd)) {
						facility.put("mainCtgCd", "사설시설");
					}
				}
			}

		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lastPage", lastPage);
		resultMap.put("adminFacilityList", adminFacilityList);
		resultMap.put("startPageNum", startPageNum);
		resultMap.put("endPageNum", endPageNum);
		return resultMap;

	}

}
