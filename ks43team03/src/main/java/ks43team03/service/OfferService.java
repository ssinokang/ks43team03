package ks43team03.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.mapper.OfferMapper;

@Service
@Transactional
public class OfferService {

	
	private final OfferMapper offerMapper;
	
	public OfferService(OfferMapper offerMapper) {
		this.offerMapper = offerMapper;
	}
	
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>>getOfferList(){
		
		List<Map<String,Object>> offerList = offerMapper.getOfferList();
		return offerList;
	}
}
