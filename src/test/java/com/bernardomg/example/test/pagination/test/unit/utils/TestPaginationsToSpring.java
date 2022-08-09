
package com.bernardomg.example.test.pagination.test.unit.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import com.bernardomg.example.pagination.model.Direction;
import com.bernardomg.example.pagination.model.Sort;
import com.bernardomg.example.pagination.utils.Paginations;

@DisplayName("Pagination utils - Pagination to Spring model")
public class TestPaginationsToSpring {

    public TestPaginationsToSpring() {
        super();
    }

    @Test
    @DisplayName("With an ascending sort the resulting sort is enabled")
    public void testSort_Ascending_Sorted() {
        final Sort       sort;
        final Pageable   result;

        sort = Sort.of("field", Direction.ASC);

        result = Paginations.toSpring(sort);

        Assertions.assertTrue(result.getSort()
            .isSorted());
    }

    @Test
    @DisplayName("Applies the correct values for ascending order")
    public void testSort_Ascending_SortValues() {
        final Sort       sort;
        final Pageable   result;

        sort = Sort.of("field", Direction.ASC);

        result = Paginations.toSpring(sort);

        Assertions.assertEquals(1, result.getSort()
            .toList()
            .size());
        Assertions.assertEquals("field", result.getSort()
            .iterator()
            .next()
            .getProperty());
        Assertions.assertEquals(org.springframework.data.domain.Sort.Direction.ASC, result.getSort()
            .iterator()
            .next()
            .getDirection());
    }

    @Test
    @DisplayName("With a descending sort the resulting sort is enabled")
    public void testSort_Descending_Sorted() {
        final Sort       sort;
        final Pageable   result;

        sort = Sort.of("field", Direction.DESC);

        result = Paginations.toSpring(sort);

        Assertions.assertTrue(result.getSort()
            .isSorted());
    }

    @Test
    @DisplayName("Applies the correct values for descending order")
    public void testSort_Descending_SortValues() {
        final Sort       sort;
        final Pageable   result;

        sort = Sort.of("field", Direction.DESC);

        result = Paginations.toSpring(sort);

        Assertions.assertEquals(1, result.getSort()
            .toList()
            .size());
        Assertions.assertEquals("field", result.getSort()
            .iterator()
            .next()
            .getProperty());
        Assertions.assertEquals(org.springframework.data.domain.Sort.Direction.DESC, result.getSort()
            .iterator()
            .next()
            .getDirection());
    }

    @Test
    @DisplayName("Applies the correct values for a disabled pagination with sort")
    public void testSort_DisabledPagination_PaginationValues() {
        final Sort       sort;
        final Pageable   result;

        sort = Sort.of("field", Direction.ASC);

        result = Paginations.toSpring(sort);

        Assertions.assertEquals(0, result.getOffset());
        Assertions.assertEquals(0, result.getPageNumber());
        Assertions.assertEquals(20, result.getPageSize());
    }

    @Test
    @DisplayName("With disabled pagination the resulting sort is disabled")
    public void testSort_DisabledPagination_Sorted() {
        final Sort       sort;
        final Pageable   result;

        sort = Sort.of("field", Direction.ASC);

        result = Paginations.toSpring(sort);

        Assertions.assertTrue(result.getSort()
            .isSorted());
    }

    @Test
    @DisplayName("With disabled pagination the resulting sort contains all the data")
    public void testSort_DisabledPagination_SortValues() {
        final Sort       sort;
        final Pageable   result;

        sort = Sort.of("field", Direction.ASC);

        result = Paginations.toSpring(sort);

        Assertions.assertEquals(1, result.getSort()
            .toList()
            .size());
        Assertions.assertEquals("field", result.getSort()
            .iterator()
            .next()
            .getProperty());
        Assertions.assertEquals(org.springframework.data.domain.Sort.Direction.ASC, result.getSort()
            .iterator()
            .next()
            .getDirection());
    }

    @Test
    @DisplayName("Applies the correct values for a disabled sort with pagination")
    public void testSort_DisabledSort_PaginationValues() {
        final Sort       sort;
        final Pageable   result;

        sort = Sort.disabled();

        result = Paginations.toSpring(sort);

        Assertions.assertEquals(0, result.getOffset());
        Assertions.assertEquals(0, result.getPageNumber());
        Assertions.assertEquals(10, result.getPageSize());
    }

    @Test
    @DisplayName("With disabled sort the resulting sort contains no data")
    public void testSort_DisabledSort_SortValues() {
        final Sort       sort;
        final Pageable   result;

        sort = Sort.disabled();

        result = Paginations.toSpring(sort);

        Assertions.assertEquals(0, result.getSort()
            .toList()
            .size());
    }

    @Test
    @DisplayName("With disabled sort the resulting sort is disabled")
    public void testSort_DisabledSort_Unsorted() {
        final Sort       sort;
        final Pageable   result;

        sort = Sort.disabled();

        result = Paginations.toSpring(sort);

        Assertions.assertFalse(result.getSort()
            .isSorted());
    }

}
