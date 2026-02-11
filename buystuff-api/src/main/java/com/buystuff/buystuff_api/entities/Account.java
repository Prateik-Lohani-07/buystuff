package com.buystuff.buystuff_api.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.NaturalId;

import com.buystuff.buystuff_api.abstract_classes.BaseEntity;
import com.buystuff.buystuff_api.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Account extends BaseEntity {
	@Id 
	@GeneratedValue(strategy=GenerationType.UUID)
	@Column(name = "account_id", columnDefinition = "uuid")
	private UUID accountId;

	@NaturalId
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

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
	private Cart cart;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addresses = new ArrayList<>();

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PaymentInfo> paymentInfoList = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> reviews = new ArrayList<>();

	@Override
	public String toString() {
		String s = String.format("Account={id=%s, email=%s, password_hash=%s}", accountId, email, passwordHash);
		return s;
	}
}
