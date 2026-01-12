package com.buystuff.buystuff_api.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.buystuff.buystuff_api.abstract_classes.BaseEntity;
import com.buystuff.buystuff_api.enums.Role;
import com.buystuff.buystuff_api.exceptions.NotFoundException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "accounts")
public class Account extends BaseEntity implements UserDetails {
	@Id 
	@GeneratedValue(strategy=GenerationType.UUID)
	@Column(name = "account_id", columnDefinition = "uuid")
	private UUID accountId;

	@Column(name = "email", columnDefinition = "varchar")
	private String email;

	@Column(name = "password_hash", columnDefinition = "varchar")
	private String passwordHash;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", length = 20, nullable = false)
	private Role role;
	
	// keeping orders since we do want to keep that information for sales purposes
	@OneToMany(mappedBy = "account")
	private List<Order> orders = new ArrayList<>();

	@OneToOne(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	private Cart cart;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addresses = new ArrayList<>();

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PaymentInfo> paymentInfoList = new ArrayList<>();

	@OneToOne(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	private User user;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> reviews = new ArrayList<>();

	public void addToCart(Product product) {
		cart.addToCart(product);
	}

	public void addPaymentInfo(PaymentInfo paymentInfo) {
		paymentInfo.setAccount(this);
		paymentInfoList.add(paymentInfo);
	}
	
	public void addAddress(Address address) {
		address.setAccount(this);
		addresses.add(address);
	}

	public void updateAddress(UUID addressId, Address updatedAddress) {
		Address existingAddress = addresses.stream()
			.filter(a -> a.getAddressId().equals(addressId))
			.findFirst()
			.orElseThrow(() -> new NotFoundException("Address not found."));

		BeanUtils.copyProperties(updatedAddress, existingAddress, "addressId", "account");
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public String getPassword() {
		return passwordHash;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	@Override
	public String toString() {
		String s = String.format("Account={id=%s, email=%s, password_hash=%s}", accountId, email, passwordHash);
		return s;
	}
}
