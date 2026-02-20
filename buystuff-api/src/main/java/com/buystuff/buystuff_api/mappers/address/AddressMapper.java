package com.buystuff.buystuff_api.mappers.address;

import com.buystuff.buystuff_api.dto.account.address.AddressDto;
import com.buystuff.buystuff_api.dto.account.address.CreateAddressDto;
import com.buystuff.buystuff_api.dto.account.address.UpdateAddressDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.Address;
import com.buystuff.buystuff_api.snapshots.AddressSnapshot;

public abstract class AddressMapper {
	public static Address toEntity(CreateAddressDto dto, Account account) {
		Address entity = new Address();

		entity.setFlatOrBlock(dto.getFlatOrBlock());
		entity.setLine1(dto.getLine1());
		entity.setLine2(dto.getLine2());
		entity.setCity(dto.getCity());
		entity.setState(dto.getState());
		entity.setCountry(dto.getCountry());
		entity.setPincode(dto.getPincode());
		entity.setAccount(account);

		return entity;
	}

	public static void updateEntity(UpdateAddressDto dto, Address entity) {
		if (dto.getFlatOrBlock() != null)
			entity.setFlatOrBlock(dto.getFlatOrBlock());
		
		if (dto.getLine1() != null)
			entity.setLine1(dto.getLine1());
		
		if (dto.getLine2() != null)
			entity.setLine2(dto.getLine2());
		
		if (dto.getCity() != null)
			entity.setCity(dto.getCity());
		
		if (dto.getState() != null)
			entity.setState(dto.getState());
		
		if (dto.getCountry() != null)
			entity.setCountry(dto.getCountry());
		
		if (dto.getPincode() != null)
			entity.setPincode(dto.getPincode());
	}

	public static AddressDto toDto(Address entity) {
		AddressDto dto = new AddressDto();

		dto.setAddressId(entity.getAddressId());

		dto.setFlatOrBlock(entity.getFlatOrBlock());
		dto.setLine1(entity.getLine1());
		dto.setLine2(entity.getLine2());
		dto.setCity(entity.getCity());
		dto.setState(entity.getState());
		dto.setCountry(entity.getCountry());
		dto.setPincode(entity.getPincode());
		dto.setAccountId(entity.getAccount().getAccountId());

		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());

		return dto;
	}

	public static AddressSnapshot toSnapshot(Address address) {
		AddressSnapshot addressSnapshot = new AddressSnapshot();

		addressSnapshot.setFlatOrBlock(address.getFlatOrBlock());
		addressSnapshot.setLine1(address.getLine1());
		addressSnapshot.setLine2(address.getLine2());
		addressSnapshot.setCity(address.getCity());
		addressSnapshot.setState(address.getState());
		addressSnapshot.setCountry(address.getCountry());
		addressSnapshot.setPincode(address.getPincode());

		return addressSnapshot;
	} 
}
