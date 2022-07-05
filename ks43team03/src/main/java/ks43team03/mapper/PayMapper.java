package ks43team03.mapper;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.Pay;

@Mapper
public interface PayMapper {
	
	// 결제 등록
	public void addPay(Pay payment);
	// 결제한 카드 내역 등록	
	public void addPayCardInfo(Pay pay);
	
	// 결제할 가상계좌 등록 
	public void addPayVirtualAccount(Pay pay);
}
