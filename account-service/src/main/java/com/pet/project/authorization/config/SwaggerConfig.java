package com.pet.project.authorization.config;

import static java.util.Arrays.asList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI customOpenAPI(final Info info, final SecurityRequirement securityRequirement) {
    return new OpenAPI().info(info).addSecurityItem(securityRequirement);
  }

  @Bean
  public SecurityRequirement securityRequirement() {
    return new SecurityRequirement().addList("bearer-jwt", asList("read", "write"));
  }

  @Bean
  public Info info() {
    return new Info()
        .title("Authorization API")
        .version("v1")
        .description("Contain solution on team pet project");
  }
}
