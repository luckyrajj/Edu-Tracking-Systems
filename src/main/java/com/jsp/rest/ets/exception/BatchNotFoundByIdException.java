package com.jsp.rest.ets.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BatchNotFoundByIdException extends RuntimeException {

	private  final String message;

}
