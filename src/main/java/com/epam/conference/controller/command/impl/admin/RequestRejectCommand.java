package com.epam.conference.controller.command.impl.admin;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.ParameterExtractor;
import com.epam.conference.service.RequestService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestRejectCommand implements Command {

    private final static String REQUEST_ID_PARAMETER_NAME = "request_id";
    private final static String CONTROLLER_COMMAND_REQUEST_ADMIN = "controller?command=requestAdmin";

    private final RequestService requestService;

    public RequestRejectCommand(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ParameterExtractor extractor = new ParameterExtractor();
        Long requestId = Long.valueOf(extractor.extractParameter(request, REQUEST_ID_PARAMETER_NAME));
        requestService.rejectById(requestId);

        return CommandResult.redirect(CONTROLLER_COMMAND_REQUEST_ADMIN);
    }

}