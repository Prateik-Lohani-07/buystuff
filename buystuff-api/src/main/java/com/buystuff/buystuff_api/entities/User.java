package com.buystuff.buystuff_api.entities;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.buystuff.buystuff_api.abstract_classes.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
	@JoinColumn(name = "account_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Account account;

	@Column(name = "name", columnDefinition = "varchar")
	private String name;
	
	@Column(name = "date_of_birth")
	private LocalDate dob;
	
	@Column(name = "phone", columnDefinition = "varchar")
	private String phone;
	
	@Column(name = "country_code", columnDefinition = "varchar")
	private String countryCode;
}
