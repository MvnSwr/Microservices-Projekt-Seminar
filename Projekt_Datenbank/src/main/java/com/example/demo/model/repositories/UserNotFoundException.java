package com.example.demo.model.repositories;

public class UserNotFoundException extends Throwable {

	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException() {
		super();
	}
	
	public UserNotFoundException(String msg) {
		super(msg);
	}

}