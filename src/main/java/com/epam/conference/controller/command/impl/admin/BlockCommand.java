package com.epam.conference.controller.command.impl.admin;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.ParameterExtractor;
import com.epam.conference.service.UserService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockCommand implements Command {

    private final static String CONTROLLER_COMMAND_USER_ADMIN = "controller?command=userAdmin";
    private final static String USER_ID_PARAMETER_NAME = "user_id";

    private final UserService userService;

    public BlockCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ParameterExtractor extractor = new ParameterExtractor();
        Long blockUserId = Long.valueOf(extractor.extractParameter(request, USER_ID_PARAMETER_NAME));
        userService.blockById(blockUserId);

        return CommandResult.redirect(CONTROLLER_COMMAND_USER_ADMIN);
    }

}