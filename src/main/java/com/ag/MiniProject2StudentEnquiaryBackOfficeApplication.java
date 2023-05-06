package com.ag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MiniProject2StudentEnquiaryBackOfficeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniProject2StudentEnquiaryBackOfficeApplication.class, args);
	}

}
