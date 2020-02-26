package com.epam.conference.controller.command.parameter.validator.parameter.predicate;

import java.util.function.Predicate;

public class StringParameterLengthArrayPredicate implements Predicate<Object> {

    private final int maxLength;

    public StringParameterLengthArrayPredicate(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public boolean test(Object o) {
        String[] strings = (String[]) o;
        boolean result = true;
        for (String s : strings) {
            result &= s.length() <= maxLength;
        }

        return result;
    }

}