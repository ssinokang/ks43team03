package ks43team03.controller;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


import ks43team03.dto.Payload;
import ks43team03.dto.PaymentResDto;
import ks43team03.service.PaymentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PaymentController {
	
	private final PaymentService paymentService;
	
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
    @GetMapping("/success")
    public String confirmPayment(
            @RequestParam String paymentKey, @RequestParam String orderId, @RequestParam Long amount,
        Model model) throws Exception {
    	
    	
    	
    	PaymentResDto paymentResDto = paymentService.confirmPayment(paymentKey, orderId, amount);
    	
    	
    	return "success";
    }

	@GetMapping("/fail")
	public String failPayment(@RequestParam String message, @RequestParam String code, @RequestParam String orderId, Model model) {
	    log.info("=======결제실패=======");
		model.addAttribute("message", message);
	    model.addAttribute("code", code);
	    return "fail";
	}
	
	@GetMapping("/virtual-account/callback")
	@ResponseStatus(HttpStatus.OK)
	public void handleVirtualAccountCallback(@RequestBody Payload payload) {
		log.info("payload 값 : {}", payload);
		
		
	
	    }

    
   
	
	@GetMapping("/pay")
	public String pay() {
		return "paymentTest";
	}
}
