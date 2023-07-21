package com.experience.oauth.domain.dao;

import com.experience.oauth.domain.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
}
