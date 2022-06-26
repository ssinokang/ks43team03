package ks43team03.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.Pass;


@Mapper
public interface PassMapper {



	public void addPass(Pass pass);
	
	public List<Pass> getPassAll();

}
