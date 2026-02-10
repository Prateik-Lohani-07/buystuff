package com.buystuff.buystuff_api.mappers.account;

import com.buystuff.buystuff_api.dto.account.AccountDto;
import com.buystuff.buystuff_api.dto.account.user.CreateUserDto;
import com.buystuff.buystuff_api.dto.authentication.SignupDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.Cart;
import com.buystuff.buystuff_api.entities.User;
import com.buystuff.buystuff_api.enums.Role;
import com.buystuff.buystuff_api.mappers.user.UserMapper;

public abstract class AccountMapper {
	public static Account toEntity(SignupDto dto, String passwordHash) {
		Account account = new Account();
        String email = dto.getEmail();
        CreateUserDto createUserDto = dto.getUserInfo();
        
		User user = UserMapper.toEntity(createUserDto, account);
		
		Cart cart = new Cart();
		cart.setAccount(account);

        account.setEmail(email);
        account.setPasswordHash(passwordHash);
        account.setUser(user);
        account.setRole(Role.CUSTOMER);
		account.setCart(cart);

		return account;
	}

	public static AccountDto toDto(Account entity) {
		AccountDto dto = new AccountDto();

		dto.setAccountId(entity.getAccountId());

		dto.setRole(entity.getRole());
		dto.setEmail(entity.getEmail());
		dto.setUser(UserMapper.toDTO(entity.getUser()));

		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());

		return dto;
	}
}
