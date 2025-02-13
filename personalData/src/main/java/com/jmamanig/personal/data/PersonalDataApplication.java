package com.jmamanig.personal.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScans({ @ComponentScan("com.jmamanig.personal.data.controller") })
@EnableJpaRepositories("com.jmamanig.personal.data.repository")
@EntityScan("com.jmamanig.personal.data.model")
public class PersonalDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalDataApplication.class, args);
	}

}
