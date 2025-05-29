package com.br.biosentinela;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BiosentinelaApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(BiosentinelaApiApplication.class, args);
	}
}
