
package com.bernardomg.example.webflux.domain.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bernardomg.example.webflux.domain.model.ExampleEntity;
import com.bernardomg.example.webflux.domain.service.ExampleEntityService;

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
        return RouterFunctions.route(RequestPredicates.GET("/reactive/entity"), req -> ServerResponse.ok()
            .body(service.getAll(), ExampleEntity.class));
    }

}
