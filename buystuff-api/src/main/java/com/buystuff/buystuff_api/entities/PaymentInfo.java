package com.buystuff.buystuff_api.entities;

import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.buystuff.buystuff_api.abstract_classes.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "payment_type")

@Table(name = "payment_info")
public abstract class PaymentInfo extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "payment_info_id", columnDefinition = "uuid")
	private UUID paymentInfoId;

	@ManyToOne(optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;

	@ColumnDefault("'true'")
	@Column(name = "is_active", nullable = false)
	private boolean isActive;

	abstract protected Boolean isValid();
}
