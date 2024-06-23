package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@SuppressWarnings("unused")
@Slf4j
@RequiredArgsConstructor
public class ControllerImpl implements Controller{

	final private MessageService service;
	
	@PostMapping(
			path = "/getAllMessages",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public Flux<Message> getAllMessages(@RequestBody List<UUID> list) {
		return service.getAllMessages(list);
	}

	@PutMapping(
			path = "/storeMessage",
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public Mono<Message> sendMessage(@RequestBody Message msg){
		return service.sendMessage(msg);
	}
	
	@PutMapping(
			path = "/updateMessage",
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public Mono<Message> updateMessage(@RequestBody Message msg) {
		return service.updateMessage(msg);
	}
	
	@DeleteMapping(
			path = "/deleteMessage/{id}"
			)
	public Mono<Void> deleteMessage(@PathVariable("id") UUID id) {
		return service.deleteMessage(id);
	}
}