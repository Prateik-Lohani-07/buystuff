package com.buystuff.buystuff_api.snapshots;

import java.util.List;

import com.buystuff.buystuff_api.entities.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSnapshot {
	private String productCode;
	private double price;
	private double discount;
	private List<Category> categories;
}
