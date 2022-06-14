package ks43team03.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import ks43team03.common.FileUtils;
import ks43team03.dto.Lesson;
import ks43team03.mapper.FileMapper;
import ks43team03.mapper.LessonMapper;

@Service
@Transactional
public class LessonService {
	private final LessonMapper lessonMapper;
	private final FileMapper   fileMapper;
	public LessonService(LessonMapper lessonMapper, FileMapper fileMapper) {
		this.lessonMapper = lessonMapper;
		this.fileMapper	  = fileMapper;
	}
	
	// 레슨 리스트 가져오기
	public List<Lesson> getLessonList(String facilityCd) {
		List<Lesson> lessonList = lessonMapper.getLessonList(facilityCd);
		System.out.println(lessonList + "!!");
		return lessonList;
	}

	//레슨 등록하기
	public void addLesson(Lesson lesson, MultipartHttpServletRequest mhsr) {
		
		//파일이 널이 아니라면
		if (Objects.nonNull(mhsr)) {
			FileUtils fu = new FileUtils(mhsr, lesson.getUserId());
			List<Map<String, String>> dtoFileList = fu.parseFileInfo();
			//t_file 테이블에 삽입
			fileMapper.uploadFile(dtoFileList);
			
			//릴레이션 테이블에 삽입
			
			
			
			
			/****************
			 *				*
			 *  log 찍어보기	*
			 * 				*
			 ****************/
			
			/*
	        Iterator<String> filenameIterator = mhsr.getFileNames();
	        String name;
	        System.out.println(filenameIterator.hasNext());
	        System.out.println(lesson);
	        while (filenameIterator.hasNext()) {
	            name = filenameIterator.next();
	            System.out.println(name + "name!!!!!!!");
	            List<MultipartFile> fileList = mhsr.getFiles(name);
	            for (MultipartFile multipartFile : fileList) {
	                System.out.println("--- start file ---");
	                System.out.println("File name : " + multipartFile.getOriginalFilename());
	                System.out.println("File size : " + multipartFile.getSize());
	                System.out.println("File content-type : " + multipartFile.getContentType());
	                System.out.println("--- end file ---");
	                
	            }
	        }
	        */
	    }
	}
}
