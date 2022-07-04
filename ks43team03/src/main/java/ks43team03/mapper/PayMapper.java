package ks43team03.mapper;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.Pay;

@Mapper
public interface PayMapper {
	
	public void addPay(Pay payment);
}
