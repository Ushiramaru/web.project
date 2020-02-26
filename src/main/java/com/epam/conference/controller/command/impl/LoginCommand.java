package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.parameter.extractor.RequestParameterExtractor;
import com.epam.conference.controller.command.parameter.validator.RequestParameterValidator;
import com.epam.conference.entity.User;
import com.epam.conference.service.UserService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand extends AbstractCommand {

    private final static String LOGIN_PARAMETER_NAME = "login";
    private final static String PASSWORD_PARAMETER_NAME = "password";
    private final static String USER_ATTRIBUTE_NAME = "user";
    private final static String CONTROLLER_COMMAND_MAIN_PAGE = "controller?command=mainPage";
    private final static String CONTROLLER_COMMAND_LOGIN_PAGE = "controller?command=loginPage";
    private final static String WRONG_DATA_ATTRIBUTE_NAME = "wrong_data";
    private final static String WRONG_BLOCK_ATTRIBUTE_NAME = "wrong_block";
    private final static String INVALID_PARAMETER_MESSAGE = "Invalid ? value";

    private final UserService service;

    public LoginCommand(RequestParameterExtractor parameterExtractor, RequestParameterValidator parameterValidator,
                        UserService service) {
        super(parameterExtractor, parameterValidator);
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        RequestParameterExtractor extractor = super.getParameterExtractor();
        RequestParameterValidator validator = super.getParameterValidator();
        String login = extractor.extractString(request, LOGIN_PARAMETER_NAME);
        if (!validator.isValid(LOGIN_PARAMETER_NAME, login)) {
            throw new ServiceException(INVALID_PARAMETER_MESSAGE.replace("?", LOGIN_PARAMETER_NAME));
        }
        String password = extractor.extractString(request, PASSWORD_PARAMETER_NAME);
        if (!validator.isValid(PASSWORD_PARAMETER_NAME, password)) {
            throw new ServiceException(INVALID_PARAMETER_MESSAGE.replace("?", PASSWORD_PARAMETER_NAME));
        }
        Optional<User> optionalUser = service.login(login, password);

        CommandResult result;
        HttpSession session = request.getSession();
        if (!optionalUser.isPresent()) {
            session.removeAttribute(WRONG_BLOCK_ATTRIBUTE_NAME);
            session.setAttribute(WRONG_DATA_ATTRIBUTE_NAME, true);
            result = CommandResult.redirect(CONTROLLER_COMMAND_LOGIN_PAGE);
        } else {
            User user = optionalUser.get();
            if (!user.isActive()) {
                session.removeAttribute(WRONG_DATA_ATTRIBUTE_NAME);
                session.setAttribute(WRONG_BLOCK_ATTRIBUTE_NAME, true);
                result = CommandResult.redirect(CONTROLLER_COMMAND_LOGIN_PAGE);
            } else {
                session.removeAttribute(WRONG_BLOCK_ATTRIBUTE_NAME);
                session.removeAttribute(WRONG_DATA_ATTRIBUTE_NAME);
                session.setAttribute(USER_ATTRIBUTE_NAME, user);
                result = CommandResult.redirect(CONTROLLER_COMMAND_MAIN_PAGE);
            }
        }

        return result;
    }

}