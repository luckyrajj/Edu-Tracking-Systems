package com.jsp.rest.ets.user;

import java.time.Year;
import java.util.List;

import com.jsp.rest.ets.rating.Rating;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse extends UserResponse {

	private String degree;
	private String stream;
	private Year   yop;
	private double degreePercentage;
	private double tenthPercentage;
	private double twelvethPercentage;
	private Stack stack;
	private List<Rating> ratings;
	private UserRole role;
}
