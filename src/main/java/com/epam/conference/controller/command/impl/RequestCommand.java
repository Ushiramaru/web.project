package com.epam.conference.controller.command.impl;

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

    private final RequestService requestService;

    public RequestCommand(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<RequestDto> requests = requestService.getAllByUserId(user.getId());
        request.setAttribute("requests", requests);

        return CommandResult.forward("/WEB-INF/userRequest.jsp");
    }

}