package com.jsp.rest.ets.batch;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.jsp.rest.ets.user.Subject;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BatchResponse {

	private String batchId;
	private String title;
	
	
	private List<Subject> subjects;
	
	private LocalDate startingDate;
	private LocalDate closedDate;
	private LocalTime beginsAt;
	
	private LocalTime endsAt;
	private BatchStatus batchStatus;

}
