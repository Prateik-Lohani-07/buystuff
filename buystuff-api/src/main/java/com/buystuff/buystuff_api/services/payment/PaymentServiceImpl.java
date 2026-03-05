package com.buystuff.buystuff_api.services.payment;

import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.buystuff.buystuff_api.dto.razorpay.RazorpayOrderDto;
import com.buystuff.buystuff_api.entities.Order;
import com.buystuff.buystuff_api.enums.OrderAction;
import com.buystuff.buystuff_api.enums.Role;
import com.buystuff.buystuff_api.mappers.razorpay.RazorpayOrderMapper;
import com.buystuff.buystuff_api.services.order.OrderService;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
	private final OrderService orderService;
	private final RazorpayClient razorpayClient;
	private final String RZRPY_API_SECRET;

	public PaymentServiceImpl(
		RazorpayClient razorpayClient,
		@Value("${razorpay.api.secret}") String razorpayApiSecret,
		OrderService orderService
		
	) {
		this.razorpayClient = razorpayClient;
		this.RZRPY_API_SECRET = razorpayApiSecret;
		this.orderService = orderService;
	}

	@Override
	public RazorpayOrderDto createRazorpayOrder(UUID accountId, UUID orderId) throws RazorpayException {
		log.info("START: createRazorpayOrder service");

		JSONObject orderRequest = new JSONObject();

		Order order = orderService.getOrder(accountId, orderId);
		Double amount = order.getNetTotalCost();

		orderRequest.put("amount", amount * 100);
		orderRequest.put("currency", "INR");

		JSONObject notes = new JSONObject();
		notes.put("order_id", orderId);
		orderRequest.put("notes", notes);

		com.razorpay.Order rzpOrder = razorpayClient.orders.create(orderRequest);
		RazorpayOrderDto razorpayOrderDto = RazorpayOrderMapper.toDto(rzpOrder);

		log.info("START: createRazorpayOrder service");
		return razorpayOrderDto;
	}

	@Override
	public Boolean verifyOrder(UUID orderId, String razorpayOrderId, String razorpayPaymentId, String razorpaySignature) throws RazorpayException {
		log.info("START: verifyOrder service");
		
		String signature = razorpayOrderId + "|" + razorpayPaymentId;
		boolean isValid = Utils.verifySignature(signature, razorpaySignature, RZRPY_API_SECRET);

		if (!isValid) {
			log.info("END: verifyOrder service");
			return false;
		}
		
		orderService.changeOrderStatusToPaid(Role.SYSTEM, orderId, OrderAction.PAY);

		log.info("END: verifyOrder service");
		return true;
	}
}
