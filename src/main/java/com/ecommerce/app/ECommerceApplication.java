package com.ecommerce.app;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootApplication
@EnableSwagger2
public class ECommerceApplication
{
	private static final Logger logger = LoggerFactory.getLogger(ECommerceApplication.class);


	public static void main(String[] args) {
		//SpringApplication.run(ECommerceApplication.class, args);
		try {
			SpringApplication.run(ECommerceApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Project Started..");
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.ecommerce.app")).build().apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo("Student_Demo", "Student details", "1.0", "Free to use",
				new springfox.documentation.service
						.Contact("Onkar Tayde", "www.getmoreinfo.com", "onkartayade62@@gmail.com"),
				"93", "jnefnsiu",Collections.emptyList());
	}



	

}
