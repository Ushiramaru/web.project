package com.epam.conference.controller.command.impl.admin;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.entity.User;
import com.epam.conference.service.UserService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserAdminCommand implements Command {

    private final static String USERS_ATTRIBUTE_NAME = "users";
    private final static String USER_ADMIN_JSP = "/WEB-INF/userAdmin.jsp";

    private final UserService userService;

    public UserAdminCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<User> users = userService.getAll();
        request.setAttribute(USERS_ATTRIBUTE_NAME, users);

        return CommandResult.forward(USER_ADMIN_JSP);
    }

}