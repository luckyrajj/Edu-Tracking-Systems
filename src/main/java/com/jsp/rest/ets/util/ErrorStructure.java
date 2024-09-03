package com.jsp.rest.ets.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorStructure<T> {

	private int status;
	private String message;
	private T rootcause;
	
	public static <T> ErrorStructure<T> error(int status,String message, T rootCause){
		ErrorStructure<T> error=new ErrorStructure<>();
		error.setStatus(status);
		error.setMessage(message);
		error.setRootcause(rootCause);
		return error;
	}
}
