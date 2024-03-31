package com.example.demo.repositories;

public class MessageNotFoundException extends Throwable{

	private static final long serialVersionUID = 1L;
	
	public MessageNotFoundException() {
		super();
	}
	
	public MessageNotFoundException(String msg) {
		super(msg);
	}
}
