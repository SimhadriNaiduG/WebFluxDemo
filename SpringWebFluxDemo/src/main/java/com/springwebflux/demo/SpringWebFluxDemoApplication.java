package com.springwebflux.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@SpringBootApplication
public class SpringWebFluxDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebFluxDemoApplication.class, args);
	}

}
