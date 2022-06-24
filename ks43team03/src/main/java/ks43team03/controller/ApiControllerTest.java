package ks43team03.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ks43team03.dto.FacilityGoods;

@RestController
public class ApiControllerTest {

	private static final Logger log = LoggerFactory.getLogger(ApiControllerTest.class);

	@PostMapping("/api/goods")
	public FacilityGoods addGoodsCode(@RequestBody FacilityGoods facilityGoods) {

		log.info("facilityGoods : {}", facilityGoods);

		return facilityGoods;
	}

	 
}
