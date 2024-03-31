package com.example.demo.components;

import java.util.UUID;

import com.example.demo.model.HelloWorldRecord;
import com.example.demo.model.User;

import reactor.core.publisher.Mono;

public interface UserController {
	// CRUD
	Mono<User> createUser(User userTemplate);

	Mono<User> getUserById(UUID id);

	Mono<User> updateUser(User user);

	Mono<Void> deleteUser(UUID id);
	
	Mono<HelloWorldRecord> weitergabe(String name);
}
