package ks43team03.strategy;

import java.util.List;
import java.util.Map;

import ks43team03.dto.LessonReservatioin;
import ks43team03.strategy.enumeration.CalenderStrategyName;

public interface CalenderStrategy {
	//캘린더(Lesson,)
	List<LessonReservatioin> getData(Map<String, String> scheduleDate);
	
	CalenderStrategyName getCalendarStrategyName();
} 

