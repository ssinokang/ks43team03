package ks43team03.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LessonOfferMapper {

	public List<Map<String,Object>> getOfferList();
}
