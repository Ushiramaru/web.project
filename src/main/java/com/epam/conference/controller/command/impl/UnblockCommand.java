package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.service.UserService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnblockCommand implements Command {

    private final UserService userService;

    public UnblockCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long unblockUserId = Long.valueOf(request.getParameter("user_id"));
        userService.unblockById(unblockUserId);

        return CommandResult.redirect("controller?command=userAdmin");
    }

}