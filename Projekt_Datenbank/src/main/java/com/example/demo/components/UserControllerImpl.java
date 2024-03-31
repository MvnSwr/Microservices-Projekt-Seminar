package com.example.demo.components;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ConfigurationRecord;
import com.example.demo.Configurationcl;
import com.example.demo.model.HelloWorldRecord;
import com.example.demo.model.User;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

	final private UserService service;
	final private Configurationcl config;
	final private ConfigurationRecord configRecord;

	@PutMapping( // sonst Post, Put ist nur neuer
			path = "/", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)

	@Override
	public Mono<User> createUser(@RequestBody User userTemplate) {

		return service.createUser(userTemplate);
	}

	@GetMapping(
			path = "/by/id/{id}", 
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	@Override
	public Mono<User> getUserById(@PathVariable UUID id) {

		return service.getUserById(id);
	}

	@PostMapping(
			path = "/", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	@Override
	public Mono<User> updateUser(@RequestBody User user) {

		return service.updateUser(user);
	}

	@DeleteMapping(
			path = "/by/id/{id}", 
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	@Override
	public Mono<Void> deleteUser(UUID id) {

		return service.deleteUser(id);
	}

	@GetMapping(
			path="greeting/{name}",
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	@Override
	public Mono<HelloWorldRecord> weitergabe(String name) {
		return service.weitergabe(name);
	}

}
