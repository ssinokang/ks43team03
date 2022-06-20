package ks43team03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/goods")
public class FacilityGoodsController {
	
	
	
	//== html  임시경로  매핑 ==//
	@GetMapping("/goods")
	public String goodsRead() {
		return "goods/goodsRead";
	}
	
	
	//== html  임시경로  매핑 ==//
	@GetMapping("/tempgoods")
	public String goodsTemp() {
		return "goods/tempGoods";
	}

}
