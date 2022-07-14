package ks43team03.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.Lesson;
import ks43team03.dto.LessonOffer;
import ks43team03.mapper.LessonMapper;
import ks43team03.mapper.LessonOfferMapper;

@Service
@Transactional
public class LessonOfferService {

	
	private static final Logger log = LoggerFactory.getLogger(LessonOfferService.class);

	private final LessonOfferMapper offerMapper;
	private final LessonMapper lessonMapper;
	
	public LessonOfferService(LessonOfferMapper offerMapper,LessonMapper lessonMapper) {
		this.offerMapper = offerMapper;
		this.lessonMapper = lessonMapper;
		
	}
	
	
	@Transactional(readOnly = true)
	public List<LessonOffer>getOfferList(){
		
		List<LessonOffer> offerList = offerMapper.getOfferList();
		return offerList;
	}
	
	
	/*
	 * LessonOffer 지역별, 종목별 검색리스트 
	 *  
	 */
	@Transactional(readOnly = true)
	public List<LessonOffer> getLessonOfferCityOrSports(String areaCd,String sportsName){
		return offerMapper.getLessonOfferCityOrSports(areaCd,sportsName);
	}
	
	/**
	 * 
	 * @param 시설코드
	 * @return 시설이등록한 레슨리스트
	 */
	
	@Transactional(readOnly = true)
	public List<Lesson> getLessonByFacilityCd(String facilityCd){
		List<Lesson> lessonList = lessonMapper.getFacilityLessonList(facilityCd);
		return lessonList;
	}
	
	/**
	 * 레슨구인구직 등록
	 */
	
	public boolean addLessonOffer(LessonOffer lessonOffer) {
		
		String facilityCd = lessonOffer.getFacilityCd();
		String lessonCd = lessonOffer.getLessonCd();
		
		
		// 시설 조회
		
		
		
		// 레슨조회
		
		
		// 레슨 구인 구직 등록 
//		offerMapper.addLessonOffer(lessonOffer);
		
		return false;
	}
}
