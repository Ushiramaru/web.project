package com.epam.conference.controller.command.impl.admin;

import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.impl.AbstractCommand;
import com.epam.conference.controller.command.parameter.extractor.RequestParameterExtractor;
import com.epam.conference.controller.command.parameter.validator.RequestParameterValidator;
import com.epam.conference.service.RequestService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestAcceptCommand extends AbstractCommand {

    private final static String REQUEST_ID_PARAMETER_NAME = "request_id";
    private final static String CONTROLLER_COMMAND_REQUEST_ADMIN = "controller?command=requestAdmin";
    private final static String INVALID_PARAMETER_MESSAGE = "Invalid ? value";

    private final RequestService requestService;

    public RequestAcceptCommand(RequestParameterExtractor parameterExtractor,
                                RequestParameterValidator parameterValidator, RequestService requestService) {
        super(parameterExtractor, parameterValidator);
        this.requestService = requestService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        RequestParameterExtractor extractor = super.getParameterExtractor();
        RequestParameterValidator validator = super.getParameterValidator();
        Long requestId = extractor.extractLong(request, REQUEST_ID_PARAMETER_NAME);
        if (!validator.isValid(REQUEST_ID_PARAMETER_NAME, requestId)) {
            throw new ServiceException(INVALID_PARAMETER_MESSAGE.replace("?", REQUEST_ID_PARAMETER_NAME));
        }
        requestService.acceptById(requestId);

        return CommandResult.redirect(CONTROLLER_COMMAND_REQUEST_ADMIN);
    }

}