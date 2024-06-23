package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Message;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Controller {
	Flux<Message> getAllMessages(@RequestBody List<UUID> list);
	
	Mono<Message> sendMessage(@RequestBody Message msg);
	
	Mono<Message> updateMessage (@RequestBody Message msg);
	
	Mono<Void> deleteMessage(@PathVariable("id") UUID id);
}