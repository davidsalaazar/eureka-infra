package com.salazar.security.client;

import com.salazar.security.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;

@Slf4j
@Service
public class AuthorizationServiceClient {

    @Value("${service.authorization-server}")
    private URI authorizationServer;

    @Autowired
    private RestTemplate restTemplate;

    public void validateToken(String token) {
        log.info("VALIDATE TOKEN: {}", token);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put(HttpHeaders.AUTHORIZATION, Collections.singletonList(token));
        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
        try {
            this.restTemplate.postForEntity(this.authorizationServer, entity, Void.class);
        } catch (HttpClientErrorException e) {
            if (!HttpStatus.NOT_FOUND.equals(e.getStatusCode()))
                throw new UnauthorizedException("ACCESS DENIED, TOKEN HAS EXPIRED");
        }
    }
}
