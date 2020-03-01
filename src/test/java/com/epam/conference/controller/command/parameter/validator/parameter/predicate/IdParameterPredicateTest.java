package com.epam.conference.controller.command.parameter.validator.parameter.predicate;

import org.junit.Assert;
import org.junit.Test;

public class IdParameterPredicateTest {

    private final static Long CORRECT_ID = 1L;
    private final static Long INCORRECT_ID = -1L;
    private final static IdParameterPredicate PREDICATE = new IdParameterPredicate();

    @Test
    public void testShouldBeReturnTrueWhenParameterOne() {
        boolean actual = PREDICATE.test(CORRECT_ID);
        Assert.assertTrue(actual);
    }

    @Test
    public void testShouldBeReturnFalseWhenParameterMinusOne() {
        boolean actual = PREDICATE.test(INCORRECT_ID);
        Assert.assertFalse(actual);
    }

}