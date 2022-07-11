package ks43team03.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.LessonOffer;

@Mapper
public interface LessonOfferMapper {

	public List<LessonOffer> getOfferList();
	
	public List<LessonOffer> getLessonOfferCityOrSports(String areaCd,String sportsName);
}
