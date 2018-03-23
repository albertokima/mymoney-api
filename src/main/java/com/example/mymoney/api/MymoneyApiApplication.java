package com.example.mymoney.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.mymoney.api.config.property.MyMoneyApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(MyMoneyApiProperty.class)
public class MymoneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MymoneyApiApplication.class, args);
	}
}
