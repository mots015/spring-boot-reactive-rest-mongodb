package com.stackroute.moviecruiser;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class MovieCruiserApplication {
	public static void main(final String[] args) {
		SpringApplication.run(MovieCruiserApplication.class, args);
	}
}
