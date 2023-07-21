package com.salazar.security.domain.dao;

import com.salazar.security.domain.model.OwnerAppModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerAppDao extends JpaRepository<OwnerAppModel, Long> {
}