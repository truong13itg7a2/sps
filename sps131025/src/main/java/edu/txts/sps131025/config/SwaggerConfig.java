package edu.txts.sps131025.config;

package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI apiDoc() {
		return new OpenAPI()
				.info(new Info()
						.title("My API")
						.description("API documentation")
						.version("1.0.0"));
	}
}

