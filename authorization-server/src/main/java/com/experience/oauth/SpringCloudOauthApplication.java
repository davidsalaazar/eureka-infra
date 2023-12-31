package com.experience.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class SpringCloudOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudOauthApplication.class);
    }
}
