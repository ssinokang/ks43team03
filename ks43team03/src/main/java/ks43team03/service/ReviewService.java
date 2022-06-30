package ks43team03.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.Review;
import ks43team03.mapper.ReviewMapper;

@Service
@Transactional
public class ReviewService {

	private static final Logger log = LoggerFactory.getLogger(ReviewService.class);
	
	private final ReviewMapper reviewMapper;
	
	public ReviewService(ReviewMapper reviewMapper){
		this.reviewMapper = reviewMapper;
	}
	
	
	
	/*사용자가 후기 등록*/
	public int addReview(Review review) {
		int result = reviewMapper.addReview(review);
		
		return result;
	}
	
	
	/*시설관리자가 후기 조회*/
	public List<Review> getAdminReviewListById(String userId){
		
		List<Review> adminReviewListById = reviewMapper.getAdminReviewListById(userId);
		
		return adminReviewListById;
	}
	
	
	
	
	/*관리자가 후기 조회*/
	public Map<String, Object> getAdminReviewList(int currentPage){
			
			int rowPerPage = 10;
			int startPageNum = 1;
			int endPageNum = 10;
			
			double rowCount = reviewMapper.getReviewCount();
			
			int lastPage = (int)Math.ceil(rowCount/rowPerPage);
			
			int startRow = (currentPage - 1) * rowPerPage;
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			
			paramMap.put("startRow", startRow);
			paramMap.put("rowPerPage", rowPerPage);
			
		
			
			if(lastPage > 10) {
				if(currentPage >= 6) {
					startPageNum = currentPage - 4;
					endPageNum = currentPage + 5;
					
					if(endPageNum >= lastPage) {
						startPageNum = lastPage - 9;
						endPageNum = lastPage;
					}
				}
			}else {
				endPageNum = lastPage;
			}
			List<Map<String, Object>> adminReviewList = reviewMapper.getAdminReviewList(paramMap);
			
			log.info("paramMap : {}", paramMap);
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("lastPage", 			lastPage);
			resultMap.put("adminReviewList",	adminReviewList);
			resultMap.put("startPageNum",		startPageNum);
			resultMap.put("endPageNum",			endPageNum);
			return resultMap;
	
		
		}

}
