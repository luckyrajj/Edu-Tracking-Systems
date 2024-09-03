package com.jsp.rest.ets.rating;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingRequest {
	
	@Min(value = 1l)
	@Max(value = 5l)
	private int rating;
	
	@NotBlank
	private String feedBack;
}
