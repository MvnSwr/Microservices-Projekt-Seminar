package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@EnableConfigurationProperties(ConfigurationRecord.class) // brauchen wir für die ConfigurationRecord
@Data
public class HelloWorldConfiguration {

	@Value("${ps.dummyEndpoint}")
	private String dummyEndpoint;
	// Das haben wie in der application.yml genutzt für eine log-Meldung
}
