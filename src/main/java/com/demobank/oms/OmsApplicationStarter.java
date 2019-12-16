package com.demobank.oms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.demobank.oms")
public class OmsApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(OmsApplicationStarter.class, args);
	}

}
