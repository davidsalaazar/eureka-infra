package com.salazar.security.exception;

import com.salazar.security.util.Constants;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class ApiGwHandler extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Throwable error = super.getError(request);

        Map<String, Object> map = new HashMap<>();
        map.put(Constants.TIME_STAMP, LocalDateTime.now());
        if (error instanceof ResponseStatusException) {
            map.put(Constants.STATUS, HttpStatus.UNPROCESSABLE_ENTITY.value());
            map.put(Constants.MESSAGE, Constants.SERVICE_UNAVAILABLE);
        } else {
            map.put(Constants.STATUS, HttpStatus.UNAUTHORIZED.value());
            map.put(Constants.MESSAGE, error.getMessage());
        }
        return map;
    }
}
