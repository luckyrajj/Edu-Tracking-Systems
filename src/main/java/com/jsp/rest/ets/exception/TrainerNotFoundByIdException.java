package com.jsp.rest.ets.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TrainerNotFoundByIdException extends RuntimeException {

	private String message;
}
