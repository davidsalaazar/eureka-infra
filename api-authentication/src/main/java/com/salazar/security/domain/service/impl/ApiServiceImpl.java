package com.salazar.security.domain.service.impl;

import com.salazar.security.domain.dao.EndPointDao;
import com.salazar.security.domain.dao.OwnerAppDao;
import com.salazar.security.domain.model.ApiModel;
import com.salazar.security.domain.model.EndPointModel;
import com.salazar.security.domain.model.OwnerAppModel;
import com.salazar.security.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ApiServiceImpl {

    @Autowired
    private OwnerAppDao ownerAppDao;

    @Autowired
    private EndPointDao endPointDao;

    public void authenticateOwnerApi(Long ownerId, String endpoint) {
        log.info("AUTHENTICATE OWNER API");
        ApiModel api = this.validateApiAccess(endpoint, ownerId);
        this.validateEndpointAccess(api, endpoint);
    }

    private ApiModel validateApiAccess(String endpoint, Long ownerId) {
        log.info("VALIDATE API ACCESS, ENDPOINT: {}, OWNER ID: {}", endpoint, ownerId);
        OwnerAppModel owner = this.ownerAppDao.findById(ownerId).orElseThrow(() -> new UnauthorizedException("ACCESS DENIED TO THIS API"));
        for (ApiModel api : owner.getApi()) if (endpoint.contains(api.getName())) return api;
        throw new UnauthorizedException("ACCESS DENIED TO THIS API");
    }

    private void validateEndpointAccess(ApiModel api, String endpoint) {
        log.info("VALIDATE ENDPOINT API: {}", api);
        List<EndPointModel> lEndpoint = this.endPointDao.findByApi(api);
        for (EndPointModel endpointN : lEndpoint) if (endpoint.contains(endpointN.getPath())) return;
        throw new UnauthorizedException("ACCESS DENIED TO THIS ENDPOINT");
    }
}
