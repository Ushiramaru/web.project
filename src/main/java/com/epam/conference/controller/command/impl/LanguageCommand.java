package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.ParameterExtractor;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LanguageCommand implements Command {

    private final static String LOCALE_PARAMETER_NAME = "l";
    private final static String LANGUAGE_ATTRIBUTE_NAME = "language";
    private final static String BACK_PARAMETER_NAME = "back";
    private final static String PARAMETER_DELIMITER_IN_BACK_PARAMETER = "__";
    private final static String STANDARD_PARAMETER_DELIMITER = "&";
    private final static String CONTROLLER_URL = "controller?";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ParameterExtractor extractor = new ParameterExtractor();
        String language = extractor.extractParameter(request, LOCALE_PARAMETER_NAME);
        HttpSession session = request.getSession();
        session.setAttribute(LANGUAGE_ATTRIBUTE_NAME, language);
        String back = extractor.extractParameter(request, BACK_PARAMETER_NAME);
        back = back.replace(PARAMETER_DELIMITER_IN_BACK_PARAMETER, STANDARD_PARAMETER_DELIMITER);

        return CommandResult.redirect(CONTROLLER_URL + back);
    }

}