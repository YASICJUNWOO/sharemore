package com.kjw.sharemore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class SharemoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SharemoreApplication.class, args);
	}

}
