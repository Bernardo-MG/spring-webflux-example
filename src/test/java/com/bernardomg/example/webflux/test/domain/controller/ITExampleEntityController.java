
package com.bernardomg.example.webflux.test.domain.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bernardomg.example.webflux.domain.model.ExampleEntity;
import com.bernardomg.example.webflux.test.configuration.annotation.IntegrationTest;

@IntegrationTest
@DisplayName("Example entity controller")
public class ITExampleEntityController {

    private final WebTestClient client;

    public ITExampleEntityController() {
        super();

        final RouterFunction<?> function;

        function = RouterFunctions.route(RequestPredicates.GET("/entity"), request -> ServerResponse.ok()
            .build());

        client = WebTestClient.bindToRouterFunction(function)
            .build();
    }

    @Test
    @DisplayName("Returns the expected data")
    void testGetEmployeesByName() {
        client.get()
            .uri("/entity")
            .header(HttpHeaders.ACCEPT, "application/json")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBodyList(ExampleEntity.class);
    }

}
