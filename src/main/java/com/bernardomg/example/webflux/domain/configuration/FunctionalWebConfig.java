
package com.bernardomg.example.webflux.domain.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bernardomg.example.webflux.domain.model.ExampleEntity;
import com.bernardomg.example.webflux.domain.service.ExampleEntityService;

import reactor.core.publisher.Mono;

@Configuration
public class FunctionalWebConfig {

    /**
     * Example entity service.
     */
    @Autowired
    private ExampleEntityService service;

    public FunctionalWebConfig() {
        super();
    }

    @Bean
    public RouterFunction<ServerResponse> getExampleEntities() {
        final RequestPredicate route;

        route = RequestPredicates.GET("/functional/entity")
            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

        return RouterFunctions.route(route, this::getAllUsers);
    }

    private final Mono<ServerResponse> getAllUsers(final ServerRequest request) {
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(service.getAll(), ExampleEntity.class);
    }

}
