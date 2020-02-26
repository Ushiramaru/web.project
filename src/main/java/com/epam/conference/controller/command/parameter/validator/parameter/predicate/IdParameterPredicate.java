package com.epam.conference.controller.command.parameter.validator.parameter.predicate;

import java.util.function.Predicate;

public class IdParameterPredicate implements Predicate<Object> {

    @Override
    public boolean test(Object o) {
        return ((Long) o).compareTo(0L) >= 0;
    }

}