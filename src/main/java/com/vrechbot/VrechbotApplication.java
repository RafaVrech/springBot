package com.vrechbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories
@EnableFeignClients
@EnableScheduling
public class VrechbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(VrechbotApplication.class, args);
	}

}
