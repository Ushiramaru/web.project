package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.service.RequestService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestRejectCommand implements Command {

    private final RequestService requestService;

    public RequestRejectCommand(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long requestId = Long.valueOf(request.getParameter("request_id"));
        requestService.rejectById(requestId);

        return CommandResult.redirect("controller?command=requestAdmin");
    }

}