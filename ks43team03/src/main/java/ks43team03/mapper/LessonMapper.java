package ks43team03.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.Lesson;
import ks43team03.dto.Sports;

@Mapper
public interface LessonMapper {

	//시설 내 레슨 조회하기
	public List<Lesson> getFacilityLessonList(String facilityCd);
	
	//레슨 등록하기
	public int addLesson(Lesson lesson);
	
	//레슨 코드를 파라미터로 주고 정보를 받아오기
	public Lesson getLessonInfoByCd(String lessonCd);
	
	//
	public List<Lesson> getLessonListForUser(Map<String, Object> lessonMap);
	
	//레슨 수정하기
	public int modifyLesson(Lesson lesson);

	public int getLessonCount();

}
