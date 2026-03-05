package com.buystuff.buystuff_api.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.buystuff.buystuff_api.dto.ApiResponse;
import com.buystuff.buystuff_api.dto.razorpay.RazorpayOrderDto;
import com.buystuff.buystuff_api.entities.UserPrincipal;
import com.buystuff.buystuff_api.services.payment.PaymentService;
import com.razorpay.RazorpayException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/api/v1/orders/payments")
@RequiredArgsConstructor
public class PaymentController {
	private final PaymentService paymentService;

	@PreAuthorize("hasRole('CUSTOMER')")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/rzp_order")
	public ApiResponse<RazorpayOrderDto> createRazorpayOrder(
		@AuthenticationPrincipal UserPrincipal userPrincipal,
		@RequestParam(name = "order_id", required = true) UUID orderId
	) throws RazorpayException {
		try {
			log.info("START: createRazorpayOrder controller");
	
			RazorpayOrderDto order = paymentService.createRazorpayOrder(userPrincipal.getId(), orderId);
	
			log.info("END: createRazorpayOrder controller");
			return ApiResponse.success(
				HttpStatus.CREATED.value(),
				"Successfully created razorpay order",
				order
			);
		} catch (RazorpayException e) {
			log.error("Razorpay exception during callback: " + e.getMessage());
			throw e;

		} catch (Exception e) {
			throw e;
		}
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/verification")
	public ApiResponse<Boolean> verifyOrder(
		@RequestParam(name = "order_id", required = true) UUID orderId, 
		@RequestParam(name = "razorpay_order_id", required = true) String razorpayOrderId, 
		@RequestParam(name = "razorpay_payment_id", required = true) String razorpayPaymentId, 
		@RequestParam(name = "razorpay_signature", required = true) String razorpaySignature
	) throws RazorpayException {
		try {
			log.info("START: verifyOrder controller");
			
			boolean isValid = paymentService.verifyOrder(orderId, razorpayOrderId, razorpayPaymentId, razorpaySignature);

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
