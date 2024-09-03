package com.jsp.rest.ets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EduTrackingSystemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduTrackingSystemsApplication.class, args);
	}

}
