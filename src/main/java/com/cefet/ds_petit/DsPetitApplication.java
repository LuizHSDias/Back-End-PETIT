package com.cefet.ds_petit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DsPetitApplication {

	public static void main(String[] args) {
		SpringApplication.run(DsPetitApplication.class, args);

		 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        String hash = encoder.encode("123456");
	        System.out.println(hash);
	}

}