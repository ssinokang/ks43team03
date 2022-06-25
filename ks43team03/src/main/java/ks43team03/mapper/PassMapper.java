package ks43team03.mapper;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.Pass;


@Mapper
public interface PassMapper {



	public void addPass(Pass pass);

}
