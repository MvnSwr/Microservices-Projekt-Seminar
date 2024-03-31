package com.example.demo.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor //We can leave out the constructor due to this annotation
@Builder
public class Message {
    private UUID messageId;
    private UUID sendUserId;
    private UUID recieveUserId;
    private String content;
    private java.sql.Date date;
//    private List<Reaction> reactions;    
}