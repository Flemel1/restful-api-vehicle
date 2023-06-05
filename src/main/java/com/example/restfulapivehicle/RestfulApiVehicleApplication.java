package com.example.restfulapivehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
public class RestfulApiVehicleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulApiVehicleApplication.class, args);
	}

}
