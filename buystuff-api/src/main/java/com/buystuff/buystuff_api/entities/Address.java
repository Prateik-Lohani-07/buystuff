package com.buystuff.buystuff_api.entities;

import java.util.UUID;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.buystuff.buystuff_api.abstract_classes.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "addresses")
public class Address extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "address_id", columnDefinition = "uuid")
	private UUID addressId;

	@Column(name = "flat_or_block", columnDefinition = "varchar")
	private String flatOrBlock;

	@Column(name = "line_1", columnDefinition = "varchar")
	private String line1;

	@Column(name = "line_2", columnDefinition = "varchar")
	private String line2;

	@Column(name = "city", columnDefinition = "varchar")
	private String city;

	@Column(name = "state", columnDefinition = "varchar")
	private String state;

	@Column(name = "country", columnDefinition = "varchar")
	private String country;
	
	@Column(name = "pincode", columnDefinition = "varchar")
	private String pincode;

	@ManyToOne(optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;
}
