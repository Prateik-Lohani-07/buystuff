package com.buystuff.buystuff_api.abstract_classes;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class BaseResponseDto {
	private Instant createdAt;
	private Instant updatedAt;
}
