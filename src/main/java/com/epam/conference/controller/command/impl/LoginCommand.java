package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.ParameterExtractor;
import com.epam.conference.entity.User;
import com.epam.conference.service.UserService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginCommand implements Command {

    private final static String LOGIN_PARAMETER_NAME = "login";
    private final static String PASSWORD_PARAMETER_NAME = "password";
    private final static String LANGUAGE_ATTRIBUTE_NAME = "language";
    private final static String LOCALE_BASE_NAME = "locale";
    private final static String USER_ATTRIBUTE_NAME = "user";
    private final static String WRONG_ATTRIBUTE_NAME = "wrong";
    private final static String WARN_USER_BLOCK_KEY = "warn.userBlock";
    private final static String WARN_WRONG_USER_DATA_KEY = "warn.wrongUserData";
    private final static String PROPERTY_FILE_EXTENSION = ".properties";
    private final static String LOCALE_DELIMITER = "_";
    private final static String CONTROLLER_COMMAND_MAIN_PAGE = "controller?command=mainPage";
    private final static String CONTROLLER_COMMAND_LOGIN_PAGE = "controller?command=loginPage";

    private UserService service;

    public LoginCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ParameterExtractor extractor = new ParameterExtractor();
        String login = extractor.extractParameter(request, LOGIN_PARAMETER_NAME);
        String password = extractor.extractParameter(request, PASSWORD_PARAMETER_NAME);
        Optional<User> optionalUser = service.login(login, password);

        CommandResult result;
        String attributeName;
        Object attributeValue;
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(LANGUAGE_ATTRIBUTE_NAME);
        ResourceBundle language;
        if (LoginCommand.class.getClassLoader().getResource(LOCALE_BASE_NAME + LOCALE_DELIMITER + locale + PROPERTY_FILE_EXTENSION) == null) {
            language = ResourceBundle.getBundle(LOCALE_BASE_NAME);
        } else {
            language = ResourceBundle.getBundle(LOCALE_BASE_NAME + LOCALE_DELIMITER + locale);
        }
        boolean isLogin = false;
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            isLogin = user.isActive();
            if (isLogin) {
                attributeName = USER_ATTRIBUTE_NAME;
                attributeValue = user;
            } else {
                attributeName = WRONG_ATTRIBUTE_NAME;
                attributeValue = language.getString(WARN_USER_BLOCK_KEY);
            }
        } else {
            attributeName = WRONG_ATTRIBUTE_NAME;
            attributeValue = language.getString(WARN_WRONG_USER_DATA_KEY);
        }

        session.setAttribute(attributeName, attributeValue);
        if (isLogin) {
            session.removeAttribute(WRONG_ATTRIBUTE_NAME);
            result = CommandResult.redirect(CONTROLLER_COMMAND_MAIN_PAGE);
        } else {
            result = CommandResult.redirect(CONTROLLER_COMMAND_LOGIN_PAGE);
        }

        return result;
    }

}