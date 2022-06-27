package ks43team03.dto;

import ks43team03.dto.type.PayType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Payment {

	
	private PayType payType;
	
	
	
	
	@Getter
	@Setter
	public static class PaymentReq {
		
//		orderId=1656032393899&paymentKey=Ae75jWNka9lpP2YxJ4K87KwNQJpM28RGZwXLObgyB0vMDm1d&amount=19000
		private int seq;
		private PayType payType;
		private int amount;
		private String cardCompany;
		private String cardNumber;			// "123123******4567"
		private String cardReceiptUrl;
		private String orderId;
		private String orderName;
		private String customerEmail;
		private String customerName;
		private String paymentKey;
		private String paySuccessYn;
		private String payFailReason;
		private String cancelYn;
		private String createDate;;
		
		
		public PaymentReq() {}
	}
	
	
	@Getter
	public static class PaymentRes {
		
//		orderId=1656032393899&paymentKey=Ae75jWNka9lpP2YxJ4K87KwNQJpM28RGZwXLObgyB0vMDm1d&amount=19000
		private int seq;
		private PayType payType;
		private int amount;
		private String cardCompany;
		private String cardNumber;			// "123123******4567"
		private String cardReceiptUrl;
		private String orderId;
		private String orderName;
		private String customerEmail;
		private String customerName;
		private String paymentKey;
		private String paySuccessYn;
		private String payFailReason;
		private String cancelYn;
		private String createDate;
		
		
		private PaymentRes() {}
		
		public PaymentRes payResponse() {
			return null;
		}
	}
}
