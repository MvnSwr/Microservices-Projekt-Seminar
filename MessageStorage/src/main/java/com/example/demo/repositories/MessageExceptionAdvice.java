package com.example.demo.repositories;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MessageExceptionAdvice {
	
	/*
	 * Exception handling if no Message was found
	 */

	@ExceptionHandler(MessageNotFoundException.class)
	ResponseEntity<Object> handleConflict(MessageNotFoundException msg ){
		
		var body = new ErrorMessage(msg.getMessage());
		
		return ResponseEntity
				.status( HttpStatusCode.valueOf(404))
				.body(body);
	}
	
	
	public record ErrorMessage(String errorMessage) {}
}