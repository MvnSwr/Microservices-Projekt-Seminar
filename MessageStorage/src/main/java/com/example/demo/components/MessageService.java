package com.example.demo.components;


import java.util.List;
import java.util.UUID;

import com.example.demo.model.Message;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MessageService {
	Flux<Message> getAllMessages(List<UUID> msgs);
	
	Mono<Message> sendMessage(Message msg);
	
	Mono<Message> updateMessage(Message msg);
	
	Mono<Void> deleteMessage(UUID messageId);
}