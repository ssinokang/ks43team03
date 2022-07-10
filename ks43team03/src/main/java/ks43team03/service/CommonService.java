package ks43team03.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.Area;
import ks43team03.dto.AreaCity;
import ks43team03.dto.Sports;
import ks43team03.mapper.CommonMapper;

@Service
@Transactional
public class CommonService {
	CommonMapper commonMapper;
	
	private static final Logger log = LoggerFactory.getLogger(CommonService.class);

	public CommonService(CommonMapper commonMapper) {
		this.commonMapper = commonMapper;
	}
	public List<AreaCity> getAreaCityList(String areaCd) {
		List<AreaCity> areaCity = commonMapper.getAreaCityList(areaCd);
		return areaCity;
	}
	public List<Area> getAreaList() {
		List<Area> area = commonMapper.getAreaList();
		return area;
	}
	public List<Sports> getSportsList() {
		log.info("___________LessonService/getSportsList_____________");
		List<Sports> sportsList = commonMapper.getSportsList();
		return sportsList;
	}

}
