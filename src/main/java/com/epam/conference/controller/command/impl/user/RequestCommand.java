package com.epam.conference.controller.command.impl.user;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.dto.RequestDto;
import com.epam.conference.entity.User;
import com.epam.conference.service.RequestService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RequestCommand implements Command {

    private final static String USER_ATTRIBUTE_NAME = "user";
    private final static String REQUESTS_ATTRIBUTE_NAME = "requests";
    private final static String USER_REQUEST_JSP = "/WEB-INF/userRequest.jsp";

    private final RequestService requestService;

    public RequestCommand(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE_NAME);

        List<RequestDto> requests = requestService.getAllWithFullInfoByUserId(user.getId());
        request.setAttribute(REQUESTS_ATTRIBUTE_NAME, requests);

        return CommandResult.forward(USER_REQUEST_JSP);
    }

}