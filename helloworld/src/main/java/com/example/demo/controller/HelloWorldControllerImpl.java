package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.ConfigurationRecord;
import com.example.demo.HelloWorldConfiguration;
import com.example.demo.component.HelloWorldService;
import com.example.demo.model.HelloWorldContainer;
import com.example.demo.model.HelloWorldPostContainer;
import com.example.demo.model.HelloWorldPostRequestRecord;
import com.example.demo.model.HelloWorldRecord;
import com.example.demo.model.Message;
import com.example.demo.model.MessageDataRecord;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HelloWorldControllerImpl implements HelloWorldController{
	
	
	final private HelloWorldService service;
	final private HelloWorldConfiguration config;
	final private ConfigurationRecord configRec;
	

	@GetMapping(
			path = "/helloCon", //Im Web| localhost:8090/hello ruft dann die Methode unten auf
			produces = MediaType.APPLICATION_JSON_VALUE 
			/*Eig. müsste ein JSON zurückgegeben werden, jedoch ist das unten String
			 * Mono<String> helloWorld()
			 * Jedoch haben wir jetzt noch ein anderes Objekt angelegt(HelloWorldContainer)
			*/
			)
	
	@Override
	public Mono<HelloWorldContainer> helloWorldCon() {
		return service.helloWorldCon();
	}
	
	/*
	 * 
	 * 
	 */
	
	//Hier das selbe mit Record
	
	@GetMapping(
			path = "/helloRec", //Webzusatz
			produces = MediaType.APPLICATION_JSON_VALUE 
			)

	@Override
	public Mono<HelloWorldRecord> helloWorldRec() {

	return service.helloWorldRec();
	}
	
	
	/*
	 * 
	 * 
	 */

	@PostMapping(
			path = "/begruessung",
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public Mono<String> postValues(@RequestBody String name) {
		
		return service.postValues("Hello" + name);
	}
	
	/*
	 * 
	 * 
	 */
	
	@PostMapping(
			path = "/hello", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public Mono<HelloWorldRecord>helloWorldPost(@RequestBody HelloWorldPostContainer container){
		// Liefert sobald wir über Postman den Post senden, kommt hier in der Console
		// die "Config" die wir in der application.yml definiert haben
		log.debug("config: {}", config.getDummyEndpoint());
		log.debug("configRec: {}", configRec.endpoint());

		return service.helloWorldPost(container);
	}

	/*
	 * 
	 * 
	 */

	@PostMapping(
			path = "helloPostRequest", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)

	public Mono<HelloWorldPostRequestRecord> helloWorldPostRequest(String name) {
		return service.helloWorldPostRequest(name);
	}
	
	/*
	 * 
	 * 
	 */
	
	@GetMapping(
			path = "Data"
			)
	public Mono<MessageDataRecord> mess(){
		return service.aufruf();
	}
	
	
	@GetMapping(
			path = "Test"
			)
	public Flux<Message> get(){
		List<UUID> ls = new ArrayList<UUID>();
		ls.add(UUID.fromString("5f32bf48-711b-4c43-8b26-0cdad8619739"));
		return WebClient.builder().baseUrl("http://localhost:8085").build().get()
				.uri("/getAllMessages")
				.retrieve()
				.bodyToFlux(Message.class);
	}
	
}
