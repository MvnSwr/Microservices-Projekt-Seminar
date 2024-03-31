package com.example.demo.components;

import java.util.UUID;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.Configurationcl;
import com.example.demo.model.HelloWorldRecord;
import com.example.demo.model.User;
import com.example.demo.model.repositories.UserNotFoundException;
import com.example.demo.model.repositories.UserRepository;

import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	final private UserRepository repo;
	final private ConnectionFactory connection;
	final private Configurationcl config;

	@Override
	public Mono<User> createUser(User userTemplate) {
		userTemplate.setId(UUID.randomUUID());

		log.debug("User with id {} is going to be created",
				userTemplate.getId());

		var r2Template = new R2dbcEntityTemplate(connection);

		return r2Template.insert(userTemplate);
	}

	@Override
	public Mono<User> getUserById(UUID id) {

		return repo.findById(id) // return user
				.switchIfEmpty(userNotFound(id));
	}

	@Override
	public Mono<User> updateUser(User user) {
		// we first check if the user exists
		// however, we could also test for a empty
		// result and then use this as a indicator
		// that the update was unsuccessful
		return repo.existsById(user.getId())
		.flatMap( res -> {
			if(res) {
				return repo.save(user);
			}
			return userNotFound(user.getId());
		})
		;
	}

	@Override
	public Mono<Void> deleteUser(UUID id) { // Status 400 Bad Request

		return repo.existsById(id)
				.flatMap( res -> {
					if(res) {
						return repo.deleteById(id);
					}
					return userNotFound(id).then();
				})
				;
	}
	
	private Mono<User> userNotFound(UUID id){
		return Mono.error(new UserNotFoundException(
				"User of id {" + id.toString() + "} not found" ));
	}

	@Override
	public Mono<HelloWorldRecord> weitergabe(String name) {
		return 	null;/*WebClient.create(config.getServiceEndpointUrl())
				.post()
				.uri(config.getServiceEndpointUri())
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue("{\"name\":\"" + name + "\"}")
				.retrieve()
				.bodyToMono(HelloWorldRecord.class);*/
	}

}
