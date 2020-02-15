package com.epam.conference.controller.command.impl.admin;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.dto.RequestDto;
import com.epam.conference.service.RequestService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RequestAdminCommand implements Command {

    private final static String REQUESTS_ATTRIBUTE_NAME = "requests";
    private final static String REQUEST_ADMIN_JSP = "/WEB-INF/requestAdmin.jsp";

    private final RequestService requestService;

    public RequestAdminCommand(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<RequestDto> requestDtoList = requestService.getAllDto();
        request.setAttribute(REQUESTS_ATTRIBUTE_NAME, requestDtoList);

        return CommandResult.forward(REQUEST_ADMIN_JSP);
    }

}