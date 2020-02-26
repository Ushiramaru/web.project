package com.epam.conference.controller.command.parameter.validator.parameter.predicate;

import java.util.function.Predicate;

public class UncheckedParameterPredicate implements Predicate<Object> {

    @Override
    public boolean test(Object o) {
        return true;
    }

}