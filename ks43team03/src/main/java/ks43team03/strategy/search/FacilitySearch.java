package ks43team03.strategy.search;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ks43team03.strategy.SearchStrategy;
import ks43team03.strategy.enumeration.SearchStrategyName;

@Component
public class FacilitySearch 
implements SearchStrategy{
	
	private static final Logger log = LoggerFactory.getLogger(FacilitySearch.class);

	@Override
	public Map<String, Object> search(Map<String, Object> searchMap, int currentPage) {
		log.info("facility 검색 정상 작동");
		return null;
	}

	@Override
	public SearchStrategyName getSearchStrategyName() {
		// TODO Auto-generated method stub
		return SearchStrategyName.FacilitySearch;
	}



}
