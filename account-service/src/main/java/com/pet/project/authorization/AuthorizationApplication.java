package com.pet.project.authorization;

import com.pet.project.authorization.config.RsaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaProperties.class)
@ConfigurationPropertiesScan("com.pet.project.authorization.config")
public class AuthorizationApplication {
  public static void main(final String[] args) {
    SpringApplication.run(AuthorizationApplication.class, args);
  }
}
