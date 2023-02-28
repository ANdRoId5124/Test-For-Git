package com.example.patternservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.example.patternservice.config")
public class PatternServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(PatternServiceApplication.class, args);
  }
}
