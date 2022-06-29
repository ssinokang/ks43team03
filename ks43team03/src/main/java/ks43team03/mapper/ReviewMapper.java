package ks43team03.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.Review;


@Mapper
public interface ReviewMapper {

	//후기등록
	public int addReview(Review review);
	
	//시설관리자가 본인 후기 조회
	public List<Review> getAdminReviewListById(String userId);
	
	//후기 테이블 총 row 수
	public int getReviewCount();
	
	//후기
	public List<Map<String, Object>> getAdminReviewList(Map<String, Object> paramMap);
}
