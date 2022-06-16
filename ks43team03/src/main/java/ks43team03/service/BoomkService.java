package ks43team03.service;

import org.springframework.stereotype.Service;

import ks43team03.dto.Boomk;
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
	
	public Boomk getBoomkInfoById(String userId) {

		Boomk boomk = boomkMapper.getBoomkInfoById(userId);

		return boomk;
	}	
}