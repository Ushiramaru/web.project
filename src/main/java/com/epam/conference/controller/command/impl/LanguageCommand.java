package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.parameter.extractor.RequestParameterExtractor;
import com.epam.conference.controller.command.parameter.validator.RequestParameterValidator;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LanguageCommand extends AbstractCommand {

    private final static String LOCALE_PARAMETER_NAME = "l";
    private final static String LANGUAGE_ATTRIBUTE_NAME = "language";
    private final static String BACK_PARAMETER_NAME = "back";
    private final static String PARAMETER_DELIMITER_IN_BACK_PARAMETER = "__";
    private final static String STANDARD_PARAMETER_DELIMITER = "&";
    private final static String CONTROLLER_URL = "controller?";
    private final static String INVALID_PARAMETER_MESSAGE = "Invalid ? value";

    public LanguageCommand(RequestParameterExtractor parameterExtractor,
                            RequestParameterValidator parameterValidator) {
        super(parameterExtractor, parameterValidator);
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        RequestParameterExtractor extractor = super.getParameterExtractor();
        RequestParameterValidator validator = super.getParameterValidator();
        String language = extractor.extractString(request, LOCALE_PARAMETER_NAME);
        if (!validator.isValid(LOCALE_PARAMETER_NAME, language)) {
            throw new ServiceException(INVALID_PARAMETER_MESSAGE.replace("?", LOCALE_PARAMETER_NAME));
        }
        HttpSession session = request.getSession();
        session.setAttribute(LANGUAGE_ATTRIBUTE_NAME, language);
        String back = extractor.extractString(request, BACK_PARAMETER_NAME);
        if (!validator.isValid(BACK_PARAMETER_NAME, back)) {
            throw new ServiceException(INVALID_PARAMETER_MESSAGE.replace("?", BACK_PARAMETER_NAME));
        }
        back = back.replace(PARAMETER_DELIMITER_IN_BACK_PARAMETER, STANDARD_PARAMETER_DELIMITER);

        return CommandResult.redirect(CONTROLLER_URL + back);
    }

}