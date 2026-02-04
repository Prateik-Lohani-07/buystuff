package com.buystuff.buystuff_api.dto.category;

import com.buystuff.buystuff_api.abstract_classes.BaseResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class CategoryDto extends BaseResponseDto {
	private String categoryCode;
	private String name;
}
