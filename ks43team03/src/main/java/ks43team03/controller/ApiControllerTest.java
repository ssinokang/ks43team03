package ks43team03.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	
	 @GetMapping(value = "display/img/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
	 public ResponseEntity<byte[]> imgFile(@RequestParam("name") String name) throws IOException {
		 InputStream imageStream = null;
		 System.out.println(name + "ApiControllerTest/name");
		 
		 return null;
		 }
	 
}
