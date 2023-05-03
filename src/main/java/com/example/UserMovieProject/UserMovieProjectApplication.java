package com.example.UserMovieProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserMovieProjectApplication {

	public static void main(String[] args) {
		System.setProperty("https.protocols", "TLSv1.1");
		SpringApplication.run(UserMovieProjectApplication.class, args);
	}
}
