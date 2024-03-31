package com.example.demo.model.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.demo.model.User;

//Wir haben das erweitert mit dem CrudRepository (Crud = standardoperationen von Datenbanken)
public interface UserRepository extends ReactiveCrudRepository<User, UUID> {

}
