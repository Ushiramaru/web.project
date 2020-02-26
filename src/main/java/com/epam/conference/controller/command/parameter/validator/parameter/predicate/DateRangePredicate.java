package com.epam.conference.controller.command.parameter.validator.parameter.predicate;

import java.time.LocalDateTime;
import java.util.function.Predicate;

public class DateRangePredicate implements Predicate<Object> {

    private final LocalDateTime minValue;
    private final LocalDateTime maxValue;

    public DateRangePredicate(LocalDateTime minValue, LocalDateTime maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public boolean test(Object o) {
        LocalDateTime dateTime = (LocalDateTime) o;

        return dateTime.isAfter(minValue) && dateTime.isBefore(maxValue);
    }

}