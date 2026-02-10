package com.buystuff.buystuff_api.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.buystuff.buystuff_api.abstract_classes.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {
	@Id
	@Column(name = "account_id")
	private UUID accountId;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "account_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Account account;

	@Column(name = "first_name", columnDefinition = "varchar", nullable = false)
	private String firstName;

	@Column(name = "last_name", columnDefinition = "varchar", nullable = false)
	private String lastName;
	
	@Column(name = "date_of_birth", nullable = false)
	private LocalDate dateOfBirth;
	
	@Column(name = "phone", columnDefinition = "varchar", nullable = false)
	private String phone;
	
	@Column(name = "country_code", columnDefinition = "varchar", nullable = false)
	private String countryCode;
}
