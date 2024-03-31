package com.example.demo.components;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.model.Message;
import com.example.demo.repositories.MessageNotFoundException;
import com.example.demo.repositories.MessageRepository;

import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceImpl implements MessageService{
	
	private final ConnectionFactory connection;
	@Autowired
	private final MessageRepository mrepo;
	
	@Override
	// I return a flux full of messages wich id's are given in a list
	public Flux<Message> getAllMessages(List<UUID> msgs) {
		log.debug("{} Message are going to be transmitted", msgs.size());
		
		return mrepo.findAllById(msgs)
					.switchIfEmpty(messageNotFound());	
	}
	
	@Override
	//A new message gets stored
	public Mono<Message> sendMessage(Message msg) { 
		log.debug("Message with id {} has been stored", msg.getMessageId() );
		
		var r2Template = new R2dbcEntityTemplate(connection);
		
		return r2Template.insert(msg);
	}
	
	@Override
	//We can update an existing message. E.g. there was a spelling mistake
	public Mono<Message> updateMessage(Message msg) {
		return mrepo.existsById(msg.getMessageId())
				.flatMap( res -> {
					if(res) {
						log.debug("Message {} has been updated", msg.getMessageId());
						return mrepo.save(msg);
					}
					return messageNotFound(msg.getMessageId());
				});
	}

	@Override
	//Message is deleted by UUID
	public Mono<Void> deleteMessage(UUID messageId) {
		return mrepo.existsById(messageId)
					.flatMap( res -> {
						if(res) {
							log.debug("Message with id: {} has been deleted!", messageId);
							return mrepo.deleteById(messageId);
						}
						log.debug("Message with id: {} was not found!", messageId);
						return messageNotFound(messageId).then();
					});
	}
	
	/*
	 * 
	 * Message exception handling
	 * 
	 */
	
	private Mono<Message> messageNotFound(){
		return Mono.error(new MessageNotFoundException("No Message was found"));
	}
	
	private Mono<Message> messageNotFound(UUID id){
		return Mono.error(new MessageNotFoundException(
				"Message with id {" + id.toString() + "} not found"));
	}
}