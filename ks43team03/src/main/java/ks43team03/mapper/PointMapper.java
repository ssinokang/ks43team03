package ks43team03.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.Point;

@Mapper
public interface PointMapper {
	
	//회원 포인트 조회
	public List<Point> getPointList(String userId);
}