package ks43team03.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.Facility;
import ks43team03.dto.FacilityUse;

@Mapper
public interface AdminFacilityMapper {
	
	
	
	
	
	
	//시설 용도 조회
	public List<FacilityUse> getFacilityUserList();
	
	//시설등록
	public int addFacility(Facility facility);
	
	//시설목록조회
	public List<Facility> getAdminFacilityList();

}
