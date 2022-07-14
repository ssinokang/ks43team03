package ks43team03.strategy.calender;

import java.util.Map;

import org.springframework.stereotype.Component;

import ks43team03.strategy.CalenderStrategy;
import ks43team03.strategy.enumeration.CalenderStrategyName;

@Component
public class LessonCalender
implements CalenderStrategy{

	@Override
	public Map<String, Object> getData(Map<String, Object> DateMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CalenderStrategyName getCalenderStrategyName() {
		// TODO Auto-generated method stub
		return CalenderStrategyName.LessonReservation;
	}

}
