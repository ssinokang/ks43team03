package ks43team03.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks43team03.dto.BookingDto;
import ks43team03.mapper.BookingMapper;

@Service
@Transactional
public class BookingService {

	private static final Logger log = LoggerFactory.getLogger(BookingService.class);
	
	public final BookingMapper bookingMapper;
	
	public BookingService(BookingMapper bookingMapper) {
		this.bookingMapper = bookingMapper;
	}
	
	public List<BookingDto> getbookingListByCd(String facilityGoodsCd){
		List<BookingDto> bookingListByCd = bookingMapper.getbookingListByCd(facilityGoodsCd);
		
		return bookingListByCd;	
	}
	
}
