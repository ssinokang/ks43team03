package ks43team03.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.LessonOffer;
import ks43team03.mapper.LessonOfferMapper;

@Service
@Transactional
public class LessonOfferService {

	
	private final LessonOfferMapper offerMapper;
	
	public LessonOfferService(LessonOfferMapper offerMapper) {
		this.offerMapper = offerMapper;
	}
	
	
	@Transactional(readOnly = true)
	public List<LessonOffer>getOfferList(){
		
		List<LessonOffer> offerList = offerMapper.getOfferList();
		return offerList;
	}
	
	@Transactional(readOnly = true)
	public List<LessonOffer> getLessonOfferCityOrSports(String areaCd,String sportsName){
		return offerMapper.getLessonOfferCityOrSports(areaCd,sportsName);
	}
}
