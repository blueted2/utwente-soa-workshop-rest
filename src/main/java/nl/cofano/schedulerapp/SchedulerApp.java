package nl.cofano.schedulerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
/**
 * Main class for starting the spring-boot application
 */
public class SchedulerApp {
	public static void main(String[] args) {
		SpringApplication.run(SchedulerApp.class, args);
	}
}
