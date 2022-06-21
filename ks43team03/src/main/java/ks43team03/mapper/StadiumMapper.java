package ks43team03.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.Stadium;
import ks43team03.dto.StadiumPrice;




@Mapper
public interface StadiumMapper {

	//회원이 구장 상세정보 조회 
	public Stadium getStadiumInfoByCd(String facilityStadiumCd);
	
	//회원이 구장 조회
	public List<Map<String, Object>> getStadiumList(Map<String, Object> paramMap);
	
	//구장 단가 수정 
	public int modifyStadiumPrice(StadiumPrice stadiumPrice);
	
	//구장코드별 구장 단가 상세정보조회
	public StadiumPrice getAdminStadiumPriceInfoByCd(String facilityStadiumCd);
	
	//구장코드별 구장 상세정보조회
	public Stadium getAdminStadiumInfoByCd(String facilityStadiumCd);
	
	//구장 수정
	public int modifyStadium(Stadium stadium);
	
	//구장 단가 등록
	public int addStadiumPrice(StadiumPrice stadiumPrice);
	
	//구장 등록
	public int addStadium(Stadium stadium);
	
	//시설 내 구장 정보 조회
	public List<Stadium> getAdminStadiumListByCd(String facilityCd);
	
	//구장 테이블 총 row(튜플) 수
	public int getStadiumCount();
	
	//구장 조회
	public List<Map<String, Object>> getAdminStadiumList(Map<String, Object> paramMap);
	
}
