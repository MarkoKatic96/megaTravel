package io.webxml.korisnikservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class KorisnikServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KorisnikServiceApplication.class, args);
	}

}
