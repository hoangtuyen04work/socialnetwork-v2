package com.hoangtuyen04work.socialnetwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SocialnetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialnetworkApplication.class, args);
	}

}
