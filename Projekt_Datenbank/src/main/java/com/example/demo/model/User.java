package com.example.demo.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("ps__user")
public class User {

	@Id
	private UUID id;

	private String name;

	@Column("first_name") // wird auch automatisch von Spring gemacht
	private String firstname;
}
