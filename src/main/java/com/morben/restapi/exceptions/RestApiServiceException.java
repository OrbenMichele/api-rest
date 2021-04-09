package com.morben.restapi.exceptions;

public class RestApiServiceException extends RuntimeException{

	private static final long serialVersionUID = 354325767610593611L;

	public RestApiServiceException(String message){
		super(message);
	}
}