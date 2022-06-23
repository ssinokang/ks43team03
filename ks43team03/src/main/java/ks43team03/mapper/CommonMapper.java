package ks43team03.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonMapper {

	
	public String createNewCode(String colunm,String tableName);
}
