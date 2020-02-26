package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.parameter.extractor.RequestParameterExtractor;
import com.epam.conference.controller.command.parameter.validator.RequestParameterValidator;

public abstract class AbstractCommand implements Command {

    private final RequestParameterExtractor parameterExtractor;
    private final RequestParameterValidator parameterValidator;

    protected AbstractCommand(RequestParameterExtractor parameterExtractor,
                              RequestParameterValidator parameterValidator) {
        this.parameterExtractor = parameterExtractor;
        this.parameterValidator = parameterValidator;
    }

    protected RequestParameterExtractor getParameterExtractor() {
        return parameterExtractor;
    }

    protected RequestParameterValidator getParameterValidator() {
        return parameterValidator;
    }

}