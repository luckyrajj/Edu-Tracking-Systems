package com.jsp.rest.ets.user;

import java.time.Year;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest extends UserRequest {

	private String degree;
	private String stream;
	private Year   yop;
	private double degreePercentage;
	private double tenthPercentage;
	private double twelvethPercentage;
}
