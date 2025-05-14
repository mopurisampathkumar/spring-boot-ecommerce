package com.sampath.projects.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sampath.projects.ecommerce.payload.APIResponse;

@RestControllerAdvice
public class MyGlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIResponse> myResourceNotFoundException(ResourceNotFoundException e){
		APIResponse apiResponse = new APIResponse(e.getMessage(), false);
		return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(APIException.class)
	public ResponseEntity<APIResponse> myAPIException(APIException e){
		APIResponse apiResponse = new APIResponse(e.getMessage(), false);
		return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	

}
