package org.practice.module4practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(UniversityHandler uh) {
        return RouterFunctions.route(RequestPredicates.GET("/uni"), uh::handleGetByName)
                .andRoute(RequestPredicates.POST("/uni"), uh::handleCreateUniversity)
                .andRoute(RequestPredicates.GET("/all"), uh::handleBackPressure);
    }
}
