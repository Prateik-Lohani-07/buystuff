package com.buystuff.buystuff_api.mappers.user;

import com.buystuff.buystuff_api.dto.user.CreateUserDto;
import com.buystuff.buystuff_api.dto.user.UpdateUserDto;
import com.buystuff.buystuff_api.dto.user.UserDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.User;

public abstract class UserMapper {
	public static User toEntity(CreateUserDto dto, Account account) {
		User entity = new User();

		entity.setAccountId(account.getAccountId());
		entity.setAccount(account);
		
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setDateOfBirth(dto.getDateOfBirth());
		entity.setPhone(dto.getPhone());
		entity.setCountryCode(dto.getCountryCode());

		return entity;
	}

	public static void toEntity(UpdateUserDto dto, User entity) {
		if (dto.getFirstName() != null)
			entity.setFirstName(dto.getFirstName());
		
		if (dto.getLastName() != null)
			entity.setLastName(dto.getLastName());
		
		if (dto.getDateOfBirth() != null)
			entity.setDateOfBirth(dto.getDateOfBirth());
		
		if (dto.getPhone() != null)
			entity.setPhone(dto.getPhone());
		
		if (dto.getCountryCode() != null)
			entity.setCountryCode(dto.getCountryCode());
	}

	public static UserDto toDTO(User entity) {
		UserDto dto = new UserDto();

		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setDateOfBirth(entity.getDateOfBirth());
		dto.setPhone(entity.getPhone());
		dto.setCountryCode(entity.getCountryCode());

		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());

		return dto;
	}
}
