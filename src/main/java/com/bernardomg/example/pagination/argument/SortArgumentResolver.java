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

package com.bernardomg.example.pagination.argument;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.bernardomg.example.pagination.model.Direction;
import com.bernardomg.example.pagination.model.Sort;

import lombok.extern.slf4j.Slf4j;

/**
 * Argument resolver for sorting data.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
@Slf4j
public final class SortArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * Default constructor.
     */
    public SortArgumentResolver() {
        super();
    }

    @Override
    public final Sort resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer,
            final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) throws Exception {
        final String    sortText;
        final String    property;
        final String[]  sortPieces;
        final Direction direction;
        final Sort      sort;
        final String    rawDirection;

        sortText = webRequest.getParameter("sort");

        if (sortText == null) {
            // No sort
            sort = Sort.disabled();
            log.trace("No sort received, using disabled sort");
        } else {
            log.trace("Received sort code: {}", sortText);
            sortPieces = sortText.split(",");

            if (sortPieces.length == 0) {
                // Invalid sort
                sort = Sort.disabled();
                log.warn("Invalid sort command: {}. Disabling sort", sortText);
            } else {
                property = sortPieces[0];

                if (sortPieces.length == 1) {
                    // No direction
                    direction = Direction.ASC;
                    log.trace("No sort direction received, using default direction: {}", direction);
                } else {
                    rawDirection = sortPieces[1].toLowerCase();
                    if ("desc".equals(rawDirection)) {
                        direction = Direction.DESC;
                    } else {
                        direction = Direction.ASC;
                    }
                }
                log.trace("Sorting by property {} and direction {}", property, direction);
                sort = Sort.of(property, direction);
            }
        }

        return sort;
    }

    @Override
    public final boolean supportsParameter(final MethodParameter parameter) {
        return Sort.class.equals(parameter.getParameterType());
    }

}
