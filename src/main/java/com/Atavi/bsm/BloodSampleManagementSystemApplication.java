package com.Atavi.bsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling // This annotation helps to Schedule Mail - it's from Spring Started Web
@SpringBootApplication
public class BloodSampleManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloodSampleManagementSystemApplication.class, args);
	}

}
