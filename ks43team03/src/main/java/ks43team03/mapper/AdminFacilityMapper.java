package ks43team03.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.Facility;
import ks43team03.dto.FacilityUse;
import ks43team03.dto.MainCtg;

@Mapper
public interface AdminFacilityMapper {
	
	
	
	
	
	
	
	//시설 수정
	public int modifyFacility(Facility facility);
	
	//시설코드별 상세정보조회
	public Facility getAdminFacilityInfoByCd(String facilityCd);
	
	
	//시설카테고리조회
	public List<MainCtg> getMainCtgList();
	
	//시설 용도 조회
	public List<FacilityUse> getFacilityUserList();
	
	//시설등록
	public int addFacility(Facility facility);
	
	//시설목록조회
	public List<Facility> getAdminFacilityList();



}
