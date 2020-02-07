package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.service.UserService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockCommand implements Command {

    private final UserService userService;

    public BlockCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long blockUserId = Long.valueOf(request.getParameter("user_id"));
        userService.blockById(blockUserId);

        return CommandResult.redirect("controller?command=userAdmin");
    }

}