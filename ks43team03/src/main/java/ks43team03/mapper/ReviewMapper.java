package ks43team03.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ReviewMapper {

	//시설후기
	public List<ReviewMapper> getReviewList(String facilityCd);
}
