package ks43team03.common;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUtils {
	
	private String fileCd;
	private MultipartHttpServletRequest mhsr;
	private String userId;
	
	public FileUtils(MultipartHttpServletRequest mhsr, String userId) {
		this.mhsr = mhsr;
		this.userId = userId;
	}
	
	public List<Map<String, String>> parseFileInfo() {
		if(Objects.isNull(mhsr)) return null;
	
		
		
		List<Map<String, String>> dtoFileList = new ArrayList<>();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime currentTime = ZonedDateTime.now();
		String path = "C:\\Users\\seonw\\Downloads\\" + currentTime.format(dateFormat);
		
		File file = new File(path);
		
		//파일이 존재하지 않다면 만들어라!
		if(!file.exists()) file.mkdirs();
		
		Iterator<String> fileNameIterator = mhsr.getFileNames();
		System.out.println(fileNameIterator + "fileNameIterator!!!!!!");
		String reFileName;
		String fileExtention;
		String fileContentType;
		
		while (fileNameIterator.hasNext()) {
			List<MultipartFile> fileList = mhsr.getFiles(fileNameIterator.next());
			System.out.println(fileList);
			for(MultipartFile mf : fileList) {
				if(!mf.isEmpty()) {
					fileContentType = mf.getContentType();
					if(fileContentType.isEmpty()) {
						break;
					} else {
						if (fileContentType.contains("image/jpeg")) {
							fileExtention = ".jpg";
                        } else if (fileContentType.contains("image/png")) {
                        	fileExtention = ".png";
                        } else if (fileContentType.contains("image/gif")) {
                        	fileExtention = ".gif";
                        } else {
                            break;
                        }
					}
					reFileName = System.nanoTime() + fileExtention;
					String size = String.valueOf(mf.getSize());
					Map<String, String> fileMap = new HashMap<>();
					//랜덤 식별자 생성
					fileCd = UUID.randomUUID().toString() + System.nanoTime();
					fileMap.put("fileCd", fileCd);
					fileMap.put("userId", userId);
					fileMap.put("fileSize", size);
					fileMap.put("originalFileName", mf.getOriginalFilename());
					fileMap.put("storedFilePath", path + "/" + reFileName);
					dtoFileList.add(fileMap);
					
					file = new File(path + "/" + reFileName);
					try {
						mf.transferTo(file);
					} catch (IllegalStateException e) {
					
						e.printStackTrace();
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
			}
			
		}
		return dtoFileList;
	}
}
