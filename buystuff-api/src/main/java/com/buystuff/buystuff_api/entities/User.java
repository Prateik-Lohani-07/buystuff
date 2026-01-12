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
	// PK being account ID since userId won't add any meaning to the tuple 
	// a "user" is just some information about the account
	// "account" is a proper domain entity though, representing a real life person
	@Id
	@Column(name = "account_id", columnDefinition = "uuid")
	private UUID accountId;

	@OneToOne(optional = false)
    @MapsId
	@JoinColumn(
		name = "account_id",
		referencedColumnName = "account_id",
		insertable = false,
		updatable = false
	)
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
