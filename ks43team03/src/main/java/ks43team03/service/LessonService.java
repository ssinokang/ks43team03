package ks43team03.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.Lesson;
import ks43team03.mapper.LessonMapper;

@Service
@Transactional
public class LessonService {
	private final LessonMapper lessonMapper;
		
	public LessonService(LessonMapper lessonMapper) {
		this.lessonMapper = lessonMapper;
	}
	
	// 레슨 리트트 가져오기
	public List<Lesson> getLessonList(String facilityCd) {
		List<Lesson> lessonList = lessonMapper.getLessonList(facilityCd);
		System.out.println(lessonList + "!!");
		return lessonList;
	}
}
