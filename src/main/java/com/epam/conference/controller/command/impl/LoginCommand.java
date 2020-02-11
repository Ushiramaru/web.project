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

    private static final String LOGIN_PARAMETER_NAME = "login";
    private static final String PASSWORD_PARAMETER_NAME = "password";
    private static final String LANGUAGE_ATTRIBUTE_NAME = "language";
    private static final String LOCALE_BASE_NAME = "locale";
    private static final String USER_ATTRIBUTE_NAME = "user";
    private static final String WRONG_ATTRIBUTE_NAME = "wrong";
    private static final String CONTROLLER_COMMAND_MAIN_PAGE = "controller?command=mainPage";
    private static final String CONTROLLER_COMMAND_LOGIN_PAGE = "controller?command=loginPage";
    private static final String WARN_USER_BLOCK_KEY = "warn.userBlock";
    private static final String WARN_WRONG_USER_DATA_KEY = "warn.wrongUserData";

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
        if (LoginCommand.class.getClassLoader().getResource(LOCALE_BASE_NAME + "_" + locale + ".properties") == null) {
            language = ResourceBundle.getBundle(LOCALE_BASE_NAME);
        } else {
            language = ResourceBundle.getBundle(LOCALE_BASE_NAME + "_" + locale);
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