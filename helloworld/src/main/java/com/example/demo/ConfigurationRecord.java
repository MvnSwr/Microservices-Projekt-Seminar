package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ps")
public record ConfigurationRecord(String endpoint) {
	// fast das gleiche wie die HelloWorldConfiguration
}