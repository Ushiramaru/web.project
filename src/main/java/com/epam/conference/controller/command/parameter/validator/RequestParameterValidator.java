package com.epam.conference.controller.command.parameter.validator;

public interface RequestParameterValidator {

    boolean isValid(String name, Object value);

}