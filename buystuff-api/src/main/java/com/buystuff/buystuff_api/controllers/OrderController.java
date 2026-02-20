package com.buystuff.buystuff_api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.buystuff.buystuff_api.dto.ApiResponse;
import com.buystuff.buystuff_api.dto.order.OrderDto;
import com.buystuff.buystuff_api.dto.order.OrderFilterDto;
import com.buystuff.buystuff_api.entities.UserPrincipal;
import com.buystuff.buystuff_api.enums.OrderAction;
import com.buystuff.buystuff_api.services.order.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
	private final OrderService orderService;

	@PreAuthorize("hasAnyRole('FULFILLMENT_MANAGER', 'CUSTOMER')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PatchMapping("/{order_id}/status")
	public ApiResponse<Void> changeOrderStatus(
		@AuthenticationPrincipal UserPrincipal userPrincipal,
		@PathVariable(name = "order_id") UUID orderId,
		@Valid @RequestParam(required = true) OrderAction action
	) {
		log.info("START: changeOrderStatus controller");
		
		orderService.updateOrderStatus(userPrincipal.getId(), userPrincipal.getRole(), orderId, action);
		
		log.info("END: changeOrderStatus controller");
		return ApiResponse.success(
			HttpStatus.NO_CONTENT.value(),
			"Successfully updated order status", 
			null
		);
	}

	@PreAuthorize("hasAnyRole('FULFILLMENT_MANAGER', 'CUSTOMER')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@GetMapping("/{order_id}")
	public ApiResponse<OrderDto> getOrder(
		@AuthenticationPrincipal UserPrincipal userPrincipal,
		@PathVariable(name = "order_id") UUID orderId
	) {
		log.info("START: getOrder controller");
		
		OrderDto orderDto = orderService.getOrder(userPrincipal.getId(), orderId);
		
		log.info("END: getOrder controller");
		return ApiResponse.success(
			HttpStatus.NO_CONTENT.value(),
			"Successfully fetched order details", 
			orderDto
		);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ApiResponse<OrderDto> createOrder(
		@AuthenticationPrincipal UserPrincipal userPrincipal,
		@RequestParam(name = "address_id", required = true) UUID addressId,
		@RequestParam(name = "payment_info_id", required = true) UUID paymentInfoId
	) {
		log.info("START: createOrder controller");
		
		OrderDto orderDto = orderService.createOrder(userPrincipal.getId(), addressId, paymentInfoId);
		
		log.info("END: createOrder controller");
		return ApiResponse.success(
			HttpStatus.CREATED.value(),
			"Successfully created order", 
			orderDto
		);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public ApiResponse<List<OrderDto>> viewOrderHistory(
		@RequestParam(defaultValue = "10") int size,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(name = "sort_by", required = false) String sortBy,
		@RequestParam(name = "sort_order", required = false) Sort.Direction sortOrder,
		@AuthenticationPrincipal UserPrincipal userPrincipal
	) {
		log.info("START: viewOrderHistory controller");
		
		OrderFilterDto filters = new OrderFilterDto(size, page, sortBy, sortOrder);
		List<OrderDto> orderDtos = orderService.viewOrderHistory(userPrincipal.getId(), filters);
		
		log.info("END: viewOrderHistory controller");
		return ApiResponse.success(
			HttpStatus.OK.value(),
			"Successfully fetched order history",
			orderDtos
		);
	}
}
