package com.buystuff.buystuff_api.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NaturalId;

import com.buystuff.buystuff_api.abstract_classes.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "products")
public class Product extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "product_id", columnDefinition = "uuid")
	private UUID productId;

	@Column(name = "name", columnDefinition = "varchar")
	private String name;

	@NaturalId
	@Column(name = "product_code", columnDefinition = "varchar")
	private String productCode;

	@Column(name = "price", columnDefinition = "numeric")
	private Double price;
	
	@ColumnDefault("0")
	@Column(name = "discount", columnDefinition = "numeric")
	private Double discount;
	
	@ColumnDefault("0")
	@Column(name = "stock", columnDefinition = "integer", nullable = false)
	private Integer stock;
	
	@Column(name = "description", columnDefinition = "varchar")
	private String description;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> reviews = new ArrayList<>();

	@Formula("(SELECT COUNT(*) FROM reviews r WHERE r.product_id = product_id)")
	private Integer numberOfReviews;
	
	@Column(name = "avg_rating", columnDefinition = "numeric")
	private Double avgRating;
	
	@ColumnDefault("'true'")
	@Column(name = "is_active", columnDefinition = "boolean")
	private Boolean isActive;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(
		name = "product_categories",
		joinColumns = @JoinColumn(name = "product_id"),
		inverseJoinColumns = @JoinColumn(name = "category_code")
	)
	private List<Category> categories = new ArrayList<>();

	public Double getNetPrice() {
		return price - discount;
	}
}
