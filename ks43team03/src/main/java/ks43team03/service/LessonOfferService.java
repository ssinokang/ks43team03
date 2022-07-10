package ks43team03.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.mapper.LessonOfferMapper;

@Service
@Transactional
public class LessonOfferService {

	
	private final LessonOfferMapper offerMapper;
	
	public LessonOfferService(LessonOfferMapper offerMapper) {
		this.offerMapper = offerMapper;
	}
	
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>>getOfferList(){
		
		List<Map<String,Object>> offerList = offerMapper.getOfferList();
		return offerList;
	}
}
