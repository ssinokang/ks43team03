package ks43team03.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.Boomk;
import ks43team03.dto.Facility;
import ks43team03.mapper.BoomkMapper;

@Service
@Transactional
public class BoomkService {
	
	private final BoomkMapper boomkMapper;
	
	public BoomkService(BoomkMapper boomkMapper) {
		this.boomkMapper = boomkMapper;
	}
	
	/**
	 * 회원 즐겨찾기 조회
	 */
	
	public List<Boomk> getBoomkList(String userId){
		List<Boomk> BoomkList = boomkMapper.getBoomkList(userId);
		
		return BoomkList;
	}
	
	/**
	 * 회원 즐겨찾기 취소
	 */
	
	public void modifyBoomk(Boomk boomk) {
		boomkMapper.modifyBoomk(boomk);
	}
	 
	
	
	
	
	
	
	
	/**
	 * 시설 조회
	 */

	public List<Facility> getFacilityList(){
		List<Facility> FacilityList = boomkMapper.getFacilityList();
		
		return FacilityList;
		
	}


	
	
	
	
	
	
	
	
}