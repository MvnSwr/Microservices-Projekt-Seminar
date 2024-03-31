package com.example.demo;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@EnableConfigurationProperties(ConfigurationRecord.class)
@Data
public class Configurationcl {

//	@Value("${ps.ServiceEndpoint.url}")
//	private String ServiceEndpointUrl;
//	
//	@Value("${ps.ServiceEndpoint.uri")
//	private String ServiceEndpointUri;
}
