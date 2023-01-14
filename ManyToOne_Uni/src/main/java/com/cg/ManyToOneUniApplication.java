package com.cg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManyToOneUniApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManyToOneUniApplication.class, args);
		System.out.println("Student - Course Application Started");
	}

}
