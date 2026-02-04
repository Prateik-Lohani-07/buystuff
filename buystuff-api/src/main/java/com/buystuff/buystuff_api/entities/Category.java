package com.buystuff.buystuff_api.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "categories")
public class Category {	
	@Id
	@Column(name = "category_code", columnDefinition = "varchar", unique = true)
	private String categoryCode;

	@Column(name = "name", columnDefinition = "varchar")
	private String name;

	public Category(String categoryCode, String name) {
		this.categoryCode = categoryCode;
		this.name = name;
	}

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Product> products = new ArrayList<>();
}
