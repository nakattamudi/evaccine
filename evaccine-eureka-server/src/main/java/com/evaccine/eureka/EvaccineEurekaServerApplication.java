package com.evaccine.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EvaccineEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvaccineEurekaServerApplication.class, args);
	}

}
