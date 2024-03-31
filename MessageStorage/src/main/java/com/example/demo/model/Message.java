package com.example.demo.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("db__message")
public class Message {
	@Id
    private UUID messageId;
	
    private UUID sendUserId;
    private UUID recieveUserId;
    private String content;
    private java.time.LocalDateTime date;
}