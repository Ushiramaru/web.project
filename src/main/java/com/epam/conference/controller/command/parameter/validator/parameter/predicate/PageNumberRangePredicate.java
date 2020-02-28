package com.epam.conference.controller.command.parameter.validator.parameter.predicate;

import java.util.function.Predicate;

public class PageNumberRangePredicate implements Predicate<Object> {

    private final Long minPageNumber;
    private final Long maxPageNumber;

    public PageNumberRangePredicate(Long minPageNumber, Long maxPageNumber) {
        this.minPageNumber = minPageNumber;
        this.maxPageNumber = maxPageNumber;
    }

    @Override
    public boolean test(Object o) {
        Long pageNumber = (Long) o;
        return pageNumber >= minPageNumber && pageNumber <= maxPageNumber;
    }

}