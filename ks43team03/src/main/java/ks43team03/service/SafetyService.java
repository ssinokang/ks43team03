package ks43team03.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.mapper.SafetyMapper;



@Service
@Transactional
public class SafetyService {
	
	private final SafetyMapper safetyMapper;
	
	public SafetyService(SafetyMapper safetyMapper) {
		this.safetyMapper = safetyMapper;
	}

	
}