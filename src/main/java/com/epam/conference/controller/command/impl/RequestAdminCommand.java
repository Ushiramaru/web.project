package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.ParameterExtractor;
import com.epam.conference.dto.RequestDto;
import com.epam.conference.service.RequestService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RequestAdminCommand implements Command {

    private final RequestService requestService;

    public RequestAdminCommand(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ParameterExtractor extractor = new ParameterExtractor();
        List<RequestDto> requestDtoList = requestService.getAllDto();
        request.setAttribute("requests",requestDtoList);

        return CommandResult.forward("/WEB-INF/requestAdmin.jsp");
    }

}