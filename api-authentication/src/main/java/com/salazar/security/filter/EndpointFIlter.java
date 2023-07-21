package com.salazar.security.filter;

import com.salazar.security.client.AuthorizationServiceClient;
import com.salazar.security.domain.service.impl.ApiServiceImpl;
import com.salazar.security.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
public class EndpointFIlter implements GlobalFilter, Ordered {

    @Autowired
    private ApiServiceImpl apiService;

    @Autowired
    private AuthorizationServiceClient authorizationServiceClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        this.validateAccess(exchange.getRequest());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }

    private void validateAccess(ServerHttpRequest httpRequest) {
        List<String> lAuthorization = httpRequest.getHeaders().get(HttpHeaders.AUTHORIZATION);
        List<String> lOwner = httpRequest.getHeaders().get(Constants.OWNER_ID);

        if (lAuthorization == null) throw new RuntimeException("TOKEN HAS EXPIRED");
        if (lOwner == null) throw new RuntimeException("OWNER IS EMPTY");

        String authorization = lAuthorization.get(0);
        Long ownerId = Long.valueOf(lOwner.get(0));
        log.info("VALIDATE ACCESS FOR OWNER ID: {}, WITH TOKEN: {}", ownerId, authorization);

        String endpoint = httpRequest.getPath().toString();
        log.info("CURRENT ENDPOINT:: {}", endpoint);
        this.apiService.authenticateOwnerApi(ownerId, endpoint);
        this.authorizationServiceClient.validateToken(authorization);
    }
}
