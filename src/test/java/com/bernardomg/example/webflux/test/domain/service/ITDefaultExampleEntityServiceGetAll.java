
package com.bernardomg.example.webflux.test.domain.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bernardomg.example.webflux.domain.model.ExampleEntity;
import com.bernardomg.example.webflux.domain.service.DefaultExampleEntityService;
import com.bernardomg.example.webflux.test.configuration.annotation.IntegrationTest;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@IntegrationTest
@DisplayName("Default example entity service - Reading all the entities")
public class ITDefaultExampleEntityServiceGetAll {

    @Autowired
    private DefaultExampleEntityService service;

    @Test
    @DisplayName("Returns all the data")
    public void testFindAll_Count() {
        final Flux<ExampleEntity> data;

        data = service.getAll();

        StepVerifier.create(data)
            .expectNextMatches(entity -> "Entity 1".equals(entity.getName()))
            .expectNextMatches(entity -> "Entity 2".equals(entity.getName()))
            .expectNextMatches(entity -> "Entity 3".equals(entity.getName()))
            .expectComplete()
            .verify();
    }

}
