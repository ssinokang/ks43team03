package ks43team03.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.Lesson;

@Mapper
public interface LessonMapper {

	//시설 내 레슨 조회하기
	public List<Lesson> getLessonList(String facilityCd);
	
}
