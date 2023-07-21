package com.salazar.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudUsersAuthenticateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudUsersAuthenticateApplication.class);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}