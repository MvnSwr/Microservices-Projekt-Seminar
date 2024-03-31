package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class HelloWorldPostContainer {
	
	@JsonProperty("name")
	private String name;
}