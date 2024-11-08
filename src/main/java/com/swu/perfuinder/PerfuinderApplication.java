package com.swu.perfuinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PerfuinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerfuinderApplication.class, args);
	}

}
