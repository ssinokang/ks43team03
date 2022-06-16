package ks43team03.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ks43team03.dto.Boomk;
import ks43team03.dto.Facility;
import ks43team03.mapper.BoomkMapper;

@Service

public class BoomkService {
	
	private final BoomkMapper boomkMapper;
	
	public BoomkService(BoomkMapper boomkMapper) {
		this.boomkMapper = boomkMapper;
	}
	
	/**
	 * 회원 즐겨찾기 조회
	 */
	
	/*
	 * public Boomk getBoomkInfoById(String userId) { Boomk boomk =
	 * boomkMapper.getBoomkInfoById(userId); return boomk; }
	 */	
	public List<Boomk> getBoomkList(String userId){
		List<Boomk> BoomkList = boomkMapper.getBoomkList(userId);
		
		return BoomkList;
	}
	
	
	/**
	 * 시설 조회
	 */

	public List<Facility> getFacilityList(){
		List<Facility> FacilityList = boomkMapper.getFacilityList();
		
		return FacilityList;
		
	}
	
	
	
	
	
	
	
	
}