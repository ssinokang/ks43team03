package ks43team03.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.Lesson;

@Mapper
public interface LessonMapper {

	//시설 내 레슨 조회하기
	public List<Lesson> getFacilityLessonList(String facilityCd);
	
	//레슨 등록하기
	public int addLesson(Lesson lesson);
	
	//레슨 코드를 파라미터로 주고 정보를 받아오기
	public Lesson getLessonInfoByCd(String lessonCd);
	
	//
	public List<Lesson> getLessonListForUser(Lesson lesosn);
	
	//레슨 수정하기
	public int modifyLesson(Lesson lesson);

}
