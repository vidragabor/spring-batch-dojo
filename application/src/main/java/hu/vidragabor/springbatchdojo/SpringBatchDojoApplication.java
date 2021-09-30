package hu.vidragabor.springbatchdojo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBatchDojoApplication {
	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(SpringBatchDojoApplication.class, args)));
	}
}
