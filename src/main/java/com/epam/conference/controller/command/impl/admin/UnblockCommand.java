package com.epam.conference.controller.command.impl.admin;

import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.impl.AbstractCommand;
import com.epam.conference.controller.command.parameter.extractor.RequestParameterExtractor;
import com.epam.conference.controller.command.parameter.validator.RequestParameterValidator;
import com.epam.conference.service.UserService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnblockCommand extends AbstractCommand {

    private final static String USER_ID_PARAMETER_NAME = "user_id";
    private final static String CONTROLLER_COMMAND_USER_ADMIN = "controller?command=userAdmin";
    private final static String INVALID_PARAMETER_MESSAGE = "Invalid ? value";

    private final UserService userService;

    public UnblockCommand(RequestParameterExtractor parameterExtractor, RequestParameterValidator parameterValidator,
                          UserService userService) {
        super(parameterExtractor, parameterValidator);
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        RequestParameterExtractor extractor = super.getParameterExtractor();
        RequestParameterValidator validator = super.getParameterValidator();
        Long unblockUserId = extractor.extractLong(request, USER_ID_PARAMETER_NAME);
        if (!validator.isValid(USER_ID_PARAMETER_NAME, unblockUserId)) {
            throw new ServiceException(INVALID_PARAMETER_MESSAGE.replace("?", USER_ID_PARAMETER_NAME));
        }
        userService.unblockById(unblockUserId);

        return CommandResult.redirect(CONTROLLER_COMMAND_USER_ADMIN);
    }

}