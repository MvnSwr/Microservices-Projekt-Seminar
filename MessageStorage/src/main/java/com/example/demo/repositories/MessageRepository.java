package com.example.demo.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Message;

@Repository
public interface MessageRepository extends ReactiveCrudRepository<Message, UUID>{

}