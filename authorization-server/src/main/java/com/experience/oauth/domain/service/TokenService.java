package com.experience.oauth.domain.service;

import com.experience.oauth.domain.dao.SessionDao;
import com.experience.oauth.domain.dto.TokenResponse;
import com.experience.oauth.domain.entities.SessionEntity;
import com.experience.oauth.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TokenService {

    @Value("${jwt.expiresIn}")
    private long jwtExpiration;

    private final JwtEncoder jwtEncoder;
    private final SessionDao sessionDao;

    public TokenService(JwtEncoder jwtEncoder, SessionDao sessionDao) {
        this.jwtEncoder = jwtEncoder;
        this.sessionDao = sessionDao;
    }

    public TokenResponse generateToken(Authentication authentication) {
        Instant currentDate = Instant.now();

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        TokenResponse tokenSaved = this.retrieveLastToken(authentication.getName());
        if (tokenSaved == null) {
            JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                    .issuer(Constants.SELF_SIGNED)
                    .issuedAt(currentDate)
                    .expiresAt(currentDate.plusSeconds(this.jwtExpiration))
                    .subject(authentication.getName())
                    .claim(Constants.SCOPE, scope)
                    .build();
            String token = this.jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
            long expiresIn = this.jwtExpiration + Constants.EXPIRATION;

            this.saveToken(authentication.getName(), token, claimsSet.getExpiresAt());
            return new TokenResponse(token, expiresIn);
        }
        return tokenSaved;
    }

    private TokenResponse retrieveLastToken(String username) {
        log.info("RETRIEVE LAST SESSION");
        Optional<SessionEntity> session = this.sessionDao.findByUsername(username);

        if (session.isPresent()) {
            log.info("CHECK IF TOKEN IS VALIDATED");
            long seconds = Duration.between(Instant.now(), session.get().getExpiresIn()).getSeconds();
            if (seconds > 4) {
                log.info("TOKEN IS VALID, SECONDS TO EXPIRE: {}", seconds);
                return new TokenResponse(session.get().getToken(), seconds);
            }
        }
        log.info("SESSION IS EMPTY");
        return null;
    }

    private void saveToken(String username, String token, Instant instant) {
        log.info("SAVE TOKEN: {} FOR:: {}", token, username);
        Optional<SessionEntity> session = this.sessionDao.findByUsername(username);

        if (session.isPresent()) {
            session.get().setExpiresIn(instant);
            session.get().setToken(token);
        } else {
            session = Optional.of(new SessionEntity(username, token, instant.plusSeconds(Constants.EXPIRATION)));
        }
        this.sessionDao.save(session.get());
    }
}
