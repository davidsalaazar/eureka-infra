package com.experience.oauth.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "session_data")
@Setter
@Getter
@NoArgsConstructor
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(length = 600)
    private String token;

    private Instant expiresIn;

    public SessionEntity(String username, String token, Instant expiresIn) {
        this.username = username;
        this.token = token;
        this.expiresIn = expiresIn;
    }
}
