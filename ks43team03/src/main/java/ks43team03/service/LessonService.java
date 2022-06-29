package ks43team03.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import ks43team03.common.FileUtils;
import ks43team03.dto.Lesson;
import ks43team03.dto.Sports;
import ks43team03.mapper.CommonMapper;
import ks43team03.mapper.FacilityGoodsMapper;
import ks43team03.mapper.FileMapper;
import ks43team03.mapper.LessonMapper;

@Service
@Transactional
public class LessonService {
	private final LessonMapper lessonMapper;
	private final FileMapper   fileMapper;
	private final FacilityGoodsMapper facilityGoodsMapper;
	private final CommonMapper commonMapper;
	
	public LessonService(LessonMapper lessonMapper, FileMapper fileMapper, FacilityGoodsMapper facilityGoodsMapper, CommonMapper commonMapper) {
		this.lessonMapper = lessonMapper;
		this.fileMapper	  = fileMapper;
		this.facilityGoodsMapper = facilityGoodsMapper;
		this.commonMapper		 = commonMapper;
	}
	
	// 레슨 리스트 가져오기
	public List<Lesson> getfacilityLessonList(String facilityCd) {
		List<Lesson> lessonList = lessonMapper.getFacilityLessonList(facilityCd);
		System.out.println(lessonList + "!!!");
		return lessonList;
	}

	//레슨 등록하기
	public void addLesson(Lesson lesson, MultipartFile[] uploadfile, String fileRealPath) {
		
		System.out.println(uploadfile.toString());
		
		boolean fileCheck = true;
		
		for (MultipartFile multipartFile : uploadfile){
			if(!multipartFile.isEmpty()) {
				fileCheck = false;
			}
		}
		//파일이 널이 아니라면
		if (!fileCheck) {
			System.out.println("파일 있음!!" + uploadfile + "파일있음!!");
			String uproaderId 		= lesson.getUserId();
			String facilityGoodsCd;
			
			facilityGoodsMapper.addFacilityGoods(lesson.getFacilityGoods());
			facilityGoodsCd = lesson.getFacilityGoods().getFacilityGoodsCd();
			
			/***
			 * test code: start
			 ***/
			
			FileUtils fu = new FileUtils(uploadfile, uproaderId, fileRealPath);
			List<Map<String, String>> dtoFileList = fu.parseFileInfo();
			// 1. t_file 테이블에 삽입
			System.out.println(dtoFileList + "LessonService/addLesson");
			fileMapper.uploadFile(dtoFileList);
			/***
			 * test code: end
			 ***/
			// 2. lesson 테이블에 삽입
			//lessonMapper.addLesson(lesson);
			System.out.println(lesson + "LessonService/addLesson/lesson");
			
			/*
			SimpleDateFormat formatter = new SimpleDateFormat("yyyymmdd");
			
			try {
				Date date = formatter.parse(lesson.getLessonStartDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			if(lesson.getFacility().getMainCtgCd().equals("gg")) {
				lesson.setLessonState("심사 대기중");
			} else {
				if("현재날짜" < "레슨 시작 날짜") {
					lesson.setLessonState("모집대기중");
				} else if("현재 날짜" > "시작 날짜" && "끝나는 날짜" > "현재 날짜") {
					lesson.setLessonState("모집중");
				} else if("현재 날짜" > "시작 날짜" && "끝나는 날짜" < "현재 날짜") {
					lesson.setLessonState("마감");
				}
			}
			*/
			lessonMapper.addLesson(lesson);
			
			// 3. 릴레이션 테이블에 삽입
			List<Map<String, String>> relationFileList = new ArrayList<>();
			for(Map<String, String> m : dtoFileList) {
				m.put("facilityGoodsCd", facilityGoodsCd);
				relationFileList.add(m);
			}
			System.out.println(relationFileList);
			
			fileMapper.uploadRelationFile(relationFileList);
			
			
			
			/****************
			 *				*
			 *  log 찍어보기	*
			 * 				*
			 ****************/
			
	    } else {
			facilityGoodsMapper.addFacilityGoods(lesson.getFacilityGoods());
			System.out.println(lesson + "LessonService/addLesson/lesson");
			lessonMapper.addLesson(lesson);
	    }
	}

	public Lesson getLessonInfoByCd(String lessonCd) {
		
		Lesson lesson = lessonMapper.getLessonInfoByCd(lessonCd);
		System.out.println(lesson + "lessonService/getLessonInfoById");
		return lesson;
	}

	public List<Lesson> getLessonListForUser(HashMap<String, Object> lessonMap) {
		System.out.println("___________________________________________________");
		System.out.println("_______________start LessonService_________________");
		System.out.println(lessonMap);

		
		List<Lesson> LessonListForUser = lessonMapper.getLessonListForUser(lessonMap);
		
		System.out.println("_______________end   LessonService_________________");
		System.out.println("___________________________________________________");
		return LessonListForUser;
	}

	public int modifyLesson(Lesson lesson) {
		System.out.println("___________________________________________________");
		System.out.println("_______________start modifyLesson");
		int result = lessonMapper.modifyLesson(lesson);
		System.out.println("_______________end   modifyLesson");
		System.out.println("___________________________________________________");
		return 0;
	}

	public List<Sports> getSportsList() {
		System.out.println("___________LessonService/getSportsList_____________");
		List<Sports> sportsList = commonMapper.getSportsList();
		return sportsList;
	}
}
