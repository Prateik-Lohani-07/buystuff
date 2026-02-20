package com.buystuff.buystuff_api.services.order;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.buystuff.buystuff_api.dto.order.OrderDto;
import com.buystuff.buystuff_api.dto.order.OrderFilterDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.Address;
import com.buystuff.buystuff_api.entities.Cart;
import com.buystuff.buystuff_api.entities.Order;
import com.buystuff.buystuff_api.entities.PaymentInfo;
import com.buystuff.buystuff_api.enums.OrderAction;
import com.buystuff.buystuff_api.enums.OrderStatus;
import com.buystuff.buystuff_api.enums.Role;
import com.buystuff.buystuff_api.exceptions.BadRequestException;
import com.buystuff.buystuff_api.exceptions.ForbiddenException;
import com.buystuff.buystuff_api.exceptions.NotFoundException;
import com.buystuff.buystuff_api.mappers.order.OrderMapper;
import com.buystuff.buystuff_api.repositories.OrderRepository;
import com.buystuff.buystuff_api.services.account.AccountService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final AccountService accountService;
	private final OrderRepository orderRepository;

	private final Set<OrderAction> FULFILLMENT_MANAGER_ACTIONS =  Set.of(OrderAction.SHIP, OrderAction.DELIVER);
	private final Set<OrderAction> CUSTOMER_ACTIONS = Set.of(OrderAction.PAY, OrderAction.CANCEL, OrderAction.RETURN);
	private final List<String> sortByFields = List.of("updated_at");

	@Override
	@Transactional
	public OrderDto createOrder(UUID accountId, UUID addressId, UUID paymentInfoId) {
		Account account = accountService.getAccount(accountId);
		Cart cart = account.getCart();
		Address address = accountService.getAddress(addressId);
		PaymentInfo paymentInfo = accountService.getPaymentInfo(paymentInfoId);
		
		Order order = OrderMapper.toEntity(account, address, paymentInfo, cart);
		order = orderRepository.save(order);
		OrderDto orderDto = OrderMapper.toDto(order);

		return orderDto;
	}

	@Override
	public List<OrderDto> viewOrderHistory(UUID accountId, OrderFilterDto filters) {
		log.info("START: viewOrderHistory service");
		
		Account account = accountService.getAccount(accountId);

		int page = filters.page();
		int pageSize = filters.size();

		if (pageSize < 0) {
			throw new BadRequestException("Limit must be greater than 0");
		}

		Sort sortOptions;
		String sortBy = filters.sortBy();
		Sort.Direction sortOrder = filters.sortOrder();

		if (sortBy != null) {
			if (!validSortBy(sortBy)) throw new BadRequestException("Invalid sort by field");
			
			if (sortBy.equals("updated_at")) sortBy = "updatedAt";

			sortOptions = Sort.by(sortBy);

			if (sortOrder == null || sortOrder.equals(Sort.Direction.ASC)) 
				sortOptions = sortOptions.ascending();
			else 
				sortOptions = sortOptions.descending();	
		}
		else sortOptions = Sort.by("updatedAt").descending();
		
		PageRequest pageRequest = PageRequest.of(
			page, 
			pageSize,
			sortOptions
		);

		Page<Order> orders = orderRepository.findByAccount(account, pageRequest);
		
		List<OrderDto> orderDtos = 
			orders.stream()
			.map(OrderMapper::toDto)
			.toList();
		
		log.info("END: viewOrderHistory service");
		return orderDtos;
	}

	@Override
	public OrderDto getOrder(UUID accountId, UUID orderId) {
		log.info("START: getOrder service");

		Order order = 
			orderRepository.findByOrderIdAndAccount_AccountId(orderId, accountId)
				.orElseThrow(() -> new NotFoundException("Order doesn't exist"));

		if (order == null) {
			throw new ForbiddenException("Cannot access order");
		}
		OrderDto orderDto = OrderMapper.toDto(order);

		log.info("END: getOrder service");
		return orderDto;
	}

	@Override
	@Transactional
	public void updateOrderStatus(UUID accountId, Role role, UUID orderId, OrderAction action) {
		log.info("START: updateOrderStatus service");


		Order order =		
			orderRepository
				.findById(orderId)
				.orElseThrow(() -> new NotFoundException("Order doesn't exist"));

		if (!actionAllowed(order, accountId, role, action)) {
			throw new ForbiddenException(String.format("Cannot invoke action: %s", action.toString()));
		}

		OrderStatus newStatus = order.getStatus().next(action);
		OrderMapper.updateEntity(order, newStatus, null);
		orderRepository.save(order);

		log.info("END: updateOrderStatus service");
	}

	private boolean actionAllowed(Order order, UUID accountId, Role role, OrderAction action) {
		boolean isAllowedIfFulfillmentManager = role.equals(Role.FULFILLMENT_MANAGER) && FULFILLMENT_MANAGER_ACTIONS.contains(action);
		
		boolean isActionAllowedIfCustomer = role.equals(Role.CUSTOMER) && CUSTOMER_ACTIONS.contains(action);
		boolean orderUpdateAllowedIfCustomer = order.getAccount().getAccountId().equals(accountId);
		boolean isAllowedIfCustomer = isActionAllowedIfCustomer && orderUpdateAllowedIfCustomer;
		
		boolean isAllowed = isAllowedIfFulfillmentManager || isAllowedIfCustomer;
		return isAllowed;
	}

	private boolean validSortBy(String fieldName) {
		return sortByFields.contains(fieldName);
	}
}
