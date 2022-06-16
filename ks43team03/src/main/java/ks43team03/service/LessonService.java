package ks43team03.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import ks43team03.common.FileUtils;
import ks43team03.dto.Lesson;
import ks43team03.mapper.FacilityGoodsMapper;
import ks43team03.mapper.FileMapper;
import ks43team03.mapper.LessonMapper;

@Service
@Transactional
public class LessonService {
	private final LessonMapper lessonMapper;
	private final FileMapper   fileMapper;
	private final FacilityGoodsMapper facilityGoodsMapper;
	
	public LessonService(LessonMapper lessonMapper, FileMapper fileMapper, FacilityGoodsMapper facilityGoodsMapper) {
		this.lessonMapper = lessonMapper;
		this.fileMapper	  = fileMapper;
		this.facilityGoodsMapper = facilityGoodsMapper;
	}
	
	// 레슨 리스트 가져오기
	public List<Lesson> getfacilityLessonList(String facilityCd) {
		List<Lesson> lessonList = lessonMapper.getFacilityLessonList(facilityCd);
		System.out.println(lessonList + "!!");
		return lessonList;
	}

	//레슨 등록하기
	public void addLesson(Lesson lesson, MultipartHttpServletRequest mhsr) {
		
		//파일이 널이 아니라면
		if (Objects.nonNull(mhsr)) {
			FileUtils fu = new FileUtils(mhsr, lesson.getUserId());
			List<Map<String, String>> dtoFileList = fu.parseFileInfo();
			// 1. t_file 테이블에 삽입
			System.out.println(dtoFileList + "LessonService/addLesson");
			fileMapper.uploadFile(dtoFileList);
			
			/***
			 * test code: start
			 ***/
			
			//facilityGoodsMapper.getFacilityGoods(lesson.getLessonCd());
			
			/***
			 * test code: end
			 ***/
			// 2. lesson 테이블에 삽입
			//lessonMapper.addLesson(lesson);
			System.out.println(lesson + "LessonService/addLesson/lesson");
			
			// 3. 릴레이션 테이블에 삽입
			/*
			String facilityGoodsCd = "test";
			for(Map<String, String> m : dtoFileList) {
				m.put("facilityGoodsCd", facilityGoodsCd);
				fileMapper.uploadRelationFile(m);
			}
			*/
			
			
			/****************
			 *				*
			 *  log 찍어보기	*
			 * 				*
			 ****************/
			
	    }
	}

	public Lesson getLessonInfoByCd(String lessonCd) {
		
		Lesson lesson = lessonMapper.getLessonInfoByCd(lessonCd);
		return lesson;
	}
}
