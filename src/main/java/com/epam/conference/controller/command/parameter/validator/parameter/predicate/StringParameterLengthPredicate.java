package com.epam.conference.controller.command.parameter.validator.parameter.predicate;

import java.util.function.Predicate;

public class StringParameterLengthPredicate implements Predicate<Object> {

    private final int maxLength;

    public StringParameterLengthPredicate(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public boolean test(Object o) {
        return ((String) o).length() <= maxLength;
    }

}