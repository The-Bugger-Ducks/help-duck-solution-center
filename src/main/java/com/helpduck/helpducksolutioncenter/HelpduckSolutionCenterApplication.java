package com.helpduck.helpducksolutioncenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class HelpduckSolutionCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpduckSolutionCenterApplication.class, args);
	}

}
