package com.jsp.rest.ets.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.rest.ets.exception.BatchNotFoundByIdException;
import com.jsp.rest.ets.exception.RatingNotFoundByIdException;
import com.jsp.rest.ets.exception.StudentNotFoundByIdException;
import com.jsp.rest.ets.exception.TrainerNotFoundByIdException;
import com.jsp.rest.ets.util.AppResponseBuilder;
import com.jsp.rest.ets.util.ErrorStructure;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class ExceptionsHandler {
	
	private AppResponseBuilder responseBuilder;

	@ExceptionHandler(value = TrainerNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> handleTrainerNotFoundByIdException(TrainerNotFoundByIdException ex){
		return responseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "failed to find the Trainer with the Id");
	}
	@ExceptionHandler(value = StudentNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> handleStudentNotFoundByIdException(StudentNotFoundByIdException ex){
		return responseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "failed to find the Student with the Id");
	}
	
	@ExceptionHandler(value = RatingNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> handleRatingNotFoundByIdException(RatingNotFoundByIdException ex){
		return responseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "failed to find the rating with the id");
	}
	
	@ExceptionHandler(value = BatchNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> handleBatchNotFoundByIdException(BatchNotFoundByIdException ex){
		return responseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "failed to find the batch with the id");
	}
	
}
