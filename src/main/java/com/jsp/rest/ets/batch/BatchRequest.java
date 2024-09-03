package com.jsp.rest.ets.batch;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.jsp.rest.ets.user.Subject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchRequest {

	@NotNull
	@NotBlank(message = "Name should be given for the batch")
	@Pattern(regexp = "^[A-Za-z0-9-]+$")
	private String title;
	
	
	private List<Subject> subjects;
	
	private LocalDate startingDate;
	
	private LocalTime beginsAt;
	
	private LocalTime endsAt;
	
	
	
}
