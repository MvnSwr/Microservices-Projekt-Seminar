package com.example.demo.component;

import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.HelloWorldContainer;
import com.example.demo.model.HelloWorldPostContainer;
import com.example.demo.model.HelloWorldPostRequestRecord;
import com.example.demo.model.HelloWorldRecord;
import com.example.demo.model.MessageDataRecord;

import reactor.core.publisher.Mono;

public interface HelloWorldService {
	Mono<HelloWorldContainer> helloWorldCon(); //Mono für Reaktiv Programmieren
	Mono<HelloWorldRecord> helloWorldRec(); 
	Mono<String> postValues(@RequestBody String name);
	Mono<HelloWorldRecord> helloWorldPost(HelloWorldPostContainer container);
	Mono<HelloWorldPostRequestRecord> helloWorldPostRequest(String name);
	Mono<MessageDataRecord> aufruf();
}
