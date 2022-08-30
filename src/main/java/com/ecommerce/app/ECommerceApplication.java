package com.ecommerce.app;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootApplication
public class ECommerceApplication
{
	private static final Logger logger = LoggerFactory.getLogger(ECommerceApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);

		logger.info("Project Started..");
	}






	

}
