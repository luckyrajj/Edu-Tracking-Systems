package com.jsp.rest.ets.rating;

import com.jsp.rest.ets.user.Subject;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class RatingResponse {

	private Subject subject;
	private int rating;
	private String feedBack;
	
}
