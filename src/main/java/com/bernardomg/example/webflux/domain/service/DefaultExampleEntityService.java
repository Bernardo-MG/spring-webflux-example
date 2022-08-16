/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2022 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.bernardomg.example.webflux.domain.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.bernardomg.example.webflux.domain.model.DefaultExampleEntity;
import com.bernardomg.example.webflux.domain.model.ExampleEntity;

import reactor.core.publisher.Flux;

/**
 * Default implementation of the example entity service.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
@Service
public class DefaultExampleEntityService implements ExampleEntityService {

    /**
     * Constructs an entities service with the specified repository.
     */
    public DefaultExampleEntityService() {
        super();
    }

    @Override
    public final Flux<ExampleEntity> getAllEntities() {
        final ExampleEntity entity1;
        final ExampleEntity entity2;
        final ExampleEntity entity3;

        entity1 = new DefaultExampleEntity();
        entity1.setId(1);
        entity1.setName("Entity 1");

        entity2 = new DefaultExampleEntity();
        entity2.setId(2);
        entity2.setName("Entity 2");

        entity3 = new DefaultExampleEntity();
        entity3.setId(3);
        entity3.setName("Entity 3");

        return Flux.fromIterable(Arrays.asList(entity1, entity2, entity3));
    }

}
