package com.example.demo.component;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.HelloWorldContainer;
import com.example.demo.model.HelloWorldPostContainer;
import com.example.demo.model.HelloWorldPostRequestRecord;
import com.example.demo.model.HelloWorldRecord;
import com.example.demo.model.MessageDataRecord;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class HelloWorldServiceImpl implements HelloWorldService{

	public Mono<HelloWorldContainer> helloWorldCon() {
		
		var container = new HelloWorldContainer();
		container.setHelloWorldString("Hello World Container");
		
		return Mono.just(container); //Dieses Hello World wird dann im Web ausgegeben
	}
	
	/*
	 * 
	 * 
	 */

	public Mono<HelloWorldRecord> helloWorldRec() {

	var recordInstance = new HelloWorldRecord("Hallo Welt Record");
	
	return Mono.just(recordInstance);	
	}
	
	
	/*
	 * 
	 * 
	 */

	public Mono<String> postValues(@RequestBody String name) {
		// Wir geben in Postman einen POST ein und geben die URL an. Dann stellen wir
		//"Body" und "raw" ein und können einen Namen übergeben
		
		return Mono.just("Hello " + name);
	}
	
	/*
	 * 
	 * 
	 */

	public Mono<HelloWorldRecord>helloWorldPost(@RequestBody HelloWorldPostContainer container){
		// Wir erstellen eine neue JSON über Postman mit { "name": "Julius"} und wir
		// ändern von "Text" auf "JSON"
		
		var retVal = new HelloWorldRecord("Hello " + container.getName());
		
		return Mono.just(retVal);
	}

	public Mono<HelloWorldPostRequestRecord> helloWorldPostRequest(String name){
		
		var recVar = new HelloWorldPostRequestRecord("Hallo " + name);
		
		return Mono.just(recVar);
	}
	
	public Mono<MessageDataRecord> aufruf(){
		var recVar = new MessageDataRecord("Hallo ");
		return Mono.just(recVar);
	}

}
