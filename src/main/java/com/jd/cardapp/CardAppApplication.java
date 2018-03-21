package com.jd.cardapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jd.cardapp.dao")
public class CardAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardAppApplication.class, args);
	}
}
