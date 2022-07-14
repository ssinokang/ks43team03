package ks43team03.strategy.factory;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ks43team03.strategy.CalenderStrategy;
import ks43team03.strategy.enumeration.CalenderStrategyName;

@Component
public class CalenderFactory {
	
	private static final Logger log = LoggerFactory.getLogger(CalenderFactory.class);
	private Map<CalenderStrategyName, CalenderStrategy> strategies;
	
	public CalenderFactory(Set<CalenderStrategy> StrategySet) {
		
	}

}
