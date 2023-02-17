package com.gorica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GoricaTimesheetAutomationApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoricaTimesheetAutomationApplication.class, args);
	}

}
