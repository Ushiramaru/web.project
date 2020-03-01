package com.epam.conference.controller.command.parameter.validator.parameter.predicate;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class DateRangePredicateTest {

    private final static String MIN_DATE = "1970-01-01T00:00";
    private final static String MAX_DATE = "2038-01-09T03:14";
    private final static DateRangePredicate PREDICATE = new DateRangePredicate(LocalDateTime.parse(MIN_DATE),
            LocalDateTime.parse(MAX_DATE));
    private final static LocalDateTime LESS_THEN_MIN_DATE = LocalDateTime.parse("1969-01-01T00:00");
    private final static LocalDateTime MORE_THEN_MAX_DATE = LocalDateTime.parse("2039-01-09T03:14");
    private final static LocalDateTime BETWEEN_MAX_AND_MIN_DATE = LocalDateTime.parse("2015-01-09T03:14");

    @Test
    public void testShouldBeReturnFalseWhenParameterLessThenMinDate() {
        boolean actual = PREDICATE.test(LESS_THEN_MIN_DATE);
        Assert.assertFalse(actual);
    }

    @Test
    public void testShouldBeReturnFalseWhenParameterMoreThenMaxDate() {
        boolean actual = PREDICATE.test(MORE_THEN_MAX_DATE);
        Assert.assertFalse(actual);
    }

    @Test
    public void testShouldBeReturnTrueWhenParameterBetweenMinAndMaxDate() {
        boolean actual = PREDICATE.test(BETWEEN_MAX_AND_MIN_DATE);
        Assert.assertTrue(actual);
    }

}