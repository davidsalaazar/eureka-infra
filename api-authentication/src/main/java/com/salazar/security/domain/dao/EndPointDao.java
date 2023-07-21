package com.salazar.security.domain.dao;

import com.salazar.security.domain.model.ApiModel;
import com.salazar.security.domain.model.EndPointModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EndPointDao extends JpaRepository<EndPointModel, Long> {
    List<EndPointModel> findByApi(ApiModel api);
}