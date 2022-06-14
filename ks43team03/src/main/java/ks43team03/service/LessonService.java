package ks43team03.service;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import ks43team03.controller.UserController;
import ks43team03.dto.Lesson;
import ks43team03.mapper.LessonMapper;

@Service
@Transactional
public class LessonService {
	private final LessonMapper lessonMapper;
		
	public LessonService(LessonMapper lessonMapper) {
		this.lessonMapper = lessonMapper;
	}
	
	// 레슨 리스트 가져오기
	public List<Lesson> getLessonList(String facilityCd) {
		List<Lesson> lessonList = lessonMapper.getLessonList(facilityCd);
		System.out.println(lessonList + "!!");
		return lessonList;
	}

	//레슨 등록하기
	public void addLesson(Lesson lesson, MultipartHttpServletRequest multipartHttpServletRequest) {
		if (ObjectUtils.isEmpty(multipartHttpServletRequest) == false) {
	        Iterator<String> filenameIterator = multipartHttpServletRequest.getFileNames();
	        String name;
	        System.out.println(filenameIterator.hasNext());
	        System.out.println(lesson);
	        while (filenameIterator.hasNext()) {
	            name = filenameIterator.next();
	            System.out.println(name + "name!!!!!!!");
	            List<MultipartFile> fileList = multipartHttpServletRequest.getFiles(name);
	            for (MultipartFile multipartFile : fileList) {
	                System.out.println("--- start file ---");
	                System.out.println("File name : " + multipartFile.getOriginalFilename());
	                System.out.println("File size : " + multipartFile.getSize());
	                System.out.println("File content-type : " + multipartFile.getContentType());
	                System.out.println("--- end file ---");
	            }
	        }
	    }
	}
}
