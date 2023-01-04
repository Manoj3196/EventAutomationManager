package com.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LibapiApplication  {
    public static void main(String[] args) {
        SpringApplication.run(LibapiApplication.class, args);
        
        System.out.println("Server Started.....!");
    }

   
}
