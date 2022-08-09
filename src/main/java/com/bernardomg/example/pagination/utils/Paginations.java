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

package com.bernardomg.example.pagination.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.bernardomg.example.pagination.model.Direction;
import com.bernardomg.example.pagination.model.Sort;

/**
 * Pagination utility class.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
public final class Paginations {

    public static final Pageable toSpring(final Sort sort) {
        final Pageable                                       pageable;
        final org.springframework.data.domain.Sort.Direction direction;

        if (sort.getSorted()) {
            // Only sorted
            direction = toSpringDirection(sort.getDirection());
            pageable = PageRequest.of(0, 20, direction, sort.getProperty());
        } else {
            // Not paged nor sorted
            pageable = Pageable.unpaged();
        }

        return pageable;
    }

    private static final org.springframework.data.domain.Sort.Direction toSpringDirection(final Direction direction) {
        final org.springframework.data.domain.Sort.Direction result;

        if (Direction.ASC.equals(direction)) {
            result = org.springframework.data.domain.Sort.Direction.ASC;
        } else {
            result = org.springframework.data.domain.Sort.Direction.DESC;
        }

        return result;
    }

    private Paginations() {
        super();
    }

}
