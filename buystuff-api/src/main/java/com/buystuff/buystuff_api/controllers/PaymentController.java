package com.buystuff.buystuff_api.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.buystuff.buystuff_api.dto.ApiResponse;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/api/v1/orders/payments")
public class PaymentController {
	private final RazorpayClient razorpayClient;
	private final String RZRPY_API_SECRET;

	public PaymentController(
		RazorpayClient razorpayClient,
		@Value("${razorpay.api.secret}") String razorpayApiSecret
	) {
		this.razorpayClient = razorpayClient;
		this.RZRPY_API_SECRET = razorpayApiSecret;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/rzp_order")
	public ApiResponse<Order> createRazorpayOrder(
		@RequestParam(required = true) int amount,
		@RequestParam(name = "order_id", required = true) String orderId
	) throws RazorpayException {
		log.info("START: createRazorpayOrder controller");

		JSONObject orderRequest = new JSONObject();

		orderRequest.put("amount", amount * 100);
		orderRequest.put("currency", "INR");

		JSONObject notes = new JSONObject();
		notes.put("order_id", orderId);
		orderRequest.put("notes", notes);

		Order order = razorpayClient.orders.create(orderRequest);

		log.info("END: createRazorpayOrder controller");
		return ApiResponse.success(
			HttpStatus.CREATED.value(),
			"Successfully created razorpay order",
			order
		);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/verification")
	public ApiResponse<Boolean> verifyOrder(
		@RequestParam(name = "razorpay_order_id", required = true) String razorpayOrderId, 
		@RequestParam(name = "razorpay_payment_id", required = true) String razorpayPaymentId, 
		@RequestParam(name = "razorpay_signature", required = true) String razorpaySignature
	) throws RazorpayException {
		try {
			log.info("START: verifyOrder controller");
			
			String signature = razorpayOrderId + "|" + razorpayPaymentId;
			boolean isValid = Utils.verifySignature(signature, razorpaySignature, RZRPY_API_SECRET);

			log.info("END: verifyOrder controller");
			return ApiResponse.success("Verified payment signature", isValid);

		} catch (RazorpayException e) {
			log.error("Razorpay exception during callback: " + e.getMessage());
			throw e;

		} catch (Exception e) {
			log.error("Unknown exception during callback: " + e.getMessage());
			throw new RazorpayException("General exception during callback", e);
		}
	}

	
}
