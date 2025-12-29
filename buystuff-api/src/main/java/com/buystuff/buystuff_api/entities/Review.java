package com.buystuff.buystuff_api.entities;

import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
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
@Table(name = "reviews")
public class Review extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "review_id", columnDefinition = "uuid")
	private UUID reviewId;

	@Column(name = "rating", columnDefinition = "integer")
	private Integer rating;

	@Column(name = "title", columnDefinition = "varchar")
	private String title;
	
	@Column(name = "content", columnDefinition = "varchar")
	private String content;

	@ColumnDefault("'false'")
	@Column(name = "verified", columnDefinition = "boolean")
	private Boolean verified;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.SET_NULL)
	@JoinColumn(name = "account_id")
	private Account account;

	@ManyToOne(optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "product_id")
	private Product product;
}
