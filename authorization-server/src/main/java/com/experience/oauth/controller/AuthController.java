package com.experience.oauth.controller;

import com.experience.oauth.domain.dto.TokenResponse;
import com.experience.oauth.domain.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenResponse> generateToken(Authentication authentication) {
        log.info("GENERATE TOKEN FOR::::{}", authentication);
        TokenResponse tokenResponse = this.tokenService.generateToken(authentication);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }
}
