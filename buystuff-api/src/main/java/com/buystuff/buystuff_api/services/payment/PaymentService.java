package com.buystuff.buystuff_api.services.payment;

import java.util.UUID;

import com.buystuff.buystuff_api.dto.razorpay.RazorpayOrderDto;
import com.razorpay.RazorpayException;

public interface PaymentService {
	RazorpayOrderDto createRazorpayOrder(UUID accountId, UUID orderId) throws RazorpayException;
	Boolean verifyOrder(UUID orderId, String razorpayOrderId, String razorpayPaymentId, String razorpaySignature) throws RazorpayException;
}
