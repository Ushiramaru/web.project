package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {

    private final static String USER_ATTRIBUTE_NAME = "user";
    private final static String CONTROLLER_COMMAND_LOGIN_PAGE = "controller?command=loginPage";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        request.getSession().removeAttribute(USER_ATTRIBUTE_NAME);

        return CommandResult.redirect(CONTROLLER_COMMAND_LOGIN_PAGE);
    }

}