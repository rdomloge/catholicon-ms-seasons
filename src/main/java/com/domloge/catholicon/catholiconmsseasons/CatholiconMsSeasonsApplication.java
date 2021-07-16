package com.domloge.catholicon.catholiconmsseasons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableMongoRepositories
@ComponentScan(basePackages= {"com.domloge"})
public class CatholiconMsSeasonsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatholiconMsSeasonsApplication.class, args);
	}
}
