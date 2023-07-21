package com.experience.oauth.domain.dao;

import com.experience.oauth.domain.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionDao extends JpaRepository<SessionEntity, Long> {

    Optional<SessionEntity> findByUsername(String username);
}
